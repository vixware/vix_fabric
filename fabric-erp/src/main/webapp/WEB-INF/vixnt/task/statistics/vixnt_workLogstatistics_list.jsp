<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="">办公</a> <span>> 日报统计 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="dealAttendance();">
						<i class="glyphicon glyphicon-list"></i>&nbsp;统计
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>日报统计</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							姓名: <input type="text" value="" class="form-control" id="empName">
						</div>
						<button onclick="taskStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#empName').val('');taskStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="taskStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var taskStatisticsColumns = [ {
	"width" : "10%",
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.empName;
	}
	}, {
	"title" : "日期",
	"data" : function(data) {
		return data.datetemp;
	}
	}, {
	"title" : "星期",
	"data" : function(data) {
		return data.weekDate;
	}
	}, {
	"width" : "10%",
	"title" : "是否编写日报",
	"data" : function(data) {
		if (data.isCreate == 0) {
			return "<span class='label label-danger'>未编写</span>";
		} else if (data.isCreate == 1) {
			return "<span class='label label-primary'>已编写</span>";
		}
		return "";
	}
	} ];

	var taskStatisticsTable = initDataTable("taskStatisticsTableId", "${nvix}/nvixnt/wxpWorkLogStatisticsAction!goWorkLogStatisticsList.action", taskStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"empName" : empName
		};
	}, 10, "1");

	function dealAttendance() {
		$.ajax({
		url : '${nvix}/nvixnt/wxpWorkLogStatisticsAction!dealWorkLogStatistics.action',
		cache : false,
		success : function() {
			taskStatisticsTable.ajax.reload();
		}
		});
	};
</script>