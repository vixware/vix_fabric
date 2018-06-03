<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	function goBeforeGoods(id) {
		$.ajax({
		url : '${vix}/business/itemDownLoadAction!goBeforeGoods.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
	function goAfterGoods(id) {
		$.ajax({
		url : '${vix}/business/itemDownLoadAction!goAfterGoods.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
</script>
<input type="hidden" id="goodsId" name="goodsId" value="${goods.id}" />
<div class="content" id="ordercontent">
	<form id="goods">
		<div class="order">
			<dl>
				<br />
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="goBeforeGoods(${goods.id });">上一条 </a> <a href="#" onclick="goAfterGoods(${goods.id });">下一条 </a>
					</span>
				</dt>
				<br />
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
											<td><input id="goodsCode" name="goodsCode" value="${goods.goodsCode }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>商品名称：</th>
											<td><input id="goodName" name="goodName" value="${goods.goodName }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
										</tr>
										<tr>
											<th>库存：</th>
											<td><input id="storenum" name="storenum" value="${goods.storenum }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>价格：</th>
											<td><input id="price" name="price" value="${goods.price }" class="ipt w88per underline" type="text" size="30" readonly="readonly" /></td>
										</tr>
										<tr>
											<th>网店名称：</th>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${goods.channelDistributor.name }" class="ipt w88per underline" type="text" size="30" readonly="readonly" /></td>
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
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 250px; width: 800px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,fitColumns: true,singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/business/itemDownLoadAction!getGoodsDetailJson.action?id=${goods.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'outerId',width:100,align:'center'">SKU编码</th>
										<th data-options="field:'bn',width:100,align:'center'">条形码</th>
										<th data-options="field:'spec',width:100,align:'center'">规格</th>
										<th data-options="field:'mktprice',width:200,align:'center'">市场价</th>
										<th data-options="field:'price',width:100,align:'center'">价格</th>
										<th data-options="field:'store',width:100,align:'center'">库存</th>
									</tr>
								</thead>
							</table>
							<script type="text/javascript">
								$('#dlAddress').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#dlAddress').datagrid('validateRow', editIndexDlAddress)) {
										$('#dlAddress').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#dlAddress').datagrid('getRows').length - 1;
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#dlAddress').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>