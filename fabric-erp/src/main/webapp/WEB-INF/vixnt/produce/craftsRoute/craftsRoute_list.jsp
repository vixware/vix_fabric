<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 生产管理 <span>> 工艺路线 </span>
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
						<h2>工艺路线列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										编码:<input type="text" value="" placeholder="工艺路线编码" class="form-control" id="craftsCode">
										名称:<input type="text" value="" placeholder="工艺路线名称" class="form-control" id="craftsName">
									</div>
									<button onclick="craftsRouteTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#craftsCode').val('');$('#craftsName').val('');craftsRouteTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="craftsRouteTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>工艺路线编码</th>
										<th>工艺路线名称</th>
										<th>物料编码</th>
										<th>物料名称</th>
										<th>版本号</th>
										<th>创建时间</th>
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
	var craftsRouteColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.craftsCode;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.craftsName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.materialCode;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.materialName;
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.versionNumber;
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.craDateStr;
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

	var craftsRouteTable = initDataTable("craftsRouteTableId", "${nvix}/nvixnt/produce/nvixCraftsRouteAction!goListContent.action", craftsRouteColumns, function(page, pageSize, orderField, orderBy) {
		var craftsCode = $("#craftsCode").val();
		var craftsName = $("#craftsName").val();
		craftsCode = Url.encode(craftsCode);
		craftsName = Url.encode(craftsName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"craftsCode" : craftsCode,
		"craftsName" : craftsName
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/produce/nvixCraftsRouteAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/produce/nvixCraftsRouteAction!deleteById.action?id=' + id, '是否删除工艺路线?', craftsRouteTable);
	}

	pageSetUp();
</SCRIPT>