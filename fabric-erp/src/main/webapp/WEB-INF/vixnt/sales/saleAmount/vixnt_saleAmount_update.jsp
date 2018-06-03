<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-pencil-square-o"></i> 销售管理 <span onclick="">&gt; 销售定额</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
						<i class="fa fa-save"></i> &nbsp;保存
					</button>
					<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSaleAmountAction!goList.action');">
						<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
					</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售定额</h2>
		</header>
		<div class="widget-body">
			<form id="saleAmountForm" class="form-horizontal">
				<input type="hidden" id="saleAmountId" name="saleAmount.id" value="${saleAmount.id}"/>
				<input type="hidden" id="createTime" name="saleAmount.createTime" value="${saleAmount.createTime}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="code" type="text" name="saleAmount.code" value="${saleAmount.code}"  class="form-control validate[required]">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" type="text" name="saleAmount.name" value="${saleAmount.name}"  class="form-control validate[required]">
						</div>
					</div>
					<div class="form-group">
						 <label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						 <div class="col-md-3">
							<div id="saleOrgTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgId" name="saleAmount.saleOrg.id" value="${saleAmount.saleOrg.id}"/>
								<input id="chooseSaleOrgName" type="text" onfocus="saleOrgShowMenu(); return false;" value="${saleAmount.saleOrg.fullName}" class="form-control validate[required]" readonly="readonly"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#chooseSaleOrgId').val('');$('#chooseSaleOrgName').val('');">
										清空
									</button>
								</div>
								<div id="saleOrgMenuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
									<ul id="chooseSaleOrgZtree" class="ztree"></ul>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售人员类别:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="treeMenu" class="input-group">
										<input id="personnelCategoryId"  name="saleAmount.personnelCategory.id"  value="${saleAmount.personnelCategory.id}" type="hidden" />
										<input id="personnelCategoryName"  onfocus="showICMenu(); return false;" value="${saleAmount.personnelCategory.name}" type="text" readonly="readonly" class="form-control" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#personnelCategoryId').val('');$('#personnelCategoryName').val('');">清空</button>
										</div>
										<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="personnelCategoryTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="customerDiv" class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售人员:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="saleAmount.employee.id" value="${saleAmount.employee.id}"/>
								<input id="employeeName" name="saleAmount.employee.name" value="${saleAmount.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>年度:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="year" name="saleAmount.year" value="${saleAmount.year}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy',el:'year'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="saleAmount.memo" value="${saleAmount.memo}" class="form-control">${saleAmount.memo}</textarea> 
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>销售定额明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
										</div>
										<button onclick="saleAmountItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchItemName').val('');saleAmountItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateSaleAmountItem('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="saleAmountItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> &nbsp; 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSaleAmountAction!goList.action');">
								<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
var saleAmountItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "月份",
	"orderable" : false,
	"data" : function(data) {
		return data.month;
	}
	}, {
	"title" : "季度",
	"orderable" : false,
	"data" : function(data) {
		return "<span class='label label-success'>第"+data.quarter+"季度</span>";
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}名称",
	"orderable" : false,
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "期间金额",
	"orderable" : false,
	"data" : function(data) {
		return data.saleAmountQuota == null ? "￥0":"￥"+data.saleAmountQuota;
	}
	}, {
	"title" : "当前期间累计金额",
	"orderable" : false,
	"data" : function(data) {
		return data.saleAmountQuotaTotal == null ? "￥0":"￥"+data.saleAmountQuotaTotal;
	}
	}, {
	"title" : "期间数量",
	"orderable" : false,
	"data" : function(data) {
		return data.saleCountQuota == null ? "0":data.saleCountQuota;
	}
	}, {
	"title" : "当前期间累计数量",
	"orderable" : false,
	"data" : function(data) {
		return data.saleCountQuotaTotal == null ? "0":data.saleCountQuotaTotal;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateSaleAmountItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteSaleAmountItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var saleAmountItemTable = initDataTable("saleAmountItemTableId", "${nvix}/nvixnt/nvixntSaleAmountAction!goSingleListSaleAmountItem.action", saleAmountItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var id = $("#saleAmountId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchItemName,
		"id":id
		};
	});
	$("#saleAmountForm").validationEngine();
	function saveOrUpdate(){
		if($("#saleAmountForm").validationEngine('validate')){
			$("#saleAmountForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixntSaleAmountAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					loadContent('', '${nvix}/nvixnt/nvixntSaleAmountAction!goList.action');
				}
			});
		}
	}
	function goSaveOrUpdateSaleAmountItem(id,title){
		var saleAmountId = $("#saleAmountId").val();
		if(!saleAmountId){
			if($("#saleAmountForm").validationEngine('validate')){
				$("#saleAmountForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/nvixntSaleAmountAction!saveOrUpdate.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == '1'){
							$("#saleAmountId").val(r[2]);
							showMessage(r[1]);
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntSaleAmountAction!goSaveOrUpdateSaleAmountItem.action?id=' + id ,"saleAmountItemForm", title, 850, 375,saleAmountItemTable);
						}
					}
				});
			}
		}else{
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntSaleAmountAction!goSaveOrUpdateSaleAmountItem.action?id=' + id ,"saleAmountItemForm", title, 850, 375,saleAmountItemTable);
		}
	}
	function deleteSaleAmountItemById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntSaleAmountAction!deleteSaleAmountItemById.action?id=' + id, '是否删除定额明细?', saleAmountItemTable,"删除返利详情");
	}
	var showICMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/nvixntPersonnelCategoryAction!findTreeToJson.action", "personnelCategoryId", "personnelCategoryName", "personnelCategoryTree", "treeMenuContent");
	var saleOrgShowMenu = initDropListTree("saleOrgTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgId","chooseSaleOrgName","chooseSaleOrgZtree","saleOrgMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgId").val("");
			$("#chooseSaleOrgName").val("");
		}
	});
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>