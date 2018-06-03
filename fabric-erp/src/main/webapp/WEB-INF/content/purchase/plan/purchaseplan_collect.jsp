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
	function choosePurchaseOrder() {
		$.ajax({
		url : '${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanList.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 900,
			height : 550,
			title : "选择订单",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						$("#purchaseOrderid").val(returnValue);
						$.ajax({
							url : '${vix}/purchase/purchaseApproveAction!collectPurchasePlan.action?purchasePlanIds=' + returnValue + "&id=${purchasePlanPackage.id}",
							cache : false,
							success : function(result) {
								showMessage(result);
								setTimeout("", 1000);
								$('#dlAddress2').datagrid("reload");
							}
							});
					} else {
						asyncbox.success("请选择!", "<s:text name='vix_message'/>");
						return false;
					}
				}
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
<input type="hidden" id="planType" name="planType" value="P" />
<input type="hidden" id="status" name="status" value="${purchasePlanPackage.status }" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePurchasePlanPackage();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a> <a
						onclick="loadContent('${vix}/purchase/purchaseApproveAction!goList.action?pageNo=${pageNo}');" href="#"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
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
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${purchasePlanPackage.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${purchasePlanPackage.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">创建人：</td>
											<td><input id="creator" name="creator" value="${purchasePlanPackage.creator }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">创建时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlanPackage.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"><input class="btn" type="button" value="选取计划" onclick="choosePurchaseOrder();" /></td>
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />采购计划</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#dlAddress2').datagrid({
							url : '${vix}/purchase/purchaseApproveAction!getPurchasePlanJson.action?id=${purchasePlanPackage.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'purchasePlanCode',
							title : '计划编码',
							width : 250,
							align : 'center'
							}, {
							field : 'purchasePlanName',
							title : '计划主题',
							width : 200,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'amount',
							title : '采购数量',
							width : 200,
							align : 'center'
							}, {
							field : 'price',
							title : '采购金额',
							width : 200,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'creator',
							title : '编制人',
							width : 200,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							} ] ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
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