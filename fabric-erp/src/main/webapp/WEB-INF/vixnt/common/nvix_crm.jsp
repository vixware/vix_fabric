<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li>
	<a id="mid_crm" href="javascript:void(0);" onclick="loadContent('mid_crm','${nvix}/nvixnt/nvixCustomerAction!goCrmList.action');">
		<i class="fa fa-lg fa-fw fa-users"></i> <span class="menu-item-parent">客户关系管理</span>
	</a>
	<ul>
		<li><a id="mid_crmSet" href="javascript:void(0);" onclick="loadContent('mid_crmSet','${nvix}/nvixnt/nvixCrmSetAction!goList.action');">基础设置</a></li>
		<li>
			<a href="javascript:void(0);">字典管理</a>
			<ul>
				<li>
					<a href="javascript:void(0);">客户字典</a>
					<ul>
						<li><a id="mid_customerSource" href="javascript:void(0);" onclick="loadContent('mid_customerSource','${nvix}/nvixnt/nvixCustomerSourceAction!goList.action');">客户来源 </a></li>
						<li><a id="mid_hotLevel" href="javascript:void(0);" onclick="loadContent('mid_hotLevel','${nvix}/nvixnt/nvixHotLevelAction!goList.action');">热度等级 </a></li>
						<li><a id="mid_industry" href="javascript:void(0);" onclick="loadContent('mid_industry','${nvix}/nvixnt/nvixIndustryAction!goList.action');">行业类型</a></li>
						<li><a id="mid_accountType" href="javascript:void(0);" onclick="loadContent('mid_accountType','${nvix}/nvixnt/nvixAccountTypeAction!goList.action');">账户类型</a></li>
						<li><a id="mid_relationshipClass" href="javascript:void(0);" onclick="loadContent('mid_relationshipClass','${nvix}/nvixnt/nvixRelationshipClassAction!goList.action');">关系等级</a></li>
						<li><a id="mid_staffScale" href="javascript:void(0);" onclick="loadContent('mid_staffScale','${nvix}/nvixnt/nvixStaffScaleAction!goList.action');">人员规模</a></li>
						<li><a id="mid_customerType" href="javascript:void(0);" onclick="loadContent('mid_customerType','${nvix}/nvixnt/nvixCustomerTypeAction!goList.action');">客户种类</a></li>
						<li><a id="mid_customerStage" href="javascript:void(0);" onclick="loadContent('mid_customerStage','${nvix}/nvixnt/nvixCustomerStageAction!goList.action');">客户阶段</a></li>
						<li><a id="mid_credentialType" href="javascript:void(0);" onclick="loadContent('mid_credentialType','${nvix}/nvixnt/nvixCredentialTypeAction!goList.action');">证件类型</a></li>
						<li><a id="mid_contactPersonType" href="javascript:void(0);" onclick="loadContent('mid_contactPersonType','${nvix}/nvixnt/nvixContactPersonTypeAction!goList.action');">联系人分类</a></li>
						<li><a id="mid_crmContactType" href="javascript:void(0);" onclick="loadContent('mid_crmContactType','${nvix}/nvixnt/nvixCrmContactTypeAction!goList.action');">联系人类型</a></li>
						<li><a id="mid_memorialDayType" href="javascript:void(0);" onclick="loadContent('mid_memorialDayType','${nvix}/nvixnt/nvixMemorialDayTypeAction!goList.action');">纪念日类型</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:void(0);">销售字典</a>
					<ul>
						<li><a id="mid_saleChangeStatus" href="javascript:void(0);" onclick="loadContent('mid_saleChangeStatus','${nvix}/nvixnt/nvixSaleChanceStatusAction!goList.action');">销售机会状态</a></li>
						<li><a id="mid_saleChangeStage" href="javascript:void(0);" onclick="loadContent('mid_saleChangeStage','${nvix}/nvixnt/nvixSaleChanceStageAction!goList.action');">销售机会阶段</a></li>
						<li><a id="mid_saleChangeType" href="javascript:void(0);" onclick="loadContent('mid_saleChangeType','${nvix}/nvixnt/nvixSaleChanceTypeAction!goList.action');">销售机会类型</a></li>
						<li><a id="mid_saleSource" href="javascript:void(0);" onclick="loadContent('mid_saleSource','${nvix}/nvixnt/nvixSaleSourceAction!goList.action');">销售机会来源</a></li>
						<li><a id="mid_saleActivityType" href="javascript:void(0);" onclick="loadContent('mid_saleActivityType','${nvix}/nvixnt/nvixSaleActivityTypeAction!goList.action');">销售活动类型</a></li>
						<li><a id="mid_expenseType" href="javascript:void(0);" onclick="loadContent('mid_expenseType','${nvix}/nvixnt/nvixExpenseTypeAction!goList.action');">销售费用类型</a></li>
						<li><a id="mid_saleInvoiceType" href="javascript:void(0);" onclick="loadContent('mid_saleInvoiceType','${nvix}/nvixnt/nvixSaleInvoiceTypeAction!goList.action');">销售票据类型</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:void(0);">项目字典</a>
					<ul>
						<li><a id="mid_projectStage" href="javascript:void(0);" onclick="loadContent('mid_projectStage','${nvix}/nvixnt/nvixProjectStageAction!goList.action');">项目阶段</a></li>
						<li><a id="mid_projectStatus" href="javascript:void(0);" onclick="loadContent('mid_projectStatus','${nvix}/nvixnt/nvixProjectStatusAction!goList.action');">项目状态</a></li>
						<li><a id="mid_projectSalePreviousStage" href="javascript:void(0);" onclick="loadContent('mid_projectSalePreviousStage','${nvix}/nvixnt/nvixProjectSalePreviousStageAction!goList.action');">售前阶段</a></li>
						<li><a id="mid_paymentType" href="javascript:void(0);" onclick="loadContent('mid_paymentType','${nvix}/nvixnt/nvixPaymentTypeAction!goList.action');">支付方式</a></li>
						<li><a id="mid_paymentCategory" href="javascript:void(0);" onclick="loadContent('mid_paymentCategory','${nvix}/nvixnt/nvixPaymentCategoryAction!goList.action');">支付分类</a></li>
						<li><a id="mid_competitive" href="javascript:void(0);" onclick="loadContent('mid_competitive','${nvix}/nvixnt/nvixCompetitiveAction!goList.action');">竞争能力</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:void(0);">售后字典</a>
					<ul>
						<li><a id="mid_customerCareType" href="javascript:void(0);" onclick="loadContent('mid_customerCareType','${nvix}/nvixnt/nvixCustomerCareTypeAction!goList.action');">客户关怀类型</a></li>
						<li><a id="mid_serviceMode" href="javascript:void(0);" onclick="loadContent('mid_serviceMode','${nvix}/nvixnt/nvixServiceModeAction!goList.action');">客户服务方式</a></li>
						<li><a id="mid_serviceType" href="javascript:void(0);" onclick="loadContent('mid_serviceType','${nvix}/nvixnt/nvixServiceTypeAction!goList.action');">客户服务类型</a></li>
						<li><a id="mid_repairOrderType" href="javascript:void(0);" onclick="loadContent('mid_repairOrderType','${nvix}/nvixnt/nvixRepairOrderTypeAction!goList.action');">维修工单类型</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">客户管理</a>
			<ul>
				<li><a id="mid_customerAccountCategory" href="javascript:void(0);" onclick="loadContent('mid_customerAccountCategory','${nvix}/nvixnt/nvixCustomerCategoryAction!goList.action');">客户分类</a></li>
				<li><a id="mid_customer" href="javascript:void(0);" onclick="loadContent('mid_customer','${nvix}/nvixnt/nvixCustomerAction!goCustomerList.action');">客户公海</a></li>
				<li><a id="mid_customerAccount" href="javascript:void(0);" onclick="loadContent('mid_customerAccount','${nvix}/nvixnt/nvixCustomerAction!goList.action');">客户列表</a></li>
				<%-- <li><a id="mid_customerAddress" href="javascript:void(0);" onclick="loadContent('mid_customerAddress','${nvix}/nvixnt/nvixCustomerAddressAction!goList.action');">客户地址</a></li> --%>
				<li><a id="mid_customerStatistics" href="javascript:void(0);" onclick="loadContent('mid_customerStatistics','${nvix}/nvixnt/nvixCustomerStatisticsAction!goList.action');">客户统计</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">联系人管理</a>
			<ul>
				<li><a id="mid_contactPerson" href="javascript:void(0);" onclick="loadContent('mid_contactPerson','${nvix}/nvixnt/nvixContactPersonAction!goList.action');">联系人列表</a></li>
				<li><a id="mid_memorialDay" href="javascript:void(0);" onclick="loadContent('mid_memorialDay','${nvix}/nvixnt/nvixMemorialDayAction!goList.action');">纪念日列表</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">项目管理</a>
			<ul>
				<li><a id="mid_crmProject" href="javascript:void(0);" onclick="loadContent('mid_crmProject','${nvix}/nvixnt/nvixCrmProjectAction!goList.action');">项目列表</a></li>
				<li><a id="mid_crmProjectDiscuss" href="${nvix}/nvixnt/nvixCrmProjectAction!goCrmProjectDiscuss.action" target="_blank">项目沟通</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">售前管理</a>
			<ul>
				<li><a id="mid_chanceAndTracking" href="javascript:void(0);" onclick="loadContent('mid_chanceAndTracking','${nvix}/nvixnt/nvixChanceAndTrackingAction!goList.action');">销售机会</a></li>
				<li><a id="mid_saleLead" href="javascript:void(0);" onclick="loadContent('mid_saleLead','${nvix}/nvixnt/nvixSaleLeadAction!goList.action');">销售线索</a></li>
				<li><a id="mid_saleActivity" href="javascript:void(0);" onclick="loadContent('mid_saleActivity','${nvix}/nvixnt/nvixSaleActivityAction!goList.action');">销售活动</a></li>
				<li><a id="mid_projectRequirement" href="javascript:void(0);" onclick="loadContent('mid_projectRequirement','${nvix}/nvixnt/nvixProjectRequirementAction!goList.action');">详细需求</a></li>
				<li><a id="mid_competitor" href="javascript:void(0);" onclick="loadContent('mid_competitor','${nvix}/nvixnt/nvixCompetitorAction!goList.action');">竞争对手</a></li>
				<li><a id="mid_projectSolution" href="javascript:void(0);" onclick="loadContent('mid_projectSolution','${nvix}/nvixnt/nvixProjectSolutionAction!goList.action');">解决方案</a></li>
				<%-- <li><a id="mid_actionHistory" href="javascript:void(0);" onclick="loadContent('mid_actionHistory','${nvix}/nvixnt/nvixActionHistoryAction!goList.action');">行动历史管理</a></li> --%>
				<li><a id="mid_saleChanceStatistics" href="javascript:void(0);" onclick="loadContent('mid_saleChanceStatistics','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!goList.action');">销售机会统计</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">售中管理</a>
			<ul>
				<li><a id="mid_salesInvoice" href="javascript:void(0);" onclick="loadContent('mid_salesInvoice','${nvix}/nvixnt/nvixSalesInvoiceAction!goList.action');">开票记录</a></li>
				<li><a id="mid_backSectionPlan" href="javascript:void(0);" onclick="loadContent('mid_backSectionPlan','${nvix}/nvixnt/nvixBackSectionPlanAction!goList.action');">回款计划</a></li>
				<li><a id="mid_backSectionRecord" href="javascript:void(0);" onclick="loadContent('mid_backSectionRecord','${nvix}/nvixnt/nvixBackSectionRecordAction!goList.action');">回款记录</a></li>
				<li><a id="mid_saleReturnBill" href="javascript:void(0);" onclick="loadContent('mid_saleReturnBill','${nvix}/nvixnt/nvixSaleReturnBillAction!goList.action');">退款记录</a></li>
				<li><a id="mid_expenses" href="javascript:void(0);" onclick="loadContent('mid_expenses','${nvix}/nvixnt/nvixExpensesAction!goList.action');">费用支出</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">售后管理</a>
			<ul>
				<li><a id="mid_customerCare" href="javascript:void(0);" onclick="loadContent('mid_customerCare','${nvix}/nvixnt/nvixCustomerCareAction!goList.action');">客户关怀</a></li>
				<li><a id="mid_customerService" href="javascript:void(0);" onclick="loadContent('mid_customerService','${nvix}/nvixnt/nvixCustomerServiceAction!goList.action');">客户服务</a></li>
				<li><a id="mid_repairOrder" href="javascript:void(0);" onclick="loadContent('mid_repairOrder','${nvix}/nvixnt/nvixRepairOrderAction!goList.action');">维修工单</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);">客户分析</a>
			<ul>
				<li><a id="mid_customerKindPie" href="javascript:void(0);" onclick="loadContent('mid_customerKindPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerKindPie.action');">客户种类分布</a></li>
				<li><a id="mid_customerStagePie" href="javascript:void(0);" onclick="loadContent('mid_customerStagePie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerStagePie.action');">客户阶段分布</a></li>
				<li><a id="mid_customerHotLevelPie" href="javascript:void(0);" onclick="loadContent('mid_customerHotLevelPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerHotLevelPie.action');">热点程度分布</a></li>
		  		<li><a id="mid_customerCreditLevelPie" href="javascript:void(0);" onclick="loadContent('mid_customerCreditLevelPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerCreditLevelPie.action');">信用等级分布</a></li>
		 		<li><a id="mid_customerRelationshipClassPie" href="javascript:void(0);" onclick="loadContent('mid_customerRelationshipClassPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerRelationshipClassPie.action');">关系等级分布</a></li>
		  		<li><a id="mid_customerStaffScalePie" href="javascript:void(0);" onclick="loadContent('mid_customerStaffScalePie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerStaffScalePie.action');">人员规模分布</a></li>
		  		<li><a id="mid_customerCustomerSourcePie" href="javascript:void(0);" onclick="loadContent('mid_customerCustomerSourcePie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerCustomerSourcePie.action');">客户来源分布</a></li>
		  		<li><a id="mid_customerIndustryPie" href="javascript:void(0);" onclick="loadContent('mid_customerIndustryPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerIndustryPie.action');">所属行业分布</a></li>
		  		<li><a id="mid_customerValueAssessmentPie" href="javascript:void(0);" onclick="loadContent('mid_customerValueAssessmentPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerValueAssessmentPie.action');">价值评估分布</a></li>
		 		<li><a id="mid_customerAccountTypePie" href="javascript:void(0);" onclick="loadContent('mid_customerAccountTypePie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerAccountTypePie.action');">账户类型分布</a></li>
		 		<li><a id="mid_customerProvincePie" href="javascript:void(0);" onclick="loadContent('mid_customerProvincePie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerProvincePie.action');">客户省份分布</a></li>
		   		<li><a id="mid_customerCityPie" href="javascript:void(0);" onclick="loadContent('mid_customerCityPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerCityPie.action');">客户城市分布</a></li>
		   		<li><a id="mid_customerEmployeePie" href="javascript:void(0);" onclick="loadContent('mid_customerEmployeePie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerEmployeePie.action');">所有者分布</a></li>
		  		<li><a id="mid_customerAccountCategoryPie" href="javascript:void(0);" onclick="loadContent('mid_customerAccountCategoryPie','${nvix}/nvixnt/nvixCustomerStatisticsAction!customerAccountCategoryPie.action');">客户分类分布</a></li>
		   </ul>
		</li>
		<li>
			<a href="javascript:void(0);">销售跟踪</a>
			<ul>
				<li><a id="mid_discoveryTimeMonthView" href="javascript:void(0);" onclick="loadContent('mid_discoveryTimeMonthView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!discoveryTimeMonthView.action');">机会发现时间月份统计</a></li>
				<li><a id="mid_preOrderDateMonthView" href="javascript:void(0);" onclick="loadContent('mid_preOrderDateMonthView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!preOrderDateMonthView.action');">机会预计签单月份统计</a></li>
				<li><a id="mid_createtimeMonthView" href="javascript:void(0);" onclick="loadContent('mid_createtimeMonthView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!createtimeMonthView.action');">机会创建时间月份统计</a></li>
				<li><a id="mid_saleChanceChargerView" href="javascript:void(0);" onclick="loadContent('mid_saleChanceChargerView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceChargerView.action');">销售机会负责人分布</a></li>
		 		<li><a id="mid_saleChanceSaleSourceView" href="javascript:void(0);" onclick="loadContent('mid_saleChanceSaleSourceView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceSaleSourceView.action');">销售机会来源分布</a></li>
		 		<li><a id="mid_saleChanceSaleTypeView" href="javascript:void(0);" onclick="loadContent('mid_saleChanceSaleTypeView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceSaleTypeView.action');">销售机会类型分布</a></li>
		 		<li><a id="mid_chargerDivisionStatusView" href="javascript:void(0);" onclick="loadContent('mid_chargerDivisionStatusView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!chargerDivisionStatusView.action');">负责人/机会状态统计</a></li>
		 		<li><a id="mid_chargerDivisionSaleStageView" href="javascript:void(0);" onclick="loadContent('mid_chargerDivisionSaleStageView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!chargerDivisionSaleStageView.action');">负责人/机会阶段统计</a></li>
		 		<li><a id="mid_saleChancePossibilityView" href="javascript:void(0);" onclick="loadContent('mid_saleChancePossibilityView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChancePossibilityView.action');">机会可能性/状态统计</a></li>
		 		<li><a id="mid_saleChanceStagePossibilityView" href="javascript:void(0);" onclick="loadContent('mid_saleChanceStagePossibilityView','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceStagePossibilityView.action');">机会可能性/阶段统计</a></li>
		 		<li><a id="mid_saleChanceStageNumFunnel" href="javascript:void(0);" onclick="loadContent('mid_saleChanceStageNumFunnel','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceStageNumFunnel.action');">各阶段机会数量漏斗</a></li>
		 		<li><a id="mid_saleChanceStageExpectedMoneyFunnel" href="javascript:void(0);" onclick="loadContent('mid_saleChanceStageExpectedMoneyFunnel','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!saleChanceStageExpectedMoneyFunnel.action');">各阶段机会预期金额漏斗</a></li>
		 		<li><a id="mid_activitySaleActivityPieShow" href="javascript:void(0);" onclick="loadContent('mid_activitySaleActivityPieShow','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!activitySaleActivityPieShow.action');">销售活动类型分布</a></li>
		 		<li><a id="mid_activityTypeDivideMonthDraw" href="javascript:void(0);" onclick="loadContent('mid_activityTypeDivideMonthDraw','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!activityTypeDivideMonthDraw.action');">销售活动类型/月份分布</a></li>
		  		<li><a id="mid_backSectionPlanAmountCustomerTop" href="javascript:void(0);" onclick="loadContent('mid_backSectionPlanAmountCustomerTop','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!backSectionPlanAmountCustomerTop.action');">应收款对应客户排行</a></li>
		  		<li><a id="mid_backSectionPlanAmountOwnerTop" href="javascript:void(0);" onclick="loadContent('mid_backSectionPlanAmountOwnerTop','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!backSectionPlanAmountOwnerTop.action');">应收款/回款计划所有者排行</a></li>
		  		<li><a id="mid_backSectionPlanAmountChargerTop" href="javascript:void(0);" onclick="loadContent('mid_backSectionPlanAmountChargerTop','${nvix}/nvixnt/nvixSaleChanceStatisticsAction!backSectionPlanAmountChargerTop.action');">应收款/回款计划负责人排行</a></li>
		   </ul>
		</li>
	</ul>
</li>