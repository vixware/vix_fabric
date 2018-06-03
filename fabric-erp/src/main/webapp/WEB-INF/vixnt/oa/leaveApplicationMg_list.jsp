<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="id" name="id" value="${id}" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-group"></i> 会议管理
			</h1>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>参会人员列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form class="navbar-form navbar-left" role="search">
						姓名：
						<div class="form-group">
							<input class="form-control" type="text" value="" placeholder="姓名" id="searchCode">
						</div>
						<button onclick="wxpEmployeeTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchCode').val('');wxpEmployeeTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="wxpEmployeeTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var wxpEmployeeColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "员工职号",
	"data" : function(data) {
		return data.staffJobNumber;
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "电话",
	"data" : function(data) {
		return data.telephone;
	}
	} ];
	var wxpEmployeeTable = initDataTable("wxpEmployeeTableId", "${nvix}/nvixnt/conferenceManagementAction!goLeaveApplicationMgList.action?id=" + $('#id').val(), wxpEmployeeColumns, function(page, pageSize, orderField, orderBy) {
		var searchCode = $("#searchCode").val();
		searchCode = Url.encode(searchCode);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchCode" : searchCode
		};
	});
</script>