<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdateItemTabTwo(func){
	$.post('${vix}/mdm/item/itemAction!saveOrUpdate.action',
		{'itemPurchaseProperties.item.id':$("#id").val(),
		  /** 采购 一般数据*/
		  'itemPurchaseProperties.id':$("#itemPurchasePropertiesId").val(),
		  'itemPurchaseProperties.supplier.id':$("#supplierId").val(),
		  'itemPurchaseProperties.supplierItemCode':$("#supplierItemCode").val(),
		  'itemPurchaseProperties.supplierItemBarCode':$("#supplierItemBarCode").val(),
		  'itemPurchaseProperties.producerSharepartCode':$("#producerSharepartCode").val(),
		  'itemPurchaseProperties.produceSharepartDesc':$("#produceSharepartDesc").val(),
		  'itemPurchaseProperties.purBaseUnit':$("#purBaseUnit").val(),
		  'itemPurchaseProperties.purAssitUnit':$("#purAssitUnit").val(),
		  'itemPurchaseProperties.purchasePerson.id':$("#purchasePersonId").val(),
		  'itemPurchaseProperties.orderType.id':$("#orderTypeId").val(),
		  'itemPurchaseProperties.recieveWarehouse':$("#recieveWarehouse").val(),
		  'itemPurchaseProperties.recieveLocation':$("#recieveLocation").val(),
		  /** 采购 控制数据*/
		  'itemPurchaseProperties.measureUnitGroup.id':$("#measureUnitGroupId").val(),
		  'itemPurchaseProperties.purBaseUnit.id':$("#purBaseUnitId").val(),
		  'itemPurchaseProperties.purAssitUnit.id':$("#purAssitUnitId").val(),
		  'itemPurchaseProperties.poUnit.id':$("#poUnitId").val(),
		  'itemPurchaseProperties.lessDeliveryAllowance':$("#lessDeliveryAllowance").val(),
		  'itemPurchaseProperties.exceedDelieryAllowance':$("#exceedDelieryAllowance").val(),
		  'itemPurchaseProperties.minDelieryPercent':$("#minDelieryPercent").val(),
		  'itemPurchaseProperties.minDelieryAmount':$("#minDelieryAmount").val(),
		  'itemPurchaseProperties.minDelieryPercent':$("#minDelieryPercent").val(),
		  'itemPurchaseProperties.minDelieryAmount':$("#minDelieryAmount").val(),
		  'itemPurchaseProperties.permitAheadDays':$("#permitAheadDays").val(),
		  'itemPurchaseProperties.permitDelayDays':$("#permitDelayDays").val(),
		  'itemPurchaseProperties.remindDay1':$("#remindDay1").val(),
		  'itemPurchaseProperties.remindDay2':$("#remindDay2").val(),
		  'itemPurchaseProperties.remindDay3':$("#remindDay3").val(),
		  'itemPurchaseProperties.remindDay4':$("#remindDay4").val(),
		  'itemPurchaseProperties.remindDay5':$("#remindDay5").val(),
		  'itemPurchaseProperties.standardDelieryDeviation':$("#standardDelieryDeviation").val(),
		  'itemPurchaseProperties.takeDelieryDays':$("#takeDelieryDays").val(),
		  'itemPurchaseProperties.quota':$("#quota").val(),
		  'itemPurchaseProperties.orderType.id':$("#orderTypeId").val(),
		  'itemPurchaseProperties.recieveWarehouse.id':$("#recieveWarehouseId").val(),
		  'itemPurchaseProperties.invShelf.id':$("#purchaseInvShelfId").val(),
		  'itemPurchaseProperties.isNeedBatch':$(":radio[name=isNeedBatch][checked]").val(),
		  'itemPurchaseProperties.isJIT':$(":radio[name=isJIT][checked]").val(),
		  'itemPurchaseProperties.isKeyComponent':$(":radio[name=isKeyComponent][checked]").val(),
		  'itemPurchaseProperties.specialPurchaseType':$("#specialPurchaseType").val(),
		  'itemPurchaseProperties.isPermitReplaceItem':$(":radio[name=isPermitReplaceItem][checked]").val(),
		  'itemPurchaseProperties.isPermitNoOrderTake':$(":radio[name=isPermitNoOrderTake][checked]").val(),
		  /** 销售 */
		  'itemSaleProperties.item.id':$("#id").val(),
		  'itemSaleProperties.id':$("#itemSalePropertiesId").val(),
		  'itemSaleProperties.saleOrg':$("#saleOrg").val(),
		  'itemSaleProperties.saleGroup':$("#saleGroup").val(),
		  'itemSaleProperties.dealAmountCommissionGroup':$("#dealAmountCommissionGroup").val(),
		  'itemSaleProperties.commissionGroup':$("#commissionGroup").val(),
		  'itemSaleProperties.salePerson.id':$("#salePersonId").val(),
		  'itemSaleProperties.deliveryFactory':$("#deliveryFactory").val(),
		  'itemSaleProperties.channel':$("#channel").val(),
		  'itemSaleProperties.defaultMarginCenter':$("#defaultMarginCenter").val(),
		  'itemSaleProperties.saleTax':$("#saleTax").val(),
		  'itemSaleProperties.minRequireAmount':$("#minRequireAmount").val(),
		  'itemSaleProperties.minDeliveryAmount':$("#minDeliveryAmount").val(),
		  'itemSaleProperties.itemPriceGroup':$("#itemPriceGroup").val(),
		  'itemSaleProperties.setPriceRefItemCode':$("#setPriceRefItemCode").val(),
		  'itemSaleProperties.isReturnCheck':$(":radio[name=isReturnCheck][checked]").val(),
		  'itemSaleProperties.saleUnit':$("#saleUnit").val(),
		  'itemSaleProperties.saleAssitUnit':$("#saleAssitUnit").val(),
		  'itemSaleProperties.productGroup':$("#productGroup").val(),
		  'itemSaleProperties.isCashDiscount':$(":radio[name=isCashDiscount][checked]").val(),
		  'itemSaleProperties.accountTitleGroup':$("#accountTitleGroup").val(),
		  'itemSaleProperties.saleAccountTitle':$("#saleAccountTitle").val(),
		  'itemSaleProperties.saleUnit':$("#saleUnit").val(),
		  'itemSaleProperties.saleDefaultUnit':$("#saleDefaultUnit").val(),
		  /** 库存 */
		  'itemInventoryProperties.item.id':$("#id").val(),
		  'itemInventoryProperties.id':$("#itemInventoryPropertiesId").val(),
		  'itemInventoryProperties.maxStockAmount':$("#maxStockAmount").val(),
		  'itemInventoryProperties.minStockAmount':$("#minStockAmount").val(),
		  'itemInventoryProperties.safeStockAmount':$("#safeStockAmount").val(),
		  'itemInventoryProperties.defaultWarehouse.id':$("#defaultWarehouseId").val(),
		  'itemInventoryProperties.invShelf.id':$("#inventoryInvShelfId").val(),
		  'itemInventoryProperties.replaceItem':$("#replaceItem").val(),
		  'itemInventoryProperties.requireMaxAmount':$("#requireMaxAmount").val(),
		  'itemInventoryProperties.inStockExceedAmount':$("#inStockExceedAmount").val(),
		  'itemInventoryProperties.outStockExceedAmount':$("#outStockExceedAmount").val(),
		  'itemInventoryProperties.bookingExceedAmount':$("#bookingExceedAmount").val(),
		  'itemInventoryProperties.unitExchangeRate':$("#unitExchangeRate").val(),
		  'itemInventoryProperties.coefficientOfLosses':$("#coefficientOfLosses").val(),
		  'itemInventoryProperties.economicBatchAmount':$("#economicBatchAmount").val(),
		  'itemInventoryProperties.abcCatalog':$("#abcCatalog").val(),
		  'itemInventoryProperties.catalogCode':$("#catalogCode").val(),
		  'itemInventoryProperties.customerCatalog':$("#customerCatalog").val(),
		  'itemInventoryProperties.receiveMateriaBatchAmount':$("#receiveMateriaBatchAmount").val(),
		  'itemInventoryProperties.minSperateGroup':$("#minSperateGroup").val(),
		  'itemInventoryProperties.shelfLife':$("#shelfLife").val(),
		  'itemInventoryProperties.countingCycle':$("#countingCycle").val(),
		  'itemInventoryProperties.countingCycleUnit':$("#countingCycleUnit").val(),
		  'itemInventoryProperties.lastCountingTime':$("#lastCountingTime").val(),
		  'itemInventoryProperties.isBackflush':$(":radio[name=isBackflush][checked]").val(),
		  'itemInventoryProperties.isNeedStock':$(":radio[name=isNeedStock][checked]").val(),
		  'itemInventoryProperties.isNeedBatchNumber':$(":radio[name=isNeedBatchNumber][checked]").val(),
		  'itemInventoryProperties.isStockAlone':$(":radio[name=isStockAlone][checked]").val(),
		  'itemInventoryProperties.isOutTraceIn':$(":radio[name=isOutTraceIn][checked]").val(),
		  'itemInventoryProperties.isNeedSerialNumber':$(":radio[name=isNeedSerialNumber][checked]").val(),
		  'itemInventoryProperties.isNeedBarCode':$(":radio[name=isNeedBarCode][checked]").val(),
		},
		function(result){
			showMessage(result);
			func();
		}
	);
}
function chooseEmployee(tag){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
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
								var result = returnValue.split(":");
								$("#"+tag+"Id").val(result[0].replace(",",""));
								$("#"+tag+"Name").val(result[1].replace(",",""));
							}else{
								$("#"+tag+"Id").val("");
								$("#"+tag+"Name").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function choosewarehouse(tag) {
	$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 500,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#"+tag+"Id").val(result[0]);
						$("#"+tag+"Name").val(result[1]);
						loadInvShelf(tag)
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
						$("#"+tag+"Id").val("");
						$("#"+tag+"Name").val("");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
	});
}

