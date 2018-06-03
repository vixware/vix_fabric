<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	/** 保存数据 */
	function saveOrUpdate() {
		if ($('#inventoryParametersForm').validationEngine('validate')) {
			$.post('${vix}/inventory/optionAction!saveOrUpdate.action', {
			'inventoryParameters.id' : $("#id").val(),
			'inventoryParameters.isAssemblyAndDisassembly' : $(":checkbox[name=isAssemblyAndDisassembly][checked]").val(),
			'inventoryParameters.isSuite' : $(":checkbox[name=isSuite][checked]").val(),
			'inventoryParameters.isPatternsTransition' : $(":checkbox[name=isPatternsTransition][checked]").val(),
			'inventoryParameters.isBatch' : $(":checkbox[name=isBatch][checked]").val(),
			'inventoryParameters.isConsignmentSales' : $(":checkbox[name=isConsignmentSales][checked]").val(),
			'inventoryParameters.isShelfLife' : $(":checkbox[name=isShelfLife][checked]").val(),
			'inventoryParameters.isOnCommission' : $(":checkbox[name=isOnCommission][checked]").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/optionAction!goList.action');
			});
		}
	};
	$("#inventoryParametersForm").validationEngine();
	function initdata() {
		if ($('#isBatchId').val() == '1') {
			$("#isBatch").prop("checked", true);
		}
		if ($('#isShelfLifeId').val() == '1') {
			$("#isShelfLife").prop("checked", true);
		}
		if ($('#isAssemblyAndDisassemblyId').val() == '1') {
			$("#isAssemblyAndDisassembly").prop("checked", true);
		}
	}
	initdata();
