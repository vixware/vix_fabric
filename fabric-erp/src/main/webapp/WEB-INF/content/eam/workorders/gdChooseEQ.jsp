<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script src="${vix}/common/core/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.ztree.all-3.0.min.js" type="text/javascript"></script>
<link href="${vix}/common/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.gd_ct_base {
	height: 300px;
	width: 750px;
	overflow: hidden;
}

.gd_ct_tree_side {
	float: left;
	height: 300px;
	width: 240px;
	border-right: 1px solid #ececec;
	overflow-y: scroll;
	overflow-x: auto;
}

.gd_ct_content_side {
	float: left;
	font-size: 13px;
	margin-left: 20px;
	padding-top: 20px;
	color: #777777;
}

.gd_ct_content_side td {
	line-height: 30px;
}

.gd_ct_content_side input {
	border: 1px solid #999999;
	width: 120px;
}

.gd_ct_content_side textarea {
	border: 1px solid #999999;
}
</style>
</head>
<body>
	<div class="gd_ct_base">
		<div class="gd_ct_tree_side">
			<div id="gd_ct_tree_root" class="ztree" style="padding: 0;"></div>
		</div>
		<div class="gd_ct_content_side">
			<table>
				<tr>
					<th>设备编号：</th>
					<td><input type="text" name="eqCode" /></td>
					<th>设备名称：</th>
					<td><input type="text" name="eqName" /></td>
				</tr>
				<tr>
					<th>计划时间：</th>
					<td><input id="_qiwangEndTime" name="qiwangBeginTime" value="" readonly="readonly" type="text" /> <img onclick="showTime('_qiwangEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th>优先级：</th>
					<td><s:select name="eqType" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
				</tr>
				<tr>
					<th>工作描述：</th>
					<td colspan="3"><textarea name="description" cols="40" rows="1"></textarea></td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript"> 
<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/eam/accountManageAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					//$("#selectId").val(treeNode.id);
					//$("#selectIdTreeId").val(treeNode.tId);
					//pager("start","${vix}/eam/accountManageAction!goListContent.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#gd_ct_tree_root"), setting);
//-->
</script>
</body>
</html>