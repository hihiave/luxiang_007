package com.lx.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 索引时间：用来记录或获取上一次创建索引的时间
 */
public class ToolIndexTime {

	/** 设置最新一次创建索引的时间
	 * @author luxiang
	 * @param latestIndexTime 最新一次创建索引的时间
	 * @param indexTimeDir 记录时间的文件地址 "C:\\lucene\\indextime\\time.txt"
	 * @return boolean,true表示记录成功,false表示记录失败
	 */
	public static boolean setLatestIndexTime(String latestIndexTime, String indexTimeDir) {
		// 写入
		OutputStreamWriter writer = null;
		BufferedWriter bufferedWriter = null;
		boolean flag = false;
		try {
			File file = new File(indexTimeDir);
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(new String(latestIndexTime.getBytes("gbk"), "utf-8"));
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/** 获取最新一次创建索引的时间
	 * @author luxiang
	 * @param indexTimeDir 记录时间的文件地址
	 * @return 
	 */
	public static int getLatestIndexTime(String indexTimeDir) {
		// 读出
		int latestIndexTime = 2147483647;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {

			File file = new File(indexTimeDir);
			if (!file.exists()) {
				file.createNewFile();
			}

			fileReader = new FileReader(indexTimeDir);
			bufferedReader = new BufferedReader(fileReader);
			String temp = bufferedReader.readLine();
			if (temp != null && !temp.trim().equals("")) {
				latestIndexTime = Integer.valueOf(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return latestIndexTime;
	}

}
