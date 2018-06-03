<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
    $(document).ready(function() {
	    pager("start", "${vix}/common/vixIndexDataAction!goIndexJobtodoList.action?1=1", 'jobtodoList');
    });
    $(document).ready(function() {
	    pager("start", "${vix}/common/vixIndexDataAction!goApprovalPendingList.action?1=1", 'oajobtodoList');
    });
    $(document).ready(function() {
	    pager("start", "${vix}/common/vixIndexDataAction!goTaskList.action?1=1", 'erpjobtodoList');
    });
    $(document).ready(function() {
	    pager("start", "${vix}/common/vixIndexDataAction!goRemindList.action?1=1", 'hrjobtodoList');
    });
    function currentToDoPager(tag) {
	    pager(tag, "${vix}/common/vixIndexDataAction!goIndexJobtodoList.action?1=1", 'jobtodoList');
    }
    function saveOrUpdate(id) {
	    $.ajax({
	    url : '${vix}/system/accraditationAction!goSaveOrUpdate.action?1=1&id=' + id,
	    cache : false,
	    success : function(html) {
		    $("#mainContent").html(html);
	    }
	    });
    }
</script>
<div class="box_content">
	<div class="c_title clearfix">
		<span class="left_bg"></span> <span class="right_bg"></span> <i><a href="#" class="close">[关闭]</a></i> <strong>待办任务</strong>
	</div>
	<div class="right_menu new_c_title">
		<ul>
			<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img alt="" src="img/todo_all.png">全部待办</a></li>
			<li class=""><a onclick="javascript:tab(4,2,'a',event)"><img alt="" src="img/todo_list.png">待审批数据</a></li>
			<li class=""><a onclick="javascript:tab(4,3,'a',event)"><img alt="" src="img/task_list.png">任务数据</a></li>
			<li class=""><a onclick="javascript:tab(4,4,'a',event)"><img alt="" src="img/exclam_list.png">提醒数据</a></li>
		</ul>
	</div>
	<div id="a1" class="c_box">
		<div id="jobtodoList" class="box_table"></div>
		<div class="pagelist clearfix">
			<div>
				<span><a class="start" onclick="currentToDoPager('start');"></a></span> <span><a class="previous" onclick="currentToDoPager('previous');"></a></span> <em>(<b class="jobtodoListInfo"></b>到 <b class="jobtodoListTotalCount"></b>)
				</em> <span><a class="next" onclick="currentToDoPager('next');"></a></span> <span><a class="end" onclick="currentToDoPager('end');"></a></span>
			</div>
		</div>
	</div>
	<div id="a2" class="c_box" style="display: none;">
		<div id="oajobtodoList" class="box_table"></div>
		<div class="pagelist clearfix">
			<div>
				<span><a class="start" onclick="currentToDoPager('start');"></a></span> <span><a class="previous" onclick="currentToDoPager('previous');"></a></span> <em>(<b class="oajobtodoListInfo"></b>到 <b class="oajobtodoListTotalCount"></b>)
				</em> <span><a class="next" onclick="currentToDoPager('next');"></a></span> <span><a class="end" onclick="currentToDoPager('end');"></a></span>
			</div>
		</div>
	</div>
	<div id="a3" class="c_box" style="display: none;">
		<div id="erpjobtodoList" class="box_table"></div>
		<div class="pagelist clearfix">
			<div>
				<span><a class="start" onclick="currentToDoPager('start');"></a></span> <span><a class="previous" onclick="currentToDoPager('previous');"></a></span> <em>(<b class="erpjobtodoListInfo"></b>到 <b class="erpjobtodoListTotalCount"></b>)
				</em> <span><a class="next" onclick="currentToDoPager('next');"></a></span> <span><a class="end" onclick="currentToDoPager('end');"></a></span>
			</div>
		</div>
	</div>
	<div id="a4" class="c_box" style="display: none;">
		<div id="hrjobtodoList" class="box_table"></div>
		<div class="pagelist clearfix">
			<div>
				<span><a class="start" onclick="currentToDoPager('start');"></a></span> <span><a class="previous" onclick="currentToDoPager('previous');"></a></span> <em>(<b class="hrjobtodoListInfo"></b>到 <b class="hrjobtodoListTotalCount"></b>)
				</em> <span><a class="next" onclick="currentToDoPager('next');"></a></span> <span><a class="end" onclick="currentToDoPager('end');"></a></span>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>

