<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	function goaddperson() {
		window.location.href = "${vix}/wechat/weChatTaskPlanAction!goResponsiblePersonChoose.action";
	};
	function goSaveOrUpdate(id) {
		window.location = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + id;
	};

	$(function() {
		$('.resSub_dia h2 strong b').click(function() {
			if ($(this).hasClass('cur')) {
				$(this).removeClass('cur');
			} else {
				$(this).addClass('cur');
			}
		});
	});

	function saveOrUpdateEvaluationReview() {
		var evaluationContent = $("#evaluationContent").val();

		// 验证
		if (!evaluationContent || $.trim(evaluationContent).length <= 0) {
			alert("请输入回复标题");
			return;
		}
		if (evaluationContent.length > 140) {
			alert("留言,请不要超过140个字！");
			return;
		}

		$("#editEvaluationReviewForm").ajaxSubmit({
		type : "post",
		url : "${vix}/wechat/weChatTaskPlanAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goTaskDetail.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};

	function goReStartTask() {
		$.post("${vix}/wechat/weChatTaskPlanAction!goReStartTask.action", {
			'vixTask.id' : $("#vixTaskId").val()
		}, function(data) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + data + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};
	function saveExecutionFeedback() {

		var executionFeedback = $("#executionFeedback").val();
		var taskSchedule = $("#taskSchedule").val();

		if (taskSchedule < 0 || taskSchedule > 100) {
			alert("请输入0-100之间的整数!");
			return;
		}
		if (!executionFeedback || $.trim(executionFeedback).length <= 0) {
			alert("请输入反馈内容!");
			return;
		}
		if ($.trim(executionFeedback).length > 300) {
			alert("输入的反馈内容不能超过300字!");
			return;
		}

		$.post("${vix}/wechat/weChatTaskPlanAction!saveOrUpdateExecutionFeedback.action", {
		'vixTaskId' : $("#vixTaskId").val(),
		'taskSchedule' : $("#taskSchedule").val(),
		'executionFeedback.id' : $("#executionFeedbackId").val(),
		'executionFeedback.executionFeedback' : $("#executionFeedback").val()
		}, function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goTaskDetail.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};

	function saveTaskSchedule() {
		$.post("${vix}/wechat/weChatTaskPlanAction!closeTask.action", {
		'vixTaskId' : $("#vixTaskId").val(),
		}, function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goFinishedTaskList.action";
		});
	};
	function godeletedoc(uploaderId) {
		$.post("${vix}/wechat/weChatTaskPlanAction!deleteUploaderById.action?uploaderId=" + uploaderId, {}, function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goTaskDetail.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};
	function cancel() {
		$("#ti_shi_su").hide();
	}
</script>
</head>
<body>
	<section class="taskDetail newDet">
		<div class="taskDetail_list1">
			<dl>
				<dt>
					<span><img src="${vixTask.employee.headImgUrl }" /></span><strong>${vixTask.employee.name }<b>${fn:substring(vixTask.createTime, 0,19)}</b></strong>
				</dt>
				<dd>
					<s:if test="check == 1">
						<a href="#" onclick="goSaveOrUpdate('${vixTask.id}');">修改</a>
					</s:if>
				</dd>
			</dl>
			<ul>
				<li>${vixTask.questName }</li>
				<li>类型： <s:if test="vixTask.type == 1">临时任务</s:if> <s:elseif test="vixTask.type == 2">紧急任务</s:elseif> <s:elseif test="vixTask.type == 3">日清任务</s:elseif> <s:elseif test="vixTask.type == 4">周期任务</s:elseif> <s:elseif test="vixTask.type == 5">里程碑</s:elseif> <s:elseif test="vixTask.type == 6">虚拟任务</s:elseif> <s:else>其他</s:else>
				</li>
				<li><span>优先级</span>： <s:if test="vixTask.priority == 1">普通</s:if> <s:elseif test="vixTask.priority == 2">!不紧急但重要</s:elseif>
					<s:elseif test="vixTask.priority == 3">!!紧急但不重要</s:elseif>
					<s:elseif test="vixTask.priority == 4">!!!紧急且重要</s:elseif>
					<s:else>其他</s:else></li>
				<p>${vixTask.taskDescription }</p>
				<li><span>开始时间</span>：${fn:substring(vixTask.taskStartTime, 0,19)}</li>
				<li><span>截止时间</span>：${fn:substring(vixTask.taskEndTime, 0,19)}<!-- <a href="#" class="edit"></a> --></li>
			</ul>
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
			<c:choose>
				<c:when test="${complete eq 1}"></c:when>
				<c:when test="${complete eq 2}">
					<p>
						<strong id="div1">结束时间:${fn:substring(vixTask.taskEndTime, 0,19)}</strong>
					</p>
				</c:when>
			</c:choose>
		</div>
		<div class="taskDetail_list2">
			<h2>
				<span class="btn resSub_click"><a href="#" onclick="">关闭</a> </span><span class="btn"><a href="#" onclick="goReStartTask();">重新提交</a> </span>
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
		<form id="editEvaluationReviewForm" action="" method="post">
			<input type="hidden" id="vixTaskId" name="vixTask.id" value="${vixTask.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
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
	<!-----开始时间弹框-->
	<%-- <div class="dialog_bg staTim_dia">
		<div class="dialog_middle">
			<div class="dialog_con">
				<h2>
					<span>新的截止时间：</span><strong><input type="text" id="newTaskEndTime" name="newTaskEndTime" value=""></strong>
				</h2>
				<h3>您还有任务未完成，需要延期处理</h3>
				<p>
					<span><a href="#" class="btn">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
				</p>
			</div>
		</div>
		<div id="datePlugin"></div>
	</div> --%>
	<!-----任务反馈-->
	<div class="dialog_bg detPro_dia">
		<div class="dialog_middle">
			<div class="dialog_con">
				<input type="hidden" id="executionFeedbackId" name="executionFeedbackId" value="${executionFeedback.id }" />
				<h1>${vixTask.questName }</h1>
				<h2>
					<span>任务进度：</span><strong><input type="text" id="taskSchedule" name="taskSchedule" value="${vixTask.taskSchedule }"></strong>
				</h2>
				<h3>
					<textarea placeholder="最多300个字" id="executionFeedback" name="executionFeedback">${executionFeedback.executionFeedback }</textarea>
				</h3>
				<p>
					<span><a href="#" class="btn" onclick="saveExecutionFeedback();">保存</a> </span><span><a href="#" class="btn">取消</a> </span>
				</p>
			</div>
		</div>
	</div>
	<!-----结束任务-->
	<div class="dialog_bg resSub_dia">
		<div class="dialog_middle">
			<div class="dialog_con">
				<h1>任务关闭原因</h1>
				<h3>
					<textarea placeholder="最多300个字" id="vixTaskMemo" name="vixTaskMemo"></textarea>
				</h3>
				<p>
					<span><a href="#" class="btn" onclick="saveTaskSchedule();">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$('.edit').click(function() {
		$('.staTim_dia').css("display", "table");
	})
	$('.staTim_dia p span a').click(function() {
		$('.staTim_dia').hide();
	})

	$('.tasPro').click(function() {
		$('.detPro_dia').css("display", "table");
	})
	$('.detPro_dia p span:last-child a').click(function() {
		$('.detPro_dia').hide();
	})
	$('.resSub_dia p span:last-child a').click(function() {
		$('.resSub_dia').hide();
	})

	$('.resSub_click').click(function() {
		$('.resSub_dia').css("display", "table");
	})
	$('.resSub_dia p span:first a').click(function() {
		$('.resSub_dia').hide();
	})

	TouchSlide({
	slideCell : "#anoTuc_exp",
	titCell : ".hd",
	mainCell : ".bd",
	effect : "left",
	autoPlay : false,
	autoPage : '<li>&nbsp;1</li>'
	});

	
</script>