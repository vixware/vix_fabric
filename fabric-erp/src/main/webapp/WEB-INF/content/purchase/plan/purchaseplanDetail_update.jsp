<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function loadCurrentExpenseDetail() {
		$.ajax({
		url : '${vix}/purchase/purchasePlanAction!goPurchasePlanDetailList.action?purchasePlanId=' + $("#purchasePlanId").val(),
		cache : false,
		success : function(html) {
			$("#currentExpenseDetailTable").html(html);
		}
		});
	};
	loadCurrentExpenseDetail();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">采购管理 </a></li>
				<li><a href="#">采购计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action');">采购计划版本</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="purchasePlanId" name="purchasePlanId" value="${purchasePlanId}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>采购计划版本信息</b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>编写人：</th>
											<td><input id="creator" name="creator" value="${purchasePlan.creator }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>编写日期：</th>
											<td><input id="createTime" name="createTime" value="${purchasePlan.createTime}" type="text" class="ipt w88per underline" readonly="readonly" /></td>
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
							<li class="current"><a onclick="javascript:tab(2,1,'a',event);loadCurrentExpenseDetail();"><img alt="" src="img/mail.png">版本列表</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="display: block;">
						<div class="list_table" style="margin: 0; width: 100%"></div>
						<div style="padding: 8px;">
							<div id="currentExpenseDetailTable" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>