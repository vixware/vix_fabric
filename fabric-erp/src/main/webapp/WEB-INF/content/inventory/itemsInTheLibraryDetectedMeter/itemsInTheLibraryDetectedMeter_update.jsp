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
			$.post('${vix}/inventory/orderReservationAction!saveOrUpdate.action', {
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
				loadContent('${vix}/inventory/orderReservationAction!goList.action?menuId=menuTransfer');
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
				<li><a href="#">其他业务处理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/itemsInTheLibraryDetectedMeterAction!goList.action?pageNo=${pageNo}');">在库待检表</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${wimTransvouch.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/itemsInTheLibraryDetectedMeterAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回"
							src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>商品信息 </b> </strong>
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
											<th>商品编码：</th>
											<td><input id="tvcode" name="wimTransvouch.tvcode" value="${wimTransvouch.tvcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>商品名称\：</th>
											<td><input id="tvcode" name="wimTransvouch.tvcode" value="${wimTransvouch.tvcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>规格型号：</th>
											<td><input id="outdepartmentCode" name="wimTransvouch.outdepartmentCode" value="${wimTransvouch.outdepartmentCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>主计量单位：</th>
											<td><input id="indepartmentCode" name="wimTransvouch.indepartmentCode" value="${wimTransvouch.indepartmentCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>待检数量：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>仓库名称：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>供应商名称：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<th>报检部门：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
										</tr>
										<tr>
											<th>批号：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" /></td>
											<td align="right">生产日期：</td>
											<td><input id="createTime" name="createTime" value="${wimStockrecords.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
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