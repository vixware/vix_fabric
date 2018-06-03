package com.vix.nvix.hr.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrAttachments;
import com.vix.hr.organization.entity.Employee;
import com.vix.hr.trainning.entity.Plan;
import com.vix.hr.trainning.entity.PlanTrainCourse;
import com.vix.hr.trainning.entity.TrainingPlanning;
import com.vix.hr.trainning.entity.TrainingPlanningDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @培训计划
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixTrainAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String trainDetailId;
	private String trainPlanId;
	private String trainCourseId;
	
	private Employee employee;
    private TrainingPlanning trainPlan;//培训计划
    private TrainingPlanningDetail trainDetail;//培训计划明细
    private Plan plan;//填报计划
    private PlanTrainCourse trainCourse;//培训课程
    
	@Override
	public String goList() {
		return GO_LIST;
	}
	
    /** 活动计划列表*/
	public void goActivityList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String applicationName = getRequestParameter("applicationName");
			if (StringUtils.isNotEmpty(applicationName)) {
				params.put("applicationName," + SearchCondition.ANYLIKE, applicationName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingPlanning.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 活动计划*/
	public String goSaveOrUpdateActivity(){
		try {
			if(StringUtils.isNotEmpty(id)){
				trainPlan = vixntBaseService.findEntityById(TrainingPlanning.class, id);
			}else{
				trainPlan = new TrainingPlanning();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateTrain";
	}
	
	/** 活动计划明细*/
	public String goSaveOrUpdateActivityDetail(){
		try {
			if(StringUtils.isNotEmpty(id)){
				trainDetail = vixntBaseService.findEntityById(TrainingPlanningDetail.class, id);
			}else{
				trainDetail = new TrainingPlanningDetail();
				if(StringUtils.isNotEmpty(trainPlanId)){
					trainPlan = vixntBaseService.findEntityById(TrainingPlanning.class, trainPlanId);
					if(trainPlan != null){
						 trainDetail.setTrainingPlanning(trainPlan);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateTrainDetail";
	}
	
	/** 活动计划明细-修改保存*/
	public void saveOrUpdateActivityDetail(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainDetail.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainDetail);
			trainDetail = vixntBaseService.merge(trainDetail);
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
		
	}
	/** 活动计划-修改保存*/
	public void saveOrUpdateActivity(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainPlan.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainPlan);
			trainPlan = vixntBaseService.merge(trainPlan);
			if (isSave) {
				renderText("1:" + trainPlan.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + trainPlan.getId() + ":" + UPDATE_SUCCESS);
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
	/** 培训计划明细列表*/
	public void getActivityDetailList(){
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String trainingWay = getRequestParameter("trainingWay");
			if (StringUtils.isNotEmpty(trainingWay)) {
				params.put("trainingWay," + SearchCondition.ANYLIKE, trainingWay);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("trainingPlanning.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), TrainingPlanningDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /** 附件列表*/
    public void getUploadersList(){
    	try {
    		Pager pager = getPager();
    		Map<String, Object> params = getParams();
    		String titleName = getDecodeRequestParameter("titleName");
    		if (StringUtils.isNotEmpty(titleName)) {
				params.put("titleName," + SearchCondition.EQUAL, titleName);
			}
    		if (StringUtils.isNotEmpty(id)) {
				params.put("trainingPlanning.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, HrAttachments.class, params);
			}
    		renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** 删除附件*/
    public void deleteUploaderById(){
    	try {
			if(StringUtils.isNoneEmpty(id)){
				HrAttachments hrAttachment = vixntBaseService.findEntityById(HrAttachments.class, id);
				 if(null != hrAttachment){
					 String fileName =hrAttachment.getTitleName();
					 String baseFolder = "c:/file/";
					 String downloadFile = baseFolder + fileName;
					 File f = new File(downloadFile); // 输入要删除的附件位置
					 if (f.exists()) {
							f.delete();
						}
					 vixntBaseService.deleteByEntity(hrAttachment);
					 renderText(DELETE_SUCCESS);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
    }
    /** 删除培训计划明细*/
    public void deletePlanDetailById(){
    	try {
			if(StringUtils.isNoneEmpty(id)){
				TrainingPlanningDetail planDetails = vixntBaseService.findEntityById(TrainingPlanningDetail.class, id);
				 if(null != planDetails){
					 vixntBaseService.deleteByEntity(planDetails);
					 renderText(DELETE_SUCCESS);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
    }
	/** 保存附件*/
    public void saveOrUpdateUploaders(){
    	try {
    		String[] savePathAndName = this.saveEmployeeUploadPic();
    		if(savePathAndName != null && savePathAndName.length == 2){
    			HrAttachments hrAttachment = new HrAttachments();
    			  hrAttachment.setTitleName(savePathAndName[1].toString());
    			  hrAttachment.setPath("/file/ws/" + savePathAndName[1].toString());
    			  hrAttachment.setCreateTime(new Date());
    				if (StringUtils.isNotEmpty(id)) {
    					trainPlan = vixntBaseService.findEntityById(TrainingPlanning.class,id);
    					if (trainPlan != null) {
    						hrAttachment.setTrainingPlanning(trainPlan);
    					}
    				}
    				vixntBaseService.merge(hrAttachment);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
    public String[] saveEmployeeUploadPic() {
		String[] pathAndName = null;
		try {
			if (null != fileToUpload) {
				/** 上传目录 */
				String saveFolder = this.getEmployeeUploadFileSavePic();
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
    
    public String getEmployeeUploadFileSavePic() {

 		String baseFolder = "c:/file";

 		String newFilePath = "";

 		newFilePath = baseFolder;

 		File dir = new File(newFilePath);
 		if (!dir.exists())
 			dir.mkdirs();

 		return newFilePath;
 	}
    ///////////////////////////////////////////////////////////////////////
    /** 填报计划*/
    public String goWriteList(){
    	return "goWriteList";
    } 
    
    /** 填报计划列表*/
    public void writeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String planName = getRequestParameter("planName");
			if (StringUtils.isNotEmpty(planName)) {
				params.put("planName," + SearchCondition.ANYLIKE, planName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Plan.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /** 新增填报计划*/
    public String goSaveOrUpdateWrite(){
    	try {
			if(StringUtils.isNotEmpty(id)){
				plan = vixntBaseService.findEntityById(Plan.class, id);
			}else{
				plan = new Plan();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateWrite";
    }
    
    /** 保存填报计划*/
    public void saveOrUpdateWrite(){
    	boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(plan.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(plan);
			plan = vixntBaseService.merge(plan);
			if (isSave) {
				renderText("1:" + plan.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + plan.getId() + ":" + UPDATE_SUCCESS);
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
    
    /** 删除填报计划*/
    public void deleteWritePlanById(){
    	try {
			if (StringUtils.isNotEmpty(id)) {
				plan = vixntBaseService.findEntityById(Plan.class, id);
				if (null != plan) {
					vixntBaseService.deleteByEntity(plan);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
    }
    ///////////////////////////////////////////////////////////////////////////
    /** 培训计划汇总*/
    public String goAllTrainList(){
    	return "goAllTrainList";
    }
    
    /** 计划汇总列表*/
    public void allTrainList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String leadings = getRequestParameter("leadings");
			if (StringUtils.isNotEmpty(leadings)) {
				params.put("leadings," + SearchCondition.ANYLIKE, leadings);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), PlanTrainCourse.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    /** 新增计划汇总*/
    public String goSaveOrUpdateAllTrain(){
    	try {
			if(StringUtils.isNotEmpty(id)){
				trainCourse = vixntBaseService.findEntityById(PlanTrainCourse.class, id);
			}else{
				trainCourse = new PlanTrainCourse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAllTrain";
    }
    
    /** 保存*/
    public void saveOrUpdateAllTrain(){
    	boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(trainCourse.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(trainCourse);
			trainCourse = vixntBaseService.merge(trainCourse);
			if (isSave) {
				renderText("1:" + trainCourse.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + trainCourse.getId() + ":" + UPDATE_SUCCESS);
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
    
    /** 选择填报计划*/
    public String goChooseWritePlan(){
    	return "goChooseWritePlan";
    }
    
    /** 增加填报计划*/
    public void createWritePlan(){
    	try {
			 if(StringUtils.isNotEmpty(trainCourseId)){
				 trainCourse = vixntBaseService.findEntityById(PlanTrainCourse.class, trainCourseId);
				if(StringUtils.isNotEmpty(id)){
					 plan = vixntBaseService.findEntityById(Plan.class, id);
					 if(trainCourse != null && plan!= null){
						  plan.setPlanTrainCourse(trainCourse);
						  initEntityBaseController.initEntityBaseAttribute(plan);
						  plan = vixntBaseService.merge(plan);
						  renderText("1:"+plan.getId()+":增加成功!");
					 }else{
						 renderText("0:" + SAVE_FAIL);
					 }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** 选择填报计划*/
    public void goWritePlanList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String planName = getRequestParameter("planName");
			if (StringUtils.isNotEmpty(planName)) {
				params.put("planName," + SearchCondition.ANYLIKE, planName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("planTrainCourse.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), Plan.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public String getTrainDetailId() {
		return trainDetailId;
	}
	
	public void setTrainDetailId(String trainDetailId) {
		this.trainDetailId = trainDetailId;
	}
	
	public String getTrainPlanId() {
		return trainPlanId;
	}
	
	public void setTrainPlanId(String trainPlanId) {
		this.trainPlanId = trainPlanId;
	}
	
	public TrainingPlanning getTrainPlan() {
		return trainPlan;
	}
	
	public void setTrainPlan(TrainingPlanning trainPlan) {
		this.trainPlan = trainPlan;
	}
	
	public TrainingPlanningDetail getTrainDetail() {
		return trainDetail;
	}
	
	public void setTrainDetail(TrainingPlanningDetail trainDetail) {
		this.trainDetail = trainDetail;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public PlanTrainCourse getTrainCourse() {
		return trainCourse;
	}

	public void setTrainCourse(PlanTrainCourse trainCourse) {
		this.trainCourse = trainCourse;
	}

	public String getTrainCourseId() {
		return trainCourseId;
	}

	public void setTrainCourseId(String trainCourseId) {
		this.trainCourseId = trainCourseId;
	}
	
}
