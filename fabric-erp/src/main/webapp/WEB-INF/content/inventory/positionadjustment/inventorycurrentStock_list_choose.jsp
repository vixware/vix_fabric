<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function chooseItemForPrice(id, code, name) {
		$("#inventoryCurrentStockId").val(id);
		$("#itemCode").val(code);
		$("#itemName").val(name);
	}
	//载入分页列表数据
	pager("start", "${vix}/inventory/positionAdjustmentAction!goInventoryCurrentStockListContent.action?1=1", 'itemPriceManage');
	//排序 
	function orderBy(orderField) {
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/inventory/positionAdjustmentAction!goInventoryCurrentStockListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&1=1", 'itemPriceManage');
	}

	function currentPager(tag) {
		pager(tag, "${vix}/inventory/positionAdjustmentAction!goInventoryCurrentStockListContent.action?1=1", 'itemPriceManage');
	}
	$("#saleOrderItemForm").validationEngine();
	function addSalesOrderItem() {
		if ($('#saleOrderItemForm').validationEngine('validate')) {
			$.post('${vix}/inventory/positionAdjustmentAction!saveOrUpdateWimAdjustpvouchItem.action', {
			'wimAdjustpvouchItem.id' : $("#wimAdjustpvouchItemId").val(),
			'inventoryCurrentStockId' : $("#inventoryCurrentStockId").val(),
			'wimAdjustpvouchItem.wimAdjustpvouch.id' : $("#wimAdjustpvouchId").val(),
			'wimAdjustpvouchItem.newInvShelf.id' : $("#invShelfId").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$('#stockrecordlines').datagrid("reload");
				clearOrderItemContent();
			});
		} else {
			return false;
		}
	}
	function clearOrderItemContent() {
		$("#inventoryCurrentStockId").val("");
		$("#itemCode").val("");
		$("#itemName").val("");
	}
	function searchForContent() {
		pager("start", "${vix}/inventory/positionAdjustmentAction!goInventoryCurrentStockListContent.action?1=1", 'itemPriceManage');
	}
	function reset() {
		$("#nameS").val("");
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
						$("#invShelfId").val(result[0]);
						$("#invShelfName").val(result[1]);
					} else {
						asyncbox.success("请选择货位信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>名称<input type="text" class="int" id="nameS" onchange="searchForContent();"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" style="width: 500px;">
			<div class="left_content">
				<div id="itemPriceManage" class="table" style="overflow-y: auto; height: 300px;"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="itemPriceManageInfo"></b> <s:text name='cmn_to' /> <b class="itemPriceManageTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<form id="saleOrderItemForm">
					<s:hidden id="inventoryCurrentStockId" name="inventoryCurrentStockId" theme="simple" />
					<s:hidden id="wimAdjustpvouchId" name="wimAdjustpvouchId" value="%{wimAdjustpvouchId}" theme="simple" />
					<s:hidden id="wimAdjustpvouchItemId" name="wimAdjustpvouchItemId" value="%{wimAdjustpvouchItem.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="itemCode" name="itemCode" value="${wimAdjustpvouchItem.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="itemName" name="itemName" value="${wimAdjustpvouchItem.itemname}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>调入货位:&nbsp;</th>
								<td><input type="hidden" id="invShelfId" name="invShelfId" value="${wimAdjustpvouchItem.newInvShelf.id }" /><input type="text" name="invShelfName" id="invShelfName" value="${wimAdjustpvouchItem.newInvShelf.name }" size="20" /><input class="btn" type="button" value="选择" onclick="chooseShelf();" /><span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 120px; padding-top: 15px;">
						<s:if test="saleOrderItem.id > 0">
							<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>修改</span></span>
						</s:if>
						<s:else>
							<span class="abtn" style="cursor: pointer;" onclick="addSalesOrderItem();"><span>保存</span></span>
						</s:else>
						<span class="abtn" style="cursor: pointer;" onclick="clearOrderItemContent();"><span>清空</span></span>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>