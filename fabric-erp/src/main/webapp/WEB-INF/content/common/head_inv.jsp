<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/inventoryManagementAction!goList.action','bg_02');">库存管理</a>
	<ul>
		<li class="fly"><a href="#">初始设置</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/inventory/optionAction!goList.action','bg_02');">选项</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inventoryManagementDictionaryAction!goList.action','bg_02');">字典管理</a></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goList.action','bg_02');">仓库管理</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goSaveOrUpdateWarehouse.action','bg_02');">新增仓库</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goSaveOrUpdateCargoSpace.action','bg_02');">新增货位</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goList.action','bg_02');">仓库列表</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/initInventoryAction!goList.action','bg_02');">期初录入</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/beginDefectiveItemAction!goList.action','bg_02');">期初不合格品</a></li>
				<li class="fly"><a href="#">保质期管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/shelfLifeAction!goList.action','bg_02');">快到期库存查询</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/expiredInventoryAction!goList.action','bg_02');">失效库存报警</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/shelfLifeEarlyWarningAction!goList.action','bg_02');">保质期预警</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="">商品拆装管理业务</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/productAssemblyAction!goList.action','bg_02');">商品组装</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/productDisassemblyAction!goList.action','bg_02');">商品拆装</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');">入库业务</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goSaveOrUpdate.action','bg_02');">采购入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goAddFinishedGoodsInbound.action','bg_02');">产成品入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goScarletLetterInbound.action','bg_02');">红字入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goAddOtherInbound.action','bg_02');">其他入库单</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goPrintOrder.action','bg_02');">单据打印</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">出库业务</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goSaveOrUpdate.action','bg_02');">销售出库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goMaterialOutBound.action','bg_02');">材料出库单</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goOtherOutBound.action','bg_02');">其他出库单</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action','bg_02');">调拨业务</a>
			<ul>
				<li class="fly_top"><a href="#">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action','bg_02');">台账业务</a></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_02');">盘点业务</a>
			<ul>
				<li class="fly_top"><a href="#">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/limitsTakeAction!goList.action','bg_02');">限额领料</a>
			<ul>
				<li class="fly_top"><a href="#">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/limitsTakeAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/reverseFlushingMaterialAction!goList.action','bg_02');">倒冲领料</a>
			<ul>
				<li class="fly_top"><a href="#">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/reverseFlushingMaterialAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/unAcceptedProductAction!goList.action','bg_02');">不合格品</a>
			<ul>
				<li class="fly_top"><a href="#">新增</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/unAcceptedProductAction!goList.action','bg_02');">列表</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/positionAdjustmentAction!goList.action','bg_02');">货位调整</a></li>
		<li class="fly"><a href="#">单据列表</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');">采购入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');">产品入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');">其他入库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">销售出库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">材料出库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');">其他出库单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/limitsTakeAction!goList.action','bg_02');">限额领料单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action','bg_02');">调拨申请单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action','bg_02');">调拨单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_02');">盘点单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/unAcceptedProductAction!goList.action','bg_02');">不合格品处理单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/positionAdjustmentAction!goList.action','bg_02');">货位调整单</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/reorderPointAction!goList.action','bg_02');">ROP采购计划</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/batchAction!goList.action','bg_02');">批次管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/batchAction!goList.action','bg_02');">批次号规则设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/batchStandingBookAction!goList.action','bg_02');">批次台账</a></li>
				<li class="fly_end"><a href="#">批次跟踪查询</a></li>
			</ul></li>
		<li class="fly"><a href="#">条形码管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/barCodeManagementAction!goList.action','bg_02');">条形码规则设置</a></li>
			</ul></li>
		<li><a href="#">RFID管理</a></li>
		<li class="fly"><a href="#">其他业务处理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/orderReservationAction!goList.action','bg_02');">订单预留</a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/itemsInTheLibraryDetectedMeterAction!goList.action','bg_02');">在库品待检表</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/collateExistingQuantityAction!goList.action','bg_02');">整理现存量</a></li>
			</ul></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/accountCheckingAction!goList.action','bg_02');">对账</a></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/monthEndingClosingAction!goList.action','bg_02');">月末结账</a></li>
		<li><a href="#" onclick="loadContent('${vix}/inventory/reorderPointAction!goList.action','bg_02');">ROP再订货点</a></li>
		<li class="fly"><a href="#">报表</a>
			<ul>
				<li class="fly_top"><a href="#">我的报表</a></li>
				<li class="fly"><a href="#">库存账</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">现存量查询</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/stockReportAction!goList.action','bg_02');">出入库流水账</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">库存台账</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">代管账</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">不合格品备查簿</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">呆滞积压备查簿</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">供货单位收发存汇总表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">入库跟踪表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">订单预留历史记录查询</a></li>
					</ul></li>
				<li class="fly"><a href="#">货位账</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">货位卡片</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">货位汇总表</a></li>
					</ul></li>
				<li class="fly"><a href="#">统计表</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">库存展望</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">收发存汇总表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">存货分布表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">业务类型汇总表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">限额领料汇总表</a></li>
					</ul></li>
				<li class="fly"><a href="#">储备分析</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">安全库存预警</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">超储存货查询</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">短缺存货查询</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">呆滞积压存货分析</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">库龄分析</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">缺料表</a></li>
					</ul></li>
				<li class="fly"><a href="#">ROP采购计划报表</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">ROP采购计划资金预算</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/guaranteePeriodAction!goList.action','bg_02');">ROP采购计划执行情况</a></li>
					</ul></li>
			</ul></li>
	</ul></li>