<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 办公 <span>&gt; 新闻管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a href="#" class="btn btn-primary" onclick="goSaveOrUpdate('','新增');"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>新闻列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form class="navbar-form navbar-left" role="search">
						标题：
						<div class="form-group">
							<input class="form-control" type="text" value="" placeholder="标题" id="searchCode">
						</div>
						<button onclick="trendsTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchCode').val('');trendsTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="trends" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>标题</th>
							<th>发布人</th>
							<th>发布范围</th>
							<th>发布时间</th>
							<th>点击次数</th>
							<th>是否置顶</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var trendsColumns = [ {
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "title",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"name" : "uploadPersonName",
	"data" : function(data) {
		return data.uploadPersonName;
	}
	}, {
	"name" : "bizOrgNames",
	"data" : function(data) {
		return data.bizOrgNames;
	}
	}, {
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"name" : "readTimes",
	"data" : function(data) {
		return data.readTimes;
	}
	}, {
	"name" : "isTop",
	"data" : function(data) {
		if (data.isTop == 0) {
			return '否';
		} else if (data.isTop == 1) {
			return '是';
		}
	}
	}, {
	"name" : "isPublish",
	"data" : function(data) {
		if (data.isPublish == 0) {
			return '发布';
		} else if (data.isPublish == 1) {
			return '终止';
		}
	}
	}, {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"newsDetail('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var trendsTable = initDataTable("trends", "${nvix}/nvixnt/newsAdministrationAction!goTrends.action", trendsColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		searchCode = Url.encode(searchCode);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchCode" : searchCode
		};
	});
	//发布新闻
	  //处理CKEDITOR的值
           
	function CKupdate() {
		for (instance in CKEDITOR.instances)
			CKEDITOR.instances[instance].updateElement();
	}
	function goSaveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/newsAdministrationAction!goPublishOrNews.action?id=' + id, "trendsForm", title, 850, 650, trendsTable,function (){
			CKupdate();
		});
	};

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/newsAdministrationAction!deleteById.action?id=' + id, '是否删除新闻?', trendsTable);
	};
	//根据ID查看新闻
	function newsDetail(id) {
		$.ajax({
		url : '${nvix}/nvixnt/newsAdministrationAction!goNewsDetail.action?id=' + id + "&syncTag=news",
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>