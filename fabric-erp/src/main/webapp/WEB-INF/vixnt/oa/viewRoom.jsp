<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="viewRoomForm" class="form-horizontal" style="padding: 20px 40px;">
	<input type="hidden" id="id" name="meetingRequest.id" value="${meetingRequest.id}" /> <input type="hidden" id="id" name="applicationMg.id" value="${applicationMg.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">会议室:</label>
			<div class="col-md-4">
				<input id="meetingName" name="meetingRequest.meetingName" disabled="disabled" value="${meetingRequest.meetingName}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">可容纳人数:</label>
			<div class="col-md-4">
				<input id="capacity" name="meetingRequest.capacity" disabled="disabled" value="${meetingRequest.capacity}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">管理员:</label>
			<div class="col-md-10">
				<input id="creator" name="meetingRequest.creator" disabled="disabled" placeholder="如没有显示管理员，此会议室无管理员！" value="${meetingRequest.creator}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">会议室描述:</label>
			<div class="col-md-10">
				<input id="description" name="meetingRequest.description" disabled="disabled" placeholder="如没有显示描述，此会议室无情况描述！" value="${meetingRequest.description}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">设备情况:</label>
			<div class="col-md-10">
				<input id="equipment" name="meetingRequest.equipment" disabled="disabled" placeholder="如没有显示设备情况，此会议室无设备！" value="${meetingRequest.equipment}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="meetingRequest.memo" disabled="disabled" placeholder="如没有显示备注，此会议室无备注！" value="${meetingRequest.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
	<div class="jarviswidget">
		<header>
			<h2>会议室申请情况</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<div class="form-group" style="margin: 0 0px;">
						<div class="col-md-3">
							<input type="text" value="" placeholder="会议主题" class="form-control" id="searchCode">
						</div>
						<button onclick="applicationMgTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchCode').val('');applicationMgTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</div>
				</div>
				<table id="applicationMg" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>会议室</th>
							<th>容纳人数</th>
							<th>会议主题</th>
							<th>申请人</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	/* 申请会议室 */
	var applicationMgColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "meetingRequest.meetingName",
	"data" : function(data) {
		return data.meetingRequest == null ? '' : data.meetingRequest.meetingName;
	}
	}, {
	"name" : "meetingRequest.capacity",
	"data" : function(data) {
		return data.meetingRequest == null ? '' : data.meetingRequest.capacity;
	}
	}, {
	"name" : "meetingTheme",
	"data" : function(data) {
		return data.meetingTheme;
	}
	}, {
	"name" : "uploadPersonName",
	"data" : function(data) {
		return data.uploadPersonName;
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
			return '进行中';
		} else if (data.bookingSituation == 1) {
			return '结束';
		}
	}
	}, ];

	var applicationMgTable = initDataTable("applicationMg", "${nvix}/nvixnt/conferenceManagementAction!goApplyRooms.action", applicationMgColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchCode" : searchCode
		};
	});
</script>
