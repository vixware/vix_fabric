<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-cogs"></i> 短息管理 <span onclick="">&gt; 短信群发配置</span>
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
			<h2>短信群发列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form class="navbar-form navbar-left" role="search">
					名称：
					<div class="form-group">
						<input class="form-control" type="text" value="" placeholder="名称" id="messageName">
					</div>
					<button onclick="messageGroupSendTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#messageName').val('');messageGroupSendTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="messageGroupSendTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var messageGroupSendColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "名称",
	"width" : "15%",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "短信内容",
	"width" : "60%",
	"data" : function(data) {
		return data.content;
	}
	}, {
	"title" : "群发类型",
	"width" : "10%",
	"data" : function(data) {
		if(data.sendType == '0'){
			return "<span class='label label-info'>节假日群发</span>";
		}else if(data.sendType == '1'){
			return "<span class='label label-info'>新店开业群发</span>";
		}else if(data.sendType == '2'){
			return "<span class='label label-info'>新品上市群发</span>";
		}else if(data.sendType == '3'){
			return "<span class='label label-info'>会员唤醒群发</span>";
		}
		return "";
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

	var messageGroupSendTable = initDataTable("messageGroupSendTableId", "${nvix}/message/messageGroupSendAction!goSingleList.action", messageGroupSendColumns, function(page, pageSize, orderField, orderBy) {
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
			url : '${nvix}/message/messageGroupSendAction!goSaveOrUpdate.action?id=' + id,
			cache : false,
			success : function(html) {
				$("#mainContent").html(html);
			}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/message/messageGroupSendAction!deleteById.action?id=' + id, '是否删除短信群发配置?', messageGroupSendTable);
	};
</script>