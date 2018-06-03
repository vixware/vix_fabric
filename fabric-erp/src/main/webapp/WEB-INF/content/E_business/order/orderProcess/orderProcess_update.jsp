<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	function goBeforeOrder(id) {
		$.ajax({
		url : '${vix}/business/orderProcessAction!goBeforeOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
	function goAfterOrder(id) {
		$.ajax({
		url : '${vix}/business/orderProcessAction!goAfterOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
</script>
<input type="hidden" id="orderId" name="orderId" value="${order.id}" />
<div class="content" id="ordercontent">
	<form id="order">
		<div class="order">
			<dl>
				<br />
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="goBeforeOrder(${order.id });"><img width="22" height="22" title="上一条" src="${vix}/common/img/m_previous.gif"> </a> <a href="#" onclick="goAfterOrder(${order.id });"> <img width="22" height="22" title="下一条" src="${vix}/common/img/m_next.gif">
					</a>
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
											<th>订单编码：</th>
											<td><input id="outerId" name="outerId" value="${order.outerId }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>订单日期：</th>
											<td><input id="created" name="created" value="<s:date name="%{order.created}" format="yyyy-MM-dd  HH:mm:ss"/>" type="text" class="ipt w88per underline" readonly="readonly" /></td>
										</tr>
										<tr>
											<th>店铺名称：</th>
											<td><input id="channelName" name="channelName" value="${order.channelDistributor.name }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>客户名称：</th>
											<td><input id="receiverName" name="receiverName" value="${order.receiverName}" class="ipt w88per underline" type="text" size="30" readonly="readonly" /></td>
										</tr>
										<tr>
											<th>收货地址：</th>
											<td colspan="3"><input id="receiverAddress" name="receiverAddress" value="${order.receiverAddress}" class="ipt w88per underline" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>客户留言：</th>
											<td colspan="3"><input id="buyerMessage" name="buyerMessage" value="${order.buyerMessage}" type="text" size="30" class="ipt w88per underline" /></td>
										</tr>
										<tr>
											<th><span style="color: red;">卖家备注：</span></th>
											<td colspan="3"><input id="sellerMemo" name="sellerMemo" value="${order.sellerMemo}" class="ipt w88per underline" type="text" size="30" /></td>
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
							<table id="dlAddress" class="easyui-datagrid" style="width: 800px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,fitColumns: true,singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/business/orderProcessAction!getOrderDetailJson.action?id=${order.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'outerGoodsId',width:100,align:'center'">商品编码</th>
										<th data-options="field:'outerId',width:100,align:'center'">SKU编码</th>
										<th data-options="field:'title',width:200,align:'center'">商品名称</th>
										<th data-options="field:'price',width:100,align:'center'">商品价格</th>
										<th data-options="field:'num',width:100,align:'center'">购买数量</th>
										<th data-options="field:'payment',width:100,align:'center'">实付金额</th>
										<th data-options="field:'status',width:100,align:'center'">状态</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="goSaveOrUpdateOrder(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
							function goSaveOrUpdateOrder(id) {
								$.ajax({
								url : '${vix}/business/orderProcessAction!goSaveOrUpdateOrderDetail.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 750,
									height : 450,
									title : "订单明细",
									html : html,
									callback : function(action) {
										if (action == 'cancel' || action == 'close') {
											$('#dlAddress').datagrid("reload");
										}
									},
									btnsbar : [ {
									text : '关闭',
									action : 'cancel'
									} ]
									});
								}
								});
							}
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