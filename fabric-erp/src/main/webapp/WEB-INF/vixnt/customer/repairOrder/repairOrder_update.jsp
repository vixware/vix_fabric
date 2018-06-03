<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售后管理 </span><span>> 维修工单 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixRepairOrderAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>维修工单</h2>
		</header>
		<div class="widget-body">
			<form id="repairOrderForm" class="form-horizontal">
				<input type="hidden" id="id" name="repairOrder.id" value="${repairOrder.id}" /> 
				<fieldset>
					<legend>单号与接件</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="subject" name="repairOrder.subject" value="${repairOrder.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>接单人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="repairOrder.employee.id" value="${repairOrder.employee.id}" /> 
								<input id="employeeName" name="repairOrder.employee.name" value="${repairOrder.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="repairOrder.customerAccount.id" value="${repairOrder.customerAccount.id}" /> 
								<input id="customerName" name="repairOrder.customerAccount.name" value="${repairOrder.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="repairOrder.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${repairOrder.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>接单日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="receiveDate" name="repairOrder.receiveDate" value="${repairOrder.receiveDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'receiveDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">工单类型:</label>
						<div class="col-md-3">
							<select id="repairOrderTypeId" name="repairOrder.repairOrderType.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${repairOrderTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${repairOrder.repairOrderType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<legend>接件详情</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>维修产品:</label>
						<div class="col-md-3">
							<input id="repairProduct" name="repairOrder.repairProduct" value="${repairOrder.repairProduct}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label">保修:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${repairOrder.isWarranty == 0}">active</c:if>"> 
									<input type="radio" value="0" id="isWarranty" name="repairOrder.isWarranty" <c:if test="${repairOrder.isWarranty == 0}">checked="checked"</c:if> >在保
								</label> 
								<label class="btn btn-default <c:if test="${repairOrder.isWarranty == 1}">active</c:if>"> 
									<input type="radio" value="1" id="isWarranty" name="repairOrder.isWarranty" <c:if test="${repairOrder.isWarranty == 1}">checked="checked"</c:if> >出保
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">产品生产日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="produceDate" name="repairOrder.produceDate" value="${repairOrder.produceDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'produceDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">产品销售日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="saleDate" name="repairOrder.saleDate" value="${repairOrder.saleDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'saleDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">故障描述:</label>
						<div class="col-md-3">
							<textarea id="troubleContent" name="repairOrder.troubleContent" class="form-control" rows="4">${repairOrder.troubleContent}</textarea>
						</div>
						<label class="col-md-2 control-label">注意事项:</label>
						<div class="col-md-3">
							<textarea id="notice" name="repairOrder.notice" class="form-control" rows="4">${repairOrder.notice}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>承接部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="treeMenu" class="input-group">
										<input id="saleOrgId" name="repairOrder.organizationUnit.id" type="hidden" value="${repairOrder.organizationUnit.id}" /> 
										<input id="saleOrgName" type="text" onfocus="chooseSaleOrg(); return false;" 
											value="${repairOrder.organizationUnit.fullName}" type="text" class="form-control validate[required]" style="cursor: pointer;" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#saleOrgId').val('');$('#saleOrgName').val('');">清空</button>
										</div>
										<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="saleOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<legend>费用与执行</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">费用:</label>
						<div class="col-md-3">
							<input id="money" name="repairOrder.money" value="${repairOrder.money}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label">已收款:</label>
						<div class="col-md-3">
							<input id="backSection" name="repairOrder.backSection" value="${repairOrder.backSection}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[0]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <c:if test="${repairOrder.status == 0}">active</c:if>"> 
									<input type="radio" value="0" id="status" name="repairOrder.status" <c:if test="${repairOrder.status == 0}">checked="checked"</c:if> class="validate[required]" >执行中
								</label> 
								<label class="btn btn-default <c:if test="${repairOrder.status == 1}">active</c:if>"> 
									<input type="radio" value="1" id="status" name="repairOrder.status" <c:if test="${repairOrder.status == 1}">checked="checked"</c:if> class="validate[required]" >结束
								</label>
								<label class="btn btn-default <c:if test="${repairOrder.status == 1}">active</c:if>"> 
									<input type="radio" value="1" id="status" name="repairOrder.status" <c:if test="${repairOrder.status == 1}">checked="checked"</c:if> class="validate[required]" >意外中止
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">约定交付日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryDate" name="repairOrder.deliveryDate" value="${repairOrder.deliveryDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'deliveryDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="repairOrder.memo" class="form-control" rows="4">${repairOrder.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixRepairOrderAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#repairOrderForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#repairOrderForm").validationEngine('validate')) {
			$("#repairOrderForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixRepairOrderAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixRepairOrderAction!goList.action');
			}
			});
		}
	}
	
	/** 加载联系人*/
	function loadContactPerson() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixContactPersonAction!loadContactPersonOption.action?parentId=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var contactPersonId = $("#contactPersonId").val();
				$("#contactPersonId").html(html);
				if (contactPersonId != '') {
					$("#contactPersonId").val(contactPersonId);
				}
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadContactPerson();
		});
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
	
	/** 初始化分类下拉列表树 */
	var chooseSaleOrg = initDropListTree("treeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","saleOrgId","saleOrgName","saleOrgTree","menuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#saleOrgId").val("");
			$("#saleOrgName").val("");
		}
	});
</script>