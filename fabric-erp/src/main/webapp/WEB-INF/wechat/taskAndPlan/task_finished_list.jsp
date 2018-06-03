<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<title>完成任务列表</title>
<script type="text/javascript">
	function goSaveOrUpdate(id) {
		window.location.href = "${vix}/wechat/weChatTaskPlanAction!goFinishedTask.action?id=" + id;
	};

	function deleteById() {
		var ids = '';
		$("span[class='cur']").each(function() {
			ids += ',' + $(this).attr("value");
		});
		$.ajax({
		url : '${vix}/wechat/weChatTaskPlanAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			window.location = "${vix}/wechat/weChatTaskPlanAction!goFinishedTaskList.action";
		}
		});
	};
	function searchByName() {
		loadContent('${vix}/wechat/weChatTaskPlanAction!goFinishedTaskList.action?searchname=' + $('#searchname').val());
	};
</script>
<style type="text/css">
#searchId {
	cursor: pointer;
}
</style>
<div id="main" role="main">
	<section>
		<div class="search">
			<p>
				<img id="searchId" src="${vix}/wechatcommon/images/search_icon.png" onclick="searchByName();" /><input type="text" placeholder="搜索" id="searchname" name="searchname">
			</p>
		</div>
		<div class="subJob">
			<s:if test="vixTaskList.size > 0">
				<s:iterator value="vixTaskList" var="entity" status="s">
					<dl>
						<dt>
							<span name="chkId" value="${entity.id}"></span> <strong><a href="#" onclick="goSaveOrUpdate('${entity.id}');"> ${entity.questName}</a><b> ${entity.taskDescription}</b> </strong>
						</dt>
						<dd>
							<span>${fn:substring(entity.createTime, 0, 10)}</span>
						</dd>
					</dl>
				</s:iterator>
			</s:if>
			<div style="clear: both; height: 60px;"></div>
			<h3>
				<a href="#" class="btn"><img src="${vix}/wechatcommon/images/subJob_detIcon.png" />删除选中任务</a>
			</h3>
		</div>
	</section>
</div>

<!-----弹框--->
<div class="dialog_bg dialog_del">
	<div class="dialog newMeet_dia">
		<p>确认删除任务吗?</p>
		<h4>
			<span><a href="#" class="btn" onclick="deleteById();">确定</a> </span><span><a href="#" class="btn">取消</a> </span>
		</h4>
	</div>
</div>
<script type="text/javascript">
	$(".subJob h3 .btn").click(function() {
		$(".dialog_del").show();
	});
	$(".newMeet_dia h4 span:last-child a").click(function() {
		$(".dialog_del").hide();
	});
</script>