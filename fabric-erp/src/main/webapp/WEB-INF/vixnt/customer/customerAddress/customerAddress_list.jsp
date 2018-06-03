<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i>客户关系管理 <span>>客户管理  </span><span>>客户地址  </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp; 新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>客户地址列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										客户姓名: <input id="searchName" type="text" value="" class="form-control">
									</div>
									<button onclick="customerAddressTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');customerAddressTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="customerAddressTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerAddressColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "客户名称",
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccountName;
	}
	}, {
	"title" : "省",
	"name" : "province",
	"data" : function(data) {
		return data.province.name;
	}
	}, {
	"title" : "市",
	"name" : "city",
	"data" : function(data) {
		return data.city.name;
	}
	}, {
	"title" : "区",
	"name" : "region",
	"data" : function(data) {
		return data.district.name;
	}
	}, {
	"title" : "地址",
	"name" : "address",
	"data" : function(data) {
		return data.address;
	}
	},{
	"title" : "邮政编码",
	"name" : "postCode",
	"data" : function(data) {
		return data.postCode;
	}
	}, {
	"title" : "是否默认",
	"name" : "isDefault",
	"data" : function(data) {
		if(data.isDefault == '1'){
			return "<span class='label label-success'>是</span>";
		}else{
			return "<span class='label label-info'>否</span>";
		}
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var customerAddressTable = initDataTable("customerAddressTableId", "${nvix}/nvixnt/nvixCustomerAddressAction!goListContent.action", customerAddressColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : searchName
		};
	});

	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAddressAction!goSaveOrUpdate.action?id=' + id, 'customerAddressForm', id == '' ? '新增客户地址' : '修改客户地址', 650, 350, customerAddressTable);
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAddressAction!deleteById.action?id=' + id, '是否删除该客户地址?', customerAddressTable);
	};
</script>