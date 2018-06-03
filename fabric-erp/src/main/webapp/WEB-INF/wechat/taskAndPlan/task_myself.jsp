<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ include file="/wechatcommon/page/wechat_css.jsp"%>
<%@ include file="/wechatcommon/page/wechat_js.jsp"%>

<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
	function goaddperson() {
		window.location.href = "${vix}/wechat/weChatTaskPlanAction!goResponsiblePersonChoose.action";
	};
	function goSaveOrUpdate(id) {
		window.location.href = "${vix}/wechat/weChatTaskPlanAction!goWechatTask.action?id=" + id;
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
			alert("请输入留言信息");
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
			$.ajax({
				url : '${vix}/wechat/weChatTaskPlanAction!goEvaluationReviewList.action?id=${vixTask.id}' ,
				cache : false,
				success : function(html) {
					$("#evaluationContent").val('');
					$("#latestOperate").html(html);
				}
				});
		}
		});
	};

	function goFinishTask() {
		$.post("${vix}/wechat/weChatTaskPlanAction!goFinishTask.action", {
		'vixTask.id' : $("#vixTaskId").val(),
		'vixTask.memo' : $("#vixTaskMemo").val()
		}, function(data) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goMyTaskList.action?id=" + data;
		});
	};
	function goReStartTask() {
		$.post("${vix}/wechat/weChatTaskPlanAction!goReStartTask.action", {
			'vixTask.id' : $("#vixTaskId").val()
		}, function(data) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goMyTask.action?id=" + data + "&rand='.$_SGLOBAL['timestamp'].";
		});
	};
	function saveExecutionFeedback() {

		var executionFeedback = $("#executionFeedback").val();
		var taskSchedule = $("#taskSchedule").val();

		if (taskSchedule < 0 || taskSchedule > 100) {
			alert("任务进度请输入0-100之间的正整数!");
			$("#taskSchedule").val('');
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
	//结束任务
	function saveTaskSchedule() {
		$.post("${vix}/wechat/weChatTaskPlanAction!saveTaskSchedule.action", {
		'vixTaskId' : $("#vixTaskId").val(),
		'vixTaskMemo' : $("#vixTaskMemo").val()
		}, function(id) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goFinishedTaskList.action";
		});
	};
</script>
<section class="taskDetail newDet">
	<div class="taskDetail_list1">
		<dl>
			<dt>
				<span><img src="${vixTask.employee.headImgUrl }" /></span><strong>${vixTask.employee.name }<b>${vixTask.createTimeTimeStr}</b></strong>
			</dt>
			<dd>
				<s:if test="check == 1">
					<s:if test="vixTask.complete in {0,1}">
						<a href="#" onclick="goSaveOrUpdate('${vixTask.id}');">修改</a>
					</s:if>
				</s:if>
			</dd>
		</dl>
		<ul>
			<li>${vixTask.questName }</li>
			<li>类型： <s:if test="vixTask.type == 1">临时任务</s:if> <s:elseif test="vixTask.type == 2">紧急任务</s:elseif> <s:elseif test="vixTask.type == 3">日清任务</s:elseif> <s:elseif test="vixTask.type == 4">周期任务</s:elseif> <s:elseif test="vixTask.type == 5">里程碑</s:elseif> <s:elseif test="vixTask.type == 6">虚拟任务</s:elseif> <s:else>其他</s:else>
			</li>
			<li><span>优先级</span>： <s:if test="vixTask.priority == 1">普通</s:if> <s:elseif test="vixTask.priority == 2">!不紧急但重要</s:elseif> <s:elseif test="vixTask.priority == 3">!!紧急但不重要</s:elseif> <s:elseif test="vixTask.priority == 4">!!!紧急且重要</s:elseif> <s:else>其他</s:else></li>
			<p>${vixTask.taskDescription }</p>
			<li><span>开始时间</span>：${vixTask.taskStartTimeTimeStr}</li>
			<li><span>截止时间</span>：${vixTask.taskEndTimeTimeStr}</li>
		</ul>
		<!--- 任务进度 --->
		<s:if test="isFeedback == 0">
			<div class="tasPro">
				<ul>
					<s:if test="employeeList.size > 0">
						<s:iterator value="employeeList" var="entity" status="s">
							<li><a href="#" onClick="alert('该任务已经过了反馈时间!');history.go(-1);"><span>${entity.name }<b>${vixTask.taskSchedule }%</b></span><strong><b style="width: ${vixTask.taskSchedule }%;"></b></strong></a></li>
						</s:iterator>
					</s:if>
				</ul>
			</div>
		</s:if>
		<s:elseif test="isFeedback == 1">
			<div class="tasPro feedback">
				<ul>
					<s:if test="employeeList.size > 0">
						<s:iterator value="employeeList" var="entity" status="s">
							<li><a href="#"><span>${entity.name }<b>${vixTask.taskSchedule }%</b></span><strong><b style="width: ${vixTask.taskSchedule }%;"></b></strong></a></li>
						</s:iterator>
					</s:if>
				</ul>
			</div>
		</s:elseif>
		<s:if test="vixTask.complete == 1"></s:if>
		<s:elseif test="vixTask.complete in {2,3}">
			<p>
				<strong id="div1">结束时间:${vixTask.taskEndTimeTimeStr}</strong>
			</p>
		</s:elseif>
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
	<s:if test="vixTask.complete in {0,1}">
		<div class="taskDetail_list2">
			<h2>
				<span class="btn resSub_click"><a href="#" onclick="">完成</a> </span><span class="btn"><a href="#" onclick="goReStartTask();">重新提交</a> </span>
			</h2>
		</div>
	</s:if>
	<s:elseif test="vixTask.complete == 2">
		<div class="taskDetail_list1" style="padding: 2px;">
			<h2 class="btn">
				<a href="#" onclick="goReStartTask('${vixTask.id}');">重启任务</a>
			</h2>
		</div>
	</s:elseif>
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
	<div class="newDet_list1" id="latestOperate">
		<h1>
			<span>回复（${evaluationReviewNum }）</span>
		</h1>
		<s:if test="evaluationReviewList.size > 0">
			<s:iterator value="evaluationReviewList" var="entity" status="s">
				<dl>
					<dt>
						<a href="#"><img src="${entity.employee.headImgUrl }"></a>
					</dt>
					<dd>
						<span>${entity.employee.name }<b>${entity.evaluationTimeTimeStr}</b></span> <strong>${entity.evaluationContent }</strong>
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
			</dl>
		</div>
	</form>
</section>
<!-----开始时间弹框-->
<div class="dialog_bg staTim_dia">
	<div class="dialog_middle">
		<div class="dialog_con">
			<h2>
				<span>新的截止时间：</span><strong><input type="text" id="newTaskEndTime" name="newTaskEndTime" value=""></strong>
			</h2>
			<div id="datePlugin"></div>
			<h3>您还有任务未完成，需要延期处理</h3>
			<p>
				<span><a href="#" class="btn">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
			</p>
		</div>
	</div>
	<div id="datePlugin"></div>
</div>
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
<!-----重新提交弹框-->
<div class="dialog_bg resSub_dia">
	<div class="dialog_middle">
		<div class="dialog_con">
			<h1>完成任务</h1>
			<h3>
				<textarea placeholder="最多300个字" id="vixTaskMemo" name="vixTaskMemo"></textarea>
			</h3>
			<p>
				<span><a href="#" class="btn" onclick="saveTaskSchedule();">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
			</p>
		</div>
	</div>
</div>
<script type="text/javascript">

	$('.edit').click(function() {
		$('.staTim_dia').css("display", "table");
	});
	$('.staTim_dia p span a').click(function() {
		$('.staTim_dia').hide();
	});
	$('.feedback').click(function() {
		$('.detPro_dia').css("display", "table");
	});
	$('.nofeedback').click(function() {
		$('.isnofeedback').css("display", "table");
	});
	$('.detPro_dia p span:last-child a').click(function() {
		$('.detPro_dia').hide();
	});
	$('.resSub_dia p span:last-child a').click(function() {
		$('.resSub_dia').hide();
	});
	$('.resSub_click').click(function() {
		$('.resSub_dia').css("display", "table");
	});
	$('.resSub_dia p span:first a').click(function() {
		$('.resSub_dia').hide();
	});

	TouchSlide({
	slideCell : "#anoTuc_exp",
	titCell : ".hd",
	mainCell : ".bd",
	effect : "left",
	autoPlay : false,
	autoPage : '<li>&nbsp;1</li>'
	});
	
</script>