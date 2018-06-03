<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	$(document).ready(function() {
		pager("start", "${vix}/common/vixIndexDataAction!goRemindList.action?1=1", 'reminds');
	});
	function currentToDoPager(tag) {
		pager(tag, "${vix}/common/vixIndexDataAction!goRemindList.action?1=1", 'reminds');
	}
</script>


<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" onclick="loadContent('${vix}/system/remindsCenterAction!goList.action');">[更多]</a></i> <strong>提醒数据</strong>
</div>
<div class="c_box">
	<div id="reminds" class="box_table"></div>
	<div class="pagelist">
		<div>
			<span><a class="start" onclick="currentToDoPager('start');"></a></span> <span><a class="previous" onclick="currentToDoPager('previous');"></a></span> <em>(<b class="remindsInfo"></b>到 <b class="remindsTotalCount"></b>)
			</em> <span><a class="next" onclick="currentToDoPager('next');"></a></span> <span><a class="end" onclick="currentToDoPager('end');"></a></span>
		</div>
	</div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>

