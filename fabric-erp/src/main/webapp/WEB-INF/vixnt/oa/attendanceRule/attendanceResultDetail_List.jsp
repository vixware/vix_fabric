<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="">考勤管理</a> <span>> 报表管理 </span> <span>> 日考勤明细表 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="dealAttendance();">
						<i class="fa fa-th"></i>&nbsp;查询计算
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>日考勤明细表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="empName">
									</div>
									考勤日期:
									<div class="input-group">
										<input id="dayAndMonth" name="dayAndMonth" value="" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dayAndMonth'});"><i class="fa fa-calendar"></i></span>
									</div>
									<button onclick="dailyStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#empName').val('');$('#dayAndMonth').val('');dailyStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="dailyStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var dailyStatisticsColumns = [ {
	"title" : "员工编码",
	"data" : function(data) {
		return data.recordNum;
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.employeeName;
	}
	}, {
	"title" : "考勤日期",
	"data" : function(data) {
		return data.dayAndMonth;
	}
	}, {
	"title" : "上班时间",
	"data" : function(data) {
		return data.startWorkTime;
	}
	}, {
	"title" : "刷卡时间",
	"data" : function(data) {
		return data.startCardStr;
	}
	}, {
	"title" : "上班状态",
	"data" : function(data) {
		if (data.startWorkStatus == 0) {
			return "<span class='label label-primary'>正常</span>";
		} else if (data.startWorkStatus == 1) {
			return "<span class='label label-warning'>迟到</span>";
		} else if (data.startWorkStatus == 2) {
			return "<span class='label label-danger'>未刷</span>";
		} else if (data.startWorkStatus == 3) {
			return "<span class='label label-default'>刷卡超时</span>";
		} else if (data.startWorkStatus == 4) {
			return "<span class='label label-success'>加班</span>";
		}
		return "";
	}
	}, {
	"title" : "下班时间",
	"data" : function(data) {
		return data.endWorkTime;
	}
	}, {
	"title" : "刷卡时间",
	"data" : function(data) {
		return data.endCardStr;
	}
	}, {
	"title" : "下班状态",
	"data" : function(data) {
		if (data.endWorkStatus == 0) {
			return "<span class='label label-primary'>正常</span>";
		} else if (data.endWorkStatus == 1) {
			return "<span class='label label-warning'>早退</span>";
		} else if (data.endWorkStatus == 2) {
			return "<span class='label label-danger'>未刷</span>";
		} else if (data.endWorkStatus == 3) {
			return "<span class='label label-default'>刷卡超时</span>";
		} else if (data.endWorkStatus == 4) {
			return "<span class='label label-success'>加班</span>";
		}
		return "";
	}
	}, {
	"title" : "工作时间",
	"data" : function(data) {
		return data.accumulativeWorkHours + "分钟";
	}
	}, {
	"title" : "考勤状态",
	"data" : function(data) {
		if (data.attendanceStatus == 0) {
			return "<span class='label label-primary'>正常</span>";
		} else if (data.attendanceStatus == 1) {
			return "<span class='label label-danger'>缺勤</span>";
		} else if (data.attendanceStatus == 2) {
			return "<span class='label label-danger'>请假</span>";
		} else if (data.attendanceStatus == 3) {
			return "<span class='label label-danger'>其他</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"data" : function(data) {
		return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
	}
	} ];

	var dailyStatisticsTable = initDataTable("dailyStatisticsTableId", "${nvix}/nvixnt/attendanceResultDetailAction!goDailyStatisticsList.action", dailyStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var dayAndMonth = $("#dayAndMonth").val();
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"dayAndMonth" : dayAndMonth,
		"empName" : empName
		};
	}, 10, "1", "0");

	function dealAttendance() {
		$.ajax({
		url : '${nvix}/nvixnt/attendanceResultDetailAction!dealAttendance.action',
		cache : false,
		success : function() {
			dailyStatisticsTable.ajax.reload();
		}
		});
	}
</script>