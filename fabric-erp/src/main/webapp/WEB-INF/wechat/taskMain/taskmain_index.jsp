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
<title>任务管理</title>
</head>
<section class="toDo">
	<ul>
		<li><a href="#"><b></b><span>待办任务</span></a></li>
		<li><a href="#"><b class="icon2"></b><span>下属任务</span></a></li>
		<li><a href="#"><b class="icon3"></b><span>已完成任务</span></a></li>
		<li><a href="${vix}/wechat/weChatTaskPlanAction!goWechatTask.action" ><b class="icon4"></b><span>新建任务</span></a></li>
		<li><a href="#"><b class="icon5"></b><span>草稿</span></a></li>
		<li><a href="#"><b class="icon6"></b><span>下达的任务</span><i>1</i></a></li>
	</ul>
</section>