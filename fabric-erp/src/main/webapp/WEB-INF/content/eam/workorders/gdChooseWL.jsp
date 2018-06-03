<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script src="${vix}/common/core/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript">

$(function(){
	$('#brand').datagrid({
		width:760,
		height:275,
		nowrap: true,
		autoRowHeight: false,
		striped: true,
		collapsible:true,
		url:'${vix}/common/json_tests/datagrid_data.json',
		sortName: 'code',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'code',
		columns:[
		[
			{field:'ck',checkbox:true},
			{field:'code',title:'${vv:varView("vix_mdm_item")}编号',width:180,sortable:false},
			{field:'name',title:'${vv:varView("vix_mdm_item")}名称',width:180,sortable:false},
			{field:'kucun',title:'库存',width:100},
			{field:'note',title:'备注',width:180,sortable:false}
		]],
		pagination:false,
		rownumbers:false,
	});
	var p = $('#brand').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
});
</script>
<style type="text/css">
.gd_ry_list {
	display: table;
	float: left;
	background-color: #fff;
	font-size: 13px;
}

.gd_ry_search {
	border: 1px solid #777777;
	width: 180px;
	margin: 5px 0px 5px 0px;
}

.gd_ry_content {
	display: table;
	clear: both;
	font-size: 13px;
	padding-left: 10px;
	padding-top: 10px;
	color: #777777;
}

.gd_ry_content td {
	line-height: 30px;
}

.gd_ry_content .gd_ry_gs {
	border: 1px solid #999999;
	width: 180px;
}

.gd_ry_content .gd_ry_rwms {
	border: 1px solid #999999;
	height: 30px;
	width: 400px;
}
</style>
</head>
<body>
	<div class="gd_ry_list">
		&nbsp; 检索商品： <input type="text" class="gd_ry_search" placeholder="名称 或 ${vv:varView(" vix_mdm_item")}编号" />
		<table id="brand" style="overflow-x: auto; width: 100%; height: 300px;"></table>
	</div>
</body>
</html>