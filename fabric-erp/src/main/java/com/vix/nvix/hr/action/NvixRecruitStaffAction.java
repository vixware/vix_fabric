package com.vix.nvix.hr.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.job.entity.HrRecruitApplication;
import com.vix.hr.job.entity.HrRecruitactivitity;
import com.vix.hr.job.entity.HrRecruitactivitityDetails;
import com.vix.hr.job.entity.HrRecruitnoficaition;
import com.vix.hr.job.entity.HrSenior;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.service.IVixntBaseService;
/**
 * @招聘征集/用人申请/招聘活动/高级人才招聘
 * 
 * @author luchuan
 *
 */
@Controller
@Scope("prototype")
public class NvixRecruitStaffAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired 
	private IVixntBaseService vixntBaseService;

	private String id;
	private String hrRecruitactivitityId;
    private HrRecruitnoficaition recruitStaff;//招聘征集
    private HrRecruitApplication hrRecruitApplication;//用人申请
    private HrRecruitactivitity hrRecruitactivitity;//招聘活动
    private HrRecruitactivitityDetails activitityDetail;//招聘活动明细
    private HrSenior hrSenior;//高级人才招聘
    
	@Override
	public String goList() {
		return GO_LIST;
	}
    /** 招聘征集列表*/
	public void goCollectList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String noticeTheName = getRequestParameter("noticeTheName");
			if (StringUtils.isNotEmpty(noticeTheName)) {
				params.put("noticeTheName," + SearchCondition.ANYLIKE, noticeTheName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitnoficaition.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 招聘征集*/
	public String goSaveOrUpdateCollect(){
		try {
			if(StringUtils.isNotEmpty(id)){
				recruitStaff = vixntBaseService.findEntityById(HrRecruitnoficaition.class, id);
			}else{
				recruitStaff = new HrRecruitnoficaition();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateCollect";
	}
	
	/** 保存招聘征集*/
	public void saveOrUpdateCollect(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(recruitStaff.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(recruitStaff);
			recruitStaff = vixntBaseService.merge(recruitStaff);
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
	
	/** 删除招聘征集*/
	public void deleteCollectById(){
		try {
			recruitStaff = vixntBaseService.findEntityById(HrRecruitnoficaition.class, id);
			if (null != recruitStaff) {
				vixntBaseService.deleteByEntity(recruitStaff);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	//////////////////////////////////////////////////////////////////////////
	/** 用人申请*/
	public String goApplyList(){
		return "goApplyList";
	}
	
	public void applyList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String applicationNumber = getRequestParameter("applicationNumber");
			if (StringUtils.isNotEmpty(applicationNumber)) {
				params.put("applicationNumber," + SearchCondition.ANYLIKE, applicationNumber);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitApplication.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 用人申请*/
	public String goSaveOrUpdateApply(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrRecruitApplication = vixntBaseService.findEntityById(HrRecruitApplication.class, id);
			}else{
				hrRecruitApplication = new HrRecruitApplication();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gosaveOrUpdateApply";
	}
	
	/** 保存用人申请*/
	public void saveOrUpdateApply(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitApplication.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrRecruitApplication);
			hrRecruitApplication = vixntBaseService.merge(hrRecruitApplication);
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
	
	/** 删除用人申请*/
	public void deleteApplyById(){
		try {
			 hrRecruitApplication = vixntBaseService.findEntityById(HrRecruitApplication.class, id);
			if (null != hrRecruitApplication) {
				vixntBaseService.deleteByEntity(hrRecruitApplication);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/////////////////////////////////////////////////////////////////////////////
	/** 招聘汇总*/
	public String goAllRecruitList(){
		return "goAllRecruitList";
	}
	
	public void allRecruitList(){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String applicationNumber = getRequestParameter("applicationNumber");
			if (StringUtils.isNotEmpty(applicationNumber)) {
				params.put("applicationNumber," + SearchCondition.ANYLIKE, applicationNumber);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitApplication.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 招聘汇总*/
	public String goSaveOrUpdateAllRecruit(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrRecruitApplication = vixntBaseService.findEntityById(HrRecruitApplication.class, id);
			}else{
				hrRecruitApplication = new HrRecruitApplication();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gosaveOrUpdateAllRecruit";
	}
	
	/** 保存招聘汇总*/
	public void saveOrUpdateAllRecruit(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitApplication.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrRecruitApplication);
			hrRecruitApplication = vixntBaseService.merge(hrRecruitApplication);
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
	
	/** 删除招聘汇总*/
	public void deleteAllRecruitById(){
		try {
			 hrRecruitApplication = vixntBaseService.findEntityById(HrRecruitApplication.class, id);
			if (null != hrRecruitApplication) {
				vixntBaseService.deleteByEntity(hrRecruitApplication);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/////////////////////////////////////////////////////////////////////////////
	/** 招聘活动*/
	public String goActivityList(){
		return "goActivityList";
	}
	
	/** 招聘活动列表*/
	public void activityList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String activityName = getRequestParameter("activityName");
			if (StringUtils.isNotEmpty(activityName)) {
				params.put("activityName," + SearchCondition.ANYLIKE, activityName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitactivitity.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/** 招聘活动*/
	public String goSaveOrUpdateActivity(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrRecruitactivitity = vixntBaseService.findEntityById(HrRecruitactivitity.class, id);
			}else{
				hrRecruitactivitity = new HrRecruitactivitity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateActivity";
	}
	
	/** 保存修改招聘活动*/
	public void saveOrUpdateActivity(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrRecruitactivitity.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrRecruitactivitity);
			hrRecruitactivitity = vixntBaseService.merge(hrRecruitactivitity);
			if (isSave) {
				renderText("1:" + hrRecruitactivitity.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText("1:" + hrRecruitactivitity.getId() + ":" + UPDATE_SUCCESS);
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
	
	/** 招聘活动明细列表*/
	public void getActivityDetailList(){
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String job = getRequestParameter("job");
			if (StringUtils.isNotEmpty(job)) {
				params.put("job," + SearchCondition.ANYLIKE, job);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("hrRecruitactivitity.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrRecruitactivitityDetails.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 招聘明细*/
	public String goSaveOrUpdateActivityDetail(){
		try {
			if(StringUtils.isNotEmpty(id)){
				activitityDetail = vixntBaseService.findEntityById(HrRecruitactivitityDetails.class, id);
			}else{
				activitityDetail = new HrRecruitactivitityDetails();
				if(StringUtils.isNotEmpty(hrRecruitactivitityId)){
					hrRecruitactivitity = vixntBaseService.findEntityById(HrRecruitactivitity.class, hrRecruitactivitityId);
					if(hrRecruitactivitity != null){
						activitityDetail.setHrRecruitactivitity(hrRecruitactivitity);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateActivityDetail";
	}
	
	/** 修改保存招聘活动明细*/
	public void saveOrUpdateActivityDetail(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(activitityDetail.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(activitityDetail);
			activitityDetail = vixntBaseService.merge(activitityDetail);
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
	
	/** 删除招聘活动明细*/
	public void deleteActivityDetailById(){
		try {
			if(StringUtils.isNoneEmpty(id)){
				HrRecruitactivitityDetails detail = vixntBaseService.findEntityById(HrRecruitactivitityDetails.class, id);
				 if(null != detail){
					 vixntBaseService.deleteByEntity(detail);
					 renderText(DELETE_SUCCESS);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/** 高级人才招聘*/
	public String goSuperTalentList(){
		return "goSuperTalentList";
	}
	
    /** 高级人才招聘列表*/
	public void superTalentList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String applicantsName = getRequestParameter("applicantsName");
			if (StringUtils.isNotEmpty(applicantsName)) {
				params.put("applicantsName," + SearchCondition.ANYLIKE, applicantsName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HrSenior.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 新增高级人才招聘*/
	public String goSaveOrUpdateSuperTalent(){
		try {
			if(StringUtils.isNotEmpty(id)){
				hrSenior = vixntBaseService.findEntityById(HrSenior.class, id);
			}else{
				hrSenior = new HrSenior();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveOrUpdateSuperTalent";
	}
	
	/** 保存高级人才招聘*/
	public void saveOrUpdateSuperTalent(){
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(hrSenior.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(hrSenior);
			hrSenior = vixntBaseService.merge(hrSenior);
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
	
	/** 富文本上传*/
	public String uploadFile(){
		//文件保存目录路径    img_upload是服务器存储上传图片的目录名
        String savePath = getServletContext().getRealPath("/") + "/resources/attached/kindeditor/";
        //文件保存目录URL
        String saveUrl = getRequest().getContextPath() + "/resources/attached/kindeditor/";
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        //允许最大上传文件大小
        long maxSize = 5000000;
        //Struts2 请求 包装过滤器
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)ServletActionContext.getRequest();
        
        //获得上传的文件名
        String fileName = wrapper.getFileNames("imgFile")[0];
        //获得文件过滤器
        File file = wrapper.getFiles("imgFile")[0];
        //得到上传文件的扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        //获得文件上传的类型
        String dirName = getRequest().getParameter("dir");
        if (dirName == null) {
                dirName = "image";
        }
        if(!extMap.containsKey(dirName)){
                setMessage("目录名不正确。");
        		return UPDATE;
        }
        //创建文件夹
        savePath += dirName + "/";
        saveUrl += dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
        }
        //检查扩展名
        if (!Arrays.<String> asList(extMap.get(dirName).split(",")).contains(fileExt)) {
                setMessage("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
        		return UPDATE;
        }
        //检查文件大小
        if (file.length() > maxSize) {
                setMessage("上传文件大小超过限制，上传的文件不能超过2M。");
        		return UPDATE;
        } 
        //检查目录
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String dir = sdf.format(new Date());
        File uploadDir = new File(savePath + dir);
        uploadDir.mkdirs();
        if (!uploadDir.isDirectory()) {
                setMessage("上传目录不存在 。");
        		return UPDATE;
        }
        //检查目录写入权限
        if (!uploadDir.canWrite()) {
                setMessage("上传目录没有写入权限。");
        		return UPDATE;
        }
        //重构上传图片的名称 
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String newImgName = df.format(new Date()) + "_"
                        + new Random().nextInt(1000) + "." + fileExt;
        
        byte[] buffer = new byte[1024];
        //获取文件输出流
        FileOutputStream fos = null;
        //获取内存中当前文件输入流
        InputStream in = null;
        try {
        	 fos = new FileOutputStream(savePath + dir + "/" + newImgName);
        	 in = new FileInputStream(file);
            int num = 0;
            while ((num = in.read(buffer)) > 0) {
                    fos.write(buffer, 0, num);
            }
        } catch (Exception e) {
                e.printStackTrace(System.err);
        } finally {
        	try{
                in.close();
                fos.close();
        	}catch(Exception ex){
        		ex.printStackTrace();
        	}
        }

        //发送给 KE 
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", saveUrl + dir + "/" + newImgName);
		setMessage(obj.toJSONString());
		return UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	public HrRecruitnoficaition getRecruitStaff() {
		return recruitStaff;
	}
	public void setRecruitStaff(HrRecruitnoficaition recruitStaff) {
		this.recruitStaff = recruitStaff;
	}
	public HrRecruitApplication getHrRecruitApplication() {
		return hrRecruitApplication;
	}
	public void setHrRecruitApplication(HrRecruitApplication hrRecruitApplication) {
		this.hrRecruitApplication = hrRecruitApplication;
	}
	public HrRecruitactivitity getHrRecruitactivitity() {
		return hrRecruitactivitity;
	}
	public void setHrRecruitactivitity(HrRecruitactivitity hrRecruitactivitity) {
		this.hrRecruitactivitity = hrRecruitactivitity;
	}
	public HrRecruitactivitityDetails getActivitityDetail() {
		return activitityDetail;
	}
	public void setActivitityDetail(HrRecruitactivitityDetails activitityDetail) {
		this.activitityDetail = activitityDetail;
	}
	public String getHrRecruitactivitityId() {
		return hrRecruitactivitityId;
	}
	public void setHrRecruitactivitityId(String hrRecruitactivitityId) {
		this.hrRecruitactivitityId = hrRecruitactivitityId;
	}
	public HrSenior getHrSenior() {
		return hrSenior;
	}
	public void setHrSenior(HrSenior hrSenior) {
		this.hrSenior = hrSenior;
	}
	
}
