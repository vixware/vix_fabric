<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cog"></i> 系统管理 <span>> 用户申请管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>申请列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="userApplicationTable.ajax.reload();" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');userApplicationTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="userApplicationTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var userApplicationColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "公司名称",
	"width" : "30%",
	"data" : function(data) {
		return data.orgName;
	}
	}, {
	"title" : "行业",
	"data" : function(data) {
		return data.industry;
	}
	}, {
	"title" : "联系人",
	"data" : function(data) {
		return data.companyOrgContact;
	}
	}, {
	"title" : "联系电话",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "邮箱",
	"data" : function(data) {
		return data.email;
	}
	}, {
	"title" : "操作",
	"orderable" : false,
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		return update;
	}
	} ];

	var userApplicationTable = initDataTable("userApplicationTableId", "${nvix}/nvixnt/system/userApplicationAction!goSingleList.action", userApplicationColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName
		};
	});

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/system/userApplicationAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</SCRIPT>