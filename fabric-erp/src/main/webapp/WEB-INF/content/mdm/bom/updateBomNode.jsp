<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentBomNode(){
	$.ajax({
		  url:'${vix}/mdm/bom/bomNodeAction!goChooseParentBomNode.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 880,
					height : 340,
					title:"选择父Bom节点",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#parentBomNodeId").val(result[0]);
								$("#parentBomNodeName").val(result[1]);
							}else{
								$("#parentBomNodeId").val("");
								$("#parentBomNodeName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseBomStruct(){
	$.ajax({
		  url:'${vix}/mdm/bom/bomStructAction!goChooseBomStruct.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 880,
					height : 340,
					title:"选择父${vv:varView('vix_mdm_item')}分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#bomStructId").val(result[0]);
								$("#bomStructName").val(result[1]);
							}else{
								$("#bomStructId").val("");
								$("#bomStructName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
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
$("#bomNodeForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="bomNodeForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="bomNode.id" value="%{bomNode.id}" theme="simple" />
				<s:hidden id="parentBomNodeId" name="parentBomNodeId" value="%{bomNode.parentBomNode.id}" theme="simple" />
				<s:hidden id="bomStructId" name="bomStructId" value="%{bomNode.bomStruct.id}" theme="simple" />
				<s:hidden id="itemId" name="itemId" value="%{bomNode.item.id}" theme="simple" />
				<tr height="30">
					<td align="right">Bom结构:&nbsp;</td>
					<td><input id="bomStructName" name="bomNode.bomStruct.name" value="${bomNode.bomStruct.name}" /> <span class="abtn"><span onclick="chooseBomStruct();">选择</span></span></td>
					<td align="right">父Bom节点:&nbsp;</td>
					<td><input id="parentBomNodeName" name="bomNode.parentBomNode.name" value="${bomNode.parentBomNode.name}" /> <span class="abtn"><span onclick="chooseParentBomNode();">选择</span></span></td>
				</tr>
				<tr height="30">
					<td width="15%" align="right">物料:&nbsp;</td>
					<td width="35%"><input id="itemName" name="bomNode.item.name" value="${bomNode.item.name}" /> <span class="abtn"><span onclick="chooseItem();">选择</span></span></td>
					<td width="10%" align="right">主题:&nbsp;</td>
					<td width="40%"><input id="subject" name="bomNode.subject" value="${bomNode.subject}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">配置节点类型:&nbsp;</td>
					<td><input id="nodeType" name="bomNode.nodeType" value="${bomNode.nodeType}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">数量:&nbsp;</td>
					<td><input id="amount" name="bomNode.amount" value="${bomNode.amount}" size="30" class="validate[custom[integer]] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">子件计量单位:&nbsp;</td>
					<td><input id="unit" name="bomNode.unit" value="${bomNode.unit}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">基本用量:&nbsp;</td>
					<td><input id="commonAmount" name="bomNode.commonAmount" value="${bomNode.commonAmount}" size="30" class="validate[custom[number]] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">基础数量:&nbsp;</td>
					<td><input id="baseAmount" name="bomNode.baseAmount" value="${bomNode.baseAmount}" size="30" class="validate[custom[number]] text-input" /><span style="color: red;">*</span></td>
					<td align="right">使用数量:&nbsp;</td>
					<td><input id="usedAmount" name="bomNode.usedAmount" value="${bomNode.usedAmount}" size="30" class="validate[custom[number]] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">子件阶别:&nbsp;</td>
					<td colspan="3"><input id="level" name="bomNode.level" value="${bomNode.level}" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>