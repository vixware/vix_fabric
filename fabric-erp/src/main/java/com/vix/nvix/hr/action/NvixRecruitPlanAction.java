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
import com.vix.hr.job.entity.HrRecruitmentPlanDetails;
import com.vix.hr.job.entity.HrRecruitmentPlansummary;
import com.vix.hr.job.entity.HrRecruitplan;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @招聘计划
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixRecruitPlanAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String planDetailId;
	private String hrplanId;
	private String plansummaryId;
	private Employee employee;
    private HrRecruitplan hrplan;
    private HrRecruitmentPlanDetails planDetail;
    private HrRecruitmentPlansummary  plansummary;//招聘汇总
	@Override
	public String goList() {
		return GO_LIST;
	}
    /** 填报列表*/
	public void goWriteList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String programName = getRequestParameter("programName");
			if (StringUtils.isNotEmpty(programName)) {
				params.put("programName," + SearchCondition.ANYLIKE, programName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitplan.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 填报*/
	public String goSaveOrUpdateWrite(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrplan = vixntBaseService.findEntityById(HrRecruitplan.class, id);
			}else{
				hrplan = new HrRecruitplan();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateWrite";
	}
	
	/** 招聘计划明细*/
	public String goSaveOrUpdatePlanDetail(){
		try {
			if(StringUtils.isNotEmpty(id)){
				planDetail = vixntBaseService.findEntityById(HrRecruitmentPlanDetails.class, id);
			}else{
				planDetail = new HrRecruitmentPlanDetails();
				if(StringUtils.isNotEmpty(hrplanId)){
					hrplan = vixntBaseService.findEntityById(HrRecruitplan.class, hrplanId);
					if(hrplan != null){
						 planDetail.setHrRecruitplan(hrplan);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdatePlanItem";
	}
	
	/** 招聘计划明细-修改保存*/
	public void saveOrUpdatePlanDetail(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(planDetail.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(planDetail);
			planDetail = vixntBaseService.merge(planDetail);
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
	/** 招聘计划-修改保存*/
	public void saveOrUpdatePlan(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrplan.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrplan);
			hrplan = vixntBaseService.merge(hrplan);
			if (isSave) {
				renderText("1:" + hrplan.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + hrplan.getId() + ":" + UPDATE_SUCCESS);
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
	/** 招聘明细列表*/
	public void getPlanDetailList(){
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String projectContent = getRequestParameter("projectContent");
			if (StringUtils.isNotEmpty(projectContent)) {
				params.put("projectContent," + SearchCondition.ANYLIKE, projectContent);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("hrRecruitplan.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitmentPlanDetails.class, params);
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
				params.put("hrRecruitplan.id," + SearchCondition.EQUAL, id);
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
    /** 删除招聘明细*/
    public void deletePlanDetailById(){
    	try {
			if(StringUtils.isNoneEmpty(id)){
				HrRecruitmentPlanDetails planDetails = vixntBaseService.findEntityById(HrRecruitmentPlanDetails.class, id);
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
    					hrplan = vixntBaseService.findEntityById(HrRecruitplan.class,id);
    					if (hrplan != null) {
    						hrAttachment.setHrRecruitplan(hrplan);
    					}
    				}
    				vixntBaseService.merge(hrAttachment);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /** 汇总*/
    public String goCollectPlanList(){
    	return "collectPlanList";
    }
    
    /** 汇总列表*/
    public void collectPlanList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String recruitment = getRequestParameter("recruitment");
			if (StringUtils.isNotEmpty(recruitment)) {
				params.put("recruitment," + SearchCondition.ANYLIKE, recruitment);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitmentPlansummary.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 新增汇总*/
    public String goSaveOrUpdateCollect(){
		try {
			if(StringUtils.isNotEmpty(id)){
				plansummary = vixntBaseService.findEntityById(HrRecruitmentPlansummary.class, id);
			}else{
				plansummary = new HrRecruitmentPlansummary();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateCollect";
	}
    
    /** 汇总-修改保存*/
    public void saveOrUpdateCollect(){
    	boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(plansummary.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(plansummary);
			plansummary = vixntBaseService.merge(plansummary);
			if (isSave) {
				renderText("1:" + plansummary.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + plansummary.getId() + ":" + UPDATE_SUCCESS);
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
   
    /** 招聘计划*/
    public String goSaveOrUpdateTrainPlan(){
    	try {
			if(StringUtils.isNotEmpty(id)){
				hrplan = vixntBaseService.findEntityById(HrRecruitplan.class, id);
			}else{
				hrplan = new HrRecruitplan();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateTrainPlan";
    }
    
    /** 选择招聘计划*/
    public String goChooseTrainPlan(){
    	return "goChooseTrainPlan";
    }
    
    /** 增加招聘计划*/
    public void createTrainPlan(){
    	try {
			 if(StringUtils.isNotEmpty(plansummaryId)){
				plansummary = vixntBaseService.findEntityById(HrRecruitmentPlansummary.class, plansummaryId);
				if(StringUtils.isNotEmpty(id)){
					 hrplan = vixntBaseService.findEntityById(HrRecruitplan.class, id);
					 if(plansummary != null && hrplan!= null){
						  hrplan.setHrRecruitmentPlansummary(plansummary);
						  initEntityBaseController.initEntityBaseAttribute(hrplan);
						  hrplan = vixntBaseService.merge(hrplan);
						  renderText("1:"+hrplan.getId()+":增加成功!");
					 }else{
						 renderText("0:" + SAVE_FAIL);
					 }
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /** 培训计划列表*/
	public void goTrainPlanList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String programName = getRequestParameter("programName");
			if (StringUtils.isNotEmpty(programName)) {
				params.put("programName," + SearchCondition.ANYLIKE, programName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("hrRecruitmentPlansummary.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitplan.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 删除培训计划*/
	public void deleteTrainPlanById(){
		try {
			if (StringUtils.isNotEmpty(id)) {
				hrplan = vixntBaseService.findEntityById(HrRecruitplan.class, id);
				if (null != hrplan) {
					vixntBaseService.deleteByEntity(hrplan);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** 下达*/
	public String goDownPlanList(){
    	return "downPlanList";
    } 
	
	/** 下达列表*/
	public void downPlanList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String recruitment = getRequestParameter("recruitment");
			if (StringUtils.isNotEmpty(recruitment)) {
				params.put("recruitment," + SearchCondition.ANYLIKE, recruitment);
			}
			/* uploadPersonId包含当前登录人id */
			/*String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL, employeeId);*/
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitmentPlansummary.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 查看招聘计划*/
	public String goSaveOrUpdateDown(){
		try {
			if(StringUtils.isNotEmpty(id)){
				plansummary = vixntBaseService.findEntityById(HrRecruitmentPlansummary.class, id);
			}else{
				plansummary = new HrRecruitmentPlansummary();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateDown";
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
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public HrRecruitplan getHrplan() {
		return hrplan;
	}
	public void setHrplan(HrRecruitplan hrplan) {
		this.hrplan = hrplan;
	}
	
	public String getPlanDetailId() {
		return planDetailId;
	}
	public void setPlanDetailId(String planDetailId) {
		this.planDetailId = planDetailId;
	}
	public HrRecruitmentPlanDetails getPlanDetail() {
		return planDetail;
	}
	public void setPlanDetail(HrRecruitmentPlanDetails planDetail) {
		this.planDetail = planDetail;
	}
	public HrRecruitmentPlansummary getPlansummary() {
		return plansummary;
	}
	public void setPlansummary(HrRecruitmentPlansummary plansummary) {
		this.plansummary = plansummary;
	}
	public String getPlansummaryId() {
		return plansummaryId;
	}
	public void setPlansummaryId(String plansummaryId) {
		this.plansummaryId = plansummaryId;
	}
	public String getHrplanId() {
		return hrplanId;
	}
	public void setHrplanId(String hrplanId) {
		this.hrplanId = hrplanId;
	}
	
}
