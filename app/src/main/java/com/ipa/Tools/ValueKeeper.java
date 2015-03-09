package com.ipa.Tools;


import android.os.Environment;
public class ValueKeeper {

	public static boolean SHOWLOG = true;
	public static String DataBasePath = Environment.getExternalStorageDirectory() + "/data/data/com.ipa.baghbadoran_panel";
	//public static String DataBasePathflash = Environment.getExternalStorageDirectory() + "/data/data/com.ipa.flashcard/FlashCards/Package";
	public static String DataBaseName = "BaghbahadoranDB";
	public static String SoundPath = "Sounds";
	public static boolean PlayAudioAlerts = true;
//	public static String DatabaseEducatePath = Environment.getExternalStorageDirectory() + "/data/data/com.ipa.flashcard";
//	public static String DatabaseEducateName = "EducateInfo";
	//public static String PackageName = Environment.getExternalStorageDirectory() + "/data/data/com.ipa.flashcard/";
	public static final String WSURL1 = "/baghbahadoranservice.asmx";
	public static final String SOAP_ACTION1 = "http://tempuri.org/MobileReq";
	public static final String NAMESPACE1 = "http://tempuri.org/";
	public static String ServerURL1 = "http://hadiservices.ir/";
	public static String pdf = Environment.getExternalStorageDirectory() + "/cachedPdfs/";
	public static String ServerPath = "http://mtrain.ir/baghbahadoran/pictures/usersPic/";
	public static String GalleryImagepath = Environment.getExternalStorageDirectory()+"/data/data/com.ipa.baghbadoran/galleryimages/";
	public static String tag = "KFL";
	public static int ImageQuality = 0;
	public static String Sep1 = "|";
	public static String Sep2 = "~";
	public static char Sp1 = '|';
	public static int ImageSizePercent = 100;
	public static String competitionID = "1"; 
	public static char Sp2 = '~';
	public static char Sp3=',';
	public static String FontName = "BYekan.ttf";
	public static String FontNameTitle = "BTitrBd.ttf";
	public static final String WSURL = "/BaghbahadoranService.asmx";
	public static final String SOAP_ACTION = "http://tempuri.org/MobileReq";
	public static final String METHOD_NAME = "MobileReq";
	public static final String NAMESPACE = "http://tempuri.org/";
	// public static String ServerURL = "http://mobileapp.kanoon.ir";
	public static String ServerURL = "http://hadiservices.ir";
	public static String Password = "";
	public static String Name = "";
	public static String UserName = "";
	public static boolean DontHideLoading = false;
	public static String AppVersion = "1.0";

}