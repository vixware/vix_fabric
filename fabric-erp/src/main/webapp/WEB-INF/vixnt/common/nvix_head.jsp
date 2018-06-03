<%@page import="com.vix.common.security.util.SecurityUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="menuId" value="${menuId}" />
<aside id="left-panel">
	<!-- User info -->
	<div class="login-info">
		<span> <a href="javascript:void(0);" id="show-shortcut" data-action="toggleShortcut"> <s:if test="userInfo.employee.headImgUrl!=null">
					<img src="${userInfo.employee.headImgUrl}" />
				</s:if> <s:else>
					<img src="${nvix}/vixntcommon/base/img/avatars/sunny.png" />
				</s:else> <span> ${userInfo.account}</span> <i class="fa fa-angle-down"></i>
		</a>
		</span>
	</div>
	<nav>
		<%
			String userName = SecurityUtil.getCurrentUserName();
			if (SecurityUtil.isSuperAdmin()) {
		%>
		<%
			} else if (!userName.startsWith("test") && !userName.startsWith("gw")) {
		%>
		<ul>
			<s:iterator var="menu1" value="#session.userMenu">
				<c:choose>
					<c:when test="${menu1.authorityCode == 'nv_sy_002'}">
						<li><a href="${nvix}${menu1.menuUrl}" target="_blank"><i class="fa fa-lg fa-fw ${menu1.iconClass}"></i> <span class="menu-item-parent">收银管理</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#" <c:if test="${menu1.menuUrl!=null}">onclick="loadContent('','${nvix}${menu1.menuUrl}');"</c:if>><c:if test="${menu1.iconClass!=null}">
									<i class="fa fa-lg fa-fw ${menu1.iconClass}"></i>
								</c:if><span class="menu-item-parent"><s:property value="%{#menu1.name}" /></span></a> <s:if test="%{#menu1.children!=null && #menu1.children.size>0}">
								<ul>
									<s:iterator var="menu2" value="#menu1.children">
										<c:set var="menu" value="${menu2}" scope="request"></c:set>
										<jsp:include page="headMenu.jsp"></jsp:include>
									</s:iterator>
								</ul>
							</s:if></li>
					</c:otherwise>
				</c:choose>
			</s:iterator>
		</ul>
		<%
			} else {
		%>
		<ul>
			<li><a id="mid_index" href="#" title="工作台" onclick="loadContent('mid_index','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');"><i class="fa fa-lg fa-fw fa-desktop"></i> <span class="menu-item-parent">工作台</span></a></li>
			<!-- 协同办公 -->
			<s:include value="nvix_work.jsp"></s:include>
			<li><a id="mid_task" href="${nvix}/nvixnt/vixntPosAction!goList.action" target="_blank"><i class="fa fa-lg fa-fw fa-money"></i> <span class="menu-item-parent">收银管理</span></a></li>
			<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/storeOperationMonitoringAction!goList.action');"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">店铺运营监控</span></a></li>
			<!-- 会员管理 -->
			<s:include value="nvix_customer.jsp"></s:include>
			<!-- 会员营销 -->
			<s:include value="nvix_customerMarketing.jsp"></s:include>
			<%-- <s:include value="nvix_intelligentAnalysis.jsp"></s:include> --%>
			<!-- 短信管理 -->
			<s:include value="nvix_message.jsp"></s:include>
			<!-- 供应商管理 -->
			<s:include value="nvix_supplier.jsp"></s:include>
			<!-- 客户管理 -->
			<s:include value="nvix_crm.jsp"></s:include>
			<!-- 合同管理 -->
			<s:include value="nvix_contract.jsp"></s:include>
			<!-- 供应链管理 -->
			<s:include value="nvix_scm.jsp"></s:include>
			<!-- 生产管理 -->
			<s:include value="nvix_produce.jsp"></s:include>
			<!-- 项目 -->
			<li><a id="" href="#" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectList.action');" ><i class="fa fa-lg fa-fw fa-puzzle-piece"></i><span class="menu-item-parent">项目管理</span></a>
				<ul>
					<li><a id="mid_project" href="#" onclick="loadContent('mid_project','${nvix}/nvixnt/nvixProjectAction!goList.action');"> 项目列表管理</a></li>
					<li><a id="mid_projectDiscuss" href="${nvix}/nvixnt/nvixProjectAction!goProjectDiscuss.action" target="_blank">项目沟通管理</a></li>
					<li><a id="mid_projecttracingmanagement" href="#" onclick="loadContent('mid_projecttracingmanagement','${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');"> 项目跟踪管理</a></li>
					<li><a id="mid_projectunitmanagement" href="#" onclick="loadContent('mid_projectunitmanagement','${nvix}/nvixnt/nvixProjectAction!goProjectUnitManagement.action');"> 项目组织管理</a></li>
				</ul>
			</li>
			<s:include value="nvix_wx.jsp"></s:include>
			<s:include value="nvix_finance.jsp"></s:include>
			<s:include value="nvix_hr.jsp"></s:include>
			
			<li><a href="#"><i class="fa fa-lg fa-fw fa-cube"></i> <span class="menu-item-parent">商品管理</span></a>
				<ul>
					<li><a href="#"><i class="fa fa-cogs"></i>设置</a>
						<ul>
							<li><a href="#" onclick="loadContent('mdm_itemBrand','${nvix}/nvixnt/mdm/nvixntItemBrandAction!goList.action');"><i class="fa fa-fw icon-iconfont-productBrand"></i>商品品牌管理</a></li>
							<li><a href="#" onclick="loadContent('mdm_itemBrand','${nvix}/nvixnt/nvixItemTagAction!goList.action');"><i class="fa fa-fw icon-iconfont-productTagGroup"></i>商品标签管理</a></li>
							<li><a href="#" onclick="loadContent('mdm_itemBrand','${nvix}/nvixnt/mdm/nvixDimensionAction!goList.action');"><i class="fa fa-fw icon-iconfont-dimension"></i>维度管理</a></li>
						</ul></li>
					<li><a href="#" onclick="loadContent('mdm_itemCatalog','${nvix}/nvixnt/mdm/nvixntItemCatalogAction!goList.action');"><i class="fa fa-fw icon-iconfont-productCategory"></i>商品分类管理</a></li>
					<li><a href="#" onclick="loadContent('mdm_itemCatalog','${nvix}/nvixnt/mdm/nvixEcproductPackageAction!goList.action');"><i class="fa fa-fw icon-iconfont-productPackage"></i>商品包管理</a></li>
					<li><a href="#" id="" onclick="loadContent('mdm_items','${nvix}/nvixnt/mdm/nvixntItemAction!goList.action');"><i class="fa fa-fw icon-iconfont-product"></i>商品列表</a></li>
					<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntProductAssemblyAction!goList.action');"><i class="fa fa-fw icon-iconfont-bindProduct"></i>商品组装</a></li>
					<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntProductDisassemblyAction!goList.action');"><i class="fa fa-fw icon-iconfont-bindProduct"></i>商品拆装</a></li>
					<li><a href="#">商品定价</a>
						<ul>
							<li><a href="#" onclick="loadContent('mdm_purchasePrice','${nvix}/nvixnt/mdm/nvixntPurchasePriceAction!goList.action');">采购定价</a></li>
							<li><a href="#" onclick="loadContent('mdm_salePrice','${nvix}/nvixnt/mdm/nvixntSalePriceAction!goList.action');">销售定价</a></li>
						</ul></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-cube"></i> <span class="menu-item-parent">物料管理</span></a>
				<ul>
					<li><a href="#" id="" onclick="loadContent('mdm_items','${nvix}/nvixnt/mdm/nvixntMaterialAction!goList.action');">物料列表</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-cog"></i> <span class="menu-item-parent">大数据分析</span></a>
				<ul>
					<li><a id="sys_personalSet" href="${nvix}/nvixnt/nvixntHadoopAction!goList.action" >大数据分析1</a></li>
					<li><a id="sys_personalSet" href="${nvix}/nvixnt/nvixntHadoopAction!goList1.action" >大数据分析2</a></li>
					<li><a id="sys_personalSet" href="${nvix}/nvixnt/nvixntHadoopAction!goList2.action" >大数据分析3</a></li>
					<li><a id="sys_personalSet" href="${nvix}/nvixnt/nvixntHadoopAction!goList3.action" >大数据分析4</a></li>
				</ul>
			</li>
			<li><a href="#"><i class="fa fa-lg fa-fw fa-cog"></i> <span class="menu-item-parent">系统管理</span></a>
				<ul>
					<li><a id="sys_personalSet" href="#" onclick="loadContent('sys_personalSet','${nvix}/nvixnt/personalSetAction!goSaveOrUpdate.action');"><i class="fa fa-fw icon-iconfont-config"></i>个人设置</a></li>
					<li><a id="sys_wxpOrganizationUnit" href="#" onclick="loadContent('sys_wxpOrganizationUnit','${nvix}/nvixnt/wxpOrganizationUnitAction!goList.action');"><i class="fa fa-fw icon-iconfont-department"></i>部门管理</a></li>
					<li><a id="sys_wxpEmployee" href="#" onclick="loadContent('sys_wxpEmployee','${nvix}/nvixnt/wxpEmployeeAction!goList.action');"><i class="fa fa-fw icon-iconfont-employee"></i>员工管理</a></li>
					<li><a id="sys_wxprole" href="#" onclick="loadContent('sys_wxprole','${nvix}/nvixnt/nvixntRoleAction!goList.action');"><i class="fa fa-fw icon-iconfont-role"></i>角色管理</a></li>
					<li><a id="sys_userAccount" href="#" onclick="loadContent('sys_userAccount','${nvix}/nvixnt/nvixntUserAccountAction!goList.action');"><i class="fa fa-fw icon-iconfont-user"></i>账号管理</a></li>
					<li><a id="sys_orgPosition" href="#" onclick="loadContent('sys_orgPosition','${nvix}/nvixnt/nvixntOrgPositionAction!goList.action');"><i class="fa fa-fw icon-iconfont-industry"></i>岗位管理</a></li>
					<li><a id="" href="#" onclick=""><i class="fa fa-exclamation-triangle"></i>预警中心</a>
						<ul>
							<li><a href="#" id="sys_waringSource" onclick="loadContent('sys_waringSource','${nvix}/nvixnt/system/nvixntWarningSourceAction!goList.action');"><i class="fa fa-fw icon-iconfont-config"></i>预警源设置</a></li>
							<li><a href="#" id="sys_waringCenter" onclick="loadContent('sys_waringCenter','${nvix}/nvixnt/system/nvixntWaringCenterAction!goList.action');"><i class="fa fa-fw icon-iconfont-task"></i>预警和定时任务</a></li>
							<li><a href="#" id="sys_alertNotice" onclick="loadContent('sys_alertNotice','${nvix}/nvixnt/system/nvixntAlertNoticeAction!goList.action');"><i class="fa fa-address-book"></i>预警信息</a></li>
						</ul></li>
					<li><a id="" href="#"><i class="fa fa-cogs"></i>基础设置</a>
						<ul>
							<li><a href="#" id="sys_systemVar" onclick="loadContent('sys_systemVar','${nvix}/nvixnt/system/nvixntSystemVarAction!goList.action');"><i class="fa fa-fw icon-iconfont-config"></i>系统常量管理</a></li>
							<li><a href="#" id="sys_vixntStoreSet" onclick="loadContent('sys_vixntStoreSet','${nvix}/nvixnt/vixntStoreSetAction!goList.action');"><i class="fa fa-fw icon-iconfont-shop"></i>店铺接口配置 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/system/nvixntOrganizationUnitTypeAction!goList.action');"><i class="fa fa-fw icon-iconfont-shop"></i>部门类型设置 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntEcSetAction!goList.action');"><i class="fa fa-fw icon-iconfont-ecConfig"></i>电商同步配置 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/messageApiSetAction!goList.action');"><i class="fa fa-cogs"></i>短信接口配置 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/system/nvixntWebColumnAction!goList.action');"><i class="fa fa-fw icon-iconfont-config"></i>门户后台配置 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/system/nvixntTemplateAction!goList.action');"><i class="fa fa-fw icon-iconfont-webColumn"></i>工作台模板管理 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/system/userApplicationAction!goList.action');"><i class="fa fa-sitemap"></i>用户申请管理 </a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntCodeAction!goList.action');"><i class="fa fa-fw icon-iconfont-billCodeRule"></i>编码管理</a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/system/vixntBillRecycleBinAction!goList.action');"><i class="fa fa-fw icon-iconfont-billCodeRule"></i>退货单回收站</a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixntMockupUsersAction!goList.action');">用户管理</a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntFabricAction!goList.action');">用户登录</a></li>
							<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntFabricBillAction!goList.action');">票据管理</a></li>
							<li><a href="#"><i class="fa fa-fw icon-iconfont-enumeration"></i>字典管理</a>
								<ul>
									<li><a href="#" id="sys_addressInfo" onclick="loadContent('sys_addressInfo','${nvix}/nvixnt/system/nvixntAddressInfoAction!goList.action');"><i class="fa fa-fw icon-iconfont-deliveryAddress"></i>地址管理</a></li>
									<li><a href="#" id="sys_currencyType" onclick="loadContent('sys_currencyType','${nvix}/nvixnt/system/nvixntCurrencyTypeAction!goList.action');"><i class="fa fa-fw icon-iconfont-currencyType"></i>币种管理</a></li>
									<li><a href="#" id="sys_exchangeRate" onclick="loadContent('sys_exchangeRate','${nvix}/nvixnt/system/nvixntExchangeRateAction!goList.action');"><i class="fa fa-cogs"></i>汇率</a></li>
									<li><a href="#" id="sys_taxRate" onclick="loadContent('sys_taxRate','${nvix}/nvixnt/system/nvixntTaxRateAction!goList.action');"><i class="fa fa-cogs"></i>税率</a></li>
									<li><a href="#" id="sys_regional" onclick="loadContent('sys_regional','${nvix}/nvixnt/system/nvixntRegionalAction!goList.action');"><i class="fa fa-map-marker"></i>地域</a>
									<li><a href="#" id="sys_measureUnitGroup" onclick="loadContent('sys_measureUnitGroup','${nvix}/nvixnt/system/nvixntMeasureUnitGroupAction!goList.action');"><i class="fa fa-balance-scale"></i>计量单位</a>
								</ul></li>
							<li><a href="#"><i class="fa fa-cogs"></i>对象类型</a>
								<ul>
									<li><a href="#" id="sys_enumerationCategory" onclick="loadContent('sys_enumerationCategory','${nvix}/nvixnt/system/nvixEnumerationCategoryAction!goList.action');"><i class="fa fa-fw icon-iconfont-enumerationCategory"></i>引用字典分类</a></li>
									<li><a href="#" id="sys_enumeration" onclick="loadContent('sys_enumeration','${nvix}/nvixnt/system/nvixEnumerationAction!goList.action');"><i class="fa fa-fw icon-iconfont-enumeration"></i>引用字典管理</a></li>
									<li><a href="#" id="sys_objectExpand" onclick="loadContent('sys_objectExpand','${nvix}/nvixnt/system/nvixObjectExpandAction!goList.action');"><i class="fa fa-fw icon-iconfont-productExpandField"></i>扩展类型管理</a>
									<li><a href="#" id="sys_specification" onclick="loadContent('sys_specification','${nvix}/nvixnt/system/nvixSpecificationAction!goList.action');"><i class="fa fa-square-o"></i>商品规格管理</a>
								</ul></li>
						</ul></li>
					<li><a id="sys_userFeedback" href="#" onclick="loadContent('sys_userFeedback','${nvix}/nvixnt/system/nvixntUserFeedbackAction!goList.action');"><i class="fa fa-user"></i>用户反馈</a></li>
				</ul></li>
		</ul>
		<%
			}
		%>
	</nav>
	<span class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i>
	</span>
</aside>