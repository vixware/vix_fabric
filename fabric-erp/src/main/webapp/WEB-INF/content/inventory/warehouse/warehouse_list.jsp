<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
var name = "";
function loadName() {
	name = $('#nameS').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
var selectCode = "";
function loadSelectCode() {
	selectCode = $('#selectCode').val();
	selectCode = Url.encode(selectCode);
	selectCode = Url.encode(selectCode);
}
var selectName = "";
function loadSelectName() {
	selectName = $('#selectName').val();
	selectName = Url.encode(selectName);
	selectName = Url.encode(selectName);
}
/* 新增仓库 */
function saveOrUpdateWarehouse(id, parentId, treeType) {
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goSaveOrUpdate.action?id=' + id + "&parentId=" + parentId + "&treeType=" + treeType,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 750,
		height : 555,
		title : "<s:text name='wim_warehouse'/>",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				if ($('#warehouseform').validationEngine('validate')) {
					$.post('${vix}/inventory/warehouseAction!saveOrUpdateWarehouse.action', {
					'invWarehouse.id' : $("#invWarehouseid").val(),
					'parentInvWarehouseId' : $("#parentInvWarehouseId").val(),
					'invWarehouse.employee.id' : $("#employeeId").val(),
					'invWarehouse.code' : $("#invWarehouseCode").val(),
					'invWarehouse.name' : $("#invWarehouseName").val(),
					'invWarehouse.properties' : $("#properties").val(),
					'invWarehouse.priceStyle' : $("#priceStyle").val(),
					'invWarehouse.warehousePerson' : $("#warehousePerson").val(),
					'invWarehouse.telephone' : $("#telephone").val(),
					'invWarehouse.charger' : $("#telephone").val(),
					'invWarehouse.fundQuota' : $("#fundQuota").val(),
					'invWarehouse.memo' : $("#memo").val(),
					'invWarehouse.provincesName' : $("#provincesName").val(),
					'provincesId' : $("#provincesId").val(),
					'updateField' : updateField,
					'invWarehouse.postion' : $("#postion").val()
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/inventory/warehouseAction!goList.action');
					});
				} else {
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
/**
 * 修改仓库
 */
function updateWarehouse(id) {
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goSaveOrUpdate.action?id=' + id ,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 750,
		height : 555,
		title : "<s:text name='wim_warehouse'/>",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				if ($('#warehouseform').validationEngine('validate')) {
					$.post('${vix}/inventory/warehouseAction!saveOrUpdateWarehouse.action', {
					'invWarehouse.id' : $("#invWarehouseid").val(),
					'parentInvWarehouseId' : $("#parentInvWarehouseId").val(),
					'invWarehouse.employee.id' : $("#employeeId").val(),
					'invWarehouse.code' : $("#invWarehouseCode").val(),
					'invWarehouse.name' : $("#invWarehouseName").val(),
					'invWarehouse.properties' : $("#properties").val(),
					'invWarehouse.priceStyle' : $("#priceStyle").val(),
					'invWarehouse.warehousePerson' : $("#warehousePerson").val(),
					'invWarehouse.telephone' : $("#telephone").val(),
					'invWarehouse.charger' : $("#telephone").val(),
					'invWarehouse.fundQuota' : $("#fundQuota").val(),
					'invWarehouse.memo' : $("#memo").val(),
					'invWarehouse.provincesName' : $("#provincesName").val(),
					'provincesId' : $("#provincesId").val(),
					'invWarehouse.postion' : $("#postion").val()
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/inventory/warehouseAction!goList.action');
					});
				} else {
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
/* 新增货区 */
function saveOrUpdateGoodsArea(id, parentId, treeType) {
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goSaveOrUpdateGoodsArea.action?id=' + id + "&parentId=" + parentId + "&treeType=" + treeType,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 275,
		title : "<s:text name='wim_goods_area'/>",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				if ($('#area').validationEngine('validate')) {
					$.post('${vix}/inventory/warehouseAction!saveOrUpdateGoodsArea.action', {
					'invWarehousezone.invWarehouse.id' : $("#parentId").val(),
					'invWarehousezone.invWarehouse.name' : $("#parentName").val(),
					'invWarehousezone.id' : $("#invWarehousezoneid").val(),
					'invWarehousezone.code' : $("#code").val(),
					'invWarehousezone.name' : $("#name").val(),
					'invWarehousezone.startTime' : $("#startTime").val(),
					'updateField' : updateField,
					'invWarehousezone.endTime' : $("#endTime").val()
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/inventory/warehouseAction!goList.action');
					});
				} else {
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
/* 新增货架 */
function goSaveOrUpdateShelf(id, parentId, treeType) {
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goSaveOrUpdateShelf.action?id=' + id + "&parentId=" + parentId + "&treeType=" + treeType,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 275,
		title : "货架",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				if ($('#shelfform').validationEngine('validate')) {
					$.post('${vix}/inventory/warehouseAction!saveOrUpdateShelf.action', {
					'invShelf.invWarehousezone.id' : $("#parentId").val(),
					'invShelf.invWarehousezone.name' : $("#parentName").val(),
					'invShelf.id' : $("#invShelfid").val(),
					'invShelf.code' : $("#invShelfCode").val(),
					'invShelf.name' : $("#invShelfName").val(),
					'invShelf.priority' : $("#priority").val(),
					'invShelf.maximum' : $("#maximum").val(),
					'updateField' : updateField,
					'invShelf.type' : 1
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/inventory/warehouseAction!goList.action');
					});
				} else {
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
/* 新增货位 */
function goSaveOrUpdateCargoSpace(id, parentId, treeType) {
	var type = 2;
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goSaveOrUpdateCargoSpace.action?id=' + id + "&parentId=" + parentId + "&treeType=" + treeType,
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 750,
		height : 375,
		title : "货位",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				if ($('#cargospaceform').validationEngine('validate')) {
					$.post('${vix}/inventory/warehouseAction!saveOrUpdateCargoSpace.action', {
					'invShelf.parentInvShelf.id' : $("#parentInvShelfId").val(),
					'invShelf.invWarehouse.id' : $("#invWarehouseId").val(),
					'invShelf.id' : $("#invShelfid").val(),
					'invShelf.code' : $("#invShelfCode").val(),
					'invShelf.name' : $("#invShelfName").val(),
					'invShelf.priority' : $("#priority").val(),
					'invShelf.maximum' : $("#maximum").val(),
					'invShelf.shelfLength' : $("#shelfLength").val(),
					'invShelf.shelfWidth' : $("#shelfWidth").val(),
					'invShelf.shelfHeight' : $("#shelfHeight").val(),
					'updateField' : updateField,
					'invShelf.type' : type
					}, function(result) {
						showMessage(result);
						setTimeout("", 1000);
						loadContent('${vix}/inventory/warehouseAction!goList.action');
					});
				} else {
					return false;
				}
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

function searchForContent(i) {
	loadName();
	loadSelectCode();
	loadSelectName()
	if (i == 0) {
		pager("start", "${vix}/inventory/warehouseAction!goSingleList.action?name=" + name, 'invShelf');
	} else {
		pager("start", "${vix}/inventory/warehouseAction!goSingleList.action?selectCode=" + selectCode + "&selectName=" + selectName, 'invShelf');
	}
}
function resetForContent() {
	$('#nameS').val('');
	$('#selectCode').val('');
	$('#selectName').val('');
}

loadName();
// 载入分页列表数据
pager("start", "${vix}/inventory/warehouseAction!goSingleList.action?name=" + name, 'invShelf');
// 排序
function orderBy(orderField) {
	loadName();
	var orderBy = $("#invShelfOrderBy").val();
	if (orderBy == 'desc') {
		orderBy = "asc";
	} else {
		orderBy = 'desc';
	}
	pager("start", "${vix}/inventory/warehouseAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'invShelf');
}

function categoryPager(tag) {
	loadName();
	pager(tag, "${vix}/inventory/warehouseAction!goSingleList.action?name=" + name, 'invShelf');
}
function categoryTab(num, befor, id, e, entity) {
	var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
	var pa = el.parentNode.getElementsByTagName("li");
	for (var i = 0; i < pa.length; i++) {
		pa[i].className = "";
	}
	el.className = "current";
	for (i = 1; i <= num; i++) {
		try {
			if (i == befor) {
				document.getElementById(id + i).style.display = "block";
			} else {
				document.getElementById(id + i).style.display = "none";
			}
		} catch (e) {

		}
	}
	categoryPager('start');
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/inventory/warehouseAction!goSearch.action',
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
				pager("start", "${vix}/inventory/warehouseAction!goSingleList.action?selectCode=" + $('#selectCode').val() + "&selectName=" + $('#selectName').val(), 'invShelf');
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
	$.ajax({
		url : '${vix}/inventory/warehouseAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/inventory/warehouseAction!goSingleList.action?1=1", 'invShelf');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#"><s:text name="wim_init_setting" /> </a></li>
				<li><a href="#"><s:text name="wim_warehouse_management" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<ul>
			<li><a href="#"><span>新增类别</span></a>
				<ul>
					<li><a href="#" onclick="saveOrUpdateWarehouse(0,$('#selectId').val(),$('#treeType').val());"><s:text name="wim_add_warehouse" /> </a></li>
					<li><a href="#" onclick="saveOrUpdateGoodsArea(0,$('#selectId').val(),$('#treeType').val());"><s:text name="wim_add_goods_area" /> </a></li>
					<li><a href="#" onclick="goSaveOrUpdateShelf(0,$('#selectId').val(),$('#treeType').val());"><s:text name="wim_add_shelf" /> </a></li>
					<li><a href="#" onclick="goSaveOrUpdateCargoSpace(0,$('#selectId').val(),$('#treeType').val());"><s:text name="wim_add_cargospace" /> </a></li>
				</ul></li>
		</ul>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label><label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label>编码:<input type="text" class="int" name="selectCode" id="selectCode" placeholder="编码"></label> <label>名称:<input type="text" class="int" name="selectName" id="selectName" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input
				type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="invShelfList" var="c">
				<li><a href="#" onclick="goSaveOrUpdateCargoSpace(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
		    var zTree;		
		    
			var setting = {
				async: {
					enable: true,
					url:"${vix}/inventory/warehouseAction!findInvTreeToJson.action",
					autoParam:["id","treeType"]
				},
				callback: {
					onClick: onClick
				}
			};
			function onClick(event, treeId, treeNode, clickFlag) {
				$("#selectId").val(treeNode.id);
				$("#treeType").val(treeNode.treeType);
				pager("start","${vix}/inventory/warehouseAction!goSingleList.action?parentId="+treeNode.id+"&treeType="+treeNode.treeType,"invShelf");
			}
			zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" />
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="categoryTab(3,1,'a',event,'invShelf')"><img src="img/mail.png" alt="" /> <s:text name='wim_slotting_information' /> </a></li>
				</ul>
			</div>
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a> </span> <span><a class="previous" onclick="categoryPager('previous');"></a> </span> <em>(<b class="invShelfInfo"></b> <s:text name='cmn_to' /> <b class="invShelfTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a> </span> <span><a class="end" onclick="categoryPager('end');"></a> </span>
					</div>
				</div>
				<div id="invShelf" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="categoryPager('start');"></a> </span> <span><a class="previous" onclick="categoryPager('previous');"></a> </span> <em>(<b class="invShelfInfo"></b> <s:text name='cmn_to' /> <b class="invShelfTotalCount"></b>)
						</em> <span><a class="next" onclick="categoryPager('next');"></a> </span> <span><a class="end" onclick="categoryPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>