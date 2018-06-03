package com.vix.nvix.oa.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.oa.bulletin.entity.AnnouncementNotification;
import com.vix.wechat.base.entity.WxpQyPicture;

/**
 * 
 * @ClassName: NoticeAction
 * @Description: 公告通知
 * @author bobchen
 * @date 2016年1月11日 下午5:53:25
 *
 */
@Controller
@Scope("prototype")
public class NoticeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IVixntBaseService vixntBaseService;

	private AnnouncementNotification announcementNotification;
	private List<AnnouncementNotification> announcementNotificationList;
	private String syncTag;
	private String id;
	private String fileId;
	private String companyCode;
	private String companyInnerCode;
	private String employeeIds;
	private String userIdStr = "";
	private List<Employee> employeeList;

	/* 公告通知管理 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			announcementNotificationList = vixntBaseService.findAllDataByConditions(AnnouncementNotification.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/* 个人公告通知 */
	public String goPersonalAnnouncement() {
		try {
			Map<String, Object> params = getParams();
			params.put("isPublish," + SearchCondition.EQUAL, 0);
			announcementNotificationList = vixntBaseService.findAllDataByConditions(AnnouncementNotification.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPersonalAnnouncement";
	}

	/* 查询公告通知数据 */
	public void goNotice() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("activeStartDate");
				getPager().setOrderBy("desc");
			}
			// 标题
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("title," + SearchCondition.ANYLIKE, searchCode);
			}
			// 发布范围
			String searchCodeA = getDecodeRequestParameter("searchCodeA");
			if (StringUtils.isNotEmpty(searchCodeA)) {
				params.put("pubNames," + SearchCondition.ANYLIKE, searchCodeA);
			}
			// 发布人
			String searchCodeB = getDecodeRequestParameter("searchCodeB");
			if (StringUtils.isNotEmpty(searchCodeB)) {
				params.put("uploadPersonName," + SearchCondition.ANYLIKE, searchCodeB);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, AnnouncementNotification.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 查询个人公告通知数据 */
	public void goGNotice() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			/*
			 * String employeeId = SecurityUtil.getCurrentEmpId();
			 * params.put("announcementNotification.employee_id," +
			 * SearchCondition.ANYLIKE, ","+employeeId+",");
			 */
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// 标题
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("title," + SearchCondition.ANYLIKE, searchCode);
			}
			// 发布范围
			String searchCodeA = getDecodeRequestParameter("searchCodeA");
			if (StringUtils.isNotEmpty(searchCodeA)) {
				params.put("pubNames," + SearchCondition.ANYLIKE, searchCodeA);
			}
			// 发布人
			String searchCodeB = getDecodeRequestParameter("searchCodeB");
			if (StringUtils.isNotEmpty(searchCodeB)) {
				params.put("uploadPersonName," + SearchCondition.ANYLIKE, searchCodeB);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, AnnouncementNotification.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 查看页面搜索公告通知 */
	public String goNotices() {
		try {
			Map<String, Object> params = getParams();
			// 标题
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			announcementNotificationList = vixntBaseService.findAllByConditions(AnnouncementNotification.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "notices";
	}

	/* 根据ID查询公告通知数据 */
	public String goViewNotice() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = vixntBaseService.findEntityById(AnnouncementNotification.class, id);
				if (announcementNotification.getReadTimes() == null) {
					announcementNotification.setReadTimes(1l);
				} else {
					announcementNotification.setReadTimes(announcementNotification.getReadTimes() + 1);
				}
				announcementNotification = vixntBaseService.merge(announcementNotification);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewNotice";
	}

	/** 跳转到公告发布页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				announcementNotification = vixntBaseService.findEntityById(AnnouncementNotification.class, id);
			} else {
				announcementNotification = new AnnouncementNotification();
				announcementNotification.setCode(VixUUID.createCode(15));
				announcementNotification.setActiveStartDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存图片
	 */
	public void saveOrUpdateWxpQyPicture() {
		try {
			// 保存图片
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				WxpQyPicture wxpQyPicture = new WxpQyPicture();
				wxpQyPicture.setPictureUrl("/img/wechat/" + savePathAndName[1].toString());
				wxpQyPicture.setCreateTime(new Date());
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
				if (wxpQyPicture != null) {
					renderText(wxpQyPicture.getId() + "," + "/img/wechat/" + savePathAndName[1].toString());
				} else {
					renderText("0,保存失败!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] saveUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getUploadFileSavePic();
				@SuppressWarnings("resource")
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

		String baseFolder = "c:/img";

		String newFilePath = "";

		newFilePath = baseFolder;

		File dir = new File(newFilePath);
		if (!dir.exists())
			dir.mkdirs();

		return newFilePath;
	}

	/**
	 * 保存公告
	 */
	public void saveOrUpdate() {
		try {
			announcementNotification.setStatus("0");
			announcementNotification.setReadTimes(0l);
			announcementNotification = vixntBaseService.merge(announcementNotification);
			if (StringUtils.isNotEmpty(fileId)) {
				WxpQyPicture wxpQyPicture = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", fileId);
				wxpQyPicture.setAnnouncementNotification(announcementNotification);
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
				announcementNotification.setFirstPictureUrl(wxpQyPicture.getPictureUrl());
			}

			// 获取创建人
			Employee employee = getEmployee();
			if (employee != null) {
				announcementNotification.setCreator(employee.getName());
				announcementNotification.setTenantId(employee.getTenantId());
				announcementNotification.setCompanyInnerCode(employee.getCompanyInnerCode());
				announcementNotification.setEmployee(employee);
				announcementNotification.setUploadPersonName(employee.getName());
				announcementNotification.setUploadPerson(employee.getName());
			}

			/** 拿到当前用户的id帐号姓名，保存 */
			this.announcementNotification.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.announcementNotification.setUploadPerson(SecurityUtil.getCurrentUserName());
			announcementNotification.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());

			// WxQyUtil.verification(wechatNo, redirect_uri);
			announcementNotification.setUpdateTime(new Date());
			announcementNotification.setCreateTime(new Date());
			announcementNotification.setActiveStartDate(new Date());
			// 不要删除,删除后上面的时间没法保存
			announcementNotification = vixntBaseService.merge(announcementNotification);
			/** 拿到发布范围人员信息 */
			Map<String, Object> p = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(employeeIds)) {
				p.put("id," + SearchCondition.IN, employeeIds);
			}
			employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
			announcementNotification = vixntBaseService.merge(announcementNotification);

			if (employeeList != null && employeeList.size() > 0) {
				for (Employee e : employeeList) {
					userIdStr += "|" + e.getUserId();
				}
				if (StringUtils.isNotEmpty(userIdStr)) {
					sendMessage("2", userIdStr.substring(1), announcementNotification.getTitle(), announcementNotification.getPlotSummary(), announcementNotification.getId(), announcementNotification.getFirstPictureUrl(), announcementNotification.getTenantId());
				}
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			AnnouncementNotification pb = vixntBaseService.findEntityById(AnnouncementNotification.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public AnnouncementNotification getAnnouncementNotification() {
		return announcementNotification;
	}

	public void setAnnouncementNotification(AnnouncementNotification announcementNotification) {
		this.announcementNotification = announcementNotification;
	}

	public List<AnnouncementNotification> getAnnouncementNotificationList() {
		return announcementNotificationList;
	}

	public void setAnnouncementNotificationList(List<AnnouncementNotification> announcementNotificationList) {
		this.announcementNotificationList = announcementNotificationList;
	}

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userIdStr
	 */
	public String getUserIdStr() {
		return userIdStr;
	}

	/**
	 * @param userIdStr
	 *            the userIdStr to set
	 */
	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	/**
	 * @return the employeeList
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * @param employeeList
	 *            the employeeList to set
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Override
	public String getCompanyInnerCode() {
		return companyInnerCode;
	}

	public void setCompanyInnerCode(String companyInnerCode) {
		this.companyInnerCode = companyInnerCode;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
