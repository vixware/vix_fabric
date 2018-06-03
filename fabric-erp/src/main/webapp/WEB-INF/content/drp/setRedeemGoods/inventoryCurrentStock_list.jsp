<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, code, name, id) {
		if (chk.checked) {
			$.returnValue = code + "," + name + "," + id;
		}
	}
	pager("start", "${vix}/drp/setRedeemGoodsAction!goInventoryCurrentStockList.action?temp=1", "inventoryCurrentStock");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/drp/setRedeemGoodsAction!goInventoryCurrentStockList.action?orderField=" + orderField + "&orderBy=" + orderBy, "inventoryCurrentStock");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/drp/setRedeemGoodsAction!goInventoryCurrentStockList.action", 'inventoryCurrentStock');
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
		pager("start", "${vix}/drp/setRedeemGoodsAction!goInventoryCurrentStockList.action?searchContent=" + code, 'inventoryCurrentStock');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	//状态
	function srmStatus(status) {
		pager("start", "${vix}/drp/setRedeemGoodsAction!goInventoryCurrentStockList.action?status=" + status, 'inventoryCurrentStock');
	}
	//最近使用
	function srmRecent(rencentDate) {
		pager("start", "${vix}/drp/setRedeemGoodsAction!goInventoryCurrentStockList.action?updateTime=" + rencentDate, 'inventoryCurrentStock');
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
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name='srm_supplier_code' />:<input id="srm_code" name="" type="text" class="int" /> </label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>"
				onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div id="inventoryCurrentStock"></div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>