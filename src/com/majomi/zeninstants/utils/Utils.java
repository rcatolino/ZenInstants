package com.majomi.zeninstants.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.majomi.zeninstants.AppLog;
import com.majomi.zeninstants.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

public class Utils {
	
	private static Context context = null;

	private static final String prefFileName = "ZenInstantsDef";

	/**
	 * Help function to serialize an object into a string
	 * @param o
	 * @return
	 */
	public static String serializeObject(Object o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(o);
			out.close();

			// Get the bytes of the serialized object
			byte[] buf = bos.toByteArray();

			return Base64.encodeToString(buf, Base64.DEFAULT);
		} catch (IOException ioe) {
			Log.e("serializeObject", "error", ioe);

			return null;
		}
	}

	/**
	 * Help function to deserialize an object from a string
	 * @param b
	 * @return
	 */
	public static Object deserializeObject(String b) {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(Base64.decode(b, Base64.DEFAULT)));
			Object object = in.readObject();
			in.close();

			return object;
		} catch (ClassNotFoundException cnfe) {
			Log.e("deserializeObject", "class not found error", cnfe);

			return null;
		} catch (IOException ioe) {
			Log.e("deserializeObject", "io error", ioe);

			return null;
		}
	}
	
	public static boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context contextt) {
		context = contextt;
	}
	
	public static Object getObjectFromSharedPreferences(String key) {
		if(context.getSharedPreferences(prefFileName, 0).contains(key)) {
			return Utils.deserializeObject(context.getSharedPreferences(prefFileName, 0).getString(key, ""));
		} else {
			return null;
		}
	}
	
	public static void putObjectIntoSharedPreferences(String key, Object obj) {
		SharedPreferences.Editor editor = context.getSharedPreferences(prefFileName, 0).edit();

		String s = serializeObject(obj);

		if(context.getSharedPreferences(prefFileName, 0).contains(key)) {
			editor.remove(key); 
		}

		editor.putString(key, s);

		editor.commit();
	}
	
	/************************************* data utils ***********************************/
	
	public static String saveData(byte[] bytes){
		String fileName = new String();
		try {
			File f = File.createTempFile("img", null, context.getCacheDir());
			FileOutputStream fos = new FileOutputStream(f); //openFileOutput(getCacheDir()+"plop", Context.MODE_PRIVATE);
			fos.write(bytes);
			fos.close();
			fileName = f.getAbsolutePath();
			AppLog.logString(" Files saved :" + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static byte[] loadData(String path){
		FileInputStream f;
		try {
			f = new FileInputStream(path);
			return readBytes(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Bitmap loadDataAsImage(String path){
		byte[] imageTile = loadData(path);
		if(imageTile != null)
			return BitmapFactory.decodeByteArray(imageTile, 0, imageTile.length);
		return null;
	}

	public static byte[] readBytes(InputStream inputStream) throws IOException {
		  // this dynamically extends to take the bytes you read
		  ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		  // this is storage overwritten on each iteration with bytes
		  int bufferSize = 1024;
		  byte[] buffer = new byte[bufferSize];

		  // we need to know how may bytes were read to write them to the byteBuffer
		  int len = 0;
		  while ((len = inputStream.read(buffer)) != -1) {
		    byteBuffer.write(buffer, 0, len);
		  }

		  // and then we can return your byte array.
		  return byteBuffer.toByteArray();
		}
	
/**************************** TO REMOVE ********************************************/	
	public static String saveDemoImage(){
		InputStream is = context.getResources().openRawResource(R.drawable.video_icon);
		try {
			return Utils.saveData(Utils.readBytes(is));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
