<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a href="#"><i class="fa fa-lg fa-fw fa-truck"></i> <span class="menu-item-parent">供应商管理</span></a>
	<ul>
		<li><a id="" href="#"><i class="fa fa-fw icon-iconfont-config"></i>基础数据管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierCategoryAction!goList.action');">供应商分类</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierLevelAction!goList.action');">供应商等级</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierAction!goSourceList.action');">供应商寻源</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierAction!goBuildingList.action');">供应商建档</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierAction!goAuditingList.action');">供应商预选与评估</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierAction!goList.action');">供应商清单</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixntRoleAction!goSupplierRole.action');">角色管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierEmployeeAction!goList.action');">供应商员工管理</a></li>
				<%-- <li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierAccountAction!goList.action');">供应商账号</a></li> --%>
			</ul>
		</li>
		<li><a id="" href="#">业务管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierContractAction!goList.action');">框架合作协议</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierSalesOrderAction!goList.action');">供应商订单管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierItemAction!goList.action');">供应商商品管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierStandingBookAction!goList.action');">供应商商品库存</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntVehicleAction!goList.action');">车辆管理</a></li>
			</ul>
		</li>
		<li><a id="" href="#">统计管理</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntOrderAction!goSupplierList.action');">门店销售记录</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierViewDataAction!goSupplierView.action');">供应商统计报表</a></li>
			</ul>
		</li>
	</ul></li>