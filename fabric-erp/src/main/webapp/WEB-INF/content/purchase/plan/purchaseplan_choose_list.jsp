<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	pager("start", "${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanSingleList.action?1=1", "radio");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, "radio");
	}
	//选择采购订单翻页
	function currentOrderPager(tag) {
		pager(tag, "${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanSingleList.action?1=1", 'radio');
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
		pager("start", "${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanSingleList.action?searchContent=" + code, 'radio');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	//状态
	function srmStatus(status) {
		pager("start", "${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanSingleList.action?status=" + status, 'radio');
	}
	//最近使用
	function srmRecent(rencentDate) {
		pager("start", "${vix}/purchase/purchaseApproveAction!goChoosePurchasePlanSingleList.action?updateTime=" + rencentDate, 'radio');
	}
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue = $.returnValue + "," + id;
		}
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>单号:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div id="radio"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>