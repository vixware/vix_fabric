<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = '';
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue = id;
		}
	}
	pager("start", "${vix}/inventory/initInventoryAction!goItemList.action?1=1&objectExpandId=" + $("#objectExpandId").val(), 'invStockRecordLines');
	function currentPager(tag) {
		pager(tag, "${vix}/inventory/initInventoryAction!goItemList.action?1=1&objectExpandId=" + $("#objectExpandId").val(), 'invStockRecordLines');
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
		pager("start", "${vix}/inventory/initInventoryAction!goItemList.action?searchContent=" + code, 'invStockRecordLines');
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
			<label>名称:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div id="invStockRecordLines"></div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>