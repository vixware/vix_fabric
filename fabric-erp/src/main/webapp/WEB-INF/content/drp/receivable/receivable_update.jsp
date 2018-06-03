<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">经销商管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/receivableAction!goList.action');">应收管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="channelDistributor.id" value="${channelDistributor.id}" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/receivableAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">单据编号：</td>
											<td><input id="name" name="name" value="${channelDistributor.name }" type="text" size="30" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">单据名称：</td>
											<td><input id="shortName" name="shortName" value="${channelDistributor.shortName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">客户名称：</td>
											<td><input id="code" name="code" value="${channelDistributor.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">应收金额：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><input id="email" name="email" value="${channelDistributor.email }" type="text" size="30" /></td>
											<td align="right">汇率：</td>
											<td><input id="permit" name="permit" value="${channelDistributor.permit }" type="text" size="30" /></td>
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