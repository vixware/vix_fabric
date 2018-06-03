<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
	</h2>
	<div id="breadCrumb" class="breadCrumb module">
		<ul>
			<li><a href="#"><img src="${vix}/common/img/transportationresourcemanagement.png" alt="" /> <s:text name="supplyChain" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_transreource" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_trafficmanagement" /> </a></li>
			<li><a href="#"><s:text name="dtbcenter_credentialmanagement" /> </a></li>
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
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/driverAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_certificateinformation" /> </b> </strong>
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
											<td align="right">证件编号：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.vehicleCode }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">证件名称：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.vehicleNO }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">证件类型：</td>
											<td><select id="type" name="type" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="身份证" selected="selected">身份证</option>
													<option value="驾驶证">驾驶证</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">证件所有人：</td>
											<td><input id="name" name="name" value="${vehicle.name }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">登记时间：</td>
											<td><input id="purchaseDate" name="purchaseDate" value="${vehicle.purchaseDate}" type="text" readonly="readonly" /><img onclick="showTime('purchaseDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">存放地点：</td>
											<td><input id="useYear" name="useYear" value="${vehicle.useYear }" type="text" size="30" /></td>
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