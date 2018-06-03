<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
	</h2>
	<div id="breadCrumb" class="breadCrumb module">
		<ul>
			<li><a href="#"><img src="${vix}/common/img/dtbcenter_transportation_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_transpotmgr" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_shippingManagement_business" /> </a></li>
		</ul>
	</div>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCustomer()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/shippingManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_shippingManagement" /> </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong><s:text name="inventory_basicinfo" /> </strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>单号：</th>
											<td><input id="directCost" name="directCost" value="${saleOrderCost.directCost}" class="validate[required] text-input" type="text" size="30" /></td>
											<th>直接成本：</th>
											<td><input id="directCost" name="directCost" value="${saleOrderCost.directCost}" class="validate[required] text-input" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>间接成本：</th>
											<td><input id="indirectCost" name="indirectCost" value="${saleOrderCost.indirectCost}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>费用合计：</th>
											<td><input id="total" name="total" value="${saleOrderCost.total }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>单位：</th>
											<td><input id="unit" name="unit" value="${saleOrderCost.unit }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
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
