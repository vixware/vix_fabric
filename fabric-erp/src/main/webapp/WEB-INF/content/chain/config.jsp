<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	/** 保存数据 */
	/* 'inventoryParameters.codeType' : $(":radio[name=codetype][checked]").val(), */
	function saveOrUpdate() {
		if ($('#inventoryParametersForm').validationEngine('validate')) {
			$.post('${vix}/inventory/optionAction!saveOrUpdate.action', {
				'inventoryParameters.id' : $("#id").val(),
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/optionAction!goList.action');
			});
		}
	};
	$("#inventoryParametersForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/inv_productDisassembly.png" alt="" /> <s:text name="cmn_supplyChain" /> </a></li>
				<li><a href="#">连锁经营管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#">参数设置</a></li>
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
			<h2>参数设置</h2>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">业务</a></li>
					<!-- <li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">预警</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np',event)">价格</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np',event)">打印</a></li> -->
				</ul>
				<div class="np_clist" id="np1" style="height: 450px;">
					<form id="inventoryParametersForm">
						<s:hidden id="id" name="id" value="%{inventoryParameters.id}" theme="simple" />
						<div class="nt">
							<div class="nt_title">业务设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><input type="checkbox" name="isAssemblyAndDisassembly" value="1" /></td>
											<td><label>自动订货</label></td>
											<td align="right"><label><input type="checkbox" name="isSuite" value="1" /> </label></td>
											<td><label>是否为集中采购</label></td>
										</tr>
									</tbody>
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
				<!-- <div class="np_clist" id="np2" style="display: none; height: 450px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">提醒设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" /> </label></td>
											<td><label>容许货位零出库</label></td>
											<td align="right"><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" /> </label></td>
											<td><label>容许超发货单出库</label></td>
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
				</div> -->
				<!-- <div class="np_clist" id="np3" style="display: none; height: 450px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">价格设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="CheckInventoryPermissions" /> </label></td>
											<td><label>销售价格控制</label></td>
											<td align="right"><label><input type="checkbox" name="CheckSuppliersPermissions" /> </label></td>
											<td><label>采购价格控制</label></td>
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
				</div> -->
				<!-- <div class="np_clist lineh30" id="np4" style="display: none; height: 450px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">打印设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="CheckInventoryPermissions" /> </label></td>
											<td><label>销售小票打印的标题</label></td>
											<td align="right"><label><input type="checkbox" name="CheckSuppliersPermissions" /> </label></td>
											<td><label>信息</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" onclick="saveOrUpdate();" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
							</tr>
						</table>
					</form>
				</div> -->
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>