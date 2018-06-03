<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i>客户关系管理 <span>>字典管理  </span><span>>客户阶段  </span>
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
						<h2>客户阶段列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称: <input id="searchName" type="text" value="" class="form-control">
									</div>
									<button onclick="customerStageTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');customerStageTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="customerStageTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</article>
			</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var customerStageColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"data" : function(data) {
		return data.name;
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
	"title" : "是否启用",
	"name" : "enable",
	"data" : function(data) {
		if(data.enable == '1'){
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

	var customerStageTable = initDataTable("customerStageTableId", "${nvix}/nvixnt/nvixCustomerStageAction!getCustomerStageJson.action", customerStageColumns, function(page, pageSize, orderField, orderBy) {
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
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerStageAction!goSaveOrUpdate.action?id=' + id, 'customerStageForm', id == '' ? '新增客户阶段' : '修改客户阶段', 650, 350, customerStageTable,null,function(){
			if(!id){
				saveOrUpdate('');
			}
		});
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerStageAction!deleteById.action?id=' + id, '是否删除该客户阶段?', customerStageTable);
	};
</script>