<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" />供应链 </a></li>
				<li><a href="#">库存管理 </a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box">
		<div id="por_left" class="np_l_l mail_left">
			<div class="addtitle " style="border: 1px solid #B4B4B4; border-bottom: 0;">
				<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;库存管理</span>
			</div>
			<ul class="datelist">
				<li id="lnp_btn1" class="clearfix on_lnpbtn" onclick="javascript:tabBig(4,1,'lnp','lnp_btn','on_lnpbtn')"><img src="img/system_basic_information.png" /> 快捷功能</li>
				<li id="lnp_btn2" class="clearfix" onclick="javascript:tabBig(4,2,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_Settings_interface_settings.png" /> 模块</li>
				<li id="lnp_btn3" class="clearfix" onclick="javascript:tabBig(4,3,'lnp','lnp_btn','on_lnpbtn')"><img src="img/personal_accounts_and_security_settings.png" /> 参数设置</li>
			</ul>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="position: relative;">
			<div id="lnp1">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;快捷功能</span>
					</div>
					<div class="fn_table">
						<table>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /> <span class="fn_table_num">${stockRecordsNum }</span> </span>入库</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /> <span class="fn_table_num">${outStockRecordsNum }</span> </span>出库</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /> <span class="fn_table_num">${stockTakingNum }</span> </span>盘点</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /> <span class="fn_table_num">${transvouchNum }</span> </span>调拨</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /> <span class="fn_table_num">${inventoryCurrentStockNum }</span> </span>台账</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/positionAdjustmentAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /> <span class="fn_table_num">${adjustpvouchNum }</span> </span>货位调整</a></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="addbox wf" style="border: 0;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(3,1,'fn',event)"><img src="img/mail.png" alt="" />常见问题</a></li>
							<li><a onclick="javascript:tab(3,2,'fn',event)"><img src="img/mail.png" alt="" />视频演示</a></li>
						</ul>
					</div>
					<div id="fn1" class="fn_cont">
						<ul class="ullist">
							<li><a href="#">常见问题</a></li>
						</ul>
					</div>
					<div id="fn2" class="fn_cont" style="display: none;">
						<ul class="ullist">
							<li><a href="#">视频演示</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="lnp2" style="display: none;">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;模块</span>
					</div>
					<div class="fn_table">
						<table>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/inventory/inboundWarehouseAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /> <span class="fn_table_num">${stockRecordsNum }</span> </span>入库</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /> <span class="fn_table_num">${outStockRecordsNum }</span> </span>出库</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /> <span class="fn_table_num">${stockTakingNum }</span> </span>盘点</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /> <span class="fn_table_num">${transvouchNum }</span> </span>调拨</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /> <span class="fn_table_num">${inventoryCurrentStockNum }</span> </span>台账</a></td>
								<td><a href="#" onclick="loadContent('${vix}/inventory/positionAdjustmentAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /> <span class="fn_table_num">${adjustpvouchNum }</span> </span>货位调整</a></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div id="lnp3" style="display: none;">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;参数设置</span>
					</div>
					<div id="lnp1">
						<div class="np_clist" id="np1">
							<form id="inventoryParametersForm">
								<s:hidden id="id" name="id" value="%{inventoryParameters.id}" theme="simple" />
								<div class="nt">
									<div class="nt_title">参数设置</div>
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
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>