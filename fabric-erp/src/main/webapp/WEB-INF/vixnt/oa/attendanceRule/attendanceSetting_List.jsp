<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row hidden-mobile">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i>考勤类型
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button onclick="saveOrUpdate('');" type="button" class="btn btn-primary">
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
						<h2>签到类型列表</h2>
					</header>
					<div>
						<div class="jarviswidget-editbox"></div>
						<div class="widget-body no-padding">
							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="s1">
									<div class="table-responsive">
										<div id="attendanceRuleSearchForm" class="input-group input-group-lg">
											<form class="navbar-form navbar-left" role="search">
												类型名称：
												<div class="form-group">
													<input class="form-control" type="text" value="" placeholder="名称" id="searchName">
												</div>
												<button onclick="attendanceRuleTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchName').val('');attendanceRuleTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</form>
										</div>
										<table id="attendanceRule" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th>编号</th>
													<th>编码</th>
													<th>类型名称</th>
													<th>创建时间</th>
													<th>状态</th>
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
	var attendanceRuleColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return '&nbsp;';
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"name" : "isEnable",
	"data" : function(data) {
		return data.isEnable == null ? '' : data.isEnable == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletesById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var attendanceRuleTable = initDataTable("attendanceRule", "${nvix}/nvixnt/attendanceSettingAction!getRulesJson.action", attendanceRuleColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName
		};
	});

	//新增
	function saveOrUpdate(id, syncTag) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceSettingAction!goSaveOrUpdate.action?id=' + id, "attSettingForm", "考勤类型", 750, 410, attendanceRuleTable);
	}
	//删除
	function deletesById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/attendanceSettingAction!deleteById.action?id=' + id, '是否删除该考勤类型?', attendanceRuleTable);
	}
</script>