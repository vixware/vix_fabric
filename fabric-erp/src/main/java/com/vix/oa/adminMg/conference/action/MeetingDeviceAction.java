
package com.vix.oa.adminMg.conference.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.conference.controller.ApplicationController;
import com.vix.oa.adminMg.conference.entity.MeetingDevice;
import com.vix.oa.adminMg.conference.entity.MeetingRequest;

/**
 * 
 * @ClassName: MeetingDeviceAction
 * @Description: 会议室设备管理
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-6-6 下午3:02:43
 */
@Controller
@Scope("prototype")
public class MeetingDeviceAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ApplicationController.class);
	
	@Autowired
	private ApplicationController applicationController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private MeetingDevice meetingDevice;
	
	private List<MeetingDevice> meetingDeviceList;
	
	/** 会议室  */
	private List<MeetingRequest> meetingRequestList;
	
	private String id;
	
	private String parentId;
	
	private String pageNo;
	
	private Date updateTime;
	
	
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			meetingDeviceList = applicationController.doListMeetingDeviceIndex();
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
			String updateTime = getRequestParameter("updateTime");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}			
			// 按最近使用
			if (null != updateTime && !"".equals(updateTime)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateU = sf.parse(getUpdateTime(updateTime));
				params.put("updateTime," + SearchCondition.MORETHAN, dateU);
			}
			if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){//if(null != parentId && parentId>0){
				params.put("parentCategory.id,"+SearchCondition.EQUAL,parentId);
			}
			Pager pager = applicationController.doMeetingDeviceSingleList(params,
					getPager());
			logger.info("获取办公用品列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 计算最近使用日期 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
	}

	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			meetingRequestList = baseHibernateService.findAllByEntityClass(MeetingRequest.class);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				meetingDevice = applicationController.doListMeetingDeviceById(id);
				logger.info("");
			}else{
				if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){//if(null != parentId && parentId > 0){
					MeetingDevice c = baseHibernateService.findEntityById(MeetingDevice.class,parentId);
					if(null != c){
						meetingDevice = new MeetingDevice();
						meetingDevice.setParentCategory(c);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			meetingRequestList = baseHibernateService.findAllByEntityClass(MeetingRequest.class);
			if(null == meetingDevice.getParentCategory() || null == meetingDevice.getParentCategory().getId()){
				meetingDevice.setParentCategory(null);
			}
			if (null != meetingDevice.getId()) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(meetingDevice);
			meetingDevice = applicationController.doSaveMeetingDevice(meetingDevice);
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
	
	/** 跳转至用户修改页面 *//*
	public String goSaveOrUpdate1(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = officeSuppliesController.doSubSingleList1(params,
					getPager());
			logger.info("获取执行反馈列表数据");
			setPager(pager);
			if (null != id && id.longValue() > 0) {
				officeSupplies = officeSuppliesController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE1;
	}*/
	
	
	/** 处理新增修改操作 */
	/*public String saveOrUpdate1() {
		boolean isSave = true;
		try {
			if (null != officeSupplies.getId()) {
				isSave = false;
			}
			
			initEntityBaseController.initEntityBaseAttribute(officeSupplies);
			officeSupplies = officeSuppliesController.doSaveSalesOrder(officeSupplies);
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
		return UPDATE1;
	}*/
		
	/** 处理删除操作 */
	public String deleteById() {
		try {
			MeetingDevice pb = applicationController.findMeetingDeviceById(id);
			if (null != pb) {
				applicationController.doDeleteByMeetingDevice(pb);
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
	
	/*public void updateIsPublish(){
		PrintWriter out = null;
		try{
			
			OfficeSupplies pb = officeSuppliesController
					.findEntityById(id);
			if(pb.getIsPublish()==0){
				isPublish =1;
				System.out.println(id+"="+isPublish+"===========0");
			}else if(pb.getIsPublish()==1){
				isPublish = 0;
				System.out.println(id+"="+isPublish+"===========1");
			}
			
			pb.setIsPublish(isPublish);
			taskDefinition = taskDefineController.doSaveSalesOrder(pb);
			getResponse().setCharacterEncoding("UTF-8");
			out = getResponse().getWriter();
			out.print(taskDefinition.getIsPublish());
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=out){
				out.close();
				out = null;
			}
		}
	}*/
	
	/** 树形结构JSON */
	public void findTreeToJson(){
		try{
			List<MeetingDevice> listCategory = new ArrayList<MeetingDevice>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			if(null!=id && !"".equals(id)){
				listCategory = baseHibernateService.findAllSubEntity(MeetingDevice.class, "parentCategory.id", id, params);
			}else{
				listCategory = baseHibernateService.findAllSubEntity(MeetingDevice.class, "parentCategory.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for(int i =0;i<listCategory.size();i++){
				MeetingDevice cc = listCategory.get(i);
				if(cc.getSubCategorys().size() > 0){
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getDeviceName());
					strSb.append("\",open:false,isParent:true}");
				}else{
					strSb.append("{id:");
					strSb.append(cc.getId());
					strSb.append(",name:\"");
					strSb.append(cc.getDeviceName());
					strSb.append("\",open:false,isParent:false}");
				}
				if(i < listCategory.size() -1){
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String goChooseCategory(){
		return "goChooseCategory";
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public MeetingDevice getMeetingDevice() {
		return meetingDevice;
	}

	public void setMeetingDevice(MeetingDevice meetingDevice) {
		this.meetingDevice = meetingDevice;
	}

	public List<MeetingDevice> getMeetingDeviceList() {
		return meetingDeviceList;
	}

	public void setMeetingDeviceList(List<MeetingDevice> meetingDeviceList) {
		this.meetingDeviceList = meetingDeviceList;
	}

	public List<MeetingRequest> getMeetingRequestList() {
		return meetingRequestList;
	}

	public void setMeetingRequestList(List<MeetingRequest> meetingRequestList) {
		this.meetingRequestList = meetingRequestList;
	}

}
