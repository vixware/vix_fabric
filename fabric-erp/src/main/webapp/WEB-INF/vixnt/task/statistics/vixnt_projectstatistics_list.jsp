<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="">任务与计划</a> <span>> 任务统计 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>任务统计</h2>
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
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var taskStatisticsColumns = [ {
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
	"title" : "任务总数",
	"data" : function(data) {
		return data.taskNum;
	}
	}, {
	"title" : "未开始任务数",
	"data" : function(data) {
		return data.noStartTaskNum;
	}
	}, {
	"title" : "进行中任务数",
	"data" : function(data) {
		return data.progressTaskNum;
	}
	}, {
	"title" : "已完成任务数",
	"data" : function(data) {
		return data.finishTaskNum;
	}
	} ];

	var taskStatisticsTable = initDataTable("taskStatisticsTableId", "${nvix}/nvixnt/wxpTaskStatisticsAction!goTaskStatisticsList.action", taskStatisticsColumns, function(page, pageSize, orderField, orderBy) {
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
</script>