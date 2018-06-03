<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<script type="text/javascript">
	function goReStartTask(id) {
		$.ajax({
		url : '${vix}/wechat/weChatTaskPlanAction!goReStartTask.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goMyTaskList.action";
		}
		});
	};
</script>
</head>
<body>
	<section class="taskDetail newDet">
		<div class="taskDetail_list1">
			<dl>
				<dt>
					<span><img src="${vixTask.employee.headImgUrl }" /></span><strong>${vixTask.employee.name }<b>${fn:substring(vixTask.createTime, 0,19)}</b></strong>
				</dt>
			</dl>
			<ul>
				<li>${vixTask.questName }</li>
				<li>类型：${vixTask.taskType.name }</li>
				<p>${vixTask.taskDescription }</p>
				<li><span>开始时间</span>：${fn:substring(vixTask.taskStartTime, 0,19)}</li>
				<li><span>截止时间</span>：${fn:substring(vixTask.taskEndTime, 0,19)}</li>
			</ul>
			<!--- 任务进度 --->
			<div class="tasPro">
				<ul>
					<s:if test="employeeList.size > 0">
						<s:iterator value="employeeList" var="entity" status="s">
							<li><a href="#"><span>${entity.name }<b>${vixTask.taskSchedule }%</b></span><strong><b style="width: ${vixTask.taskSchedule }%;"></b></strong></a></li>
						</s:iterator>
					</s:if>
				</ul>
			</div>
			<p>
				<strong id="div1">结束时间:${fn:substring(vixTask.taskEndTime, 0,19)}</strong>
			</p>
		</div>
		<div class="taskDetail_list1">
			<h2 class="btn">
				<a href="#" onclick="goReStartTask('${vixTask.id}');">重启任务</a>
			</h2>
		</div>
		<div class="newMeet_list1 newCla_list2">
			<p>负责人(${employeeNum })</p>
			<h4>
				<s:if test="employeeList.size > 0">
					<input type="hidden" id="ids" name="ids" value="${ids}" />
					<s:iterator value="employeeList" var="entity" status="s">
						<a href="#"><img src="${entity.headImgUrl }" /></a>
					</s:iterator>
				</s:if>
			</h4>
		</div>
		<div class="newDet_list1">
			<h1>
				<span>回复（${evaluationReviewNum }）</span>
				<!-- <a href="#">只看评论</a> -->
			</h1>
			<s:if test="evaluationReviewList.size > 0">
				<s:iterator value="evaluationReviewList" var="entity" status="s">
					<dl>
						<dt>
							<a href="#"><img src="${entity.employee.headImgUrl }"></a>
						</dt>
						<dd>
							<span>${entity.employee.name }<b>${fn:substring(entity.evaluationTime, 0,19)}</b></span> <strong>${entity.evaluationContent }</strong>
						</dd>
					</dl>
				</s:iterator>
			</s:if>
			<s:else>
				<p>暂无回复</p>
			</s:else>
		</div>
		<div style="height: 70px; clear: both;"></div>
		<div class="chat_bot">
			<dl>
				<dt>
					<span><a href="#" class="chat_icon"><img src="${vix}/wechatcommon/images/chat_fot_icon3.png"></a></span> <b><input type="text" class="chat_text"></b> <strong><a href="#">发表</a> </strong>
				</dt>
				<dd class="chat_more">
					<a href="#" onclick="goaddperson();"><img src="${vix}/wechatcommon/images/chat_more_icon5.png"><span>点名@人</span></a> <a href="#" class="facTuc_tag"><img src="${vix}/wechatcommon/images/chat_more_icon6.png"><span>表情</span></a> <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon3.png"><span>图片</span></a>
				</dd>
			</dl>
		</div>
	</section>
</body>
</html>
<script type="text/javascript">
	TouchSlide({
	slideCell : "#anoTuc_exp",
	titCell : ".hd",
	mainCell : ".bd",
	effect : "left",
	autoPlay : false,
	autoPage : '<li>&nbsp;1</li>'
	});
	$(".edit").click(function() {
		$(".dialog_del").show();
	});

	$(".newMeet_dia h4 span:last-child a").click(function() {
		$(".dialog_del").hide();
	});
</script>