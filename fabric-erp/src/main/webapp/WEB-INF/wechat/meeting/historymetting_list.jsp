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
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/iscroll.js"></script>
<script type="text/javascript">
	function goSaveOrUpdate(id) {
		window.location.href = "${vix}/wechat/conferenceAssistantAction!goShowHistoryMetting.action?id=" + id;
	};
	function deleteById() {
		var ids = '';
		$("span[class='cur']").each(function() {
			ids += ',' + $(this).attr("value");
		});
		$.ajax({
		url : '${vix}/wechat/conferenceAssistantAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/conferenceAssistantAction!goHistoryMettingList.action";
		}
		});
	};
</script>
</head>
<body>
	<section>
		<div class="search">
			<p>
				<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="搜索">
			</p>
		</div>
		<div class="subJob">
			<s:if test="applicationMgList.size > 0">
				<s:iterator value="applicationMgList" var="entity" status="s">
					<dl>
						<dt>
							<span name="chkId" value="${entity.id}"></span> <strong><a href="#" onclick="goSaveOrUpdate('${entity.id}');"> ${entity.meetingTheme}</a><b> 地点: ${entity.meetingRequest.address}</b><b> 时间: ${fn:substring(entity.meetingStartTime, 0,16)} 至 ${fn:substring(entity.meetingEndTime, 0,16)}</b> </strong>
						</dt>
						<dd>
							<span>${fn:substring(entity.createTime, 0,16)}</span>
						</dd>
					</dl>
				</s:iterator>
			</s:if>
			<div style="clear: both; height: 60px;"></div>
			<h3>
				<a href="#" class="btn"><img src="${vix}/wechatcommon/images/subJob_detIcon.png" />删除选中会议</a>
			</h3>
		</div>
	</section>
	<!-----弹框--->
	<div class="dialog_bg dialog_del">
		<div class="dialog newMeet_dia">
			<p>确认删除会议吗?</p>
			<h4>
				<span><a href="#" class="btn" onclick="deleteById();">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
			</h4>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
	$(".subJob h3 .btn").click(function() {
		$(".dialog_del").show();
	});
	$(".newMeet_dia h4 span:last-child a").click(function() {
		$(".dialog_del").hide();
	});
</script>