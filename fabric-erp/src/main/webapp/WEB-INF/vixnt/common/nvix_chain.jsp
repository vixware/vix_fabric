<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#"><i class="fa fa-lg fa-fw fa-share-alt"></i> <span class="menu-item-parent">连锁管理</span></a>
	<ul>
		<li><a id="" href="#"><i class="fa fa-fw icon-iconfont-config"></i>基础数据管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntDistributionSystemAction!goList.action');"><i class="fa fa-fw icon-iconfont-shop"></i> 连锁店铺管理 </a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntAloneShopAction!goList.action');"> <i class="fa fa-fw icon-iconfont-shop"></i>独立店铺管理
				</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixntRoleAction!goShopRoleList.action');"><i class="fa fa-fw icon-iconfont-role"></i>门店角色管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntAllocationItemAction!goList.action');"><i class="fa fa-fw icon-iconfont-inventoryStock"></i>商品库管理</a></li>
			</ul></li>
		<li><a id="" href="#"><i class="fa fa-fw icon-iconfont-order"></i>业务管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSalesOrderAction!goList.action');"><i class="fa fa-fw icon-iconfont-order"></i>订单管理</a></li>
			</ul></li>
		<li><a id="" href="#"><i class="fa fa-fw icon-iconfont-statistic"></i>统计管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStockRecordLinesStatisticsAction!goAllShopList.action');"><i class="fa fa-fw icon-iconfont-stockOutAndIn"></i>门店出入库流水账</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntStoreSalesStatisticsAction!goAllSalesList.action');"><i class="fa fa-sort-numeric-asc"></i>门店销售额排名</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntInAndOutStatisticsAction!goList.action');"><i class="fa fa-lg fa-list-alt"></i>门店出入库统计表</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/orderDetailStatisticsAction!goList.action');"><i class="fa fa-lg fa-list-alt"></i>门店销售统计表</a></li>
			</ul></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntRequirementAction!goList.action');"><i class=""></i>新品需求</a></li>
	</ul></li>
<li><a href="#"><i class="fa fa-lg fa-fw fa-institution"></i> <span class="menu-item-parent">门店管理</span></a>
	<ul>
		<li><a href="#" id="" onclick="loadContent('drp_storerequiregoods','${nvix}/nvixnt/vixntStoreRequireGoodsAction!goList.action');"><i class="fa fa-lg fa-fw fa-institution"></i>门店订单管理</a></li>
		<li><a id="" href="#"><i class="fa fa-fw icon-iconfont-config"></i>基础数据管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('drp_storeperson','${nvix}/nvixnt/vixntStorePersonAction!goList.action');"><i class="fa fa-fw icon-iconfont-employee"></i>门店人员管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntPercentageAction!goList.action');">提成管理</a></li>
			</ul></li>
		<li><a id="" href="#">业务管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('drp_warehouse','${nvix}/nvixnt/vixntDrpWarehouseAction!goList.action');">门店仓库管理</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_storeitem','${nvix}/nvixnt/vixntAllocationItemAction!goStoreItemList.action');">门店商品管理</a></li>
				<li><a href="#" id="" onclick="loadContent('inv_storeinboundwarehouse','${nvix}/nvixnt/vixntStoreInboundWarehouseAction!goList.action');">门店入库管理</a></li>
				<li><a href="#" id="" onclick="loadContent('inv_shopoutbound','${nvix}/nvixnt/vixntShopOutBoundAction!goList.action');">门店出库管理</a></li>
				<li><a href="#" id="" onclick="loadContent('inv_vixnttakestock','${nvix}/nvixnt/vixntTakeStockAction!goList.action');">门店盘点管理</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_storegoods','${nvix}/nvixnt/vixntStoreGoodsAction!goList.action');">门店库存台账管理</a></li>
			</ul></li>
		<li><a id="" href="#">数据统计</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('drp_dobusinesssurveys','${nvix}/nvixnt/vixntDoBusinessDataAction!goDoBusinessSurveyView.action');">门店营业统计</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_dobusinesssurvey','${nvix}/nvixnt/vixntDoBusinessDataAction!goDoBusinessView.action');">门店营业概况</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_storeorder','${nvix}/nvixnt/vixntOrderAction!goList.action');">门店销售记录</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_inventorystatistics','${nvix}/nvixnt/vixntStockViewDataAction!goStockView.action');">门店库存报表</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_stockrecordlinesstatistics','${nvix}/nvixnt/vixntStockRecordLinesStatisticsAction!goList.action');">门店出入库流水账</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_storesalesstatistics','${nvix}/nvixnt/vixntStockViewDataAction!goSaleView.action');">门店销售统计</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_requiregoodswastage','${nvix}/nvixnt/vixntRequireGoodsWastageAction!goList.action');">门店要货耗损统计</a></li>
				<li><a href="#" id="" onclick="loadContent('drp_storesalesmargin','${nvix}/nvixnt/vixntStoreSalesMarginAction!goList.action');">门店商品毛利统计</a></li>
			</ul></li>
	</ul></li>

