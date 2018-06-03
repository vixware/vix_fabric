<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	if ($(".ms").length > 0) {
		$(".ms").hover(function() {
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">ul", this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function() {
			$(">a", this).addClass("hover");
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">a", this).removeClass("hover");
			$(">ul", this).stop().slideUp(100);
		});
	}
	function goShowBeforeAndAfterPurchaseOrder(id, str) {
		$.ajax({
		url : '${vix}/purchase/purchaseApproveAction!goShowBeforeAndAfterPurchaseOrder.action?id=' + id + "&str=" + str,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function initdata(){
		//设置单据类型选中(修改)
		$("#packageType").val('${purchasePlanPackage.packageType}');
		if(${null == purchasePlanPackage.createTime }){
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
		}
	}
	initdata();
	function loadTravelExpenseDetail() {
		$.ajax({
		url :  '${vix}/purchase/purchaseApproveAction!goListContent.action?id=' + $("#purchasePlanPackageId").val(),
		cache : false,
		success : function(html) {
			$("#purchasePlan").html(html);
		}
		});
	};
	loadTravelExpenseDetail();
	
	function goSaveOrUpdatePurchasePlan(id) {
		$.ajax({
			  url:'${vix}/purchase/purchaseApproveAction!goSaveOrUpdate.action?id='+id,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 950,
						height : 600,
						title:"采购计划",
						html:html,
						callback : function(action){
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">采购管理 </a></li>
				<li><a href="#" onclick="">计划汇总 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="purchasePlanPackageId" name="purchasePlanPackageId" value="${purchasePlanPackage.id }" />
<input type="hidden" id="planType" name="planType" value="${purchasePlanPackage.planType }" />
<input type="hidden" id="status" name="status" value="${purchasePlanPackage.status }" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePurchasePlanPackage(0);" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <s:if test="isAllowAudit == 1">
							<a onclick="subPurchasePlanPackage('${purchasePlanPackage.id}');" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
						</s:if> <a onclick="loadContent('${vix}/purchase/purchaseApproveAction!goList.action?pageNo=${pageNo}');" href="#"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>计划汇总</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${purchasePlanPackage.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">上报类型：</td>
											<td><select id="packageType" name="packageType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="">部门</option>
													<option value="E">人员</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">创建人：</td>
											<td><input id="creator" name="creator" value="${purchasePlanPackage.creator }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">创建时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlanPackage.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a><img alt="" src="img/mail.png">采购计划</a></li>
						</ul>
					</div>
					<div class="right_content" style="display: block;">
						<div style="padding: 8px;">
							<div id="purchasePlan" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">采购数量：</td>
											<td><input id="amount" name="amount" value="${purchasePlanPackage.amount }" type="text" size="30" /></td>
											<td align="right">采购金额：</td>
											<td><input id="price" name="price" value="${purchasePlanPackage.price }" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>