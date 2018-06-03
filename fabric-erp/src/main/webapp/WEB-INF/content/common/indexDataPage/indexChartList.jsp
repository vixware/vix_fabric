<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	$(document).ready(function() {
		pager("start", "${vix}/common/vixIndexDataAction!goIndexChartList.action?pageNo=${pageNo}", 'chartList');
	});
</script>


<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#">更多</a></i> <strong>统计图表</strong>
</div>
<div class="c_box">
	<div id="chartList" class="box_table"></div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>

