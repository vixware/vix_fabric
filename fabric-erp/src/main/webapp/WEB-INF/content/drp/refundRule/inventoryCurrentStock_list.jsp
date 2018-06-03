<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, code, name, id) {
		if (chk.checked) {
			$.returnValue = code + "," + name + "," + id;
		}
	}
	pager("start", "${vix}/drp/refundRuleAction!goInventoryCurrentStockList.action?temp=1", "inventoryCurrentStock");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/drp/refundRuleAction!goInventoryCurrentStockList.action?orderField=" + orderField + "&orderBy=" + orderBy, "inventoryCurrentStock");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/drp/refundRuleAction!goInventoryCurrentStockList.action", 'inventoryCurrentStock');
	}
	var code = "";
	function loadCode() {
		code = $('#srm_code').val();
		code = Url.encode(code);
		code = Url.encode(code);
	}
	/*搜索*/
	function searchForContent(i) {
		loadCode();
		pager("start", "${vix}/drp/refundRuleAction!goInventoryCurrentStockList.action?searchContent=" + code, 'inventoryCurrentStock');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>商品编码:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div id="inventoryCurrentStock"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>