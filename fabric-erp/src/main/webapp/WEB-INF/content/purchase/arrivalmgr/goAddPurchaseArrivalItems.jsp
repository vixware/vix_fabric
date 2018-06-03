<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div id="my_item_page">
	<s:action name="salesOrderAction!goSaveOrUpdateSaleOrderItem" namespace="/sales/order" executeResult="true" ignoreContextParams="true">
	</s:action>
	<s:hidden id="purchaseApplyItemId" name="purchaseItem.id" value="%{purchaseItem.id}" theme="simple" />
</div>



<script type="text/javascript">
$(function(){
	var old_right = $('#my_item_page #right');
	var my_right = $('#my_right');
	if(my_right.length>0 && old_right.length>0){
		//加载html不激活js
		old_right.html(my_right.html());
		my_right.remove();
	}

	_pad_addInputCheckNumEvent('#my_item_page #price');
	_pad_addInputCheckNumEvent('#my_item_page #soiAmount');
	_pad_addInputCheckNumEvent('#my_item_page #inTax');

	$("#recieveWareHouseId").val('${purchaseItem.recieveWareHouseId}');
});


//override
function fixedPrice(){
	if($("#my_item_page #itemId").val() == ''){
		asyncbox.success("请选择${vv:varView("vix_mdm_item")}!","<s:text name='vix_message'/>");
		return false;	
	}
	if($("#my_item_page #soiAmount").val() == ''){
		asyncbox.success("请输入${vv:varView("vix_mdm_item")}数量!","<s:text name='vix_message'/>");
		return false;
	}
	if($("#my_item_page #requireTime").val() == ''){
		asyncbox.success("请输入需求日期!","<s:text name='vix_message'/>");
		return false;
	}
	$.ajax({
		  url:'${vix}/mdm/item/purchaseItemPriceAction!goFixedPrice.action?priceConditionType=purchase&id='+$("#my_item_page #itemId").val()+"&count="+$("#my_item_page #soiAmount").val()+"&billCreateDate="+$("#my_item_page #requireTime").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 480,
					title:"定价",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								 $("#my_item_page #price").val(returnValue);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

//override
function addSalesOrderItem(){
	if($("#id").val() == ''){
		asyncbox.success("没有有效的申请id","<s:text name='vix_message'/>");
		return false;
	}
	if($("#my_item_page #itemId").val() == ''){
		asyncbox.success("请选择${vv:varView("vix_mdm_item")}!","<s:text name='vix_message'/>");
		return false;	
	}
	if($("#my_item_page #soiAmount").val() == ''){
		asyncbox.success("请输入${vv:varView("vix_mdm_item")}数量!","<s:text name='vix_message'/>");
		return false;
	}
	if($("#my_item_page #requireTime").val() == ''){
		asyncbox.success("请输入需求日期!","<s:text name='vix_message'/>");
		return false;
	}
	if($("#my_item_page #recieveWareHouseId").val() == ''){
		//asyncbox.success("请输入需收获仓库!","<s:text name='vix_message'/>");
		//return false;
	}
	if($('#saleOrderItemForm').validationEngine('validate')){
		$.post('${vix}/purchase/purchaseArrivalAction!saveOrUpdatePurchaseArrivalItem.action',
			{ 'purchaseItem.id':$("#my_item_page #purchaseApplyItemId").val(),
			  'purchaseItem.specification':$("#specification").val(),
			  'purchaseItem.itemId':$("#my_item_page #itemId").val(),
			  'purchaseItem.itemCode':$("#my_item_page #itemCode").val(),
			  'purchaseItem.itemName':$("#my_item_page #itemName").val(),
			  'purchaseItem.price':$("#my_item_page #price").val(),
			  'purchaseItem.amount':$("#my_item_page #soiAmount").val(),
			  'purchaseItem.unit':$("#my_item_page #unit").val(),
			  'purchaseItem.requireTime':$("#my_item_page #requireTime").val(),
			  'purchaseItem.taxRate':$("#my_item_page #inTax").val(),
			  'purchaseItem.recieveWareHouseId':$("#my_item_page #recieveWareHouseId").val(),
			  'purchaseItem.recieveWareHouse':$("#my_item_page #recieveWareHouseId option:selected").text(),
			  'purchaseItem.purchaseArrival.id':$("#id").val(),
			  'purchaseItem.skuCode':$("#my_item_page #skuCode").val()
			},
			function(result){
				if(result != null){
					$("#my_item_page #itemId").val("");
					$("#my_item_page #itemCode").val("");
					$("#my_item_page #itemName").val("");
					$("#my_item_page #specification").val("");
					$("#my_item_page #soiAmount").val("");
					$("#my_item_page #price").val("");
					$("#my_item_page #unit").val("");
					$("#my_item_page #skuCode").val("");
					//$("#my_item_page #inTax").val("");
					//$("#my_item_page #requireTime").val("");
					showMessage('信息保存成功');
				}else{
		    		showErrorMessage('信息保存失败');
				}
			}
		);
	}
}

//override
function chooseItemForPrice(id,code,name,price){
	$.post('${vix}/purchase/purchaseArrivalAction!loadPurchaseItemInfo.action',
		{'id':id},
		function(json){
			if(json){
				$("#my_item_page #itemId").val(json.id);
				$("#my_item_page #itemCode").val(json.code);
				$("#my_item_page #itemName").val(json.name);
				$("#my_item_page #soiAmount").val("1");
				//规格的处理逻辑需要修改
				$("#my_item_page #specification").val("");
				$("#my_item_page #price").val(json.price);
				$("#my_item_page #inTax").val(json.inTax);
				$("#my_item_page #unit").val(json.unit);
				$("#my_item_page #skuCode").val(json.skuCode);
			}
		},
		'json'
	);
}
</script>


<div id="my_right" style="display: none;">
	<div class="right_content" style="height: 390px;">
		<form id="saleOrderItemForm">
			<s:hidden id="oiId" name="purchaseItem.id" value="%{purchaseItem.id}" theme="simple" />
			<div class="box order_table" style="line-height: normal;">
				<table class="table-padding020">
					<tr height="30">
						<th align="right">编码:&nbsp;</th>
						<td><input id="itemCode" readonly="readonly" name="purchaseItem.itemCode" value="${purchaseItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					</tr>
					<tr height="30">
						<th align="right">名称:&nbsp;</th>
						<td><input id="itemId" type="hidden" value="${purchaseItem.itemId}" /> <input id="itemName" readonly="readonly" name="purchaseItem.itemName" value="${purchaseItem.itemName}" type="text" /></td>
					</tr>
					<tr height="30">
						<th align="right">数量:&nbsp;</th>
						<td><input id="soiAmount" name="purchaseItem.amount" value="${purchaseItem.amount}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
					</tr>
					<tr height="30">
						<th align="right">单位:&nbsp;</th>
						<td><input id="unit" name="purchaseItem.unit" value="${purchaseItem.unit}" type="text" class="validate[required,custom[number]] text-input" /></td>
					</tr>
					<tr height="30">
						<th align="right">规格:&nbsp;</th>
						<td><input id="specification" name="purchaseItem.specification" value="${purchaseItem.specification}" type="text" /></td>
					</tr>
					<tr height="30">
						<th align="right">价格:&nbsp;</th>
						<td><input id="price" name="purchaseItem.price" value="${purchaseItem.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span> <span class="abtn" style="cursor: pointer;" onclick="fixedPrice();"><span>定价</span></span></td>
					</tr>
					<tr height="30">
						<th align="right">进项税(%):&nbsp;</th>
						<td><input id="inTax" name="purchaseItem.taxRate" value="${purchaseItem.taxRate}" type="text" class="validate[required] text-input" /></td>
					</tr>
					<tr height="30">
						<th align="right">采购时间:&nbsp;</th>
						<td><input id="requireTime" name="requireTime" readonly="readonly" value="<s:date name="purchaseItem.requireTime" format="yyyy-MM-dd HH:mm:ss"/>" type="text" class="validate[required] text-input" onclick="showTime('requireTime','yyyy-MM-dd HH:mm:ss')" /><img onclick="showTime('requireTime','yyyy-MM-dd HH:mm:ss')"
							src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span></td>
					</tr>
					<tr height="30">
						<th align="right">收货仓库:&nbsp;</th>
						<td><select id="recieveWareHouseId" name="purchaseItem.recieveWareHouseId" class="validate[required] text-input">
								<s:iterator value="loadInvWarehouseList()" var="wh">
									<option value="${wh.id }">${wh.name }</option>
								</s:iterator>
						</select></td>
					</tr>
					<tr height="30">
						<th align="right">SKU码:&nbsp;</th>
						<td><input id="skuCode" name="purchaseItem.skuCode" value="${purchaseItem.skuCode}" type="text" class="validate[required] text-input" /></td>
					</tr>
				</table>
			</div>
			<div style="padding-left: 150px; padding-top: 15px;">
				<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>保存明细</span></span>
			</div>
		</form>
	</div>
</div>