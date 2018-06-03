<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-pencil-square-o"></i> 销售管理 <span onclick="">&gt; 佣金条件</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
						<i class="fa fa-save"></i> &nbsp;保存
					</button>
					<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntCommissionTermAction!goList.action');">
						<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
					</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>佣金条件</h2>
		</header>
		<div class="widget-body">
			<form id="commissionTermForm" class="form-horizontal">
				<input type="hidden" id="commissionTermId" name="commissionTerm.id" value="${commissionTerm.id}"/>
				<input type="hidden" id="createTime" name="commissionTerm.createTime" value="${commissionTerm.createTime}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="code" type="text" name="commissionTerm.code" value="${commissionTerm.code}"  class="form-control validate[required]">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" type="text" name="commissionTerm.name" value="${commissionTerm.name}"  class="form-control validate[required]">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">类型:</label>
						<div class="col-md-3">
							<input id="type" type="text" name="commissionTerm.type" readonly="readonly" value="${commissionTerm.type}"  class="form-control">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 佣金方案明细:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="commissionPlanItemType" name="commissionTerm.commissionPlanItem.id" value="${commissionTerm.commissionPlanItem.id}"/>
								<input type="hidden" id="commissionPlanItemId" name="commissionTerm.commissionPlanItem.id" value="${commissionTerm.commissionPlanItem.id}"/>
								<input id="commissionPlanItemName" value="${commissionTerm.commissionPlanItem.name}" onfocus="chooseCommissionPlanItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor:pointer;" onclick="">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">数量单位:</label>
						<div class="col-md-3">
							<input id="amountUnit" type="text" name="commissionTerm.amountUnit" value="${commissionTerm.amountUnit}"  class="form-control">
						</div>
						<label class="col-md-2 control-label">金额单位:</label>
						<div class="col-md-3">
							<input id="sumUnit" type="text" name="commissionTerm.sumUnit" value="${commissionTerm.sumUnit}"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>从:</label>
						<div class="col-md-3">
							<input id="from" type="text" name="commissionTerm.from" value="${commissionTerm.from}"  class="form-control validate[required]">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>到:</label>
						<div class="col-md-3">
							<input id="to" type="text" name="commissionTerm.to" value="${commissionTerm.to}"  class="form-control validate[required]">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">返款比率:</label>
						<div class="col-md-3">
							<input id="returnTerm" type="text" onblur="checkReturnTerm();" name="commissionTerm.returnTerm" value="${commissionTerm.returnTerm}"  class="form-control validate[range:[0,100]]">
						</div>
						<label class="col-md-2 control-label"> 返款金额:</label>
						<div class="col-md-3">
							<input id="returnAmount" type="text" onblur="checkReturnAmount();" name="commissionTerm.returnAmount" value="${commissionTerm.returnAmount}"  class="form-control validate[number:true]">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="commissionTerm.memo" value="${commissionTerm.memo}" class="form-control">${commissionTerm.memo}</textarea> 
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> &nbsp; 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntCommissionTermAction!goList.action');">
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
var commissionTermItemColumns = [ {
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
	"title" : "${vv:varView('vix_mdm_item')}分类",
	"orderable" : false,
	"data" : function(data) {
		return data.itemType;
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
		return data.performanceEvaluationMethod == null ? "": data.performanceEvaluationMethod.name;
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
	var commissionTermItemTable = initDataTable("commissionTermItemTableId", "${nvix}/nvixnt/nvixntCommissionTermAction!goSingleListCommissionPlanItem.action", commissionTermItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var id = $("#commissionTermId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchItemName,
		"id":id
		};
	});
	$("#commissionTermForm").validationEngine();
	function saveOrUpdate(){
		if($("#commissionTermForm").validationEngine('validate')){
			$("#commissionTermForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixntCommissionTermAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					loadContent('', '${nvix}/nvixnt/nvixntCommissionTermAction!goList.action');
				}
			});
		}
	}
	var showICMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/nvixntPersonnelCategoryAction!findTreeToJson.action", "personnelCategoryId", "personnelCategoryName", "personnelCategoryTree", "treeMenuContent");
	var saleOrgShowMenu = initDropListTree("saleOrgTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgId","chooseSaleOrgName","chooseSaleOrgZtree","saleOrgMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgId").val("");
			$("#chooseSaleOrgName").val("");
		}
	});
	function chooseCommissionPlanItem() {
		chooseListData('${nvix}/nvixnt/nvixntCommissionTermAction!goChooseCommissionPlanItem.action', 'single', '选择佣金方案明细', 'commissionPlanItem',function(){
			var commissionPlanItemType = $("#commissionPlanItemType").val();
			layer.alert(commissionPlanItemType);
			if(commissionPlanItemType){
				if(commissionPlanItemType == "1"){
					layer.alert(2)
					$("#type").val("定额金额完成百分比");
				}else if(commissionPlanItemType == "2"){
					$("#type").val("定额销售金额");
				}else if(commissionPlanItemType == "3"){
					$("#type").val("定额数量完成百分比");
				}else if(commissionPlanItemType == "4"){
					$("#type").val("实际销售数量");
				}else if(commissionPlanItemType == "5"){
					$("#type").val("毛利");
				}
			}
		});
	}
	$(function(){
		checkReturnAmount();
		checkReturnTerm();
	})
	function checkReturnAmount(){
		var returnAmount = $("#returnAmount").val();
		var returnTerm = $("#returnTerm").val();
		if(returnAmount){
			$("#returnTerm").val("");
			$("#returnTerm").attr("readonly","readonly");
		}else{
			$("#returnTerm").val("");
			$("#returnTerm").removeAttr("readonly");
		}
	}
	function checkReturnTerm(){
		var returnTerm = $("#returnTerm").val();
		var returnAmount = $("#returnAmount").val();
		if(returnTerm){
			$("#returnAmount").val("");
			$("#returnAmount").attr("readonly","readonly");
		}else{
			$("#returnAmount").val("");
			$("#returnAmount").removeAttr("readonly");
		}
	}
</script>