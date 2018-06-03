<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>

<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');

var name = "";
function loadName() {
	name = $('#nameS').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
function saveOrUpdate(id, pageNo, biztype) {
	if (biztype == 1) {
		saveOrUpdateInboundWarehouse(id, pageNo);
	} else if (biztype == 0) {
		saveOrUpdateFinishedInboundWarehouse(id, pageNo);
	} else if (biztype == 2) {
		saveOrUpdateOtherInboundWarehouse(id, pageNo);
	} else if (biztype == 3) {
		saveOrUpdateScarletLetterInbound(id, pageNo);
	} else if (biztype == 4) {
		saveOrUpdateAdjustmentInboundWarehouse(id, pageNo);
	}else if (biztype == 5) {
		saveOrUpdateStoreEnquiryRequest(id, pageNo);
	}
}
function showOrder(id, pageNo, biztype) {
	if (biztype == 1) {
		showInboundWarehouse(id, pageNo);
	} else if (biztype == 0) {
		showFinishedInboundWarehouse(id, pageNo);
	} else if (biztype == 2) {
		showOtherInboundWarehouse(id, pageNo);
	} else if (biztype == 3) {
		showScarletLetterInbound(id, pageNo);
	}else if (biztype == 4) {
		showAdjustmentInboundWarehouse(id, pageNo);
	}else if (biztype == 5) {
		saveOrUpdateStoreEnquiryRequest(id, pageNo);
	}
}
function saveOrUpdateInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goSaveOrUpdate.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function showInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goShowInboundWarehouse.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function saveOrUpdateScarletLetterInbound(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goScarletLetterInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function saveOrUpdateAdjustmentInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goAdjustmentInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function showScarletLetterInbound(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goShowScarletLetterInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
//查看入库调整单
function showAdjustmentInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goShowAdjustmentInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function saveOrUpdateFinishedInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goAddFinishedGoodsInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function showFinishedInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goShowFinishedGoodsInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function saveOrUpdateOtherInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goAddOtherInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
		$("#selectOptionId2").hide();
		$("#selectOptionId1").show();
	}
	});
}
//来源要货单
function saveOrUpdateStoreEnquiryRequest(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goAddStoreEnquiryRequest.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function showOtherInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goShowOtherInbound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function deleteStockRecordsById(id) {
	asyncbox.confirm('确定要删除该单据吗?', '提示信息', function(action) {
		if (action == 'ok') {
			$.ajax({
			url : '${vix}/inventory/inboundWarehouseAction!deleteById.action?id=' + id,
			cache : false,
			success : function(html) {
				pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?name=" + name, 'stockrecords');
			}
			});
		}
	});
}

function searchForContent(tag) {
	loadName();
	if (tag == 0) {
		pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?content=" + name + "&tag=" + tag, 'stockrecords');
	}
}
function resetForContent(tag) {
	loadName();
	pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?1=1" , 'stockrecords');
	$("#selectlabelid").css("display", "none");
	$("#nameS").val('');
	$('#code1').val('');
	$('#warehousecode1').val('');
}
loadName();
// 载入分页列表数据
pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?name=" + name + "&pageNo=${pageNo}", 'stockrecords');
// 排序
function orderBy(orderField) {
	loadName();
	var orderBy = $("#stockrecordsOrderBy").val();
	if (orderBy == 'desc') {
		orderBy = "asc";
	} else {
		orderBy = 'desc';
	}
	pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'stockrecords');
}
function currentPager(tag) {
	loadName();
	pager(tag, "${vix}/inventory/inboundWarehouseAction!goSingleList.action?name=" + name, 'stockrecords');
}
/** 状态 */
function saleOrderStatus(status) {
	pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?status=" + status, 'stockrecords');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?days=" + days, 'stockrecords');
}
/** 选择单选供应商 */
function chooseSupplier() {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goChooseSupplier.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 1000,
		height : 500,
		title : "选择供应商",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != undefined) {
					var rv = returnValue.split(",");
					$("#supplierId").val(rv[0]);
					$("#supplierName").val(rv[1]);
				} else {
					asyncbox.success("请选择分类信息!", "提示信息");
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}

//bindSearch();
/* 加载顶部工具栏 */
loadTopDynamicMenuContent('${vix}/inventory/inboundWarehouseAction!goTopDynamicMenuContent.action');
// 打印入库单
function goPrintDelivery(id) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goPrintStockRecordLines.action?id=' + id,
	cache : false,
	success : function(html) {
		LODOP = getLodop();
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
		LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
		LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
		// LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
		LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
		/* LODOP.PRINT(); */
		LODOP.PREVIEW();
	}
	});
};
function goShowPurchaseOrder(id) {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goShowPurchaseOrder.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
};
/* 修改留痕 */
function goModifiedLeaveMark() {
	$.ajax({
		  url:'${vix}/inventory/inboundWarehouseAction!goModifiedLeaveMark.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 950,
					height : 600,
					title:"修改留痕信息",
					html:html
				});
		  }
	});
};
//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/inventory/inboundWarehouseAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				loadInventoryName();
				loadWarehouseCode();
				loadWarehousePerson();
				loadCheckPerson();
				loadItemName();
				loadSpecification()();
				pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?warehousecode=" + warehousecode + "&code1=" + $('#code1').val()+ "&name=" + inventoryname+ "&createTime=" + $('#createTime').val()+ "&warehousePerson=" +warehousePerson+ "&checkperson=" + checkperson+ "&itemname=" + itemname+ "&itemcode=" + $('#itemcode').val()+ "&specification=" + specification+ "&skuCode=" + $('#skuCode').val(), 'stockrecords');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	deleteByIds(ids);
}

function deleteByIds(ids) {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/inventory/inboundWarehouseAction!goSingleList.action?name=" + name, 'stockrecords');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="helps/scr/How to create a new project.htm#view1" target="_blank"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#"><s:text name="inventory_inbound_business" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span> </a>
				<ul>
					<li><a href="#" onclick="saveOrUpdateInboundWarehouse(0);">采购入库单 </a></li>
					<li><a href="#" onclick="saveOrUpdateFinishedInboundWarehouse(0);">产成品入库单 </a></li>
					<li><a href="#" onclick="saveOrUpdateScarletLetterInbound(0);">红字入库单 </a></li>
					<li><a href="#" onclick="saveOrUpdateOtherInboundWarehouse(0);">其他入库单 </a></li>
					<li><a href="#" onclick="saveOrUpdateAdjustmentInboundWarehouse(0);">入库调整单 </a></li>
					<li><a href="#" onclick="saveOrUpdateStoreEnquiryRequest(0);">要货单入库 </a></li>
				</ul></li>
		</ul>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="saleOrderStatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="saleOrderStatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="saleOrderStatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="saleOrderStatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>搜索内容:<input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" /></label> <label><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label><label> <input type="button" value="高级搜索"
				class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="stockRecordsList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},0,${c.biztype});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="stockrecordsInfo"></b> <s:text name='cmn_to' /> <b class="stockrecordsTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="stockrecords" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="stockrecordsInfo"></b> <s:text name='cmn_to' /> <b class="stockrecordsTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span><span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>