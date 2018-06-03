<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>

<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-group"></i> 办公 <span>&gt; 会议室管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a href="#" class="btn btn-primary" onclick="saveMeetingRD();">新增会议室</a>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						会议室管理 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/conferenceManagementAction!goList.action');">会议室视图</a></li>
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/conferenceManagementAction!goApplyApplicationMg.action');">会议管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="jarviswidget jarviswidget-sortable">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>会议室列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<div id="meetingRequestSearchForm" class="input-group input-group-lg">
						<form class="navbar-form navbar-left" role="search">
							会议室名称：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="会议室名称" id="searchCode">
							</div>
							会议室编码：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="会议室编码" id="searchCodeA">
							</div>
							管理员：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="管理员" id="searchCodeB">
							</div>
							<button onclick="meetingRequestTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchCode').val('');$('#searchCodeA').val('');$('#searchCodeB').val('');$('#searchCodeC').val('');meetingRequestTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
				</div>
				<table id="meetingRequest" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>会议室名称</th>
							<th>会议室编码</th>
							<th>管理员</th>
							<th>可容纳人数</th>
							<th>会议室描述</th>
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
	//新增会议室基础数据
	function saveMeetingRD(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/conferenceManagementAction!goSaveMeetingRD.action?id=' + id, "meetingRequestForm", "新增会议室", 800, 650, meetingRequestTable);
	};

	//修改会议室状态
	function saveState(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/conferenceManagementAction!goSaveState.action?id=' + id, "meetingRequestForm", "修改会议室状态", 800, 670, meetingRequestTable);
	};

	//删除
	function deletessById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/conferenceManagementAction!deletessById.action?id=' + id, '是否删除会议室基础数据?', meetingRequestTable);
	};

	/* 创建会议室 */
	var meetingRequestColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "meetingName",
	"data" : function(data) {
		return data.meetingName;
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"name" : "capacity",
	"data" : function(data) {
		return data.capacity;
	}
	}, {
	"name" : "description",
	"data" : function(data) {
		return data.description;
	}
	}, {
	"name" : "meetingRoomStatus",
	"data" : function(data) {
		if (data.meetingRoomStatus == 0) {
			return '已启用';
		} else if (data.meetingRoomStatus == 1) {
			return '禁用';
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveMeetingRD('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var updates = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveState('" + data.id + "');\" title='状态'><span class='txt-color-blue pull-right'><i class='fa fa-dot-circle-o'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletessById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + updates + "&nbsp;&nbsp;" + del;
	}
	} ];

	var meetingRequestTable = initDataTable("meetingRequest", "${nvix}/nvixnt/conferenceManagementAction!goMeetingRequest.action", meetingRequestColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		searchCode = Url.encode(searchCode);
		var searchCodeA = $("#searchCodeA").val();
		searchCodeA = Url.encode(searchCodeA);
		var searchCodeB = $("#searchCodeB").val();
		searchCodeB = Url.encode(searchCodeB);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchCode" : searchCode,
		"searchCodeA" : searchCodeA,
		"searchCodeB" : searchCodeB
		};
	});
</script>