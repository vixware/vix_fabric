<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="">任务与计划</a> <span>> 任务统计 </span>
			</h1>
		</div>
	</div>
	<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>任务统计</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div style="padding-bottom: 60px;">
								<form role="search" class="navbar-form navbar-left">
									<input type="hidden" id="syncTag" value="${syncTag}" />
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="empName">
									</div>
									<button onclick="taskStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#empName').val('');taskStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
									<button onclick="exportExcel();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-export"></i>导出
									</button>
								</form>
							</div>
							<div class="jarviswidget" id="customerAccountTabs" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
								<header>
									<ul class="nav nav-tabs pull-left in">
										<li <s:if test="syncTag == 'week'">class="active"</s:if>>
											<a data-toggle="tab" href="#taskStatisticsDiv" onclick="$('#syncTag').val('week');taskStatisticsTable.ajax.reload();"> 
												<i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">周统计</span>
											</a>
										</li>
										<li <s:if test="syncTag == 'month'">class="active"</s:if>>
											<a data-toggle="tab" href="#taskStatisticsDiv" onclick="$('#syncTag').val('month');taskStatisticsTable.ajax.reload();"> 
												<i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">月统计</span>
											</a>
										</li>
										<li <s:if test="syncTag == 'reason'">class="active"</s:if>>
											<a data-toggle="tab" href="#taskStatisticsDiv" onclick="$('#syncTag').val('reason');taskStatisticsTable.ajax.reload();"> 
												<i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">季统计</span>
											</a>
										</li>
										<li <s:if test="syncTag == 'year'">class="active"</s:if>>
											<a data-toggle="tab" href="#taskStatisticsDiv" onclick="$('#syncTag').val('year');taskStatisticsTable.ajax.reload();"> 
												<i class="fa fa-list-alt"></i> <span class="hidden-mobile hidden-tablet">年统计</span>
											</a>
										</li>
									</ul>
								</header>
								<div class="widget-body" style="padding: 0;">
									<div class="tab-content">
										<div class="tab-pane no-padding active" id="taskStatisticsDiv">
											<table id="taskStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>项目任务统计</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div style="padding-bottom: 60px;">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										项目名称: <input type="text" value="" class="form-control" id="projectName">
									</div>
									<button onclick="projectTaskStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#projectName').val('');projectTaskStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
									<button onclick="exportExcel('projectTask');" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-export"></i>导出
									</button>
								</form>
							</div>
							<div class="widget-body" style="padding: 0;">
								<div class="tab-content">
									<div class="tab-pane no-padding active" id="">
										<table id="projectTaskStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>部门任务统计</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div style="padding-bottom: 60px;">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										部门名称: <input type="text" value="" class="form-control" id="orgName">
									</div>
									<button onclick="orgTaskStatisticsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#orgName').val('');orgTaskStatisticsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
									<button onclick="exportExcel('orgTask');" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-export"></i>导出
									</button>
								</form>
							</div>
							<div class="widget-body" style="padding: 0;">
								<div class="tab-content">
									<div class="tab-pane no-padding active" id="">
										<table id="orgTaskStatisticsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="row">
							<div class="col-sm-6 col-md-6" >
								<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
										<h2>任务完成排名</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="completeTask" style="height: 300px"></div>
											<script type="text/javascript">
												var myChart1 = echarts.init(document.getElementById('completeTask'));
				
												var option1 = {
												tooltip : {
													show : true
												},
												legend : {
													data : [ '任务完成数量' ]
												},
												xAxis : [
										            {
										                type : 'value'
										            }
										        ],
										        yAxis : [
										            {
										                type : 'category',
										                data : [${completeTaskName}]
										            }
										        ],
										        grid: {
										        	 // 控制图的大小，调整下面这些值就可以，
										             x: 100,
										             x2: 25,
										            y2: 25// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
										         },
												color : ['#d2691e'],
												series : [ {
												"name" : "任务完成数量",
												"type" : "bar",
												"data" : [ ${completeTaskValue} ]
												} ]
												};
												// 为echarts对象加载数据 
												myChart1.setOption(option1);
											</script>
										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-6 col-md-6" >
								<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
									<header>
										<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
										<h2>任务超时排名</h2>
									</header>
									<div>
										<div class="widget-body no-padding">
											<div id="timeOutTask" style="height: 300px"></div>
											<script type="text/javascript">
												var myChart = echarts.init(document.getElementById('timeOutTask'));
				
												var option = {
												tooltip : {
													show : true
												},
												legend : {
													data : [ '任务超时数量' ]
												},
												xAxis : [
										            {
										                type : 'value'
										            }
										        ],
										        yAxis : [
										            {
										                type : 'category',
										                data : [${timeOutTaskName}]
										            }
										        ],
										        grid: {
										        	 // 控制图的大小，调整下面这些值就可以，
										             x: 100,
										             x2: 25,
										            y2: 25// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
										         },
												color : ['#d2691e'],
												series : [ {
												"name" : "任务超时数量",
												"type" : "bar",
												"data" : [ ${timeOutTaskValue} ]
												} ]
												};
												// 为echarts对象加载数据 
												myChart.setOption(option);
											</script>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	// 任务统计
	var taskStatisticsColumns = [ {
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "时间",
	"data" : function(data) {
		return data.datetemp;
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
	}, {
	"title" : "超时任务数",
	"data" : function(data) {
		return data.overtimeTaskNum;
	}
	} ];

	var taskStatisticsTable = initDataTable("taskStatisticsTableId", "${nvix}/nvixnt/wxpTaskStatisticsAction!goTaskStatisticsList.action", taskStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var empName = $("#empName").val();
		empName = Url.encode(empName);
		var syncTag = $("#syncTag").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"empName" : empName,
		"syncTag" : syncTag
		};
	}, 10, "1");
	// 项目任务统计
	var projectTaskStatisticsColumns = [ {
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "项目名称",
	"data" : function(data) {
		return data.projectName;
	}
	}, {
	"title" : "任务总数",
	"data" : function(data) {
		return data.taskNum;
	}
	}, {
	"title" : "未开始任务数",
	"data" : function(data) {
		return data.nostartTaskNum;
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
	}, {
	"title" : "超时任务数",
	"data" : function(data) {
		return data.timeoutTaskNum;
	}
	} ];
	var projectTaskStatisticsTable = initDataTable("projectTaskStatisticsTableId", "${nvix}/nvixnt/wxpTaskStatisticsAction!goProjectTaskStatisticsList.action", projectTaskStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var projectName = $("#projectName").val();
		projectName = Url.encode(projectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"projectName" : projectName
		};
	}, 10, "1");
	// 部门任务统计
	var orgTaskStatisticsColumns = [ {
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "部门编码",
	"data" : function(data) {
		return data.datetemp;
	}
	}, {
	"title" : "部门名称",
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
	}, {
	"title" : "超时任务数",
	"data" : function(data) {
		return data.overtimeTaskNum;
	}
	} ];
	var orgTaskStatisticsTable = initDataTable("orgTaskStatisticsTableId", "${nvix}/nvixnt/wxpTaskStatisticsAction!goOrgTaskStatisticsList.action", orgTaskStatisticsColumns, function(page, pageSize, orderField, orderBy) {
		var orgName = $("#orgName").val();
		orgName = Url.encode(orgName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"orgName" : orgName
		};
	}, 10, "1");
	
	function exportExcel(syncTag) {
		if(!syncTag){
			syncTag = $("#syncTag").val();
		}
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/wxpTaskStatisticsAction!exportTaskStatisticsExcel.action?syncTag="+syncTag;
		form.submit();
	};
</script>