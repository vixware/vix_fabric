<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-desktop"></i> 办公 <span>&gt; 公告通知</span>
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
			<h2>公告通知列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form class="navbar-form navbar-left" role="search">
						标题：
						<div class="form-group">
							<input class="form-control" type="text" value="" placeholder="标题" id="searchCode">
						</div>
						<button onclick="noticeTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchCode').val('');noticeTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="notice" class="table table-striped table-bordered table-hover">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var noticeColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "title",
	"title" : "标题",
	"data" : function(data) {
		return data.title;
	}
	}, {
	"title" : "类型",
	"name" : "bulletinType",
	"data" : function(data) {
		if (data.bulletinType == 0) {
			return '公告';
		} else if (data.bulletinType == 1) {
			return '通知';
		}
	}
	}, {
	"title" : "范围",
	"name" : "pubNames",
	"data" : function(data) {
		return data.pubNames;
	}
	}, {
	"title" : "发布人",
	"name" : "uploadPersonName",
	"data" : function(data) {
		return data.uploadPersonName;
	}
	}, {
	"title" : "状态",
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
	"title" : "操作",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a class='btn btn-xs btn-default' onclick=\"viewNotice('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;
	}
	} ];

	var noticeTable = initDataTable("notice", "${nvix}/nvixnt/noticeAction!goNotice.action", noticeColumns, function(page, pageSize, orderField, orderBy) {
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
	  //处理CKEDITOR的值
    
	function CKupdate() {
		for (instance in CKEDITOR.instances)
			CKEDITOR.instances[instance].updateElement();
	}
	//发布公告通知
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/noticeAction!goSaveOrUpdate.action?id=' + id, "announcementNotificationForm", title, 850, 650, noticeTable,function (){
			CKupdate();
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/noticeAction!deleteById.action?id=' + id, '是否删除公告通知?', noticeTable);
	}

	function viewNotice(id) {
		$.ajax({
		url : '${nvix}/nvixnt/noticeAction!goViewNotice.action?id=' + id + "&syncTag=notice",
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>