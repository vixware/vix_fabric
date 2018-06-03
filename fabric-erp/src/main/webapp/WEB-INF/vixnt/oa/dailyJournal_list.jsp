<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 办公 <span>&gt; 日报</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
				<button class="btn btn-primary" type="button" onclick="goWorkLogStatistics();">
					<i class="glyphicon glyphicon-stats"></i>&nbsp;日报统计
				</button>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget">
		<header>
		<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>日志列表</h2>
			<ul class="nav nav-tabs pull-right in" id="myTab">
				<li class="<c:if test='${source != "other"}'>active</c:if>"><a href="#s1" data-toggle="tab">我的日志</a></li>
				<li class="<c:if test='${source == "other"}'>active</c:if>"><a href="#s2" data-toggle="tab">下属日志</a></li>
			</ul>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="" class="tab-content">
					<div class="tab-pane fade in <c:if test='${source != "other"}'>active</c:if>" id="s1">
						<form class="navbar-form navbar-left" role="search">
							标题：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="标题" id="mytitle">
							</div>
							<button onclick="myworkLogTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#mytitle').val('');myworkLogTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
						<table id="myworkLog" class="table table-striped table-bordered table-hover" width="100%"></table>
					</div>
					<div class="tab-pane fade in <c:if test='${source == "other"}'>active</c:if>" id="s2">
						<form class="navbar-form navbar-left" role="search">
							标题：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="标题" id="title">
							</div>
							编写人：
							<div class="form-group">
								<input class="form-control" type="text" value="" placeholder="编写人" id="creator">
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
	"title" : "标题",
	"width" : "55%",
	"name" : "title",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "类型",
	"width" : "15%",
	"data" : function(data) {
		return data.logType == 0 ? '工作日志' : '个人日志';
	}
	}, {
	"title" : "编写时间",
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
		var show = "<a class='btn btn-xs btn-default' onclick=\"viewLog('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var showfile = "<a class='btn btn-xs btn-default' onclick=\"showfile('" + data.id + "');\" title='查看附件'><span class='txt-color-blue pull-right'><i class='fa fa-file'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var myworkLogTable = initDataTable("myworkLog", "${nvix}/nvixnt/dailyJournalAction!goMyWorkLog.action", myworkLogColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		return {
		"page" : page,
		"pageSize" : pageSize,
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
	"title" : "标题",
	"width" : "45%",
	"name" : "title",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "编写人",
	"width" : "15%",
	"name" : "uploadPerson",
	"data" : function(data) {
		return data.uploadPerson;
	}
	}, {
	"title" : "类型",
	"width" : "15%",
	"name" : "logType",
	"data" : function(data) {
		return data.logType == 0 ? '工作日志' : '个人日志';
	}
	}, {
	"title" : "编写时间",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "<a class='btn btn-xs btn-default' onclick=\"viewLog('" + data.id + "','other');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
	}
	} ];

	var otherworkLogTable = initDataTable("otherworkLog", "${nvix}/nvixnt/dailyJournalAction!goOtherWorkLog.action", otherworkLogColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		var creator = $("#creator").val();
		creator = Url.encode(creator);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title,
		"creator" : creator
		};
	});
	//根据ID查看工作日志
	function viewLog(id, source) {
		$.ajax({
		url : '${nvix}/nvixnt/dailyJournalAction!goViewLog.action?id=' + id + '&source=' + source,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	//新增
	function saveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/dailyJournalAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function goWorkLogStatistics(id) {
		$.ajax({
		url : '${nvix}/nvixnt/wxpWorkLogStatisticsAction!goAllStatisticsList.action',
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function showfile(id) {
		$.ajax({
		url : '${nvix}/nvixnt/dailyJournalAction!goWorkLogAttachment.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/dailyJournalAction!deleteById.action?id=' + id, '是否删除工作日志?', myworkLogTable);
	};
</script>