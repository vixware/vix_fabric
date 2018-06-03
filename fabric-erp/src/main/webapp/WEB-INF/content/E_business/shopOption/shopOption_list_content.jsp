<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	/** 保存数据 */
	function saveOrUpdate() {
		if ($('#onLineStoreParametersForm').validationEngine('validate')) {
			$.post('${vix}/business/shopOptionAction!saveOrUpdate.action', {
			'onLineStoreParameters.id' : $("#onLineStoreParametersId").val(),
			'onLineStoreParameters.fromCompany' : $("#fromCompany").val(),
			'onLineStoreParameters.contactPerson' : $("#contactPerson").val(),
			'onLineStoreParameters.fromPhone' : $("#fromPhone").val(),
			'onLineStoreParameters.fromAddress' : $("#fromAddress").val(),
			'onLineStoreParameters.greetings' : $("#greetings").val(),
			'onLineStoreParameters.isOpenAutomaticWarehouse' : $(":checkbox[name=isOpenAutomaticWarehouse][checked]").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/business/shopOptionAction!goList.action');
			});
		}
	};
	$("#onLineStoreParametersForm").validationEngine();

	$(function() {
		if ($("#isOpenAutomaticWarehouse").val() == '1') {
			$("#isOpenAutomaticWarehouseCheckbox").prop("checked", true);
		}
	});
</script>
<style>
.nt1 {
	padding: 20px 10px 10px 10px;
	border: #CCC solid 1px;
	line-height: 20px;
	margin: 10px 0 30px;
}

.nt_title1 {
	float: left;
	font-size: 14px;
	font-weight: bold;
	display: inline;
	margin: -30px 0 0;
	background: #FAFAFA;
	padding: 0 5px;
}

.nt_line1 table {
	table-layout: fixed;
}

.nt_line1 table td {
	padding: 5px 5px 5px;
	vertical-align: middle;
	line-height: 24px;
}

.nt_line1 table td input, .nt_line table td select {
	vertical-align: middle;
	margin-right: 5px;
}

.nt_line1 table td.tr {
	padding-right: 5px;
	text-align: right;
}
</style>
<input type="hidden" id="isOpenAutomaticWarehouse" name="isOpenAutomaticWarehouse" value="${onLineStoreParameters.isOpenAutomaticWarehouse}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/inv_productDisassembly.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">选项设置</a></li>
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
			<div class="np_clist">
				<form id="onLineStoreParametersForm">
					<s:hidden id="onLineStoreParametersId" name="onLineStoreParametersId" value="%{onLineStoreParameters.id}" theme="simple" />
					<div class="nt1">
						<div class="nt_title1">
							<label>&nbsp;&nbsp;同步订单分仓设置</label>
						</div>
						<div class="nt_line1">
							<table>
								<tr height="40">
									<td><label><input type="checkbox" id="isOpenAutomaticWarehouseCheckbox" name="isOpenAutomaticWarehouse" value="1" />是否开启自动分仓</label></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="nt1">
						<div class="nt_title1">
							<label>&nbsp;&nbsp;快递单寄件人联系方式设置</label>
						</div>
						<div class="nt_line1">
							<table>
								<tr height="40">
									<td align="right">寄&nbsp;件&nbsp;人:&nbsp;</td>
									<td align="left"><input type="text" id="contactPerson" name="contactPerson" value="${onLineStoreParameters.contactPerson}" size="25" class="ipt" /></td>
								</tr>
								<tr height="40">
									<td align="right">联系方式:&nbsp;</td>
									<td align="left"><input type="text" id="fromPhone" name="fromPhone" value="${onLineStoreParameters.fromPhone}" size="25" class="ipt" /></td>
								</tr>
								<tr height="40">
									<td align="right">寄件公司:&nbsp;</td>
									<td align="left"><input type="text" id="fromCompany" name="fromCompany" value="${onLineStoreParameters.fromCompany}" size="65" class="ipt" /></td>
								</tr>
								<tr height="40">
									<td align="right">寄件地址:&nbsp;</td>
									<td align="left"><input type="text" id="fromAddress" name="fromAddress" value="${onLineStoreParameters.fromAddress}" size="65" class="ipt" /></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="nt1">
						<div class="nt_title1">
							<label>&nbsp;&nbsp;发货单设置</label>
						</div>
						<div class="nt_line1">
							<table>
								<tr>
									<th align="right">贺&nbsp;&nbsp;&nbsp;&nbsp;语：&nbsp;</th>
									<td><textarea id="greetings" name="greetings" cols="3" rows="2" style="height: 75px; width: 550px; margin-top: 10px;">${onLineStoreParameters.greetings } </textarea></td>
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
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>