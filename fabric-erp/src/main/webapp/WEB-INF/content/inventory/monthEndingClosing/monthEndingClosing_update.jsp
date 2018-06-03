<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
    function saveOrUpdateOrder() {
	    var dlData = $("#dlAddress").datagrid("getRows");
	    var dlJson = JSON.stringify(dlData);
	    if ($('#order').validationEngine('validate')) {
		    $.post('${vix}/inventory/allocateTransferAction!saveOrUpdate.action', {
		    'wimTransvouch.id' : $("#id").val(),
		    'wimTransvouch.tvcode' : $("#tvcode").val(),
		    'wimTransvouch.outdepartmentCode' : $("#outdepartmentCode").val(),
		    'wimTransvouch.tvdate' : $("#tvdate").val(),
		    'wimTransvouch.indepartmentCode' : $("#indepartmentCode").val(),
		    'wimTransvouch.outwarehousecode' : $("#outwarehousecode").val(),
		    'wimTransvouch.inwarehousecode' : $("#inwarehousecode").val(),
		    'wimTransvouch.outstockcatalogcode' : $("#outstockcatalogcode").val(),
		    'wimTransvouch.instockcatalogcode' : $("#instockcatalogcode").val(),
		    'dlJson' : dlJson
		    }, function(result) {
			    showMessage(result);
			    setTimeout("", 1000);
			    loadContent('${vix}/inventory/allocateTransferAction!goList.action?menuId=menuTransfer');
		    });
	    }
    }
    $("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/monthEndingClosingAction!goList.action?pageNo=${pageNo}');">月末结账</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="wimTransvouch.id" value="${wimTransvouch.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/inventory/monthEndingClosingAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>库存信息</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>商品编号：</th>
											<td><input id="tvcode" name="wimTransvouch.tvcode" value="${wimTransvouch.tvcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>期初库存：</th>
											<td><input id="outdepartmentCode" name="wimTransvouch.outdepartmentCode" value="${wimTransvouch.outdepartmentCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>期初成本：</th>
											<td><input id="outdepartmentCode" name="wimTransvouch.outdepartmentCode" value="${wimTransvouch.outdepartmentCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>期初余额：</th>
											<td><input id="indepartmentCode" name="wimTransvouch.indepartmentCode" value="${wimTransvouch.indepartmentCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>入库数量：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>入库成本：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>入库金额：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>出库数量：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>出库成本：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>出库金额：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>期末库存：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>期末成本：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>期末余额：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>期初在检数：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>期初在检金额：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>期初再检数：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>期末在检数：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>期末在检金额：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>