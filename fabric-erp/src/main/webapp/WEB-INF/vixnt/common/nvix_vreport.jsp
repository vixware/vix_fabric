<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a href="#" id="vreport_flow" onclick="loadContent('vreport_flow','${nvix}/vixntcommon/flow/flowlist.jsp');"><i class="fa fa-lg fa-fw fa-retweet"></i><span class="menu-item-parent">流程管理</span></a></li>
<li><a href="#"><i class="fa fa-lg fa-fw fa-file-text-o"></i><span class="menu-item-parent">表单管理</span></a>
	<ul>
		<li><a href="#">基础设置</a>
			<ul>
				<li><a href="#" id="vreport_metadata" onclick="loadContent('vreport_metadata','${nvix}/vixntcommon/form/metadata.jsp');">字典维护</a></li>
				<li><a href="#" id="vreport_datasource" onclick="loadContent('vreport_datasource','${nvix}/vixntcommon/form/datasource.jsp');">数据源维护</a></li>
				<li><a href="#" id="vreport_baseform" onclick="loadContent('vreport_baseform','${nvix}/vixntcommon/form/baseform.jsp');">基础表维护</a></li>
				<li><a href="#" id="vreport_businessform" onclick="loadContent('vreport_businessform','${nvix}/vixntcommon/form/businessform.jsp');">表单维护</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixFormBindingAction!goList.action');">表单模板绑定</a></li>
				<%-- <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixFormBindingAction!goEmp.action');">表单绑定人员</a></li> --%>
			</ul></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixAdministrativeAction!goList.action');">行政办公中心</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/vixntcommon/form/businessFormdata_list.jsp');">我的表单</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixFormAction!goList.action');">我的审批</a></li>
		<%-- <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vreport/nvixJobTodoAction!goList.action');">普通表单审批</a></li> --%>
	</ul></li>