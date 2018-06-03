<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-envelope"></i> 短息管理 <span onclick="">&gt; 短信发送记录</span>
			</h1>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header><span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>发送记录列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form class="navbar-form navbar-left" role="search">
					主题：
					<div class="form-group">
						<input class="form-control" type="text" value="" placeholder="主题" id="messageName">
					</div>
					手机号：
					<div class="form-group">
						<input class="form-control" type="text" value="" placeholder="名称" id="mobilePhone">
					</div>
					<button onclick="messageSendRecordTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#messageName').val('');$('#mobilePhone').val('');messageSendRecordTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table id="messageSendRecordTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var messageSendRecordColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "手机号",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}, {
	"title" : "短信内容",
	"width" : "50%",
	"data" : function(data) {
		return data.msgContent;
	}
	}, {
	"title" : "短信类型",
	"data" : function(data) {
		if (data.sendType == '0') {
			return "<span class='label label-info'>验证码</span>";
		} else if (data.sendType == '2') {
			return "<span class='label label-warning'>通知</span>";
		} else if (data.sendType == '1') {
			return "<span class='label label-primary'>群发</span>";
		}
		return "";
	}
	}, {
	"title" : "发送人",
	"data" : function(data) {
		return data.operator;
	}
	}, {
	"title" : "发送时间",
	"data" : function(data) {
		return data.postTimeStr;
	}
	} ];

	var messageSendRecordTable = initDataTable("messageSendRecordTableId", "${nvix}/message/messageSendRecordAction!goSingleList.action", messageSendRecordColumns, function(page, pageSize, orderField, orderBy) {
		var messageName = $("#messageName").val();
		messageName = Url.encode(messageName);
		var mobilePhone = $("#mobilePhone").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"messageName" : messageName,
		"mobilePhone" : mobilePhone
		};
	});
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/message/messageSendRecordAction!deleteById.action?id=' + id, '是否删除短信发送记录?', messageSendRecordTable);
	};
</script>