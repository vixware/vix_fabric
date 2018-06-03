package com.vix.common.mail.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.vix.common.mail.entity.MailInfo;
import com.vix.common.mail.entity.PersonalEmail;
import com.vix.system.entity.Attachment;

import jodd.mail.Pop3Server;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;
import jodd.mail.SimpleAuthenticator;

/**
 * 邮件处理类
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-15
 */
public class EmailProcess {
	/**
	 * 
	 * @param mailInfo
	 *            邮件
	 * @param personalEmail
	 *            邮箱信息
	 * @return
	 */
	public static Boolean postEmail(MailInfo mailInfo, PersonalEmail personalEmail) {
		Boolean isSucess = false;
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(personalEmail.getMailServerHost());
			email.setAuthentication(personalEmail.getEmailUserName(), personalEmail.getEmailPassword()); ///发送者邮箱的用户名和密码
			email.setCharset("utf-8");
			try {
				email.setFrom(personalEmail.getEmailAddress());
				email.addTo(mailInfo.getToMail());
				email.setSubject(mailInfo.getSubject());
				email.setMsg(mailInfo.getTextMsg());
				email.send();
				System.out.println("邮件发送成功");
			} catch (Exception e) {
				System.out.println("邮件发送失败");
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
			e.printStackTrace();
		}
		return isSucess;
	}

	// 发送带附件的邮件  
	public static Boolean sendMutiMail(MailInfo mailInfo, PersonalEmail personalEmail, String path, String name) {
		Boolean isSucess = false;

		try {
			String[] tomails = mailInfo.getToMail().split(";");
			if (tomails != null && tomails.length > 0) {
				for (String s : tomails) {

					EmailAttachment attachment = new EmailAttachment();
					attachment.setDisposition(EmailAttachment.ATTACHMENT);
					attachment.setDescription("python resource");
					attachment.setPath(path);
					attachment.setName(MimeUtility.encodeText(name));//设置附件的中文编码  

					MultiPartEmail email = new MultiPartEmail();
					email.setHostName(personalEmail.getMailServerHost());// 发送服务器  
					email.setAuthentication(personalEmail.getEmailUserName(), personalEmail.getEmailPassword());// 发送邮件的用户名和密码  
					email.addTo(s);// 接收邮箱  
					email.setFrom(personalEmail.getEmailAddress());// 发送邮箱  
					email.setSubject(mailInfo.getSubject());// 主题  
					email.setMsg(mailInfo.getTextMsg());// 内容
					email.setCharset("utf-8");//编码
					//添加附件
					if (attachment.getPath() != null && !"".equals(attachment.getPath())) {
						email.attach(attachment);
					}
					//发送邮件
					email.send();
					isSucess = true;
				}
			}
		} catch (Exception e) {
			isSucess = false;
			e.printStackTrace();
		}
		return isSucess;
	}

	/**
	 * 
	 * @param personalEmail
	 *            个人邮箱信息
	 * @param path
	 *            附件存放路径
	 * @return
	 */
	public static List<MailInfo> getEmail(PersonalEmail personalEmail, String path) {
		List<MailInfo> mailInfoList = new ArrayList<MailInfo>();
		Pop3Server popServer = new Pop3Server(personalEmail.getMailServerHost(), new SimpleAuthenticator(personalEmail.getEmailUserName(), personalEmail.getEmailPassword()));
		ReceiveMailSession session = popServer.createSession();
		session.open();
		ReceivedEmail[] emails = session.receiveEmail();
		if (emails != null) {
			for (ReceivedEmail email : emails) {
				if (email != null) {
					MailInfo mailInfo = new MailInfo();
					//mailInfo.setFromMail(getEmailAddress(email.getFrom()));
					//mailInfo.setToMail(getEmailAddress(email.getTo()[0]));
					mailInfo.setSubject(email.getSubject());
					mailInfo.setMailSendDate(email.getSentDate());
					mailInfo.setMailReceiveDate(email.getReceiveDate());

					// 处理附件
					List<jodd.mail.EmailAttachment> attachments = email.getAttachments();
					if (attachments != null) {
						Set<Attachment> ats = new HashSet<Attachment>();
						for (jodd.mail.EmailAttachment attachment : attachments) {
							Attachment at = new Attachment();
							at.setName(attachment.getName());
							attachment.writeToFile(new File(path, attachment.getName()));
							ats.add(at);
						}
						mailInfo.setAttachments(ats);
					}
					mailInfoList.add(mailInfo);
				}
			}
		}
		session.close();
		return mailInfoList;
	}

	/**
	 * 获取<>中的内容
	 * 
	 * @param s
	 * @return
	 */
	public static String getEmailAddress(String s) {
		Pattern pattern = Pattern.compile("(<(\n|.)*?>)");
		Matcher matcher = pattern.matcher(s);
		if (matcher != null) {
			while (matcher.find()) {
				return matcher.group().substring(1, matcher.group().length() - 1);
			}
		}
		return "";
	}
}
