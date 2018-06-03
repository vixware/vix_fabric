<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentItemCatalog(){
	$.ajax({
		  url:'${vix}/mdm/item/itemCatalogAction!goChooseItemCatalog.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择父${vv:varView('vix_mdm_item')}分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) == undefined){
								$("#parentItemCatalogId").val("");
								$("#parentItemCatalogName").val("");
								asyncbox.success("<s:text name='pleaseChooseItemCategory'/>","<s:text name='vix_message'/>");
							}else{
								var result = returnValue.split(",");
								$("#parentItemCatalogId").val(result[0]);
								$("#parentItemCatalogName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#itemCatalogForm").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="itemCatalogForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="itemCatalog.id" value="%{itemCatalog.id}" theme="simple" />
				<s:hidden id="parentItemCatalogId" name="parentItemCatalogId" value="%{itemCatalog.parentItemCatalog.id}" theme="simple" />
				<tr height="30">
					<td align="right">父分类:&nbsp;</td>
					<td><input id="parentItemCatalogName" name="itemCatalog.parentItemCatalog.name" value="${itemCatalog.parentItemCatalog.name}" /> <span class="abtn"><span onclick="chooseParentItemCatalog();">选择</span></span></td>
					<td align="right">编码:&nbsp;</td>
					<td><input id="codeRule" name="itemCatalog.codeRule" value="${itemCatalog.codeRule}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="itemCatalog.name" value="${itemCatalog.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">预设验证标准:&nbsp;</td>
					<td><input id="preExamineStandard" name="itemCatalog.preExamineStandard" value="${itemCatalog.preExamineStandard}" /></td>
				</tr>
				<tr height="30">
					<td align="right">预设采购天数:&nbsp;</td>
					<td><input id="prePurchaseDays" name="itemCatalog.prePurchaseDays" value="${itemCatalog.prePurchaseDays}"></td>
					<td align="right">预设制造天数:&nbsp;</td>
					<td><input id="preProduceDays" name="itemCatalog.preProduceDays" value="${itemCatalog.preProduceDays}" /></td>
				</tr>
				<tr height="30">
					<td align="right">预设检验天数:&nbsp;</td>
					<td><input id="preExamineDays" name="itemCatalog.preExamineDays" value="${itemCatalog.preExamineDays}"></td>
					<td align="right">预设备料天数:&nbsp;</td>
					<td><input id="preBackupDays" name="itemCatalog.preBackupDays" value="${itemCatalog.preBackupDays}" /></td>
				</tr>
				<tr height="30">
					<td align="right">预设库存计量单位:&nbsp;</td>
					<td><input id="preInventoryUnit" name="itemCatalog.preInventoryUnit" value="${itemCatalog.preInventoryUnit}"></td>
					<td align="right">预设批号控制标识:&nbsp;</td>
					<td><input id="preBatchCode" name="itemCatalog.preBatchCode" value="${itemCatalog.preBatchCode}" /></td>
				</tr>
				<tr height="30">
					<td align="right">预设虚项标识:&nbsp;</td>
					<td><input id="preVirtualItem" name="itemCatalog.preVirtualItem" value="${itemCatalog.preVirtualItem}"></td>
					<td align="right">预设是否单项发料:&nbsp;</td>
					<td><input id="isSingalItemDelivery" name="itemCatalog.isSingalItemDelivery" value="${itemCatalog.isSingalItemDelivery}" /></td>
				</tr>
				<tr height="30">
					<td align="right">预设整包发料标识:&nbsp;</td>
					<td><input id="preWholePackaged" name="itemCatalog.preWholePackaged" value="${itemCatalog.preWholePackaged}"></td>
					<td align="right">预设承认标识:&nbsp;</td>
					<td><input id="preSingal" name="itemCatalog.preSingal" value="${itemCatalog.preSingal}" /></td>
				</tr>

				<tr height="30">
					<td align="right">预设循环盘点周期:&nbsp;</td>
					<td><input id="preCycleCheck" name="itemCatalog.preCycleCheck" value="${itemCatalog.preCycleCheck}"></td>
					<td align="right">预设损耗率:&nbsp;</td>
					<td><input id="preAttritionRate" name="itemCatalog.preAttritionRate" value="${itemCatalog.preAttritionRate}" />% 范围(1-100)</td>
				</tr>
				<tr height="30">
					<td align="right">预设损耗发料标识:&nbsp;</td>
					<td><input id="preAttritionDelivery" name="itemCatalog.preAttritionDelivery" value="${itemCatalog.preAttritionDelivery}"></td>
					<td align="right">预设海关商品编码:&nbsp;</td>
					<td><input id="preCustomhouseProductCode" name="itemCatalog.preCustomhouseProductCode" value="${itemCatalog.preCustomhouseProductCode}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>