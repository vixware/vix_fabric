<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	pager("start", "${vix}/purchase/purchaseOrderAction!goPurchaseInquireList.action?temp=1", "purchaseInquire");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/template/simpleGridAction!goPurchaseOrderList.action?orderField=" + orderField + "&orderBy=" + orderBy, "purchaseInquire");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/template/simpleGridAction!goPurchaseOrderList.action", 'purchaseInquire');
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
		pager("start", "${vix}/purchase/purchaseOrderAction!goPurchaseInquireList.action?searchContent=" + code, 'purchaseInquire');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue = id;
		}
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>名称:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" /></label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div id="purchaseInquire"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>