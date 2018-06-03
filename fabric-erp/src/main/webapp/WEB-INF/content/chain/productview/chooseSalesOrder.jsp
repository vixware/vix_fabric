<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue = id;
		}
	}
	pager("start", "${vix}/chain/productViewAction!goSalesOrderList.action?temp=1", "salesOrder");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/template/simpleGridAction!goSalesOrderList.action?orderField=" + orderField + "&orderBy=" + orderBy, "salesOrder");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/template/simpleGridAction!goSalesOrderList.action", 'salesOrder');
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
		pager("start", "${vix}/chain/productViewAction!goSalesOrderList.action?searchContent=" + code, 'salesOrder');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	//状态
	function srmStatus(status) {
		pager("start", "${vix}/chain/productViewAction!goSalesOrderList.action?status=" + status, 'salesOrder');
	}
	//最近使用
	function srmRecent(rencentDate) {
		pager("start", "${vix}/chain/productViewAction!goSalesOrderList.action?updateTime=" + rencentDate, 'salesOrder');
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
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
			<label><s:text name='srm_supplier_code' />:<input id="srm_code" name="" type="text" class="int" /> </label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>"
				onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div id="salesOrder"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>