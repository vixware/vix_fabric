<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-envelope"></i> 短信管理 <span>&gt; 短信接口配置</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header>
		<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>短信接口列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form class="navbar-form navbar-left" role="search">
					接口类型：
					<div class="form-group">
						<input class="form-control" type="text" value="" placeholder="接口类型" id="msgType">
					</div>
					<button onclick="ecMessageConfigTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#msgType').val('');ecMessageConfigTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="ecMessageConfigTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var ecMessageConfigColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "接口类型",
	"data" : function(data) {
		if (data.msgType == "AO_YI_HU_TONG") {
			return "<a onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='label label-info'>奥易互通</span></a>";
		} else if (data.msgType == "MEI_LIAN_RUAN_TONG") {
			return "<a onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='label label-primary'>美联软通</span></a>";
		}
		return "";
	}
	}, {
	"title" : "是否启用",
	"data" : function(data) {
		if (data.enable == 0) {
			return "<span class='label label-info'>禁用</span>";
		} else if (data.enable == 1) {
			return "<span class='label label-primary'>启用</span>";
		}
		return "";
	}
	}, {
	"title" : "接口账号",
	"data" : function(data) {
		return data.msgAccount;
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

	var ecMessageConfigTable = initDataTable("ecMessageConfigTableId", "${nvix}/nvixnt/messageApiSetAction!goSingleList.action", ecMessageConfigColumns, function(page, pageSize, orderField, orderBy) {
		var msgType = $("#msgType").val();
		msgType = Url.encode(msgType);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"msgType" : msgType
		};
	});

	//新增
	function saveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/messageApiSetAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/messageApiSetAction!deleteById.action?id=' + id, '是否删除短信接口?', ecMessageConfigTable);
	};
</script>