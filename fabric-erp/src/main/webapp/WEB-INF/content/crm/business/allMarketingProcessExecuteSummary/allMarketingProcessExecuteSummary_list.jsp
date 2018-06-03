<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-globe"></i> 会员营销 <span>>智能营销</span><span>>营销流程执行汇总</span>
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>营销流程列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div class="tab-content">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							姓名: <input type="text" value="" class="form-control" id="vixTaskName">
						</div>
						<button onclick="messageTemplateTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#vixTaskName').val('');messageTemplateTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
					<table id="messageTemplateTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var messageTemplateColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "活动主题",
	"name" : "title",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "开始时间",
	"name" : "title",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "结束时间",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "状态",
	"name" : "title",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var messageTemplateTable = initDataTable("messageTemplateTableId", "${nvix}/nvixnt/vixntMarketingProcessManagementAction!goListContent.action", messageTemplateColumns, function(page, pageSize, orderField, orderBy) {
		var messageName = $("#vixTaskName").val();
		messageName = Url.encode(messageName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"messageName" : messageName
		};
	});
</script>