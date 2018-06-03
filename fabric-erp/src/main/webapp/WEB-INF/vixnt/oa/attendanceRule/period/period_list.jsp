<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row hidden-mobile">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i>工作时段设置
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button onclick="editPeriodRule('');" type="button" class="btn btn-primary">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;
						<s:text name="add" />
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" id="wid-id-0" data-widget-togglebutton="false" data-widget-editbutton="false" data-widget-fullscreenbutton="false" data-widget-colorbutton="false" data-widget-deletebutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>工作时段列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form class="navbar-form navbar-left" role="search">
									名称：
									<div class="form-group">
										<input class="form-control" type="text" value="" placeholder="名称" id="searchPeriodRule">
									</div>
									<button onclick="periodRuleTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchPeriodRule').val('');periodRuleTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="periodRule" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var periodRuleColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return '&nbsp;';
	}
	}, {
	"title" : "编码",
	"width" : "10%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "名称",
	"width" : "10%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "创建时间",
	"width" : "10%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"editPeriodRule('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletesById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var periodRuleTable = initDataTable("periodRule", "${nvix}/nvixnt/periodAction!getPeriodRulesJson.action", periodRuleColumns, function(page, pageSize, orderField, orderBy) {
		var searchPeriodRule = $("#searchPeriodRule").val();
		searchPeriodRule = Url.encode(searchPeriodRule);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchPeriodRule" : searchPeriodRule
		};
	});

	//新增
	function editPeriodRule(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/periodAction!goSaveOrUpdate.action?id=' + id, 'periodRuleForm', '工作时段设定', 955, 635, periodRuleTable, null, function() {
			periodRuleTable.ajax.reload();
		});
	}
	//删除
	function deletesById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleSetAction!deleteById.action?id=' + id, '是否删除该考勤类型?', workDayTable);
	}
</script>