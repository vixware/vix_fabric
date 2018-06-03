<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="main" role="main">
	<div class="index_box">
		<section>
			<div class="index_nav">
				<dl>
					<dd>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/weChatDynListAction!goAnnouncementNotificationList.action');"><img src="${vix}/wechatcommon/images/nav_icon1.png"><b>公告</b></a>
						</span> 
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/identityVerificationAction!goList.action');"><img src="${vix}/wechatcommon/images/nav_icon2.png"><b>新闻</b></a>
						</span> 
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action');"><img src="${vix}/wechatcommon/images/nav_icon3.png"><b>会议</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/jobLogAction!goList.action');"><img src="${vix}/wechatcommon/images/nav_icon6.png"><b>工作日志</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/weChatLeaveApproveAction!goSaveOrUpdate.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>请假出差</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goFinishedTask.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>任务</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/weChatTaskPlanAction!goFinishedTaskList.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>任务列表</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/wechatTravelClaimsAction!goSaveOrUpdate.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>移动报销</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/webChatCustomerAction!goCustomerAccountList.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>移动CRM</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/webChatSalesAction!goSalesList.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>移动销售</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/webChatCustomerAction!goSaveOrUpdateCustomerAccont.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>新增客户</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/mobileFieldAction!goList.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>移动外勤</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/signPunchAction!goList.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>考勤打卡</b></a>
						</span>
						<span>
							<a href="#" onclick="loadContent('${vix}/wechat/signPunchAction!getMyAttendance.action');"><img src="${vix}/wechatcommon/images/nav_icon7.png"><b>我的考勤</b></a>
						</span>
					</dd>
				</dl>
			</div>
		</section>
	</div>
</div>

<!-- <section class="toDo">
    <ul>
        <li><a href="#"><b></b><span>新闻公告</span></a> </li>
        <li><a href="#"><b class="icon2"></b><span>超级表单</span></a> </li>
        <li><a href="#"><b class="icon3"></b><span>请假出差</span></a> </li>
        <li><a href="#"><b class="icon4"></b><span>工作日志</span></a> </li>
        <li><a href="#"><b class="icon5"></b><span>任务分派</span></a> </li>
        <li><a href="#"><b class="icon6"></b><span>会议助手</span><i>1</i></a> </li>
        <li><a href="#"><b class="icon7"></b><span>审批提示</span></a> </li>
        <li><a href="#"><b class="icon8"></b><span>拿快递</span></a> </li>
        <li><a href="#"><b class="icon9"></b><span>移动报销</span></a> </li>
        <li><a href="#"><b class="icon10"></b><span>同事社区</span></a> </li>
        <li><a href="#"><b class="icon11"></b><span>车辆管理</span></a> </li>
        <li><a href="#"></a></li>
    </ul>
</section> -->