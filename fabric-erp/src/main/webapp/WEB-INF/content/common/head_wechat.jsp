<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">微信企业号管理</a>
	<ul>
		<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/enterpriseWechatAccountAction!goList.action');">设置</a></li>
		<li><a href="#" onclick="loadContent('${vix}/wechat/identityVerificationAction!goList.action');">微信新闻管理</a></li>
		<li><a href="#" onclick="loadContent('${vix}/wechat/wechatDepartmentAction!goList.action');">微信企业号部门管理</a></li>
		<li><a href="#" onclick="loadContent('${vix}/wechat/wxpQyContactsAction!goList.action');">微信企业号成员管理</a></li>
		<li><a href="#" onclick="">知识百科</a></li>
		<li><a href="#" onclick="loadContent('${vix}/wechat/chatAction!groupChat.action');">社区群聊</a></li>
		<li class="fly"><a href="#">请假出差</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/weChatLeaveApproveAction!goSaveOrUpdate.action');">新建</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/weChatLeaveApproveAction!goExamineList.action');">我提交的</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/weChatLeaveApproveAction!goMyExamineList.action');">我审批的</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/weChatLeaveApproveAction!goList.action');">历史</a></li>
			</ul></li>
		<li class="fly"><a href="#">企业动态</a>
			<ul>
				<li class="fly"><a href="#">企业活动</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/wxpActivityAction!goSaveOrUpdate.action');">发布活动</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/wxpActivityAction!goList.action');">活动列表</a></li>
					</ul></li>
				<li class="fly"><a href="#">企业新闻</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/identityVerificationAction!goSaveOrUpdate.action');">发布新闻</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/identityVerificationAction!goList.action');">新闻列表</a></li>
					</ul></li>
				<li class="fly"><a href="#">企业公告</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/weChatDynListAction!goSaveOrUpdateAnnouncementNotification.action');">发布公告</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/weChatDynListAction!goAnnouncementNotificationList.action');">公告列表</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#">工作日志 </a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/jobLogAction!goSaveOrUpdate.action');">写日志</a></li>
				<li><a href="#" onclick="loadContent('${vix}/wechat/jobLogAction!goList.action');">日志管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/jobLogAction!goSubordinateList.action');">下属日志</a></li>
			</ul></li>
		<li class="fly"><a href="#">报销管理 </a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/wechatTravelClaimsAction!goList.action');">报销管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/wechatTravelClaimsAction!goSaveOrUpdate.action');">填写报销单</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/wechat/">移请外勤</a></li>
		<li><a href="#" onclick="loadContent('${vix}/wechat/">移动审批</a></li>
		<li class="fly"><a href="#">通讯录</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/contactListAction!getEnterContact.action');">企业通讯录</a></li>
				<li><a href="#" onclick="loadContent('${vix}/wechat/contactListAction!getPerContactList.action');">个人通讯录</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/contactListAction!getfreContact.action');">最近联系人</a></li>
			</ul></li>
		<li class="fly"><a href="#">任务与计划</a>
			<ul>
				<li class="fly"><a href="#">任务</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goWechatTask.action');">新任务</a></li>
						<li><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goMyDraftTaskList.action');">草稿</a></li>
						<li><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goMyTaskList.action');">待办任务</a></li>
						<li><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goFinishedTaskList.action');">历史任务</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goTaskList.action');">我提交的任务</a></li>
					</ul></li>
				<li class="fly"><a href="#">计划</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goNewPlan.action');">新计划</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goPlanList.action');">计划列表</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#">会议助手</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/conferenceAssistantAction!goList.action');">会议列表</a></li>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/wechat/conferenceAssistantAction!goHistoryMettingList.action');">历史列表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action');">创建会议</a></li>
			</ul></li>
		<li><a href="#" onclick="">素材管理</a></li>
		<li><a href="#" onclick="">消息管理</a></li>
		<li><a href="#" onclick="">企业支付</a></li>
		<li class="fly_end"><a href="#" onclick="">企业红包</a></li>
	</ul></li>