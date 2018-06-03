<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" />供应链 </a></li>
				<li><a href="#">采购管理 </a></li>
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
				<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;采购管理</span>
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
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /><span class="fn_table_num">${purchasePlanNum }</span> </span>采购计划</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num"></span> ${purchaseApplyNum }</span>采购申请</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /><span class="fn_table_num"></span>${purchaseOrderNum } </span>采购订单</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /><span class="fn_table_num"></span>${purchaseArrivalNum } </span>到货管理</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /><span class="fn_table_num"></span>${purchaseInboundNum } </span>采购入库</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num"></span>${purchaseReturnNum } </span>采购退货</a></td>
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
						<ul class="ullist">常见问题
						</ul>
					</div>
					<div id="fn2" class="fn_cont" style="display: none;">
						<ul class="ullist">视频演示
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
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchasePlanAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /><span class="fn_table_num">${purchasePlanNum }</span> </span>采购计划</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseApplyAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num"></span> ${purchaseApplyNum }</span>采购申请</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /><span class="fn_table_num"></span>${purchaseOrderNum } </span>采购订单</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /><span class="fn_table_num"></span>${purchaseArrivalNum } </span>到货管理</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseInboundAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /><span class="fn_table_num"></span>${purchaseInboundNum } </span>采购入库</a></td>
								<td><a href="#" onclick="loadContent('${vix}/purchase/purchaseReturnAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num"></span>${purchaseReturnNum } </span>采购退货</a></td>
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
					<div class="np_clist" id="np31">
						<div class="nt">
							<div class="nt_line">
								<table>
									<tbody>
										<tr class="alt">
											<td><label><input type="checkbox" name="OrdinaryBizMustHaveOrders" />普通业务必须要有订单</label></td>
											<td><label><input type="checkbox" name="DirectShipmentBizMustHaveOrders" />直运业务必须要有订单</label></td>
											<td><label><input type="checkbox" name="ConsignmentBizMustHaveOrders" />受托代销业务必须要有订单</label></td>
										</tr>
										<tr class="">
											<td><label><input type="checkbox" name="EnableConsignment" />启用受托代销</label></td>
											<td><label><input type="checkbox" name="WhetherToAllowUltraOrderArrivalAndStorage" />是否允许超订单到货及入库</label></td>
											<td><label><input type="checkbox" name="WhetherToAllowUltraPlannedOrders" />是否允许超计划订货</label></td>
										</tr>
									</tbody>
								</table>
							</div>
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