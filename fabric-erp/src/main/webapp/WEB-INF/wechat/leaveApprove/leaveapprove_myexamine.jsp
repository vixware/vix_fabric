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
<script type="text/javascript">
	function goFinishTripRecord(id) {
		$.ajax({
		url : '${vix}/wechat/weChatLeaveApproveAction!goFinishTripRecord.action?id=' + id,
		cache : false,
		success : function(html) {
			window.location.href = "${vix}/wechat/weChatLeaveApproveAction!goMyExamineList.action";
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
					<span><img src="${tripRecord.employee.headImgUrl }" /></span><strong>${tripRecord.employee.name }<b>${fn:substring(tripRecord.createTime, 0,16)}</b></strong>
				</dt>
			</dl>
			<ul>
				<li>${tripRecord.name }</li>
				<li>类型： <c:choose>
						<c:when test="${tripRecord.vacateType eq 1}">调休</c:when>
						<c:when test="${tripRecord.vacateType eq 2}">事假</c:when>
						<c:when test="${tripRecord.vacateType eq 3}">病假</c:when>
						<c:when test="${tripRecord.vacateType eq 4}">年休假</c:when>
						<c:when test="${tripRecord.vacateType eq 5}">婚假</c:when>
						<c:when test="${tripRecord.vacateType eq 6}">产假/陪产假</c:when>
						<c:when test="${tripRecord.vacateType eq 7}">丧假</c:when>
						<c:when test="${tripRecord.vacateType eq 8}">远途出差</c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose></li>
				<li>申请时长：${tripRecord.dates }天,${tripRecord.minutes }小时</li>
				<li>开始时间：${fn:substring(tripRecord.vacateendDate, 0,16)}</li>
				<li>结束时间：${fn:substring(tripRecord.vacateStartdate, 0,16)}</li>
			</ul>
			<p>${tripRecord.vacateReason }</p>
			<h2 class="btn">
				<a href="#" onclick="goFinishTripRecord('${tripRecord.id}');">审批通过</a>
			</h2>
		</div>
		<div class="newMeet_list1 newCla_list2">
			<p>审批人(${employeeNum })</p>
			<h4>
				<s:if test="employeeList.size > 0">
					<input type="hidden" id="ids" name="ids" value="${ids}" />
					<s:iterator value="employeeList" var="entity" status="s">
						<a href="#"><img src="${entity.headImgUrl }" /></a>
					</s:iterator>
				</s:if>
			</h4>
			<p>相关人（0）</p>
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
			<input type="hidden" id="tripRecordId" name="tripRecord.id" value="${tripRecord.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
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
		<!--  表情 -->
		<!-- <div class="anoTuc_exp" id="anoTuc_exp">
			<ul class="bd">
				<li>
					<div class="anoTuc_exp_01">
						<span><a href="#"></a></span> <span><a href="#" class="exp_02"></a></span> <span><a href="#" class="exp_03"></a></span> <span><a href="#" class="exp_04"></a></span> <span><a href="#" class="exp_05"></a></span> <span><a href="#" class="exp_06"></a></span> <span><a href="#" class="exp_07"></a></span> <span><a href="#" class="exp_08"></a></span>

						<span><a href="#" class="exp_09"></a></span> <span><a href="#" class="exp_10"></a></span> <span><a href="#" class="exp_11"></a></span> <span><a href="#" class="exp_12"></a></span> <span><a href="#" class="exp_13"></a></span> <span><a href="#" class="exp_14"></a></span> <span><a href="#" class="exp_15"></a></span> <span><a href="#"
							class="exp_16"></a></span> <span><a href="#" class="exp_17"></a></span> <span><a href="#" class="exp_18"></a></span> <span><a href="#" class="exp_19"></a></span> <span><a href="#" class="exp_20"></a></span> <span><a href="#" class="exp_21"></a></span> <span><a href="#" class="exp_22"></a></span> <span><a href="#" class="exp_23"></a></span> <span
							class="close_exp"><a href="javascript:;" class="exp_24"></a></span>
					</div>
				</li>
				<li>
					<div class="anoTuc_exp_01">
						<span><a href="#"></a></span> <span><a href="#" class="exp_02"></a></span> <span><a href="#" class="exp_03"></a></span> <span><a href="#" class="exp_04"></a></span> <span><a href="#" class="exp_05"></a></span> <span><a href="#" class="exp_06"></a></span> <span><a href="#" class="exp_07"></a></span> <span><a href="#" class="exp_08"></a></span>

						<span><a href="#" class="exp_09"></a></span> <span><a href="#" class="exp_10"></a></span> <span><a href="#" class="exp_11"></a></span> <span><a href="#" class="exp_12"></a></span> <span><a href="#" class="exp_13"></a></span> <span><a href="#" class="exp_14"></a></span> <span><a href="#" class="exp_15"></a></span> <span><a href="#"
							class="exp_16"></a></span> <span><a href="#" class="exp_17"></a></span> <span><a href="#" class="exp_18"></a></span> <span><a href="#" class="exp_19"></a></span>
					</div>
				</li>
			</ul>
			<ol class="hd">
				<li></li>
				<li></li>
			</ol>
		</div> -->
	</section>
</body>
</html>
<script type="text/javascript">
	function saveOrUpdateEvaluationReview() {
		var evaluationContent = $("#evaluationContent").val();

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
		url : "${vix}/wechat/weChatLeaveApproveAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/weChatLeaveApproveAction!goExamine.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	/* TouchSlide({
	slideCell : "#anoTuc_exp",
	titCell : ".hd",
	mainCell : ".bd",
	effect : "left",
	autoPlay : false,
	autoPage : '<li>&nbsp;1</li>'
	}); */
</script>