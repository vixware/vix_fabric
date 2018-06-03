package com.vix.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 获取sql文件中执行插入的sql语句
 * 
 * com.vix.test.FileOption
 *
 * @author zhanghaibing
 *
 * @date 2014年9月4日
 */
public class FileOption {

	/**
	 * 根据路径读取文件 *
	 * 
	 * @param readPath
	 *            读取文件的路径
	 * @return
	 * @throws Exception
	 */
	public String readFile(String readPath) throws Exception {
		return readFile(new File(readPath));
	}

	/**
	 * 读取文件
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String readFile(File file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuffer sbf = new StringBuffer("");
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("INSERT")) {
				sbf.append(line).append("\r\n");
				// 按行读取，追加换行\r\n
			}
		}
		br.close();
		return sbf.toString();
	}

	/**
	 * 写入文件 *
	 * 
	 * @param str
	 *            要保存的内容
	 * @param savePath
	 *            保存的文件路径
	 * @throws Exception
	 *             找不到路径
	 */
	public void writeFile(String str, String savePath) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(savePath));
		bw.write(str);
		bw.close();
	}

	public static void main(String[] args) {
		FileOption fop = new FileOption();
		String filePath = "d:/vix.sql";
		String str = null;
		try {
			str = fop.readFile(filePath);
			System.out.println(str);
		} catch (Exception e) {
			System.out.println("文件不存在");
		}
		String savePath = "d:/newvix.sql";
		// 将上一个读取的文件另存一份
		try {
			fop.writeFile(str, savePath);
		} catch (Exception e) {
			System.out.println("保存文件失败（路径错误）");
		}
	}
}
