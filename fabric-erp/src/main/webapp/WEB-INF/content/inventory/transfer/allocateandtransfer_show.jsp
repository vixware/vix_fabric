<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	if ($(".ms").length > 0) {
		$(".ms").hover(function() {
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">ul", this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function() {
			$(">a", this).addClass("hover");
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">a", this).removeClass("hover");
			$(">ul", this).stop().slideUp(100);
		});
	}

	//上一条数据
	function goBeforeWimTransvouch(id) {
		$.ajax({
		url : '${vix}/inventory/allocateTransferAction!goBeforeWimTransvouch.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//下一条
	function goAfterWimTransvouch(id) {
		$.ajax({
		url : '${vix}/inventory/allocateTransferAction!goAfterWimTransvouch.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action?pageNo=${pageNo}');"><s:text name="inventory_transfer_business" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${stockRecords.id}" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
							width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goBeforeWimTransvouch('${wimTransvouch.id}');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png"> </a> <a
						href="#" onclick="goAfterWimTransvouch('${wimTransvouch.id}');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintAllocateTransfer('${wimTransvouch.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
							<ul style="display: none; overflow: hidden; height: auto; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#" onclick="goShowPurchaseOrder('${stockRecords.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/inventory/allocateTransferAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>调拨单 </b><i></i> </strong>
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
											<th>调拨单号：</th>
											<td><input id="code" name="code" value="${wimTransvouch.code }" type="text" size="30" readonly="readonly"></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${wimTransvouch.name }" type="text" size="30" readonly="readonly"></td>
										</tr>
										<tr>
											<th>调拨人：</th>
											<td><input id="allocationPerson" name="allocationPerson" value="${wimTransvouch.allocationPerson }" type="text" size="30" readonly="readonly"></td>
											<th>调拨日期：</th>
											<td><input id="tvdate" type="text" size="25" value="<s:date name="%{wimTransvouch.tvdate}" format="yyyy-MM-dd HH:mm:ss"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
										</tr>
										<tr>
											<th>转出仓库：</th>
											<td><input type="hidden" id="outInvWarehouseId" name="outInvWarehouseId" value="${wimTransvouch.outInvWarehouse.id}" /> <input id="outInvWarehouseName" name="outInvWarehouseName" value="${wimTransvouch.outInvWarehouse.name}" type="text" size="30" /><input class="btn" type="button" value="选仓" onclick="chooseWarehouse();" /></td>
											<th>转入仓库：</th>
											<td><input type="hidden" id="inInvWarehouseId" name="inInvWarehouseId" value="${wimTransvouch.inInvWarehouse.id}" /> <input id="inInvWarehouseName" name="inInvWarehouseName" value="${wimTransvouch.inInvWarehouse.name}" type="text" size="30" /><input class="btn" type="button" value="选仓" onclick="chooseInWarehouse();" /></td>
										</tr>
										<tr>
											<th>出库类别：</th>
											<td><select id="outstockcatalogcode" name="outstockcatalogcode" disabled="disabled">
													<option value="">请选择</option>
													<option value="销售出库">销售出库</option>
													<option value="生产出库">生产出库</option>
													<option value="其他出库">其他出库</option>
											</select></td>
											<th>入库类别：</th>
											<td><select id="instockcatalogcode" name="instockcatalogcode" disabled="disabled">
													<option value="" selected="selected">请选择</option>
													<option value="采购入库">采购入库</option>
													<option value="生产入库">生产入库</option>
													<option value="其他入库">其他入库</option>
											</select></td>
										</tr>
										<tr>
											<th>审批人：</th>
											<td><input id="approvalPerson" name="approvalPerson" value="${wimTransvouch.approvalPerson }" type="text" size="30" readonly="readonly"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/allocateTransferAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '商品编码',
							width : 100,
							align : 'center'
							}, {
							field : 'itemname',
							title : '商品名称',
							width : 500,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'skuCode',
							title : 'SKU编码',
							width : 100,
							align : 'center'
							}, {
							field : 'masterUnit',
							title : '单位',
							width : 100,
							align : 'center'
							}, {
							field : 'tvquantity',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							} ] ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>