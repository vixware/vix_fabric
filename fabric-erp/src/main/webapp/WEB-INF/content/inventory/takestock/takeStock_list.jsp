<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
/* 内容 */
var name = "";
function loadName() {
	name = $('#nameS').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
/* 盘点单号 */
/* var stocktakingcode = "";
function loadStocktakingcode() {
	stocktakingcode = $('#stocktakingcode').val();
	stocktakingcode = Url.encode(stocktakingcode);
	stocktakingcode = Url.encode(stocktakingcode);
} */
/* 盘点仓库 */
/* var warehousename = "";
function loadWarehousename() {
	warehousename = $('#warehousename').val();
	warehousename = Url.encode(warehousename);
	warehousename = Url.encode(warehousename);
} */
/* 盘点人 */
/* var personcode = "";
function loadPersoncode() {
	personcode = $('#personcode').val();
	personcode = Url.encode(personcode);
	personcode = Url.encode(personcode);
} */
/* 搜索 */
function searchForContent(tag) {
	loadName();
	//loadStocktakingcode();
	//loadWarehousename();
	//loadPersoncode();
	//if (tag == 0) {
		pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?name=" + name + "&tag=" + tag, 'takeStock');
	/* } else {
		pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?stocktakingcode=" + stocktakingcode + "&warehousename=" + warehousename + "&personcode=" + personcode + "&tag=" + tag, 'takeStock');
	} */
}
/* 重置 */
function resetForContent(tag) {
	$("#nameS").val("");
	//$("#stocktakingcode").val("");
	//$("#warehousename").val("");
	//$("#personcode").val("");
}
//bindSearch();
/* 新建盘点单 */
function saveOrUpdate(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/takeStockAction!goSaveOrUpdate.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
};
//导出
function exportExcel(id) {
	form = document.getElementById("exportMD");
	form.action = "${vix}/inventory/takeStockAction!exportTakeStockExcel2.action?id=" + id;
	form.submit();
}
function exportExcel1(id) {
	form = document.getElementById("exportMD1");
	form.action = "${vix}/inventory/takeStockAction!exportTakeStockExcel1.action?id=" + id;
	form.submit();
}
loadName();
// 载入分页列表数据
pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?name=" + name, 'takeStock');
// 排序
function orderBy(orderField) {
	var orderBy = $("#takeStockOrderBy").val();
	if (orderBy == 'desc') {
		orderBy = "asc";
	} else {
		orderBy = 'desc';
	}
	pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'takeStock');
}

function currentPager(tag) {
	loadName();
	pager(tag, "${vix}/inventory/takeStockAction!goSingleList.action?name=" + name, 'takeStock');
}
function importXlsFile() {
	$.ajaxFileUpload({
	url : '${vix}/inventory/takeStockAction!importFile.action?id='+$('#stockTakingId').val(),// 用于文件上传的服务器端请求地址
	secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
	fileElementId : 'fileToUpload',
	dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
	success : function(data, status) { // 服务器成功响应处理函数
		var data = $.parseJSON(data);
		if (data.success == '1') {
			asyncbox.success(data.msg, "提示");
			searchForContent();
		} else {
			if (typeof (data.error) != 'undefined') {
				if (data.error != '') {
					asyncbox.alert(data.error, "上传错误");
				} else {
					alert(data.msg);
				}
			}
		}
	},
	error : function(data, status, e) {// 服务器响应失败处理函数
		asyncbox.success("上传错误!", "提示");
	}
	});
}

function goTakeStock(id) {
	$.ajax({
	url : '${vix}/inventory/takeStockAction!goTakeStock.action?1=1',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 200,
		title : "盘点条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				$.ajax({
				url : '${vix}/inventory/takeStockAction!goSaveOrUpdate.action?warehouseId=' + $("#warehouseId").val() + "&invShelfId=" + $("#invShelfId").val(),
				cache : false,
				success : function(html) {
					$("#mainContent").html(html);
				}
				});
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
/** 状态 */
function saleOrderStatus(status) {
	pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?status=" + status, 'takeStock');
}
/* 最近使用 */
function leastRecentlyUsed(days) {
	pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?days=" + days, 'takeStock');
}
function goShowTakeStock(id, pageNo) {
	$.ajax({
	url : '${vix}/inventory/takeStockAction!goShowTakeStock.action?id=' + id + "&pageNo=" + pageNo,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}

//打印入库单
function goPrintDelivery(id) {
	$.ajax({
	url : '${vix}/inventory/takeStockAction!goPrintStockTaking.action?id=' + id,
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

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/inventory/takeStockAction!goSearch.action',
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
				pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?stocktakingcode=" + $("#stocktakingcode").val() + "&warehousename=" + $("#warehousename").val() + "&personcode=" + $("#personcode").val()+ "&itemname=" + $('#itemname').val()+ "&itemcode=" + $('#itemcode').val(), 'takeStock');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
//单条删除
function deleteById(id) {
	$.ajax({
	url : '${vix}/inventory/takeStockAction!deleteById.action?id=' + id,
	cache : false,
	success : function(html) {
		asyncbox.success(html, "提示信息", function(action) {
			pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?name=" + name, 'takeStock');
		});
	}
	});
}
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
		url : '${vix}/inventory/takeStockAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/inventory/takeStockAction!goSingleList.action?name=" + name, 'takeStock');
			});
		}
	});
};

function setValue(id){
	$('#stockTakingId').val(id);
}
/* function importXlsFile() {
	$.ajaxFileUpload({
	url : '${vix}/inventory/standingBookAction!importFile.action',//用于文件上传的服务器端请求地址
	secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
	fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
	dataType : 'text',//返回值类型 ,可以使xml、text、json、html
	success : function(data, status) { //服务器成功响应处理函数
		var data = $.parseJSON(data);
		if (data.success == '1') {
			showMessage(data.msg);
			setTimeout("", 1000);
			loadContent('${vix}/inventory/takeStockAction!goList.action');
		} else {
			if (typeof (data.error) != 'undefined') {
				if (data.error != '') {
					showErrorMessage(data.error);
					setTimeout("", 1000);
				} else {
					showMessage(data.msg);
					setTimeout("", 1000);
				}
			}
		}
	},
	error : function(data, status, e) {//服务器响应失败处理函数
		showErrorMessage("上传错误!");
		setTimeout("", 1000);
	}
	});
} */

</script>
<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
<input type="hidden" id="stockTakingId" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_takestock.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#"><s:text name="inventory_takeStock_business" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="goTakeStock(0);"><span>盘点</span> </a>
		</p>
	</div>
</div>
<form action="${vix}/inventory/takeStockAction!exportTakeStockExcel2.action" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<form action="${vix}/inventory/takeStockAction!exportTakeStockExcel1.action" method="post" name="exportMD1" id="exportMD1" target="form_iframe" style="margin: 0"></form>
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
			<label>内容:<input type="text" class="int" id="nameS" placeholder="编码"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label>编码:<input type="text" class="int" name="stocktakingcode" id="stocktakingcode" placeholder="编码"></label> <label>仓库:<input type="text" class="int" name="warehousename" id="warehousename" placeholder="仓库"></label> <label>盘点人:<input type="text" class="int" name="personcode" id="personcode" placeholder="盘点人"></label><label><input
				type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="stockTakingList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});"><span style="display: none;">${c.chineseCharacter}</span>${c.name}</a></li>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="takeStockInfo"></b> <s:text name='cmn_to' /> <b class="takeStockTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="takeStock" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="takeStockInfo"></b> <s:text name='cmn_to' /> <b class="takeStockTotalCount"></b>)
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