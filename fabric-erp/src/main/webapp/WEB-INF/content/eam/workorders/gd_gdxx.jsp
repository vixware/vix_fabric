<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
.order .order_table input[type=text] {
	width: 130px;
}
</style>
<script type="text/javascript">
$(function(){
	addInputCheckNumEvent('costUpLimit');
	addInputCheckNumEvent('costDownLimit');
});

function saveWoGdxx(){
	$("#gdxxForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/eam/workOrdersAction!saveGdxx.action",
	     dataType: "text",
	     success: function(result){
	    	var dataId = result;
	    	$('#woId').val(dataId);
	    	$('#workOrderId').val(dataId);
	    	$('#detail_info_btn a').fadeIn('fast');
	    	showMessage('设备基本信息保存完毕');
	    	
	    	_tab_reload_opener_grid();
	     }
	 });
}
</script>
<s:form action="" id="gdxxForm" method="post" theme="simple">
	<s:hidden id="workOrderId" name="workOrder.id" theme="simple"></s:hidden>
	<div class="order">
		<dl>

			<dd>
				<div class="order_table">
					<table>
						<tr>
							<th>工单编号：</th>
							<td><s:textfield id="wocode" name="workOrder.wocode"></s:textfield><font color="red">*</font></td>
							<th>工单名称：</th>
							<td><s:textfield id="woname" name="workOrder.woname"></s:textfield><font color="red">*</font></td>
							<th>工单申请号：</th>
							<td><s:textfield id="woApplyNo" name="workOrder.woApplyNo"></s:textfield><font color="red">*</font></td>
						</tr>
						<tr>
							<th>工单类型：</th>
							<td><s:select id="wotype" name="workOrder.wotype" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
							<th>优先级：</th>
							<td><s:select id="priority" name="workOrder.priority" list="#{'0':'普通','1':'重要','2':'紧急','3':'次要'}" theme="simple"></s:select></td>
							<th>工单报告人：</th>
							<td><s:textfield id="reporter" name="workOrder.reporter"></s:textfield><font color="red">*</font></td>
						</tr>
						<tr>
							<th>期望开始时间：</th>
							<td><input id="_hopeStartTime" name="workOrder.hopeStartTime" value="<fmt:formatDate value='${workOrder.hopeStartTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_hopeStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><font
								color="red">*</font></td>
							<th>期望结束时间：</th>
							<td><input id="_hopeEndTime" name="workOrder.hopeEndTime" value="<fmt:formatDate value='${workOrder.hopeEndTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_hopeEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><font
								color="red">*</font></td>
						</tr>
						<tr>
							<th>负责人：</th>
							<td><s:textfield id="responsiblePerson" name="workOrder.responsiblePerson"></s:textfield><font color="red">*</font></td>
							<th>电话：</th>
							<td><s:textfield id="phone" name="workOrder.phone"></s:textfield></td>
							<th>维护中心：</th>
							<td><s:textfield id="defendCenter" name="workOrder.defendCenter"></s:textfield></td>
						</tr>
						<tr>
							<th>成本中心：</th>
							<td><s:textfield id="costCenter" name="workOrder.costCenter"></s:textfield></td>
							<th>成本范围上限：</th>
							<td><s:textfield id="costUpLimit" name="workOrder.costUpLimit"></s:textfield></td>
							<th>成本范围下限：</th>
							<td><s:textfield id="costDownLimit" name="workOrder.costDownLimit"></s:textfield></td>
						</tr>
						<tr>
							<th>工单描述：</th>
							<td colspan="5"><textarea id="memo" name="workOrder.memo" cols="76" rows="1"><s:property value="workOrder.memo" /></textarea></td>
						</tr>
						<tr>
							<td colspan="6">&nbsp;</td>
						</tr>
						<tr>
							<th></th>
							<td><input name="" id="" onclick="javascript:return saveWoGdxx()" type="button" class="btn" value="保存" /></td>
						</tr>
					</table>
				</div>


			</dd>
		</dl>
	</div>
</s:form>