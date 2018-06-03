<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/plugin/highcharts/highcharts.js"></script>
<script src="${vix}/plugin/highcharts/modules/exporting.js"></script>
<script type="text/javascript">
	$ ('#box_news').highcharts ({
	chart : {} ,
	title : {
		text : 'Logarithmic axis demo'
	} ,
	xAxis : {
		tickInterval : 1
	} ,
	yAxis : {
	type : 'logarithmic' ,
	minorTickInterval : 0.1
	} ,
	tooltip : {
	headerFormat : '<b>{series.name}</b><br />' ,
	pointFormat : 'x = {point.x}, y = {point.y}'
	} ,
	series : [
		{
		data : [
		1 , 2 , 4 , 8 , 16 , 32 , 64 , 128 , 256
		]
		}
	]
	});
</script>

<input type="hidden" id="newsListTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="newsListPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="newsListTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="newsListOrderField" value="${pager.orderField}" />
<input type="hidden" id="newsListOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="newsListInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="c_box">
	<div id="box_news" style="min-width: 300px; height: 230px; margin: 0 auto"></div>
</div>
