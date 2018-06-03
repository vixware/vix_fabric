<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
    $(document).ready(function() {
	    pager("start", "${vix}/common/vixIndexDataAction!goIndexMsgList.action?pageNo=${pageNo}", 'msgList');
    });

    function currentPager(tag) {
	    pager(tag, "${vix}/common/vixIndexDataAction!goIndexMsgList.action?1=1", 'msgList');
    }
</script>


<div class="c_title">
	<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>消息</strong>
</div>
<div class="c_box">
	<div id="msgList" class="box_table"></div>
	<div class="pagelist">
		<div>
			<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="msgListInfo"></b>到 <b class="msgListTotalCount"></b>)
			</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
		</div>
	</div>
</div>
<div class="c_foot">
	<span class="left_bg"></span> <span class="right_bg"></span>
</div>

