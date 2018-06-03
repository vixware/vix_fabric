<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdateItemTabThree(func){
	$.post('${vix}/mdm/item/itemAction!saveOrUpdate.action',
		{'itemCostProperties.item.id':$("#id").val(),
		  'itemCostProperties.id':$("#itemCostPropertiesId").val(),
		  /** 一般信息 */
		  'itemCostProperties.priceStyle':$("#priceStyle").val(),
		  'itemCostProperties.feeRate':$("#feeRate").val(),
		  'itemCostProperties.maxCost':$("#maxCost").val(),
		  'itemCostProperties.planPrice':$("#planPrice").val(),
		  'itemCostProperties.salePrice':$("#salePrice").val(),
		  'itemCostProperties.costPrice':$("#costPrice").val(),
		  'itemCostProperties.refereceCost':$("#refereceCost").val(),
		  'itemCostProperties.newCost':$("#newCost").val(),
		  'itemCostProperties.refereceSalePrice':$("#refereceSalePrice").val(),
		  'itemCostProperties.minSalePrice':$("#minSalePrice").val(),
		  'itemCostProperties.defaultWarehouse':$("#defaultWarehouse").val(),
		  'itemCostProperties.defaultShelf':$("#defaultShelf").val(),
		  /** 成本税费率 */
		  'itemCostProperties.rebateRate':$("#rebateRate").val(),
		  'itemCostProperties.saleAddRate':$("#saleAddRate").val(),
		  'itemCostProperties.retailPrice':$("#retailPrice").val(),
		  'itemCostProperties.prepareManPower':$("#prepareManPower").val(),
		  'itemCostProperties.processManPower':$("#processManPower").val(),
		  'itemCostProperties.variableManufacturingCost':$("#variableManufacturingCost").val(),
		  'itemCostProperties.fixedManufacturingCost':$("#fixedManufacturingCost").val(),
		  'itemCostProperties.outSoucingFee':$("#outSoucingFee").val(),
		  'itemCostProperties.perMaterialCost':$("#perMaterialCost").val(),
		  'itemCostProperties.materialCost':$("#materialCost").val(),
		  /** 财务属性 */
		  'itemFinanceProperties.item.id':$("#id").val(),
		  'itemFinanceProperties.id':$("#itemFinancePropertiesId").val(),
		  'itemFinanceProperties.gernalCatalog':$("#gernalCatalog").val(),
		  'itemFinanceProperties.priceUnit':$("#priceUnit").val(),
		  'itemFinanceProperties.companyCode':$("#companyCode").val(),
		  'itemFinanceProperties.stockOrg':$("#stockOrg").val(),
		  'itemFinanceProperties.warehouseCode':$("#warehouseCode").val(),
		  'itemFinanceProperties.inStockPricingStyle':$("#inStockPricingStyle").val(),
		  'itemFinanceProperties.outStockPricingStyle':$("#outStockPricingStyle").val(),
		  'itemFinanceProperties.movingAveragePrice':$("#movingAveragePrice").val(),
		  'itemFinanceProperties.fifoPrice':$("#fifoPrice").val(),
		  'itemFinanceProperties.lifoPrice':$("#lifoPrice").val(),
		  'itemFinanceProperties.weightedAveragePrice':$("#weightedAveragePrice").val(),
		  'itemFinanceProperties.standardCost':$("#standardCost").val()
		},
		function(result){
			showMessage(result);
			func();
		}
	);
}

