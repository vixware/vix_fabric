<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">打印</a> <a href="#"><img alt="" src="img/icon_15.gif">帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/cmn_setting.png" alt="" /> 系统管理</a></li>
				<li><a href="#">流程管理</a></li>
				<a href="#" onclick="loadContent('${vix}/flow/flowbase.jsp')">流程列表</a>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<iframe src="${flow_ip}/activiti/flowmodel/index" style="width: 100%; height: 550px; border-style: none; margin: 0px; padding: 0px;"> </iframe>
</div>