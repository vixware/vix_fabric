<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-pencil-square-o"></i> 销售管理 <span onclick="">&gt; 佣金方案</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
						<i class="fa fa-save"></i> &nbsp;保存
					</button>
					<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntCommissionPlanAction!goList.action');">
						<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
					</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>佣金方案</h2>
		</header>
		<div class="widget-body">
			<form id="commissionPlanForm" class="form-horizontal">
				<input type="hidden" id="commissionPlanId" name="commissionPlan.id" value="${commissionPlan.id}"/>
				<input type="hidden" id="createTime" name="commissionPlan.createTime" value="${commissionPlan.createTime}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="code" type="text" name="commissionPlan.code" value="${commissionPlan.code}"  class="form-control validate[required]">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" type="text" name="commissionPlan.name" value="${commissionPlan.name}"  class="form-control validate[required]">
						</div>
					</div>
					<div class="form-group">
						 <label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						 <div class="col-md-3">
							<div id="saleOrgTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgId" name="commissionPlan.saleOrg.id" value="${commissionPlan.saleOrg.id}"/>
								<input id="chooseSaleOrgName" type="text" onfocus="saleOrgShowMenu(); return false;" value="${commissionPlan.saleOrg.fullName}" class="form-control validate[required]" readonly="readonly"/>
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
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="commissionPlan.memo" value="${commissionPlan.memo}" class="form-control">${commissionPlan.memo}</textarea> 
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>佣金方案明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
										</div>
										<button onclick="commissionPlanItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchItemName').val('');commissionPlanItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateCommissionPlanItem('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="commissionPlanItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntCommissionPlanAction!goList.action');">
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
var commissionPlanItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
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
	"title" : "名称",
	"orderable" : false,
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "业务类别",
	"orderable" : false,
	"data" : function(data) {
		return data.bizType;
	}
	}, {
	"title" : "物料分类",
	"orderable" : false,
	"data" : function(data) {
		return data.itemTypeName;
	}
	}, {
	"title" : "计算基础",
	"orderable" : false,
	"data" : function(data) {
		return data.computeBaseTarget;
	}
	}, {
	"title" : "业绩考评方式",
	"orderable" : false,
	"data" : function(data) {
		if(data.performanceEvaluationMethod != null){
			if(data.performanceEvaluationMethod.name == "1"){
				return "<a href='javascript:void(0);' title='修改'><span class='label label-warning'>定额金额完成百分比</span></a>";
			}else if(data.performanceEvaluationMethod.name == "2"){
				return "<a href='javascript:void(0);' title='修改'><span class='label label-warning'>实际销售金额</span></a>";
			}else if(data.performanceEvaluationMethod.name == "3"){
				return "<a href='javascript:void(0);' title='修改'><span class='label label-warning'>定额数量完成百分比</span></a>";
			}else if(data.performanceEvaluationMethod.name == "4"){
				return "<a href='javascript:void(0);' title='修改'><span class='label label-warning'>实际销售数量</span></a>";
			}else if(data.performanceEvaluationMethod.name == "5"){
				return "<a href='javascript:void(0);' title='修改'><span class='label label-warning'>毛利</span></a>";
			}
		}
		return ""
	}
	}, {
	"title" : "业务调整系数",
	"orderable" : false,
	"data" : function(data) {
		return data.adjustCoefficient;
	}
	}, {
	"title" : "计算方式",
	"orderable" : false,
	"data" : function(data) {
		return data.computeStyle;
	}
	}, {
	"title" : "是否考虑累计业绩",
	"orderable" : false,
	"data" : function(data) {
		return data.isGrandTotal;
	}
	}, {
	"title" : "固定佣金比率",
	"orderable" : false,
	"data" : function(data) {
		return data.fixedCommissionTerm;
	}
	}, {
	"title" : "固定佣金",
	"orderable" : false,
	"data" : function(data) {
		return data.fixedCommission;
	}
	}, {
	"title" : "操作",
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateCommissionPlanItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteCommissionPlanItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var commissionPlanItemTable = initDataTable("commissionPlanItemTableId", "${nvix}/nvixnt/nvixntCommissionPlanAction!goSingleListCommissionPlanItem.action", commissionPlanItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var id = $("#commissionPlanId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchItemName,
		"id":id
		};
	});
	$("#commissionPlanForm").validationEngine();
	function saveOrUpdate(){
		if($("#commissionPlanForm").validationEngine('validate')){
			$("#commissionPlanForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixntCommissionPlanAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					loadContent('', '${nvix}/nvixnt/nvixntCommissionPlanAction!goList.action');
				}
			});
		}
	}
	function goSaveOrUpdateCommissionPlanItem(id,title){
		var commissionPlanId = $("#commissionPlanId").val();
		if(!commissionPlanId){
			if($("#commissionPlanForm").validationEngine('validate')){
				$("#commissionPlanForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/nvixntCommissionPlanAction!saveOrUpdate.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == '1'){
							$("#commissionPlanId").val(r[2]);
							showMessage(r[1]);
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntCommissionPlanAction!goSaveOrUpdateCommissionPlanItem.action?id=' + id ,"commissionPlanItemForm", title, 850, 475,commissionPlanItemTable);
						}
					}
				});
			}
		}else{
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntCommissionPlanAction!goSaveOrUpdateCommissionPlanItem.action?id=' + id ,"commissionPlanItemForm", title, 850, 475,commissionPlanItemTable);
		}
	}
	function deleteCommissionPlanItemById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntCommissionPlanAction!deleteCommissionPlanItemById.action?id=' + id, '是否删除佣金方案明细?', commissionPlanItemTable,"删除返利详情");
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