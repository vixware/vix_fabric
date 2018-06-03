<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-file"></i> 文件管理
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
			<h2>文件</h2>
			<ul class="nav nav-tabs pull-right in" id="myTab">
				<li class="<c:if test='${source != "other"}'>active</c:if>"><a href="#s1" data-toggle="tab">我接收的</a></li>
				<li class="<c:if test='${source == "other"}'>active</c:if>"><a href="#s2" data-toggle="tab">我上传的</a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="" class="tab-content">
					<div class="tab-pane fade in <c:if test='${source != "other"}'>active</c:if>" id="s1">
						<form class="navbar-form navbar-left" role="search">
							内容：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="内容" id="title">
							</div>
							留言人：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="留言人" id="creator">
							</div>
							<button onclick="otherworkLogTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#title').val('');$('#creator').val('');otherworkLogTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="otherworkLog" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in <c:if test='${source == "other"}'>active</c:if>" id="s2">
						<form class="navbar-form navbar-left" role="search">
							内容：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="内容" id="mytitle">
							</div>
							<button onclick="myworkLogTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#mytitle').val('');$('#mycreator').val('');myworkLogTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myworkLog" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var myworkLogColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "内容",
	"width" : "70%",
	"name" : "logContent",
	"data" : function(data) {
		return data.logContent;
	}
	}, {
	"title" : "创建时间",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var showfile = "<a class='btn btn-xs btn-default' onclick=\"goCommunicationAttachment('" + data.id + "');\" title='查看附件'><span class='txt-color-blue pull-right'><i class='fa fa-file'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteCommunicationById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + showfile + "&nbsp;&nbsp;" + del;
	}
	} ];

	var myworkLogTable = initDataTable("myworkLog", "${nvix}/nvixnt/nvixFileAction!goFileList.action", myworkLogColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : mytitle
		};
	});
	var otherworkLogColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "留言内容",
	"width" : "55%",
	"name" : "logContent",
	"data" : function(data) {
		return data.logContent;
	}
	}, {
	"title" : "留言人",
	"width" : "15%",
	"data" : function(data) {
		return data.employeeName;
	}
	}, {
	"title" : "创建时间",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var show = "<a class='btn btn-xs btn-default' onclick=\"goCommunication('" + data.id + "','other');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var showfile = "<a class='btn btn-xs btn-default' onclick=\"goCommunicationAttachment('" + data.id + "');\" title='查看附件'><span class='txt-color-blue pull-right'><i class='fa fa-file'></i></span></a>";
		return show + "&nbsp;&nbsp;" + showfile;
	}
	} ];

	var otherworkLogTable = initDataTable("otherworkLog", "${nvix}/nvixnt/nvixFileAction!goOtherCommunication.action", otherworkLogColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		var creator = $("#creator").val();
		creator = Url.encode(creator);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"creator" : creator
		};
	});
	//根据ID查看工作日志
	function goCommunication(id, source) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixFileAction!goCommunication.action?id=' + id + '&source=' + source,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	//新增
	function saveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixFileAction!goSaveOrUpdate.action?id=' + id + '&source=file',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goCommunicationAttachment(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixFileAction!goCommunicationAttachment.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteCommunicationById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixFileAction!deleteCommunicationById.action?id=' + id, '是否删除沟通信息?', myworkLogTable);
	};
</script>