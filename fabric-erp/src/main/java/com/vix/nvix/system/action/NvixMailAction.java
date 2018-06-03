package com.vix.nvix.system.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.mail.entity.MailInfo;
import com.vix.common.mail.entity.PersonalEmail;
import com.vix.common.mail.entity.UserAccountToPersonalEmail;
import com.vix.common.mail.service.IMailService;
import com.vix.common.mail.util.EmailProcess;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.wab.entity.Wab;
import com.vix.system.entity.Attachment;

@Controller
@Scope("prototype")
public class NvixMailAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String pageNo;
	@Autowired
	private IMailService mailService;
	/**
	 * 邮件
	 */
	private MailInfo mailInfo;
	private List<MailInfo> mailInfoList;
	private List<Wab> wabList = new ArrayList<Wab>();

	/**
	 * 
	 * @return
	 */
	public void goSingleList() {
		try {
			UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
			List<UserAccountToPersonalEmail> userAccountToPersonalEmailList = mailService.findAllByEntityClassAndAttribute(UserAccountToPersonalEmail.class, "userAccount.id", userAccount.getId());
			if (userAccountToPersonalEmailList != null && userAccountToPersonalEmailList.size() > 0) {
				for (UserAccountToPersonalEmail userAccountToPersonalEmail : userAccountToPersonalEmailList) {
					PersonalEmail personalEmail = mailService.findEntityById(PersonalEmail.class, userAccountToPersonalEmail.getPersonalEmail().getId());
					if ("1".equals(personalEmail.getIsDefault()) && "R".equals(personalEmail.getType())) {
						List<MailInfo> mailInfoList = EmailProcess.getEmail(personalEmail, "D:/Email_package");
						if (mailInfoList != null && mailInfoList.size() > 0) {
							for (MailInfo mailInfo : mailInfoList) {
								MailInfo m = mailService.findEntityByAttribute(MailInfo.class, "subject", mailInfo.getSubject());
								if (m != null) {
								} else {
									mailInfo.setMailType(3);
									mailService.merge(mailInfo);
								}
							}
						}
					}
				}
			}
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("mailType," + SearchCondition.EQUAL, 1);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goDirectoryList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = mailService.findPagerByHqlConditions(getPager(), Wab.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDirectoryList";
	}

	public String goAsteriskMailList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("mailType," + SearchCondition.EQUAL, 3);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAsteriskMailList";
	}

	/**
	 * 获取已发送邮件
	 * 
	 * @return
	 */
	public String goHasBeenSentList() {
		try {
			Map<String, Object> params = getParams();
			params.put("mailType," + SearchCondition.EQUAL, 1);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goHasBeenSentList";
	}

	public String goHasDeleteList() {
		try {
			Map<String, Object> params = getParams();
			params.put("mailType," + SearchCondition.EQUAL, 1);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goHasDeleteList";
	}

	public String goDustbinList() {
		try {
			Map<String, Object> params = getParams();
			params.put("mailType," + SearchCondition.EQUAL, 1);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goDustbinList";
	}

	/**
	 * 草稿箱邮件
	 * 
	 * @return
	 */
	public String goRoughCopyList() {
		try {
			Map<String, Object> params = getParams();
			params.put("mailType," + SearchCondition.EQUAL, 0);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRoughCopyList";
	}

	/**
	 * 处理邮件发送 首先获取登录账号的邮箱账号信息
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != mailInfo.getId() && !"".equals(mailInfo.getId())) {
				isSave = false;
			}
			UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
			List<UserAccountToPersonalEmail> userAccountToPersonalEmailList = mailService.findAllByEntityClassAndAttribute(UserAccountToPersonalEmail.class, "userAccount.id", userAccount.getId());
			if (userAccountToPersonalEmailList != null && userAccountToPersonalEmailList.size() > 0) {
				for (UserAccountToPersonalEmail userAccountToPersonalEmail : userAccountToPersonalEmailList) {
					PersonalEmail personalEmail = mailService.findEntityById(PersonalEmail.class, userAccountToPersonalEmail.getPersonalEmail().getId());
					if ("1".equals(personalEmail.getIsDefault()) && "S".equals(personalEmail.getType())) {

						String[] savePathAndName = this.saveUploadFile();
						String path = null;
						String name = null;
						if (savePathAndName != null && savePathAndName.length == 2) {
							path = savePathAndName[0] + savePathAndName[1];
							name = savePathAndName[1];
							path = path.replace("\\", "/");
						}
						if (EmailProcess.sendMutiMail(mailInfo, personalEmail, path, name)) {
							mailInfo.setMailType(1);
						} else {
							mailInfo.setMailType(0);
						}
					}
				}
			}
			mailInfo.setMailSendDate(new Date());
			mailInfo = mailService.merge(mailInfo);
			if ("1".equals(mailInfo.getMailType())) {
				setMessage("发送成功!");
			} else {
				setMessage("发送成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage("发送失败!");
			} else {
				this.setMessage("发送失败!");
			}
		}
		return UPDATE;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				mailInfo = mailService.findEntityById(MailInfo.class, id);
			} else {
				mailInfo = new MailInfo();
				mailInfo = mailService.merge(mailInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	public String goMailList() {
		Map<String, Object> params = getParams();
		try {
			wabList = mailService.findAllByConditions(Wab.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMailList";
	}

	public String goMailEdit() {
		return "goMailEdit";
	}
	public String goEmailList() {
		return "goEmailList";
	}
	public String goEmailOpened() {
		return "goEmailOpened";
	}
	public String goEmailReply() {
		return "goEmailReply";
	}
	public String goEmailCompose() {
		return "goEmailCompose";
	}

	public String goMailDetail() {
		return "goMailDetail";
	}

	public String goHasBeenSent() {
		return "goHasBeenSent";
	}

	public String goRoughCopy() {
		try {
			Map<String, Object> params = getParams();
			params.put("mailType," + SearchCondition.EQUAL, 0);
			Pager pager = mailService.findPagerByHqlConditions(getPager(), MailInfo.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRoughCopy";
	}

	public void savePic() {
		try {
			// 保存附件
			String[] savePathAndName = saveUploadPic();
			Attachment attachment = new Attachment();
			if (savePathAndName != null && savePathAndName.length == 2) {
				attachment.setName(savePathAndName[1]);
				attachment.setPath(savePathAndName[0]);
				renderText("ftp://127.0.0.1/" + SecurityUtil.getCurrentUserTenantId() + "/" + savePathAndName[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	private String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(fileToUpload));
				String[] fileNames = fileToUploadFileName.split("\\.");
				String extFileName = fileNames[fileNames.length - 1];
				String fileName = fileToUploadFileName.substring(0, fileToUploadFileName.length() - extFileName.length() - 1);

				long newFileMark = System.currentTimeMillis() / 1000;
				String saveFileName = fileName + "_" + newFileMark + "." + extFileName;

				String saveFilePath = saveFolder + "/" + saveFileName;

				BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(saveFilePath));
				byte[] buf = new byte[1024 * 100];
				int len = -1;
				while ((len = bufIn.read(buf)) != -1) {
					bufOut.write(buf, 0, len);
				}
				bufOut.close();

				pathAndName = new String[2];
				pathAndName[0] = saveFolder;
				pathAndName[1] = saveFileName;

			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return pathAndName;
	}

	@Override
	public String getUploadFileSavePic() {

		String baseFolder = "D:/img";
		String newFilePath = "";
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if (StrUtils.isEmpty(tenantId))
			tenantId = "no_tenantId";
		newFilePath = baseFolder + "/" + tenantId;
		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();
		return newFilePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MailInfo getMailInfo() {
		return mailInfo;
	}

	public void setMailInfo(MailInfo mailInfo) {
		this.mailInfo = mailInfo;
	}

	/**
	 * @return the wabList
	 */
	public List<Wab> getWabList() {
		return wabList;
	}

	/**
	 * @param wabList
	 *            the wabList to set
	 */
	public void setWabList(List<Wab> wabList) {
		this.wabList = wabList;
	}

	public List<MailInfo> getMailInfoList() {
		return mailInfoList;
	}

	public void setMailInfoList(List<MailInfo> mailInfoList) {
		this.mailInfoList = mailInfoList;
	}

}
