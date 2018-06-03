<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
if($('#por_switch').length > 0){
	$('#por_switch').click(function(){
		$(this).toggleClass("off");
		$('#por_left').toggle();
		if($('#por_right').css("margin-left")=="220px"){
			$('#por_right').css("margin-left",10);
			$(this).css("left",-18);
		}else{
			$('#por_right').css("margin-left",220);
			$(this).css("left",-8);
		}
	});
}
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="srm_supp_rela_manage" /></a></li>
				<li><a href="#"><s:text name='srm_basic_settings' /></a></li>
				<a href="#">招标设置</a>
			</ul>
		</div>
	</h2>
	<div class="drop" style="display: none;">
		<p>
			<!-- <a href="#" onclick="saveOrUpdate(0);"><span>添加</span></a> -->
		</p>
	</div>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_left_title">
			<h2>招标设置</h2>
			<p>
				<a onclick="javascript:addContract();" href="javascript:;" class="abtn"><span>新建</span></a>
			</p>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">业务</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">预警</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np',event)">权限</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np',event)">参照控制</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,5,'np',event)">流程控制</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,6,'np',event)">打印\打印控制</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<form method="get">
						<div class="nt">
							<div class="nt_title">基础选项</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" /></label></td>
											<td><label>普通业务必须要有订单</label></td>
											<td align="right"><label><input type="checkbox" name="DirectShipmentBizMustHaveOrders" /></label></td>
											<td><label>直运业务必须要有订单</label></td>
											<td align="right"><label><input type="checkbox" name="ConsignmentBizMustHaveOrders" /></label></td>
											<td><label>受托代销业务必须要有订单</label></td>
										</tr>
										<tr class="">
											<td align="right"><label><input type="checkbox" name="EnableConsignment" /></label></td>
											<td><label>启用受托代销</label></td>
											<td align="right"><label><input type="checkbox" name="WhetherToAllowUltraOrderArrivalAndStorage" /></label></td>
											<td><label>是否允许超订单到货及入库</label></td>
											<td align="right"><label><input type="checkbox" name="WhetherToAllowUltraPlannedOrders" /></label></td>
											<td><label>是否允许超计划订货</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">
								<input type="checkbox" name="StorageNoteWhetherToAutomaticallyIntoTheUnitPrice" />&nbsp;入库单是否自动带入单价
							</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="IntoTheUnitPriceMethod" disabled="disabled" /></label></td>
											<td><label>手工录入</label></td>
											<td align="right"><label><input type="radio" name="IntoTheUnitPriceMethod" disabled="disabled" /></label></td>
											<td><label>参考成本</label></td>
											<td align="right"><label><input type="radio" name="IntoTheUnitPriceMethod" disabled="disabled" checked="checked" /></label></td>
											<td><label>最新成本</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">订单\到货单\发票单价录入方式</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="MonovalentEntryWay" disabled="disabled" /></label></td>
											<td><label>手工录入</label></td>
											<td align="right"><label><input type="radio" name="MonovalentEntryWay" disabled="disabled" /></label></td>
											<td><label>取自供应商存货价格表价格</label></td>
											<td align="right"><label><input type="radio" name="MonovalentEntryWay" disabled="disabled" checked="checked" /></label></td>
											<td><label>最新价格(来源同历史交易价参照设置)</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">历史交易价参照设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="WhetherTheSuppliersTakePrice" disabled="disabled" /></label></td>
											<td><label>是否按供应商取价</label></td>
											<td align="right"><label>来源：</label></td>
											<td><label> <select name="Source">
														<option value="0">订单</option>
												</select></label></td>
											<td align="center" colspan="2"><label>显示最近<input type="text" class="ipt" name="TransactionHistory" />次历史交易记录
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">最高进阶控制口令</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="center"><label><input type="text" name="txtCommand" class="ipt time_aoto_input wb80" value=""></label></td>
											<td><label><input type="button" value="更改" name="btnTheAdvancedControlPassword" /></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">修改税额时是否改变税率</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label>单行容差：</label></td>
											<td><label><input type="text" name="SpecificTolerance" class="ipt time_aoto_input wb80" /></label></td>
											<td align="right"><label>合计容差：</label></td>
											<td><label><input type="text" name="TotalTolerance" class="ipt time_aoto_input wb80" /></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">结算选项</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="SettlementOptions" /></label></td>
											<td><label>商业版费用是否分摊到入库成本</label></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist" id="np2" style="display: none; height: 260px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">
								<input type="checkbox" name="ChangingTaxRates" />&nbsp;预警和报警
							</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="center"><label>提前预警天数&nbsp;<input type="text" name="AdvanceWarningTime" class="ipt time_aoto_input wb50" />&nbsp;天
											</label></td>
											<td align="center"><label>逾期报警天数&nbsp;<input type="text" name="EarlyDaysOverdue" class="ipt time_aoto_input wb50" />&nbsp;天
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist" id="np3" style="display: none; height: 260px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">基本权限控制</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="CheckInventoryPermissions" /></label></td>
											<td><label>检查存货权限</label></td>
											<td align="right"><label><input type="checkbox" name="CheckSuppliersPermissions" /></label></td>
											<td><label>检查供应商权限</label></td>
											<td align="right"><label><input type="checkbox" name="InspectionDepartmentPermission" /></label></td>
											<td><label>检查部门权限</label></td>
										</tr>
										<tr class="">
											<td align="right"><label><input type="checkbox" name="CheckSalesmanPermissions" /></label></td>
											<td><label>检查业务员权限</label></td>
											<td align="right"><label><input type="checkbox" name="CheckTheOperatorPrivileges" /></label></td>
											<td><label>检查操作员权限</label></td>
											<td align="right"><label><input type="checkbox" name="CheckTheAmountOfAuditPermissions" /></label></td>
											<td><label>检查金额审核权限</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist lineh30" id="np4" style="display: none;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">系统启用</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label>启用日期：</label></td>
											<td><label>2013-03-18</label></td>
											<td align="right"><label>本系统启用的会计月：</label></td>
											<td><label> <select><option>2013</option></select>年 <select><option>5</option></select>月
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">浮动换算率计算规则</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="RdoPrevail" value="0" /></label></td>
											<td><label>件数为准</label></td>
											<td align="right"><label><input type="radio" name="RdoPrevail" value="1" /></label></td>
											<td><label>数量为准</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">远程应用</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="checkbox" name="EnableRemote" /></label></td>
											<td><label>启用远程</label></td>
											<td align="right">远程识别号</label></td>
											<td><label><input type="text" name="RemoteIdentificationNumber" class="ipt time_aoto_input wb50" /></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">发票默认率设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label>专用发票默认税率：</label></td>
											<td><label><input type="text" name="SpecialInvoicesDefaultRate" class="ipt time_aoto_input wb50" /></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">参照方式设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="BaseStringSet" value="0" /></label></td>
											<td><label>基于基串精确匹配</label></td>
											<td align="right"><label><input type="radio" name="BaseStringSet" value="1" /></label></td>
											<td><label>基于基串向后匹配</label></td>
											<td align="right"><label><input type="radio" name="BaseStringSet" value="2" /></label></td>
											<td><label>基于基串向前匹配</label></td>
										</tr>
										<tr class="">
											<td align="right"><label><input type="radio" name="BaseStringSet" value="3" /></label></td>
											<td><label>基于基串前后模糊匹配</label></td>
											<td align="right"><label><input type="radio" name="BaseStringSet" value="4" /></label></td>
											<td><label>查询全部不做模糊匹配</label></td>
											<td align="right"><label></label></td>
											<td><label></label></td>
										</tr>
										<tr class="">
											<td align="right"><label><input type="checkbox" name="StaffAuthorityInspectionBiz" /></label></td>
											<td><label>检查业务员权限</label></td>
											<td align="right"><label></label></td>
											<td><label></label></td>
											<td align="right"><label></label></td>
											<td><label></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist lineh30" id="np5" style="display: none; height: 260px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">参照方式设置</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="BaseStringSet" value="0" /></label></td>
											<td><label>基于基串精确匹配</label></td>
											<td align="right"><label><input type="radio" name="BaseStringSet" value="1" /></label></td>
											<td><label>基于基串向后匹配</label></td>
											<td align="right"><label><input type="radio" name="BaseStringSet" value="2" /></label></td>
											<td><label>基于基串向前匹配</label></td>
										</tr>
										<tr class="">
											<td align="right"><label><input type="radio" name="BaseStringSet" value="3" /></label></td>
											<td><label>基于基串前后模糊匹配</label></td>
											<td align="right"><label><input type="radio" name="BaseStringSet" value="4" /></label></td>
											<td><label>查询全部不做模糊匹配</label></td>
											<td align="right"><label></label></td>
											<td><label></label></td>
										</tr>
										<tr class="">
											<td align="right"><label><input type="checkbox" name="StaffAuthorityInspectionBiz" /></label></td>
											<td><label>检查业务员权限</label></td>
											<td align="right"><label></label></td>
											<td><label></label></td>
											<td align="right"><label></label></td>
											<td><label></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="np_clist lineh30" id="np6" style="display: none; height: 260px;">
					<form method="get">
						<div class="nt">
							<div class="nt_title">打印控制</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label>打印次数最多：</label></td>
											<td><label><input type="text" name="ControlTheNumberOfPrints" class="ipt time_aoto_input wb50" />&nbsp;次</label></td>
											<td align="right"><label>打印预览格式：</label></td>
											<td><label> <select name="PrintPreviewFormat" style="width: 50%;">
														<option value="0">A4</option>
														<option value="1">A5</option>
												</select>
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">导出控制</div>
							<div class="nt_line">
								<table width="100%">
									<tbody>
										<tr class="alt">
											<td align="right"><label>导出路径：</label></td>
											<td><label> <input type="text" name="ExportPath" class="ipt time_aoto_input wb50" />&nbsp; <input type="button" value="更改路径" />
											</label></td>
											<td align="right"><label>导出格式：</label></td>
											<td><label> <select name="PrintPreviewFormat" class="logtimeaddselect" style="width: 50%;">
														<option value="0">Word</option>
														<option value="1">Excel</option>
												</select>
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
			</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
				<tr>
					<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>