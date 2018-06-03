<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	$(document).ready(function() {
		pager("start", "${vix}/common/vixIndexDataAction!goIndexBulletinList.action?1=1", 'bulletinList');
	});
	function currentBulletinPager(tag) {
		pager(tag, "${vix}/common/vixIndexDataAction!goIndexBulletinList.action?1=1", 'bulletinList');
	}
	function goAnnouncementNotification(id) {
		asyncbox.open({
		modal : true,
		width : 780,
		height : 460,
		title : '通知公告',
		url : "${vix}/common/vixIndexDataAction!goAnnouncementNotification.action?id=" + id,
		buttons : [ {
		value : '确定',
		result : 'ok'
		}, {
		value : '已读',
		result : false
		} ]
		});
	};
</script>
<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" onclick="loadContent('${vix}/common/vixIndexDataAction!goBulletInboardNotices.action');">[更多]</a></i> <strong>通知公告</strong>
</div>
<div class="c_box">
	<div id="bulletinList" class="box_table"></div>
	<div class="pagelist">
		<div>
			<span><a class="start" onclick="currentBulletinPager('start');"></a></span> <span><a class="previous" onclick="currentBulletinPager('previous');"></a></span> <em>(<b class="bulletinListInfo"></b>到 <b class="bulletinListTotalCount"></b>)
			</em> <span><a class="next" onclick="currentBulletinPager('next');"></a></span> <span><a class="end" onclick="currentBulletinPager('end');"></a></span>
		</div>
	</div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>