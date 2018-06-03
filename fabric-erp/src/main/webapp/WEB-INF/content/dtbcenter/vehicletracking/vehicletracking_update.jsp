<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#">货位跟踪管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/vehicleTrackingAction!goList.action');">车辆跟踪 </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /> </a> <a
					href="#" onclick="loadContent('${vix}/dtbcenter/vehicleTrackingAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span>
			</dt>
		</dl>
	</div>
	<iframe src="${vix}/common/page/map.html" width="100%" height="650px"></iframe>
</div>