function loadInvShelf(tag){
	if(tag == 'defaultWarehouse'){
		var dwhId = $("#defaultWarehouseId").val();
		if(dwhId != '-1'){
			$.ajax({
				  url:"${vix}/mdm/item/itemInventoryPropertiesAction!loadInvShelf.action?id="+$("#itemInventoryPropertiesId").val()+"&invWarehouseId="+dwhId,
				  cache: false,
				  success: function(html){
					  $("#inventoryInvShelfId").html(html);
				  }
			});
		}
	}
	if(tag == 'recieveWarehouse'){
		var rwhId = $("#recieveWarehouseId").val();
		if(rwhId != '-1'){
			$.ajax({
				  url:"${vix}/mdm/item/itemPurchasePropertiesAction!loadInvShelf.action?id="+$("#itemPurchasePropertiesId").val()+"&invWarehouseId="+rwhId,
				  cache: false,
				  success: function(html){
					  $("#purchaseInvShelfId").html(html);
				  }
			});
		}
	}
}
loadInvShelf('defaultWarehouse');loadInvShelf('recieveWarehouse');
//选择供应商
function chooseSupplier(){
	$.ajax({
		  url:'${vix}/purchase/purchaseOrderAction!goChooseRadioSupplier.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 500,
					title:"选择供应商",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#supplierId").val(result[0]);
								$("#supplierName").val(result[1]);
								$("#supplierCode").val(result[2]);
							}else{
								$("#supplierId").val("");
								$("#supplierName").val("");
								$("#supplierCode").val("");
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
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#measureUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=purBaseUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#purBaseUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=purAssitUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#purAssitUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/mdm/item/itemAction!loadMeasureUnit.action?type=poUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#poUnitId").html(html);
			  }
		});
	}
}
loadMeasureUnit();
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
$("#two").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="two">
		<div class="order">
			<dl>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">编码:</td>
								<td width="35%"><input value="${item.code}" type="text" size="30" readonly="readonly" /></td>
								<td align="right" width="10%">名称:</td>
								<td width="40%"><input value="${item.name}" type="text" size="30" readonly="readonly" /></td>
							</tr>
						</table>
						<input type="hidden" id="itemPurchasePropertiesId" name="itemPurchasePropertiesId" value="${itemPurchaseProperties.id}" /> <input type="hidden" id="itemSalePropertiesId" name="itemSalePropertiesId" value="${itemSaleProperties.id}" /> <input type="hidden" id="itemInventoryPropertiesId" name="itemInventoryPropertiesId"
							value="${itemInventoryProperties.id}" />
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>采购信息</strong>
								</dt>
								<dd style="display: block;">
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">一般数据</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">供应商编码：</td>
											<td width="35%"><input id="supplierCode" name="itemPurchaseProperties.supplier.code" value="${itemPurchaseProperties.supplier.code}" type="text" size="30" /></td>
											<td width="10%" align="right">供应商名称：</td>
											<td width="40%"><input id="supplierName" name="itemPurchaseProperties.supplier.name" value="${itemPurchaseProperties.supplier.name}" type="text" size="30" /> <input type="hidden" id="supplierId" name="supplierId" value="${itemPurchaseProperties.supplier.id}" /> <a class="abtn" href="###" onclick="chooseSupplier();"><span>选择</span></a>
											</td>
										</tr>
										<tr>
											<td align="right">供应商零配件概况：</td>
											<td><input id="produceSharepartDesc" name="itemPurchaseProperties.produceSharepartDesc" value="${itemPurchaseProperties.produceSharepartDesc}" type="text" size="30" /></td>
											<td align="right">供应商${vv:varView('vix_mdm_item')}编码：</td>
											<td><input id="supplierItemCode" name="itemPurchaseProperties.supplierItemCode" value="${itemPurchaseProperties.supplierItemCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供应商${vv:varView('vix_mdm_item')}条形码：</td>
											<td><input id="supplierItemBarCode" name="itemPurchaseProperties.supplierItemBarCode" value="${itemPurchaseProperties.supplierItemBarCode}" type="text" size="30" /></td>
											<td align="right">供应商零配件编码：</td>
											<td><input id="producerSharepartCode" name="itemPurchaseProperties.recieveLocation" value="${itemPurchaseProperties.recieveLocation}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计量单位组：</td>
											<td><s:select id="measureUnitGroupId" headerKey="-1" headerValue="--请选择--" list="%{measureUnitGroupList}" listValue="name" listKey="id" value="%{itemPurchaseProperties.measureUnitGroup.id}" multiple="false" theme="simple" onchange="loadMeasureUnit();"></s:select></td>
											<td align="right">采购基本计量单位：</td>
											<td><select id="purBaseUnitId"><option value="-1">--------请选择------</option></select></td>
										</tr>
										<tr>
											<td align="right">采购辅助计量单位：</td>
											<td><select id="purAssitUnitId"><option value="-1">--------请选择------</option></select></td>
											<td align="right">采购订单单位：</td>
											<td><select id="poUnitId"><option value="-1">--------请选择------</option></select></td>
										</tr>
										<tr>
											<td align="right">采购组：</td>
											<td><input id="purchaseGroup" name="itemPurchaseProperties.purchaseGroup" value="${itemPurchaseProperties.purchaseGroup}" type="text" size="30" /></td>
											<td align="right">采购员：</td>
											<td><input type="hidden" id="purchasePersonId" value="${itemPurchaseProperties.purchasePerson.id}" /> <input id="purchasePersonName" name="itemPurchaseProperties.purchasePerson.name" value="${itemPurchaseProperties.purchasePerson.name}" type="text" size="30" /> <a class="abtn" href="###" onclick="chooseEmployee('purchasePerson');"><span>选择</span></a>
											</td>
										</tr>
										<tr>
											<td align="right">采购类型：</td>
											<td><s:select id="orderTypeId" headerKey="-1" headerValue="--请选择--" list="%{orderTypeList}" listValue="name" listKey="id" value="%{itemPurchaseProperties.orderType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">接收仓库：</td>
											<td><input type="hidden" id="recieveWarehouseId" value="${itemPurchaseProperties.recieveWarehouse.id}" /> <input id="recieveWarehouseName" name="itemPurchaseProperties.recieveWarehouse.name" value="${itemPurchaseProperties.recieveWarehouse.name}" type="text" size="30" /> <a class="abtn" href="###"
												onclick="choosewarehouse('recieveWarehouse');"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">库位:</td>
											<td><select id="purchaseInvShelfId"><option value="-1">--------请选择------</option></select></td>
											<td align="right">接收地址：</td>
											<td><input id="recieveLocation" name="itemPurchaseProperties.recieveLocation" value="${itemPurchaseProperties.recieveLocation}" type="text" size="30" /></td>
										</tr>
									</table>
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">采购控制数据</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">不足交货容差：</td>
											<td width="35%"><input id="lessDeliveryAllowance" name="itemPurchaseProperties.lessDeliveryAllowance" value="${itemPurchaseProperties.lessDeliveryAllowance}" type="text" size="30" /></td>
											<td width="10%" align="right">超量交货容差：</td>
											<td width="40%"><input id="exceedDelieryAllowance" name="itemPurchaseProperties.exceedDelieryAllowance" value="${itemPurchaseProperties.exceedDelieryAllowance}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小交货数量百分比：</td>
											<td><input id="minDelieryPercent" name="itemPurchaseProperties.minDelieryPercent" value="${itemPurchaseProperties.minDelieryPercent}" type="text" size="30" /></td>
											<td align="right">最小交货数量：</td>
											<td><input id="minDelieryAmount" name="itemPurchaseProperties.minDelieryAmount" value="${itemPurchaseProperties.minDelieryAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小交货数量百分比：</td>
											<td><input id="minDelieryPercent" name="itemPurchaseProperties.minDelieryPercent" value="${itemPurchaseProperties.minDelieryPercent}" type="text" size="30" /></td>
											<td align="right">最小交货数量：</td>
											<td><input id="minDelieryAmount" name="itemPurchaseProperties.minDelieryAmount" value="${itemPurchaseProperties.minDelieryAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">允许提前天数：</td>
											<td><input id="permitAheadDays" name="itemPurchaseProperties.permitAheadDays" value="${itemPurchaseProperties.permitAheadDays}" type="text" size="30" /></td>
											<td align="right">允许滞后天数：</td>
											<td><input id="permitDelayDays" name="itemPurchaseProperties.permitDelayDays" value="${itemPurchaseProperties.permitDelayDays}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">催付天数1：</td>
											<td><input id="remindDay1" name="itemPurchaseProperties.remindDay1" value="${itemPurchaseProperties.remindDay1}" type="text" size="30" /></td>
											<td align="right">催付天数2：</td>
											<td><input id="remindDay2" name="itemPurchaseProperties.remindDay2" value="${itemPurchaseProperties.remindDay2}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">催付天数3：</td>
											<td><input id="remindDay3" name="itemPurchaseProperties.remindDay3" value="${itemPurchaseProperties.remindDay3}" type="text" size="30" /></td>
											<td align="right">催付天数4：</td>
											<td><input id="remindDay4" name="itemPurchaseProperties.remindDay4" value="${itemPurchaseProperties.remindDay4}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">催付天数5：</td>
											<td><input id="remindDay5" name="itemPurchaseProperties.remindDay5" value="${itemPurchaseProperties.remindDay5}" type="text" size="30" /></td>
											<td align="right">标准交货时间偏差：</td>
											<td><input id="standardDelieryDeviation" name="itemPurchaseProperties.standardDelieryDeviation" value="${itemPurchaseProperties.standardDelieryDeviation}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">收货处理时间：</td>
											<td><input id="takeDelieryDays" name="itemPurchaseProperties.takeDelieryDays" value="${itemPurchaseProperties.takeDelieryDays}" type="text" size="30" /></td>
											<td align="right">配额安排：</td>
											<td><input id="quota" name="itemPurchaseProperties.quota" value="${itemPurchaseProperties.quota}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否需要批次管理：</td>
											<td><s:if test="itemPurchaseProperties.isNeedBatch == 0">
													<input type="radio" id="isNeedBatch1" name="isNeedBatch" value="1" />是
													<input type="radio" id="isNeedBatch2" name="isNeedBatch" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemPurchaseProperties.isNeedBatch == 1">
													<input type="radio" id="isNeedBatch1" name="isNeedBatch" value="1" checked="checked" />是
													<input type="radio" id="isNeedBatch2" name="isNeedBatch" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isNeedBatch1" name="isNeedBatch" value="1" />是
													<input type="radio" id="isNeedBatch2" name="isNeedBatch" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否为关键组件：</td>
											<td><s:if test="itemPurchaseProperties.isKeyComponent == 0">
													<input type="radio" id="isKeyComponent1" name="isKeyComponent" value="1" />是
													<input type="radio" id="isKeyComponent2" name="isKeyComponent" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemPurchaseProperties.isKeyComponent == 1">
													<input type="radio" id="isKeyComponent1" name="isKeyComponent" value="1" checked="checked" />是
													<input type="radio" id="isKeyComponent2" name="isKeyComponent" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isKeyComponent1" name="isKeyComponent" value="1" />是
													<input type="radio" id="isKeyComponent2" name="isKeyComponent" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否JIT：</td>
											<td><s:if test="itemPurchaseProperties.isJIT == 0">
													<input type="radio" id="isJIT1" name="isJIT" value="1" />是
													<input type="radio" id="isJIT2" name="isJIT" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemPurchaseProperties.isJIT == 1">
													<input type="radio" id="isJIT1" name="isJIT" value="1" checked="checked" />是
													<input type="radio" id="isJIT2" name="isJIT" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isJIT1" name="isJIT" value="1" />是
													<input type="radio" id="isJIT2" name="isJIT" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">特殊采购类：</td>
											<td><input id="specialPurchaseType" name="itemPurchaseProperties.specialPurchaseType" value="${itemPurchaseProperties.specialPurchaseType}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否允许替换物品收货：</td>
											<td><s:if test="itemPurchaseProperties.isPermitReplaceItem == 0">
													<input type="radio" id="isPermitReplaceItem1" name="isPermitReplaceItem" value="1" />是
													<input type="radio" id="isPermitReplaceItem2" name="isPermitReplaceItem" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemPurchaseProperties.isPermitReplaceItem == 1">
													<input type="radio" id="isPermitReplaceItem1" name="isPermitReplaceItem" value="1" checked="checked" />是
													<input type="radio" id="isPermitReplaceItem2" name="isPermitReplaceItem" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isPermitReplaceItem1" name="isPermitReplaceItem" value="1" />是
													<input type="radio" id="isPermitReplaceItem2" name="isPermitReplaceItem" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否允许没有订单收货：</td>
											<td><s:if test="itemPurchaseProperties.isPermitNoOrderTake == 0">
													<input type="radio" id="isPermitNoOrderTake1" name="isPermitNoOrderTake" value="1" />是
													<input type="radio" id="isPermitNoOrderTake2" name="isPermitNoOrderTake" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemPurchaseProperties.isPermitNoOrderTake == 1">
													<input type="radio" id="isPermitNoOrderTake1" name="isPermitNoOrderTake" value="1" checked="checked" />是
													<input type="radio" id="isPermitNoOrderTake2" name="isPermitNoOrderTake" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isPermitNoOrderTake1" name="isPermitNoOrderTake" value="1" />是
													<input type="radio" id="isPermitNoOrderTake2" name="isPermitNoOrderTake" value="0" checked="checked" />否
												</s:else></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>销售信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">销售组织 ：</td>
											<td width="35%"><input id="saleOrg" name="itemSaleProperties.saleOrg" value="${itemSaleProperties.saleOrg}" type="text" size="30" /></td>
											<td width="10%" align="right">销售组：</td>
											<td width="40%"><input id="saleGroup" name="itemSaleProperties.saleGroup" value="${itemSaleProperties.saleGroup}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">成交额回扣组 ：</td>
											<td><input id="dealAmountCommissionGroup" name="itemSaleProperties.dealAmountCommissionGroup" value="${itemSaleProperties.dealAmountCommissionGroup}" type="text" size="30" /></td>
											<td align="right">佣金组：</td>
											<td><input id="commissionGroup" name="itemSaleProperties.commissionGroup" value="${itemSaleProperties.commissionGroup}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售员：</td>
											<td><input type="hidden" id="salePersonId" value="${itemSaleProperties.salePerson.id}" /> <input id="salePersonName" name="itemSaleProperties.salePerson.name" value="${itemSaleProperties.salePerson.name}" type="text" size="30" /> <a class="abtn" href="###" onclick="chooseEmployee('salePerson');"><span>选择</span></a></td>
											<td align="right">交货工厂：</td>
											<td><input id="deliveryFactory" name="itemSaleProperties.deliveryFactory" value="${itemSaleProperties.deliveryFactory}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">渠道：</td>
											<td><input id="channel" name="itemSaleProperties.channel" value="${itemSaleProperties.channel}" type="text" size="30" /></td>
											<td align="right">缺省利润中心：</td>
											<td><input id="defaultMarginCenter" name="itemSaleProperties.defaultMarginCenter" value="${itemSaleProperties.defaultMarginCenter}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售税率 ：</td>
											<td><input id="saleTax" name="itemSaleProperties.saleTax" value="${itemSaleProperties.saleTax}" type="text" size="30" />% 范围(1-100)</td>
											<td align="right">最小订购量：</td>
											<td><input id="minRequireAmount" name="itemSaleProperties.minRequireAmount" value="${itemSaleProperties.minRequireAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最小发货量：</td>
											<td><input id="minDeliveryAmount" name="itemSaleProperties.minDeliveryAmount" value="${itemSaleProperties.minDeliveryAmount}" type="text" size="30" /></td>
											<td align="right">${vv:varView('vix_mdm_item')}定价组：</td>
											<td><input id="itemPriceGroup" name="itemSaleProperties.itemPriceGroup" value="${itemSaleProperties.itemPriceGroup}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">定价参考${vv:varView('vix_mdm_item')} ：</td>
											<td><input id="setPriceRefItemCode" name="itemSaleProperties.setPriceRefItemCode" value="${itemSaleProperties.setPriceRefItemCode}" type="text" size="30" /></td>
											<td align="right">销售退回是否需检验：</td>
											<td><s:if test="itemSaleProperties.isReturnCheck == 0">
													<input type="radio" id="isReturnCheck1" name="isReturnCheck" value="1" />是
													<input type="radio" id="isReturnCheck2" name="isReturnCheck" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemSaleProperties.isReturnCheck == 1">
													<input type="radio" id="isReturnCheck1" name="isReturnCheck" value="1" checked="checked" />是
													<input type="radio" id="isReturnCheck2" name="isReturnCheck" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isReturnCheck1" name="isReturnCheck" value="1" />是
													<input type="radio" id="isReturnCheck2" name="isReturnCheck" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">销售计量单位：</td>
											<td><input id="saleUnit" name="itemSaleProperties.saleUnit" value="${itemSaleProperties.saleUnit}" type="text" size="30" /></td>
											<td align="right">销售辅助计量单位：</td>
											<td><input id="saleAssitUnit" name="itemSaleProperties.saleAssitUnit" value="${itemSaleProperties.saleAssitUnit}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">产品组：</td>
											<td><input id="productGroup" name="itemSaleProperties.productGroup" value="${itemSaleProperties.productGroup}" type="text" size="30" /></td>
											<td align="right">是否提供现金折扣：</td>
											<td><s:if test="itemSaleProperties.isCashDiscount == 0">
													<input type="radio" id="isCashDiscount1" name="isCashDiscount" value="1" />是
													<input type="radio" id="isCashDiscount2" name="isCashDiscount" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemSaleProperties.isCashDiscount == 1">
													<input type="radio" id="isCashDiscount1" name="isCashDiscount" value="1" checked="checked" />是
													<input type="radio" id="isCashDiscount2" name="isCashDiscount" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isCashDiscount1" name="isCashDiscount" value="1" />是
													<input type="radio" id="isCashDiscount2" name="isCashDiscount" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">科目设置组：</td>
											<td><input id="accountTitleGroup" name="itemSaleProperties.accountTitleGroup" value="${itemSaleProperties.accountTitleGroup}" type="text" size="30" /></td>
											<td align="right">销售科目：</td>
											<td><input id="saleAccountTitle" name="itemSaleProperties.saleAccountTitle" value="${itemSaleProperties.saleAccountTitle}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">销售单位：</td>
											<td><input id="saleUnit" name="itemSaleProperties.saleUnit" value="${itemSaleProperties.saleUnit}" type="text" size="30" /></td>
											<td align="right">销售默认单位：</td>
											<td><input id="saleDefaultUnit" name="itemSaleProperties.saleDefaultUnit" value="${itemSaleProperties.saleDefaultUnit}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>库存信息</strong>
								</dt>
								<dd style="display: block;">
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">库存控制</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">最高库存：</td>
											<td width="35%"><input id="maxStockAmount" name="itemInventoryProperties.maxStockAmount" value="${itemInventoryProperties.maxStockAmount}" type="text" size="30" /></td>
											<td width="10%" align="right">最低库存：</td>
											<td width="40%"><input id="minStockAmount" name="itemInventoryProperties.minStockAmount" value="${itemInventoryProperties.minStockAmount}" type="text" size="30" size="30" /></td>
										</tr>
										<tr>
											<td align="right">安全库存：</td>
											<td><input id="safeStockAmount" name="itemInventoryProperties.safeStockAmount" value="${itemInventoryProperties.safeStockAmount}" type="text" size="30" /></td>
											<td align="right">默认仓库：</td>
											<td><input type="hidden" id="defaultWarehouseId" value="${itemInventoryProperties.defaultWarehouse.id}" /> <input id="defaultWarehouseName" name="itemInventoryProperties.defaultWarehouse.name" value="${itemInventoryProperties.defaultWarehouse.name}" type="text" size="30" /> <a class="abtn" href="###"
												onclick="choosewarehouse('defaultWarehouse');"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">库位:</td>
											<td><select id="inventoryInvShelfId"><option value="-1">--------请选择------</option></select></td>
											<td align="right">替换${vv:varView('vix_mdm_item')}：</td>
											<td><input id="replaceItem" name="itemInventoryProperties.replaceItem" value="${itemInventoryProperties.replaceItem}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">请购时上限：</td>
											<td><input id="requireMaxAmount" name="itemInventoryProperties.requireMaxAmount" value="${itemInventoryProperties.requireMaxAmount}" type="text" size="30" /></td>
											<td align="right">入库超额上限：</td>
											<td><input id="inStockExceedAmount" name="itemInventoryProperties.inStockExceedAmount" value="${itemInventoryProperties.inStockExceedAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">出库超额上限：</td>
											<td><input id="outStockExceedAmount" name="itemInventoryProperties.outStockExceedAmount" value="${itemInventoryProperties.outStockExceedAmount}" type="text" size="30" /></td>
											<td align="right">订货超额上限：</td>
											<td><input id="bookingExceedAmount" name="itemInventoryProperties.bookingExceedAmount" value="${itemInventoryProperties.bookingExceedAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">主辅单位转换率：</td>
											<td><input id="unitExchangeRate" name="itemInventoryProperties.unitExchangeRate" value="${itemInventoryProperties.unitExchangeRate}" type="text" size="30" />% 范围(1-100)</td>
											<td align="right">合理耗损率：</td>
											<td><input id="coefficientOfLosses" name="itemInventoryProperties.coefficientOfLosses" value="${itemInventoryProperties.coefficientOfLosses}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">经济批次：</td>
											<td><input id="economicBatchAmount" name="itemInventoryProperties.economicBatchAmount" value="${itemInventoryProperties.economicBatchAmount}" type="text" size="30" /></td>
											<td align="right">ABC分类：</td>
											<td><input id="abcCatalog" name="itemInventoryProperties.abcCatalog" value="${itemInventoryProperties.abcCatalog}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">库存分类：</td>
											<td><input id="catalogCode" name="itemInventoryProperties.catalogCode" value="${itemInventoryProperties.catalogCode}" type="text" size="30" /></td>
											<td align="right">自定义库存分类：</td>
											<td><input id="customerCatalog" name="itemInventoryProperties.customerCatalog" value="${itemInventoryProperties.customerCatalog}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">领料批量：</td>
											<td><input id="receiveMateriaBatchAmount" name="itemInventoryProperties.receiveMateriaBatchAmount" value="${itemInventoryProperties.receiveMateriaBatchAmount}" type="text" size="30" /></td>
											<td align="right">最小分割组：</td>
											<td><input id="minSperateGroup" name="itemInventoryProperties.minSperateGroup" value="${itemInventoryProperties.minSperateGroup}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">库存寿命(保质期)：</td>
											<td><input id="shelfLife" name="itemInventoryProperties.shelfLife" value="${itemInventoryProperties.shelfLife}" type="text" size="30" /></td>
											<td align="right">盘点周期：</td>
											<td><input id="countingCycle" name="itemInventoryProperties.countingCycle" value="${itemInventoryProperties.countingCycle}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">盘点周期单位：</td>
											<td><input id="countingCycleUnit" name="itemInventoryProperties.countingCycleUnit" value="${itemInventoryProperties.countingCycleUnit}" type="text" size="30" /></td>
											<td align="right">上次盘点时间：</td>
											<td><input id="lastCountingTime" name="itemInventoryProperties.lastCountingTime" value="${itemInventoryProperties.lastCountingTime}" type="text" size="30" /> <img onclick="showTime('lastCountingTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">参数控制</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">是否倒冲入账：</td>
											<td width="35%"><s:if test="itemInventoryProperties.isBackflush == 0">
													<input type="radio" id="isBackflush1" name="isBackflush" value="1" />是
													<input type="radio" id="isBackflush2" name="isBackflush" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isBackflush == 1">
													<input type="radio" id="isBackflush1" name="isBackflush" value="1" checked="checked" />是
													<input type="radio" id="isBackflush2" name="isBackflush" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isBackflush1" name="isBackflush" value="1" />是
													<input type="radio" id="isBackflush2" name="isBackflush" value="0" checked="checked" />否
												</s:else></td>
											<td width="10%" align="right">是否进行库存管理：</td>
											<td width="40%"><s:if test="itemInventoryProperties.isNeedStock == 0">
													<input type="radio" id="isNeedStock1" name="isNeedStock" value="1" />是
													<input type="radio" id="isNeedStock2" name="isNeedStock" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isNeedStock == 1">
													<input type="radio" id="isNeedStock1" name="isNeedStock" value="1" checked="checked" />是
													<input type="radio" id="isNeedStock2" name="isNeedStock" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isNeedStock1" name="isNeedStock" value="1" />是
													<input type="radio" id="isNeedStock2" name="isNeedStock" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否需要批次号：</td>
											<td><s:if test="itemInventoryProperties.isNeedBatchNumber == 0">
													<input type="radio" id="isNeedBatchNumber1" name="isNeedBatchNumber" value="1" />是
													<input type="radio" id="isNeedBatchNumber2" name="isNeedBatchNumber" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isNeedBatchNumber == 1">
													<input type="radio" id="isNeedBatchNumber1" name="isNeedBatchNumber" value="1" checked="checked" />是
													<input type="radio" id="isNeedBatchNumber2" name="isNeedBatchNumber" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isNeedBatchNumber1" name="isNeedBatchNumber" value="1" />是
													<input type="radio" id="isNeedBatchNumber2" name="isNeedBatchNumber" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否单独存放：</td>
											<td><s:if test="itemInventoryProperties.isStockAlone == 0">
													<input type="radio" id="isStockAlone1" name="isStockAlone" value="1" />是
													<input type="radio" id="isStockAlone2" name="isStockAlone" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isStockAlone == 1">
													<input type="radio" id="isStockAlone1" name="isStockAlone" value="1" checked="checked" />是
													<input type="radio" id="isStockAlone2" name="isStockAlone" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isStockAlone1" name="isStockAlone" value="1" />是
													<input type="radio" id="isStockAlone2" name="isStockAlone" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">出入库跟踪：</td>
											<td><s:if test="itemInventoryProperties.isOutTraceIn == 0">
													<input type="radio" id="isOutTraceIn1" name="isOutTraceIn" value="1" />是
													<input type="radio" id="isOutTraceIn2" name="isOutTraceIn" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isOutTraceIn == 1">
													<input type="radio" id="isOutTraceIn1" name="isOutTraceIn" value="1" checked="checked" />是
													<input type="radio" id="isOutTraceIn2" name="isOutTraceIn" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isOutTraceIn1" name="isOutTraceIn" value="1" />是
													<input type="radio" id="isOutTraceIn2" name="isOutTraceIn" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否序列号管理：</td>
											<td><s:if test="itemInventoryProperties.isNeedSerialNumber == 0">
													<input type="radio" id="isNeedSerialNumber1" name="isNeedSerialNumber" value="1" />是
													<input type="radio" id="isNeedSerialNumber2" name="isNeedSerialNumber" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isNeedSerialNumber == 1">
													<input type="radio" id="isNeedSerialNumber1" name="isNeedSerialNumber" value="1" checked="checked" />是
													<input type="radio" id="isNeedSerialNumber2" name="isNeedSerialNumber" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isNeedSerialNumber1" name="isNeedSerialNumber" value="1" />是
													<input type="radio" id="isNeedSerialNumber2" name="isNeedSerialNumber" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否条码管理：</td>
											<td colspan="3"><s:if test="itemInventoryProperties.isNeedBarCode == 0">
													<input type="radio" id="isNeedBarCode1" name="isNeedBarCode" value="1" />是
													<input type="radio" id="isNeedBarCode2" name="isNeedBarCode" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemInventoryProperties.isNeedBarCode == 1">
													<input type="radio" id="isNeedBarCode1" name="isNeedBarCode" value="1" checked="checked" />是
													<input type="radio" id="isNeedBarCode2" name="isNeedBarCode" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isNeedBarCode1" name="isNeedBarCode" value="1" />是
													<input type="radio" id="isNeedBarCode2" name="isNeedBarCode" value="0" checked="checked" />否
												</s:else></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>