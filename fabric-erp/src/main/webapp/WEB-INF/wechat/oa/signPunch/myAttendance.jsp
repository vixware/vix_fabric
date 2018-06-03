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
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
</head>
<body>
	<section class="taskDetail newDet">
		<div class="taskDetail_list1">
			<dl>
				<dt>
					<span><img src="${headImg }" /></span><strong>${wxId }<b>${nowTimes}</b></strong>
				</dt>
				<dd>
					<s:if test="check == 1">
						<a href="#" onclick="goSaveOrUpdate('${workLog.id}');">继续考勤</a>
					</s:if>
				</dd>
			</dl>
			<ul>
				<li>考勤规则:${wxCode}
				</li>
				<p>
					<strong>工作${times }小时</strong>
				</p>
			</ul>
		</div>
		<s:if test="doAttendanceList.size == 0">
			<div id="doAttendance" class="proDet">
				<h2>
					<span>签到:</span>
					<strong>暂无签到数据</strong>
				</h2>
			</div>
		</s:if>
		<s:elseif test="doAttendanceList.size > 0">
			<div id="doAttendance" class="proDet proDet_b">
				<s:iterator value="doAttendanceList">
					<h2>
						<span>签到:<s:property value="attAddress"/><br/><br/>签到时间: ${startTimes }</span>
						<strong>
							<b>
								<s:if test="IsAttendance == 1">签到</s:if>
								<s:if test="IsAttendance == 3">迟到</s:if>
							</b>
						</strong>
					</h2>
				</s:iterator>
			</div>
		</s:elseif>
		<s:if test="doAttendanceOutList.size == 0">
			<div id="doAttendanceOut" class="proDet">
				<h2>
					<span>签退:</span>
		            <strong>暂无签退数据</strong>
				</h2>
			</div>
		</s:if>
		<s:elseif test="doAttendanceOutList.size > 0">
			<div id="doAttendanceOut" class="proDet proDet_b">
				<s:iterator value="doAttendanceOutList">
					<h2>
						<span>签退:<s:property value="attAddress"/><br/><br/>签退时间: ${endTimes }</span>
						<strong>
							<b onclick="doAttendanceOut();">
								<s:if test="IsAttendance == 2">签退</s:if>
								<s:if test="IsAttendance == 4">早退</s:if>
							</b>
						</strong>
					</h2>
				</s:iterator>
			</div>
		</s:elseif>
	</section>
	<section class="newMeet newCla new">
		<div class="newMeet_list1 newCla_list2">
			<p>上级领导(${employeeNum })</p>
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
		<form id="editEvaluationReviewForm" action="" method="post">
			<input type="hidden" id="workLogId" name="workLog.id" value="${workLog.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
			<div class="chat_bot">
				<dl>
					<dt>
						<span><a href="#" class="chat_icon"><img src="${vix}/wechatcommon/images/chat_fot_icon3.png"></a></span> <b><input type="text" class="chat_text" id="evaluationContent" name="evaluationReview.evaluationContent"></b> <strong><a href="#" onclick="saveOrUpdateEvaluationReview()">发表</a> </strong>
					</dt>
					<dd class="chat_more">
						<a href="#" onclick="goaddperson();"><img src="${vix}/wechatcommon/images/chat_more_icon5.png"><span>点名@人</span></a> <a href="#" class="facTuc_tag"><img src="${vix}/wechatcommon/images/chat_more_icon6.png"><span>表情</span></a> <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon3.png"><span>图片</span></a>
					</dd>
				</dl>
			</div>
		</form>
	</section>
</body>
</html>
<script type="text/javascript">
	function saveOrUpdateEvaluationReview() {
		alert("功能暂未开放，请联系管理员！");
		/* var evaluationContent = $("#evaluationContent").val();

		// 验证
		if (!evaluationContent || $.trim(evaluationContent).length <= 0) {
			alert("请输入任务标题");
			return;
		}
		if (evaluationContent.length > 140) {
			alert("留言,请不要超过140个字！");
			return;
		}

		$("#editEvaluationReviewForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/jobLogAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			window.location.href = "${vix}/wechat/jobLogAction!goShowDetails.action?id=" + id;
		}
		}); */
	};

	function goSaveOrUpdate(id) {
		alert("功能暂未开放，请联系管理员！");
		//window.location.href = "${vix}/wechat/jobLogAction!goSaveOrUpdate.action?id=" + id;
	};

	function deletepic(wxpQyPictureId) {
		$.ajax({
		type : "post",
		url : "${vix}/wechat/jobLogAction!deletePictureById.action?wxpQyPictureId=" + wxpQyPictureId,
		dataType : "Text",
		success : function(id) {
			window.location = '${vix}/wechat/jobLogAction!goShowDetails.action?id=' + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
</script>