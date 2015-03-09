package com.ipa.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MediaHelper {

	private static MediaPlayer mp;

	public static void PlayAudioFile(String path, String fileName) {
		// set up MediaPlayer
		InitPlayer();
		try {
			mp.setDataSource(path + "/" + fileName);
			mp.prepare();
			mp.start();
		} catch (Exception e) {
            MethodHelper.ShowErrorLog(null,
					"Error Play Music: " + e.getMessage());
		}
	}
	
	public void ReadFromAssets(Activity act,String fileName)
	{
			InitPlayer();
			try
			{
				AssetFileDescriptor afd = act.getAssets().openFd(fileName);
				mp = new MediaPlayer();
				mp.setDataSource(afd.getFileDescriptor());
				mp.prepare();
				mp.start();
			}
			catch(IOException e)
			{
				e.getMessage();
			}
	}

	public enum BeepTypes {
		Error, Warning, Information, Question
	}

	public static void PlayBeep(Context context, BeepTypes beepType) {
		try {
			InitPlayer();
			String FileName = "info.mp3";
			if (beepType == BeepTypes.Error)
				FileName = "error.mp3";
			else if (beepType == BeepTypes.Information)
				FileName = "info.mp3";
			else if (beepType == BeepTypes.Question)
				FileName = "ques.mp3";
			else if (beepType == BeepTypes.Warning)
				FileName = "warning.mp3";

			AssetFileDescriptor descriptor = context.getAssets().openFd(
					FileName);
			mp.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(), descriptor.getLength());
			descriptor.close();

			mp.prepare();
			// mp.setVolume(1f, 1f);
			mp.setLooping(false);
			mp.start();
		} catch (Exception e) {
            MethodHelper
					.ShowErrorLog(null, "Error Play Beep: " + e.getMessage());
		}
	}

	public static void StopPlayer() {
		if (mp != null) {
			if (mp.isPlaying()) {
				mp.stop();
				mp.release();
				mp = null;
			}
		}
	}

	public static void InitPlayer() {
		StopPlayer();
		mp = new MediaPlayer();
	}

}
