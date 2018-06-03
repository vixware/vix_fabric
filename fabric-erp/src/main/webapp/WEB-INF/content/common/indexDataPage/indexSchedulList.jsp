<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	$(document).ready(function() {
		pager("start", "${vix}/common/vixIndexDataAction!goIndexSchedulList.action?pageNo=${pageNo}", 'schedulList');
	});
	function currentPager(tag) {
		pager(tag, "${vix}/common/vixIndexDataAction!goIndexSchedulList.action?1=1", 'schedulList');
	}
	function saveOrUpdateSchedul(id) {
		$.ajax({
		url : '${vix}/common/canlendarAction!showCanlendar.action?id=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 850,
			height : 400,
			title : "日程",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" onclick="loadContent('${vix}/common/canlendarAction!goCanlendar.action');">[更多]</a></i> <strong>日程安排</strong>
</div>
<div class="c_box">
	<div id="schedulList" class="box_table"></div>
	<div class="pagelist">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="schedulListInfo"></b>到 <b class="schedulListTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>