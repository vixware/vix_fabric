<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, id, name) {
		if (chk.checked) {
			$.returnValue = id + "," + name;
		}
	}
	pager("start", "${vix}/oa/vehicleApplicationsAction!goCarList.action?temp=1", "radio");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/template/simpleGridAction!goSubRadioSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, "radio");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/template/simpleGridAction!goSubRadioSingleList.action", 'radio');
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label><s:text name='车辆信息查看' /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div id="radio"></div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>