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
		saveOrUpdateSellOutBound(id, pageNo);
	} else if (biztype == 2) {
		saveOrUpdateMaterialOutBound(id, pageNo);
	} else if (biztype == 3) {
		saveOrUpdateOtherOutBound(id, pageNo);
	} else if (biztype == 4) {
		saveOrUpdateBusinessOrder(id, pageNo);
	}
};
function showOrder(id, pageNo, biztype) {
	showInboundWarehouse(id, pageNo);
}

function showInboundWarehouse(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goShowOutBound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
/**
 * 销售出库单
 */
function saveOrUpdateSellOutBound(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goSaveOrUpdate.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
/**
 * 材料出库
 */
function saveOrUpdateMaterialOutBound(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goMaterialOutBound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
/**
 * 其他出库
 */
function saveOrUpdateOtherOutBound(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goOtherOutBound.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
/* 网店出库单 */
function saveOrUpdateBusinessOrder(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goBusinessOrder.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
var outcode = "";
function loadOutcode() {
	outcode = $('#code').val();
	outcode = Url.encode(outcode);
	outcode = Url.encode(outcode);
}
var departmentcode = "";
function loadDepartmentcode() {
	departmentcode = $('#name').val();
	departmentcode = Url.encode(departmentcode);
	departmentcode = Url.encode(departmentcode);
}
/**
 * 搜索
 */
function searchForContent(tag) {
	loadName();
	loadOutcode();
	loadDepartmentcode();
	if (tag == 0) {
		pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?content=" + name, 'outBound');
	} else {
		pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?code=" + outcode + "&name=" + departmentcode, 'outBound');
	}
}
function resetForContent(tag) {
	if (tag == 0) {
		$("#nameS").val("");
	} else {
		$("#code").val("");
		$("#name").val("");
	}
}
loadName();
// 载入分页列表数据
pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?name=" + name, 'outBound');
// 排序
function orderBy(orderField) {
	loadName();
	var orderBy = $("#outBoundOrderBy").val();
	if (orderBy == 'desc') {
		orderBy = "asc";
	} else {
		orderBy = 'desc';
	}
	pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'outBound');
}

function currentPager(tag) {
	loadName();
	pager(tag, "${vix}/inventory/outBoundAction!goSingleList.action?name=" + name, 'outBound');
}
/** 状态 */
function saleOrderStatus(status) {
	pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?status=" + status, 'outBound');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?days=" + days, 'outBound');
}

function chooseWarehouse() {
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 560,
		height : 340,
		title : "选择仓库",
		html : html,
		callback : function(action, returnValue) {
			if (action == 'ok') {
				if (returnValue != undefined) {
					var result = returnValue.split(",");
					$("#warehouseId").val(result[0]);
					$("#warehouseName").val(result[1]);
				} else {
					asyncbox.success("请选择仓库信息!", "提示信息");
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
}
bindSearch();
// 打印出库单
function goPrintStockRecords(id) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goPrintStockRecords.action?id=' + id,
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
	url : '${vix}/inventory/outBoundAction!goShowSalesOrder.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
};

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!goSearch.action',
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
				pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?code=" + $('#code').val() + "&name=" + $('#name').val()+ "&warehousename=" + $('#warehousename').val()+ "&createTime=" + $('#createTime').val()+ "&warehousePerson=" + $('#warehousePerson').val()+ "&itemname=" + $('#itemname').val()+ "&itemcode=" + $('#itemcode').val(), 'outBound');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
function deleteByIds() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	asyncbox.success(ids, "选中的id");
}

function deleteById(id) {
	$.ajax({
	url : '${vix}/inventory/outBoundAction!deleteById.action?id=' + id,
	cache : false,
	success : function(html) {
		asyncbox.success(html, "提示信息", function(action) {
			pager("start", "${vix}/inventory/outBoundAction!goSingleList.action?name=" + name, 'outBound');
		});
	}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_outbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#"><s:text name="inventory_outbound_business" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>单据来源</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdateSellOutBound(0);">销售出库单 </a></li>
					<li><a href="#" onclick="saveOrUpdateMaterialOutBound(0);">材料出库单 </a></li>
					<li><a href="#" onclick="saveOrUpdateOtherOutBound(0);">其他出库单</a></li>
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
			<label>内容:<input type="text" class="int" id="nameS" placeholder="单号"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label>单号:<input type="text" class="int" name="code" id="code" placeholder="单号"></label> <label>主题:<input type="text" class="int" name="name" id="name" placeholder="主题"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="stockRecordsList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},0,${c.biztype});">${c.code}</a></li>
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
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="outBoundInfo"></b> <s:text name='cmn_to' /> <b class="outBoundTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="outBound" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="outBoundInfo"></b> <s:text name='cmn_to' /> <b class="outBoundTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>