package com.vix.nvix.oa.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
import com.vix.oa.bulletin.service.IAnnouncementNotificationService;
import com.vix.oa.share.entity.Trends;
import com.vix.oa.task.taskDefinition.entity.EvaluationReview;
import com.vix.wechat.base.entity.WxpQyPicture;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

/**
 * 
 * @ClassName: NewsAdministrationAction
 * @Description: 新闻管理
 * @author bobchen
 * @date 2016年1月6日 上午11:24:46
 *
 */
@Controller
@Scope("prototype")
public class NewsAdministrationAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IAnnouncementNotificationService announcementNotificationService;
	private Trends trends;
	private List<Trends> trendsList;
	private List<EvaluationReview> evaluationReviewsList;
	private String fileId;
	private String syncTag;
	private String userIdStr = "";
	private List<Employee> employeeList;
	private String employeeIds;
	private EvaluationReview evaluationReview;
	private EvaluationReview subevaluationReview;
	/** 回复数量 */
	private Integer evaluationReviewNum = 0;

	/** 新闻管理 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			trendsList = announcementNotificationService.findAllDataByConditions(Trends.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 查看新闻 */
	public String goViewNews() {
		try {
			Map<String, Object> params = getParams();
			params.put("isPublish," + SearchCondition.EQUAL, 0);
			trendsList = announcementNotificationService.findAllDataByConditions(Trends.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goViewNews";
	}

	/**
	 * 跳转到新闻发布页面
	 */
	public String goPublishOrNews() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				trends = vixntBaseService.findEntityById(Trends.class, id);
			} else {
				trends = new Trends();
				trends.setCode(VixUUID.createCode(15));
				trends.setCreateTime(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPublishOrNews";
	}

	/* 查看页面搜索新闻 */
	public String goNewss() {
		try {
			Map<String, Object> params = getParams();
			// 标题
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			trendsList = vixntBaseService.findAllByConditions(Trends.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "newss";
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

	/**
	 * 发布新闻
	 */
	public void saveOrUpdatePublishNews() {
		try {
			trends.setStatus("0");
			trends.setReadTimes(0l);
			trends = vixntBaseService.merge(trends);
			if (StringUtils.isNotEmpty(fileId)) {
				WxpQyPicture wxpQyPicture = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyPicture.class, "id", fileId);
				wxpQyPicture.setTrends(trends);
				wxpQyPicture = vixntBaseService.mergeOriginal(wxpQyPicture);
				trends.setFirstPictureUrl(wxpQyPicture.getPictureUrl());
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = vixntBaseService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					trends.setCreator(employee.getName());
					trends.setTenantId(employee.getTenantId());
					trends.setCompanyInnerCode(employee.getCompanyInnerCode());
					trends.setEmployee(employee);
				}
			}
			// WxQyUtil.verification(wechatNo, redirect_uri);
			trends.setCreateTime(new Date());
			trends.setUpdateTime(new Date());
			Employee employees = getEmployee();
			if (employees != null) {
				/** 拿到当前用户的id帐号姓名，保存 */
				trends.setUploadPersonId(employees.getId());
				trends.setUploadPerson(employees.getName());
				trends.setUploadPersonName(employees.getName());
				trends.setUploadPerson(employees.getName());
			}
			// 拿到发布范围人员信息
			if (StringUtils.isNotEmpty(employeeIds)) {
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("id," + SearchCondition.IN, employeeIds);
				employeeList = vixntBaseService.findAllByConditions(Employee.class, p);
			}
			trends = vixntBaseService.merge(trends);

			if (employeeList != null && employeeList.size() > 0) {
				for (Employee employee : employeeList) {
					userIdStr += "|" + employee.getUserId();
				}
				WxpQyWeixinSite site = vixntBaseService.findEntityByAttributeNoTenantId(WxpQyWeixinSite.class, "tenantId", trends.getTenantId());
				if (site != null) {
					saveOrUpdateWxpWeixinSite(site);
					WxQyUtil.messageSendNews("15", userIdStr.substring(1), trends.getTitle(), site.getQiyeAccessToken(), trends.getSourceFrom(), "http://www.vixware.cn/wechat/identityVerificationAction!goShowNews.action?id=" + trends.getId(), "http://www.vixware.cn" + trends.getFirstPictureUrl());
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
			Trends pb = baseHibernateService.findEntityById(Trends.class, id);
			if (null != pb) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("trends.id," + SearchCondition.EQUAL, id);
				List<EvaluationReview> evaluationReviewList = vixntBaseService.findAllByConditions(EvaluationReview.class, params);
				if (evaluationReviewList != null && evaluationReviewList.size() > 0) {
					for (EvaluationReview evaluationReview : evaluationReviewList) {
						baseHibernateService.deleteByEntity(evaluationReview);
					}
				}
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/* 查询新闻数据 */
	public void goTrends() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			/** 根据isTop和时间进行倒序排序，isTop优先 */
			String orderField = getPager().getOrderField();
			if (null != orderField && !"".equals(orderField)) {
				orderField = "isTop desc," + orderField;
				getPager().setOrderField(orderField);
			} else {
				orderField = "isTop desc,createTime";
				getPager().setOrderField(orderField);
				getPager().setOrderBy("desc");
			}

			// 标题
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("title," + SearchCondition.ANYLIKE, searchCode);
			}
			// 副标题
			String searchCodeA = getDecodeRequestParameter("searchCodeA");
			if (StringUtils.isNotEmpty(searchCodeA)) {
				params.put("subtitle," + SearchCondition.ANYLIKE, searchCodeA);
			}
			// 新闻来源
			String searchCodeB = getDecodeRequestParameter("searchCodeB");
			if (StringUtils.isNotEmpty(searchCodeB)) {
				params.put("sourceFrom," + SearchCondition.ANYLIKE, searchCodeB);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Trends.class, params);
			String[] s = {"*.sex"};
			renderDataTable(pager, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 根据ID查询新闻数据 */
	public String goNewsDetail() {
		try {
			trends = baseHibernateService.findEntityById(Trends.class, id);
			if (trends.getReadTimes() == 0) {
				trends.setReadTimes(1l);
			} else {
				trends.setReadTimes(trends.getReadTimes() + 1l);
			}
			if (trends.getSubEvaluationReviews() != null && trends.getSubEvaluationReviews().size() > 0) {
				evaluationReviewNum = trends.getSubEvaluationReviews().size();
				evaluationReviewsList = new ArrayList<EvaluationReview>();
				evaluationReviewsList.addAll(trends.getSubEvaluationReviews());
			}
			trends = baseHibernateService.merge(trends);
			logger.info("获取评论人员列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goNewsDetail";
	}

	/* 新闻评论 */
	public String goNewsCommentary() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				trends = baseHibernateService.findEntityById(Trends.class, id);
				if (trends.getSubEvaluationReviews() != null && trends.getSubEvaluationReviews().size() > 0) {
					evaluationReviewNum = trends.getSubEvaluationReviews().size();
					evaluationReviewsList = new ArrayList<EvaluationReview>();
					evaluationReviewsList.addAll(trends.getSubEvaluationReviews());
				}
				trends = vixntBaseService.merge(trends);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goNewsCommentary";
	}

	/**
	 * 评论
	 */
	public void saveOrUpdateComments() {
		try {
			if (trends != null && trends.getId() != null && !"".equals(trends.getId())) {
				evaluationReview.setTrends(trends);
				evaluationReview.setCreateTime(new Date());
				/** 拿到当前用户的姓名，保存 */
				Employee employee = getEmployee();
				if (employee != null) {
					evaluationReview.setEmployee(employee);
					evaluationReview.setUploadPersonName(employee.getName());
					evaluationReview.setUploadPerson(employee.getName());
					evaluationReview.setUploadPersonId(employee.getId());
				}
				evaluationReview.setEvaluationTime(new Date());
				evaluationReview = vixntBaseService.merge(evaluationReview);
				renderText(trends.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 根据新闻评论ID查看新闻评论的评论 */
	public String goComment() {
		try {
			evaluationReview = baseHibernateService.findEntityById(EvaluationReview.class, id);
			if (evaluationReview.getSubEvaluationReviews() != null && evaluationReview.getSubEvaluationReviews().size() > 0) {
				evaluationReviewNum = evaluationReview.getSubEvaluationReviews().size();
				evaluationReviewsList = new ArrayList<EvaluationReview>();
				evaluationReviewsList.addAll(evaluationReview.getSubEvaluationReviews());
			}
			evaluationReview = baseHibernateService.merge(evaluationReview);
			logger.info("获取评论人员列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goComment";
	}

	/* 根据新闻评论评论新闻 */
	public String goReply() {
		try {
			evaluationReview = baseHibernateService.findEntityById(EvaluationReview.class, id);
			if (evaluationReview.getSubEvaluationReviews() != null && evaluationReview.getSubEvaluationReviews().size() > 0) {
				evaluationReviewNum = evaluationReview.getSubEvaluationReviews().size();
				evaluationReviewsList = new ArrayList<EvaluationReview>();
				evaluationReviewsList.addAll(evaluationReview.getSubEvaluationReviews());
			}
			subevaluationReview = new EvaluationReview();
			subevaluationReview.setParentEvaluationReview(evaluationReview);
			subevaluationReview = baseHibernateService.merge(subevaluationReview);
			logger.info("获取评论人员列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goReply";
	}

	/**
	 * 回复
	 */
	public void saveOrUpdateCommentss() {
		try {
			subevaluationReview.setCreateTime(new Date());
			/** 拿到当前用户的姓名，保存 */
			Employee employee = getEmployee();
			if (employee != null) {
				subevaluationReview.setUploadPersonName(employee.getName());
				subevaluationReview.setUploadPerson(employee.getName());
				subevaluationReview.setUploadPersonId(employee.getId());
				subevaluationReview.setEmployee(employee);
			}
			subevaluationReview = vixntBaseService.merge(subevaluationReview);
			renderText(subevaluationReview.getParentEvaluationReview().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Trends getTrends() {
		return trends;
	}

	public void setTrends(Trends trends) {
		this.trends = trends;
	}

	public EvaluationReview getSubevaluationReview() {
		return subevaluationReview;
	}

	public void setSubevaluationReview(EvaluationReview subevaluationReview) {
		this.subevaluationReview = subevaluationReview;
	}

	public List<Trends> getTrendsList() {
		return trendsList;
	}

	public void setTrendsList(List<Trends> trendsList) {
		this.trendsList = trendsList;
	}

	public String getSyncTag() {
		return syncTag;
	}

	public void setSyncTag(String syncTag) {
		this.syncTag = syncTag;
	}

	public List<EvaluationReview> getEvaluationReviewsList() {
		return evaluationReviewsList;
	}

	public void setEvaluationReviewsList(List<EvaluationReview> evaluationReviewsList) {
		this.evaluationReviewsList = evaluationReviewsList;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	public EvaluationReview getEvaluationReview() {
		return evaluationReview;
	}

	public void setEvaluationReview(EvaluationReview evaluationReview) {
		this.evaluationReview = evaluationReview;
	}

	public Integer getEvaluationReviewNum() {
		return evaluationReviewNum;
	}

	public void setEvaluationReviewNum(Integer evaluationReviewNum) {
		this.evaluationReviewNum = evaluationReviewNum;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}
}
