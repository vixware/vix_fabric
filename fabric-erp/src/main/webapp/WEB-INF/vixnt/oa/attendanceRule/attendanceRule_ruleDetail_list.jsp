<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row hidden-mobile">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i>基本规则
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button onclick="editDetail('');" type="button" class="btn btn-primary">
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
										<div id="ruleDetailSearchForm" class="input-group input-group-lg">
											<form class="navbar-form navbar-left" role="search">
												名称：
												<div class="form-group">
													<input class="form-control" type="text" value="" placeholder="名称" id="searchDetail">
												</div>
												<button onclick="ruleDetailTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchDetail').val('');ruleDetailTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</form>
										</div>
										<table id="ruleDetail" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th>编号</th>
													<th>编码</th>
													<th>名称</th>
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
	var ruleDetailColumns = [ {
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
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"editDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			if (data.isEnable == '1') {
				var enable = "<a href='javascript:void(0);' onclick=\"enableStockProductNature('" + data.id + "');\" title='禁用' class='btn btn-xs btn-default'><span class='txt-color-red pull-right'><i class='fa fa-ban'></i></span></a>";
			} else {
				var enable = "<a href='javascript:void(0);' onclick=\"enableStockProductNature('" + data.id + "');\" title='启用' class='btn btn-xs btn-default'><span class='txt-color-green pull-right'><i class='fa fa-check'></i></span></a>";
			}
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			if (data.code == null) {
				return update + "&nbsp;&nbsp;" + enable + "&nbsp;&nbsp;" + del;
			} else {
				return update + "&nbsp;&nbsp;" + enable;
			}
		} else {
			return '';
		}
	}
	} ];

	var ruleDetailTable = initDataTable("ruleDetail", "${nvix}/nvixnt/attendanceRuleSetAction!getRuleDetailsJson.action", ruleDetailColumns, function(page, pageSize, orderField, orderBy) {
		var searchDetail = $("#searchDetail").val();
		searchDetail = Url.encode(searchDetail);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchDetail" : searchDetail
		};
	});

	//新增
	function editDetail(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/attendanceRuleSetAction!goSaveOrUpdateRuleDetail.action?id=' + id, 'ruleDetailForm', '基本规则', 985, 600, ruleDetailTable);
	}
	//删除
	function deletesById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/attendanceRuleSetAction!deleteById.action?id=' + id, '是否删除该考勤类型?', attendanceRuleTable);
	}
</script>