</script>
<input type="hidden" id="isBatchId" name="isBatchId" value="${inventoryParameters.isBatch}" />
<input type="hidden" id="isShelfLifeId" name="isShelfLifeId" value="${inventoryParameters.isShelfLife}" />
<input type="hidden" id="isAssemblyAndDisassemblyId" name="isAssemblyAndDisassemblyId" value="${inventoryParameters.isAssemblyAndDisassembly}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/inv_productDisassembly.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">库存管理</a></li>
				<li><a href="#">初始设置</a></li>
				<li><a href="#">选项</a></li>
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
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">通用设置</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">专用设置</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np',event)">可用量检查</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<form id="inventoryParametersForm">
						<s:hidden id="id" name="id" value="%{inventoryParameters.id}" theme="simple" />
						<div class="nt">
							<div class="nt_title">基本设置</div>
							<div class="nt_line">
								<table>
									<tr>
										<td><label><input type="checkbox" id="isBatch" name="isBatch" value="1" />有无批次管理</label></td>
										<td><label><input type="checkbox" id="isShelfLife" name="isShelfLife" value="1" />有无保质期管理</label></td>
										<td><label><input type="checkbox" id="isAssemblyAndDisassembly" name="isAssemblyAndDisassembly" value="1" />有无组装拆卸业务</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" id="isSuite" name="isSuite" value="1" />有无成套件管理</label></td>
										<td><label><input type="checkbox" id="isPatternsTransition" name="isPatternsTransition" value="1" />有无形态转换业务</label></td>
										<td><label><input type="checkbox" id="isConsignmentSales" name="isConsignmentSales" value="1" />有无委托代销业务</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" id="isOnCommission" name="isOnCommission" value="1" />有无受托代销业务</label></td>
										<td><label><input type="checkbox" id="" name="" />采购入库审核时改现存量</label></td>
										<td><label><input type="checkbox" id="" name="" />销售出库审核时改现存量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="IntoTheUnitPriceMethod" />材料出库审核时改现存量</label></td>
										<td><label><input type="checkbox" name="IntoTheUnitPriceMethod" />产成品入库审核改现存量</label></td>
										<td><label><input type="checkbox" name="IntoTheUnitPriceMethod" />其他出入库审核改现存量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查仓库存货对应关系</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查存货货位对应关系</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />调拨单只控制出库权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />审核时是否检查货位</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />是否库存生成销售出库单</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />出库跟踪入库存货入库单审核后才能出库</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />是否显示未审核的产品结构</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />倒冲材料出库单自动审核</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />限额领料单领料时自动带入未出库数量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />浮动换算率 ，不反算件数和数量</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />浮动换算率 ，修改数量时不反算换算率</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />单据序时控制</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查仓库权限</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查存货权限</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查货位权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查部门权限</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />检查操作员权限</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />近效期先出</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />批号先进先出</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />先进先出</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />后进先出</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />档案换算率</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />结存换算率</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />不带换算率</label></td>
									</tr>
								</table>
							</div>
						</div>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" onclick="saveOrUpdate();" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="np_clist" id="np2" style="display: none;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">专用设置</div>
							<div class="nt_line">
								<table>
									<tr>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许货位零出库</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许超发货单出库</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许超调拨单出库</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许超生产订单领料</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许超限额领料</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许超采购订单入库</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />容许超生产订单入库</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />生产领料后方可入库</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />按生产订单领料比例控制产品入库数量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />失效存货报警</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />最高最低库存预警</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />最高最低库存控制</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />委外领料后方可入库</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />按仓库控制最高最低库存量</label></td>
										<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />按仓库控制盘点参数</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />最新成本</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />优先顺序</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />量少先出</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />仓库成本</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />计划单价</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />按计价方式取单价</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />最新成本</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />仓库成本</label></td>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />计划单价</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="MonovalentEntryWay" />按计价方式取单价</label></td>
									</tr>
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
				<div class="np_clist" id="np3" style="display: none;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">可用量控制</div>
							<div class="nt_line">
								<table>
									<tr>
										<td><label><input type="checkbox" name="CheckInventoryPermissions" />检查存货权限</label></td>
										<td><label><input type="checkbox" name="CheckSuppliersPermissions" />检查供应商权限</label></td>
										<td><label><input type="checkbox" name="InspectionDepartmentPermission" />检查部门权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckSalesmanPermissions" />检查业务员权限</label></td>
										<td><label><input type="checkbox" name="CheckTheOperatorPrivileges" />检查操作员权限</label></td>
										<td><label><input type="checkbox" name="CheckTheAmountOfAuditPermissions" />检查金额审核权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckInventoryPermissions" />检查存货权限</label></td>
										<td><label><input type="checkbox" name="CheckSuppliersPermissions" />检查供应商权限</label></td>
										<td><label><input type="checkbox" name="InspectionDepartmentPermission" />检查部门权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckSalesmanPermissions" />检查业务员权限</label></td>
										<td><label><input type="checkbox" name="CheckTheOperatorPrivileges" />检查操作员权限</label></td>
										<td><label><input type="checkbox" name="CheckTheAmountOfAuditPermissions" />检查金额审核权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckInventoryPermissions" />检查存货权限</label></td>
										<td><label><input type="checkbox" name="CheckSuppliersPermissions" />检查供应商权限</label></td>
										<td><label><input type="checkbox" name="InspectionDepartmentPermission" />检查部门权限</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckSalesmanPermissions" />检查业务员权限</label></td>
										<td><label><input type="checkbox" name="CheckTheOperatorPrivileges" />检查操作员权限</label></td>
										<td><label><input type="checkbox" name="CheckTheAmountOfAuditPermissions" />检查金额审核权限</label></td>
									</tr>
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
				<div class="np_clist lineh30" id="np4" style="display: none;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">可用量检查</div>
							<div class="nt_line">
								<table>
									<tr>
										<td><label><input type="checkbox" name="CheckInventoryPermissions" />已请购量</label></td>
										<td><label><input type="checkbox" name="CheckSuppliersPermissions" />采购在途量</label></td>
										<td><label><input type="checkbox" name="InspectionDepartmentPermission" />到货在检量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckSalesmanPermissions" />生产订单量</label></td>
										<td><label><input type="checkbox" name="CheckTheOperatorPrivileges" />调拨在途量</label></td>
										<td><label><input type="checkbox" name="CheckInventoryPermissions" />预计出库量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckInventoryPermissions" />已订购量</label></td>
										<td><label><input type="checkbox" name="CheckSuppliersPermissions" />待发货量</label></td>
										<td><label><input type="checkbox" name="InspectionDepartmentPermission" />调拨待发量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="CheckSalesmanPermissions" />备料计划量</label></td>
										<td><label><input type="checkbox" name="RdoPrevail" value="0" />已请购量</label></td>
										<td><label><input type="checkbox" name="RdoPrevail" value="1" />采购在途量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="RdoPrevail" value="0" />到货再捡量</label></td>
										<td><label><input type="checkbox" name="RdoPrevail" value="1" />生产订单量</label></td>
										<td><label><input type="checkbox" name="RdoPrevail" value="0" />掉不在途量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="RdoPrevail" value="0" />已订购量</label></td>
										<td><label><input type="checkbox" name="RdoPrevail" value="1" />待发货量</label></td>
										<td><label><input type="checkbox" name="RdoPrevail" value="0" />调拨待发量</label></td>
									</tr>
									<tr>
										<td><label><input type="checkbox" name="RdoPrevail" value="1" />备料计划量</label></td>
									</tr>
								</table>
							</div>
						</div>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" onclick="saveOrUpdate();" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
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
	<!-- c_foot -->
</div>