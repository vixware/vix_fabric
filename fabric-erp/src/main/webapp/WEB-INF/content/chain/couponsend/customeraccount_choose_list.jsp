<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue += ',' + id;
		}
	}
	pager("start", "${vix}/chain/couponSendAction!goCustomerAccountList.action?couponId=" + $('#couponId').val(), "customerAccount");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/chain/couponSendAction!goCustomerAccountList.action?orderField=" + orderField + "&orderBy=" + orderBy, "customerAccount");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/chain/couponSendAction!goCustomerAccountList.action", 'customerAccount');
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
		pager("start", "${vix}/chain/couponSendAction!goCustomerAccountList.action?searchContent=" + code, 'customerAccount');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	//状态
	function srmStatus(status) {
		pager("start", "${vix}/chain/couponSendAction!goCustomerAccountList.action?status=" + status, 'customerAccount');
	}
	//最近使用
	function srmRecent(rencentDate) {
		pager("start", "${vix}/chain/couponSendAction!goCustomerAccountList.action?updateTime=" + rencentDate, 'customerAccount');
	}
</script>
<input type="hidden" id="couponId" name="couponId" value="${couponId}" />
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>编码:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div id="customerAccount" class="table"></div>
				<div class="pagelist drop">
					<strong><s:text name="cmn_selected" />:<span id="selectCount">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="customerAccountInfo"></b> <s:text name='cmn_to' /> <b class="customerAccountTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>