<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_option.png" alt="" /> <s:text name="cmn_supplyChain" /> </a></li>
				<li><a href="#">分销管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#">分销选项</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_left_title">
			<h2>选项设置</h2>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">业务</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">预警</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np',event)">价格</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<form method="get">
						<div class="nt">
							<div class="nt_title">基础选项</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr>
											<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" /> 普通业务必须要有订单</label></td>
											<td><label><input type="checkbox" name="DirectShipmentBizMustHaveOrders" /> 直运业务必须要有订单</label></td>
										</tr>
										<tr>
											<td><label><input type="checkbox" name="ConsignmentBizMustHaveOrders" /> 受托代销业务必须要有订单</label></td>
											<td><label><input type="checkbox" name="EnableConsignment" /> 启用受托代销</label></td>
										</tr>
										<tr>
											<td><label><input type="checkbox" name="WhetherToAllowUltraOrderArrivalAndStorage" /> 是否允许超订单到货及入库</label></td>
											<td><label><input type="checkbox" name="WhetherToAllowUltraPlannedOrders" /> 是否允许超计划订货</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="np_clist" id="np2" style="display: none; height: 260px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">
								<input type="checkbox" name="ChangingTaxRates" />&nbsp;预警和报警
							</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr>
											<td align="center"><label>提前预警天数&nbsp;<input type="text" name="AdvanceWarningTime" class="ipt" />&nbsp;天
											</label></td>
											<td align="center"><label>逾期报警天数&nbsp;<input type="text" name="EarlyDaysOverdue" class="ipt" />&nbsp;天
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="np_clist" id="np3" style="display: none; height: 260px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">基本权限控制</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr>
											<td><label><input type="checkbox" name="CheckInventoryPermissions" /> 检查存货权限</label></td>
											<td><label><input type="checkbox" name="CheckSuppliersPermissions" />检查供应商权限</label></td>
										</tr>
										<tr>
											<td><label><input type="checkbox" name="InspectionDepartmentPermission" /> 检查部门权限</label></td>
											<td><label><input type="checkbox" name="CheckSalesmanPermissions" /> 检查业务员权限</label></td>
										</tr>
										<tr>
											<td><label><input type="checkbox" name="CheckTheOperatorPrivileges" /> 检查操作员权限</label></td>
											<td><label><input type="checkbox" name="CheckTheAmountOfAuditPermissions" /> 检查金额审核权限</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>