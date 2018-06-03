<%@page import="com.vix.common.security.util.SecurityUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function loadContent(url, mainMenuId) {
		if (null != mainMenuId) {
			$(".nav_current").attr("class", "nav_arrow");
			$("#" + mainMenuId).attr("class", "nav_current nav_arrow");
		}
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
			$("#mainContent").trigger('main_content_load');
		}
		});
	}

	/**
	 * 加载内容到div
	 */
	function loadDivContent(divContent, url) {
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#" + divContent).html(html);
		}
		});
	}

	/**
	 * 下载附件
	 */
	function downloadVixFile(fileId) {
		var url = "${vix}/common/model/fileAction!checkFileExist.action";
		var downUrl = "${vix}/common/model/fileAction!download.action";// fileId="+fileId;
		var downParam = "fileId=" + fileId;
		$.ajax({
		type : "POST",
		async : false,//修改表单提交为同步
		url : url,
		data : "fileId=" + fileId,
		success : function(result) {
			var success = result.success;
			var msgTmp = result.message;
			if (!success) {
				asyncbox.alert(msgTmp, "错误");
			} else {
				if (Get_IE_Version() == 6)
					window.open(downUrl + '?' + downParam);
				else if (isFireFox()) {
					document.location.href = downUrl + "?" + downParam;
				} else {
					postWindow(downUrl, downParam);
				}
			}

		}
		});
	}

	function postWindow(url, param) {
		var postUrl = url;
		var postData = param;
		var iframe = document.getElementById("postData_iframe");
		if (!iframe) {
			iframe = document.createElement("iframe");
			iframe.id = "postData_iframe";
			iframe.scr = "about:blank";
			iframe.frameborder = "0";
			iframe.style.width = "0px";
			iframe.style.height = "0px";
			var form = document.createElement("form");
			form.id = "postData_form";
			form.method = "post";
			form.target = "_blank";

			document.body.appendChild(iframe);
			iframe.contentWindow.document.write("<body>" + form.outerHTML + "</body>");

		}

		var paramsArray = new Array();
		paramsArray = param.split("&");
		var field_input = "";

		for (i = 0; i < paramsArray.length; i++) {
			var paramTemp = paramsArray[i];
			var paramTempOne = new Array();
			paramTempOne = paramTemp.split("=");
			;
			var paramTempName = paramTempOne[0];
			var paramTempValue = paramTempOne[1];
			field_input = field_input + " <input type=\"hidden\" name=\"" + paramTempName+ "\" value=\"" + paramTempValue +"\" >";
		}

		iframe.contentWindow.document.getElementById("postData_form").innerHTML = field_input;

		iframe.contentWindow.document.getElementById("postData_form").action = postUrl;
		iframe.contentWindow.document.getElementById("postData_form").submit();
	}

	/**
	 * 删除附件
	 */
	function deleteVixFile(fileId, divId, dataId, businessTag, fileAttType) {
		var fileDeleteUrl = '${vix}/common/model/fileAction!deleteFile.action';
		var paramStr = "fileId=" + fileId;
		$.ajax({
		type : "POST",
		async : false,//修改表单提交为同步
		url : fileDeleteUrl,
		data : paramStr,
		success : function(result) {
			var success = result.success;
			var msgTmp = result.message;
			if (!success) {
				asyncbox.alert(msgTmp, "错误");
			} else {
				var paramTmp = "dataId=" + dataId + "&businessTag=" + businessTag + "&fileAttType=" + fileAttType + "&divId=" + divId;
				loadDivContent(divId, '${vix}/common/model/fileAction!findBizFileList.action?' + paramTmp);
			}
		}
		});
	}
	function isFireFox() {
		if (navigator.userAgent.indexOf("Firefox") > 0) {
			return true;
		}
		return false;
	}
	function Get_IE_Version() {
		if (!document.all)
			return 0;
		var v;
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0)//IE 6.0
		{
			v = 6;
		} else if (navigator.userAgent.indexOf("MSIE 7.0") > 0)//IE 7.0 
		{
			v = 7
		} else if (navigator.userAgent.indexOf("MSIE 8.0") > 0)//IE 8.0
		{
			v = 8;
		}
		return v;
	}
	function goAbout() {
		asyncbox.open({
		modal : true,
		width : 780,
		height : 460,
		title : "关于",
		url : "${vix}/common/vixAction!goAbout.action"
		});
	}
	$(document).ready(function(showsamount) {
		$.ajax({
		url : '${vix}/system/latestOperateAction!goList.action?showsamount=' + showsamount,
		cache : false,
		success : function(html) {
			$("#latestOperate").html(html);
		}
		});
	});
