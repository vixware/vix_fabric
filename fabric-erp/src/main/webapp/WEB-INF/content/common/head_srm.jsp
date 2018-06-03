<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<li class="fly"><a href="#">供应商关系管理</a>
	<ul>
		<li class="fly" icon="${vix}/common/img/drp_channel.png"><a href="#">基本设置</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/srm/directoryManagementAction!goList.action','bg_02');">产品目录管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/contractTemplatesAction!goList.action','bg_02');">合同模版</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/tenderSetAction!goListContent.action','bg_02');">招标设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/supplierCategoryAction!goList.action','bg_02');">分类设置</a>
				<li><a href="#" onclick="loadContent('${vix}/srm/tempAction!goAssessmentManagement.action','bg_02');">评估项管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/parameterSetAction!goListContent.action','bg_02');">参数设置</a></li>
			</ul></li>
		<li class="fly"><a href="#">供应商与商业伙伴管理</a>
			<ul>
				<li class="fly_top" icon="${vix}/common/img/srm_supplier.png"><a id="qk_pur_supplier" href="#" onclick="loadContent('${vix}/srm/managementBusinessPartnerAction!goList.action','bg_02');">供应商清单</a></li>
				<li icon="${vix}/common/img/srm_searchSupplier.png"><a href="#" onclick="loadContent('${vix}/srm/searchSupplierAction!goList.action','bg_02');">供应商寻源</a></li>
				<li icon="${vix}/common/img/srm_supplier.png"><a href="#" onclick="loadContent('${vix}/srm/supplierFileAction!goList.action','bg_02');">供应商建档</a></li>
				<li icon="${vix}/common/img/srm_supplier.png"><a href="#" onclick="loadContent('${vix}/srm/supplierEvaluationAction!goList.action','bg_02');">供应商预选与评估</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/tempAction!goCooperativeAgreementsList.action','bg_02');">框架合作协议</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/supplierEmployeeAction!goList.action','bg_02');">供应商员工</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/supplierAccountAction!goList.action','bg_02');">供应商帐号</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/querySupplierAction!goList.action','bg_02');">查询</a></li>
			</ul></li>
		<li class="fly"><a href="#">采购管理</a>
			<ul>
				<li icon="${vix}/common/img/pur_apply.png" class="fly_top" icon="${vix}/common/img/pur_apply.png"><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_02');">采购申请</a></li>
				<li><a href="#">采购审批</a></li>
				<li icon="${vix}/common/img/pur_inquire.png" class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInquireAction!goList.action','bg_02');">采购询价</a>
					<ul>
						<li><a href="#">报价邀请</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goSaveOrUpdate.action','bg_02');">合同管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseInvoiceAction!goList.action','bg_02');">发票管理</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_02');">到货管理</a></li>
			</ul></li>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goList.action','bg_02');">招标管理</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goSaveOrUpdate2.action','bg_02');">创建普通招标</a></li>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goSaveOrUpdate.action','bg_02');">创建项目包招标</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/invitingTenderAction!goList.action','bg_02');">发布招标邀请</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/supplierTenderAction!goList.action','bg_02');">投标管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/reviewTenderAction!goList.action','bg_02');">评标管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/quoteMonitorAction!goList.action','bg_02');">供应商实时报价监控</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/expertAction!goList.action','bg_02');">专家管理</a></li>
			</ul></li>
		<li class="fly"><a href="#">供应商门户</a>
			<ul>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/srm/registeredSupplierAction!goSaveOrUpdate.action','bg_02');">网上自行注册</a>
					<ul>
						<li><a href="#">填写调查问卷</a></li>
					</ul></li>

				<li class="fly"><a href="#">采购协同</a>
					<ul>
						<li icon="${vix}/common/img/pur_order.png" class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_02');">订单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseTenderAction!goList.action','bg_02');">投标/竞标</a></li>
						<li icon="${vix}/common/img/pur_inquire.png"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInquireAction!goList.action','bg_02');">报价</a></li>
						<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseInvoiceAction!goList.action','bg_02');">发票</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/sales/saleDeliveryTransferAction!goList.action','bg_02');">货运</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/srm/supplierVMIAction!goList.action','bg_02');">供应商管理库存</a></li>
				<li class="fly"><a href="#">目录管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/srm/productManagementAction!goList.action','bg_02');">产品管理</a></li>
						<li><a href="#">价格管理</a></li>
						<li class="fly_end"><a href="#">资料管理</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#">自助采购门户</a>
			<ul>
				<li class="fly_top"><a href="#" onclick="loadContent('${vix}/srm/proInternalStaffAction!goList.action','bg_02');">内部员工采购</a></li>
				<li class="fly_end"><a href="#" onclick="loadContent('${vix}/srm/purchaseOrderManagementAction!goList.action','bg_02');">订单管理</a></li>
			</ul></li>
		<li class="fly"><a href="#">分析与统计</a>
			<ul>
				<li class="fly_top"><a href="#">采购成本预测</a></li>
				<li class="fly"><a href="#">支出分析</a>
					<ul>
						<li class="fly_top"><a href="#">分析报表与地图信息</a></li>
						<li><a href="#">供应商分布</a></li>
						<li><a href="#">支出重点区域</a></li>
						<li><a href="#">十大供应商</a></li>
						<li><a href="#">供应商清单及信息</a></li>
						<li><a href="#">采构信息额分析</a></li>
						<li><a href="#">全局采购金额分析</a></li>
						<li><a href="#">物资和服务分析</a></li>
						<li><a href="#">同比和环比分析</a></li>
						<li><a href="#">合同采购比分析</a></li>
						<li><a href="#">合同细节分析</a></li>
						<li><a href="#">采购员节约分析</a></li>
						<li class="fly_end"><a href="#">采购效率分析</a></li>
					</ul></li>
				<li><a href="#">采购成本分析与统计</a></li>
				<li class="fly_end"><a href="#">采购监控</a></li>
			</ul></li>
	</ul></li>