package com.vix.core.flow.activiti.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.vix.common.job.entity.JobTodo;
import com.vix.common.job.wraper.ConditionPath;
import com.vix.core.web.Pager;

/**
 *  人员配置方式：#{aUtil.assignUser(execution)}:会签审批， #{aUtil.getSignUser(execution)}:普通审批
 * @author Administrator
 *
 */
public interface IStandardActivitiService {

	/**
	 * 发起并提交流程
	 * @param userId  用户id <br>
	 * @param deploymentId	部署流程id<br>
	 * @param requestMap	流程请求表单数据<br>
	 * @param saveFormData	是否保存表单数据,true:会调用e6soft-form相应接口存储表单数据，false:不保存<br>
	 * @return
	 */
	public String startSubmit(String userId,String deploymentId,Map<String,Object> requestMap);

	/**
	 * 判断是否是历史任务 <br>
	 * @param taskId  任务id
	 * @return
	 */
	public boolean isHistory(String taskId);
	
	/**
	 * 提交流程 <br>
	 * @param userId  	用户id <br>
	 * @param taskId	任务id <br>
	 * @param requestMap 请求表单数据map<br>
	 * @param saveFormData 是否保存表单数据 ,true调用e6soft-form接口保存表单相应数据，false:不保存
	 */
	public void submitTask(String userId,String taskId,Map<String, String> requestMap, boolean saveFormData);
	
	/**
	 * 回退流程<br>
	 * @param userId  用户id <br>
	 * @param taskId	任务id <br>
	 * @param requestMap	 请求表单数据map<br>
	 * @param saveFormData	是否保存表单数据 ,true调用e6soft-form接口保存表单相应数据，false:不保存
	 */
	public void rejectTask(String userId,String taskId,Map<String,String> requestMap,boolean saveFormData);
	
	
	/**
	 * 收回流程【撤销流程】	<br>
	 * @param userId	用户id<br>
	 * @param taskId	任务id<br>
	 */
	public void callBackTask(String userId,String taskId);
	
	/**
	 * 获取代办列表	<br>
	 * @param userId	用户id<br>
	 * @param pager	分页对象<br>
	 * @param startDate	开始时间<br>
	 * @param endDate	结束时间<br>
	 */
	public Pager getAgencyTaskList(String userId,Pager pager,Date startDate,Date endDate);
	
	/**
	 * 获取当前节点的所有出口，如果出口为exclusiveGateway,则取exclusiveGateway的所有出口
	 * @param taskId
	 * @return
	 */
	public List<ConditionPath> getConditionPathList(String taskId);
	
	
	public JobTodo findJobTodoByTaskId(String taskId);
	
	/**
	 * 根据分类code获取流程定义Id
	 * @param code
	 * @return
	 */
	public String findProcessDefinitionIdByCode(String code);
	
	/**
	 * 根据processInstanceId获取流程上下文变量
	 * @param processInstanceId
	 * @return
	 */
	public Map<String,Object> findProcessMapByProcessInstanceId(String processInstanceId);
}
