<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	pager("start", "${vix}/common/vixIndexDataAction!goIndexOperHisList.action?1=1", 'operHisList');

	function currentOperHisPager(tag) {
		pager(tag, "${vix}/common/vixIndexDataAction!goIndexOperHisList.action?1=1", 'operHisList');
	}
</script>


<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" onclick="loadContent('${vix}/common/vixLogAction!goList.action');">[更多]</a></i> <strong>操作历史</strong>
</div>
<div class="c_box">
	<div id="operHisList" class="box_table"></div>
	<div class="pagelist">
		<div>
			<span><a class="start" onclick="currentOperHisPager('start');"></a></span> <span><a class="previous" onclick="currentOperHisPager('previous');"></a></span> <em>(<b class="operHisListInfo"></b>到 <b class="operHisListTotalCount"></b>)
			</em> <span><a class="next" onclick="currentOperHisPager('next');"></a></span> <span><a class="end" onclick="currentOperHisPager('end');"></a></span>
		</div>
	</div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>

