<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
	loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	//载入分页列表数据
	pager("start", "${vix}/drp/webPOSAction!goItemCatalogList.action?1=1", 'itemCatalogList');
	pager("start", "${vix}/drp/webPOSAction!goItemList.action?1=1", 'itemList');
	function currentPager(tag) {
		pager(tag, "${vix}/drp/webPOSAction!goItemCatalogList.action?1=1", 'itemCatalogList');
	}
	function currentItemPager(tag) {
		pager(tag, "${vix}/drp/webPOSAction!goItemList.action?1=1", 'itemList');
	}

	function paynow() {
		saveDlLineItem();
		var dlData = $("#dlLineItem").datagrid("getRows");
		var saleOrderItemDataList = JSON.stringify(dlData);

		$.post('${vix}/drp/webPOSAction!saveOrUpdate.action', {
			'saleOrderItemDataList' : saleOrderItemDataList
		}, function(json) {
			$.ajax({
			url : '${vix}/drp/webPOSAction!goPrintSalesOrder.action?id=' + json,
			cache : false,
			success : function(html) {
				LODOP = getLodop();
				LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
				LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
				LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
				LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
				LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
				LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
				/* LODOP.PRINT(); */
				LODOP.PREVIEW();
			}
			});
			loadContent('${vix}/drp/webPOSAction!goList.action');
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_training.png" alt="" />供应链</a></li>
				<li><a href="#">分销管理</a></li>
				<li><a href="#">门店管理</a></li>
				<li><a href="#">WebPOS</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="pos_box clearfix">
			<div class="pos_left">
				<div class=" pos_border pos_btns">
					<div class="pos_title">
						<span>商品分类</span>
					</div>
					<div class="pos_type">
						<div id="itemCatalogList"></div>
					</div>
					<div class="pos_page">
						<a href="#" class="pos_btn" onclick="currentPager('previous');"><span>上一个</span> </a> <a href="#" class="pos_btn" onclick="currentPager('next');"><span>下一个</span> </a>
					</div>
				</div>
			</div>
			<div class="pos_middle">
				<div class="pos_border">
					<div class="pos_title">
						<span>商品</span>
					</div>
					<div class="pos_item">
						<div id="itemList"></div>
					</div>
					<div class="pos_page">
						<a href="#" class="pos_btn" onclick="currentItemPager('start');"><span>返回</span> </a> <a href="#" class="pos_btn" onclick="currentItemPager('previous');"><span>上一个</span> </a> <a href="#" class="pos_btn" onclick="currentItemPager('next');"><span>下一个</span> </a>
					</div>
				</div>
				<div class="pos_border">
					<div class="pos_title">
						<span>其他</span>
					</div>
					<a href="#" class="pos_btn"><span>订单信息</span> </a> <a href="#" class="pos_btn"><span>MISC</span> </a> <a href="#" class="pos_btn"><span>订单信息</span> </a>
				</div>
			</div>
			<div class="pos_right">
				<div class="pos_border">
					<div class="pos_title">
						<span>票据</span>
					</div>
					<div style="height: 300px;">
						<table id="dlLineItem" class="easyui-datagrid" style="height: 275px; margin: 6px;" data-options="iconCls: 'icon-edit',toolbar: '#dlAddressTb',singleSelect: true,fitColumns:true,url: '${vix}/drp/storeReturnRecordsAction!getSaleReturnFormItemJson.action?id=${saleReturnForm.id}',onClickRow: onDlClickRow1">
							<thead>
								<tr>
									<th data-options="field:'title',align:'left',width:180">名称</th>
									<th data-options="field:'price',width:60,align:'left',editor:'numberbox'">单价</th>
									<th data-options="field:'amount',width:60,align:'left',editor:'numberbox'">数量</th>
									<th data-options="field:'netTotal',width:60,align:'left',editor:'numberbox'">小计</th>
								</tr>
							</thead>
						</table>
						<div id="dlAddressTb" style="height: auto">
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlLineItem()"><span
								class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span></a>
						</div>
						<script type="text/javascript">
							$('#dlLineItem').datagrid();
							var editIndexDlLineItem = undefined;
							function endDlEditing1() {
								if (editIndexDlLineItem == undefined) {
									return true;
								}
								if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)) {
									$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow1(index) {
								if (editIndexDlLineItem != index) {
									if (endDlEditing1()) {
										$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlLineItem = index;
									} else {
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
									}
								}
							}
							function appendDlLineItem(id) {
								if (endDlEditing1()) {
									$.ajax({
									url : '${vix}/drp/webPOSAction!getInventoryCurrentStockJson.action?id=' + id,
									cache : false,
									success : function(json) {
										if (json != undefined) {
											var obj = eval(json);
											var index = $('#dlLineItem').datagrid('appendRow', {
											title : obj.itemname,
											price : obj.price,
											amount : 1,
											netTotal : obj.price
											}).datagrid('getRows').length - 1;
											$('#dlLineItem').datagrid('beginEdit', index);
										}
									}
									});
								}
							}
							function removeDlLineItem() {
								if (editIndexDlLineItem == undefined) {
									return;
								}
								$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem).datagrid('deleteRow', editIndexDlLineItem);
								editIndexDlLineItem = undefined;
							}
							function saveDlLineItem() {
								if (endDlEditing1()) {
									$('#dlLineItem').datagrid('acceptChanges');
								}
							}
						</script>
					</div>
					<div class="pos_form">
						<table>
							<tr>
								<td width="90" align="right">商品个数:</td>
								<td><input type="text" class="ipt" id="subtotal"></td>
							</tr>
							<tr>
								<td width="90" align="right">应收金额:</td>
								<td><input type="text" class="ipt" id="total"></td>
							</tr>
						</table>
					</div>
					<table class="pos_order_btn">
						<tr>
							<td colspan="2"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_02.png" /> </span> </a></td>
							<td colspan="2"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_01.png" /> </span> </a></td>
							<td colspan="2"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_03.png" /> </span> </a></td>
						</tr>
						<tr>
							<td colspan="4"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_05.png" /> 删除</span> </a></td>
							<td colspan="2"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_04.png" /> </span> </a></td>
						</tr>
						<tr>
							<td colspan="6"><a href="#" class="pos_btn" onclick="paynow();"><span><img src="img/webpos/pos_icon_06.png" /> 支付</span> </a></td>
						</tr>
						<tr>
							<td colspan="3"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_08.png" /> 取消</span> </a></td>
							<td colspan="3"><a href="#" class="pos_btn"><span><img src="img/webpos/pos_icon_07.png" /> 完成</span> </a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>