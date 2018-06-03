<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#customerizeProductItemForm").validationEngine();
 
function chooseCpiEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=singleChoose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								returnValue = returnValue.replace(',','');
								var result = returnValue.split(":");
								$("#cpiChargerId").val(result[0]);
								$("#cpiChargerName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseItemForCustomerizeProductItem(){
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
								$("#itemCustomerizeProductItemId").val(i[0]);
								$("#itemCustomerizeProductItemName").val(i[2]);
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

//-->
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="customerizeProductItemForm">
		<s:hidden id="itemCustomerizeProductItemId" name="customerizeProductItem.item.id" value="%{customerizeProductItem.item.id}" theme="simple" />
		<input type="hidden" id="customerizeProductItemId" name="customerizeProductItemId" value="${customerizeProductItem.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table style="width: 100%;">
				<tr height="30">
					<td align="right" width="15%">${vv:varView('vix_mdm_item')}:&nbsp;</td>
					<td width="35%"><input type="text" id="itemCustomerizeProductItemName" name="customerizeProductItem.item.name" value="${customerizeProductItem.item.name}" readonly="readonly" size="17" /> <span class="abtn"><a href="###" onclick="chooseItemForCustomerizeProductItem();"><span>选择</span></a></span></td>
					<td align="right" width="10%">项目负责人:&nbsp;</td>
					<td width="40%"><input type="hidden" id="cpiChargerId" name="customerizeProductItem.charger.id" value="${customerizeProductItem.charger.id}" readonly="readonly" size="17" /> <input type="text" id="cpiChargerName" name="customerizeProductItem.charger.name" value="${customerizeProductItem.charger.name}" readonly="readonly" size="17" /> <span
						class="abtn"><a href="###" onclick="chooseCpiEmployee();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">销售数量:</td>
					<td><input id="cpiAmount" name="customerizeProductItem.amount" value="${customerizeProductItem.amount}" type="text" size="25" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">计量单位 :</td>
					<td><input id="cpiUnit" name="customerizeProductItem.unit" value="${customerizeProductItem.unit}" type="text" size="25" /></td>
				</tr>
				<tr height="30">
					<td align="right">辅计算量:</td>
					<td><input id="assitAmount" name="customerizeProductItem.assitAmount" value="${customerizeProductItem.assitAmount}" type="text" size="25" /></td>
					<td align="right">辅助计量单位:</td>
					<td><input id="assitUnit" name="customerizeProductItem.assitUnit" value="${customerizeProductItem.assitUnit}" type="text" size="25" /></td>
				</tr>
				<tr height="30">
					<td align="right">单位换算率:</td>
					<td colspan="3"><input id="unitExchange" name="customerizeProductItem.unitExchange" value="${customerizeProductItem.unitExchange}" type="text" size="25" />% 范围(1-100)</td>
				</tr>
				<tr height="30">
					<td align="right">需求项内容 :</td>
					<td colspan="3"><textarea id="requirementContent" name="requirementContent" style="width: 506px; height: 84px;">${customerizeProductItem.requirementContent}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
