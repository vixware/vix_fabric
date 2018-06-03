<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<!-- 供应链 -->
<li class="nav_arrow" id="bg_02"><a href="#"><span>供应链</span> </a>
	<ul>

		<s:include value="head_srm.jsp"></s:include>
		<s:include value="head_purchase.jsp"></s:include>
		<li class="fly"><a href="#" onclick="loadContent('${vix}/crm/crmAction!goCrmHome.action','bg_02');">客户关系管理</a>
			<ul>
				<li class="fly"><a href="#">设置</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/crm/base/crmDictionaryAction!goDictionaryEdit.action','bg_02');">字典管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/base/crmDictionaryAction!goDictionaryEdit.action','bg_02');">标签管理</a></li>
					</ul></li>
				<li class="fly"><a href="#">工作台</a>
					<ul>
						<li><a href="#" onclick="loadContent('/vix/common/model/jobTodoAction!goList.action','bg_02');">待办任务</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/agenda/calendarsAction!goCalendars.action','bg_02');">日程</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/workbench/innerBulletinAction!goList.action','bg_02');">内部公告</a></li>
						<!--  
								<li><a href="/vix/content/scm/crm/cda.jsp">公司数据</a></li>
							 	<li><a href="/vix/content/scm/crm/pd.jsp">小组数据</a></li>
						-->
						<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action','bg_02');">热点客户</a></li>
						<li><a href="###" onclick="loadContent('${vix}/crm/customer/crmRemindAction!goBackSectionPlanRemind.action','bg_02');">本月应收款提醒</a></li>
						<li><a href="###">发货提醒</a></li>
						<li class="fly_end"><a href="###" onclick="loadContent('${vix}/crm/customer/crmRemindAction!goCustomerComplaintRemind.action','bg_02');">客户投诉提醒</a></li>
					</ul></li>
				<li class="fly"><a href="#">工作日报</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action','bg_02');">查看日报</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action','bg_02');">撰写日报</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/agenda/weeklyAction!goList.action','bg_02');">撰写周报</a></li>
						<li class="fly_end"><a href="#" title="dev_list" onclick="loadContent('${vix}/crm/agenda/monthlyAction!goList.action','bg_02');">撰写月报</a></li>
					</ul></li>
				<li class="fly"><a href="#">基础资料管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/project/competitorAction!goList.action','bg_02');">竞争对手管理</a></li>
						<li><a href="#" title="dev_主子 左树有grid" onclick="loadContent('${vix}/crm/project/competitorProductAction!goList.action','bg_02');">竞争产品管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/project/projectSolutionAction!goList.action','bg_02');" title="dev_">解决方案</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/project/projectRequirementAction!goList.action','bg_02');" title="dev_">项目需求</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action','bg_02');" title="">产品信息管理</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/workbench/knowledgeAction!goList.action','bg_02');">知识库管理</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAction!goList.action','bg_02');">客户管理</a>
					<ul>
						<script type="text/javascript">
							function fastCreateCustomer() {
								$.ajax({
								url : "${vix}/mdm/crm/customerAccountAction!goSaveOrUpdate.action?id=0&customerAccountType=enterPrise",
								cache : false,
								success : function(html) {
									$("#mainContent").html(html);
								}
								});
							};
						</script>
						<li class="fly_top"><a href="###" onclick="fastCreateCustomer();">快速新建客户</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountCategoryAction!goList.action','bg_02');">客户分类</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountGroupAction!goList.action','bg_02');">客户组</a></li>
						<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=crm','bg_02');">客户列表</a></li>
						<li><a href="###" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goCustomerAccountShare.action','bg_02');">客户共享和转移</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/service/customerCareAction!goList.action','bg_02');">客户关怀</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/customer/reconciliationAction!goList.action','bg_02');" title="dev_">往来对账</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/project/projectCostAction!goList.action','bg_02');" title="dev_跟客户相关的费用">费用</a></li>
						<li><a href="###" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goSearch.action','bg_02');">高级查询</a></li>
						<li><a href="###" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchAddList.action','bg_02');">批量新建客户</a></li>
						<li><a href="###" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountBatchAction!goBatchUpdateList.action','bg_02');">批量编辑客户</a></li>
						<li><a href="/vix/content/scm/crm/eetfd.jsp">导出excel表格式数据</a></li>
						<li class="fly_end"><a href="/vix/content/scm/crm/sct.jsp">统计图表</a></li>
					</ul></li>
				<li class="fly"><a href="#">联系人管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/customer/crmContactPersonAction!goList.action','bg_02');">联系人列表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmContactPersonAction!goFastList.action','bg_02');">百家姓</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/customer/memorialDayAction!goList.action','bg_02');" title="dev_">纪念日提醒</a></li>
						<li class="fly_end"><a href="/vix/content/scm/crm/mm.jsp">短信管理</a></li>
					</ul></li>

				<li class="fly"><a href="#" onclick="loadContent('${vix}/crm/activity/activityAction!goList.action','bg_02');">销售活动</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/activity/activityAction!goSaveOrUpdate.action','bg_02');">新增活动</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/activity/activityAction!goList.action','bg_02');">活动列表</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/crm/lead/saleLeadAction!goList.action','bg_02');">销售线索</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/lead/saleLeadAction!goSaveOrUpdate.action','bg_02');">新增线索</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/lead/saleLeadAction!goList.action','bg_02');">线索列表</a></li>
						<li class="fly"><a href="#">网上线索挖掘</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/scm/crm/atobl.jsp">获取网上购买线索</a></li>
								<li><a href="/vix/content/scm/crm/eft.jsp">电子表单模版</a></li>
								<li><a href="/vix/content/scm/crm/acg.jsp">自动生成代码</a></li>
								<li class="fly_end"><a href="/vix/content/scm/crm/ccr.jsp">线索来电记录</a></li>
							</ul></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action','bg_02');">销售机会</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goSaveOrUpdate.action','bg_02');">新增机会</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action','bg_02');">销售机会列表</a></li>
						<!-- <li><a href="/vix/content/scm/crm/jdtlsj.jsp">阶段停留时间</a></li> -->
						<li><a href="#" onclick="loadContent('${vix}/crm/salechance/backSectionPlanAction!goList.action','bg_02');">回款计划</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/salechance/backSectionRecordAction!goList.action','bg_02');">回款记录</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goSaleFunnel.action','bg_02');">销售漏斗</a></li>
						<li class="fly_end"><a href="/vix/content/scm/crm/sct.jsp">统计图表</a></li>
					</ul></li>
				<li class="fly"><a href="#">销售管理</a>
					<ul>
						<li class="fly"><a href="#">销售目标</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/scm/crm/tmtp.jsp">三个月目标参数</a></li>
								<li><a href="/vix/content/scm/crm/mgs.jsp">月度目标设置</a></li>
								<li><a href="/vix/content/scm/crm/ts.jsp">目标范围设置</a></li>
								<li><a href="/vix/content/scm/crm/ctdoat.jsp">校验分解目标</a></li>
								<li><a href="/vix/content/scm/crm/pvoaa.jsp">实际完成值自动获取</a></li>
								<li class="fly_end"><a href="/vix/content/scm/crm/pce.jsp">完成百分比</a></li>
							</ul></li>
						<li class="fly"><a href="#">交付发货记录列表</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/scm/crm/cttdrc.jsp">合同对应交付记录</a></li>
								<li><a href="/vix/content/scm/crm/octdr.jsp">订单对应发货记录</a></li>
								<li><a href="/vix/content/scm/crm/cdy.jsp">合同交付情况</a></li>
								<li class="fly_end"><a href="/vix/content/scm/crm/or.jsp">订单发货情况</a></li>
							</ul></li>
						<li class="fly"><a href="/vix/content/scm/crm/sf.jsp">销售计划及计划分析</a></li>
						<li><a href="/vix/content/scm/crm/mf.jsp">销售任务</a></li>
						<li><a href="/vix/content/scm/crm/teta.jsp">竞争对手管理</a></li>
						<li><a href="/vix/content/scm/crm/teta.jsp">报价单</a></li>
						<li><a href="/vix/content/scm/crm/prt.jsp">订单管理</a></li>
						<li><a href="/vix/content/scm/crm/prt.jsp">订单执行</a></li>
						<li class="fly"><a href="#">发货单列表</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/scm/crm/sdm.jsp">发货明细管理</a></li>
								<li class="fly_end"><a href="/vix/content/scm/crm/vis.jsp">查看发货单状态</a></li>
							</ul></li>
						<li class="fly_end"><a href="/vix/content/scm/crm/sct.jsp">应收款和应付款管理</a></li>
					</ul></li>
				<li class="fly"><a href="#">市场管理</a>
					<ul>
						<!-- <li class="fly_top"><a href="/vix/content/scm/crm/scjh.jsp">市场计划</a></li>
										<li><a href="/vix/content/scm/crm/">市场计划评估</a></li>
										<li><a href="/vix/content/scm/crm/dhxs.jsp">电话销售</a></li>
										<li><a href="/vix/content/scm/crm/">市场线索</a></li>
										<li><a href="/vix/content/scm/crm/MarketActivity.jsp">市场活动</a></li>
										<li><a href="/vix/content/scm/crm/">市场活动分析</a></li>
										<li><a href="/vix/content/scm/crm/">市场任务</a></li>
										<li><a href="/vix/content/scm/crm/AdManagement.jsp">广告管理</a></li> -->
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/market/printedMatterAction!goList.action','bg_02');">印刷品管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/market/printedMatterUnitAction!goList.action','bg_02');">印刷品单位</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/market/printedRequisitionAction!goList.action','bg_02');">印刷品领用</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/market/crmGiftAction!goList.action','bg_02');">礼品管理</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/market/crmGiftRequisitionAction!goList.action','bg_02');">礼品领用</a></li>
					</ul></li>
				<li class="fly"><a href="#">项目管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goSaveOrUpdate.action','bg_02');">新建项目</a></li>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goList.action','bg_02');">项目管理</a></li>
					</ul></li>
				<li class="fly"><a href="#">客服管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/service/customerServiceAction!goList.action','bg_02');">客服记录</a></li>
						<li><a href="#" onclick="loadContent('${vix}/crm/service/customerComplaintAction!goList.action','bg_02');">投诉处理</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/crm/service/qualityAssuranceAction!goList.action','bg_02');">QA库</a></li>
						<!-- <li class="fly_top"><a href="/vix/content/scm/crm/">知识库查询</a></li>
										<li><a href="/vix/content/scm/crm/">客服计划及服务分析</a></li>
										<li><a href="/vix/content/scm/crm/">客户满意度调查</a></li>
										<li><a href="/vix/content/scm/crm/">服务受理单</a></li>
										<li><a href="/vix/content/scm/crm/">服务受理单分析</a></li>
										<li><a href="/vix/content/scm/crm/">产品缺陷管理</a></li>
										<li><a href="/vix/content/scm/crm/">服务退件</a></li>
										<li><a href="/vix/content/scm/crm/">服务合同</a></li>
										<li><a href="/vix/content/scm/crm/">备件库存交易</a></li>
										<li><a href="/vix/content/scm/crm/">备件盘点查询</a></li>
										<li><a href="/vix/content/scm/crm/">质量问题反馈单</a></li>
										<li><a href="/vix/content/scm/crm/cs.jsp">客服服务</a></li>
										<li><a href="/vix/content/scm/crm/vccs.jsp">查看客户内容设置</a></li>
										<li><a href="/vix/content/scm/crm/csdc.jsp">客服专用控制台</a></li>
										<li><a href="/vix/content/scm/crm/cssc.jsp">客服特殊控制台</a></li>
										<li><a href="/vix/content/scm/crm/dl.jsp">数据日志</a></li>
										<li><a href="/vix/content/scm/crm/cim.jsp">客户信息管理</a></li>
										<li><a href="/vix/content/scm/crm/lc.jsp">下级客户</a></li> -->
					</ul></li>
				<li class="fly"><a href="#">智能分析</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action','bg_02');">RFM模型</a></li>
					</ul></li>
				<li class="fly"><a href="#">报表中心</a>
					<ul>
						<li class="fly"><a href="#">综合报表</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/scm/crm/">年度基本情况表</a></li>
								<li><a href="/vix/content/scm/crm/">销售综合汇总表</a></li>
								<li><a href="/vix/content/scm/crm/">人员签署合同明细表</a></li>
								<li><a href="/vix/content/scm/crm/">客户汇款明细表</a></li>
								<li><a href="/vix/content/scm/crm/">人员汇款明细表</a></li>
								<li><a href="/vix/content/scm/crm/">库存资产表</a></li>
								<li><a href="/vix/content/scm/crm/">采购汇总表</a></li>
								<li><a href="/vix/content/scm/crm/">毛利明细表</a></li>
								<li><a href="/vix/content/scm/crm/">毛利汇总表</a></li>
								<li><a href="/vix/content/scm/crm/">出库汇总表</a></li>
								<li><a href="/vix/content/scm/crm/">发货交付汇总表</a></li>
								<li><a href="/vix/content/scm/crm/">客户毛利统计表</a></li>
								<li class="fly_end"><a href="/vix/content/scm/crm/">产品毛利统计表</a></li>
							</ul></li>
						<li class="fly"><a href="#">同比环比同组分析</a>
							<ul>
								<li class="fly_top"><a href="/vix/content/scm/crm/">同比分析与环比分析</a></li>
								<li><a href="/vix/content/scm/crm/">同比环比分析图表</a></li>
								<li><a href="/vix/content/scm/crm/">同比时间单位选择</a></li>
								<li><a href="/vix/content/scm/crm/">环比时间段选择</a></li>
								<li><a href="/vix/content/scm/crm/">环比时间单位选择</a></li>
								<li><a href="/vix/content/scm/crm/">钻取统计数据</a></li>
								<li class="fly_end"><a href="/vix/content/scm/crm/">维度分组统计</a></li>
							</ul></li>
					</ul></li>
				<li class="fly"><a href="#">工具箱</a>
					<ul>
						<li class="fly_top"><a href="/vix/content/scm/crm/pss.jsp">个人设置</a></li>
						<li><a href="/vix/content/scm/crm/rsn.jsp">回收站</a></li>
						<li><a href="/vix/content/scm/crm/mm.jsp">短信管理</a></li>
						<li><a href="/vix/content/scm/crm/hs.jsp">热点设置</a></li>
						<li><a href="/vix/content/scm/crm/moa.jsp">附件管理</a></li>
						<li><a href="/vix/content/scm/crm/pc.jsp">打印快递单</a></li>
						<li class="fly_end"><a href="/vix/content/scm/crm/dd.jsp">数据字典</a></li>
					</ul></li>
			</ul></li>
		<s:include value="head_customer.jsp"></s:include>

		<li class="fly"><a href="#">售前分析</a>
			<ul>
				<li class="fly"><a href="#">ATP模拟</a>
					<ul>
						<li class="fly_top"><a href="#">ATP选项</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/forecast/simulationSchemeAction!goList.action','bg_02');">ATP模拟方案定义</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/sales/forecast/simulationBudgetsAction!goList.action','bg_02');">ATP模拟运算</a></li>
					</ul></li>
				<li class="fly"><a href="#">模拟报价</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/forecast/biddingSchemeAction!goList.action','bg_02');">模拟报价方案</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/forecast/quotationBudgetAction!goList.action','bg_02');">模拟报价运算</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/forecast/exceptionInformationAction!goList.action','bg_02');">例外信息</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/forecast/quotationAction!goList.action','bg_02');">报价列表</a></li>
						<li class="fly_end"><a href="#">报表</a></li>
					</ul></li>
			</ul></li>
		<li class="fly"><a href="#">销售管理</a>
			<ul>
				<li class="fly"><a href="#">设置</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/common/salesAction!goDictionaryEdit.action','bg_02');">字典管理</a></li>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/config/saleConfigAction!goSaleConfig.action','bg_02');">销售选项</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/config/saleOrgAction!goList.action','bg_02');">销售组织</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/config/productGroupAction!goList.action','bg_02');">产品组</a></li>
						<%-- <li><a href="#" onclick="loadContent('${vix}/sales/config/salesAreaAction!goList.action','bg_02');">销售区域</a></li> --%>
						<li class="fly"><a href="#">后勤执行</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/sales/config/shippingPointAction!goList.action','bg_02');">起运点</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/config/mountPointAction!goList.action','bg_02');">装载点</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/config/transportationConditionAction!goList.action','bg_02');">运输条件</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/sales/config/loadingGroupAction!goList.action','bg_02');">装载组</a></li>
							</ul></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/config/saleBillTypeAction!goList.action','bg_02');">销售单据类型</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationCategoryAction!goList.action','bg_02');">报价模版分类</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationTemplateAction!goList.action','bg_02');">报价单模板</a></li>
						<%-- <li><a href="#" onclick="loadContent('${vix}/mdm/item/priceConditionAction!goList.action','bg_02');">价格管理</a></li> --%>
						<li class="fly_end"><a href="#">期初录入</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/sales/forDefAction!goList.action','bg_02');">销售预测</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/forDefAction!goList.action','bg_02');">预测定义列表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/predictionDefinitionAction!goList.action','bg_02');">预测定义</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/comparisonPredictedAction!goList.action','bg_02');">预测对比</a></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/sales/predictionHistoryAction!goList.action','bg_02');">预测历史</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/sales/plan/salePlanAction!goList.action','bg_02');">销售计划</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/plan/salePlanAction!goList.action','bg_02');">计划列表</a></li>
						<li class="fly_end"><a href="#">销售计划执行报告</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goList.action','bg_02');">销售报价</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goSaveOrUpdate.action','bg_02');">新增报价单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/quotes/salesQuotationAction!goList.action','bg_02');">报价单列表</a></li>
						<li class="fly"><a href="/vix/content/scm/sm/pq.jsp">项目式报价</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/sales/quotes/projectQuotationTemplateAction!goList.action','bg_02');">项目式报价单模板</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/quotes/customerizeProductAction!goList.action','bg_02');">客户需求方案列表</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goSaveOrUpdate.action','bg_02');">新增项目式报价单</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/quotes/projectQuotationSchemeAction!goList.action','bg_02');">项目式报价单列表</a></li>
							</ul></li>
						<li><a href="/vix/content/scm/sm/pa.jsp">报价单审批</a></li>
						<li><a href="/vix/content/scm/sm/bp.jsp">批量处理</a></li>
						<li class="fly_end"><a href="/vix/content/scm/sm/hq.jsp">历史报价</a></li>
					</ul></li>
				<li class="fly"><a href="#">合同管理</a>
					<ul>
						<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goContractMarket.action','bg_02');">新增</a></li>
						<li><a href="#">审批</a></li>
						<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goList.action','bg_02');">列表</a></li>
						<li><a href="#">合同收款</a></li>
						<li class="fly_end"><a href="#">模版</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action','bg_02');">销售订单</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goSaveOrUpdate.action','bg_02');">新增销售订单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action','bg_02');">销售订单列表</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/sales/saleDeliveryTransferAction!goList.action','bg_02');">销售发货与运输管理</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/delivery/deliveryDocumentAction!goList.action','bg_02');">发货单</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action','bg_02');">退货单</a></li>
						<li><a href="#">发货单列表</a></li>
						<li><a href="#">批量处理</a></li>
						<li><a href="#">批量生成发货单</a></li>
					</ul></li>
				<li class="fly"><a href="#">销售开票</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action','bg_02');">销售发票列表</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdate.action?invoiceType=1','bg_02');">新增销售普通发票</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdate.action?invoiceType=2','bg_02');">新增销售专用发票</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdate.action?invoiceType=3','bg_02');">新增红字普通销售发票</a></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdate.action?invoiceType=4','bg_02');">新增红字专用销售发票</a></li>
						<li><a href="#">批量生成发票</a></li>
						<li><a href="#">专票批量处理</a></li>
						<li><a href="#">普票批量处理</a></li>
					</ul></li>
				<li class="fly"><a href="#">代垫费用</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/payment/disbursementCostAction!goList.action','bg_02');">代垫费用单</a></li>
						<li><a href="#">代垫费用批处理</a></li>
						<li><a href="#">代垫费用统计表</a></li>
					</ul></li>
				<li class="fly"><a href="#" onclick="loadContent('${vix}/sales/payment/expensesAction!goList.action','bg_02');">费用支出</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/sales/payment/expensesAction!goList.action','bg_02');">销售费用支出单</a></li>
						<li><a href="#">销售费用支出单列表</a></li>
						<li><a href="#">销售费用支出批处理</a></li>
						<li><a href="#">销售费用支出统计表</a></li>
					</ul></li>
				<li class="fly"><a href="/vix/content/scm/sm/pl.jsp">包装物租借</a>
					<ul>
						<li><a href="#">包装物租借登记</a></li>
						<li><a href="#">包装物退回登记</a></li>
						<li><a href="#">包装物租借统计表</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/credit/creditControlAction!goList.action','bg_02');">信用管理</a>
					<ul>
						<li><a href="###">风险等级</a></li>
						<li><a onclick="loadContent('${vix}/sales/credit/customerCreditInfoAction!goList.action','bg_02');" href="###">授信管理</a></li>
						<li><a onclick="loadContent('${vix}/sales/credit/creditControlAction!goList.action','bg_02');" href="###">信用规则</a></li>
						<li><a onclick="loadContent('${vix}/sales/credit/creditExcuteResultAction!goList.action','bg_02');" href="###">信用执行结果</a></li>
					</ul></li>
				<li class="fly"><a href="#">返款</a>
					<ul>
						<li><a href="###" onclick="loadContent('${vix}/sales/refund/returnRuleAction!goList.action','bg_02');">返款规则</a></li>
						<li><a href="###" onclick="loadContent('${vix}/sales/refund/saleReturnBillAction!goList.action','bg_02');">返款单管理</a></li>
						<li class="fly"><a href="#">制单</a>
							<ul>
								<li><a href="#">新增</a></li>
								<li><a href="#">列表</a></li>
							</ul></li>
					</ul></li>
				<li class="fly"><a href="#">销售佣金管理</a>
					<ul>
						<li class="fly"><a href="#">设置</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/sales/commission/personnelCategoryAction!goList.action','bg_02');">销售人员类别</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/commission/saleAmountAction!goList.action','bg_02');">销售定额</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/common/salesAction!goDictionaryEdit.action','bg_02');">销售业绩考评方式</a></li>
							</ul></li>
						<li class="fly"><a href="#">佣金方案</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionTermAction!goList.action','bg_02');">佣金条件</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionPlanAction!goSaveOrUpdate.action','bg_02');">新增方案</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionPlanAction!goList.action','bg_02');">列表</a></li>
							</ul></li>
						<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionResultAction!goList.action','bg_02');">佣金计算</a></li>
						<li><a href="#">统计报表</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/stockQueryAction!goList.action','bg_02');">销售现存量查询</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/saleMonthEndAction!goList.action','bg_02');">月末结账</a></li>
				<li class="fly"><a href="/vix/content/scm/sm/rf.jsp">报表</a>
					<ul>
						<li><a href="#">我的报表</a></li>
						<li class="fly"><a href="#">统计表</a>
							<ul>
								<li><a href="#">销售统计表</a></li>
								<li><a href="#">发货统计表</a></li>
								<li><a href="#">发货单开票收款勾兑表</a></li>
								<li><a href="#">销售综合统计表</a></li>
								<li><a href="#">发票日报</a></li>
								<li><a href="#">发票使用明细表</a></li>
								<li><a href="#">信用余额表</a></li>
							</ul></li>
						<li class="fly"><a href="#">明细表</a>
							<ul>
								<li><a href="#">销售收入明细账</a></li>
								<li><a href="#">销售成本明细账</a></li>
								<li><a href="#">发货明细表</a></li>
								<li><a href="#">销售明细表</a></li>
								<li><a href="#">销售明细账</a></li>
								<li><a href="#">发货结算勾兑表</a></li>
							</ul></li>
						<li class="fly"><a href="#">销售分析</a>
							<ul>
								<li><a href="#">销售增长分析</a></li>
								<li><a href="#">货物流向分析</a></li>
								<li><a href="#">销售结构分析</a></li>
								<li><a href="#">销售毛利分析</a></li>
								<li><a href="#">市场分析</a></li>
								<li><a href="#">货龄分析</a></li>
							</ul></li>
					</ul></li>
			</ul></li>
		<s:include value="head_chain.jsp"></s:include>
		<s:include value="head_drp.jsp"></s:include>
		<s:include value="head_drpom.jsp"></s:include>
		<s:include value="head_business.jsp"></s:include>
		<li class="fly"><a href="#">合同管理</a>
			<ul>
				<li class="fly"><a href="#">初始设置</a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/contract/config/systemOptionsAction!goList.action','bg_02');">系统选项</a></li>
						<li><a href="#" onclick="loadContent('${vix}/contract/config/contractTypeAction!goList.action','bg_02');">合同类型</a></li>
						<li><a href="#" onclick="loadContent('${vix}/contract/contractClauseAction!goList.action','bg_02');">合同条款</a></li>
						<li><a href="#" onclick="loadContent('${vix}/contract/contractCatalogAction!goList.action','bg_02');">合同分类</a></li>
						<li class="fly"><a href="#">字典管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/contractGroupTypeAction!goList.action','bg_02');">所属合同组</a></li>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/contractTypeCombineAction!goList.action','bg_02');">合同类型集合</a></li>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/contractNatureTypeAction!goList.action','bg_02');">合同性质</a></li>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/viewIndustryTypeAction!goList.action','bg_02');">所属行业</a></li>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/modeTypeAction!goList.action','bg_02');">合同履行状态</a></li>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/enableStageTypeAction!goList.action','bg_02');">启用阶段</a></li>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/contract/config/contractStageGroupTypeAction!goList.action','bg_02');">合同阶段组</a></li>
							</ul></li>
					</ul></li>
				<li class="fly"><a href="#">日常操作</a>
					<ul>
						<li class="fly_top"><a href="/vix/content/scm/cm/cn.jsp">合同导航</a></li>
						<li><a href="/vix/content/scm/cm/me.jsp">批量生效</a></li>
						<li><a href="/vix/content/scm/cm/bf.jsp">批量失效</a></li>
						<li><a href="/vix/content/scm/cm/bs.jsp">批量结案</a></li>
						<li><a href="/vix/content/scm/cm/bwn.jsp">批量弃结</a></li>
						<li><a href="/vix/content/scm/cm/cbo.jsp">合同结算单</a></li>
						<li class="fly_end"><a href="/vix/content/scm/cm/cffrb.jsp">合同备查簿</a></li>
					</ul></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractInquiryAction!goList.action','bg_02');">合同列表</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractApprovalAction!goList.action','bg_02');">合同审批</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/todoContractsAction!goList.action','bg_02');">待办合同</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractProgressAction!goList.action','bg_02');">合同进度</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractWarningAction!goList.action','bg_02');">合同预警</a></li>
				<li><a href="/vix/content/scm/cm/cs.jsp">合同监控</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractPaymentsAction!goList.action','bg_02');">合同付款</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractReceivablesAction!goList.action','bg_02');">合同收款</a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractAssociateTemplateAction!goList.action','bg_02');">合同模板管理</a></li>
				<li><a href="/vix/content/scm/cm/pv.jsp">流程查看</a></li>
				<li class="fly_end"><a href="#">合同报表</a>
					<ul>
						<li class="fly_top"><a href="#">我的报表</a></li>
						<li class="fly"><a href="#">合同统计表</a>
							<ul>
								<li class="fly_top"><a href="#">合同汇总统计表</a></li>
								<li class="fly_end"><a href="#">合同明细统计表</a></li>
							</ul></li>
						<li class="fly"><a href="#">合同执行表</a>
							<ul>
								<li class="fly_top"><a href="#">合同执行汇总表</a></li>
								<li class="fly_end"><a href="#">合同执行明细表</a></li>
							</ul></li>
						<li class="fly"><a href="#">合同变更表</a>
							<ul>
								<li class="fly_top"><a href="#">合同变更汇总表</a></li>
								<li><a href="#">合同概要变更明细表</a></li>
								<li><a href="#">合同标的变更明细表</a></li>
								<li class="fly_end"><a href="#">合同条款变更明细表</a></li>
							</ul></li>
						<li><a href="#">合同履行跟踪表</a></li>
						<li><a href="#">合同收付款分析表</a></li>
						<li class="fly_end"><a href="#">合同收付款进展表</a></li>
					</ul></li>
			</ul></li>
		<s:include value="head_project.jsp"></s:include>
		<s:include value="head_inv.jsp"></s:include>
		<s:include value="head_qm.jsp"></s:include>
		<s:include value="head_outsourcing.jsp"></s:include>
		<s:include value="head_dtbcenter.jsp"></s:include>
		<s:include value="head_inventoryaccounting.jsp"></s:include>
	</ul></li>