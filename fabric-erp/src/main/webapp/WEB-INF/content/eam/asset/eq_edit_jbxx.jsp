<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style type="text/css">
.order .order_table input[type=text] {
	width: 130px;
}
</style>
<script type="text/javascript">
$(function(){
	if($('#equipmentId').val()==''){
		$('#parentEquipment').val($('#selectEqCode').val());
		$('#parentEqname').val($('#selectName').val());
	}
	if($('#selectId').val()>0){
		$('#parentId').val($('#selectId').val());
	}

	addInputCheckNumEvent('upLimit');
	addInputCheckNumEvent('downLimit');
	addInputCheckNumEvent('weight');
	addInputCheckNumEvent('caigouJiage');
});

function saveEquipmentJbxx(){
	$("#jbxxForm").ajaxSubmit({
	     type: "post",
	     url: "${vix}/eam/accountManageAction!saveJbxx.action",
	     dataType: "text",
	     success: function(result){
	    	if(result != null){
		    	var dataId = result;
		    	$('#eqId').val(dataId);
		    	$('#equipmentId').val(dataId);
		    	$('#detail_info_btn a').fadeIn('fast');
		    	showMessage('设备基本信息保存完毕');
		    	
		    	_tab_reload_opener_grid();
	    	}else{
	    		showErrorMessage('信息保存失败');
	    	}
	     }
	 });
}

</script>
</head>
<body>
	<s:form id="jbxxForm" name="jbxxForm" action="" method="post" theme="simple">
		<input type="hidden" name="parentId" id="parentId" />
		<s:hidden id="equipmentId" name="equipment.id" theme="simple"></s:hidden>
		<div class="order_table">
			<table>
				<tr>
					<th>父设备编码：</th>
					<td><s:textfield id="parentEquipment" name="equipment.parentEquipment" readonly="true" type="text"></s:textfield><font color="red">*</font></td>
					<th>父设备名称：</th>
					<td><s:textfield id="parentEqname" name="equipment.parentEqname" readonly="true" type="text"></s:textfield><font color="red">*</font></td>
				</tr>
				<tr>
					<th>设备编号：</th>
					<td><s:textfield type="text" id="eqCode" name="equipment.eqCode"></s:textfield><font color="red">*</font></td>
					<th>设备名称：</th>
					<td><s:textfield type="text" id="eqName" name="equipment.eqName"></s:textfield><font color="red">*</font></td>
					<!-- 
					<th>零件号：</th>
					<td><s:textfield id="eqPartSn" name="equipment.eqPartSn" type="text" ></s:textfield></td>
					<th>序号：</th>
					<td><s:textfield id="eqSerialNo" name="equipment.eqSerialNo" type="text" ></s:textfield></td>
-->
				</tr>
				<tr>
					<th>设备类别：</th>
					<td><s:select id="eqCategory" name="equipment.eqCategory" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
					<th>设备类型：</th>
					<td><s:select id="eqType" name="equipment.eqType" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
				</tr>
				<tr>
					<th>设备状态：</th>
					<td><s:select id="eqSatus" name="equipment.eqSatus" list="#{'1':'设计','2':'运行','3':'库存','4':'维修','5':'退出运行','6':'废弃'}" theme="simple"></s:select></td>
					<th>重要程度：</th>
					<td><s:select id="criticality" name="equipment.criticality" list="#{'1':'不重要(NC)', '2':'重要程度低(LC)', '3':'中等重要程度(MC)', '4':'高重要程度(HC)', '5':'对环境有特殊影响(EC)'}" theme="simple"></s:select></td>
				</tr>
				<tr>
					<th>设备组号：</th>
					<td><s:textfield id="eqGroupCode" name="equipment.eqGroupCode" type="text"></s:textfield></td>
					<th>设备组名称：</th>
					<td><s:textfield id="eqGroupName" name="equipment.eqGroupName" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>安装房间号：</th>
					<td><s:textfield id="eqRoomCode" name="equipment.eqRoomCode" type="text"></s:textfield></td>
					<th>安装位置编号：</th>
					<td><s:textfield id="eqPlaceCode" name="equipment.eqPlaceCode" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>型号规格：</th>
					<td><s:select id="modelnum" name="equipment.modelnum" list="#{'1':'基础数据维护'}" theme="simple"></s:select></td>
					<th>技术等级：</th>
					<td><s:select id="techClass" name="equipment.techClass" list="#{'1':'值列表选择'}" theme="simple"></s:select></td>
				</tr>
				<tr>
					<th>重量：</th>
					<td><s:textfield id="weight" name="equipment.weight" type="text"></s:textfield></td>
					<th>重量单位：</th>
					<td><s:textfield id="wunit" name="equipment.wunit" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>客户名称：</th>
					<td><s:textfield id="kehuMingcheng" name="equipment.kehuMingcheng" type="text"></s:textfield></td>
					<th>度量单位：</th>
					<td><s:textfield id="measureUnit" name="equipment.measureUnit" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>上限值：</th>
					<td><s:textfield id="upLimit" name="equipment.upLimit" type="text"></s:textfield></td>
					<th>下限值：</th>
					<td><s:textfield id="downLimit" name="equipment.downLimit" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>采购单号：</th>
					<td><s:textfield id="caigouDanhao" name="equipment.caigouDanhao" type="text"></s:textfield></td>
					<th>采购日期：</th>
					<td><input id="_caigouRiqi" name="equipment.caigouRiqi" value="<fmt:formatDate value='${equipment.caigouRiqi}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_caigouRiqi','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th>设备账号：</th>
					<td><s:textfield id="eqAccount" name="equipment.eqAccount" type="text"></s:textfield></td>
					<th>设备成本：</th>
					<td><s:textfield id="totalCost" name="equipment.totalCost" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>采购价格：</th>
					<td><s:textfield id="caigouJiage" name="equipment.caigouJiage" type="text"></s:textfield></td>
					<th>保修期：</th>
					<td><input id="_warrantyDate" name="equipment.warrantyDate" value="<fmt:formatDate value='${equipment.warrantyDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_warrantyDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th>生产商：</th>
					<td><s:textfield id="madeName" name="equipment.madeName" type="text"></s:textfield></td>
					<th>供应商：</th>
					<td><s:textfield id="supplyName" name="equipment.supplyName" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>出厂日期：</th>
					<td><input id="_outFactoryDate" name="equipment.outFactoryDate" value="<fmt:formatDate value='${equipment.outFactoryDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('_outFactoryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th>出厂编号：</th>
					<td><s:textfield id="chuchangBianhao" name="equipment.chuchangBianhao" type="text"></s:textfield></td>
				</tr>
				<tr>
					<th>资产编号：</th>
					<td><s:textfield id="zichanBianhao" name="equipment.zichanBianhao" type="text"></s:textfield></td>
					<th>设备下是否有物理设备：</th>
					<td><s:select id="hasSub" name="equipment.hasSub" list="#{'0':'否','1':'是'}" theme="simple"></s:select></td>
				</tr>
				<tr>
					<th>是否与其他设备相连接：</th>
					<td><s:select id="isConnected" name="equipment.isConnected" list="#{'1':'是', '0':'否'}" theme="simple"></s:select></td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>设备描述：</th>
					<td colspan="3"><textarea id="description" name="equipment.description" cols="52" rows="1"><s:property value="equipment.description" /></textarea></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td><input name="" id="" onclick="javascript:return saveEquipmentJbxx()" type="button" class="btn" value="保存" /></td>
				</tr>
			</table>
		</div>

	</s:form>
</body>
</html>