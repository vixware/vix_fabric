<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-group"></i> 会议管理
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						会议管理 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/conferenceManagementAction!goList.action');">会议室视图</a></li>
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/conferenceManagementAction!goConferenceRoomManagement.action');">会议室管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>会议列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form class="navbar-form navbar-left" role="search">
						会议主题：
						<div class="form-group">
							<input class="form-control" type="text" value="" placeholder="会议主题" id="searchCode">
						</div>
						<button onclick="applicationMgTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchCode').val('');applicationMgTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="applicationMg" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>会议室</th>
							<th>主题</th>
							<th>参与人</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	/* 申请会议室 */
	var applicationMgColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.meetingRequest == null ? '' : data.meetingRequest.meetingName;
	}
	}, {
	"name" : "meetingTheme",
	"data" : function(data) {
		return data.meetingTheme;
	}
	}, {
	"name" : "participants",
	"data" : function(data) {
		return data.participants;
	}
	}, {
	"name" : "meetingStartTime",
	"data" : function(data) {
		return data.meetingStartTimeStr;
	}
	}, {
	"name" : "meetingEndTime",
	"data" : function(data) {
		return data.meetingEndTimeStr;
	}
	}, {
	"name" : "bookingSituation",
	"data" : function(data) {
		if (data.bookingSituation == 0) {
			return '已申请';
		} else if (data.bookingSituation == 1) {
			return '结束';
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var updatemeetingsummary = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdateMeetingSummary('" + data.id + "');\" title='新增会议纪要'><span class='txt-color-blue pull-right'><i class='fa fa-edit'></i></span></a>";
		var affiliatedApplicationMg = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goAffiliatedApplicationMg('" + data.id + "');\" title='查看参会人'><span class='txt-color-blue pull-right'><i class='fa fa-edit'></i></span></a>";
		var leaveApplicationMg = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goLeaveApplicationMg('" + data.id + "');\" title='查看请假人'><span class='txt-color-blue pull-right'><i class='fa fa-edit'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletesById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + updatemeetingsummary + "&nbsp;&nbsp;" + affiliatedApplicationMg + "&nbsp;&nbsp;" + leaveApplicationMg + "&nbsp;&nbsp;" + del;
	}
	} ];

	var applicationMgTable = initDataTable("applicationMg", "${nvix}/nvixnt/conferenceManagementAction!goApplicationMg.action", applicationMgColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		searchCode = Url.encode(searchCode);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchCode" : searchCode
		};
	});
	function goAffiliatedApplicationMg(id) {
		$.ajax({
		url : '${nvix}/nvixnt/conferenceManagementAction!goAffiliatedApplicationMg.action?d=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goLeaveApplicationMg(id) {
		$.ajax({
		url : '${nvix}/nvixnt/conferenceManagementAction!goLeaveApplicationMg.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//新增申请会议室
	function saveOrUpdate(id, syncTag) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/conferenceManagementAction!goSaveOrUpdate.action?id=' + id + "&applicationMgType=" + syncTag, "applicationMgForm", "申请会议室", 750, 400, applicationMgTable, function() {
			var meetingStartTime = $("#meetingStartTime").val();
			var meetingEndTime = $("#meetingEndTime").val();
			//不带时分秒
			//var tag = dateTimeRange(meetingStartTime + " 00:00:01",meetingEndTime + " 23:59:59");
			//带时分秒
			var tag = dateTimeRange(meetingStartTime, meetingEndTime);
			if (!tag) {
				layer.alert("结束时间不能小于开始时间!");
			}
			return tag;
		});
	};
	//新增会议纪要
	function saveOrUpdateMeetingSummary(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/conferenceManagementAction!goSaveOrUpdateMeetingSummary.action?id=' + id, "applicationMgForm", "新增会议纪要", 750, 500, applicationMgTable);
	};
	//删除
	function deletesById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/conferenceManagementAction!deletesById.action?id=' + id, '是否删除申请会议室?', applicationMgTable);
	}
</script>