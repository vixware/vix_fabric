<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 生产管理 <span>> 工作中心 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#testid">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget jarviswidget-color-darken" id="chanceAndTrackingHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>工作中心列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										编码:<input type="text" value="" placeholder="工作中心编码" class="form-control" id="org">
										名称:<input type="text" value="" placeholder="工作中心名称" class="form-control" id="orgName">
									</div>
									<button onclick="workCenterTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#org').val('');$('#orgName').val('');workCenterTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="workCenterTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>工作中心编码</th>
										<th>工作中心名称</th>
										<th>每日人工产能</th>
										<th>每日机器产能</th>
										<th>标准人工效率</th>
										<th>标准机器负荷</th>
										<th>制费分摊依据</th>
										<th>标准工资率</th>
										<th>标准制费分摊率</th>
										<th>创建者</th>
										<th width="8%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var workCenterColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.org;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.orgName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.humanCapacity + "人时";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.machineCapacity + "机时";
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.orgEfficiency + "%";
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.machineLoad + "%";
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		if(data.types == 0){
			return "<span class='label label-info'>人时</span>";
		}else if(data.types == 1){
			return "<span class='label label-info'>机时</span>";
		}else if(data.types == 2){
			return "<span class='label label-info'>人工</span>";
		}
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.wageRate + "%";
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.systemRate + "%";
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.establishmentUser;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var workCenterTable = initDataTable("workCenterTableId", "${nvix}/nvixnt/produce/nvixWorkCenterAction!goListContent.action", workCenterColumns, function(page, pageSize, orderField, orderBy) {
		var org = $("#org").val();
		var orgName = $("#orgName").val();
		org = Url.encode(org);
		orgName = Url.encode(orgName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"org" : org,
		"orgName" : orgName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/produce/nvixWorkCenterAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/produce/nvixWorkCenterAction!deleteById.action?id=' + id, '是否删除工作中心?', workCenterTable);
	}

	pageSetUp();
</SCRIPT>