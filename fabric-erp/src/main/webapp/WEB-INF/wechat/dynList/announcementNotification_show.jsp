<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery.form.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/TouchSlide.1.1.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<title>公告详情</title>
</head>
<body>
	<section class="preRel">
		<div class="preRel_list1">
			<h1>${announcementNotification.title }</h1>
			<h2>
				${fn:substring(announcementNotification.createTime, 0, 19)} <b>中盛企云</b><span>${announcementNotification.readTimes }</span>
			</h2>
			<dl>
				<dt>公告详情</dt>
				<dd>${announcementNotification.content }
					<span>中盛企云</span> <span>${fn:substring(announcementNotification.createTime, 0, 19)}</span>
				</dd>
			</dl>
		</div>
		<div class="preRel_list2" id="isPointPraiseId">
			<h2>
				<c:choose>
					<c:when test="${wxpTrendsAndEmployee.isPointPraise  eq 1}">
						<a href="#" class="cur" onclick="cancelPointPraise('${announcementNotification.id }');"></a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="pointPraise('${announcementNotification.id }');"></a>
					</c:otherwise>
				</c:choose>
				<span>已有<b id="pointpraisenumsspanid">${announcementNotification.pointPraiseNums }</b>人点赞
				</span>
			</h2>
			<dl>
				<s:if test="wxpTrendsAndEmployeeList.size > 0">
					<dt>已点赞</dt>
					<dd>
						<s:iterator value="wxpTrendsAndEmployeeList" var="entity" status="s">
				${entity.employeeName }
				</s:iterator>
					</dd>
				</s:if>
			</dl>
			<!-- <h3>
				<a href="#">分享到外部</a>
			</h3> -->
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
		<div style="height: 60px; clear: both;"></div>
		<form id="editEvaluationReviewForm" action="" method="post">
			<input type="hidden" id="announcementNotificationId" name="announcementNotification.id" value="${announcementNotification.id }" /> <input type="hidden" id="evaluationReviewId" name="evaluationReview.id" value="${evaluationReview.id }" />
			<div class="chat_bot">
				<dl>
					<dt>
						<span><a href="#" class="chat_icon"><img src="${vix}/wechatcommon/images/chat_fot_icon3.png"></a></span> <b><input type="text" class="chat_text" id="evaluationContent" name="evaluationReview.evaluationContent"></b> <strong><a href="#" onclick="saveOrUpdateEvaluationReview()">发表</a> </strong>
					</dt>
					<dd class="chat_more">
						<a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon5.png"><span>点名@人</span></a> <a href="#" class="facTuc_tag"><img src="${vix}/wechatcommon/images/chat_more_icon6.png"><span>表情</span></a> <a href="#"><img src="${vix}/wechatcommon/images/chat_more_icon3.png"><span>图片</span></a>
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
</body>
</html>
<script type="text/javascript">
	function pointPraise(id) {
		$.ajax({
		type : "post",
		url : "${vix}/wechat/weChatDynListAction!pointPraise.action?id=" + id,
		dataType : "Text",
		success : function(id) {
			$.ajax({
			url : '${vix}/wechat/weChatDynListAction!goIsPointPraiseList.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#isPointPraiseId").html(html);
			}
			});
		}
		});
	};
	function cancelPointPraise(id) {
		$.ajax({
		type : "post",
		url : "${vix}/wechat/weChatDynListAction!cancelPointPraise.action?id=" + id,
		dataType : "Text",
		success : function(id) {
			$.ajax({
			url : '${vix}/wechat/weChatDynListAction!goIsPointPraiseList.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#isPointPraiseId").html(html);
			}
			});
		}
		});
	};
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
		url : "${vix}/wechat/weChatDynListAction!saveOrUpdateEvaluationReview.action",
		dataType : "text",
		success : function(id) {
			window.location = "${vix}/wechat/weChatDynListAction!goShowAnnouncementNotification.action?id=" + id + "&rand='.$_SGLOBAL['timestamp'].";
		}
		});
	};
	//分类展开
	$(function() {
		var preRel_list2 = $('.preRel_list2');
		preRel_list2.find('dl').each(function() {
			if ($(this).find('dd').height() > 50) {
				$(this).append('<p class="actLef_click"><a href="javascript:;">展开</a></p>');
			}
			$(this).find('dd').css('height', 50);
		})

		$('.actLef_click').click(function() {
			$(this).parents('dl').siblings('dl').find('dd').css({
				height : 50
			}).end().find('.actLef_click a').text('展开');
			$(this).parents('dl').siblings('dl').find('.actLef_click').removeClass('actLef_up');
			if ($(this).hasClass('actLef_up')) {
				$(this).removeClass('actLef_up');
				$(this).find('a').text('展开');
				$(this).siblings('dd').css({
					height : 50
				})
			} else {
				$(this).addClass('actLef_up');
				$(this).find('a').text('收起');
				$(this).siblings('dd').css({
					height : 'auto'
				})
			}
		})
	});

	$('.preRel_list2 h2 a').click(function() {
		if ($(this).hasClass('cur')) {
			$(this).removeClass('cur');
		} else {
			$(this).addClass('cur');
		}

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
