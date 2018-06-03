<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orderDetailForm").validationEngine();
	var name = "";
	function loadName() {
		name = $('#nameS').val();
		name = Url.encode(name);
		name = Url.encode(name);
	}
	function chooseItemForOrder(id, code, name, price, skuCode) {
		$("#outerGoodsId").val(code);
		$("#title").val(name);
		$("#skuCode").val(skuCode);
		$("#price").val(price);
	}
	loadName();
	//载入分页列表数据
	pager("start", "${vix}/business/orderProcessAction!goOrderDetailList.action?1=1", 'orderDetail');
	//排序 
	function orderBy(orderField) {
		loadName();
		var orderBy = $("#orderOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/business/orderProcessAction!goOrderDetailList.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name, 'orderDetail');
	}

	function currentPager(tag) {
		loadName();
		pager(tag, "${vix}/business/orderProcessAction!goOrderDetailList.action?name=" + name + '&companyCode=' + $("#companyCode").val(), 'orderDetail');
	}
	function addOrderDetail() {
		if ($('#orderDetailForm').validationEngine('validate')) {
			$.post('${vix}/business/orderProcessAction!saveOrUpdateOrderDetail.action', {
			'id' : $("#orderId").val(),
			'orderDetail.id' : $("#orderDetailId").val(),
			'orderDetail.outerGoodsId' : $("#outerGoodsId").val(),
			'orderDetail.title' : $("#title").val(),
			'orderDetail.outerId' : $("#skuCode").val(),
			'orderDetail.price' : $("#price").val(),
			'orderDetail.num' : $("#num").val(),
			'orderDetail.payment' : $("#payment").val(),
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				$('#dlAddress').datagrid("reload");
				clearOrderItemContent();
			});
		} else {
			return false;
		}
	}
	function clearOrderItemContent() {
		$("#orderDetailId").val("");
		$("#outerGoodsId").val("");
		$("#title").val("");
		$("#skuCode").val("");
		$("#price").val("");
		$("#num").val("");
		$("#payment").val("");
	}
	function searchForContent() {
		loadName();
		pager("start", "${vix}/business/orderProcessAction!goOrderDetailList.action?name=" + name, 'orderDetail');
	}
	function reset() {
		$("#nameS").val("");
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
		<div id="left" style="width: 400px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="orderDetailInfo"></b> <s:text name='cmn_to' /> <b class="orderDetailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
				<div id="orderDetail" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPager('start','account');"></a></span> <span><a class="previous" onclick="currentPager('previous','account');"></a></span> <em>(<b class="orderDetailInfo"></b> <s:text name='cmn_to' /> <b class="orderDetailTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next','account');"></a></span> <span><a class="end" onclick="currentPager('end','account');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<form id="orderDetailForm">
					<s:hidden id="orderDetailId" name="orderDetailId" value="%{orderDetail.id}" theme="simple" />
					<div class="box order_table" style="line-height: normal;">
						<table>
							<tr height="40">
								<th>商品编码:&nbsp;</th>
								<td><input id="outerGoodsId" name="outerGoodsId" value="${orderDetail.outerGoodsId}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>商品名称:&nbsp;</th>
								<td><input id="title" name="title" value="${orderDetail.title}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>SKU编码:&nbsp;</th>
								<td><input id="skuCode" name="skuCode" value="${orderDetail.outerId}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>价格:&nbsp;</th>
								<td><input id="price" name="price" value="${orderDetail.price}" type="text" class="validate[required,custom[number]] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>数量:&nbsp;</th>
								<td><input id="num" name="num" value="${orderDetail.num}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
							<tr height="40">
								<th>实付金额:&nbsp;</th>
								<td><input id="payment" name="payment" value="${orderDetail.payment}" type="text" class="validate[required,custom[number]] text-input" /> <span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<span class="abtn" style="cursor: pointer;" onclick="addOrderDetail();"><span>保存</span></span> <span class="abtn" style="cursor: pointer;" onclick="clearOrderItemContent();"><span>清空</span></span>
					</div>
				</form>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
