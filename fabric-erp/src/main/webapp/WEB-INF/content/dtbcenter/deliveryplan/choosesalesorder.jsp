<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = '';
	function chkChange(chk, id) {
		if (chk.checked) {
			$.returnValue = $.returnValue + "," + id;
		}
	}
	/* 	$.returnValue = "";
	 function chkChange (chk , id , name){
	 if ($ ("#chooseType").val () == 'multi'){
	 if (chk.checked){
	 $.returnValue = $.returnValue + "," + id;
	 }else{
	 $.returnValue = $.returnValue.replace ("," + id , "");
	 }
	 }else{
	 $.returnValue = id + "," + name;
	 }
	 } */
	pager("start", "${vix}/dtbcenter/distributionManagementAction!goSalesOrderList.action?temp=1", "salesOrder");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/dtbcenter/distributionManagementAction.action?orderField=" + orderField + "&orderBy=" + orderBy, "salesOrder");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/dtbcenter/distributionManagementAction.action", 'salesOrder');
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
		pager("start", "${vix}/dtbcenter/distributionManagementAction!goSalesOrderList.action?searchContent=" + code, 'salesOrder');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	//状态
	function srmStatus(status) {
		pager("start", "${vix}/dtbcenter/distributionManagementAction!goSalesOrderList.action?status=" + status, 'salesOrder');
	}
	//最近使用
	function srmRecent(rencentDate) {
		pager("start", "${vix}/dtbcenter/distributionManagementAction!goSalesOrderList.action?updateTime=" + rencentDate, 'salesOrder');
	}
</script>
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
				</ul></li>
		</ul>
		<div>
			<label>单号:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="salesOrderInfo"></b> <s:text name='cmn_to' /> <b class="salesOrderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="salesOrder" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="salesOrderInfo"></b> <s:text name='cmn_to' /> <b class="salesOrderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>