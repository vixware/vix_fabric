<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 合同管理 <span onclick="">&gt; 劳动合同管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('srm_contract','${nvix}/nvixnt/contract/vixntLaborContractAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>劳动合同</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="contractForm">
				<input type="hidden" id="contractId" name="contract.id" value="${contract.id}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主合同编码:</label>
						<div class="col-md-3">
							<input id="masterContractCoding" name="contract.masterContractCoding" value="${contract.masterContractCoding}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 所属合同组:</label>
						<div class="col-md-3">
							<select id="contractGroupTypeId" name="contract.contractGroupType.id" class="form-control validate[required]">
									<c:forEach items="${contractGroupTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contract.contractGroupType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 合同编码:</label>
						<div class="col-md-3">
							<input id="contractCode" name="contract.contractCode" value="${contract.contractCode}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 合同名称:</label>
						<div class="col-md-3">
							<input id="contractName" name="contract.contractName" value="${contract.contractName}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 类型:</label>
						<div class="col-md-3">
							<select id="contractTypeCombineId" name="contract.contractTypeCombine.id" class="form-control validate[required]">
								<c:forEach items="${contractTypeCombineList}" var="entity">
									<option value="${entity.id}" <c:if test="${contract.contractTypeCombine.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">合同性质:</label>
						<div class="col-md-3">
							<select id="contractNatureTypeId" name="contract.contractNatureType.id" class="form-control validate[required]">
									<c:forEach items="${contractNatureTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contract.contractNatureType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">所属行业:</label>
						<div class="col-md-3">
							<select id="viewIndustryTypeId" name="contract.viewIndustryType.id" class="form-control validate[required]">
									<c:forEach items="${viewIndustryList}" var="entity">
										<option value="${entity.id}" <c:if test="${contract.viewIndustryType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 合同签订日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="signDate" name="contract.signDate" value="${contract.signDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2"><span class="text-danger">*</span> 合同开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="contractStartTime" name="contract.contractStartTime" value="${contract.contractStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="control-label col-md-2"><span class="text-danger">*</span> 合同结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="contractEndTime" name="contract.contractEndTime" value="${contract.contractEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 经办人:</label>
						<div class="col-md-3">
							<input id="operator" name="contract.operator" value="${contract.operator}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 谈判人:</label>
						<div class="col-md-3">
							<input id="negotiator" name="contract.negotiator" value="${contract.negotiator}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 审批人:</label>
						<div class="col-md-3">
							<input id="approval" name="contract.approval" value="${contract.approval}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 审批日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="approvalDate" name="contract.approvalDate" value="${contract.approvalDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 合同履行状态:</label>
						<div class="col-md-3">
							<select id="modeTypeId" name="contract.modeType.id" class="form-control validate[required]">
									<c:forEach items="${modeTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contract.modeType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">所属部门:</label>
						<div class="col-md-3">
							<input id="viewDepartment" name="contract.viewDepartment" value="${contract.viewDepartment}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<legend>合同方</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"> <span class="text-danger">*</span> 甲方:</label>
						<div class="col-md-3">
							<input id="firstParty" name="contract.firstParty" value="${contract.firstParty}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> <span class="text-danger">*</span> 乙方:</label>
						<div class="col-md-3">
							<input id="secondParty" name="contract.secondParty" value="${contract.secondParty}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 甲方联系人:</label>
						<div class="col-md-3">
							<input id="firstPartyContact" name="contract.firstPartyContact" value="${contract.firstPartyContact}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 乙方联系人:</label>
						<div class="col-md-3">
							<input id="bcontact" name="contract.bcontact" value="${contract.bcontact}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 甲方电话:</label>
						<div class="col-md-3">
							<input id="firstPartyPhone" name="contract.firstPartyPhone" value="${contract.firstPartyPhone}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 乙方电话:</label>
						<div class="col-md-3">
							<input id="bphone" name="contract.bphone" value="${contract.bphone}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 甲方邮件:</label>
						<div class="col-md-3">
							<input id="firstPartyEmail" name="contract.firstPartyEmail" value="${contract.firstPartyEmail}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 乙方邮件:</label>
						<div class="col-md-3">
							<input id="email" name="contract.email" value="${contract.email}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<legend>金额</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"> 币种:</label>
						<div class="col-md-3">
							<select id="currency" name="contract.currency" class="validate[required] form-control">
									<option value="">请选择</option>
									<option value="1">美元</option>
									<option value="2">人民币</option>
									<option value="3">欧元</option>
									<option value="4">日元</option>
							</select>
						</div>
					<%-- 	<label class="col-md-2 control-label">汇率:</label>
						<div class="col-md-3">
							<select id="exchangeRateId" name="contract.exchangeRate.id" class="form-control validate[required]">
								<c:forEach items="${exchangeRateList}" var="entity">
									<option value="${entity.id}" <c:if test="${contract.exchangeRate.id == entity.id}">selected="selected"</c:if>>${entity.exchangeRate}</option>
								</c:forEach>
							</select>
						</div> --%>
					</div>
				 	<legend>合同内容</legend>
					<div class="form-group">
					<label class="col-md-1 control-label"></label>
						<div class="col-md-8">
							<textarea id="mainContent" name="mainContent">${contract.mainContent}</textarea> <script type="text/javascript" src="${nvix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
							 var contents = KindEditor.create('textarea[name="mainContent"]',
								{basePath:'${nvix}/plugin/KindEditor/',
									width:950,
									height:200,
									cssPath : '${nvix}/plugin/KindEditor/plugins/code/prettify.css',
									uploadJson : '${nvix}/plugin/KindEditor/jsp/upload_json.jsp',
									fileManagerJson : '${nvix}/plugin/KindEditor/jsp/file_manager_json.jsp',
									allowFileManager : true 
								}
				 				);
						  </script>
					  </div>
					</div>
					<div class="form-group">
						<!-- <label class="col-md-2 control-label"></label> -->
						<div class="col-md-12">
							<div class="jarviswidget jarviswidget-color-darken">
								<header style="height: 1px; border-color: #ddd !important"></header>
								<div>
									<div id="salesOrderRightContent" class="widget-body no-padding">
										<div class="jarviswidget" id="salesOrderDetailTabs" style="margin: 0; padding: 12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
											<header>
												<ul class="nav nav-tabs pull-left in">
													<li class="active"><a data-toggle="tab" href="#contractWarningTab" onclick="contractWarningTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">合同预警</span>
													</a></li>
													<li><a data-toggle="tab" href="#applicationFormTab" onclick="applicationFormTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">合同审批</span>
													</a></li>
													<li><a data-toggle="tab" href="#contractTemplateTab" onclick="contractTemplateTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">附件</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="contractWarningTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="contractWarningName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="contractWarningTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#contractWarningName').val('');contractWarningTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addcontractWarning();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="contractWarningTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="applicationFormTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="applicationFormName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="applicationFormTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#applicationFormName').val('');applicationFormTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addapplicationForm();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="applicationFormTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="contractTemplateTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="contractTemplateName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="contractTemplateTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#contractTemplateName').val('');contractTemplateTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class="listMenu-float-right">
																	<div class="form-group" style="margin: 0 5px;">
																		<div class="listMenu-float-right">
																			<input type="file" id="fileToUpload" name="fileToUpload" onchange="fileChange();" value="上传附件" style="width: 100%;">
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<table id="contractTemplateTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('srm_contract','${nvix}/nvixnt/contract/vixntLaborContractAction!goList.action');">
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
	pageSetUp();
	var contractWarningColumns =  [ {
		"title" : "编号",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "预警信息",
		"data" : function(data) {
			return data.warnningContent;
		}
	}, {
		"title" : "预警时间",
		"data" : function(data) {
			return data.warnningTimeStr;
		}
	}, {
		"title" : "预警类型",
		"data" : function(data) {
			return data.warnningType;
		}
	}, {
		"title" : "操作",
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteContractWarningById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var contractWarningTable = initDataTable("contractWarningTableId", "${nvix}/nvixnt/contract/vixntLaborContractAction!getContractWarningtList.action", contractWarningColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#contractId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		};
	},10,"0");
	var applicationFormColumns =  [ {
		"title" : "编号",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "审批人",
		"data" : function(data) {
			return data.underTakePerson;
		}
	}, {
		"title" : "审批时间",
		"data" : function(data) {
			return data.underTakeDateStr;
		}
	}, {
		"title" : "操作",
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteApplicationFormById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var applicationFormTable = initDataTable("applicationFormTableId", "${nvix}/nvixnt/contract/vixntLaborContractAction!getApplicationFormList.action", applicationFormColumns, function(page, pageSize, orderField, orderBy) {
		var applicationFormName = $('#applicationFormName').val();
		applicationFormName = Url.encode(applicationFormName);
		var id = $("#contractId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":applicationFormName
		};
	},10,"0");
	var contractTemplateColumns =  [ {
		"title" : "编号",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "附件名称",
		"data" : function(data) {
			return data.name;
		}
	}, {
		"title" : "上传时间",
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"title" : "操作",
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletecontractTemplateById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var contractTemplateTable = initDataTable("contractTemplateTableId", "${nvix}/nvixnt/contract/vixntLaborContractAction!getContractTemplateList.action", contractTemplateColumns, function(page, pageSize, orderField, orderBy) {
		var contractTemplateName = $('#contractTemplateName').val();
		contractTemplateName = Url.encode(contractTemplateName);
		var id = $("#contractId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":contractTemplateName
		};
	},10,"0");
//保存预警
function addcontractWarning(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntLaborContractAction!goSaveOrUpdateContractWarning.action?contractId="+r[1]+"&id="+id,"contractWarningForm","合同预警",750,350,contractWarningTable);
					}else{
						showMessage(r[1]);
						return;
					}
				}
				});
		}else{
			return false;
		}
	}else{
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntLaborContractAction!goSaveOrUpdateContractWarning.action?contractId="+$("#contractId").val()+"&id="+id,"contractWarningForm","合同预警",750,350,contractWarningTable);
	}
}
function deleteContractWarningById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntLaborContractAction!deleteContractWarningById.action?id=' + id, '是否删除合同预警?', contractWarningTable);
}
//保存审批
function addapplicationForm(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntLaborContractAction!goSaveOrUpdateApplicationForm.action?contractId="+r[1]+"&id="+id,"applicationFormForm","合同审批",750,350,applicationFormTable);
					}else{
						showMessage(r[1]);
						return;
					}
				}
				});
		}else{
			return false;
		}
	}else{
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntLaborContractAction!goSaveOrUpdateApplicationForm.action?contractId="+$("#contractId").val()+"&id="+id,"applicationFormForm","合同审批",750,350,applicationFormTable);
	}
}
function deleteApplicationFormById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntLaborContractAction!deleteApplicationFormById.action?id=' + id, '是否删除合同审批?', applicationFormTable);
}
function deletecontractTemplateById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntLaborContractAction!deleteUploaderById.action?id=' + id, '是否删除附件?', contractTemplateTable);
}
$("#contractForm").validationEngine();
function saveOrUpdate() {
	if ($("#contractForm").validationEngine('validate')) {
		$("#contractForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			loadContent('srm_supplier', '${nvix}/nvixnt/contract/vixntLaborContractAction!goList.action');
		}
		});
	} else {
		return false;
	}
};


//上传附件
function fileChange() {
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0]=="1"){
					$("#contractId").val(r[1]);
					uploadFileOrImage("contractFileForm", "${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdateAttachments.action?id="+r[1], "fileToUpload", "file", function(data) {
						contractTemplateTable.ajax.reload();
						showMessage("文件上传成功!");
					});
				}else{
					showMessage(r[1]);
					return;
				}
			}
			});
		} else {
			return false;
		}
	}else{
		uploadFileOrImage("contractFileForm", "${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdateAttachments.action?id="+$("#contractId").val(), "fileToUpload", "file", function(data) {
			contractTemplateTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	}
};
</script>