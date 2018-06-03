<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function goBeforeOrder(id) {
		$.ajax({
		url : '${vix}/drp/distributororderManagementAction!goBeforeSalesOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
	function goAfterOrder(id) {
		$.ajax({
		url : '${vix}/drp/distributororderManagementAction!goAfterSalesOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
</script>
<input type="hidden" id="orderId" name="orderId" value="${salesOrder.id}" />
<div class="content" id="ordercontent">
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
										<th>编码：</th>
										<td><input id="code" name="code" value="${salesOrder.code }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
										<th>主题：</th>
										<td><input id="name" name="name" value="${salesOrder.name }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
									</tr>
									<tr>
										<th>要货方：</th>
										<td><input id="channelDistributorName" name="channelDistributorName" value="${salesOrder.channelDistributor.name }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
										<th>要货日期：</th>
										<td><input id="createTime" name="createTime" value="<s:date name="%{salesOrder.createTime}" format="yyyy-MM-dd  HH:mm:ss"/>" type="text" class="ipt w88per underline" readonly="readonly" /></td>
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
						<table id="dlAddress" class="easyui-datagrid" style="width: 800px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,fitColumns: true,singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/drp/distributororderManagementAction!getSaleOrderItemJson.action?id=${salesOrder.id}'">
							<thead>
								<tr>
									<th data-options="field:'itemCode',width:100,align:'center'">商品编码</th>
									<th data-options="field:'title',width:200,align:'center'">商品名称</th>
									<th data-options="field:'price',width:100,align:'center'">价格</th>
									<th data-options="field:'amount',width:100,align:'center'">数量</th>
									<th data-options="field:'netTotal',width:100,align:'center'">金额</th>
								</tr>
							</thead>
						</table>
						<div id="dlAddressTb" style="height: auto">
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="goSaveOrUpdateOrder(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
								class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
							</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
						</div>
						<script type="text/javascript">
							$('#dlAddress').datagrid();
						</script>
					</div>
				</div>
			</div>
		</dl>
	</div>
</div>