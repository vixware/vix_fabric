<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	pager("start", "${vix}/inventory/outBoundAction!goInventoryCurrentStockListContent.action?masterInventoryCurrentStockId=" + $('#masterInventoryCurrentStockId').val(), "masterInventoryCurrentStock");
	//选择采购订单翻页
	function currentOrderPager(tag) {
		pager(tag, "${vix}/inventory/outBoundAction!goInventoryCurrentStockListContent.action?1=1", 'masterInventoryCurrentStock');
	}
	function chkChange(chk, id, batchcode) {
		if (chk.checked) {
			$.returnValue = id + "," + batchcode;
		}
	}
</script>
<s:hidden id="masterInventoryCurrentStockId" name="masterInventoryCurrentStockId" value="%{masterInventoryCurrentStockId}" theme="simple" />
<div class="content" style="background: #DCE7F1">
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div id="masterInventoryCurrentStock"></div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>