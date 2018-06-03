<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
	/** 保存商品信息 */
	function saveOrUpdateInventoryCurrentStock() {

		if ($("#objectExpandId").val() != null && $("#objectExpandId").val() != '') {
			if ($('#inventoryForm').validationEngine('validate')) {
				var ids = "[";
				if (document.getElementById("skutable")) {
					var tb = document.getElementById("skutable");
					for (var i = 1; i < tb.rows.length; i++) {
						if (i != 1) {
							ids += ",";
						}
						ids += "{";
						for (var j = 0; j < tb.rows[i].cells.length; j++) {
							var child = tb.rows[i].cells[j].childNodes[0];
							if (child && child.type == "text") {
								if (j != 0) {
									ids += ",";
								}
								ids += "'" + child.name + "':'" + child.value + "'";
							}
						}
						ids += "}";
					}
				}
				ids += "]";

				$.post('${vix}/inventory/initInventoryAction!saveOrUpdate.action', {
				'inventoryCurrentStock.id' : $("#id").val(),
				'inventoryCurrentStock.itemcode' : $("#itemcode").val(),
				'inventoryCurrentStock.itemname' : $("#itemname").val(),
				'inventoryCurrentStock.masterUnit' : $("#masterUnit").val(),
				'inventoryCurrentStock.itemtype' : $("#itemtype").val(),
				'inventoryCurrentStock.batchcode' : $("#batchcode").val(),
				'inventoryCurrentStock.serialcode' : $("#serialcode").val(),
				'inventoryCurrentStock.producedate' : $("#producedate").val(),
				'inventoryCurrentStock.manufactureDate' : $("#manufactureDate").val(),
				'inventoryCurrentStock.listingDate' : $("#listingDate").val(),
				'inventoryCurrentStock.massunitEndTime' : $("#massunitEndTime").val(),
				'inventoryCurrentStock.invWarehouse.id' : $("#warehouseId").val(),
				'inventoryCurrentStock.invShelf.id' : $("#invShelfId").val(),
				'inventoryCurrentStock.price' : $("#price").val(),
				'inventoryCurrentStock.quantity' : $("#quantity").val(),
				'inventoryCurrentStock.skuCode' : $("#skuCode").val(),
				'updateField' : updateField,
				'skudata' : ids
				}, function(result) {
					showMessage(result);
					setTimeout("", 1000);
					loadContent('${vix}/inventory/initInventoryAction!goList.action');
				});
			} else {
				return false;
			}
		} else {
			asyncbox.success("请选择商品类型!", "提示信息");
		}

	}

	function choosewarehouse() {
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
	function chooseShelf() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseShelf.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择货位",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#invShelfName").val(result[1]);
						$("#invShelfId").val(result[0]);
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
	function loadSpecification(id) {
		$.ajax({
		url : '${vix}/inventory/initInventoryAction!loadSpecification.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#objectExpandField").html(html);
		}
		});
	}
	function updateSpecification() {
		var specificationDetailids = '';
		$("input[type=checkbox]:checked").each(function() {
			specificationDetailids += $(this).attr('name') + ',';
		});
		$.ajax({
		url : '${vix}/inventory/initInventoryAction!goUpdateSpecification.action?specificationDetailids=' + specificationDetailids + "&objectExpandId=" + $("#objectExpandId").val(),
		cache : false,
		success : function(html) {
			$("#specification_div").html(html);
		}
		});
	}

	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#channelDistributorId").val(result[0]);
						$("#channelDistributorName").val(result[1]);
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseItem() {
		if ($("#objectExpandId").val() != null && $("#objectExpandId").val() != '') {
			$.ajax({
			url : '${vix}/inventory/initInventoryAction!goItem.action?1=1&objectExpandId=' + $("#objectExpandId").val(),
			cache : false,
			success : function(html) {
				asyncbox.open({
				modal : true,
				width : 750,
				height : 450,
				title : "选择商品",
				html : html,
				callback : function(action, returnValue) {
					if (action == 'ok') {
						if (returnValue != '') {
							$.ajax({
							url : '${vix}/inventory/initInventoryAction!getItemListJson.action?itemId=' + returnValue,
							cache : false,
							success : function(json) {
								var obj = eval(json);
								$("#itemcode").val(obj.code);
								$("#itemname").val(obj.name);
								$("#masterUnit").val(obj.measureUnit.name);
								$("#itemtype").val(obj.itemType);
								$("#price").val(obj.costPrice);
								$("#producedate").val(obj.produceDate);
								$("#qualityperiod").val(obj.qualityPeriod);
								$("#batchcode").val(obj.batchCode);
								$("#serialcode").val(obj.serialCode);
								$("#skuCode").val(obj.skuCode);
							}
							});
						} else {
							asyncbox.success("请选择${vv:varView("vix_mdm_item")}!", "<s:text name='vix_message'/>");
							return false;
						}
					}
				},
				btnsbar : $.btn.OKCANCEL
				});
			}
			});
		} else {
			asyncbox.success("请选择商品类型!", "提示信息");
		}
	}
	$("#inventoryForm").validationEngine();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='business' ">
					<li><a href="#"><img src="${vix}/common/img/inv_initialinventory.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#">网店管理 </a></li>
					<li><a href="#">商品管理 </a></li>
					<li><a href="#">商品创建</a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/inv_initialinventory.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#">库存管理 </a></li>
					<li><a href="#">初始设置 </a></li>
					<li><a href="#">期初录入</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><a onclick="saveOrUpdateInventoryCurrentStock()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <s:if test="source =='business' ">
					<a href="#" onclick="loadContent('${vix}/business/itemDownLoadAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</s:if> <s:else>
					<a href="#" onclick="loadContent('${vix}/inventory/standingBookAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</s:else> </b>
		</div>
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
				async : {
				enable : true,
				url : "${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
				autoParam : [ "id" ]
				},
				callback : {
					onClick : onClick
				}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					loadSpecification(treeNode.id);
					$("#objectExpandId").val(treeNode.id);
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<!-- left -->
		<form id="inventoryForm">
			<input type="hidden" id="id" name="id" value="${inventoryCurrentStock.id}" /> <input type="hidden" id="qualificationid" name="qualificationid" value="${qualification.id}" /> <input type="hidden" id="objectExpandId" name="objectExpandId" value="${objectExpandId}" />
			<div class="right_content">
				<div class="order_table">
					<div class="nt">
						<div class="nt_title">
							<label>基本信息</label>
						</div>
						<table>
							<tr height="30">
								<td width="90" align="right">编码：</td>
								<td><input type="text" name="itemcode" id="itemcode" value="${inventoryCurrentStock.itemcode }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><input class="btn" type="button" value="选择" onclick="chooseItem();" /><span style="color: red;">*</span></td>
								<td width="90" align="right">名称：</td>
								<td><input type="text" name="itemname" id="itemname" value="${inventoryCurrentStock.itemname }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="30">
								<td width="90" align="right">计量单位：</td>
								<td><input type="text" name="masterUnit" id="masterUnit" value="${inventoryCurrentStock.masterUnit }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
								<td width="90" align="right">门店：</td>
								<td><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${inventoryCurrentStock.channelDistributor.id }" onchange="fieldChanged(this);" /><input type="text" name="channelDistributorName" id="channelDistributorName" value="${inventoryCurrentStock.channelDistributor.name }" size="30" /><input class="btn"
									type="button" value="选择" onclick="chooseParentOrganization();" /></td>
							</tr>
							<tr height="30">
								<td width="90" align="right">仓库：</td>
								<td><input type="hidden" id="warehouseId" name="warehouseId" /><input type="text" name="warehouseName" id="warehouseName" value="${inventoryCurrentStock.invWarehouse.name }" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><input class="btn" type="button" value="选择" onclick="choosewarehouse();" /><span
									style="color: red;">*</span></td>
								<td width="90" align="right">货位：</td>
								<td><input type="hidden" id="invShelfId" name="invShelfId" value="${inventoryCurrentStock.invShelf.id }" onchange="fieldChanged(this);" /><input type="text" name="invShelfName" id="invShelfName" value="${inventoryCurrentStock.invShelf.name }" size="30" class="validate[required] text-input" /><input class="btn" type="button"
									value="选择" onclick="chooseShelf();" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="30">
								<td width="90" align="right">批号：</td>
								<td><input type="text" name="batchcode" id="batchcode" value="${inventoryCurrentStock.batchcode }" size="30" onchange="fieldChanged(this);" /></td>
								<td width="90" align="right">序列号：</td>
								<td><input type="text" name="serialcode" id="serialcode" value="${inventoryCurrentStock.serialcode }" size="30" onchange="fieldChanged(this);" /></td>
							</tr>
							<tr height="30">
								<td width="90" align="right">生产日期：</td>
								<td><input id="producedate" name="producedate" value="${inventoryCurrentStock.producedate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('producedate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td width="90" align="right">到期日：</td>
								<td><input id="massunitEndTime" name="massunitEndTime" value="${inventoryCurrentStock.massunitEndTime}" type="text" readonly="readonly" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('massunitEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
									align="absmiddle"><span style="color: red;">*</span></td>
							</tr>
							<tr height="30">
								<td width="90" align="right">出厂日期：</td>
								<td><input id="manufactureDate" name="manufactureDate" value="${inventoryCurrentStock.manufactureDate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('manufactureDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td width="90" align="right">上市日期：</td>
								<td><input id="listingDate" name="listingDate" value="${inventoryCurrentStock.listingDate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('listingDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
						</table>
					</div>
					<div id="objectExpandField"></div>
					<div id="specification_div"></div>
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
						<tr height="30">
							<td colspan="4" align="center"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdateInventoryCurrentStock();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>