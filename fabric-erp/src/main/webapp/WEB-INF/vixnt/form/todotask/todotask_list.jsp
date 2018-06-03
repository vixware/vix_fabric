<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-file-text-o "></i> 表单管理<span>&gt; 我的审批</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>待审批列表</h2>
			<ul class="nav nav-tabs pull-right in">
				<li class="active"><a data-toggle="tab" href="#hr1"><span class="hidden-mobile hidden-tablet">单据表单</span></a></li>
				<li><a data-toggle="tab" href="#hr2"><span class="hidden-mobile hidden-tablet">自定义表单</span></a></li>
			</ul>
		</header>
		<div class="tab-content">
			<div class="tab-pane active" id="hr1">
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								主题: <input type="text" value="" class="form-control" id="searchName">
							</div>
							<button onclick="jobTodoTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchName').val('');jobTodoTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="jobTodoTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
			<div class="tab-pane" id="hr2">
				<div class="widget-body no-padding">
					<div>
						<iframe id="businessFormTemplateIframe" src="${flow_ip}/activiti/task/undo_list" style="width: 100%; height: 550px; border-style: none; margin: 0px; padding: 0px;"> </iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var jobTodoColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "名称",
	"name" : "jobName",
	"width" : "40%",
	"data" : function(data) {
		return data.jobName;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		if (data.id) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goAudit('" + data.id + "');\" title='审批'><span class='txt-color-blue pull-right'><i class='fa icon-iconfont-employee'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update;
		}
		return "&nbsp;";
	}
	} ];

	var jobTodoTable = initDataTable("jobTodoTableId", "${nvix}/nvixnt/vreport/nvixJobTodoAction!goSingleList.action", jobTodoColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	});
	function goAudit(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vreport/nvixJobTodoAction!goAudit.action?id=' + id, "jobTodoForm", "审批", 750, 350, jobTodoTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vreport/nvixJobTodoAction!deleteById.action?id=' + id, '是否删除任务?', jobTodoTable);
	};
	$("#jobTodoForm").validationEngine();
	function loadBillPage() {
		var url = "";
		url = "${vix}" + $("#jobTodoUrl").val() + '?id=' + $("#billId").val() + "&taskId=" + $("#taskId").val();
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			$("#billId").after(html);
		}
		});
	}
</script>