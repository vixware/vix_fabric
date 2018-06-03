<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
.list_title td {
	background-color: #ececec;
	text-align: center;
}

.top_tool_btn {
	padding: 5px 0px 10px 0px;
}

.select_list_tab {
	width: 98%;
	margin-left: 6px;
}
</style>
<script type="text/javascript">
function gd_chooseTreeNode(){

	asyncbox.open({  
	    id : "gd_choose_equipment" ,  
	    modal: true,  
	    title:'选择工单设备',
	    width:800,
	    height:400,
	    url: "${vix}/eam/workOrdersAction!gdChooseEQ.action",  
	    btnsbar:$.btn.OKCANCEL,  
	    callback : function(b){
	        //$.opener("gd_choose_equipment").$('input[name="百度中的某元素名称"]:checked').val();  
	    }  
	});
	return false;
}
</script>
</head>
<div class="box">
	<div id="right">
		<div id="newtab1">
			<div class="top_tool_btn">
				<label style="margin-left: 30px;"> <input name="" type="button" onclick="javascript:return gd_chooseTreeNode();" class="btn" value="添加设备" /> &nbsp;&nbsp; <input name="" type="button" onclick="javascript:void(0);" class="btn" value="选工作包" />
				</label>
			</div>
			<table class="select_list_tab list borderlist">
				<tr class="list_title">
					<td width="5%">编号</td>
					<td width="15%">设备编码</td>
					<td width="15%">设备名称</td>
					<td width="40%" nowrap="nowrap">工作描述</td>
					<td width="10%" nowrap="nowrap">计划时间</td>
					<td width="10%" nowrap="nowrap">优先级</td>
					<td width="5%">操作</td>
				</tr>
				<!-- 
				  <tr>
				  	<td align="center">1</td>
				  	<td align="center">1A2B3C4D</td>
				  	<td align="center">设备名称</td>
				  	<td align="left">工作内容详细描述</td>
				  	<td align="center">2013-12-30</td>
				  	<td align="center">普通</td>
				  	<td align="center"><a href="#">删除</a></td>
				  </tr>
-->
			</table>
		</div>
	</div>
</div>
</body>
</html>