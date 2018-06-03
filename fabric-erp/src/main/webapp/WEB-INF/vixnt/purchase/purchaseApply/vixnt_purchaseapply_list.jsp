<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span>&gt; 采购申请</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a href="#" class="btn btn-primary" onclick="goSaveOrUpdate('');"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</a>
			</div>
		</div>
	</div>
	<!-- row -->
	<div class="jarviswidget" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>申请列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							主题: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="purchasApplyTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '230px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
						<button onclick="clearSearchCondition('searchName,chooseSaleOrgDeptId,code,requirePerson,createTime',purchasApplyTable);" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<div id="advanceSearch" style="padding:20px 15px;display:none;">
						<form id="advanceSearchForm" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<lable class="col-md-2 control-label">单据编码:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="code" placeholder="单据编码" class="form-control"/>
									</div>
									<label class="col-md-2 control-label"><span class="text-danger">*</span>申请部门:</label>
									<div class="col-md-4">
										<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
											<input type="hidden" id="chooseSaleOrgDeptId" name="purchaseApply.purchaseOrgId" value="${purchaseApply.purchaseOrgId}"/>
											<input id="chooseSaleOrgDeptName" type="text" name="purchaseApply.purchaseOrg" onfocus="saleOrgDeptShowMenu(); return false;" value="${purchaseApply.purchaseOrg}" class="form-control validate[required]" readonly="readonly"/>
											<div class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="$('#chooseSaleOrgDeptId').val('');$('#chooseSaleOrgDeptName').val('');">
													清空
												</button>
											</div>
											<div id="saleOrgDeptMenuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
												<ul id="chooseSaleOrgDeptZtree" class="ztree"></ul>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<lable class="col-md-2 control-label">申请人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="requirePerson" placeholder="申请人" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">申请日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="申请日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('searchName,chooseSaleOrgDeptId,code,requirePerson,createTime',purchasApplyTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
				<table id="purchasApplyTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//我的报销单
	var purchasApplyColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "编码",
	"orderable" : false,
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"orderable" : false,
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "部门",
	"orderable" : false,
	"data" : function(data) {
		return data.purchaseOrg;
	}
	}, {
	"title" : "申请人",
	"orderable" : false,
	"data" : function(data) {
		return data.requirePerson;
	}
	}, {
	"title" : "申请日期",
	"orderable" : false,
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + show + "&nbsp;&nbsp;" + del;

	}
	} ];
	var purchasApplyTable = initDataTable("purchasApplyTableId", "${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goSingleList.action", purchasApplyColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		var code = $("#code").val();
		var requirePerson = $("#requirePerson").val();
		var createTime = $("#createTime").val();
		var chooseSaleOrgDeptId = $("#chooseSaleOrgDeptId").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code,
		"requirePerson" : requirePerson,
		"createTime" : createTime,
		"chooseSaleOrgDeptId" : chooseSaleOrgDeptId,
		"name" : searchName
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function show(id) {
		$.ajax({
		url : '${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!show.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!deleteById.action?id=' + id, '是否删除申请单?', purchasApplyTable);
	};
	var saleOrgDeptShowMenu = initDropListTree("saleOrgDeptTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgDeptId","chooseSaleOrgDeptName","chooseSaleOrgDeptZtree","saleOrgDeptMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgDeptId").val("");
			$("#chooseSaleOrgDeptName").val("");
		}
	});
	function advanceSearch(){
		purchasApplyTable.ajax.reload();
		layer.closeAll('page');
	}
</script>