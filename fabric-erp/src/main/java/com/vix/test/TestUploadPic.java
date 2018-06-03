package com.vix.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.vix.ebusiness.util.ftp.ContinueFTP;

public class TestUploadPic {

	static SimpleDateFormat dateFormator = new SimpleDateFormat("yyyyMMdd");

	public static void main(String[] args) {
		try {
			URL url = null;
			url = new URL("file:///D:\\pic\\goodsimage\\goods\\20141104\\fbf7d25585e249e29b1a3e434966520f.jpg");
			url.openConnection();
			InputStream ins = null;
			ins = url.openStream();
			ContinueFTP ftp = new ContinueFTP();
			try {
				String httpservice = "127.0.0.1";
				int port = 21;
				String name = "think";
				String password = "130724";
				ftp.connect(httpservice, port, name, password);
			} catch (IOException e) {
				e.printStackTrace();
			}
			StringBuilder finalName = new StringBuilder();
			finalName.append("goods");
			finalName.append("/");
			Date date = new Date();
			String str = dateFormator.format(date);
			finalName.append(str);
			finalName.append("/");
			finalName.append(UUID.randomUUID().toString().replaceAll("-", ""));
			finalName.append(".");
			finalName.append("jpg");
			System.out.println(ftp.uploadFile("pic/" + finalName.toString(), ins));
			System.out.println("ftp://127.0.0.1/goodsimage/"+finalName.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
