<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryHomePageAction!goStockQueryHomePage.action');"><i class="fa fa-lg fa-fw fa-cubes"></i> <span class="menu-item-parent">库存管理</span></a>
	<ul>
		<li><a href="#" id="">基础设置</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntInventorySetAction!goList.action');">库存设置</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntWarehouseAction!goList.action');">仓库管理</a></li>
				<li><a href="#" id="" onclick="">生产批次管理</a></li>
				<li><a href="#" id="" onclick="">序列号管理</a></li>
				<li><a href="#" id="" onclick="">保质期管理</a></li>
			</ul></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntInboundWarehouseAction!goList.action');">入库管理</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntOutBoundAction!goList.action');">出库管理</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/inventory/vixntAllocateTransferAction!goList.action');">调拨管理</a></li>
		<li><a href="#" id="" onclick="loadContent('inv_vixnttakestock','${nvix}/nvixnt/vixntTakeStockAction!goList.action');">盘点管理</a></li>
		<li><a href="#" id="" onclick="loadContent('inv_vixntstandingbook','${nvix}/nvixnt/vixntStandingBookAction!goList.action');">台账管理</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntInventoryFreezeAction!goList.action');">库存冻结</a></li>
		<li><a href="#" id="" onclick="">领料管理</a></li>
		<li><a href="#" id="">库存报表</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryStatisticsAction!goStockInOutWaterAccount.action');">出入库流水账 <!-- 库存展望 --></a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryStatisticsAction!goStockInOutDepositSummary.action');">收发存汇总表</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryStatisticsAction!goStockHasDistribution.action');">存货分布表</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixntInventoryStatisticsAction!businessType.action');">业务类型汇总表</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryStatisticsAction!goStockQuotaReceiveSummary.action');">限额领料汇总表</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryStatisticsAction!goStockHasMoneyDistribution.action');">现存物料价值分布表</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryHomePageAction!goStockQueryHomePage.action');">库存仪表盘</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockQueryHomePageAction!goStockAnalysisPage.action');">库存分析</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockSerachDataAction!goinOutStoreOverviewPage.action');">进销存概览</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockSerachDataAction!goRecommendPage.action');">促销推荐</a></li>
			</ul></li>
	</ul></li>

