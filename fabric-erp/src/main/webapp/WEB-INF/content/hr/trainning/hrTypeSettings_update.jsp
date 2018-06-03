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
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/cmn_setting.png" alt="" />人力资源</a></li>
				<li><a href="#">基础设置</a></li>
				<a href="#">系统参数</a>
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
			<h2>系统设置</h2>
			<p>
				<a href="javascript:;" class="abtn"><span>保存</span></a> <a href="javascript:;" class="abtn"><span>返回</span></a>
			</p>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">人资业务</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">预警</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np',event)">权限</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np',event)">参照控制</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,5,'np',event)">流程控制</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,6,'np',event)">打印\打印控制</a></li>
				</ul>
				<div class="np_clist" id="np1">
					<form method="get">
						<div class="nt">
							<div class="nt_title">组织管理</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />创建人员必须有部门</label></td>
											<td><label><input type="checkbox" name="DirectShipmentBizMustHaveOrders" />创建部门必须有组织</label></td>
											<td><label><input type="checkbox" name="ConsignmentBizMustHaveOrders" />创建组织可以分集团和子公司</label></td>
										</tr>
										<tr class="">
											<td><label><input type="checkbox" name="EnableConsignment" />是否允许一岗多人</label></td>
											<td><label><input type="checkbox" name="WhetherToAllowUltraPlannedOrders" />是否允许一人多岗位</label></td>
											<td><label><input type="checkbox" name="WhetherToAllowUltraOrderArrivalAndStorage" />是否允许空岗创建人员</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">薪酬管理</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="radio" name="IntoTheUnitPriceMethod" disabled="disabled" />只允许薪酬专员操作</label></td>
											<td><label><input type="radio" name="IntoTheUnitPriceMethod" disabled="disabled" />自动计算薪酬额度</label></td>
											<td><label><input type="radio" name="IntoTheUnitPriceMethod" disabled="disabled" checked="checked" />自定义薪酬系数</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">考勤管理</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="radio" name="MonovalentEntryWay" disabled="disabled" />允许导入导出</label></td>
											<td><label><input type="radio" name="MonovalentEntryWay" disabled="disabled" />请假额度限制</label></td>
											<td><label><input type="radio" name="MonovalentEntryWay" disabled="disabled" checked="checked" />生成考勤周期</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">招聘管理</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td align="right"><label><input type="radio" name="WhetherTheSuppliersTakePrice" disabled="disabled" />是否自动搜索网络简历</label></td>
											<td align="right"><label><input type="radio" name="WhetherTheSuppliersTakePrice" disabled="disabled" />自动按学历筛选简历</label></td>
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
								<table>
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
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="checkbox" name="CheckInventoryPermissions" />检查系统管理员权限</label></td>
											<td><label><input type="checkbox" name="CheckSuppliersPermissions" />检查主管权限</label></td>
											<td><label><input type="checkbox" name="InspectionDepartmentPermission" />检查专员权限</label></td>
										</tr>
										<tr class="">
											<td><label><input type="checkbox" name="CheckSalesmanPermissions" />检查业务员权限</label></td>
											<td><label><input type="checkbox" name="CheckTheOperatorPrivileges" />检查操作员权限</label></td>
											<td><label><input type="checkbox" name="CheckTheAmountOfAuditPermissions" />检查审核权限</label></td>
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
								<table>
									<tbody>
										<tr class="alt">
											<td align="right"><label>启用日期：2013-03-18</label></td>
											<td align="right"><label>本系统启用的会计月： <select><option>2013</option></select>年 <select><option>5</option></select>月
											</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">浮动换算率计算规则</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="radio" name="RdoPrevail" value="0" />件数为准</label></td>
											<td><label><input type="radio" name="RdoPrevail" value="1" />数量为准</label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">远程应用</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="checkbox" name="EnableRemote" />启用远程</label></td>
											<td><label>远程识别号：<input type="text" name="RemoteIdentificationNumber" class="ipt time_aoto_input wb50" /></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">发票默认率设置</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label>专用发票默认税率：<input type="text" name="SpecialInvoicesDefaultRate" class="ipt time_aoto_input wb50" /></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">参照方式设置</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="radio" name="BaseStringSet" value="0" />基于基串精确匹配</label></td>
											<td><label><input type="radio" name="BaseStringSet" value="1" />基于基串向后匹配</label></td>
											<td><label><input type="radio" name="BaseStringSet" value="2" />基于基串向前匹配</label></td>
										</tr>
										<tr class="">
											<td><label><input type="radio" name="BaseStringSet" value="3" />基于基串前后模糊匹配</label></td>
											<td><label><input type="radio" name="BaseStringSet" value="4" />查询全部不做模糊匹配</label></td>
										</tr>
										<tr class="">
											<td><label><input type="checkbox" name="StaffAuthorityInspectionBiz" />检查业务员权限</label></td>
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
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="radio" name="BaseStringSet" value="0" />基于基串精确匹配</label></td>
											<td><label><input type="radio" name="BaseStringSet" value="1" />基于基串向后匹配</label></td>
											<td><label><input type="radio" name="BaseStringSet" value="2" />基于基串向前匹配</label></td>
										</tr>
										<tr class="">
											<td><label><input type="radio" name="BaseStringSet" value="3" />基于基串前后模糊匹配</label></td>
											<td><label><input type="radio" name="BaseStringSet" value="4" />查询全部不做模糊匹配</label></td>
										</tr>
										<tr class="">
											<td><label><input type="checkbox" name="StaffAuthorityInspectionBiz" />检查业务员权限</label></td>
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
								<table>
									<tbody>
										<tr class="alt">
											<td><label>打印次数最多：<input type="text" name="ControlTheNumberOfPrints" class="ipt time_aoto_input wb50" />&nbsp;次
											</label></td>
											<td><label>打印预览格式：<select name="PrintPreviewFormat"><option value="0">A4</option>
														<option value="1">A5</option></select></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="nt">
							<div class="nt_title">导出控制</div>
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td align="right"><label>导出路径：<input type="text" name="ExportPath" class="ipt time_aoto_input wb50" />&nbsp;<input type="button" value="更改路径" /></label></td>
											<td align="right"><label>导出格式： <select name="PrintPreviewFormat" class="logtimeaddselect">
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