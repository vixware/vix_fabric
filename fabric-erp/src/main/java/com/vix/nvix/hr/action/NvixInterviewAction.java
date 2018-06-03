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
import com.vix.hr.job.entity.HrDataSelection;
import com.vix.hr.job.entity.HrHirManage;
import com.vix.hr.job.entity.HrInterviewManagement;
import com.vix.hr.job.entity.HrRecruitSummary;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @面试管理/录用管理/资料刷选/招聘总结
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixInterviewAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
    private HrInterviewManagement hrInterviewManagement;//面试管理
    private HrHirManage hrHirManage;//录用管理
    private HrDataSelection hrDataSelection;//资料刷选
    private HrRecruitSummary  hrRecruitSummary;//招聘总结
	@Override
	public String goList() {
		return GO_LIST;
	}
	
    /** 面试列表*/
	public void goInterviewList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String candidateName = getRequestParameter("candidateName");
			if (StringUtils.isNotEmpty(candidateName)) {
				params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrInterviewManagement.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 面试*/
	public String goSaveOrUpdateInterview(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrInterviewManagement = vixntBaseService.findEntityById(HrInterviewManagement.class, id);
			}else{
				hrInterviewManagement = new HrInterviewManagement();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateCollect";
	}
	
	/** 保存面试*/
	public void saveOrUpdateInterview(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrInterviewManagement.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrInterviewManagement);
			hrInterviewManagement = vixntBaseService.merge(hrInterviewManagement);
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
	
	/** 删除面试*/
	public void deleteInterviewById(){
		try {
			hrInterviewManagement = vixntBaseService.findEntityById(HrInterviewManagement.class, id);
			if (null != hrInterviewManagement) {
				vixntBaseService.deleteByEntity(hrInterviewManagement);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
   
	/** 录用管理*/
	public  String goEmployList(){
		return "goEmployList";
	}
	
	/** 录用管理列表*/
	public void employList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String candidateName = getRequestParameter("candidateName");
			if (StringUtils.isNotEmpty(candidateName)) {
				params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrHirManage.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增录用管理*/
	public String goSaveOrUpdateEmploy(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrHirManage = vixntBaseService.findEntityById(HrHirManage.class, id);
			}else{
				hrHirManage = new HrHirManage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateEmploy";
	}
	
	/** 保存录用管理*/
	public void saveOrUpdateEmploy(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrHirManage.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrHirManage);
			hrHirManage = vixntBaseService.merge(hrHirManage);
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
	
	/** 资料筛选*/
	public String goSelectDataList(){
		return "goSelectDataList";
	}
	
	/** 资料筛选列表*/
	public void selectDataList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String candidateName = getRequestParameter("candidateName");
			if (StringUtils.isNotEmpty(candidateName)) {
				params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrDataSelection.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增资料筛选*/
	public String goSaveOrUpdateData(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrDataSelection = vixntBaseService.findEntityById(HrDataSelection.class, id);
			}else{
				hrDataSelection = new HrDataSelection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateData";
	}
	
	/** 保存资料刷选*/
	public void saveOrUpdateData(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrDataSelection.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrDataSelection);
			hrDataSelection = vixntBaseService.merge(hrDataSelection);
			if (isSave) {
				renderText("1:" + hrDataSelection.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + hrDataSelection.getId() + ":" + UPDATE_SUCCESS);
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
	
	/** 后备简历库*/
	public String goResumeLibraryList(){
		return "goResumeLibraryList";
	}
	
	/** 简历库列表*/
	public void resumeLibraryList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String candidateName = getRequestParameter("candidateName");
			if (StringUtils.isNotEmpty(candidateName)) {
				params.put("candidateName," + SearchCondition.ANYLIKE, candidateName);
			}
			params.put("employmentSituation," + SearchCondition.EQUAL, "2");
			params.put("intoTheCategory," + SearchCondition.EQUAL, "2");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrInterviewManagement.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdateResumeLibrary(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrInterviewManagement = vixntBaseService.findEntityById(HrInterviewManagement.class, id);
			}else{
				hrInterviewManagement = new HrInterviewManagement();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateResumeLibrary";
	}
	
	public void saveOrUpdateResumeLibrary(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrInterviewManagement.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrInterviewManagement);
			hrInterviewManagement = vixntBaseService.merge(hrInterviewManagement);
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
	
	/** 招聘总结*/
	public String goSumRecruit(){
		 return "goSumRecruit";
	}
	
	/** 招聘总结列表*/
	public void goSumRecruitList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String recruitmentPlanName = getRequestParameter("recruitmentPlanName");
			if (StringUtils.isNotEmpty(recruitmentPlanName)) {
				params.put("recruitmentPlanName," + SearchCondition.ANYLIKE, recruitmentPlanName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitSummary.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增招聘总结*/
	public String goSaveOrUpdateSumRecruit(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrRecruitSummary = vixntBaseService.findEntityById(HrRecruitSummary.class, id);
			}else{
				hrRecruitSummary = new HrRecruitSummary();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSumRecruit";
	}
	
	/** 保存招聘总结*/
	public void saveOrUpdateSumRecruit(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitSummary.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrRecruitSummary);
			hrRecruitSummary = vixntBaseService.merge(hrRecruitSummary);
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
	
	/** 删除招聘总结*/
	public void deleteSumRecruitById(){
		try {
			HrRecruitSummary recruit = vixntBaseService.findEntityById(HrRecruitSummary.class, id);
			if (null != recruit) {
				vixntBaseService.deleteByEntity(recruit);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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
				params.put("hrDataSelection.id," + SearchCondition.EQUAL, id);
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
    					hrDataSelection = vixntBaseService.findEntityById(HrDataSelection.class,id);
    					if (hrDataSelection != null) {
    						hrAttachment.setHrDataSelection(hrDataSelection);
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
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public HrInterviewManagement getHrInterviewManagement() {
		return hrInterviewManagement;
	}

	public void setHrInterviewManagement(HrInterviewManagement hrInterviewManagement) {
		this.hrInterviewManagement = hrInterviewManagement;
	}

	public HrHirManage getHrHirManage() {
		return hrHirManage;
	}

	public void setHrHirManage(HrHirManage hrHirManage) {
		this.hrHirManage = hrHirManage;
	}

	public HrDataSelection getHrDataSelection() {
		return hrDataSelection;
	}

	public void setHrDataSelection(HrDataSelection hrDataSelection) {
		this.hrDataSelection = hrDataSelection;
	}

	public HrRecruitSummary getHrRecruitSummary() {
		return hrRecruitSummary;
	}

	public void setHrRecruitSummary(HrRecruitSummary hrRecruitSummary) {
		this.hrRecruitSummary = hrRecruitSummary;
	}
	
}
