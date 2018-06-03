<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="projectQuotationTemplateItemForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="projectQuotationTemplateItemId" name="projectQuotationTemplateItem.id" value="%{projectQuotationTemplateItem.id}" theme="simple" />
			<s:hidden id="itemProjectQuotationTemplateItemId" name="projectQuotationTemplateItem.item.id" value="%{projectQuotationTemplateItem.item.id}" theme="simple" />
			<s:hidden id="parentProjectQuotationTemplateItemId" name="projectQuotationTemplateItem.parentProjectQuotationTemplateItem.id" value="%{projectQuotationTemplateItem.parentProjectQuotationTemplateItem.id}" theme="simple" />
			<table class="table-padding020">
				<tr height="30">
					<td align="right">上级报价明细 :&nbsp;</td>
					<td><input type="text" id="parentProjectQuotationTemplateItemName" name="projectQuotationTemplateItem.parentProjectQuotationTemplateItem.name" value="${projectQuotationTemplateItem.parentProjectQuotationTemplateItem.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseProjectQuotationTemplateItem();"><span>选择</span></a></span>
					</td>
					<td align="right">主题</td>
					<td><input type="text" id="projectQuotationTemplateItemName" name="projectQuotationTemplateItem.name" value="${projectQuotationTemplateItem.name}" /></td>
				</tr>
				<tr height="30">
					<td align="right">${vv:varView('vix_mdm_item')}:&nbsp;</td>
					<td><input type="text" id="itemProjectQuotationTemplateItemName" name="projectQuotationTemplateItem.item.name" value="${projectQuotationTemplateItem.item.name}" readonly="readonly" /> <span class="abtn"><a href="###" onclick="chooseItemForProjectQuotationTemplateItem();"><span>选择</span></a></span></td>
					<td align="right">数量</td>
					<td><input type="text" id="projectQuotationTemplateItemAmount" name="projectQuotationTemplateItem.amount" value="${projectQuotationTemplateItem.amount}" /></td>
				</tr>
				<tr height="30">
					<td align="right">辅计算量:&nbsp;</td>
					<td><input type="text" id="assitAmount" name="projectQuotationTemplateItem.assitAmount" value="${projectQuotationTemplateItem.assitAmount}" /></td>
					<td align="right">计量单位组：</td>
					<td><s:select id="measureUnitGroupId" headerKey="" headerValue="--请选择--" list="%{measureUnitGroupList}" listValue="name" listKey="id" value="%{projectQuotationTemplateItem.measureUnitGroup.id}" multiple="false" theme="simple" onchange="loadMeasureUnit();"></s:select></td>
				</tr>
				<tr>
					<td align="right">计量单位:&nbsp;</td>
					<td><select id="measureUnitId"><option value="-1">--------请选择------</option></select></td>
					<td align="right">辅助计量单位:&nbsp;</td>
					<td><select id="assitMeasureUnitId"><option value="-1">--------请选择------</option></select></td>
				</tr>
				<tr height="30">
					<td align="right">主辅计量单位换算率</td>
					<td><input type="text" id="unitExchange" name="projectQuotationTemplateItem.unitExchange" value="${projectQuotationTemplateItem.unitExchange}" />% 范围(1-100)</td>
					<td align="right">税率:&nbsp;</td>
					<td><input type="text" id="projectQuotationTemplateItemTax" name="projectQuotationTemplateItem.tax" value="${projectQuotationTemplateItem.tax}" />% 范围(1-100)</td>
				</tr>
				<tr height="30">
					<td align="right">单价:&nbsp;</td>
					<td><input type="text" id="projectQuotationTemplateItemPrice" name="projectQuotationTemplateItem.price" value="${projectQuotationTemplateItem.price}" /></td>
					<td align="right">折扣</td>
					<td><input type="text" id="projectQuotationTemplateItemDiscount" name="projectQuotationTemplateItem.discount" value="${projectQuotationTemplateItem.discount}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	
$("#projectQuotationTemplateItemForm").validationEngine();
function chooseProjectQuotationTemplateItem(){
	$.ajax({
		  url:'${vix}/sales/quotes/projectQuotationTemplateItemAction!goChooseQuotationTemplateItem.action?projectQuotationTemplateId='+$("#id").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 660,
					height : 380,
					title:"选择上级报价明细",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var i = returnValue.split(",");
								$("#parentProjectQuotationTemplateItemId").val(i[0]);
								$("#parentProjectQuotationTemplateItemName").val(i[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseItemForProjectQuotationTemplateItem(){
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
							if(returnValue != ''){
								var i = returnValue.split(",");
								$("#itemProjectQuotationTemplateItemId").val(i[0]);
								$("#itemProjectQuotationTemplateItemName").val(i[2]);
								$("#price").val(i[3]);
							}else{
								asyncbox.success("请选择${vv:varView('vix_mdm_item')}!","<s:text name='vix_message'/>");
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function loadMeasureUnit(){
	var mugId = $("#measureUnitGroupId").val();
	if(mugId != '-1'){	
		$.ajax({
			  url:"${vix}/sales/quotes/projectQuotationTemplateItemAction!loadMeasureUnit.action?type=pqtiMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#measureUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/sales/quotes/projectQuotationTemplateItemAction!loadMeasureUnit.action?type=pqtiAssitMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#assitMeasureUnitId").html(html);
			  }
		});
	}
}
loadMeasureUnit();
</script>