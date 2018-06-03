<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-pencil-square-o"></i> 销售管理 <span onclick="">&gt; 返利规则</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
						<i class="fa fa-save"></i> &nbsp;保存
					</button>
					<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntReturnRuleAction!goList.action');">
						<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
					</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>返利规则</h2>
		</header>
		<div class="widget-body">
			<form id="returnRuleForm" class="form-horizontal">
				<input type="hidden" id="returnRuleId" name="returnRule.id" value="${returnRule.id}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>规则类型:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label onchange="rrTypeChange();" class="btn btn-default <s:if test='returnRule.rrType == "customerAccount"'>active</s:if>"> <input onchange="rrTypeChange();" type="radio" value="customerAccount" id="rrType" name="returnRule.rrType" class="validate[required]" onclick="rrTypeChange();" <s:if test='returnRule.rrType == "customerAccount"'>checked="checked"</s:if>>客户
								</label> <label onchange="rrTypeChange();" class="btn btn-default <s:if test='returnRule.rrType == "item"'>active</s:if>"> <input onchange="rrTypeChange();" type="radio" value="item" id="rrType" name="returnRule.rrType" <s:if test='returnRule.rrType == "item"'>checked="checked"</s:if>>商品
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 明细计算类型:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='returnRule.detailType == "count"'>active</s:if>"> <input type="radio" value="count" id="detailType" name="returnRule.detailType" class="validate[required]" <s:if test='returnRule.detailType == "count"'>checked="checked"</s:if>>数量
								</label><label class="btn btn-default <s:if test='returnRule.detailType == "money"'>active</s:if>"> <input type="radio" value="money" id="detailType" name="returnRule.detailType" <s:if test='returnRule.detailType == "money"'>checked="checked"</s:if>>金额
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">返款利率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="ratio" type="text" name="returnRule.ratio" value="${returnRule.ratio}"  class="form-control">
								<span class="input-group-addon">%</span>
							</div>
						</div>
						<label class="col-md-2 control-label">最低销售数量:</label>
						<div class="col-md-3">
							<input id="lowerSaleCount" type="text" name="returnRule.lowerSaleCount" value="${returnRule.lowerSaleCount}"  class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="returnRule.customerAccount.id" value="${returnRule.customerAccount.id}"/>
								<input id="customerName" value="${returnRule.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor:pointer;" onclick="">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label" id="itemDiv1">商品:</label>
						<div class="col-md-3" id="itemDiv">
							<div class="input-group">
								<input type="hidden" id="itemId" name="returnRule.item.id" value="${returnRule.item.id}"/>
								<input id="itemName" value="${returnRule.item.name}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor:pointer;" onclick="chooseItem();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="returnRule.memo" value="${returnRule.memo}" class="form-control">${returnRule.memo}</textarea> 
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>返利规则明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
										</div>
										<button onclick="returnRuleItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchItemName').val('');returnRuleItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateReturnRuleItem('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="returnRuleItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntReturnRuleAction!goList.action');">
								返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
var returnRuleItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "从",
	"orderable" : false,
	"data" : function(data) {
		return data.from;
	}
	}, {
	"title" : "到",
	"orderable" : false,
	"data" : function(data) {
		return data.to;
	}
	}, {
	"title" : "返款比率",
	"orderable" : false,
	"data" : function(data) {
		return data.ratio+"%";
	}
	}, {
	"title" : "计量单位",
	"orderable" : false,
	"data" : function(data) {
		return data.unit;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateReturnRuleItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteReturnRuleItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var returnRuleItemTable = initDataTable("returnRuleItemTableId", "${nvix}/nvixnt/nvixntReturnRuleAction!goSingleListReturnRuleItem.action", returnRuleItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var id = $("#returnRuleId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchItemName,
		"id":id
		};
	});
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	function chooseItem(){
		chooseListData('${nvix}/nvixnt/vixntProductAssemblyAction!goChooseItem.action', 'single', '选择商品','item');
	}
	$("#returnRuleForm").validationEngine();
	function saveOrUpdate(){
		if($("#returnRuleForm").validationEngine('validate')){
			$("#returnRuleForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixntReturnRuleAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					loadContent('', '${nvix}/nvixnt/nvixntReturnRuleAction!goList.action');
				}
			});
		}
	}
	function goSaveOrUpdateReturnRuleItem(id,title){
		var returnRuleId = $("#returnRuleId").val();
		if(!returnRuleId){
			if($("#returnRuleForm").validationEngine('validate')){
				$("#returnRuleForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/nvixntReturnRuleAction!saveOrUpdate.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == '1'){
							$("#returnRuleId").val(r[2]);
							showMessage(r[1]);
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntReturnRuleAction!goSaveOrUpdateReturnRuleItem.action?id=' + id ,"returnRuleItemForm", title, 850, 375,returnRuleItemTable);
						}
					}
				});
			}
		}else{
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntReturnRuleAction!goSaveOrUpdateReturnRuleItem.action?id=' + id ,"returnRuleItemForm", title, 850, 375,returnRuleItemTable);
		}
	}
	function deleteReturnRuleItemById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntReturnRuleAction!deleteReturnRuleItemById.action?id=' + id, '是否删除返利详情?', returnRuleItemTable,"删除返利详情");
	}
	$(function(){
		rrTypeChange();
	})
	function rrTypeChange(){
		if($("input[name='returnRule.rrType']:checked").val() == "customerAccount"){
			$("#itemDiv").attr("style","display:none");
			$("#itemDiv1").attr("style","display:none");
			$("#itemId").attr("disabled","disabled");
		}else if($("input[name='returnRule.rrType']:checked").val() == "item"){
			$("#itemDiv").attr("style","");
			$("#itemDiv1").attr("style","");
			$("#itemId").removeAttr("disabled");
		}
	}
</script>