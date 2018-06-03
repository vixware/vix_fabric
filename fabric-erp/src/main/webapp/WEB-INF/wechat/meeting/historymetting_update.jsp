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
		url : "${vix}/wechat/conferenceAssistantAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goApplicationMg.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
</script>
</head>
<body>
	<section class="taskDetail newDet test">
		<div class="taskDetail_list1">
			<dl>
				<dt>
					<span><img src="${applicationMg.employee.headImgUrl}" /></span><strong>${ applicationMg.proposer}<b>${fn:substring(applicationMg.createTime, 0,16)}</b></strong>
				</dt>
			</dl>
			<h2>
				<span>${applicationMg.meetingTheme}</span> <strong>${applicationMg.meetingDescription}</strong>
			</h2>
			<h3>
				<span><img src="${vix}/wechatcommon/images/taskDetail_icon1.png" /> 会议地点：<b>${applicationMg.meetingRequest.meetingName}</b></span> <span><img src="${vix}/wechatcommon/images/taskDetail_icon2.png" /> 开始时间：<b>${fn:substring(applicationMg.meetingStartTime, 0,16)} </b></span> <span><img src="${vix}/wechatcommon/images/taskDetail_icon2.png" />
					结束时间：<b>${fn:substring(applicationMg.meetingEndTime, 0,16)}</b></span>
			</h3>
		</div>
		<div class="test_list1">
			<h2>
				<img src="${vix}/wechatcommon/images/test_twoDim.jpg">
			</h2>
			<ul>
				<li><a href="#">参加(${ applicationMg.arrinliatedNum})人</a></li>
				<li><a href="#">请假(${ applicationMg.leaveNum})人</a></li>
				<li><a href="#">未反馈(0)人</a></li>
			</ul>
		</div>
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
		</div>
		<div class="newDet_list1">
			<h1>
				<span>回复（${evaluationReviewNum }）</span><!-- <a href="#">只看评论</a> -->
			</h1>
			<s:if test="evaluationReviewList.size > 0">
				<s:iterator value="evaluationReviewList" var="entity" status="s">
					<c:choose>
						<c:when test="${not empty entity.employee }">
							<dl>
								<dt>
									<a href="#"><img src="${entity.employee.headImgUrl }"></a>
								</dt>
								<dd>
									<span>${entity.employee.name }<b>${fn:substring(entity.createTime, 0, 16)}</b></span> <strong>${entity.evaluationContent }</strong>
								</dd>
							</dl>
						</c:when>
					</c:choose>
				</s:iterator>
			</s:if>
		</div>
		<div style="height: 60px; clear: both;"></div>
		<form id="editEvaluationReviewForm" action="" method="post">
			<input type="hidden" id="applicationMgId" name="applicationMg.id" value="${applicationMg.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
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
		<!--  表情  end-->
	</section>
	<!-----操作导航---->
	<div class="leftSlider">
		<ul>
			<li><a href="#"><img src="${vix}/wechatcommon/images/slider_icon1.png"></a></li>
			<li><a href="#"><img src="${vix}/wechatcommon/images/slider_icon2.png"></a></li>
			<li><a href="#"><img src="${vix}/wechatcommon/images/slider_icon3.png"></a></li>
		</ul>
	</div>
</body>
</html>
<!-- <script type="text/javascript">
	TouchSlide({
	slideCell : "#anoTuc_exp",
	titCell : ".hd",
	mainCell : ".bd",
	effect : "left",
	autoPlay : false,
	autoPage : '<li>&nbsp;1</li>'
	});
</script> -->