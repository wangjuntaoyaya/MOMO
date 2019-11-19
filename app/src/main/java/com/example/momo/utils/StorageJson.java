package com.example.momo.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
  
public class StorageJson {
	File file;

	public StorageJson(String data) {
		file = new File(Environment.getDataDirectory(), "myjson.txt");
		if (!file.exists()) {

			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
 
		}
		warpData(data);
	}

	private void warpData(String data) {

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(data.getBytes());
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
