<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="membershipSubdivisionId" name="" value="${id}" />
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 会员管理<span>> 会员细分管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntMembershipSubdivisionAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>会员列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var customerAccountColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"name" : "name",
	"data" : function(data) {
		return data.customerName;
	}
	}, {
	"title" : "手机号",
	"data" : function(data) {
		return data.mobilePhone;
	}
	}, {
	"title" : "地址",
	"data" : function(data) {
		return data.address;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goShowCustomer('" + data.customerAccountId + "');\"><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		return update;
	}
	} ];

	var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/vixntMembershipSubdivisionAction!goCustomerListContent.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#membershipSubdivisionId").val();
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"searchName" : searchName,
		"id" : id
		};
	});

	function goShowCustomer(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixCustomerAccountAction!show.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>