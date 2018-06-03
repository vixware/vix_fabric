<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	$("#order").validationEngine();
	$("#itemcode").focus();
	function textareaChanged() {
		updateOrderDetail();
	}
	//扫描快递单号
	function shippingNoChanged() {
		if ($("#newShippingNo").val() != $("#shippingNo").val()) {
			asyncbox.alert("快递单号不一致！", "提示");
		}
	}
	function updateOrderDetail() {/* 
				$.post('${vix}/business/orderProcessAction!updateOrderDetail.action', {
				'id' : $("#id").val(),
				'itemcode' : $("#itemcode").val()
				}, function(result) {
					showMessage(result);
					setTimeout("", 1000);
					$('#dlAddress').datagrid("reload");
					$("#itemcode").val('');
				}); */
	}
</script>
<input type="hidden" id="orderId" name="orderId" value="${order.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
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
											<td><input id="created" name="created" value="${order.createTime}" type="text" class="ipt w88per underline" readonly="readonly" /><img onclick="showTime('created','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>店铺名称：</th>
											<td><input id="channelName" name="channelName" value="${order.channelDistributor.channelName }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>网店类型：</th>
											<td><input id="channelType" name="channelType" value="${order.channelDistributor.channelTypeId}" class="ipt w88per underline" type="text" size="30" readonly="readonly" /></td>
										</tr>
										<tr>
											<th>客户名称：</th>
											<td><input id="receiverName" name="receiverName" value="${order.receiverName}" class="ipt w88per underline" type="text" size="30" readonly="readonly" /></td>
											<th>客户留言：</th>
											<td><input id="buyerMessage" name="buyerMessage" value="${order.buyerMessage}" type="text" size="30" class="ipt w88per underline" readonly="readonly" /></td>
										</tr>
										<tr>
											<th>扫描条形码：</th>
											<td><input id="itemcode" name="itemcode" type="text" onchange="textareaChanged();" class="ipt w88per underline" /></td>
											<th><span style="color: red;">重量：</span></th>
											<td><input id="weight" name="weight" type="text" value="${order.weight}" class="ipt w88per underline" /></td>
										</tr>
										<tr>
											<th>快递单号：</th>
											<td><input id="shippingNo" name="shippingNo" type="text" value="${order.shippingNo}" class="ipt w88per underline" /></td>
											<th>扫描快递单号：</th>
											<td><input id="newShippingNo" name="newShippingNo" type="text" onchange="shippingNoChanged();" class="ipt w88per underline" /></td>
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
							<table id="dlAddress" class="easyui-datagrid" style="width: 900px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,fitColumns: true,singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/business/orderProcessAction!getOrderDetailJson.action?id=${order.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'outerGoodsId',width:100,align:'center',editor:'text'">商品编码</th>
										<th data-options="field:'outerId',width:100,align:'center',editor:'text'">SKU编码</th>
										<th data-options="field:'title',width:200,align:'center',editor:'text'">商品名称</th>
										<th data-options="field:'num',width:100,align:'center',editor:'numberbox'">购买数量</th>
										<th data-options="field:'pickingNum',width:100,align:'center',editor:'numberbox'">配货数量</th>
										<th data-options="field:'price',width:100,align:'center',editor:'numberbox'">商品价格</th>
										<th data-options="field:'payment',width:100,align:'center',editor:'numberbox'">实付金额</th>
										<th data-options="field:'status',width:100,align:'center',editor:'text'">状态</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
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