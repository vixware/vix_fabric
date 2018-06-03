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
 
	$("#salesInvoiceItemForm").validationEngine();
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
								if(returnValue != ''){
									var r = returnValue.split(",");
									$("#itemCode").val(r[1]);
									$("#itemName").val(r[2]);
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
<input type="hidden" id="salesInvoiceItemId" name="salesInvoiceItemId" value="${salesInvoiceItem.id}" />
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="salesInvoiceItemForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">${vv:varView('vix_mdm_item')}编码:</td>
					<td><input id="itemCode" name="salesInvoiceItem.item.code" value="${salesInvoiceItem.itemCode}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</td>
					<td><input id="itemName" value="${salesInvoiceItem.itemName}" type="text" readonly="readonly" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem();">选择</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">币种</td>
					<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{salesInvoiceItem.currencyType.id}" multiple="false" theme="simple"></s:select></td>
					<td align="right">数量:</td>
					<td><input id="salesInvoiceItemAmount" name="salesInvoiceItem.amount" value="${salesInvoiceItem.amount}" type="text" /></td>

				</tr>
				<tr height="30">
					<td align="right">税率:</td>
					<td><input id="salesInvoiceItemTax" name="salesInvoiceItem.tax" value="${salesInvoiceItem.tax}" type="text" /></td>
					<td align="right">单价:</td>
					<td><input id="salesInvoiceItemPrice" name="salesInvoiceItem.price" value="${salesInvoiceItem.price}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!-- content -->
