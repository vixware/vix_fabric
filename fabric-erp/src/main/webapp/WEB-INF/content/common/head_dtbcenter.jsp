<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<li class="fly"><a href="#" onclick="loadContent('${vix}/dtbcenter/dtbcenterAction!goList.action','bg_02');">配送中心管理</a>
	<ul>
		<li class="fly"><a href="#">设置</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/dtbcenter/dtbcenterConfigAction!goConfig.action','bg_02');">规划参数</a>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/costsSetAction!goList.action','bg_02');">费用设定</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/dtbcenter/transferFeesetupAction!goList.action','bg_02');">运费制定</a></li>
			</ul></li>
		<li class="fly"><a href="#">接单处理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/automatedQuotationAction!goList.action','bg_02');">自动报价</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/orderProcessingAction!goList.action','bg_02');">订单处理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/wayBillProcessAction!goList.action','bg_02');">托运单处理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/expressSingleAction!goList.action','bg_02');">快递单处理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/costEstimatingAction!goList.action','bg_02');">订单成本估算</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/singleStepprocessingAction!goList.action','bg_02');">退单处理</a></li>
			</ul></li>
		<li class="fly"><a href="#">经营管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/rentVehicleAction!goList.action','bg_02');">车辆设备租用</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/distributionCostAction!goList.action','bg_02');">配送成本分析</a></li>
			</ul></li>
		<li class="fly"><a href="#">配载调度管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/distributionManagementAction!goList.action','bg_02');">配送计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/deliveryRouteAction!goList.action','bg_02');">调度路线</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/vehiclePlanAction!goList.action','bg_02');">装载规划</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/dtbcenter/pickupManagementAction!goList.action','bg_02');">提货调度管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/dtbcenter/pickupBusinessHandlingAction!goList.action','bg_02');">提货业务受理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/pickupBusinessAnalysisAction!goList.action','bg_02');">提货业务分拆</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/dtbcenter/pickupDispatchSendAction!goList.action','bg_02');">提货调度派车</a></li>
			</ul></li>
		<li class="fly"><a href="#">运输管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/sentCarSingleManagementAction!goList.action','bg_02');">派车单管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/loadingManagementAction!goList.action','bg_02');">装车管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/dispatchingReceiptAction!goList.action','bg_02');">配送回执</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/shippingManagementAction!goList.action','bg_02');">托运管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/shippingManagementAction!goList.action','bg_02');">汽运管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/shippingManagementAction!goList.action','bg_02');">铁路运输管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/settlementManagementAction!goList.action','bg_02');">结算管理</a></li>
			</ul></li>
		<li class="fly"><a href="#">运输资源管理</a>
			<ul>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/yardManagementAction!goList.action','bg_02');">车场管理</a></li>
				<li class="fly"><a href="#">车务管理</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/documentManagementAction!goList.action','bg_02');">证件管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/securityProjectAction!goList.action','bg_02');">安检项目管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/securityTypeAction!goList.action','bg_02');">安检类型管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/securityRegistrationAction!goList.action','bg_02');">安检登记管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/insuranceLevelAction!goList.action','bg_02');">保险登记管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/accidentAction!goList.action','bg_02');">事故管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/claimInformationAction!goList.action','bg_02');">理赔信息管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/dtbcenter/maintenanceRecordsAction!goList.action','bg_02');">维修保养记录管理</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/driverAction!goList.action','bg_02');">人员管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/vehicleManagementAction!goList.action','bg_02');">车辆管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/dtbcenter/outercarManagementAction!goList.action','bg_02');">外车管理</a></li>
			</ul></li>
		<li class="fly"><a href="#">货物跟踪管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/dtbcenter/vehicleTrackingAction!goList.action','bg_02');">车辆跟踪</a></li>
			</ul></li>

		<li class="fly"><a href="#">客户服务管理</a>
			<ul>
				<li class="fly_top"><a href="#">网上查单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/cargoTrackingAction!goList.action','bg_02');">客户投诉</a></li>
				<li class="fly_end"><a href="#">基础资料</a></li>
			</ul></li>
		<li class="fly"><a href="#">搬家管理</a>
			<ul>
				<li class="fly"><a href="#">设置</a>
					<ul>
						<li class="fly_top"><a href="#">初定价格</a></li>
					</ul></li>
				<li><a href="#">调度单</a>
					<ul>
						<li><a href="#">新增</a></li>
						<li><a href="#">列表</a></li>
					</ul></li>
				<li><a href="#">结算清单</a></li>
			</ul></li>
		<li class="fly"><a href="#">货代管理</a>
			<ul>
				<li class="fly_top"><a href="#">托运单管理</a></li>
				<li><a href="#">计费协议管理</a></li>
				<li><a href="#">托运客户结算管理</a></li>
				<li><a href="#">承运商结算管理</a></li>
			</ul></li>
	</ul></li>
