<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt;销售计划</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
					<i class="fa fa-save"></i> &nbsp; 保存
				</button>
				<s:if test="isAllowAudit == 1">
					<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
						<i class="fa fa-save"></i> &nbsp; 提交审批
					</button>
				</s:if>
				<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSalePlanAction!goList.action');">
					<i class="glyphicon glyphicon-repeat"></i>&nbsp;返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售计划</h2>
		</header>
		<div class="widget-body">
			<form id="salePlanForm" class="form-horizontal">
				<input type="hidden" id="salePlanId" name="salePlan.id" value="${salePlan.id}"/>
				<input type="hidden" id="createTime" name="salePlan.createTime" value="${salePlan.createTime}"/>
				<input type="hidden" id="updateTime" name="salePlan.updateTime" value="${salePlan.updateTime}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="code" name="salePlan.code" value="${salePlan.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
						<div class="col-md-3">
							<input id="name" name="salePlan.name" value="${salePlan.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>部门:</label>
						<div class="col-md-3">
							<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgDeptId" name="salePlan.departmet.id" value="${salePlan.departmet.id}"/>
								<input id="chooseSaleOrgDeptName" type="text" onfocus="saleOrgDeptShowMenu(); return false;" value="${salePlan.departmet.fullName}" class="form-control validate[required]" readonly="readonly"/>
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售人员:</label>
						<div class="col-md-3">
							<input type="hidden" id="employeeId" name="salePlan.salesMan.id" value="${salePlan.salesMan.id}"/>
							<input id="employeeName" name="salePlan.salesMan.name" value="${salePlan.salesMan.name}"  data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="planDate" name="salePlan.planDate" value="${salePlan.planDate}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>版本号:</label>
						<div class="col-md-3">
							<input id="version" name="salePlan.version" value="${salePlan.version}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="salePlan.memo" value="${salePlan.memo}" class="form-control">${salePlan.memo}</textarea> 
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>计划明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
										</div>
										<button onclick="salePlanDetailsTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');salePlanDetailsTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdate('','新增');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="salePlanDetailsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">计划数量:</label>
						<div class="col-md-3">
							<input id="amount" name="salePlan.amount" value="${salePlan.amount}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">计划金额:</label>
						<div class="col-md-3">
							<input id="acount" name="salePlan.acount" value="${salePlan.acount}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> &nbsp; 保存
							</button>
							<s:if test="isAllowAudit == 1">
								<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
									<i class="fa fa-save"></i> &nbsp; 提交审批
								</button>
							</s:if>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixntSalePlanAction!goList.action');">
								<i class="glyphicon glyphicon-repeat"></i>返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#salePlanForm").validationEngine();
	 /** 初始化销售组织下拉列表树 */
	var saleOrgShowMenu = initDropListTree("saleOrgTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgId","chooseSaleOrgName","chooseSaleOrgZtree","saleOrgMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgId").val("");
			$("#chooseSaleOrgName").val("");
		}
	});
	 /** 初始化部门下拉列表树 */
	var saleOrgDeptShowMenu = initDropListTree("saleOrgDeptTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseSaleOrgDeptId","chooseSaleOrgDeptName","chooseSaleOrgDeptZtree","saleOrgDeptMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseSaleOrgDeptId").val("");
			$("#chooseSaleOrgDeptName").val("");
		}
	});
	var salePlanMenu = initDropListTree("parentSalePlanTreeMenu","${nvix}/nvixnt/nvixntSalePlanAction!findTreeToJson.action","chooseParentSalePlanId","chooseParentSalePlanName","chooseParentSalePlanZtree","parentSalePlanMenuContent",null);
	/* var salePlanShowMenu = initDropListTree("salePlanShowTreeMenu","${nvix}/nvixnt/nvixntSalePlanAction!findTreeToJson.action","chooseSalePlanId","chooseSalePlanName","chooseSalePlanZTree","salePlanMenuContent",null,function(treeNode){
		alert("1");
	}); */
	function saveOrUpdate(){
		//表单验证
		if($("#salePlanForm").validationEngine('validate')){
			$("#salePlanForm").ajaxSubmit({
				type: "post",
				url:"${nvix}/nvixnt/nvixntSalePlanAction!saveOrUpdate.action",
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "1"){
						showMessage(r[1]);
						loadContent('','${nvix}/nvixnt/nvixntSalePlanAction!goList.action');
					}else{
						showMessage(r[1]);
						return;
					}
				}
			});
		}
	}
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action?orgId='+$("#chooseSaleOrgDeptId").val(), 'single', '选择人员', 'employee');
	}
	function chooseItem() {
		chooseListData('${nvix}/nvixnt/vixntAllocationItemAction!goChooseItem.action', 'single', '选择商品', 'item');
	}
	function loadItemMeasureUnit(){
		var mug = $("#measureUnitGroupId").val();
		if (mug != "") {
			$.ajax({
			url : "${nvix}/nvixnt/nvixntSalePlanAction!loadMeasureUnit.action?parentId=" + mug,
			cache : false,
			success : function(html) {
				$("#measureUnitId").html(html);
			}
			});
			$.ajax({
			url : "${nvix}/nvixnt/nvixntSalePlanAction!loadMeasureUnit.action?parentId=" + mug,
			cache : false,
			success : function(html) {
				$("#assitMeasureUnitId").html(html);
			}
			});
		}
	}
	function approvalSalesOrder(){
		layer.confirm('订单提交审批后,将不可修改,确定要审批订单吗?',{
			btn:['确定','取消']
		},function(action){
			layer.close(action);
			audit();
		},function(action){
			layer.close(action);
		});
	}
	function audit(){
		//表单验证
		if($("#salePlanForm").validationEngine('validate')){
			$("#salePlanForm").ajaxSubmit({
				type: "post",
				url:"${nvix}/nvixnt/nvixntSalePlanAction!audit.action",
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success : function(result) {
					showMessage(result,'success');
					loadContent('','${nvix}/nvixnt/nvixntSalePlanAction!goList.action');
				}
			});
		}
	}
	function calculateAcount(){
		var amount = $("#amount").val();
		var itemPrice = $("#itemPrice").val();
		if(amount){
			if(itemPrice){
				var acount = amount * itemPrice;
				$("#acount").val(acount);
			}else{
				layer.alert("请选择商品!");
			}
		}else{
			layer.alert("请填写数量!");
		}
	}
	var salePlanDetailsColumns = [ {
		"title" : "编号",
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "商品编码",
		"data" : function(data) {
			return data.item == null ? "":data.item.code;
		}
		}, {
		"title" : "商品名称",
		"data" : function(data) {
			return data.item == null ? "":data.item.name;
		}
		}, /* {
		"title" : "规格型号",
		"name" : "specification",
		"data" : function(data) {
			return data.specification;
		}
		}, {
		"title" : "SKU编码",
		"name" : "skuCode",
		"data" : function(data) {
			return data.skuCode;
		}
		}, */{
		"title" : "单价",
		"name" : "price",
		"data" : function(data) {
			return data.item == null ? "": "￥"+data.item.price;
		}
		},{
		"title" : "01月",
		"data" : function(data) {
			return data.jan;
		}
		}, {
		"title" : "02月",
		"data" : function(data) {
			return data.feb;
		}
		},{
		"title" : "03月",
		"data" : function(data) {
			return data.mar;
		}
		}, {
		"title" : "04月",
		"data" : function(data) {
			return data.apr;
		}
		},{
		"title" : "05月",
		"data" : function(data) {
			return data.may;
		}
		}, {
		"title" : "06月",
		"data" : function(data) {
			return data.jun;
		}
		},{
		"title" : "07月",
		"data" : function(data) {
			return data.jul;
		}
		}, {
		"title" : "08月",
		"data" : function(data) {
			return data.aug;
		}
		},{
		"title" : "09月",
		"data" : function(data) {
			return data.sep;
		}
		}, {
		"title" : "10月",
		"data" : function(data) {
			return data.oct;
		}
		},{
		"title" : "11月",
		"data" : function(data) {
			return data.nov;
		}
		}, {
		"title" : "12月",
		"data" : function(data) {
			return data.december;
		}
		}, {
		"title" : "总计",
		"data" : function(data) {
			return data.amount;
		}
		}, {
		"title" : "操作",
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		} ];
		var salePlanDetailsTable = initDataTable("salePlanDetailsTableId", "${nvix}/nvixnt/nvixntSalePlanDetailsAction!goSingleList.action", salePlanDetailsColumns, function(page, pageSize, orderField, orderBy) {
			var id = $("#salePlanId").val();
			var searchName = $("#searchName").val();
			searchName = Url.encode(searchName);
			return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"itemName" : searchName,
			"id" : id
			};
		});
		
		function goSaveOrUpdate(id,title){
			var salePlanId = $("#salePlanId").val();
			if(salePlanId){
				openWindowForShow("${nvix}/nvixnt/nvixntSalePlanDetailsAction!goSaveOrUpdate.action?id="+id,"销售计划明细",800,630,function(){
					$.ajax({
						url : '${nvix}/nvixnt/nvixntSalePlanDetailsAction!compSalePlanAmountAndAcount.action?id=' + $("#salePlanId").val(),
						cache : false,
						success : function(result) {
							var r = result.split(":");
							$("#amount").val(r[0]);
							$("#acount").val(r[1]);
						}
					});
					salePlanDetailsTable.ajax.reload();
				});
			}else{
				if($("#salePlanForm").validationEngine('validate')){
					$("#salePlanForm").ajaxSubmit({
						type: "post",
						url:"${nvix}/nvixnt/nvixntSalePlanAction!saveOrUpdate.action",
						contentType: "application/x-www-form-urlencoded; charset=utf-8", 
						success : function(result) {
							var r = result.split(":");
							if(r[0] == "1"){
								$("#salePlanId").val(r[2]);
								showMessage(r[1],"success");
								openWindowForShow("${nvix}/nvixnt/nvixntSalePlanDetailsAction!goSaveOrUpdate.action?id="+id,"销售计划明细",800,630,function(){
									$.ajax({
										url : '${nvix}/nvixnt/nvixntSalePlanDetailsAction!compSalePlanAmountAndAcount.action?id=' + $("#salePlanId").val(),
										cache : false,
										success : function(result) {
											var r = result.split(":");
											$("#amount").val(r[0]);
											$("#acount").val(r[1]);
										}
									});
								});
							}else{
								showMessage(r[1],"success");
							}
						}
					});
				}
			}
		}
		function deleteById(id){
			deleteEntityByConfirm("${nvix}/nvixnt/nvixntSalePlanDetailsAction!deleteById.action?id="+id,"是否删除计划明细?",salePlanDetailsTable,"提示信息",function(){
				$.ajax({
					url : '${nvix}/nvixnt/nvixntSalePlanDetailsAction!compSalePlanAmountAndAcount.action?id=' + $("#salePlanId").val(),
					cache : false,
					success : function(result) {
						var r = result.split(":");
						$("#amount").val(r[0]);
						$("#acount").val(r[1]);
					}
				});
			});
		}
	pageSetUp();
</script>