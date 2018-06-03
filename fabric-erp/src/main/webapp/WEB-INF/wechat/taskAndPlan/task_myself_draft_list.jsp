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
<title>草稿</title>
<script type="text/javascript">
	function goSaveOrUpdate(id) {
		window.location = "${vix}/wechat/weChatTaskPlanAction!goMyDraftTask.action?id=" + id;
	};
</script>
<div id="main" role="main">
	<section>
		<div class="search">
			<p>
				<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="搜索">
			</p>
		</div>
		<div class="subJob">
			<s:if test="vixTaskList.size > 0">
				<s:iterator value="vixTaskList" var="entity" status="s">
					<dl>
						<dt>
							<span></span> <strong><a href="#" onclick="goSaveOrUpdate('${entity.id}');"> ${entity.questName}</a><b> ${entity.taskDescription}</b> </strong>
						</dt>
						<dd>
							<span>${fn:substring(entity.createTime, 0, 19)}</span>
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