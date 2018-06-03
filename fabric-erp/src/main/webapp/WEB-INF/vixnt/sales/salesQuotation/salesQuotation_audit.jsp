<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt;报价单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
					<i class="glyphicon glyphicon-ok-circle"></i> 同意
				</button>
				<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
					<i class="glyphicon glyphicon-remove-circle"></i> 驳回
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>报价单</h2>
		</header>
		<div class="widget-body">
			<form id="salesQuotationForm" class="form-horizontal" action="${nvix}/nvixnt/nvixSalesQuotationAction!saveOrUpdate.action">
				<input type="hidden" id="id" name="salesQuotation.id" value="${salesQuotation.id}"/>
				<input type="hidden" id="isDeleted" name="salesQuotation.isDeleted" value="${salesQuotation.isDeleted}"/>
				<input type="hidden" id="billGroupCodeId" name="billGroupCode.id" value="${billGroupCode.id}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价单编码:</label>
						<div class="col-md-3">
							<input id="code" name="salesQuotation.code" value="${salesQuotation.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价单主题:</label>
						<div class="col-md-3">
							<input id="quoteSubject" name="salesQuotation.quoteSubject" value="${salesQuotation.quoteSubject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<input type="hidden" id="customerId" name="salesQuotation.customerAccount.id" value="${salesQuotation.customerAccount.id}"/>
							<input id="customerName" name="salesQuotation.customerAccount.name" value="${salesQuotation.customerAccount.name}"  data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
						<label class="col-md-2 control-label">销售机会:</label>
						<div class="col-md-3">
							<select id="saleChanceId" name="salesQuotation.saleChance.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleChanceList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesQuotation.saleChance.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="salesQuotation.currencyType.id" class="form-control">
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesQuotation.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">报价单类型:</label>
						<div class="col-md-3">
							<select id="typeId" name="salesQuotation.type.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${billsTypeList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesQuotation.type.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>部门:</label>
						<div class="col-md-3">
							<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgDeptId" name="salesQuotation.dept.id" value="${salesQuotation.dept.id}"/>
								<input id="chooseSaleOrgDeptName" type="text" onfocus="saleOrgDeptShowMenu(); return false;" value="${salesQuotation.dept.fullName}" class="form-control validate[required]" readonly="readonly"/>
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						<div class="col-md-3">
							<div id="saleOrgTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgId" name="salesQuotation.salesOrg.id" value="${salesQuotation.salesOrg.id}"/>
								<input id="chooseSaleOrgName" type="text" onfocus="saleOrgShowMenu(); return false;" value="${salesQuotation.salesOrg.fullName}" class="form-control validate[required]" readonly="readonly"/>
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span>业务类型:</label>
						<div class="col-md-3">
							<input id="bizType" name="salesQuotation.bizType" value="${salesQuotation.bizType}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>地域:</label>
						<div class="col-md-3">
							<select id="regionalId" name="salesQuotation.regional.id" data-prompt-position="topLeft" class="form-control validate[required]" >
								<option value="">------请选择------</option>
								<c:forEach items="${regionalList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesQuotation.regional.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="billDate" name="salesQuotation.billDate" value="${salesQuotation.billDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">税率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="tax" name="salesQuotation.tax" value="${salesQuotation.tax}" class="form-control validate[custom[number],min[1],max[100]]" type="text"/>
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">接收人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="salesQuotation.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesQuotation.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">报价人:</label>
						<div class="col-md-3">
								<input type="hidden" id="employeeId" name="salesQuotation.employee.id" value="${salesQuotation.employee.id}"/>
								<input id="employeeName" name="salesQuotation.employee.name" value="${salesQuotation.employee.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">报价人联系方式:</label>
						<div class="col-md-8">
							<input id="phone" name="salesQuotation.phone" value="${salesQuotation.phone}" class="form-control validate[custom[phone]]" type="text"/>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label">交货日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dilveryDate" name="salesQuotation.dilveryDate" value="${salesQuotation.dilveryDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dilveryDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价有效期从:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="validQuotationFrom" name="salesQuotation.validQuotationFrom" value="${salesQuotation.validQuotationFromStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'validQuotationFrom'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>报价有效期至:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="validQuotationTo" name="salesQuotation.validQuotationTo" value="${salesQuotation.validQuotationToStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'validQuotationTo'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div> --%>
					<div class="form-group">
						<label class="col-md-2 control-label">交付说明:</label>
						<div class="col-md-3">
							<textarea rows="3" id="deliveryMemo" name="salesQuotation.deliveryMemo" class="form-control">${salesQuotation.deliveryMemo}</textarea> 
						</div>
						<label class="col-md-2 control-label">付款说明:</label>
						<div class="col-md-3">
							<textarea rows="3" id="paymentMemo" name="salesQuotation.paymentMemo" class="form-control">${salesQuotation.paymentMemo}</textarea> 
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">包装运输说明:</label>
						<div class="col-md-3">
							<textarea rows="3" id="packMemo" name="salesQuotation.packMemo" class="form-control">${salesQuotation.packMemo}</textarea> 
						</div>
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<textarea rows="3" id="memo" name="salesQuotation.memo" class="form-control">${salesQuotation.memo}</textarea> 
						</div>
					</div>
					<br/>
					<div class="form-group">
						<div class="col-md-12">
							<div id="salesQuotationDetailDiv" class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>报价单明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin:5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="货品名称" class="form-control" id="searchSalesItemName">
												</div>
												<button onclick="salesQuotationDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left" >
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchSalesItemName').val('');salesQuotationDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
											</div>
										</div>
									   	<table id="salesQuotationDetail" class="table table-striped table-bordered table-hover" width="100%"></table>
								 	</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<%-- <label class="col-md-2 control-label">报价人:</label>
						<div class="col-md-2">
							<div class="input-group">
								<input type="hidden" id="chooseSalesManId" name="salesQuotation.salesMan.id" value="${salesQuotation.salesMan.id}"/>
								<input id="chooseSalesManName" name="salesQuotation.salesMan.name" value="${salesQuotation.salesMan.name}" onfocus="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalesMan');" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalesMan');;">选择</span>
							</div>
						</div> --%>
						<label class="col-md-2 control-label">金额:</label>
						<div class="col-md-3">
							<input id="amount" name="salesQuotation.amount" value="${salesQuotation.amount}" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
					<s:if test="flowApprovalOpinionList.size > 0">
						<s:iterator value="flowApprovalOpinionList" var="entity" status="s">
							<div class="form-group">
								<label class="control-label col-md-2">审批人:</label>
								<div class="col-md-3">
									<input class="form-control" id="" name="" value="${entity.approvalPersonName}" type="text" readonly="readonly">
								</div>
								<label class="control-label col-md-2">审批意见:</label>
								<div class="col-md-3">
									<input class="form-control" id="" name="" value="${entity.opinion}" type="text" readonly="readonly">
								</div>
							</div>
						</s:iterator>
					</s:if>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button class="btn bg-color-green txt-color-white" type="button" onclick="goAudit('${taskId}');">
								<i class="glyphicon glyphicon-ok-circle"></i> 同意
							</button>
							<button class="btn bg-color-red txt-color-white" type="button" onclick="reject('${taskId}');">
								<i class="glyphicon glyphicon-remove-circle"></i> 驳回
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
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
	$("#salesQuotationForm").validationEngine();
	
	var salesQuotationDetailColumns = [
			{"title":"条形码","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.barCode;}},
			{"title":"SKU","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.skuCode;}},
			{"title":"编码","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.code;}},
			{"title":"名称","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.name;}},
			{"title":"规格型号","width":"6%","orderable":false,"data" : function(data) {return data.specification;}},
			{"title":"主计量单位","width":"6%","orderable":false,"data" : function(data) {return data.measureUnit == null ? '' : data.measureUnit;}},
			{"title":"数量","width":"6%","orderable":false,"data" : function(data) {return data.amount;}},
			{"title":"含税单价","width":"6%","orderable":false,"data" : function(data) {return data.taxPrice == null ? "￥ 0" : ("￥" + data.taxPrice);}},
			{"title":"金额","width":"6%","orderable":false,"data" : function(data) {return data.price == null ? "￥ 0" : ("￥" + data.price);}},
			{"title":"无税单价","width":"6%","orderable":false,"data" : function(data) {return data.netPrice == null ? "￥ 0" : ("￥" + data.netPrice);}},
			{"title":"无税金额","width":"6%","orderable":false,"data" : function(data) {return data.netTotal == null ? "￥ 0" : ("￥" + data.netTotal);}},
			{"title":"税率","width":"6%","orderable":false,"data" : function(data) {return data.tax == null ? "0&nbsp;%" : (data.tax + "&nbsp;%");}},
			{"title":"折扣率","width":"6%","orderable":false,"data" : function(data) {return data.discount == null ? "0&nbsp;%" : (data.discount + "&nbsp;%");}},
			{"title":"发货日期","width":"6%","orderable":false,"data" : function(data) {return data.requireDate == null ? '' : data.requireDateStr;}},
			{"title":"备注","width":"6%","orderable":false,"data" : function(data) {return data.memo;}}
		];
   	var salesQuotationDetailTable = initDataTable("salesQuotationDetail","${nvix}/nvixnt/nvixSalesQuotationAction!getSalesQuotationItemJson.action",salesQuotationDetailColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#searchSalesItemName').val();
   			name = Url.encode(name);
   	 		var id = $("#id").val();
   	 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   		},10,'1','0');
   	function goAudit(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vreport/nvixJobTodoAction!goAudit.action?id=' + id, "jobTodoForm", "流程审批", 750, 350, null, null, function() {
			loadContent('', '${nvix}/nvixnt/vixNtIndexAction!goListContent.action');
		});
	};
	
	function reject(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vreport/nvixJobTodoAction!reject.action?id=' + id, "是否驳回该申请?", null, null, function() {
			loadContent('', '${nvix}/nvixnt/vixNtIndexAction!goListContent.action');
		});
	};
	pageSetUp();
</script>