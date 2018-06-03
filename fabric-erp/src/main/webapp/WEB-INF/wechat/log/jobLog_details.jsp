<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>


<link rel="stylesheet" href="//cdn.bootcss.com/weui/1.1.0/style/weui.min.css">
<link rel="stylesheet" href="//cdn.bootcss.com/jquery-weui/1.0.0/css/jquery-weui.min.css">

</head>
<body ontouchstart>
	<div class="page__bd">
		<div class="weui-panel weui-panel_access">
			<div class="weui-panel__bd">
				<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
					<div class="weui-media-box__hd">
						<img class="weui-media-box__thumb" src="${workLog.employee.headImgUrl }">
					</div>
					<div class="weui-media-box__bd">
						<h4 class="weui-media-box__title">${workLog.employee.name }</h4>
						<p class="weui-media-box__desc">${workLog.createTimeTimeStr}</p>
					</div>
				</a>
				<s:if test="check == 1">
					<div style="position: absolute; right: 15; top: 15;">
						<i><a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" onclick="goSaveOrUpdate('${workLog.id}');">修改</a></i>
					</div>
				</s:if>
			</div>
		</div>
		<div class="weui-form-preview">
			<div class="weui-form-preview__bd">
				<div class="weui-form-preview__item">
					<label class="weui-form-preview__label">类型：</label> <span class="weui-form-preview__value"><s:if test="workLog.logType == 0">工作日志</s:if> <s:elseif test="workLog.logType == 1">个人日志</s:elseif> </span>
				</div>
			</div>
		</div>
	</div>
	<article class="weui-article">
		<h3>${workLog.title }</h3>
		<section>
			<section>
				<p style="font-size: 13px;">${workLog.logContent }</p>
			</section>
		</section>
	</article>
	<div class="page__bd">
		<div class="weui-panel">
			<div class="weui-panel__hd">附件（${docNum }）</div>
			<div class="weui-panel__bd">
				<div class="weui-media-box weui-media-box_small-appmsg">
					<div class="weui-cells">
						<s:if test="uploaderList.size > 0">
							<s:iterator value="uploaderList" var="entity" status="s">
								<a class="weui-cell weui-cell_access" href="javascript:;">
									<div class="weui-cell__hd">
										<img src="${vix}/vixntcommon/base/images/icon-08.png" alt="" style="width: 20px; margin-right: 5px; display: block; width: 35px; height: 35px;">
									</div>
									<div class="weui-cell__bd weui-cell_primary">
										<p style="font-size: 13px;">${fn:substring(entity.fileName, 0,16)}</p>
									</div> <span class="weui-cell__ft" style="font-size: 13px;">${entity.filesize }</span>
								</a>
							</s:iterator>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="weui-cells__title">上级领导(${employeeNum })</div>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<div class="weui-uploader">
					<div class="weui-uploader__bd">
						<ul class="weui-uploader__files" id="uploaderFiles">
							<s:if test="employeeList.size > 0">
								<input type="hidden" id="ids" name="ids" value="${ids}" />
								<s:iterator value="employeeList" var="entity" status="s">
									<li class="weui-uploader__file" style="background-image: url(${entity.headImgUrl });width: 50px; height: 50px;"></li>
								</s:iterator>
							</s:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="page__bd" id="latestOperate">
		<div class="weui-panel weui-panel_access">
			<s:if test="evaluationReviewList.size > 0">
				<div class="weui-panel__hd">回复（${evaluationReviewNum }）</div>
				<div class="weui-panel__bd">
					<s:iterator value="evaluationReviewList" var="entity" status="s">
						<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
							<div class="weui-media-box__hd">
								<img class="weui-media-box__thumb" src="${entity.employee.headImgUrl }" alt="" style="width: 50px; height: 50px;">
							</div>
							<div class="weui-media-box__bd">
								<h4 class="weui-media-box__title">${entity.employee.name }</h4>
								<p class="weui-media-box__desc">${entity.evaluationContent }</p>
							</div>
							<div class="weui-media-box__ft">${entity.evaluationTimeTimeStr}</div>
						</a>
					</s:iterator>
				</div>
			</s:if>
			<s:else>
				<div class="weui-panel__hd">暂无回复</div>
			</s:else>
		</div>
	</div>
	<section class="newMeet newCla new">
		<div style="height: 70px; clear: both;"></div>
		<form id="editEvaluationReviewForm" action="" method="post">
			<input type="hidden" id="workLogId" name="workLog.id" value="${workLog.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
			<div class="chat_bot">
				<dl>
					<dt>
						<span><a href="#" class="chat_icon"><img src="${vix}/wechatcommon/images/chat_fot_icon3.png"></a></span> <b><input type="text" class="chat_text" id="evaluationContent" name="evaluationReview.evaluationContent"></b> <strong><a href="#" onclick="saveOrUpdateEvaluationReview()">发表</a> </strong>
					</dt>
				</dl>
			</div>
		</form>
	</section>
	<!-- body 最后 -->
	<script src="//cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/jquery-weui/1.0.0/js/jquery-weui.min.js"></script>

	<!-- 如果使用了某些拓展插件还需要额外的JS -->
	<script src="//cdn.bootcss.com/jquery-weui/1.0.0/js/swiper.min.js"></script>
	<script src="//cdn.bootcss.com/jquery-weui/1.0.0/js/city-picker.min.js"></script>
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
		url : "${vix}/wechat/jobLogAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			$.ajax({
			url : '${vix}/wechat/jobLogAction!goEvaluationReviewList.action?id=${workLog.id}',
			cache : false,
			success : function(html) {
				$("#evaluationReviewId").val('');
				$("#latestOperate").html(html);
			}
			});
		}
		});
	};

	function goSaveOrUpdate(id) {
		window.location.href = "${vix}/wechat/jobLogAction!goSaveOrUpdate.action?id=" + id;
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