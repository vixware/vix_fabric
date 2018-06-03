package com.vix.nvix.hr.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.trainning.entity.TrainingChannel;
import com.vix.hr.trainning.entity.TrainingCourse;
import com.vix.hr.trainning.entity.TrainingData;
import com.vix.hr.trainning.entity.TrainingLecturer;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @培训体系
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixTrainSystemAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String channelId;
	private Employee employee;
    private TrainingCourse course;//培训课程
    private TrainingData trainingData;//培训资料
    private TrainingLecturer trainingLecturer;//培训讲师
    private TrainingChannel channel;//培训渠道
    
	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 培训课程列表*/
	public void goCourseList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String courseName = getRequestParameter("courseName");
			if (StringUtils.isNotEmpty(courseName)) {
				params.put("courseName," + SearchCondition.ANYLIKE, courseName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, TrainingCourse.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
   /** 增加培训课程*/
	public String goSaveOrUpdateCourse(){
		try {
			if(StringUtils.isNotEmpty(id)){
				course = vixntBaseService.findEntityById(TrainingCourse.class, id);
			}else{
				course = new TrainingCourse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCourse";
	}
	
	/** 保存培训课程*/
	public void saveOrUpdateCourse(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(course.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(course);
			course = vixntBaseService.merge(course);
			if (isSave) {
				renderText("1:" + course.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + course.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	
	/** 删除培训课程*/
	public void deleteCourseById(){
		try {
			TrainingCourse trainingCourse = vixntBaseService.findEntityById(TrainingCourse.class, id);
			if (null != trainingCourse) {
				vixntBaseService.deleteByEntity(trainingCourse);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		
	}
	//////////////////////////////////////////////////////////////////////
	/** 培训资料*/
	public String goDataList(){
		return "goDataList";
	}
	
	/** 培训资料列表*/
	public void dataList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String datumName = getRequestParameter("datumName");
			if (StringUtils.isNotEmpty(datumName)) {
				params.put("datumName," + SearchCondition.ANYLIKE, datumName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, TrainingData.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 增加培训资料*/
	public String goSaveOrUpdateData(){
		try {
			if(StringUtils.isNotEmpty(id)){
				trainingData = vixntBaseService.findEntityById(TrainingData.class, id);
			}else{
				trainingData = new TrainingData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateData";
	}
	/** 保存培训资料*/
	public void saveOrUpdateData(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingData.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainingData);
			trainingData = vixntBaseService.merge(trainingData);
			if (isSave) {
				renderText("1:" + trainingData.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + trainingData.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	/** 删除培训课程*/
	public void deleteDataById(){
		try {
			TrainingData data = vixntBaseService.findEntityById(TrainingData.class, id);
			if (null != data) {
				vixntBaseService.deleteByEntity(data);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		
	}
	////////////////////////////////////////////////////////////////////////
	/** 培训讲师*/
	public String goTeacherList(){
		return "goTeacherList";
	}
	
	/** 培训讲师列表*/
	public void teacherList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String lecturerName = getRequestParameter("lecturerName");
			if (StringUtils.isNotEmpty(lecturerName)) {
				params.put("lecturerName," + SearchCondition.ANYLIKE, lecturerName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, TrainingLecturer.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 增加培训讲师*/
	public String goSaveOrUpdateTeacher(){
		try {
			if(StringUtils.isNotEmpty(id)){
				trainingLecturer = vixntBaseService.findEntityById(TrainingLecturer.class, id);
			}else{
				trainingLecturer = new TrainingLecturer();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTeacher";
	}
	/** 保存讲师资料*/
	public void saveOrUpdateTeacher(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainingLecturer.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainingLecturer);
			trainingLecturer = vixntBaseService.merge(trainingLecturer);
			if (isSave) {
				renderText("1:" + trainingLecturer.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + trainingLecturer.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	/** 删除讲师*/
	public void deleteTeacherById(){
		try {
			TrainingLecturer trew = vixntBaseService.findEntityById(TrainingLecturer.class, id);
			if (null != trew) {
				vixntBaseService.deleteByEntity(trew);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////
	/** 培训渠道*/
	public String goChannelList(){
		return "goChannelList";
	}
	
	/** 渠道列表*/
	public void channelList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String channelName = getRequestParameter("channelName");
			if (StringUtils.isNotEmpty(channelName)) {
				params.put("channelName," + SearchCondition.ANYLIKE, channelName);
			}
			Pager pager = new Pager();
			if(null == pager.getOrderField() || "".equals(pager.getOrderField())){
				pager.setOrderField("createTime");
			    pager.setOrderBy("desc");
			}
			vixntBaseService.findPagerByHqlConditions(pager, TrainingChannel.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 增加渠道*/
	public String goSaveOrUpdateChannel(){
		try {
			if(StringUtils.isNotEmpty(id)){
				channel = vixntBaseService.findEntityById(TrainingChannel.class, id);
			}else{
				channel = new TrainingChannel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateChannel";
	}
	
	/**讲师列表*/
	public void goChooseTeacherList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String lecturerName = getRequestParameter("lecturerName");
			if (StringUtils.isNotEmpty(lecturerName)) {
				params.put("lecturerName," + SearchCondition.ANYLIKE, lecturerName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("trainingChannel.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingLecturer.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 选择培训讲师*/
	public String goChooseTeacher(){
    	return "goChooseTeacher";
    }
	
	/** 增加招聘计划*/
    public void createTeacher(){
    	try {
			 if(StringUtils.isNotEmpty(channelId)){
				 channel = vixntBaseService.findEntityById(TrainingChannel.class, channelId);
				if(StringUtils.isNotEmpty(id)){
					trainingLecturer = vixntBaseService.findEntityById(TrainingLecturer.class, id);
					 if(trainingLecturer != null && channel!= null){
						  trainingLecturer.setTrainingChannel(channel);
						  initEntityBaseController.initEntityBaseAttribute(trainingLecturer);
						  trainingLecturer = vixntBaseService.merge(trainingLecturer);
						  renderText("1:"+trainingLecturer.getId()+":增加成功!");
					 }else{
						 renderText("0:" + SAVE_FAIL);
					 }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/** 保存培训渠道*/
	public void saveOrUpdateChannel(){
    	boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(channel.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(channel);
			channel = vixntBaseService.merge(channel);
			if (isSave) {
				renderText("1:" + channel.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + channel.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TrainingCourse getCourse() {
		return course;
	}

	public void setCourse(TrainingCourse course) {
		this.course = course;
	}

	public TrainingData getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(TrainingData trainingData) {
		this.trainingData = trainingData;
	}

	public TrainingLecturer getTrainingLecturer() {
		return trainingLecturer;
	}

	public void setTrainingLecturer(TrainingLecturer trainingLecturer) {
		this.trainingLecturer = trainingLecturer;
	}

	public TrainingChannel getChannel() {
		return channel;
	}

	public void setChannel(TrainingChannel channel) {
		this.channel = channel;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
}
