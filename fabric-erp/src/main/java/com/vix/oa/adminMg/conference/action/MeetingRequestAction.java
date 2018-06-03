package com.vix.oa.adminMg.conference.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.controller.MeetingRequestController;
import com.vix.oa.adminMg.conference.entity.ApplicationMg;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;

/**
 * 
 * @ClassName: MeetingRequestAction
 * @Description: 会议室管理
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-3-19 上午11:10:14
 */
@Controller
@Scope("prototype")
public class MeetingRequestAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(MeetingRequestController.class);
	
	@Autowired
	private MeetingRequestController meetingRequestController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private MeetingRequest meetingRequest;
	private ApplicationMg applicationMg;
	private String id;
	private String pageNo;
	/** 会议室管理*/
	private List<MeetingRequest> meetingRequestList;
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			meetingRequestList = meetingRequestController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			//状态
			String meetingRoomStatus = getRequestParameter("meetingRoomStatus");
			if (null != meetingRoomStatus && !"".equals(meetingRoomStatus)) {
				params.put("meetingRoomStatus," + SearchCondition.EQUAL, Integer.parseInt(meetingRoomStatus));
			}
			// 按最近使用
			String createTime = getRequestParameter("createTime");
			if (createTime != null && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(createTime));
			}
			//uploadPersonId包含当前登录人id
			/*Long employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);*/
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("createTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = meetingRequestController.doSubSingleList(params,getPager());
			logger.info("获取会议室基础数据列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取会议室基础数据搜索列表数据 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				code = URLDecoder.decode(code, "utf-8");
			}
			// 名称
			String meetingName = getRequestParameter("meetingName");
			if (null != meetingName && !"".equals(meetingName)) {
				meetingName = URLDecoder.decode(meetingName, "utf-8");
			}
			// 管理员
			String creator = getRequestParameter("creator");
			if (null != creator && !"".equals(creator)) {
				creator = URLDecoder.decode(creator, "utf-8");
			}
			// 设备情况
			String equipment = getRequestParameter("equipment");
			if (null != equipment && !"".equals(equipment)) {
				equipment = URLDecoder.decode(equipment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("meetingName," + SearchCondition.ANYLIKE, meetingName);
				pager = meetingRequestController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != code && !"".equals(code)) {
					params.put("code," + SearchCondition.ANYLIKE, code);
				}
				if (null != meetingName && !"".equals(meetingName)) {
					params.put("meetingName," + SearchCondition.ANYLIKE, meetingName);
				}
				if (null != creator && !"".equals(creator)) {
					params.put("creator," + SearchCondition.ANYLIKE, creator);
				}
				if (null != equipment && !"".equals(equipment)) {
					params.put("equipment," + SearchCondition.ANYLIKE, equipment);
				}
				pager = meetingRequestController.goSingleList(params, getPager());
			}
			logger.info("获取会议室基础数据搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				meetingRequest = meetingRequestController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != meetingRequest.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(meetingRequest);
			/**索引 */
			String description = meetingRequest.getDescription();
			String py = ChnToPinYin.getPYString(description);
			meetingRequest.setChineseFirstLetter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(meetingRequest);
			this.meetingRequest.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.meetingRequest.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			meetingRequest.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			meetingRequest = meetingRequestController.doSaveSalesOrder(meetingRequest);
			meetingRequest.setCreateTime(new Date());
			meetingRequest.setMeetingRoomStatus(0);
			this.saveBaseEntity(this.meetingRequest);
			logger.info("新增！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			MeetingRequest pb = meetingRequestController.findEntityById(id);
			if (null != pb) {
				meetingRequestController.doDeleteByEntity(pb);
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
	 * 会议申请
	 * @return
	 */
	public String goMeetingRoom() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				meetingRequest = meetingRequestController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goMeetingRoom";
	}
	

	public MeetingRequest getMeetingRequest() {
		return meetingRequest;
	}

	public void setMeetingRequest(MeetingRequest meetingRequest) {
		this.meetingRequest = meetingRequest;
	}

	public List<MeetingRequest> getMeetingRequestList() {
		return meetingRequestList;
	}

	public void setMeetingRequestList(List<MeetingRequest> meetingRequestList) {
		this.meetingRequestList = meetingRequestList;
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

	public ApplicationMg getApplicationMg() {
		return applicationMg;
	}

	public void setApplicationMg(ApplicationMg applicationMg) {
		this.applicationMg = applicationMg;
	}

}
