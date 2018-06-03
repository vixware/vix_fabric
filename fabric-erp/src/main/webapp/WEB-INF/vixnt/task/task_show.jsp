<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<header>
	<h2>任务主题:&nbsp;&nbsp;${vixTask.questName }</h2>
</header>
<div>
	<div class="widget-body">
		<div class="input-group">
			<span class="input-group-addon">
				<i class="icon-prepend fa fa-user" id="alertname"></i>
			</span> 
			<input type="text" class="form-control" name="fname" placeholder="">
		</div>
		<br>
		<div class="well well-sm" role="alert" style="height: 250px;">
			<p>${vixTask.taskDescription }</p>
		</div>
		<div class="input-group">
			<span class="input-group-addon">
				<i class="icon-prepend fa fa-plus" id="alertvalue"></i>
			</span> 
			<input type="text" class="form-control" name="fname" placeholder="">
		</div>
		<br>
		<div>
			<a class="btn btn-default" href="javascript:void(0);" onclick="deleteById('${vixTask.id }')">删 除</a> 
			<a class="btn btn-default" href="javascript:void(0);" onclick="updateFinishTask('${vixTask.id }')">完 成</a>
			<!-- <a class="btn btn-default" href="javascript:void(0);" id="toauther">转TA人</a> -->
		</div>
		<br>
	</div>
</div>