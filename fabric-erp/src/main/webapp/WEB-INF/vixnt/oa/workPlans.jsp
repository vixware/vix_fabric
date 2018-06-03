<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 任务与计划 <span>&gt; 工作计划</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/workPlansAction!goList.action');">
					<i class="fa fa-rotate-left"></i>&nbsp;返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>下属计划列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form class="navbar-form navbar-left" role="search">
						计划标题：
						<div class="form-group">
							<input class="form-control" type="text" value="" placeholder="工作计划标题" id="title">
						</div>
						编写人：
						<div class="form-group">
							<input class="form-control" type="text" value="" placeholder="编写人" id="uploadPersonName">
						</div>
						<button onclick="workPlansTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#title').val('');$('#uploadPersonName').val('');workPlansTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="workPlans" class="table table-striped table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>计划标题</th>
							<th>编写人</th>
							<th>开始时间</th>
							<th>截止时间</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var workPlansColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "proposalTitle",
	"data" : function(data) {
		return data.proposalTitle;
	}
	}, {
	"name" : "uploadPersonName",
	"data" : function(data) {
		return data.uploadPersonName;
	}
	}, {
	"name" : "initiateTime",
	"data" : function(data) {
		return data.initiateTimeStr;
	}
	}, {
	"name" : "overTime",
	"data" : function(data) {
		return data.overTimeStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return "<a class='btn btn-xs btn-default' onclick=\"viewPlan('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	}
	} ];

	var workPlansTable = initDataTable("workPlans", "${nvix}/nvixnt/workPlansAction!goWorkPlans.action", workPlansColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		var uploadPersonName = $("#uploadPersonName").val();
		uploadPersonName = Url.encode(uploadPersonName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"uploadPersonName" : uploadPersonName
		};
	});

	//根据ID查看工作计划
	function viewPlan(id) {
		$.ajax({
		url : '${nvix}/nvixnt/workPlansAction!goViewPlan.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>