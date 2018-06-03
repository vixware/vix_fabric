<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
    function chkChange(chk,id,name,amount) {
	    if (chk.checked) {
		    $.returnValue = id + "," + name + "," + amount;
	    }
    }
    pager("start", "${vix}/drp/collectManagementAction!goSalesOrderList.action?temp=1", "salesOrder");
    //排序 
    function subOrderBy(orderField) {
	    var orderBy = $("#subBrandOrderBy").val();
	    if (orderBy == 'desc') {
		    orderBy = "asc";
	    } else {
		    orderBy = 'desc';
	    }
	    pager("start", "${vix}/drp/collectManagementAction!goSalesOrderList.action?orderField=" + orderField + "&orderBy=" + orderBy, "salesOrder");
    }
    function currentPager(tag) {
	    pager(tag, "${vix}/drp/collectManagementAction!goSalesOrderList.action", 'salesOrder');
    }
    $(".drop>ul>li").bind('mouseover', function() {
	    $(this).children('ul').delay(400).slideDown('fast');
    }).bind('mouseleave', function() {
	    $(this).children('ul').slideDown('fast').stop(true, true);
	    $(this).children('ul').slideUp('fast');
    });
    var code = "";
    function loadCode() {
	    code = $('#srm_code').val();
	    code = Url.encode(code);
	    code = Url.encode(code);
    }
    /*搜索*/
    function searchForContent(i) {
	    loadCode();
	    pager("start", "${vix}/drp/collectManagementAction!goSalesOrderList.action?searchContent=" + code, 'salesOrder');
    }
    /*重置搜索*/
    function resetForContent() {
	    $("#srm_code").val("");
    }
    //状态
    function srmStatus(status) {
	    pager("start", "${vix}/drp/collectManagementAction!goSalesOrderList.action?status=" + status, 'salesOrder');
    }
    //最近使用
    function srmRecent(rencentDate) {
	    pager("start", "${vix}/drp/collectManagementAction!goSalesOrderList.action?updateTime=" + rencentDate, 'salesOrder');
    }
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<ul>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="srmStatus('S1')"><img alt="" src="${vix}/common/img/icon_10.png">待确认</a></li>
					<li><a href="#" onclick="srmStatus('S2')"><img alt="" src="${vix}/common/img/icon_10.png">审批中</a></li>
					<li><a href="#" onclick="srmStatus('S3')"><img alt="" src="${vix}/common/img/icon_10.png">已发货</a></li>
					<li><a href="#" onclick="srmStatus('S4')"><img alt="" src="${vix}/common/img/icon_10.png">已完成</a></li>
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
			<div id="salesOrder"></div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>