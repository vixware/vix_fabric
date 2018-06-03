<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 公文管理 <span>> 收文管理 </span><span>> 收文审核 </span>
			</h1>
		</div>
	</div>
	<section id="" class="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget jarviswidget-color-darken" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>收文列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										来文标题:<input type="text" value="" placeholder="来文标题" class="form-control" id="title"> 
									</div>
									<button onclick="receiveDocumentTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#title').val('');receiveDocumentTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="receiveDocumentTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>收文编号</th>
										<th>收文类型</th>
										<th>来文字号</th>
										<th>来文标题</th>
										<th>收文日期</th>
										<th>来文单位</th>
										<th>创建人</th>
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
	var receiveDocumentColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.code;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if(data.receiveType != null){
			return "<span class='label label-info'>"+data.receiveType.name+"</span>";
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.word;
	}
	},{
	"orderable" : false,
	"data" : function(data) {
		return data.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.receiveDateStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.organizationUnit.fullName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"check('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
			return update;
		}
		return '';
	}
	} ];

	var receiveDocumentTable = initDataTable("receiveDocumentTableId", "${nvix}/nvixnt/document/nvixReceiveDocumentAction!goCheckListContent.action", receiveDocumentColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#title").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title
		};
	});

	//更新
	function check(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/document/nvixReceiveDocumentAction!goCheck.action?id=' + id);
	}

	pageSetUp();
</SCRIPT>