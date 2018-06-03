<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公中心</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/administrativeAction!goList.action');">完结任务</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<iframe id="businessFormTemplateIframe" src="${flow_ip}/activiti/task/done_list" style="width: 100%; height: 550px; border-style: none; margin: 0px; padding: 0px;"> </iframe>
</div>