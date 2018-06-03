package com.vix.hr.trainning.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.controller.TrainingChannelController;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.hr.trainning.service.IDemandApplyService;

/**
 * 
 * @Description:培训渠道
 * @author bobchen
 * @date 2015-8-25 下午5:15:20
 */
@Controller
@Scope("prototype")
public class TrainingChannelAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(TrainingChannel.class);

	/** 注入service */
	@Autowired
	private TrainingChannelController trainingChannelController;
	private List<TrainingChannel> trainingChannelList;
	private TrainingChannel trainingChannel;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	@Autowired
	private IDemandApplyService iDemandApplyService;
	/** 培训讲师 */
	private TrainingLecturer trainingLecturer;
	private String id;
	private String pageNo;
	private String parentId;

	@Override
	public String goList() {
		try {
			trainingChannelList = trainingChannelController.findTrainingChannelIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}
	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			/* 状态 */
			String channelType = getRequestParameter("channelType");
			if (null != channelType && !"".equals(channelType)) {
				params.put("channelType," + SearchCondition.ANYLIKE, channelType);
			}
			/* 按最近使用 */
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			/* uploadPersonId包含当前登录人id */
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);
			Pager pager = trainingChannelController.goSingleList(params, getPager());
			logger.info("获取列表数据");
			setPager(pager);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}
	/**
	 * 获取搜索列表数据
	 * 
	 * @return
	 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 渠道名称
			String channelName = getRequestParameter("channelName");
			if (null != channelName && !"".equals(channelName)) {
				channelName = URLDecoder.decode(channelName, "utf-8");
			}
			// 渠道费用
			String channelCost = getRequestParameter("channelCost");
			if (null != channelCost && !"".equals(channelCost)) {
				channelCost = URLDecoder.decode(channelCost, "utf-8");
			}
			// 所在省份
			String province = getRequestParameter("province");
			if (null != province && !"".equals(province)) {
				province = URLDecoder.decode(province, "utf-8");
			}
			// 联系方式
			String contactInformation = getRequestParameter("contactInformation");
			if (null != contactInformation && !"".equals(contactInformation)) {
				contactInformation = URLDecoder.decode(contactInformation, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("channelName," + SearchCondition.ANYLIKE, channelName);
				pager = trainingChannelController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != channelName && !"".equals(channelName)) {
					params.put("channelName," + SearchCondition.ANYLIKE, channelName);
				}
				if (null != channelCost && !"".equals(channelCost)) {
					params.put("channelCost," + SearchCondition.ANYLIKE, channelCost);
				}
				if (null != province && !"".equals(province)) {
					params.put("province," + SearchCondition.ANYLIKE, province);
				}
				if (null != contactInformation && !"".equals(contactInformation)) {
					params.put("contactInformation," + SearchCondition.ANYLIKE, contactInformation);
				}
				pager = trainingChannelController.goSingleList(params, getPager());
			}
			logger.info("获取培训渠道搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 跳转至用户修改页面
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				trainingChannel = trainingChannelController.doListEntityById(id);
				logger.info("");
			} else {
				trainingChannel = new TrainingChannel();
				trainingChannel = trainingChannelController.doSaveTrainingChannel(trainingChannel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/**
	 * 
	 * 处理修改/保存操作
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingChannel.getId()) && !"0".equals(trainingChannel.getId())) {
				isSave = false;
			}
			/** 索引 */
			String title = trainingChannel.getChannelName();
			String py = ChnToPinYin.getPYString(title);
			trainingChannel.setChineseFirstLetter(py.toUpperCase());

			initEntityBaseController.initEntityBaseAttribute(trainingChannel);
			trainingChannel = trainingChannelController.doSaveTrainingChannel(trainingChannel);

			this.trainingChannel.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.trainingChannel.setUploadPerson(SecurityUtil.getCurrentUserName());
			/** 拿到当前用户的姓名，保存 */
			trainingChannel.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			this.saveBaseEntity(this.trainingChannel);
			logger.info("对培训渠道进行了修改！");
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	/**
	 * 
	 * 处理删除操作
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			TrainingChannel trainingChannel = trainingChannelController.doListEntityById(id);
			if (null != trainingChannel) {
				trainingChannelController.doDeleteByEntity(trainingChannel);
				logger.info("");
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除培训渠道");
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * 弹出选择部门，职位窗体
	 * 
	 * @return
	 */
	public String goChooseCategory() {
		return "goChooseCategory";
	}
	/**
	 * 
	 * 树形结构JSON
	 */
	public void findTreeToJson() {
		try {
			List<Organization> listCategory = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listCategory = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listCategory = iDemandApplyService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listCategory.size(); i++) {
				Organization sc = listCategory.get(i);
				if (sc.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(sc.getId());
					strSb.append("\",name:\"");
					strSb.append(sc.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < listCategory.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseCourse() {
		logger.info("选择讲师");
		return "goChooseCourse";
	}
	/**
	 * 获取讲师列表数据
	 * 
	 * @return
	 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			/* String updateTime = getRequestParameter("updateTime"); */
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = trainingChannelController.goSingleList2(params, getPager());
			logger.info("获取选择讲师的数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}
	/**
	 * 
	 * 获取讲师明细json数据
	 */
	public void getTrainingCourseJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				TrainingChannel trainingChannel = trainingChannelController.findEntityById(id);
				json = convertListToJson(new ArrayList<TrainingLecturer>(trainingChannel.getTrainingLecturers()), trainingChannel.getTrainingLecturers().size(), "trainingChannel");
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
	 * 培训讲师明细
	 * 
	 * @return
	 */
	public String saveOrUpdateTrainingCourse() {
		try {
			String tcIdStr = getRequestParameter("tcId");
			if (null != tcIdStr) {
				trainingLecturer = trainingChannelController.findTrainingLecturerById(tcIdStr);
				trainingChannel = trainingChannelController.doListEntityById(id);
				trainingLecturer.setTrainingChannel(trainingChannel);
				trainingChannelController.doSaveTrainingLecturer(trainingLecturer);
				setMessage("保存成功！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			setMessage("保存失败！");
		}
		return UPDATE;
	}
	/**
	 * 处理删除tab明细操作
	 * 
	 * @return
	 */
	public String deleteTrainingLecturerById() {
		try {
			TrainingLecturer trainingLecturer = trainingChannelController.doListTrainingLecturerById(id);
			if (null != trainingLecturer) {
				trainingChannelController.deleteTrainingLecturerEntity(trainingLecturer);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转到讲师添加明细页面 */
	public String goAddtrainingChannel() {
		try {
			String lineItemIdStr = getRequestParameter("lineItemId");
			if (null != lineItemIdStr) {
				trainingLecturer = trainingChannelController.findTrainingLecturerById(lineItemIdStr);
			}
			logger.info("添加讲师明细");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddtrainingChannel";
	}

	public List<TrainingChannel> getTrainingChannelList() {
		return trainingChannelList;
	}
	public void setTrainingChannelList(List<TrainingChannel> trainingChannelList) {
		this.trainingChannelList = trainingChannelList;
	}
	public TrainingChannel getTrainingChannel() {
		return trainingChannel;
	}
	public void setTrainingChannel(TrainingChannel trainingChannel) {
		this.trainingChannel = trainingChannel;
	}
	public TrainingLecturer getTrainingLecturer() {
		return trainingLecturer;
	}
	public void setTrainingLecturer(TrainingLecturer trainingLecturer) {
		this.trainingLecturer = trainingLecturer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
