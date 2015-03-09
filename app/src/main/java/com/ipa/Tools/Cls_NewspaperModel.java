package com.ipa.Tools;

import java.io.Serializable;

public class Cls_NewspaperModel implements Serializable{
	public static final long serialVersionUID =256516156156L;
	public int newsID;
	public  int Groupid;
	public int senderid;
	public  int subgroupid;
	public String imagepath;
	public String text;
	public String userName;
	

	public Cls_NewspaperModel() {
	}

	
	public Cls_NewspaperModel(int newsID, int userID, String userName, String text, int groupID, int subGroupID, String imageAddress)
	{
		this.newsID = newsID;
		this.senderid = userID;
		this.userName = userName;
		this.text = text;
		this.Groupid = groupID;
		this.subgroupid = subGroupID;
		this.imagepath = imageAddress;
	}
}
