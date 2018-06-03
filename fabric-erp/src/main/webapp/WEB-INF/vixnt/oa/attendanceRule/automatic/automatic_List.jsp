<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row hidden-mobile">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i>自动套班
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button onclick="editAutomaticRule('');" type="button" class="btn btn-primary">
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
						<h2>自动套班列表</h2>
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
													<input class="form-control" type="text" value="" placeholder="名称" id="searchAutomaticRule">
												</div>
												<button onclick="automaticRuleTable.ajax.reload();" type="button" class="btn btn-info">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchAutomaticRule').val('');automaticRuleTable.ajax.reload();" type="button" class="btn btn-default">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</form>
										</div>
										<table id="automaticRule" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th>编号</th>
													<th>是否启用自动套班</th>
													<th>是否统计请假和缺勤</th>
													<th>回溯匹配最佳刷卡记录</th>
													<th>匹配最佳班次</th>
													<th>排班错误时再使用自动套班</th>
													<th>自动匹配所有班次</th>
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
	var automaticRuleColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return '&nbsp;';
	}
	}, {
	"name" : "isDisables",
	"data" : function(data) {
		return data.isDisables == null ? '' : data.isDisables == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"name" : "calculationLAA",
	"data" : function(data) {
		return data.calculationLAA == null ? '' : data.calculationLAA == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"name" : "recall",
	"data" : function(data) {
		return data.recall == null ? '' : data.recall == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"name" : "baseShift",
	"data" : function(data) {
		return data.baseShift == null ? '' : data.baseShift == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"name" : "selfMotion",
	"data" : function(data) {
		return data.selfMotion == null ? '' : data.selfMotion == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"name" : "selfMotionAll",
	"data" : function(data) {
		return data.selfMotionAll == null ? '' : data.selfMotionAll == '0' ? '<span style="color: red;">禁用</span>' : '<span style="color: blue;">启用</span>';
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"editAutomaticRule('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletesById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var automaticRuleTable = initDataTable("automaticRule", "${nvix}/nvixnt/automaticAction!getAutomaticsJson.action", automaticRuleColumns, function(page, pageSize, orderField, orderBy) {
		var searchAutomaticRule = $("#searchAutomaticRule").val();
		searchAutomaticRule = Url.encode(searchAutomaticRule);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchAutomaticRule" : searchAutomaticRule
		};
	});

	//新增
	function editAutomaticRule(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/automaticAction!goSaveOrUpdate.action?id=' + id, 'automaticRuleForm', '自动套班', 720, 420, automaticRuleTable, null, function() {
			automaticRuleTable.ajax.reload();
		});
	}
	//删除
	function deletesById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/automaticAction!deleteById.action?id=' + id, '是否删除该考勤类型?', automaticRuleTable);
	}
</script>