<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
	</h2>
	<div id="breadCrumb" class="breadCrumb module">
		<ul>
			<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_transreource" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_carparkingmanagement" /> </a></li>
			<a href="#"><s:text name="dtbcenter_carparkinginformation" /> </a>
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
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/vehicleManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png">
					</a>
					</span> <strong><b><s:text name="dtbcenter_carparkinginformation" /> </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong><s:text name="inventory_basicinfo" /> </strong>
								</dt>
								<dd style="display: block;">
									<p>
										<span><i>车场编号：</i><input name="employeeCode" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><span><i>车场名称：</i><input name="name" type="text" size="20" /> </span><span><i>位置：</i><input name="englishName" type="text" size="20" /> </span>
									</p>
									<p>
										<span><i>容量：</i><input name="sex" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span><span><i>负责人：</i><input name="identityNumber" type="text" size="20" /> </span><span><i>状态：</i><input name="educationalBackground" type="text" size="20" class="validate[required] text-input sweet-tooltip" /> </span>
									</p>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />其他</a></li>
						</ul>
					</div>
					<div id="a2" style="position: relative; z-index: 1; background: #FFF;">
						<div class="right_btn">
							<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /> </a> </span> <span><a href="#"><img src="${vix}/common/img/dt_line_edit.png" alt="" /> </a> </span> <span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /> </a> </span> <span><a href="#"><img
									src="${vix}/common/img/dt_line_delete.png" alt="" /> </a> </span>
						</div>
						<table id="test"></table>
					</div>
				</div>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>