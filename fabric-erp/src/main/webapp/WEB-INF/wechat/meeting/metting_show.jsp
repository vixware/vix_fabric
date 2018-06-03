<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
</head>
<body>
	<section class="taskDetail newDet test">
		<div class="taskDetail_list1">
			<dl>
				<dt>
					<span><img src="${applicationMg.employee.headImgUrl}" /></span><strong>${ applicationMg.employee.name}<b>${applicationMg.createTimeTimeStr}</b></strong>
				</dt>
				<dd>
					<s:if test='check == 1'>
						<a href="#" onclick="goSaveOrUpdate('${applicationMg.id}');">修改</a>
						<a href="#" onclick="goFinished('${applicationMg.id}');">结束</a>
					</s:if>
					<a href="#" class="green" onclick="goMeetingSummary('${applicationMg.id}');">纪要</a>
				</dd>
			</dl>
			<h2>
				<span>${applicationMg.meetingTheme}</span>
				<p>${applicationMg.meetingDescription}</p>
			</h2>
			<h3>
				<span><img src="${vix}/wechatcommon/images/taskDetail_icon1.png" /> 会议地点：<b>${applicationMg.meetingRequest.meetingName}</b></span> <span><img src="${vix}/wechatcommon/images/taskDetail_icon2.png" /> 开始时间：<b>${fn:substring(applicationMg.meetingStartTime, 0,19)} </b></span> <span><img src="${vix}/wechatcommon/images/taskDetail_icon2.png" />
					结束时间：<b>${fn:substring(applicationMg.meetingEndTime, 0,19)}</b></span>
			</h3>
		</div>
		<div class="attLis">
			<s:if test="uploaderList.size > 0">
				<s:iterator value="uploaderList" var="entity" status="s">
					<dl>
						<dt>
							<span><img src="${vix}/vixntcommon/base/images/icon-08.png"></span> <strong><b>${fn:substring(entity.fileName, 0,20)}</b>${entity.filesize }</strong>
						</dt>
						<a onclick="godeletedoc('${entity.id }');"></a>
					</dl>
				</s:iterator>
			</s:if>
		</div>
		<div class="test_list1">
			<ul>
				<li><a href="#">参加(${ applicationMg.arrinliatedNum})人</a></li>
				<li><a href="#">请假(${ applicationMg.leaveNum})人</a></li>
				<li><a href="#">未反馈(${ noFeedbackNum})人</a></li>
			</ul>
		</div>
		<s:if test='isLeave == "C"'>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="goConferenceAttendance('${applicationMg.id}');">会议签到</a> </span><span class="btn resSub_click"><a href="#" onclick="" id="">请假</a> </span>
				</h2>
			</div>
		</s:if>
		<s:elseif test='isLeave == "Q"'>
			<div class="taskDetail_list1" style="padding: 2px;">
				<h2 class="btn">
					<a href="#" onclick="goCannelLeave('${applicationMg.id}');">取消请假</a>
				</h2>
			</div>
		</s:elseif>
		<s:elseif test='isLeave == "H"'>
			<div class="taskDetail_list1" style="padding: 2px;">
				<h2 class="btn">
					<a href="#" onclick="goCannelConferenceAttendance('${applicationMg.id}');">取消签到</a>
				</h2>
			</div>
		</s:elseif>
		<s:else>
			<div class="taskDetail_list2">
				<h2>
					<span class="btn"><a href="#" onclick="goArrinliated('${applicationMg.id}');">确认参加</a> </span><span class="btn resSub_click"><a href="#" onclick="" id="">请假</a> </span>
				</h2>
			</div>
		</s:else>
		<div class="newMeet_list1 newCla_list2">
			<p>发起人(${employeeNum })人</p>
			<h4>
				<s:if test="employeeList.size > 0">
					<s:iterator value="employeeList" var="entity" status="s">
						<a href="#"><img src="${entity.headImgUrl }" /></a>
					</s:iterator>
				</s:if>
			</h4>
			<p>参会人(${affiliatedEmployeeNum })人</p>
			<h4>
				<s:if test="empList.size > 0">
					<s:iterator value="empList" var="entity" status="s">
						<a href="#"><img src="${entity.headImgUrl }" /></a>
					</s:iterator>
				</s:if>
			</h4>
			<!-- <h2>
				<a href="#">添加会议纪要记录人</a>
			</h2> -->
		</div>
		<div class="newDet_list1">
			<h1>
				<span>回复（${evaluationReviewNum }）</span>
			</h1>
			<s:if test="evaluationReviewList.size > 0">
				<s:iterator value="evaluationReviewList" var="entity" status="s">
					<s:if test="entity.employee !=null">
						<dl>
							<dt>
								<a href="#"><img src="${entity.employee.headImgUrl }"></a>
							</dt>
							<dd>
								<span>${entity.employee.name }<b>${fn:substring(entity.createTime, 0, 19)}</b></span> <strong>${entity.evaluationContent }</strong>
							</dd>
						</dl>
					</s:if>
				</s:iterator>
			</s:if>
		</div>
		<div style="height: 60px; clear: both;"></div>
		<form id="editEvaluationReviewForm" action="" method="post">
			<input type="hidden" id="applicationMgId" name="applicationMg.id" value="${applicationMg.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
			<div class="chat_bot">
				<dl>
					<dt>
						<span><a href="#" class="chat_icon"><img src="${vix}/wechatcommon/images/chat_fot_icon3.png"></a></span> <b><input type="text" class="chat_text" id="evaluationContent" name="evaluationReview.evaluationContent" maxlength="100"></b> <strong><a href="#" onclick="saveOrUpdateEvaluationReview()">发表</a> </strong>
					</dt>
				</dl>
			</div>
		</form>
	</section>
</body>
<div class="dialog_bg resSub_dia">
	<div class="dialog_middle">
		<div class="dialog_con">
			<h1>请假原因</h1>
			<h3>
				<textarea placeholder="最多300个字" id="vixTaskMemo" name="vixTaskMemo"></textarea>
			</h3>
			<p>
				<span><a href="#" class="btn" onclick="goLeave('${applicationMg.id}');">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
			</p>
		</div>
	</div>
</div>
</html>
<script type="text/javascript">
	function goSaveOrUpdate(id) {
		window.location = "${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
	};
	function goMeetingSummary(id) {
		window.location = "${vix}/wechat/conferenceAssistantAction!goSaveOrUpdate.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
	};
	function goArrinliated(id) {
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!goArrinliated.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	//会议签到
	function goConferenceAttendance(id) {
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!goConferenceAttendance.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	//取消请假
	function goCannelLeave(id) {
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!goCannelLeave.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	function goCannelConferenceAttendance(id) {
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!goCannelConferenceAttendance.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	function goFinished(id) {
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!goFinished.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	function saveOrUpdateEvaluationReview() {
		var evaluationContent = $("#evaluationContent").val();

		// 验证
		if (!evaluationContent || $.trim(evaluationContent).length <= 0) {
			alert("请输入回复内容");
			return;
		}
		if (evaluationContent.length > 100) {
			alert("回复,请不要超过100个字！");
			return;
		}

		$("#editEvaluationReviewForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	$('.resSub_click').click(function() {
		$('.resSub_dia').css("display", "table");
	});

	$('.resSub_dia p span:last-child a').click(function() {
		$('.resSub_dia').hide();
	});

	function goLeave(id) {
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!goLeave.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	TouchSlide({
	slideCell : "#anoTuc_exp",
	titCell : ".hd",
	mainCell : ".bd",
	effect : "left",
	autoPlay : false,
	autoPage : '<li>&nbsp;1</li>'
	});
</script>
