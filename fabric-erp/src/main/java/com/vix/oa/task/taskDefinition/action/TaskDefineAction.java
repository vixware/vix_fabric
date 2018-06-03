package com.vix.oa.task.taskDefinition.action;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.vix.oa.task.taskDefinition.controller.TaskDefineController;
import com.vix.oa.task.taskDefinition.entity.ExecutionFeedback;
import com.vix.oa.task.taskDefinition.entity.Uploader;
import com.vix.oa.task.taskDefinition.entity.VixTask;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;
import com.vix.oa.task.typeSettings.entity.CompletionMark;
import com.vix.oa.task.typeSettings.entity.DifficultyCoefficient;
import com.vix.oa.task.typeSettings.entity.TaskLevel;
import com.vix.oa.task.typeSettings.entity.TaskSourceType;
import com.vix.oa.task.typeSettings.entity.TaskType;

/**
 * 
 * @ClassName: TaskDefineAction
 * @Description: 任务定义 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-25 下午4:17:18
 */
@Controller
@Scope("prototype")
public class TaskDefineAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(TaskDefineController.class);
	
	@Autowired
	private ITaskDefineService taskDefineService;
	@Autowired
	private TaskDefineController taskDefineController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private VixTask taskDefinition;
	
	private String id;
	
	private String parentId;
	
	private String pageNo;
	
	
	public Integer isPublish;
	
	private Uploader uploader;
	
	private String eqId;
	
	
	/** 任务定义*/
	private List<VixTask> taskDefinitionList;
	/** 任务来源 */
	private List<TaskSourceType> taskSourceTypeList;
	/** 任务类型 */
	private List<TaskType> taskTypeList;
	/** 难度系数 */
	private List<DifficultyCoefficient> difficultyCoefficientList;
	/** 任务层级 */
	private List<TaskLevel> taskLevelList;
	/** 完成标志 */
	private List<CompletionMark> completionMarkList;
	/** 执行反馈 */
	private ExecutionFeedback executionFeedback;
	/** 任务列表附件集合*/
	private  Set<Uploader> uploaderList;
	/** 任务反馈附件集合 */
	private  Set<Uploader> feedbackUploaderList=new HashSet<Uploader>();
		
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			taskDefinitionList = taskDefineController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取任务定义列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			//状态
			String isPublish = getRequestParameter("isPublish");
			if (null != isPublish && !"".equals(isPublish)) {
				params.put("isPublish," + SearchCondition.EQUAL, Integer.parseInt(isPublish));
			}			
			// 按最近使用
			String endTime = getRequestParameter("endTime");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(endTime));
			}
			if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){
				params.put("parentCategory.id,"+SearchCondition.EQUAL,parentId);
			}
			//uploadPersonId包含当前登录人id
			String employeeId = SecurityUtil.getCurrentUserId();
			params.put("uploadPersonId," + SearchCondition.EQUAL,employeeId);
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("updateTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = taskDefineController.doSubSingleList(params,getPager());
			logger.info("获取任务定义列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取搜索任务定义列表数据 */	
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 标题
			String questName = getRequestParameter("questName");
			if (null != questName && !"".equals(questName)) {
				questName = URLDecoder.decode(questName, "utf-8");
			}
			// 发布人
			String uploadPersonName = getRequestParameter("uploadPersonName");
			if (null != uploadPersonName && !"".equals(uploadPersonName)) {
				uploadPersonName = URLDecoder.decode(uploadPersonName, "utf-8");
			}
			// 有效期
			String validity = getRequestParameter("validity");
			if (null != validity && !"".equals(validity)) {
				validity = URLDecoder.decode(validity, "utf-8");
			}
			// 任务权重
			String taskWeights = getRequestParameter("taskWeights");
			if (null != taskWeights && !"".equals(taskWeights)) {
				taskWeights = URLDecoder.decode(taskWeights, "utf-8");
			}
			// 执行人/部门
			String executiveAgency = getRequestParameter("executiveAgency");
			if (null != executiveAgency && !"".equals(executiveAgency)) {
				executiveAgency = URLDecoder.decode(executiveAgency, "utf-8");
			}
			// 考核人/部门
			String assessDepartment = getRequestParameter("assessDepartment");
			if (null != assessDepartment && !"".equals(assessDepartment)) {
				assessDepartment = URLDecoder.decode(assessDepartment, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("questName," + SearchCondition.ANYLIKE, questName);
				pager = taskDefineController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != questName && !"".equals(questName)) {
					params.put("questName," + SearchCondition.ANYLIKE, questName);
				}
				if (null != uploadPersonName && !"".equals(uploadPersonName)) {
					params.put("uploadPersonName," + SearchCondition.ANYLIKE, uploadPersonName);
				}
				if (null != validity && !"".equals(validity)) {
					params.put("validity," + SearchCondition.ANYLIKE, validity);
				}
				if (null != taskWeights && !"".equals(taskWeights)) {
					params.put("taskWeights," + SearchCondition.ANYLIKE, taskWeights);
				}
				if (null != executiveAgency && !"".equals(executiveAgency)) {
					params.put("executiveAgency," + SearchCondition.ANYLIKE, executiveAgency);
				}
				if (null != assessDepartment && !"".equals(assessDepartment)) {
					params.put("assessDepartment," + SearchCondition.ANYLIKE, assessDepartment);
				}
				pager = taskDefineController.goSingleList(params, getPager());
			}
			logger.info("获取任务搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @ClassName: goSaveOrUpdate
	 * @Description: 新增任务定义数据
	 * @author chenzhengwen
	 * @author w_a_533@163.com 
	 * @date 2014-2-25 下午4:17:18
	 */
	public String goSaveOrUpdate(){
		try {
			taskSourceTypeList = taskDefineService.findAllByEntityClass(TaskSourceType.class);
			taskTypeList = taskDefineService.findAllByEntityClass(TaskType.class);
			difficultyCoefficientList = taskDefineService.findAllByEntityClass(DifficultyCoefficient.class);
			taskLevelList = taskDefineService.findAllByEntityClass(TaskLevel.class);
			completionMarkList = taskDefineService.findAllByEntityClass(CompletionMark.class);
			logger.info("获取列表数据");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskDefinition = taskDefineController.doListEntityById(id);
				logger.info("");
			}else{
				
				if(StringUtils.isNotEmpty(parentId) && !parentId.equals("0")){
					VixTask c = taskDefineService.findEntityById(VixTask.class,parentId);
					if(null != c){
						taskDefinition = new VixTask();
						taskDefinition.setParentVixTask(c);
						taskDefinition.setIsTemp("1");
						taskDefinition=taskDefineService.merge(taskDefinition);
					}
				}else {
					taskDefinition = new VixTask();
					taskDefinition.setIsTemp("1");
					taskDefinition=taskDefineService.merge(taskDefinition);
				}
			}
			Map<String, Object> params1 = getParams();
			params1.put("taskDefinition.id," + SearchCondition.EQUAL, id);
			Pager pager = taskDefineController.doUploader(params1,getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void goListTaskDefinition(){
		try {
			Map<String, Object> params = getParams();
			params.put("id," + SearchCondition.EQUAL, id);
			Pager pager = taskDefineController.doSubSingleList(params,getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @ClassName: saveOrUpdate
	 * @Description: 处理任务定义新增修改操作，并保存当前操作人员
	 * @author chenzhengwen
	 * @author w_a_533@163.com 
	 * @date 2014-2-25 下午4:17:18
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (taskDefinition.getParentVixTask() == null||StringUtils.isNotEmpty(taskDefinition.getParentVixTask().getId() )){
				taskDefinition.setParentVixTask(null);
			}
			if (StringUtils.isNotEmpty(taskDefinition.getId()) && !"".equals(taskDefinition.getId())) {
				isSave = false;
			}
			/**索引 */
			String questName = taskDefinition.getQuestName();
			String py = ChnToPinYin.getPYString(questName);
			taskDefinition.setChineseFirstLetter(py.toUpperCase());
			
			initEntityBaseController.initEntityBaseAttribute(taskDefinition);
			taskDefinition = taskDefineController.doSaveSalesOrder(taskDefinition);
			this.taskDefinition.setUploadPersonId(SecurityUtil.getCurrentUserId());
			this.taskDefinition.setUploadPerson(SecurityUtil.getCurrentUserName());
			/**拿到当前用户的姓名，保存*/
			taskDefinition.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
			taskDefinition.setIsTemp("");
			this.saveBaseEntity(this.taskDefinition);
			logger.info("新增！");
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
		return UPDATE;
	}
	

	/**
	 * 
	 * @ClassName: goSaveOrUpdate1
	 * @Description: 获取执行反馈列表数据，获取该任务对应的附件，获取该任务对应反馈的附件
	 * @author chenzhengwen
	 * @author w_a_533@163.com 
	 * @date 2014-2-25 下午4:17:18
	 */
	public String goSaveOrUpdate1(){
		try {
			Map<String, Object> params = getParams();
			params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
			Pager pager = taskDefineController.doSubSingleList1(params,getPager());
			logger.info("获取执行反馈列表数据");
			setPager(pager);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskDefinition = taskDefineController.doListEntityById(id);
				//获取该任务对应的附件
				uploaderList=  taskDefinition.getUploader();
				if(taskDefinition.getExecutionFeedbacks()!=null){
					for (ExecutionFeedback executionFeedback : taskDefinition.getExecutionFeedbacks()) {
						//获取该任务对应反馈的附件
						feedbackUploaderList.addAll(executionFeedback.getUploader());
					}
				}
						
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrUpdate1";
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate1() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(taskDefinition.getId()) && !"".equals(taskDefinition.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(taskDefinition);
			taskDefinition = taskDefineController.doSaveSalesOrder(taskDefinition);
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
	
	/**
	 * 
	 * @ClassName: goSaveOrUpdate1
	 * @Description: 任务过程查看，获取该任务对应的附件，获取该任务对应，反馈内容和反馈附件
	 * @author chenzhengwen
	 * @author w_a_533@163.com 
	 * @date 2014-2-25 下午4:17:18
	 */
	public String goTaskView(){
		try {
			Map<String, Object> params = getParams();
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
			getPager().setOrderField("feedbackTime");
			getPager().setOrderBy("desc");
			}
			params.put("taskDefinition.id," + SearchCondition.EQUAL, id);
			Pager pager = taskDefineController.doSubSingleList1(params,getPager());
			logger.info("获取执行反馈列表数据");
			setPager(pager);
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskDefinition = taskDefineController.doListEntityById(id);
				//获取该任务对应的附件
				uploaderList=  taskDefinition.getUploader();
				if(taskDefinition.getExecutionFeedbacks()!=null){
					for (ExecutionFeedback executionFeedback : taskDefinition.getExecutionFeedbacks()) {
						//获取该任务对应反馈的附件
						feedbackUploaderList.addAll(executionFeedback.getUploader());
					}
				}
						
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goTaskView";
	}
		
	/** 处理删除操作 */
	public String deleteById() {
		try {
			VixTask pb = taskDefineController.findEntityById(id);
			if (null != pb) {
				taskDefineController.doDeleteByEntity(pb);
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
	
	
	public void updateIsPublish(){
		try{
			VixTask pb = taskDefineController.findEntityById(id);
			if(pb.getIsPublish()==1){
				isPublish =0;
			}else if(pb.getIsPublish()==0){
				isPublish = 1;
			}
			pb.setIsPublish(isPublish);
			taskDefinition = taskDefineController.doSaveSalesOrder(pb);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = taskDefineService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = taskDefineService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String eqSbwdPager()
	{
		//设备文档
		if(StringUtils.isNotEmpty(eqId) && !eqId.equals("0")){
			Map<String,Object> params = getParams();
			this.addTimeLimitToParam(params);
			params.put("uploader.id,"+SearchCondition.EQUAL, this.eqId);
			
			Pager pager = this.getPager();
			pager.setOrderField("id");
			pager.setOrderBy("desc");

			//在parms之后，覆盖相同项目
			this.addAdvFilterAndSort(params, pager);

			try{
				this.taskDefineService.findPagerByHqlConditions(pager, Uploader.class, params);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}
		return "eqSbwdPager";
	}
	
	
	public String eqSbwdEdit(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				uploader = taskDefineController.doUploader(id);
				logger.info("");
			}else {
				uploader=new Uploader();
				//将任务set到Uploader里边
				String taskDefineId = getRequestParameter("taskDefineId");
				taskDefinition=taskDefineService.findEntityById(VixTask.class, taskDefineId);
				uploader.setTaskDefinition(taskDefinition);
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "eqSbwdEdit";
	}
	
	public void saveEqSbwd()
	{
		String[] savePathAndName = this.saveUploadFile();
		if(savePathAndName!=null && savePathAndName.length==2)
		{
			this.uploader.setFileName(savePathAndName[1]);
			this.uploader.setFilePath(savePathAndName[0]);
		}

		this.uploader.setUploadPersonId(SecurityUtil.getCurrentUserId());
		this.uploader.setUploadPerson(SecurityUtil.getCurrentUserName());
		/**拿到当前用户的姓名，保存*/
		uploader.setUploadPersonName(SecurityUtil.getCurrentRealUser().getName());
		this.uploader.setUploadTime(new Date());
		this.saveBaseEntity(this.uploader);
		try {
			uploader=this.taskDefineService.merge(uploader);
			/*this.renderText(String.valueOf(uploader.getTaskDefinition().getId()));*/
			renderText(String.valueOf(uploader.getTaskDefinition().getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String downloadEqDocument()
	{
		if(StringUtils.isNotEmpty(id) && !id.equals("0")){
			try{
				Uploader doc = this.getTaskDefineService().findEntityById(Uploader.class, this.id);
				String fileName = doc.getFileName();
				String filePath = doc.getFilePath();
				String title = doc.getTitle();
				int idx = fileName.lastIndexOf(".");
				if(idx!=-1)
				{
					title = title + fileName.substring(idx);
				}
				
				this.setOriFileName(title);

				String downloadFile = filePath + fileName;
		        this.setInputStream(new FileInputStream(downloadFile));
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			return NONE;
		}
		return "downloadEqDocument";
	}	
	
	public String goChooseCategory(){
		return "goChooseCategory";
	}

	public List<TaskSourceType> getTaskSourceTypeList() {
		return taskSourceTypeList;
	}


	public void setTaskSourceTypeList(List<TaskSourceType> taskSourceTypeList) {
		this.taskSourceTypeList = taskSourceTypeList;
	}


	public List<TaskType> getTaskTypeList() {
		return taskTypeList;
	}


	public void setTaskTypeList(List<TaskType> taskTypeList) {
		this.taskTypeList = taskTypeList;
	}


	public List<DifficultyCoefficient> getDifficultyCoefficientList() {
		return difficultyCoefficientList;
	}


	public void setDifficultyCoefficientList(
			List<DifficultyCoefficient> difficultyCoefficientList) {
		this.difficultyCoefficientList = difficultyCoefficientList;
	}


	public List<TaskLevel> getTaskLevelList() {
		return taskLevelList;
	}


	public void setTaskLevelList(List<TaskLevel> taskLevelList) {
		this.taskLevelList = taskLevelList;
	}


	public List<CompletionMark> getCompletionMarkList() {
		return completionMarkList;
	}


	public void setCompletionMarkList(List<CompletionMark> completionMarkList) {
		this.completionMarkList = completionMarkList;
	}


	public VixTask getTaskDefinition() {
		return taskDefinition;
	}


	public void setTaskDefinition(VixTask taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<VixTask> getTaskDefinitionList() {
		return taskDefinitionList;
	}

	public void setTaskDefinitionList(List<VixTask> taskDefinitionList) {
		this.taskDefinitionList = taskDefinitionList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ExecutionFeedback getExecutionFeedback() {
		return executionFeedback;
	}

	public void setExecutionFeedback(ExecutionFeedback executionFeedback) {
		this.executionFeedback = executionFeedback;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ITaskDefineService getTaskDefineService() {
		return taskDefineService;
	}

	public void setTaskDefineService(ITaskDefineService taskDefineService) {
		this.taskDefineService = taskDefineService;
	}

	public Uploader getUploader() {
		return uploader;
	}

	public void setUploader(Uploader uploader) {
		this.uploader = uploader;
	}

	public String getEqId() {
		return eqId;
	}

	public void setEqId(String eqId) {
		this.eqId = eqId;
	}

	public Set<Uploader> getUploaderList() {
		return uploaderList;
	}

	public void setUploaderList(Set<Uploader> uploaderList) {
		this.uploaderList = uploaderList;
	}

	public Set<Uploader> getFeedbackUploaderList() {
		return feedbackUploaderList;
	}

	public void setFeedbackUploaderList(Set<Uploader> feedbackUploaderList) {
		this.feedbackUploaderList = feedbackUploaderList;
	}



}
