<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-envelope"></i> 短息管理 <span onclick="">&gt; 短信模板配置</span>
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
			<h2>短信模板列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form class="navbar-form navbar-left" role="search">
					模板名称：
					<div class="form-group">
						<input class="form-control" type="text" value="" placeholder="模板名称" id="messageName">
					</div>
					<button onclick="messageTemplateTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#messageName').val('');messageTemplateTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="messageTemplateTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var messageTemplateColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "模板名称",
	"width" : "15%",
	"data" : function(data) {
		return "<a onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'>"+data.name+"</a>";
	}
	}, {
	"title" : "短信内容",
	"width" : "70%",
	"data" : function(data) {
		return data.msgContentText;
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

	var messageTemplateTable = initDataTable("messageTemplateTableId", "${nvix}/message/messageTemplateAction!goSingleList.action", messageTemplateColumns, function(page, pageSize, orderField, orderBy) {
		var messageName = $("#messageName").val();
		messageName = Url.encode(messageName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"messageName" : messageName
		};
	});

	//新增
	function saveOrUpdate(id) {
		$.ajax({
			url : '${nvix}/message/messageTemplateAction!goSaveOrUpdate.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/message/messageTemplateAction!deleteById.action?id=' + id, '是否删除短信模板?', messageTemplateTable);
	};
</script>