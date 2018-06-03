<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="applyRoomForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/conferenceManagementAction!saveOrUpdateApplyRoom.action">
	<input type="hidden" id="id" name="meetingRequest.id" value="${meetingRequest.id}" /> <input type="hidden" id="applicationMgId" name="applicationMg.id" value="${applicationMg.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="employeeIds" name="employeeIds" value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>会议标题:</label>
			<div class="col-md-4">
				<input id="meetingTheme" name="applicationMg.meetingTheme" placeholder="会议标题" value="${applicationMg.meetingTheme}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label">是否申请:</label>
			<div class="col-md-4">
				<s:if test="meetingRequest.bookingSituation == 0">
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="0" checked="checked"> 是
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="1"> 否
					</label>
				</s:if>
				<s:elseif test="applicationMg.bookingSituation == 1">
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="0"> 是
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="1" checked="checked"> 否
					</label>
				</s:elseif>
				<s:else>
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="0" checked="checked"> 是
					</label>
					<label class="radio radio-inline"> <input type="radio" id="bookingSituation" name="applicationMg.bookingSituation" value="1"> 否
					</label>
				</s:else>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
			<div class="col-md-4">
				<input id="meetingStartTime" name="applicationMg.meetingStartTime" placeholder="开始时间不能与已申请时间相同" value="<s:date name="%{applicationMg.meetingStartTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
			<div class="col-md-4">
				<input id="meetingEndTime" name="applicationMg.meetingEndTime" placeholder="结束时间不能与已申请时间相同" value="<s:date name="%{applicationMg.meetingEndTime}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 参与对象:</label>
			<div class="col-md-7">
				<input id="employeeNames" name="applicationMg.participants" placeholder="会议参与人！" value="${applicationMg.participants}" class="form-control validate[required]" type="text" />
			</div>
			<div class="col-md-3">
				<button onclick="chooseEmployee();" type="button" class="btn btn-info">
					<i class="glyphicon glyphicon-search"></i> 选择
				</button>
				<button onclick="$('#employeeNames').val('');" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 会议描述:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="meetingDescription" name="applicationMg.meetingDescription" placeholder="会议描述，方便参会人员了解会议情况！" value="${applicationMg.meetingDescription}">${applicationMg.meetingDescription}</textarea>
			</div>
		</div>
	</fieldset>

	<div class="jarviswidget jarviswidget-color-darken">
		<header>
			<h2>会议室申请情况</h2>
		</header>
		<div>
			<div class="jarviswidget-editbox"></div>
			<div class="widget-body no-padding">
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade active in" id="s1">
						<div class="table-responsive">
							<div style="margin: 5px;">
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
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	//表单校验
	$("#applyRoomForm").validationEngine();

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

	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	};
</script>