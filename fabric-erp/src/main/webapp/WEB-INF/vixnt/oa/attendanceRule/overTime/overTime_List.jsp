<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row hidden-mobile">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i>加班规则
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button onclick="editOverTime('');" type="button" class="btn btn-primary">
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
						<h2>规则列表</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="s1">
									<div class="table-responsive">
										<div id="" class="input-group input-group-lg">
											<form class="navbar-form navbar-left" role="search">
												名称：
												<div class="form-group">
													<input class="form-control" type="text" value="" placeholder="名称" id="searchOverTimeRule">
												</div>
												<button onclick="overTimeTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchOverTimeRule').val('');overTimeTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</form>
										</div>
										<table id="overTime" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th>编号</th>
													<th>是否启用加班倍率</th>
													<th>日常加班倍率</th>
													<th>周末加班倍率</th>
													<th>假日加班倍率</th>
													<th>操作</th>
												</tr>
											</thead>
										</table>
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
	var overTimeColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return '&nbsp;';
	}
	}, {
	"name" : "isEnableOTRate",
	"data" : function(data) {
		return data.isEnableOTRate == null ? '' : data.isEnable == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"name" : "dailyOTRate",
	"data" : function(data) {
		return data.dailyOTRate;
	}
	}, {
	"name" : "weekendOTRatel",
	"data" : function(data) {
		return data.weekendOTRatel;
	}
	}, {
	"name" : "holidayOTRatel",
	"data" : function(data) {
		return data.holidayOTRatel;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"editOverTime('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletesById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var overTimeTable = initDataTable("overTime", "${nvix}/nvixnt/overTimeAction!getOverTimeRulesJson.action", overTimeColumns, function(page, pageSize, orderField, orderBy) {
		var searchOverTimeRule = $("#searchOverTimeRule").val();
		searchOverTimeRule = Url.encode(searchOverTimeRule);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchOverTimeRule" : searchOverTimeRule
		};
	});

	//新增
	function editOverTime(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/overTimeAction!goSaveOrUpdate.action?id=' + id, 'overTimeForm', '加班规则设定', 1000, 780, overTimeTable, null, function() {
			overTimeTable.ajax.reload();
		});
	}
	//删除
	function deletesById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleSetAction!deleteById.action?id=' + id, '是否删除该考勤类型?', attendanceRuleTable);
	}
</script>