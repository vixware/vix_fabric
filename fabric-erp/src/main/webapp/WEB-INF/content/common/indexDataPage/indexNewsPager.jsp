<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
    $(document).ready(function() {
	    pager("start", "${vix}/common/vixIndexDataAction!goIndexTrendsPage.action?pageNo=${pageNo}", 'news1List');
    });
    function currentPager(tag) {
	    pager(tag, "${vix}/common/vixIndexDataAction!goIndexTrendsPage.action?1=1", 'news1List');
    }
</script>


<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>新闻</strong>
</div>
<div class="c_box">
	<div id="news1List" class="box_table"></div>
	<div class="pagelist">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="news1ListInfo"></b>到 <b class="news1ListTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>