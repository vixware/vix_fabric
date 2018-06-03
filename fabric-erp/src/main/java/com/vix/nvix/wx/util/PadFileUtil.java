package com.vix.nvix.wx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;

public class PadFileUtil {

	public static void writeString2File(String text, String filePathAndName) throws Exception {
		if (filePathAndName == null || "".equals(filePathAndName.trim()))
			throw new Exception("Corrupt File Path and Name !!");

		File targetFile = null;
		DataOutputStream dos = null;
		BufferedWriter bw = null;

		BufferedReader br = null;
		try {
			targetFile = prepareTargetFile(filePathAndName, true);

			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(targetFile)));
			bw = new BufferedWriter(new OutputStreamWriter(dos));

			br = new BufferedReader(new StringReader(text));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				bw.write(temp);
				bw.newLine();
			}
			// bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (dos != null)
					dos.close();
				if (br != null)
					br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dos = null;
			bw = null;
			br = null;
			targetFile = null;
		}
	}

	public static void File2FileCopy(String sourcefile, String targerfile) throws Exception {
		File sfile = null, tfile = null;
		try {
			sfile = findSourceFile(sourcefile, true);
			tfile = prepareTargetFile(targerfile, false);

			File2FileCopy(sfile, tfile);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sfile != null)
				sfile = null;
			if (tfile != null)
				tfile = null;
		}
	}

	public static void File2FileCopy(File sourcefile, File targetfile) throws Exception {
		if (sourcefile == null || targetfile == null || !sourcefile.exists() || sourcefile.isDirectory())
			throw new Exception();

		if (targetfile.isDirectory()) {
			targetfile = new File(targetfile.getPath() + File.separator + sourcefile.getName());
		} else {
			fixFileParentDirs(targetfile);
		}

		BufferedReader breader = null;
		BufferedWriter bwriter = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(sourcefile)));
			// breader = new BufferedReader(new InputStreamReader(dis));
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(targetfile)));
			// bwriter = new BufferedWriter(new OutputStreamWriter(dos));
			int len = 0;
			byte[] b = new byte[1024 * 10];
			while ((len = dis.read(b)) > 0) {
				dos.write(b, 0, len);
			}
			// bwriter.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (breader != null)
					breader.close();
				if (bwriter != null)
					bwriter.close();
				if (dis != null)
					dis.close();
				if (dos != null)
					dos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dis = null;
			dos = null;
			breader = null;
			bwriter = null;
		}
	}

	public void DirectoryCopy(String sourceDir, String targerDir) throws Exception {
		File sfile = null, tfile = null;
		try {
			sfile = findSourceFile(sourceDir, false);
			tfile = prepareTargetFile(targerDir, false);

			this.DirectoryCopy(sfile, tfile);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sfile != null)
				sfile = null;
			if (tfile != null)
				tfile = null;
		}
	}

	public void DirectoryCopy(File sourceDir, File targetDir) throws Exception {
		if (sourceDir == null || targetDir == null || !sourceDir.exists() || !targetDir.exists())
			throw new Exception();
		if (!sourceDir.isDirectory() || !targetDir.isDirectory()) {
			PadFileUtil.File2FileCopy(sourceDir, targetDir);
		} else {
			this.goodDirectoryCopy(sourceDir, targetDir);
		}
	}

	private void goodDirectoryCopy(File sourceDir, File targetDir) throws Exception {
		File[] sfiles = null;
		File copyTo = null;
		try {
			sfiles = sourceDir.listFiles();
			for (int i = 0; i < sfiles.length; i++) {
				if (sfiles[i].isFile()) {
					PadFileUtil.File2FileCopy(sfiles[i], targetDir);
				} else if (sfiles[i].isDirectory()) {
					String newdir = targetDir.getPath() + File.separator + sfiles[i].getName();
					copyTo = new File(newdir);
					copyTo.mkdirs();
					this.goodDirectoryCopy(sfiles[i], copyTo);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (sfiles != null)
				sfiles = null;
			if (copyTo != null)
				copyTo = null;
		}
	}

	public String readFile2String(String PathAndName) throws Exception {
		File file = PadFileUtil.findSourceFile(PathAndName, true);
		try {
			return this.readFile2String(file);
		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null)
				file = null;
		}
	}

	public String readFile2String(File file2read) throws Exception {
		StringBuffer sb = null;
		BufferedReader bufreader = null;
		DataInputStream dis = null;
		try {
			if (file2read == null || !file2read.exists() || file2read.isDirectory())
				return "";
			sb = new StringBuffer();
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file2read)));
			bufreader = new BufferedReader(new InputStreamReader(dis));
			String temp = null;
			while ((temp = bufreader.readLine()) != null) {
				sb.append(temp + System.getProperty("line.separator"));
			}

			return sb.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			sb = null;
			try {
				if (bufreader != null)
					bufreader.close();
				if (dis != null)
					dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String readFile2String(String PathAndName, String encode) throws Exception {
		File file = PadFileUtil.findSourceFile(PathAndName, true);
		try {
			return this.readFile2String(file, encode);
		} catch (Exception e) {
			throw e;
		} finally {
			if (file != null)
				file = null;
		}
	}

	public String readFile2String(File file2read, String encode) throws Exception {
		StringBuffer sb = null;
		BufferedReader bufreader = null;
		DataInputStream dis = null;
		try {
			if (file2read == null || !file2read.exists() || file2read.isDirectory())
				return "";
			sb = new StringBuffer();
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file2read)));
			bufreader = new BufferedReader(new InputStreamReader(dis, encode));
			String temp = null;
			while ((temp = bufreader.readLine()) != null) {
				sb.append(temp + System.getProperty("line.separator"));
			}

			return sb.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			sb = null;
			try {
				if (bufreader != null)
					bufreader.close();
				if (dis != null)
					dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String fixFilePathAndName(String PathAndName) {

		if (File.separator.equals("/"))
			StringUtils.replace(PathAndName, "\\", File.separator);
		else if (File.separator.equals("\\"))
			StringUtils.replace(PathAndName, "/", File.separator);
		return PathAndName;
	}

	public static File findSourceFile(String PathAndName, boolean justForFile) throws Exception {
		if (PathAndName == null || "".equals(PathAndName.trim()))
			return null;
		PathAndName = fixFilePathAndName(PathAndName);
		File file = new File(PathAndName);

		if (!file.exists())
			return null;
		if (justForFile && file.isDirectory())
			return null;
		return file;
	}

	public static File prepareTargetFile(String PathAndName, boolean justForFile) throws Exception {
		File file = null;
		PathAndName = fixFilePathAndName(PathAndName);
		try {
			file = new File(PathAndName);
			if (!file.exists()) {
				if (justForFile) {
					fixFileParentDirs(file);
				} else {
					fixFileDirs(file);
				}
			} else {
				// 是否需要一下处理??
				/*
				 * if(justForFile&&file.isDirectory()){ System.out.println(
				 * "WRONG IN UTILITY OF prepareTargetFile:" +PathAndName); throw
				 * new Exception(
				 * "No File Name Exception, check your target filePathAndName");
				 * }
				 */
			}
			return file;
		} catch (Exception e) {
			System.out.println("WRONG IN UTILITY OF COPY:" + PathAndName);
			e.printStackTrace();
			throw e;
		} finally {
			if (file != null)
				file = null;
		}
	}

	protected static void fixFileParentDirs(File file) throws Exception {
		File tempfile = null;
		try {
			tempfile = new File(file.getParent());
			if (!tempfile.exists())
				tempfile.mkdirs();
		} catch (Exception e) {
			throw e;
		} finally {
			if (tempfile != null)
				tempfile = null;
		}
	}

	protected static void fixFileDirs(File file) throws Exception {
		try {
			String name = file.getName();
			if (isFileName(name)) {
				fixFileParentDirs(file);
			} else {
				if (!file.exists())
					file.mkdirs();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private static boolean isFileName(String name) {
		if (name.indexOf(".") != -1)
			return true;
		else
			return false;
	}
}