$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
$("#three").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="three">
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
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>财务信息</strong>
								</dt>
								<dd style="display: block;">
									<input type="hidden" id="itemCostPropertiesId" name="itemCostPropertiesId" value="${itemCostProperties.id}" /> <input type="hidden" id="itemFinancePropertiesId" name="itemFinancePropertiesId" value="${itemFinanceProperties.id}" />
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">一般数据</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">计价方式：</td>
											<td width="35%"><input id="priceStyle" name="itemCostProperties.priceStyle" value="${itemCostProperties.priceStyle}" type="text" size="30" /></td>
											<td width="10%" align="right">费用率：</td>
											<td width="40%"><input id="feeRate" name="itemCostProperties.feeRate" value="${itemCostProperties.feeRate}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">计划价格：</td>
											<td><input id="planPrice" name="itemCostProperties.planPrice" value="${itemCostProperties.planPrice}" type="text" size="30" /></td>
											<td align="right">销售价格：</td>
											<td><input id="salePrice" name="itemCostProperties.salePrice" value="${itemCostProperties.salePrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最高进价：</td>
											<td><input id="maxCost" name="itemCostProperties.maxCost" value="${itemCostProperties.maxCost}" type="text" size="30" /></td>
											<td align="right">成本价格：</td>
											<td><input id="costPrice" name="itemCostProperties.costPrice" value="${itemCostProperties.costPrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">参考成本：</td>
											<td><input id="refereceCost" name="itemCostProperties.refereceCost" value="${itemCostProperties.refereceCost}" type="text" size="30" /></td>
											<td align="right">最新成本：</td>
											<td><input id="newCost" name="itemCostProperties.newCost" value="${itemCostProperties.newCost}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">参考售价：</td>
											<td><input id="refereceSalePrice" name="itemCostProperties.refereceSalePrice" value="${itemCostProperties.refereceSalePrice}" type="text" size="30" /></td>
											<td align="right">最低售价：</td>
											<td><input id="minSalePrice" name="itemCostProperties.minSalePrice" value="${itemCostProperties.minSalePrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">默认仓库：</td>
											<td><input id="defaultWarehouse" name="itemCostProperties.defaultWarehouse" value="${itemCostProperties.defaultWarehouse}" type="text" size="30" /></td>
											<td align="right">默认库位：</td>
											<td><input id="defaultShelf" name="itemCostProperties.defaultShelf" value="${itemCostProperties.defaultShelf}" type="text" size="30" /></td>
										</tr>
									</table>
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">成本税费率</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">退税率：</td>
											<td width="35%"><input id="rebateRate" name="itemCostProperties.rebateRate" value="${itemCostProperties.rebateRate}" type="text" size="30" />% 范围(1-100)</td>
											<td width="10%" align="right">销售加成率：</td>
											<td width="40%"><input id="saleAddRate" name="itemCostProperties.saleAddRate" value="${itemCostProperties.saleAddRate}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">零售价格：</td>
											<td><input id="retailPrice" name="itemCostProperties.retailPrice" value="${itemCostProperties.retailPrice}" type="text" size="30" /></td>
											<td align="right">准备人工：</td>
											<td><input id="prepareManPower" name="itemCostProperties.prepareManPower" value="${itemCostProperties.prepareManPower}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">加工人工：</td>
											<td><input id="processManPower" name="itemCostProperties.processManPower" value="${itemCostProperties.processManPower}" type="text" size="30" /></td>
											<td align="right">变动制作费用：</td>
											<td><input id="variableManufacturingCost" name="itemCostProperties.variableManufacturingCost" value="${itemCostProperties.variableManufacturingCost}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">固定制造费用：</td>
											<td><input id="fixedManufacturingCost" name="itemCostProperties.fixedManufacturingCost" value="${itemCostProperties.fixedManufacturingCost}" type="text" size="30" /></td>
											<td align="right">外协费用：</td>
											<td><input id="outSoucingFee" name="itemCostProperties.outSoucingFee" value="${itemCostProperties.outSoucingFee}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">单位材料成本：</td>
											<td><input id="perMaterialCost" name="itemCostProperties.perMaterialCost" value="${itemCostProperties.perMaterialCost}" type="text" size="30" /></td>
											<td align="right">材料单价：</td>
											<td><input id="materialCost" name="itemCostProperties.materialCost" value="${itemCostProperties.materialCost}" type="text" size="30" /></td>
										</tr>
									</table>
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">财务属性</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">总帐分类 ：</td>
											<td width="35%"><input id="gernalCatalog" name="itemFinanceProperties.gernalCatalog" value="${itemFinanceProperties.gernalCatalog}" type="text" size="30" /></td>
											<td width="10%" align="right">价格单位：</td>
											<td width="40%"><input id="priceUnit" name="itemFinanceProperties.priceUnit" value="${itemFinanceProperties.priceUnit}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">公司标识代码：</td>
											<td><input id="companyCode" name="itemFinanceProperties.companyCode" value="${itemFinanceProperties.companyCode}" type="text" size="30" /></td>
											<td align="right">库存组织：</td>
											<td><input id="stockOrg" name="itemFinanceProperties.stockOrg" value="${itemFinanceProperties.stockOrg}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">仓库代码：</td>
											<td><input id="outStockPricingStyle" name="itemFinanceProperties.outStockPricingStyle" value="${itemFinanceProperties.outStockPricingStyle}" type="text" size="30" /></td>
											<td align="right">入库计价方式：</td>
											<td><input id="inStockPricingStyle" name="itemFinanceProperties.inStockPricingStyle" value="${itemFinanceProperties.inStockPricingStyle}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">出库计价方式 ：</td>
											<td><input id="warehouseCode" name="itemFinanceProperties.warehouseCode" value="${itemFinanceProperties.warehouseCode}" type="text" size="30" /></td>
											<td align="right">移动平均价：</td>
											<td><input id="movingAveragePrice" name="itemFinanceProperties.movingAveragePrice" value="${itemFinanceProperties.movingAveragePrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">先进先出价：</td>
											<td><input id="fifoPrice" name="itemFinanceProperties.fifoPrice" value="${itemFinanceProperties.fifoPrice}" type="text" size="30" /></td>
											<td align="right">后进先出价：</td>
											<td><input id="lifoPrice" name="itemFinanceProperties.lifoPrice" value="${itemFinanceProperties.lifoPrice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">全月平均价：</td>
											<td><input id="weightedAveragePrice" name="itemFinanceProperties.weightedAveragePrice" value="${itemFinanceProperties.weightedAveragePrice}" type="text" size="30" /></td>
											<td align="right">标准成本价 ：</td>
											<td><input id="standardCost" name="itemFinanceProperties.standardCost" value="${itemFinanceProperties.standardCost}" type="text" size="30" /></td>
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