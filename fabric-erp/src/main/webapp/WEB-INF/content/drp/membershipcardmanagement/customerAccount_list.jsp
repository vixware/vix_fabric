<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, id, name, code) {
		if (chk.checked) {
			$.returnValue = id + "," + name + "," + code;
		}
	}
	pager("start", "${vix}/drp/membershipCardmanagementAction!goCustomerAccountList.action?1=1", "customerAccount");
	var code = "";
	function loadCode() {
		code = $('#srm_code').val();
		code = Url.encode(code);
		code = Url.encode(code);
	}
	/*搜索*/
	function searchForContent() {
		loadCode();
		pager("start", "${vix}/drp/membershipCardmanagementAction!goCustomerAccountList.action?searchContent=" + code, 'customerAccount');
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
			<label>姓名:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div id="customerAccount"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>