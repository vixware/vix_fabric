<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function saveOrUpdateItemTabFour(func){
	$.post('${vix}/mdm/item/itemAction!saveOrUpdate.action',
		{'itemMRPProperties.item.id':$("#id").val(),
		  /** MRP */
		  'itemMRPProperties.id':$("#itemMRPPropertiesId").val(),
		  'itemMRPProperties.mrpType':$("#mrpType").val(),
		  'itemMRPProperties.projectDrawNumber':$("#projectDrawNumber").val(),
		  'itemMRPProperties.routeCode':$("#routeCode").val(),
		  'itemMRPProperties.reOrderPoint':$("#reOrderPoint").val(),
		  'itemMRPProperties.produceDepartment':$("#produceDepartment").val(),
		  'itemMRPProperties.planner':$("#planner").val(),
		  'itemMRPProperties.planMethod':$("#planMethod").val(),
		  'itemMRPProperties.planTimeFence':$("#planTimeFence").val(),
		  'itemMRPProperties.planPeroid':$("#planPeroid").val(),
		  'itemMRPProperties.assembleScrapRate':$("#assembleScrapRate").val(),
		  'itemMRPProperties.outPutRate':$("#outPutRate").val(),
		  'itemMRPProperties.requirementTimefence':$("#requirementTimefence").val(),
		  'itemMRPProperties.planTimefenceDays':$("#planTimefenceDays").val(),
		  'itemMRPProperties.periodType':$("#periodType").val(),
		  'itemMRPProperties.overlapDays':$("#overlapDays").val(),
		  'itemMRPProperties.maxSupplyAmount':$("#maxSupplyAmount").val(),
		  'itemMRPProperties.minSupplyAmount':$("#minSupplyAmount").val(),
		  'itemMRPProperties.supplyPolicy':$("#supplyPolicy").val(),
		  'itemMRPProperties.supplyPeriod':$("#supplyPeriod").val(),
		  'itemMRPProperties.supplyMultiple':$("#supplyMultiple").val(),
		  'itemMRPProperties.supplyType':$("#supplyType").val(),
		  'itemMRPProperties.changeBase':$("#changeBase").val(),
		  'itemMRPProperties.changeLeadTime':$("#changeLeadTime").val(),
		  'itemMRPProperties.aTPCheckType':$("#aTPCheckType").val(),
		  'itemMRPProperties.planItem':$("#planItem").val(),
		  'itemMRPProperties.conversionFactor':$("#conversionFactor").val(),
		  'itemMRPProperties.replaceDate':$("#replaceDate").val(),
		  'itemMRPProperties.safeStockMethod':$("#safeStockMethod").val(),
		  'itemMRPProperties.dynamicSafeStockMethod':$("#dynamicSafeStockMethod").val(),
		  'itemMRPProperties.overlayDays':$("#overlayDays").val(),
		  'itemMRPProperties.percent':$("#percent").val(),
		  'itemMRPProperties.bookingPolice':$("#bookingPolice").val(),
		  'itemMRPProperties.aTPRule':$("#aTPRule").val(),
		  'itemMRPProperties.lowLevelCode':$("#lowLevelCode").val(),
		  'itemMRPProperties.isCheckATP':$(":radio[name=isCheckATP][checked]").val(),
		  'itemMRPProperties.isBelongMPS':$(":radio[name=isBelongMPS][checked]").val(),
		  'itemMRPProperties.isCostRelated':$(":radio[name=isCostRelated][checked]").val(),
		  'itemMRPProperties.isRemovalMantissa':$(":radio[name=isRemovalMantissa][checked]").val(),
		  'itemMRPProperties.isProductOrderMerge':$(":radio[name=isProductOrderMerge][checked]").val(),
		  'itemMRPProperties.isRepeatPlan':$(":radio[name=isRepeatPlan][checked]").val(),
		  'itemMRPProperties.isMPSComponent':$(":radio[name=isMPSComponent][checked]").val(),
		  'itemMRPProperties.isPermitBOMParent':$(":radio[name=isPermitBOMParent][checked]").val(),
		  'itemMRPProperties.isPermitBOMChild':$(":radio[name=isPermitBOMChild][checked]").val(),
		  'itemMRPProperties.isPermitProductOrder':$(":radio[name=isPermitProductOrder][checked]").val(),
		  'itemMRPProperties.isSaleTrace':$(":radio[name=isSaleTrace][checked]").val(),
		  //批量数据
		  'itemMRPProperties.batchMinAmount':$("#batchMinAmount").val(),
		  'itemMRPProperties.batchMaxAmount':$("#batchMaxAmount").val(),
		  'itemMRPProperties.batchFixedAmount':$("#batchFixedAmount").val(),
		  'itemMRPProperties.batchMaxInventory2':$("#batchMaxInventory2").val(),
		  'itemMRPProperties.batchOrderCost':$("#batchOrderCost").val(),
		  'itemMRPProperties.batchAssembleScrap':$("#batchAssembleScrap").val(),
		  'itemMRPProperties.batchPeriodTime':$("#batchPeriodTime").val(),
		  'itemMRPProperties.batchRoundedValue':$("#batchRoundedValue").val(),
		  'itemMRPProperties.batchUnitGroup':$("#batchUnitGroup").val(),
		  //其他数据
		  'itemMRPProperties.purType':$("#purType").val(),
		  'itemMRPProperties.purRecoil':$("#purRecoil").val(),
		  'itemMRPProperties.purJITDelivery':$("#purJITDelivery").val(),
		  'itemMRPProperties.planTakeDeliveryTime':$("#planTakeDeliveryTime").val(),
		  'itemMRPProperties.planDeliveryTime':$("#planDeliveryTime").val(),
		  'itemMRPProperties.planCalendar':$("#planCalendar").val(),
		  /** 计划 */
		  'itemPlanProperties.item.id':$("#id").val(),
		  'itemPlanProperties.id':$("#itemPlanPropertiesId").val(),
		  'itemPlanProperties.ropBatchRule':$("#ropBatchRule").val(),
		  'itemPlanProperties.promiseSupplyDay':$("#promiseSupplyDay").val(),
		  'itemPlanProperties.prescriptSupplyAmount':$("#prescriptSupplyAmount").val(),
		  'itemPlanProperties.maxBookingAmount':$("#maxBookingAmount").val(),
		  /** 质量 */
		  'itemQualityProperties.item.id':$("#id").val(),
		  'itemQualityProperties.id':$("#itemQualityPropertiesId").val(),
		  'itemQualityProperties.qualityRequirement':$("#qualityRequirement").val(),
		  'itemQualityProperties.qualityMethod':$("#qualityMethod").val(),
		  'itemQualityProperties.qualityCycle':$("#qualityCycle").val(),
		  'itemQualityProperties.qualityCycleDays':$("#qualityCycleDays").val(),
		  'itemQualityProperties.qualityDescription':$("#qualityDescription").val(),
		  'itemQualityProperties.isInStockQuality':$(":radio[name=isInStockQuality][checked]").val(),
		  'itemQualityProperties.isOutStockQuality':$(":radio[name=isOutStockQuality][checked]").val(),
		  'itemQualityProperties.isReturnQuality':$(":radio[name=isReturnQuality][checked]").val(),
		  'itemQualityProperties.spotCheck':$("#spotCheck").val(),
		  'itemQualityProperties.spotCheckRate':$("#spotCheckRate").val(),
		  'itemQualityProperties.spotCheckAmount':$("#spotCheckAmount").val(),
		  'itemQualityProperties.yieldRate':$("#yieldRate").val(),
		  'itemQualityProperties.checkClass':$("#checkClass").val(),
		  'itemQualityProperties.isBackCheck':$(":radio[name=isBackCheck][checked]").val(),
		  'itemQualityProperties.checkStandart':$("#checkStandart").val(),
		  'itemQualityProperties.averageAQL':$("#averageAQL").val()
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
$("#four").validationEngine();
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="four">
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
									<b></b> <strong>MRP信息</strong>
								</dt>
								<dd style="display: block;">
									<input type="hidden" id="itemMRPPropertiesId" name="itemMRPPropertiesId" value="${itemMRPProperties.id}" /> <input type="hidden" id="itemPlanPropertiesId" name="itemPlanPropertiesId" value="${itemPlanProperties.id}" /> <input type="hidden" id="itemQualityPropertiesId" name="itemQualityPropertiesId" value="${itemQualityProperties.id}" />
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">控制属性</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">MRP类型：</td>
											<td width="35%"><input id="mrpType" name="itemMRPProperties.mrpType" value="${itemMRPProperties.mrpType}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="10%" align="right">${vv:varView('vix_mdm_item')}低阶码:</td>
											<td width="40%"><input id="lowLevelCode" name="itemMRPProperties.lowLevelCode" value="${itemMRPProperties.lowLevelCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">工程图号：</td>
											<td><input id="projectDrawNumber" name="itemMRPProperties.projectDrawNumber" value="${itemMRPProperties.projectDrawNumber}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">工艺路线代码：</td>
											<td><input id="routeCode" name="itemMRPProperties.routeCode" value="${itemMRPProperties.routeCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">生产部门：</td>
											<td><input id="produceDepartment" name="itemMRPProperties.produceDepartment" value="${itemMRPProperties.produceDepartment}" type="text" size="30" /></td>
											<td align="right">计划员：</td>
											<td><input id="planner" name="itemMRPProperties.planner" value="${itemMRPProperties.planner}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划方法：</td>
											<td><input id="planMethod" name="itemMRPProperties.planMethod" value="${itemMRPProperties.planMethod}" type="text" size="30" /></td>
											<td align="right">计划时界：</td>
											<td><input id="planTimeFence" name="itemMRPProperties.planner" value="${itemMRPProperties.planner}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划周期：</td>
											<td><input id="planPeroid" name="itemMRPProperties.planPeroid" value="${itemMRPProperties.planPeroid}" type="text" size="30" /></td>
											<td align="right">装配报废率：</td>
											<td><input id="assembleScrapRate" name="itemMRPProperties.assembleScrapRate" value="${itemMRPProperties.assembleScrapRate}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">产出率：</td>
											<td><input id="outPutRate" name="itemMRPProperties.outPutRate" value="${itemMRPProperties.outPutRate}" type="text" size="30" />% 范围(1-100)</td>
											<td align="right">需求时栅：</td>
											<td><input id="requirementTimefence" name="itemMRPProperties.requirementTimefence" value="${itemMRPProperties.requirementTimefence}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划时栅天数：</td>
											<td><input id="planTimefenceDays" name="itemMRPProperties.planTimefenceDays" value="${itemMRPProperties.planTimefenceDays}" type="text" size="30" /></td>
											<td align="right">期间数：</td>
											<td><input id="periodType" name="itemMRPProperties.periodType" value="${itemMRPProperties.periodType}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">重叠天数：</td>
											<td><input id="overlapDays" name="itemMRPProperties.overlapDays" value="${itemMRPProperties.overlapDays}" type="text" size="30" /></td>
											<td align="right">最高供应量：</td>
											<td><input id="maxSupplyAmount" name="itemMRPProperties.maxSupplyAmount" value="${itemMRPProperties.maxSupplyAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">最低供应量：</td>
											<td><input id="minSupplyAmount" name="itemMRPProperties.minSupplyAmount" value="${itemMRPProperties.minSupplyAmount}" type="text" size="30" /></td>
											<td align="right">供需政策：</td>
											<td><input id="supplyPolicy" name="itemMRPProperties.supplyPolicy" value="${itemMRPProperties.supplyPolicy}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供应期间：</td>
											<td><input id="supplyPeriod" name="itemMRPProperties.supplyPeriod" value="${itemMRPProperties.supplyPeriod}" type="text" size="30" /></td>
											<td align="right">供应倍数：</td>
											<td><input id="supplyMultiple" name="itemMRPProperties.supplyMultiple" value="${itemMRPProperties.supplyMultiple}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供应类型：</td>
											<td><input id="supplyType" name="itemMRPProperties.supplyType" value="${itemMRPProperties.supplyType}" type="text" size="30" /></td>
											<td align="right">变动基数：</td>
											<td><input id="changeBase" name="itemMRPProperties.changeBase" value="${itemMRPProperties.changeBase}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">变动提前期：</td>
											<td><input id="changeLeadTime" name="itemMRPProperties.changeLeadTime" value="${itemMRPProperties.changeLeadTime}" type="text" size="30" /></td>
											<td align="right">可用性检查方式：</td>
											<td><input id="aTPCheckType" name="itemMRPProperties.aTPCheckType" value="${itemMRPProperties.aTPCheckType}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划品：</td>
											<td><input id="planItem" name="itemMRPProperties.planItem" value="${itemMRPProperties.planItem}" type="text" size="30" /></td>
											<td align="right">转换因子：</td>
											<td><input id="conversionFactor" name="itemMRPProperties.conversionFactor" value="${itemMRPProperties.conversionFactor}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">替换日期：</td>
											<td><input id="replaceDate" name="itemMRPProperties.replaceDate" value="${itemMRPProperties.replaceDate}" type="text" size="30" /></td>
											<td align="right">安全库存方法：</td>
											<td><input id="safeStockMethod" name="itemMRPProperties.safeStockMethod" value="${itemMRPProperties.safeStockMethod}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">动态安全库存方法：</td>
											<td><input id="dynamicSafeStockMethod" name="itemMRPProperties.dynamicSafeStockMethod" value="${itemMRPProperties.dynamicSafeStockMethod}" type="text" size="30" /></td>
											<td align="right">覆盖天数：</td>
											<td><input id="overlayDays" name="itemMRPProperties.overlayDays" value="${itemMRPProperties.overlayDays}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">百分比：</td>
											<td><input id="percent" name="itemMRPProperties.percent" value="${itemMRPProperties.percent}" type="text" size="30" /></td>
											<td align="right">订购策略：</td>
											<td><input id="bookingPolice" name="itemMRPProperties.bookingPolice" value="${itemMRPProperties.bookingPolice}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">ATP规则：</td>
											<td><input id="aTPRule" name="itemMRPProperties.aTPRule" value="${itemMRPProperties.aTPRule}" type="text" size="30" /></td>
											<td align="right">重订货点：</td>
											<td><input id="reOrderPoint" name="itemMRPProperties.reOrderPoint" value="${itemMRPProperties.reOrderPoint}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否属于主生产计划：</td>
											<td><s:if test="itemMRPProperties.isBelongMPS == 0">
													<input type="radio" id="isBelongMPS1" name="isBelongMPS" value="1" />是
													<input type="radio" id="isBelongMPS2" name="isBelongMPS" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isBelongMPS == 1">
													<input type="radio" id="isBelongMPS1" name="isBelongMPS" value="1" checked="checked" />是
													<input type="radio" id="isBelongMPS2" name="isBelongMPS" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isBelongMPS1" name="isBelongMPS" value="1" />是
													<input type="radio" id="isBelongMPS2" name="isBelongMPS" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否成本相关：</td>
											<td><s:if test="itemMRPProperties.isCostRelated == 0">
													<input type="radio" id="isCostRelated1" name="isCostRelated" value="1" />是
													<input type="radio" id="isCostRelated2" name="isCostRelated" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isCostRelated == 1">
													<input type="radio" id="isCostRelated1" name="isCostRelated" value="1" checked="checked" />是
													<input type="radio" id="isCostRelated2" name="isCostRelated" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isCostRelated1" name="isCostRelated" value="1" />是
													<input type="radio" id="isCostRelated2" name="isCostRelated" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否切除尾数：</td>
											<td><s:if test="itemMRPProperties.isRemovalMantissa == 0">
													<input type="radio" id="isRemovalMantissa1" name="isRemovalMantissa" value="1" />是
													<input type="radio" id="isRemovalMantissa2" name="isRemovalMantissa" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isRemovalMantissa == 1">
													<input type="radio" id="isRemovalMantissa1" name="isRemovalMantissa" value="1" checked="checked" />是
													<input type="radio" id="isRemovalMantissa2" name="isRemovalMantissa" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isRemovalMantissa1" name="isRemovalMantissa" value="1" />是
													<input type="radio" id="isRemovalMantissa2" name="isRemovalMantissa" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否令单合并：</td>
											<td><s:if test="itemMRPProperties.isProductOrderMerge == 0">
													<input type="radio" id="isProductOrderMerge1" name="isProductOrderMerge" value="1" />是
													<input type="radio" id="isProductOrderMerge2" name="isProductOrderMerge" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isProductOrderMerge == 1">
													<input type="radio" id="isProductOrderMerge1" name="isProductOrderMerge" value="1" checked="checked" />是
													<input type="radio" id="isProductOrderMerge2" name="isProductOrderMerge" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isProductOrderMerge1" name="isProductOrderMerge" value="1" />是
													<input type="radio" id="isProductOrderMerge2" name="isProductOrderMerge" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否为重复计划：</td>
											<td><s:if test="itemMRPProperties.isRepeatPlan == 0">
													<input type="radio" id="isRepeatPlan1" name="isRepeatPlan" value="1" />是
													<input type="radio" id="isRepeatPlan2" name="isRepeatPlan" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isRepeatPlan == 1">
													<input type="radio" id="isRepeatPlan1" name="isRepeatPlan" value="1" checked="checked" />是
													<input type="radio" id="isRepeatPlan2" name="isRepeatPlan" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isRepeatPlan1" name="isRepeatPlan" value="1" />是
													<input type="radio" id="isRepeatPlan2" name="isRepeatPlan" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否MPS件：</td>
											<td><s:if test="itemMRPProperties.isMPSComponent == 0">
													<input type="radio" id="isMPSComponent1" name="isMPSComponent" value="1" />是
													<input type="radio" id="isMPSComponent2" name="isMPSComponent" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isMPSComponent == 1">
													<input type="radio" id="isMPSComponent1" name="isMPSComponent" value="1" checked="checked" />是
													<input type="radio" id="isMPSComponent2" name="isMPSComponent" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isMPSComponent1" name="isMPSComponent" value="1" />是
													<input type="radio" id="isMPSComponent2" name="isMPSComponent" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否允许BOM母件：</td>
											<td><s:if test="itemMRPProperties.isPermitBOMParent == 0">
													<input type="radio" id="isPermitBOMParent1" name="isPermitBOMParent" value="1" />是
													<input type="radio" id="isPermitBOMParent2" name="isPermitBOMParent" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isPermitBOMParent == 1">
													<input type="radio" id="isPermitBOMParent1" name="isPermitBOMParent" value="1" checked="checked" />是
													<input type="radio" id="isPermitBOMParent2" name="isPermitBOMParent" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isPermitBOMParent1" name="isPermitBOMParent" value="1" />是
													<input type="radio" id="isPermitBOMParent2" name="isPermitBOMParent" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否允许BOM子件：</td>
											<td><s:if test="itemMRPProperties.isPermitBOMChild == 0">
													<input type="radio" id="isPermitBOMChild1" name="isPermitBOMChild" value="1" />是
													<input type="radio" id="isPermitBOMChild2" name="isPermitBOMChild" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isPermitBOMChild == 1">
													<input type="radio" id="isPermitBOMChild1" name="isPermitBOMChild" value="1" checked="checked" />是
													<input type="radio" id="isPermitBOMChild2" name="isPermitBOMChild" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isPermitBOMChild1" name="isPermitBOMChild" value="1" />是
													<input type="radio" id="isPermitBOMChild2" name="isPermitBOMChild" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否允许生产订单：</td>
											<td><s:if test="itemMRPProperties.isPermitProductOrder == 0">
													<input type="radio" id="isPermitProductOrder1" name="isPermitProductOrder" value="1" />是
													<input type="radio" id="isPermitProductOrder2" name="isPermitProductOrder" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isPermitProductOrder == 1">
													<input type="radio" id="isPermitProductOrder1" name="isPermitProductOrder" value="1" checked="checked" />是
													<input type="radio" id="isPermitProductOrder2" name="isPermitProductOrder" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isPermitProductOrder1" name="isPermitProductOrder" value="1" />是
													<input type="radio" id="isPermitProductOrder2" name="isPermitProductOrder" value="0" checked="checked" />否
												</s:else></td>
											<td align="right">是否为销售跟单：</td>
											<td><s:if test="itemMRPProperties.isSaleTrace == 0">
													<input type="radio" id="isSaleTrace1" name="isSaleTrace" value="1" />是
													<input type="radio" id="isSaleTrace2" name="isSaleTrace" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isSaleTrace == 1">
													<input type="radio" id="isSaleTrace1" name="isSaleTrace" value="1" checked="checked" />是
													<input type="radio" id="isSaleTrace2" name="isSaleTrace" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isSaleTrace1" name="isSaleTrace" value="1" />是
													<input type="radio" id="isSaleTrace2" name="isSaleTrace" value="0" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否检查ATP：</td>
											<td colspan="3"><s:if test="itemMRPProperties.isCheckATP == 0">
													<input type="radio" id="isCheckATP1" name="isCheckATP" value="1" />是
													<input type="radio" id="isCheckATP2" name="isCheckATP" value="0" checked="checked" />否
												</s:if> <s:elseif test="itemMRPProperties.isCheckATP == 1">
													<input type="radio" id="isCheckATP1" name="isCheckATP" value="1" checked="checked" />是
													<input type="radio" id="isCheckATP2" name="isCheckATP" value="0" />否
												</s:elseif> <s:else>
													<input type="radio" id="isCheckATP1" name="isCheckATP" value="1" />是
													<input type="radio" id="isCheckATP2" name="isCheckATP" value="0" checked="checked" />否
												</s:else></td>
										</tr>
									</table>
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">批量数据</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td align="right">最小批量大小：</td>
											<td><input id="batchMinAmount" name="itemMRPProperties.batchMinAmount" value="${itemMRPProperties.batchMinAmount}" type="text" size="30" /></td>
											<td align="right">最大批量大小：</td>
											<td><input id="batchMaxAmount" name="itemMRPProperties.batchMaxAmount" value="${itemMRPProperties.batchMaxAmount}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">固定批量大小：</td>
											<td><input id="batchFixedAmount" name="itemMRPProperties.batchFixedAmount" value="${itemMRPProperties.batchFixedAmount}" type="text" size="30" /></td>
											<td align="right">最大库存水平：</td>
											<td><input id="batchMaxInventory2" name="itemMRPProperties.batchMaxInventory2" value="${itemMRPProperties.batchMaxInventory2}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">订购成本：</td>
											<td><input id="batchOrderCost" name="itemMRPProperties.batchOrderCost" value="${itemMRPProperties.batchOrderCost}" type="text" size="30" /></td>
											<td align="right">装配报废率：</td>
											<td><input id="batchAssembleScrap" name="itemMRPProperties.batchAssembleScrap" value="${itemMRPProperties.batchAssembleScrap}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">间隔时间：</td>
											<td><input id="batchPeriodTime" name="itemMRPProperties.batchPeriodTime" value="${itemMRPProperties.batchPeriodTime}" type="text" size="30" /></td>
											<td align="right">舍入值：</td>
											<td><input id="batchRoundedValue" name="itemMRPProperties.batchRoundedValue" value="${itemMRPProperties.batchRoundedValue}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计量单位组：</td>
											<td colspan="3"><input id="batchUnitGroup" name="itemMRPProperties.batchUnitGroup" value="${itemMRPProperties.batchUnitGroup}" type="text" size="30" /></td>
										</tr>
									</table>
									<div style="background-color: #F3E2FA; margin-top: -5px;">
										<h3 style="margin-left: 20px;">其他数据</h3>
									</div>
									<table style="border: none;">
										<tr>
											<td align="right">采购类型：</td>
											<td><input id="purType" name="itemMRPProperties.purType" value="${itemMRPProperties.purType}" type="text" size="30" /></td>
											<td align="right">采购反冲：</td>
											<td><input id="purRecoil" name="itemMRPProperties.purRecoil" value="${itemMRPProperties.purRecoil}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购JIT交货：</td>
											<td><input id="purJITDelivery" name="itemMRPProperties.purJITDelivery" value="${itemMRPProperties.purJITDelivery}" type="text" size="30" /></td>
											<td align="right">计划收货处理时间：</td>
											<td><input id="planTakeDeliveryTime" name="itemMRPProperties.planTakeDeliveryTime" value="${itemMRPProperties.planTakeDeliveryTime}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划交货时间：</td>
											<td><input id="planDeliveryTime" name="itemMRPProperties.planDeliveryTime" value="${itemMRPProperties.planDeliveryTime}" type="text" size="30" /></td>
											<td align="right">计划日历：</td>
											<td><input id="planCalendar" name="itemMRPProperties.planCalendar" value="${itemMRPProperties.planCalendar}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>计划信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">ROP批量规则 ：</td>
											<td width="35%"><input id="ropBatchRule" name="itemPlanProperties.ropBatchRule" value="${itemPlanProperties.ropBatchRule}" type="text" size="30" /></td>
											<td width="10%" align="right">保证供应天数：</td>
											<td width="40%"><input id="promiseSupplyDay" name="itemPlanProperties.promiseSupplyDay" value="${itemPlanProperties.promiseSupplyDay}" type="text" size="30" /></td>
										</tr>

										<tr>
											<td align="right">规定供应量：</td>
											<td><input id="prescriptSupplyAmount" name="itemPlanProperties.prescriptSupplyAmount" value="${itemPlanProperties.prescriptSupplyAmount}" type="text" size="30" /></td>
											<td align="right">最大订购量：</td>
											<td><input id="maxBookingAmount" name="itemPlanProperties.maxBookingAmount" value="${itemPlanProperties.maxBookingAmount}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>质量信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">质量要求：</td>
											<td width="35%"><input id="qualityRequirement" name="itemQualityProperties.qualityRequirement" value="${itemQualityProperties.qualityRequirement}" type="text" size="30" /></td>
											<td width="10%" align="right">质量检验方法：</td>
											<td width="40%"><input id="qualityMethod" name="itemQualityProperties.qualityMethod" value="${itemQualityProperties.qualityMethod}" type="text" size="30" size="30" /></td>
										</tr>
										<tr>
											<td align="right">质检周期：</td>
											<td><input id="qualityCycle" name="itemQualityProperties.qualityCycle" value="${itemQualityProperties.qualityCycle}" type="text" size="30" /></td>
											<td align="right">质检周期天数：</td>
											<td><input id="qualityCycleDays" name="itemQualityProperties.qualityCycleDays" value="${itemQualityProperties.qualityCycleDays}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">检验说明：</td>
											<td><input id="qualityDescription" name="itemQualityProperties.qualityDescription" value="${itemQualityProperties.qualityDescription}" type="text" size="30" /></td>
											<td align="right">是否到货入库质检：</td>
											<td><s:if test="itemQualityProperties.isInStockQuality == 'N'">
													<input type="radio" id="isInStockQuality1" name="isInStockQuality" value="Y" />是
													<input type="radio" id="isInStockQuality2" name="isInStockQuality" value="N" checked="checked" />否
												</s:if> <s:elseif test="itemQualityProperties.isInStockQuality == 'Y'">
													<input type="radio" id="isInStockQuality1" name="isInStockQuality" value="Y" checked="checked" />是
													<input type="radio" id="isInStockQuality2" name="isInStockQuality" value="N" />否
												</s:elseif> <s:else>
													<input type="radio" id="isInStockQuality1" name="isInStockQuality" value="Y" />是
													<input type="radio" id="isInStockQuality2" name="isInStockQuality" value="N" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">是否出库质检：</td>
											<td><s:if test="itemQualityProperties.isOutStockQuality == 'N'">
													<input type="radio" id="isOutStockQuality1" name="isOutStockQuality" value="Y" />是
													<input type="radio" id="isOutStockQuality2" name="isOutStockQuality" value="N" checked="checked" />否
												</s:if> <s:elseif test="itemQualityProperties.isOutStockQuality == 'Y'">
													<input type="radio" id="isOutStockQuality1" name="isOutStockQuality" value="Y" checked="checked" />是
													<input type="radio" id="isOutStockQuality2" name="isOutStockQuality" value="N" />否
												</s:elseif> <s:else>
													<input type="radio" id="isOutStockQuality1" name="isOutStockQuality" value="Y" />是
													<input type="radio" id="isOutStockQuality2" name="isOutStockQuality" value="N" checked="checked" />否
												</s:else></td>
											<td align="right">是否退货质检：</td>
											<td><s:if test="itemQualityProperties.isReturnQuality == 'N'">
													<input type="radio" id="isReturnQuality1" name="isReturnQuality" value="Y" />是
													<input type="radio" id="isReturnQuality2" name="isReturnQuality" value="N" checked="checked" />否
												</s:if> <s:elseif test="itemQualityProperties.isReturnQuality == 'Y'">
													<input type="radio" id="isReturnQuality1" name="isReturnQuality" value="Y" checked="checked" />是
													<input type="radio" id="isReturnQuality2" name="isReturnQuality" value="N" />否
												</s:elseif> <s:else>
													<input type="radio" id="isReturnQuality1" name="isReturnQuality" value="Y" />是
													<input type="radio" id="isReturnQuality2" name="isReturnQuality" value="N" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">抽检方案：</td>
											<td><input id="spotCheck" name="itemQualityProperties.spotCheck" value="${itemQualityProperties.spotCheck}" type="text" size="30" /></td>
											<td align="right">抽检率：</td>
											<td><input id="spotCheckRate" name="itemQualityProperties.spotCheckRate" value="${itemQualityProperties.spotCheckRate}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">抽检量：</td>
											<td><input id="spotCheckAmount" name="itemQualityProperties.spotCheckAmount" value="${itemQualityProperties.spotCheckAmount}" type="text" size="30" /></td>
											<td align="right">良品率：</td>
											<td><input id="yieldRate" name="itemQualityProperties.yieldRate" value="${itemQualityProperties.yieldRate}" type="text" size="30" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">检验等级：</td>
											<td><input id="checkClass" name="itemQualityProperties.checkClass" value="${itemQualityProperties.checkClass}" type="text" size="30" /></td>
											<td align="right">是否批次取样：</td>
											<td><s:if test="itemQualityProperties.isBackCheck == 'N'">
													<input type="radio" id="isBackCheck1" name="isBackCheck" value="Y" />是
													<input type="radio" id="isBackCheck2" name="isBackCheck" value="N" checked="checked" />否
												</s:if> <s:elseif test="itemQualityProperties.isBackCheck == 'Y'">
													<input type="radio" id="isBackCheck1" name="isBackCheck" value="Y" checked="checked" />是
													<input type="radio" id="isBackCheck2" name="isBackCheck" value="N" />否
												</s:elseif> <s:else>
													<input type="radio" id="isBackCheck1" name="isBackCheck" value="Y" />是
													<input type="radio" id="isBackCheck2" name="isBackCheck" value="N" checked="checked" />否
												</s:else></td>
										</tr>
										<tr>
											<td align="right">取样标准：</td>
											<td><input id="checkStandart" name="itemQualityProperties.checkStandart" value="${itemQualityProperties.checkStandart}" type="text" size="30" /></td>
											<td align="right">允许水平AQL：</td>
											<td><input id="averageAQL" name="itemQualityProperties.averageAQL" value="${itemQualityProperties.averageAQL}" type="text" size="30" /></td>
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