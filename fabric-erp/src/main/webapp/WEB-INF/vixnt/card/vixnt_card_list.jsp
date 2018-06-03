<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 会员管理<span>> 卡定义 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>卡列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="cardEntityTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');cardEntityTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="cardEntityTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var cardEntityColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "20%",
	"name" : "name",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "金额",
	"width" : "20%",
	"data" : function(data) {
		return data.payMoney;
	}
	}, {
	"title" : "类型",
	"width" : "20%",
	"data" : function(data) {
		if (data.type == 1) {
			return "<span class='label label-warning'>余额卡</span>";
		} else if (data.type == 2) {
			return "<span class='label label-info'>次卡</span>";
		} else if (data.type == 3) {
			return "<span class='label label-primary'>通用卡</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		/* var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del; */
		
		var start = "<div class=\"btn-group\"><button class=\"btn btn-success btn-xs dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-gear\"></i> 操作  <span class=\"caret\"></span></button><ul class=\"dropdown-menu pull-right\">";
		var update = "<li><a href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'> <span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span>修改</a></li>";
		var del = "<li><a href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span>删除</a></li>";
		var end = "</ul></div>";
		return start + update + del + end;
	}
	} ];

	var cardEntityTable = initDataTable("cardEntityTableId", "${nvix}/nvixnt/cardAction!goSingleList.action", cardEntityColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	});
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/cardAction!goSaveOrUpdate.action?id=' + id, "cardEntityForm", title, 850, 650, cardEntityTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/cardAction!deleteById.action?id=' + id, '是否删除会员卡?', cardEntityTable);
	};
</script>