</script>
<input type="hidden" id="menuId" value="${menuId}" />
<div class="head">
	<div>
		<h1 id="logo">
			<a href="${vix}/common/vixAction!goIndex.action?isHomePage=1"><img src="${vix}/common/img/mmxlogo.png" alt="" onclick="" /> </a>
		</h1>
		<div class="menu">
			<strong id="menu"> <i>欢迎,<b>${userInfo.account}</b>
			</i> <em>│<a href="${vix}/logout">注销</a>
			</em> <em class="menu_hidd">│<a href="#" onclick="loadContent('${vix}/hr/hrEmpstaffselfAction!goSaveOrUpdate.action');">员工自助</a>│<a href="#">支持平台</a>│<a href="#" onclick="goAbout();">关于我们</a>
			</em>
			</strong> <span id="menu_btn"></span>
		</div>
		<div id="nav" class="drop_nav">
			<div id="navHideButton"></div>
			<ul id="nav_menu">

				<%
					String userName = SecurityUtil.getCurrentUserName();
					if (!userName.equalsIgnoreCase("admin") && !userName.startsWith("test")) {
				%>

				<s:iterator var="menu1" value="#session.userMenu">
					<li class="nav_arrow"><a href="#"><span><s:property value="%{#menu1.name}" /></span></a> <s:if test="%{#menu1.children!=null && #menu1.children.size>0}">
							<ul>
								<s:iterator var="menu2" value="#menu1.children">
									<c:set var="menu" value="${menu2}" scope="request"></c:set>
									<jsp:include page="headMenu.jsp"></jsp:include>
								</s:iterator>
							</ul>
						</s:if></li>
				</s:iterator>
				<%
					} else {
				%>
				<li class="nav_current nav_arrow" id="bg_01"><a href="/vix/common/vixAction!goIndex.action"><span>工作台</span> </a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goToDo.action','bg_01');" title="待办事宜">待办事宜</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/canlendarAction!goCanlendar.action','bg_01');">日程安排</a></li>
						<%-- <li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goOECC.action','bg_01');">日清管理</a></li>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goTaskCenter.action','bg_01');">任务中心</a></li> --%>
						<li><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goBulletInboardNotices.action','bg_01');">通知公告</a></li>
						<%-- <li><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');">邮件</a></li> --%>
						<%-- <li><a href="#" onclick="loadContent('${vix}/common/contactsAction!goList.action');">通讯录</a></li> --%>
						<li><a href="#" onclick="loadContent('${vix}/system/remindsCenterAction!goList.action','bg_01');">预警中心</a></li>
					</ul></li>
				<!-- 供应链 -->
				<li class="nav_arrow" id="bg_02"><a href="#"><span>门店管理</span></a>
					<ul>
						<li class="fly"><a href="#">设置</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/chain/chainConfigAction!goConfig.action','bg_02');">参数配置</a></li>
							</ul></li>
						<li class="fly"><a href="#">门店基础信息</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/drp/storeInformationAction!goList.action?source=chain','bg_02');">门店信息</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/storePersonnelinformationAction!goList.action?source=chain','bg_02');">门店人员信息</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/accountManagementAction!goList.action?source=chain','bg_02');">门店账号管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/ridesAction!goList.action','bg_02');">游乐设施管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/vixLogAction!goList.action','bg_02');">操作日志</a></li>
							</ul></li>
						<li class="fly"><a href="#">门店业务管理</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=mdm','bg_02');">内部客户管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action','bg_02');">门店销售订单</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/storeEnquiryrequestAction!goList.action?source=chain','bg_02');">门店要货请求</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/storeSalesrecordAction!goList.action?source=chain','bg_02');">门店销售记录</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/storeReceivingrecordsAction!goList.action?source=chain','bg_02');">门店收货记录</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/storeReturnRecordsAction!goList.action?source=chain','bg_02');">门店退货记录</a></li>
							</ul></li>
						<li class="fly"><a href="#">门店数据查询</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/chain/counterSalesRecordsAction!goList.action?source=chain','bg_02');">门店吧台销售记录</a></li>
								<li><a href="#" onclick="loadContent('${vix}/chain/tranLogAction!goList.action?source=chain','bg_02');">门店机台消费记录</a></li>
								<li><a href="#" onclick="loadContent('${vix}/chain/customerLogAction!goList.action?source=chain','bg_02');">门店会员日账</a></li>
							</ul></li>
						<li class="fly" icon="${vix}/common/img/mmxrepair.png"><a href="#">设备报修</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/chain/mmxEquipmentAction!repairMgr.action','bg_02');">报修单</a></li>
								<li><a href="#" onclick="loadContent('${vix}/chain/mmxEquipmentAction!repairFeedBackMgr.action','bg_02');">维修反馈</a></li>
								<li><a href="#" onclick="loadContent('${vix}/chain/mmxEquipmentAction!repairArchiveMgr.action','bg_02');">维修工作完成</a></li>
							</ul></li>
						<li class="fly"><a href="#">会员管理</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/drp/membershipInformationAction!goList.action','bg_02');">会员信息</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/membershipCardmanagementAction!goList.action','bg_02');">会员卡管理</a></li>
								<li class="fly"><a href="#">会员积分信息</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/drp/membershipPointsregistrationAction!goList.action','bg_02');">会员积分记录</a></li>
										<li><a href="#" onclick="loadContent('${vix}/drp/expiredIntegralAction!goList.action','bg_02');">过期积分记录</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/memberPointsforAction!goList.action','bg_02');">会员积分兑换</a></li>
							</ul></li>
						<li class="fly"><a href="#">积分管理</a>
							<ul>
								<%-- <li><a href="#" onclick="loadContent('${vix}/drp/integralRulesSetAction!goList.action');">积分规则设置</a></li> --%>
								<li><a href="#" onclick="loadContent('${vix}/drp/reDeemAction!goList.action','bg_02');">积分兑换</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/setRedeemGoodsAction!goList.action?source=drp','bg_02');">设置可积分兑换商品</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/integralManagementsubsidiaryAction!goList.action','bg_02');">积分管理明细</a></li>
							</ul></li>
						<%-- <li class="fly"><a href="#">促销活动管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignAction!goList.action');">营销活动列表</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/activityPlanAction!goList.action');">营销活动计划</a></li>
								<li><a href="#" onclick="loadContent('${vix}/drp/marketingExecutiveAction!goList.action');">营销执行管理</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignTrackingAction!goList.action');">营销活动追踪</a></li>
							</ul></li> --%>
						<li class="fly"><a href="#">结算管理</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/chain/productViewAction!goList.action','bg_02');">商品视图</a></li>
								<li><a href="#" onclick="loadContent('${vix}/chain/suppliersViewAction!goList.action','bg_02');">门店视图</a></li>
							</ul></li>
						<li class="fly_end"><a href="#" onclick="loadContent('${vix}/chain/playgroundManagementStatisticsAction!goList.action','bg_02');">游乐场运营管理统计</a></li>
					</ul></li>
				<li class="nav_arrow" id="bg_03"><a href="#"><span>采购管理</span></a>
					<ul>
						<li><a href="#" onclick="loadContent('${vix}/srm/managementBusinessPartnerAction!goList.action','bg_03');">供应商清单</a></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_03');">采购计划</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goSaveOrUpdate.action','bg_03');">新增</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_03');">列表</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_03');">采购申请</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goSaveOrUpdate.action','bg_03');">新增</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_03');">列表</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_03');">采购订单</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goSaveOrUpdate.action','bg_03');">新增</a></li>
								<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_03');">列表</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_03');">到货管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goSaveOrUpdate.action','bg_03');">新增</a></li>
								<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_03');">列表</a></li>
								<li class="fly_end"><a href="#">到货退回单</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goList.action','bg_03');">采购入库</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goSaveOrUpdate.action','bg_03');">新增</a></li>
								<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goList.action','bg_03');">列表</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goScarletLetterInbound.action','bg_03');">红字入库单</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goList.action','bg_03');">采购退货</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goSaveOrUpdate.action','bg_03');">新增</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goList.action','bg_03');">列表</a></li>
							</ul></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action','bg_03');">现存量查询</a></li>
					</ul></li>
				<li class="nav_arrow" id="bg_04"><a href="#"><span>库存管理</span></a>
					<ul>
						<li class="fly"><a href="#">初始设置</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/inventory/optionAction!goList.action','bg_04');">选项</a></li>
								<li><a href="#" onclick="loadContent('${vix}/inventory/optionAction!goSetting.action','bg_04');">字典管理</a></li>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goList.action','bg_04');">仓库管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/inventory/warehouseAction!goList.action','bg_04');">仓库列表</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/inventory/initInventoryAction!goList.action','bg_04');">期初录入</a></li>
								<li><a href="#" onclick="loadContent('${vix}/inventory/beginDefectiveItemAction!goList.action','bg_04');">期初不合格品</a></li>
								<li class="fly"><a href="#">保质期管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/shelfLifeAction!goList.action','bg_04');">快到期库存查询</a></li>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="">商品拆装管理业务</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/productAssemblyAction!goList.action','bg_04');">商品组装</a></li>
								<li><a href="#" onclick="loadContent('${vix}/inventory/productDisassemblyAction!goList.action','bg_04');">商品拆装</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_04');">入库业务</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goSaveOrUpdate.action','bg_04');">采购入库单</a></li>
								<li><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goScarletLetterInbound.action','bg_04');">红字入库单</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goPrintOrder.action','bg_04');">单据打印</a></li>
							</ul></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_04');">出库业务</a></li>
						<li><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action','bg_04');">台账业务</a></li>
						<li class="fly"><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_04');">盘点业务</a>
							<ul>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_04');">列表</a></li>
							</ul></li>
					</ul></li>
				<li class="nav_arrow" id="bg_05"><a href="#"><span>协同办公</span> </a>
					<ul>
						<li class="fly"><a href="#">个人办公</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/oa/messageManagementAction!goList.action','bg_05');">消息管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/announcementAction!goList.action','bg_05');">公告通知</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/newsManagementAction!goList.action','bg_05');">新闻</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/scHeduleAction!goList.action','bg_05');">日程安排</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/workLogAction!goList.action','bg_05');">工作日志</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/personalAttendanceAction!goList.action','bg_05');">个人考勤</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/wabAction!goList.action','bg_05');">通讯簿</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/privateCabinetAction!goList.action','bg_05');">个人文件柜</a></li>
							</ul></li>
						<li class="fly"><a href="#">任务管理</a>
							<ul>
								<li class="fly"><a href="#">初始设置</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/taskSourceTypeAction!goList.action','bg_05');">任务来源</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/taskTypeAction!goList.action','bg_05');">任务类型</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/difficultyCoefficientAction!goList.action','bg_05');">难度系数</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/taskLevelAction!goList.action','bg_05');">任务层级</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/completionMarkAction!goList.action','bg_05');">完成标志</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/progressSituationAction!goList.action','bg_05');">进度情况</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/nissinManagementAction!goList.action','bg_05');">日清管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goSaveOrUpdate.action','bg_05');">任务定义</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action','bg_05');">任务列表</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/myTasksAction!goList.action','bg_05');">我的任务</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/viewProceduresAction!goList.action','bg_05');">任务过程查看</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/taskPandectAction!goList.action','bg_05');">任务总览</a></li>
							</ul></li>
						<li class="fly"><a href="#">行政办公</a>
							<ul>
								<li><a href="#" onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action','bg_05');">公告通知管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/trendsAction!goList.action','bg_05');">新闻管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/votingManagementAction!goList.action','bg_05');">投票管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/scheduleQueriesAction!goList.action','bg_05');">日程安排管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/workLogqueryAction!goList.action','bg_05');">工作日志管理</a></li>
								<li class="fly"><a href="#">工作计划</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/workPlanAction!goList.action','bg_05');">工作计划查询</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action','bg_05');">工作计划管理</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/projectSettingsAction!goListType.action','bg_05');">工作计划类型设置</a></li>
									</ul></li>
								<li class="fly"><a href="#">办公用品管理</a>
									<ul>
										<li class="fly"><a href="#">初始设置</a>
											<ul>
												<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesTypeAction!goList.action','bg_05');">办公用品类别</a></li>
											</ul></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesAction!goList.action','bg_05');">办公用品台帐</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/officeSuppliesRegisterAction!goList.action','bg_05');">公用品业务管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">图书管理</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/bookEntryAction!goList.action','bg_05');">图书录入</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/bookLibrarianAction!goList.action','bg_05');">借还书管理</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/resourceRequestAction!goList.action','bg_05');">资源申请管理</a></li>
								<li class="fly"><a href="#">会议申请安排</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/oa/meetingRequestAction!goList.action','bg_05');">会议室管理</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/applicationAction!goList.action','bg_05');">会议管理</a>
										<li><a href="#" onclick="loadContent('${vix}/oa/meetingDeviceAction!goList.action','bg_05');">会议室设备管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">车辆申请安排</a>
									<ul>
										<li class="fly"><a href="#">初始设置</a>
											<ul>
												<li><a href="#" onclick="loadContent('${vix}/oa/vehicleTypeAction!goListType.action','bg_05');">车辆类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/vehicleColorAction!goListType.action','bg_05');">车辆颜色</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/transmissionTypeAction!goListType.action','bg_05');">变速器类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/engineTypeAction!goListType.action','bg_05');">引擎类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/displacementTypeAction!goListType.action','bg_05');">排量类型</a></li>
												<li><a href="#" onclick="loadContent('${vix}/oa/maintenanceTypeAction!goListType.action','bg_05');">维护类型</a></li>
											</ul></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/carUseAction!goList.action','bg_05');">车辆申请管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/carEmployAction!goList.action','bg_05');">车辆使用管理</a></li>
										<%-- <li><a href="#" onclick="loadContent('${vix}/oa/carApprovalAction!goList.action');">车辆审批管理</a></li> --%>
										<li><a href="#" onclick="loadContent('${vix}/oa/carMaintenanceAction!goList.action','bg_05');">车辆维护管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/requisitionCarAction!goList.action','bg_05');">车辆信息管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/oa/tpkAction!goList.action','bg_05');">油耗统计</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/oa/commonWabAction!goList.action','bg_05');">公共通讯薄</a></li>
							</ul></li>
					</ul></li>
				<li id="systemManage" class="nav_arrow"><a href="#"><span>系统管理</span> </a>
					<ul>
						<li class="fly"><a href="#">基础设置</a>
							<ul>
								<li class="fly"><a href="#">编码管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/codeAction!goList.action','systemManage');">编码级数管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/codeAction!goCodeList.action','systemManage');">编码规则管理</a></li>
									</ul></li>
								<li class="fly"><a href="#">字典管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/currencyTypeAction!goList.action','systemManage');">币种管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/exchangeRateAction!goList.action','systemManage');">汇率</a></li>
										<%-- <li><a href="#" onclick="loadContent('${vix}/common/measureUnitAction!goList.action','systemManage');">计量单位</a></li> --%>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/common/measureUnitGroupAction!goList.action','systemManage');">计量单位</a></li>
									</ul></li>
								<li class="fly"><a href="#">对象类型</a>
									<ul>
										<li><a href="#" onclick="loadContent('${vix}/system/enumerationCategoryAction!goList.action','systemManage');">字典分类</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/enumerationAction!goList.action','systemManage');">字典管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/objectExpandAction!goList.action','systemManage');">类型管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/specificationAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}规格管理</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/system/precisionControlAction!goList.action','systemManage');">数据精度管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/billTypeManagementAction!goList.action','systemManage');">单据类型管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/billTypeSetAction!goList.action','systemManage');">单据类型设置</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/system/operatingParametersAction!goList.action','systemManage');">运行参数</a></li>
							</ul></li>
						<li class="fly"><a href="#">${vv:varView("vix_mdm_item")}管理</a>
							<ul>
								<li class="fly"><a href="###">设置</a>
									<ul>
										<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemGroupAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}组设置</a></li>
										<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemBrandAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}品牌</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemCatalogAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}分类管理</a></li>
								<li><a href="###" onclick="loadContent('${vix}/mdm/item/itemAction!goSaveOrUpdate.action?id=','systemManage');">新增${vv:varView("vix_mdm_item")}</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}列表</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/salePriceConditionAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}销售定价</a></li>
								<li><a href="#" onclick="loadContent('${vix}/mdm/item/purchasePriceConditionAction!goList.action','systemManage');">${vv:varView("vix_mdm_item")}采购定价</a></li>
							</ul></li>
						<li class="fly"><a href="#">组织架构管理</a>
							<ul>
								<li class="fly"><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action','systemManage');">企业组织机构管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/org/organizationAction!goList.action','systemManage');">组织架构</a></li>
										<li><a href="/vix/content/hr/pm/sm.jsp">组织架构图</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/organizationUnitAction!goList.action','systemManage');">部门管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/businessViewAction!goList.action','systemManage');">业务组织视图管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/org/businessOrgAction!goList.action','systemManage');">业务组织管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action','systemManage');">岗位管理</a></li>
										<!-- <li><a href="#">高级查询</a></li> -->
									</ul></li>
								<li><a href="#">用户管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/org/employeeOrgAction!goList.action','systemManage');">员工管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/advancedSearchAction!goSaveOrUpdate.action','systemManage');">高级查询</a></li>
								<li><a href="#" onclick="loadContent('${vix}/hr/position/orgPositionAction!goList.action','systemManage');">职位管理</a></li>
							</ul></li>
						<li class="fly"><a href="#">权限管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/security/roleAction!goList.action','systemManage');">角色管理</a>
								<li><a href="#" onclick="loadContent('${vix}/common/security/userAccountAction!goList.action','systemManage');">账号管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/authorityAction!goList.action','systemManage');">权限管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/resourceAction!goList.action','systemManage');">资源管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/dataColSecAction!goList.action','systemManage');">列级权限配置</a></li>
								<li><a href="#" onclick="loadContent('${vix}/common/security/dataColPolicyAction!goList.action','systemManage');">列级权限策略管理</a></li>
								<li class="fly"><a href="#">行级权限管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyObjAction!goList.action','systemManage');">行集权限策略配置管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowPolicyAction!goList.action','systemManage');">行集权限策略管理</a></li>
										<li><a href="#" onclick="loadContent('${vix}/common/security/dataResRowOwnerAction!goList.action','systemManage');">行集权限主体管理</a>
									</ul></li>
							</ul></li>
						<li class="fly"><a href="#">预警管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/remindsCenterAction!goList.action','systemManage');">提醒中心</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/warningSourceAction!goList.action');">预警源设置</a></li>
								<li class="fly_end"><a href="#" onclick="loadContent('${vix}/system/warningCenterAction!goList.action','systemManage');">预警和定时任务</a></li>
							</ul></li>
						<%-- <li class="fly"><a href="#">运行管理</a>
							<ul>
								<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/changeManagementAction!goList.action','systemManage');">变更管理</a></li>
								<li class="fly"><a href="#">维护管理</a>
									<ul>
										<li class="fly_top"><a href="#" onclick="loadContent('${vix}/system/onLineUpdateAction!goList.action','systemManage');">在线更新</a></li>
										<li><a href="#" onclick="loadContent('${vix}/system/issuesFeedbackAction!goList.action','systemManage');">问题反馈</a></li>
										<li class="fly_end"><a href="#" onclick="loadContent('${vix}/system/onLineAssistenceAction!goList.action','systemManage');">在线帮助</a></li>
									</ul></li>
								<li><a href="#" onclick="loadContent('${vix}/system/databaseManagementAction!goList.action','systemManage');">数据库管理</a></li>
								<li><a href="#" onclick="loadContent('${vix}/system/systemLogManagementAction!goList.action','systemManage');">系统日志</a></li>
							</ul></li> --%>
						<li><a href="#" onclick="goAbout();">关于</a></li>
					</ul></li>
				<li class="nav_arrow">
					<ul>
						<li><a onclick="javascript:change('点这里');"><img src="img/balloon.png" alt="" />点这里</a></li>
						<li class="fly"><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a>
							<ul>
								<li><a href="#"><img src="img/home.png" alt="" />新建选项</a></li>
								<li><a href="#"><img src="img/user.png" alt="" />新建选项</a></li>
								<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
								<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
							</ul></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />新建选项</a></li>
						<li><a href="#"><img src="img/balloon.png" alt="" />新建选项</a></li>
					</ul>
				</li>
				<%
					}
				%>
			</ul>
			<div id="nav_ico">
				<span><a href="#" onclick="loadContent('${vix}/system/personalInformationAction!goList.action');"><img src="img/user.png" alt="" /><small>个人设置</small> </a> </span> <span><a href="#" onclick="loadContent('${vix}/common/vixAction!goPersonalInformation.action');"><img src="img/wrench_screwdriver.png" alt="" /> </a> </span> <span><a
					href="#" onclick="loadContent('${vix}/common/contactsAction!goList.action');"><img src="img/address_book.png" alt="" /><small>通讯簿</small> </a> </span> <span><a href="#" onclick="loadContent('${vix}/common/mailAction!goMailList.action');"><img src="img/mail.png" alt="" /><small>邮件管理</small> </a> </span>
				<div id="nicemenu">
					<ul>
						<li><span class="head_menu"><img src="img/application_lightning.png" class="arrow" /></span>
							<div class="flick_menu" id="latestOperate">
								<s:iterator value="latestOperateEntityList" var="latestOperateEntity" status="s">
									<a href="#">${latestOperateEntity.objectType}</a>
								</s:iterator>
							</div></li>
						<li><span class="head_menu"><img src="img/recent_list.png" class="arrow" /> </span>
							<div class="flick_menu">
								<a href="index.html">用户中心</a> <a href="index.html">我的订单</a> <a href="index.html">我的流程</a> <a href="index.html">我的日程</a>
							</div></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>