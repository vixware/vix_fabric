<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseItem(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 580,
					title:"选择${vv:varView('vix_mdm_item')}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#itemId").val(result[0]);
								$("#itemName").val(result[1]);
							}else{
								$("#itemId").val("");
								$("#itemName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#bomStructForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="bomStructForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="bomStruct.id" value="%{bomStruct.id}" theme="simple" />
				<s:hidden id="itemId" name="itemId" value="%{bomStruct.item.id}" theme="simple" />
				<tr height="30">
					<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
					<td><input id="itemName" name="bomStruct.item.name" value="${bomStruct.item.name}" /> <span class="abtn"><span onclick="chooseItem();">选择</span></span></td>
					<td align="right">规格型号:&nbsp;</td>
					<td><input id="specification" name="bomStruct.specification" value="${bomStruct.specification}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td width="15%" align="right">bom类型:&nbsp;</td>
					<td width="35%"><input id="bomType" name="bomStruct.bomType" value="${bomStruct.bomType}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td width="16%" align="right">bom类别:&nbsp;</td>
					<td width="34%"><input id="bomClass" name="bomStruct.bomClass" value="${bomStruct.bomClass}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">配置Bom名称:&nbsp;</td>
					<td><input id="configItemBomName" name="bomStruct.configItemBomName" value="${bomStruct.configItemBomName}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">母件损耗率:&nbsp;</td>
					<td><input id="parentPartAttritionRate" name="bomStruct.parentPartAttritionRate" value="${bomStruct.parentPartAttritionRate}" size="30" class="validate[required] text-input" />% 范围(1-100)<span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">版本:&nbsp;</td>
					<td><input id="version" name="bomStruct.version" value="${bomStruct.version}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">版本日期:&nbsp;</td>
					<td><input id="versionDate" name="bomStruct.versionDate" value="${bomStruct.versionDate}" size="30" class="validate[required] text-input" onfocus="showTime('versionDate','yyyy-MM-dd')" /><span style="color: red;">*</span> <img onclick="showTime('versionDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
						align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">版本描述:&nbsp;</td>
					<td><input id="versionDescription" name="bomStruct.versionDescription" value="${bomStruct.versionDescription}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">默认基础数量:&nbsp;</td>
					<td><input id="defaultBaseAmount" name="bomStruct.defaultBaseAmount" value="${bomStruct.defaultBaseAmount}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">产品配置限定模型:&nbsp;</td>
					<td><input id="productConfigureModel" name="bomStruct.productConfigureModel" value="${bomStruct.productConfigureModel}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>