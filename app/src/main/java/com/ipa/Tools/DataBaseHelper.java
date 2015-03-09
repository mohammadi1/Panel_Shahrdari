/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipa.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

/**
 * 
 * @author MrHadi
 */
public class DataBaseHelper {

	String DataBasePath = "";
	String DataBaseName = "";

	public DataBaseHelper() {
		DataBasePath = ValueKeeper.DataBasePath;
		DataBaseName = ValueKeeper.DataBaseName;
		initDB();
	}

	public DataBaseHelper(String DBPath, String DBName) {
		DataBaseName = DBName;
		DataBasePath = DBPath;
		initDB();
	}

	public void initDB() {
		try {
			File file = new File(DataBasePath);
			file.mkdirs();
			MethodHelper.ShowDebugLog(null, "Creating Directories: " + file.mkdirs());
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);

//			db.execSQL("CREATE TABLE IF NOT EXISTS tblShoraMembers (MemberID INTEGER PRIMARY KEY,Name NVARCHAR,Family NVARCHAR,Semat NVARCHAR,Savabegh NVARCHAR,Tahsilat NVARCHAR);");
//			db.execSQL("CREATE TABLE IF NOT EXISTS tblTextValues (ID INTEGER PRIMARY KEY AUTOINCREMENT,Key NVARCHAR,Value NVARCHAR,LanguaeID INTEGER);");
//			db.execSQL("CREATE TABLE IF NOT EXISTS tblShahrdar (ShahrdarID INTEGER PRIMARY KEY,Name NVARCHAR,Family NVARCHAR,Sabeghe NVARCHAR,Tahsilat NVARCHAR,Check INTEGER);");
//			db.execSQL("CREATE TABLE IF NOT EXISTS tblBusSchedule (BusID INTEGER PRIMARY KEY,lineNumber INTEGER,Mabdae NVARCHAR,MAGHSAD NVARCHAR,Start INTEGER,End INTEGER,Schedule Integer,Count INTEGER,Type INTEGER);");
			
			db.close();

			MethodHelper.ShowDebugLog(null, "DataBase:" + DataBaseName + " Initialized!");
		} catch (Exception e) {
			MethodHelper.ShowErrorLog(null, "initDB Error: " + e.getMessage());
		}
	}

	public void InsertIntoDB(String tblName, Object[] Values) {
		try {
			String st = "";
			for (int i = 0; i < Values.length; i++) {
				if (st != "") {
					st += ",";
				}
				if (Values[i].getClass() == String.class) {
					st += "\'" + Values[i] + "\'";
				} else {
					st += Values[i];
				}
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			MethodHelper.ShowDebugLog(null, "inserting Data to DB: " + DataBaseName + " Table: " + tblName);
			db.execSQL("INSERT INTO " + tblName + " VALUES ( " + st + ");");
			db.close();

			MethodHelper.ShowDebugLog(null, "Insert Into DB Success!");
		} catch (Exception ex) {
			Log.e(MethodHelper.tag, "Error Inserting Into DB : " + ex.getMessage());
		}
	}

	public long InsertIntoDB(String tblName, String[] ParamNames, Object[] ParamValues) {
		try {
			long ret = 0;
			String st = "";
			String st2 = "";
			for (int i = 0; i < ParamNames.length; i++) {
				if (st != "") {
					st += ",";
					st2 += ",";
				}
				st += ParamNames[i];
				st2 += "?";
			}
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			MethodHelper.ShowDebugLog(null, "inserting Data to DB: " + DataBaseName + " Table: " + tblName);
			String sql = "INSERT INTO " + tblName + " ( " + st + " ) " + " VALUES ( " + st2 + ");";
			SQLiteStatement insertStmt = db.compileStatement(sql);
			insertStmt.clearBindings();
			for (int i = 0; i < ParamValues.length; i++) {
				if (ParamValues[i] == null) {
					continue;
				}
				if (ParamValues[i] instanceof Long) {
					insertStmt.bindLong(i + 1, Long.parseLong(ParamValues[i].toString()));
				} else if (ParamValues[i] instanceof Double) {
					insertStmt.bindDouble(i + 1, Double.parseDouble(ParamValues[i].toString()));
				} else if (ParamValues[i] instanceof Blob || ParamValues[i] instanceof byte[]) {
					insertStmt.bindBlob(i + 1, (byte[]) ParamValues[i]);
				} else {
					insertStmt.bindString(i + 1, ParamValues[i].toString());
				}
			}
			ret = insertStmt.executeInsert();
			db.close();
			MethodHelper.ShowDebugLog(null, "Insert Into DB Success!");
			return ret;
		} catch (Exception ex) {
			Log.e(ValueKeeper.tag, "Error Inserting Into DB : " + ex.getMessage());
			return -1;
		}
	}

	public Cursor ReadfromDB(String tblName, String Condition) {
		SQLiteDatabase db = null;
		try {
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				Condition = " WHERE " + Condition;
			}
			MethodHelper.ShowDebugLog(null, "Raeading DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			Cursor c = db.rawQuery("SELECT * FROM " + tblName + Condition, null);

			c.moveToFirst();
			db.close();
			MethodHelper.ShowDebugLog(null, "Read From Table " + tblName + " Success! RowCount:" + c.getCount());
			if (c.getCount() == 0)
				c = null;
			return c;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Reading From DB: " + e.getMessage());
			db.close();
			return null;
		}
	}
	
	public Cursor ReadfromDB(String tblName, String Condition,String orderBy,String sort) {
		SQLiteDatabase db = null;
		try {
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				Condition = " WHERE " + Condition;
			}
			if(orderBy == null || orderBy == "")
			{
				orderBy = "";
			}
			else
			{
				if(sort == null || sort.equalsIgnoreCase("asc"))
				{
					orderBy = " Order By " + orderBy;
				}
				else if(sort.equalsIgnoreCase("desc"))
				{
					orderBy = " Order By " + orderBy + " desc";
				}
			}
			MethodHelper.ShowDebugLog(null, "Raeading DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			Cursor c = db.rawQuery("SELECT * FROM " + tblName + Condition+orderBy, null);

			c.moveToFirst();
			db.close();
			MethodHelper.ShowDebugLog(null, "Read From Table " + tblName + " Success! RowCount:" + c.getCount());
			if (c.getCount() == 0)
				c = null;
			return c;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Reading From DB: " + e.getMessage());
			db.close();
			return null;
		}
	}
	
	public Cursor ReadCountfromDB(String tblName, String Condition) {
		SQLiteDatabase db = null;
		try {
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				Condition = " WHERE " + Condition;
			}
			MethodHelper.ShowDebugLog(null, "Raeading DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			Cursor c = db.rawQuery("SELECT  COUNT(*) FROM " + tblName + Condition, null);

			c.moveToFirst();
			db.close();
			MethodHelper.ShowDebugLog(null, "Read From Table " + tblName + " Success! RowCount:" + c.getCount());
			if (c.getCount() == 0)
				c = null;
			return c;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Reading From DB: " + e.getMessage());
			db.close();
			return null;
		}
	}
	
	public Cursor ReadMaxColumnfromDB(String tblName,String ColumnName, String Condition) {
		SQLiteDatabase db = null;
		try {
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				Condition = " WHERE " + Condition;
			}
			MethodHelper.ShowDebugLog(null, "Raeading DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			Cursor c = db.rawQuery("SELECT  MAX("+ColumnName+") FROM " + tblName + Condition, null);

			c.moveToFirst();
			db.close();
			MethodHelper.ShowDebugLog(null, "Read From Table " + tblName + " Success! RowCount:" + c.getCount());
			if (c.getCount() == 0)
				c = null;
			return c;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Reading From DB: " + e.getMessage());
			db.close();
			return null;
		}
	}

	public int DeleteFromDB(String tblName, String Condition) {
		SQLiteDatabase db = null;
		try {
			int ret = 0;
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				// Condition = " WHERE " + Condition;
			}
			MethodHelper.ShowDebugLog(null, "Deleting From DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			ret = db.delete(tblName, Condition, null);
			db.close();
			MethodHelper.ShowDebugLog(null, "Delete From Table " + tblName + " Success! Affected Rows:" + ret);

			return ret;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Deleting From DB: " + e.getMessage());
			db.close();
			return -1;
		}
	}

	// public String[] ConvertCursorToStringArray(Cursor cr){
	// String[] outp=null;
	//
	// try{
	// outp=new String[cr.getCount()];
	// }catch (Exception e) {
	// }
	//
	// return outp;
	// }

	public static boolean copyDatabaseFromAssets(Context context, String DBasePath, String DB_Name) {
		boolean retValue = false;
	
		
		try {
			InputStream is = context.getAssets().open(DB_Name);
			File dbFile = new File(DBasePath + "/" + DB_Name);
			//boolean CopyDataBase=SharedPrefrencesHelper.getBooleanPref(context, ValueKeeper.getAppVersion(context)+DB_Name, false);
//			if(CopyDataBase && dbFile.exists())
//				return true;
//			if (dbFile.exists()) {
//				int sz=(int)dbFile.length();
//				int szis=is.available();
//				if (sz==szis) {
//					MethodHelper.ShowDebugLog(null, "Copy Database From Assets: DataBase Exists!");
//					return true;
//				}
//			}
//			if(!dbFile.exists())
//			{
			OutputStream os = new FileOutputStream(dbFile);

			byte[] buffer = new byte[1024];
			while (is.read(buffer) > 0) {
				os.write(buffer);
			}

			os.flush();
			os.close();
			is.close();
			retValue = true;
			MethodHelper.ShowDebugLog(null, "Copy Database From Assets Succeed!");
//			}
		} catch (Exception e) {
			MethodHelper.ShowErrorLog(null, "Error Copy Database From Assets: " + e.getLocalizedMessage());
		}
		SharedPrefrencesHelper.setBooleanPref(context, ValueKeeper.AppVersion+ValueKeeper.DataBaseName, true);
		return retValue;
	}
	
	
	public Cursor ReadTopfromDB(String tblName, String Condition, String OrderBy, int ROWS) {
		SQLiteDatabase db = null;
		try {
			if (OrderBy == null || OrderBy == "") {
				OrderBy = "";
			} else {
				OrderBy = " ORDER BY " + OrderBy;
			}
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				Condition = " WHERE " + Condition;
			}
			MethodHelper.ShowDebugLog(null, "Raeading DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			Cursor c = db.rawQuery("SELECT * FROM " + tblName + Condition + OrderBy+" LIMIT "+ROWS, null);

			c.moveToFirst();
			db.close();
			MethodHelper.ShowDebugLog(null, "Read From Table " + tblName + " Success! RowCount:" + c.getCount());
			// if (c.getCount() == 0)
			// c = null;
			return c;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Reading From DB: " + e.getMessage());
			db.close();
			return null;
		}
	}
	
	public Cursor ReadTopDescfromDB(String tblName, String Condition, String OrderBy, int ROWS) {
		SQLiteDatabase db = null;
		try {
			if (OrderBy == null || OrderBy == "") {
				OrderBy = "";
			} else {
				OrderBy = " ORDER BY " + OrderBy + " Desc";
			}
			if (Condition == null || Condition == "") {
				Condition = "";
			} else {
				Condition = " WHERE " + Condition;
			}
			MethodHelper.ShowDebugLog(null, "Raeading DB:" + DataBaseName + " Table:" + tblName + " Condition:" + Condition);
			db = SQLiteDatabase.openOrCreateDatabase(DataBasePath + "/" + DataBaseName, null);
			Cursor c = db.rawQuery("SELECT * FROM " + tblName + Condition + OrderBy+" LIMIT "+ROWS, null);
			
			c.moveToFirst();
			db.close();
			MethodHelper.ShowDebugLog(null, "Read From Table " + tblName + " Success! RowCount:" + c.getCount());
			// if (c.getCount() == 0)
			// c = null;
			return c;
		} catch (Exception e) {
			Log.e(ValueKeeper.tag, "Error Reading From DB: " + e.getMessage());
			db.close();
			return null;
		}
	}
}