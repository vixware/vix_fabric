<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	function loadCurrentExpenseDetail() {
		$.ajax({
		url : '${vix}/business/outBoundProcessAction!goListOrderBatchContent.action?orderBatchId=' + $("#orderBatchId").val(),
		cache : false,
		success : function(html) {
			$("#currentExpenseDetailTable").html(html);
		}
		});
	};
	loadCurrentExpenseDetail();
	//打印单个发货单
	function goPrintDelivery(invoiceListId) {
		$.ajax({
		url : '${vix}/business/outBoundProcessAction!goPrintSinglePickingList.action?invoiceListId=' + invoiceListId,
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PREVIEW_WINDOW(1, 2, 0, 1024, 550, "");
			/* LODOP.PRINT(); */
			LODOP.PREVIEW();
		}
		});
	};
	//打印单个快递单
	function bahtPreviewPrintExpressList(orderId) {
		$.ajax({
		url : '${vix}/business/printExpressListAction!goPrintSingleExpressList.action?orderId=' + orderId,
		cache : false,
		success : function(json) {
			var shipping = eval(json);
			//根据物流公司编码判断快递单模板
			LODOP = getLodop();
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);//打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			if (shipping.logisticCode == "STO") {
				createStoPage(shipping.sellerNick, shipping.sellerNick, shipping.receiverName, shipping.receiverCity, shipping.sellerNick, shipping.receiverAddress, shipping.sellerNick, shipping.receiverPhone, shipping.itemTitle, shipping.receiverCity);
			} else if (shipping.logisticCode == "ZTO") {
				createZtoPage(shipping.sellerNick, shipping.sellerNick, shipping.receiverName, shipping.receiverCity, shipping.sellerNick, shipping.receiverAddress, shipping.sellerNick, shipping.receiverPhone, shipping.itemTitle, shipping.receiverCity);
			} else if (shipping.logisticCode == "POST") {
				createPostPage(shipping.receiverName, shipping.receiverPhone, shipping.receiverAddress, shipping.sellerNick, shipping.receiverCity, shipping.itemTitle, shipping.sellerNick, shipping.receiverPhone, shipping.itemTitle, shipping.receiverCity);
			} else if (shipping.logisticCode == "EMS") {
				createEmsPage(shipping.receiverName, shipping.receiverPhone, shipping.receiverAddress, shipping.sellerNick, shipping.receiverCity, shipping.itemTitle, shipping.receiverCity, shipping.receiverPhone, shipping.itemTitle, shipping.receiverCity);
			}
			LODOP.SET_PREVIEW_WINDOW(1, 2, 0, 1024, 550, "");
			LODOP.PREVIEW();
		}
		});
	};

	//申通快递单打印测试通过
	function createStoPage(name1, name2, name3, name4, name5, name6, name7, name8, name9, name10) {
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/sto.jpg'>");
		LODOP.SET_PRINT_PAGESIZE(0, "230mm", "127mm", "");
		LODOP.ADD_PRINT_TEXTA("name3", "23mm", "108mm", "31.2mm", "7.4mm", name3);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 18);
		LODOP.ADD_PRINT_TEXTA("name5", "23mm", "17.5mm", "32mm", "5.3mm", name5);
		LODOP.ADD_PRINT_TEXTA("name6", "50.3mm", "102.1mm", "83.9mm", "5.6mm", name6);
		LODOP.ADD_PRINT_TEXTA("name8", "60.3mm", "117.5mm", "51.1mm", "6.6mm", name8);
		LODOP.ADD_PRINT_TEXTA("name9", "67.2mm", "19.6mm", "81.8mm", "5.3mm", name9);
		LODOP.ADD_PRINT_TEXTA("name10", "24.9mm", "155.8mm", "35.5mm", "6.9mm", name10);
		LODOP.ADD_PRINT_TEXTA("name4", "88.4mm", "143.4mm", "43.4mm", "16.4mm", name4);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 30);
	};
	//中通快递单打印测试通过
	function createZtoPage(name1, name2, name3, name4, name5, name6, name7, name8, name9, name10) {
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/zto.jpg'>");
		LODOP.SET_PRINT_PAGESIZE(0, "230mm", "127mm", "");
		LODOP.SET_SHOW_MODE("BKIMG_WIDTH", 870);
		LODOP.SET_SHOW_MODE("BKIMG_HEIGHT", 480);
		LODOP.ADD_PRINT_TEXTA("name2", "27.8mm", "18.5mm", "30.2mm", "7.4mm", name2);
		LODOP.ADD_PRINT_TEXTA("name3", "27.3mm", "101.9mm", "32.5mm", "5.8mm", name3);
		LODOP.ADD_PRINT_TEXTA("name4", "94.2mm", "125.1mm", "49.2mm", "14.6mm", name4);
		LODOP.SET_PRINT_STYLEA(0, "FontSize", 30);
		LODOP.ADD_PRINT_TEXTA("name6", "38.1mm", "101.9mm", "70.9mm", "13.8mm", name6);
		LODOP.ADD_PRINT_TEXTA("name8", "58.5mm", "101.6mm", "55.3mm", "6.6mm", name8);
		LODOP.ADD_PRINT_TEXTA("name9", "93.4mm", "11.6mm", "61.6mm", "9.3mm", name9);
		LODOP.ADD_PRINT_TEXTA("name10", "26.7mm", "152.1mm", "24.1mm", "6.6mm", name10);
	};

	//post测试通过
	function createPostPage(name1, name2, name3, name4, name5, name6, name7, name8, name9, name10) {
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/post.jpg'>");
		LODOP.SET_PRINT_PAGESIZE(0, "230mm", "127mm", "");
		LODOP.SET_SHOW_MODE("BKIMG_WIDTH", 870);
		LODOP.SET_SHOW_MODE("BKIMG_HEIGHT", 480);
		LODOP.ADD_PRINT_TEXTA("name1", "43.1mm", "16.4mm", "26.5mm", "5.3mm", name1);
		LODOP.ADD_PRINT_TEXTA("name2", "42.3mm", "55.8mm", "26.5mm", "5.3mm", name2);
		LODOP.ADD_PRINT_TEXTA("name3", "54.8mm", "21.2mm", "63mm", "12.2mm", name3);
		LODOP.ADD_PRINT_TEXTA("name4", "69.6mm", "15.9mm", "26.5mm", "5.3mm", name4);
		LODOP.ADD_PRINT_TEXTA("name6", "42.6mm", "88.9mm", "44.4mm", "39.7mm", name6);
	};
	//测试通过Ems
	function createEmsPage(name1, name2, name3, name4, name5, name6, name7, name8, name9, name10) {
		LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='${vix}/common/img/print/ems.jpg'>");
		LODOP.SET_PRINT_PAGESIZE(0, "230mm", "127mm", "");
		LODOP.SET_SHOW_MODE("BKIMG_WIDTH", 870);
		LODOP.SET_SHOW_MODE("BKIMG_HEIGHT", 480);
		LODOP.ADD_PRINT_TEXTA("name1", "52.1mm", "16.1mm", "26.5mm", "5.3mm", name1);
		LODOP.ADD_PRINT_TEXTA("name2", "52.1mm", "51.3mm", "26.5mm", "5.3mm", name2);
		LODOP.ADD_PRINT_TEXTA("name3", "65.9mm", "16.1mm", "85.2mm", "12.2mm", name3);
		LODOP.ADD_PRINT_TEXTA("name4", "21.4mm", "14mm", "26.5mm", "5.3mm", name4);
		LODOP.ADD_PRINT_TEXTA("name6", "99.7mm", "19.3mm", "94.7mm", "6.6mm", name6);
		LODOP.ADD_PRINT_TEXTA("name5", "81mm", "16.7mm", "32mm", "6.6mm", name5);
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">订单管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/business/printPickingListAction!goList.action');">分拣单管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="orderBatchId" name="orderBatchId" value="${orderBatchId}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="loadContent('${vix}/business/outBoundProcessAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>分拣单信息</b> </strong>
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
											<th>批次号：</th>
											<td><input id="code" name="code" value="${orderBatch.code }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
										</tr>
										<tr>
											<th>操作人：</th>
											<td><input id="creator" name="creator" value="${orderBatch.creator }" type="text" size="30" class="ipt w88per underline" readonly="readonly"></td>
											<th>操作日期：</th>
											<td><input id="createTime" name="createTime" value="${orderBatch.createTime}" type="text" class="ipt w88per underline" readonly="readonly" /></td>
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
							<li class="current"><a onclick="javascript:tab(2,1,'a',event);loadCurrentExpenseDetail();"><img alt="" src="img/mail.png">订单明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="display: block;">
						<div class="list_table" style="margin: 0; width: 100%"></div>
						<div style="padding: 8px;">
							<div id="currentExpenseDetailTable" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>