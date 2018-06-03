package com.vix.system.PersonalInformation.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.mail.entity.PersonalEmail;
import com.vix.common.mail.entity.UserAccountToPersonalEmail;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.system.PersonalInformation.controller.PersonalInformationController;
import com.vix.system.entity.Attachment;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

@Controller
@Scope("prototype")
public class PersonalInformationAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PersonalInformationAction.class);
	@Autowired
	private PersonalInformationController personalInformationController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 个人邮箱设置
	 */
	private PersonalEmail personalEmail;
	private List<PersonalEmail> personalEmailList;
	/**
	 * 当前登录账号信息
	 */
	private UserAccount userAccount;
	/**
	 * 登陆员工信息
	 */
	private Employee employee;

	@Override
	public String goList() {
		try {
			personalEmailList = personalInformationController.doListPersonalEmailIndex();
			userAccount = SecurityUtil.getCurrentUserAccount();
			String employeeId = SecurityUtil.getCurrentUserId();
			if (employeeId != null && !employeeId.trim().equals("")) {
				employee = baseHibernateService.findEntityById(Employee.class, employeeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = personalInformationController.doListPersonalEmail(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				personalEmail = personalInformationController.doListPersonalEmailById(id);
				logger.info("");
			} else {
				personalEmail = new PersonalEmail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdateEmployee() {
		boolean isSave = true;
		try {
			if (null != employee.getId()) {
				isSave = false;
			}
			if (employee != null && employee.getId() != null) {
				Employee e = baseHibernateService.findEntityById(Employee.class, employee.getId());
				if (e != null) {
					e.setName(employee.getName());
					e.setIdNumber(employee.getIdNumber());
					e.setPictureurl(employee.getPictureurl());
					e.setGender(employee.getGender());
					e.setBirthday(employee.getBirthday());
					e.setBloodType(employee.getBloodType());
					e.setNational(employee.getNational());
					e.setCurrentResidence(employee.getCurrentResidence());
					e.setTelephone(employee.getTelephone());
					employee = baseHibernateService.merge(e);
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != personalEmail.getId()) {
				isSave = false;
			}
			personalEmail = personalInformationController.doSavePersonalEmail(personalEmail);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	public String saveOrUpdateUserAccount() {
		try {
			UserAccount u = SecurityUtil.getCurrentUserAccount();
			u.setPassword(userAccount.getPassword());
			userAccount = personalInformationController.doSaveUserAccount(u);
			renderText(UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(UPDATE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PersonalEmail pb = personalInformationController.doListPersonalEmailById(id);
			if (null != pb) {
				personalInformationController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 获取个人常用邮箱信息列表
	 */
	public void getPersonalEmailJson() {
		try {
			String json = "";
			UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
			List<PersonalEmail> personalEmailList = new ArrayList<PersonalEmail>();
			if (null != userAccount) {
				List<UserAccountToPersonalEmail> userAccountToPersonalEmailList = baseHibernateService.findAllByEntityClassAndAttribute(UserAccountToPersonalEmail.class, "userAccount.id", userAccount.getId());
				if (userAccountToPersonalEmailList != null && userAccountToPersonalEmailList.size() > 0) {
					for (UserAccountToPersonalEmail userAccountToPersonalEmail : userAccountToPersonalEmailList) {
						personalEmailList.add(userAccountToPersonalEmail.getPersonalEmail());
					}
				}
				json = convertListToJson(personalEmailList, personalEmailList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存个人邮箱
	 * 
	 * @return
	 */
	public String saveOrUpdatePersonalEmail() {
		boolean isSave = true;
		try {
			String personalEmailJson = getRequestParameter("personalEmailJson");
			List<PersonalEmail> personalEmailList = new ArrayList<PersonalEmail>();
			if (personalEmailJson != null && !"".equals(personalEmailJson)) {
				DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
				personalEmailList = new JSONDeserializer<List<PersonalEmail>>().use(Date.class, dateTransformer).use("values", PersonalEmail.class).deserialize(personalEmailJson);
			}
			if (personalEmailList != null && personalEmailList.size() > 0) {
				for (PersonalEmail pe : personalEmailList) {
					personalEmail = baseHibernateService.merge(pe);
					UserAccountToPersonalEmail userAccountToPersonalEmail = new UserAccountToPersonalEmail();
					UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
					userAccountToPersonalEmail.setUserAccount(userAccount);
					userAccountToPersonalEmail.setPersonalEmail(personalEmail);
					userAccountToPersonalEmail.setTenantId(userAccount.getTenantId());
					baseHibernateService.merge(userAccountToPersonalEmail);
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
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

	public PersonalEmail getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(PersonalEmail personalEmail) {
		this.personalEmail = personalEmail;
	}

	public List<PersonalEmail> getPersonalEmailList() {
		return personalEmailList;
	}

	public void setPersonalEmailList(List<PersonalEmail> personalEmailList) {
		this.personalEmailList = personalEmailList;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
