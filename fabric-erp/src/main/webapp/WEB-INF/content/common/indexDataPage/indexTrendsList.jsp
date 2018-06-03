<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	/* 载入内容区 */
	$(document).ready(function() {
		$.ajax({
		url : '${vix}/common/vixIndexDataAction!goIndexTrendsList.action',
		cache : false,
		success : function(html) {
			$("#indexTrendsList").html(html);
		}
		});
	});
</script>


<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" onclick="loadContent('${vix}/oa/trendsAction!goList.action');">[更多]</a></i> <strong>新闻</strong>
</div>
<div class="c_box">
	<div id="indexTrendsList"></div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>

