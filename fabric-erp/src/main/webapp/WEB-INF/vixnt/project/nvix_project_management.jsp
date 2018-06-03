<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i> 项目管理
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-gear"></i>&nbsp;项目视图<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');">跟踪视图</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>项目列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							项目名称: <input type="text" value="" class="form-control" id="projectName">
						</div>
						<button onclick="projectTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#projectName').val('');projectTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
					<table id="project" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function goSaveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdate.action?id='+id, "projectForm", "新增项目", 950, 600, projectTable);
	};

	var projectColumns = [ {
	"title" : "编号",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "项目名称",
	"width" : "20%",
	"data" : function(data) {
		return data.projectName;
	}
	}, {
	"title" : "进度",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		if(data.projectSchedule != null){
			return "<div data-progressbar-value='"+data.projectSchedule+"' class='progress progress-xs'><div class='progress-bar'></div></div>";
		}else{
			return "";
		}
	}
	}, {
	"title" : "负责人",
	"width" : "20%",
	"data" : function(data) {
		if(data.employeeNames != null){
			// return "<div class='project-members'>" + data.empliststr + "</div>";
			return data.employeeNames;
		}else{
			return "";
		}
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.status == 1) {
			return "<span class='label label-info'>未开始</span>";
		} else if (data.status == 2) {
			return "<span class='label label-primary'>进行中</span>";
		} else if (data.status == 3) {
			return "<span class='label label-success'>已完成</span>";
		} else if (data.status == 4) {
			return "<span class='label label-danger'>已逾期</span>";
		}
		return "";
	}
	}, {
	"title" : "计划开始时间",
	"width" : "15%",
	"data" : function(data) {
		return data.estimateStartTimeStr;
	}
	}, {
	"title" : "计划完成时间",
	"width" : "10%",
	"data" : function(data) {
		return data.estimateEndTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		if (data.status != null) {
			var gtt = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectTaskGtt('" + data.id + "');\" title='任务甘特图'><span class='txt-color-green pull-right'><i class='fa fa-picture-o'></i></span></a>";
			var detail = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goProjectDetail('" + data.id + "');\" title='项目视图'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var deltete =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + detail + "&nbsp;&nbsp;" + gtt + "&nbsp;&nbsp;" + deltete;
		}else{
			return "";
		}
	}
	} ];

	var projectTable = initDataTable("project", "${nvix}/nvixnt/nvixProjectAction!goSingleList.action", projectColumns, function(page, pageSize, orderField, orderBy) {
		var projectName = $("#projectName").val();
		projectName = Url.encode(projectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"projectName" : projectName
		};
	}, 10);

	function format(d) {
		return '<table cellpadding="5" cellspacing="0" border="0" class="table table-hover table-condensed">' + '<tr>' + '<td style="width:100px">项目名称:</td>' + '<td>' + d.projectName + '</td>' + '</tr>' + '<tr>' + '<td style="width:100px">项目描述:</td>' + '<td>' + d.note + '</td>' + '</tr>' + '<tr>' + '<td>计划完成时间:</td>' + '<td>' + moment(d.taskEndTime)
				.format('YYYY-MM-DD') + '</td>' + '</tr>' + '<tr>' + '<td>操作:</td>' + '<td><button class="btn btn-xs" onclick="goProjectDetail(\'' + d.id + '\');">详情查看</button> <button class="btn btn-xs btn-danger pull-right" style="margin-left:5px" onclick="deleteById(\'' + d.id + '\');">删除</button> <button class="btn btn-xs btn-success pull-right" onclick="goSaveOrUpdate(\'' + d.id + '\',\'修改项目\');">修改</button> </td>' + '</tr>' + '</table>';
	}

	$('#project tbody').on('click', 'td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = projectTable.row(tr);
		if (row.child.isShown()) {
			row.child.hide();
			tr.removeClass('shown');
		} else {
			if(row.data().id != null){
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		}
	});
	
	function goProjectDetail(id) {
		loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectDetail.action?id=' + id + "&source=project");
	};
	
	function goProjectTaskGtt(id) {
		loadContent('', '${nvix}/nvixnt/nvixProjectAction!goProjectTaskGtt.action?id=' +id);
	}
</script>