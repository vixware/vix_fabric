<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 合同管理 <span onclick="">&gt;新增合同</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('srm_contract','${nvix}/nvixnt/contract/vixntContractAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>合同信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="contractForm">
				<input type="hidden" id="contractId" name="contract.id" value="${contract.id}" />
				<input type="hidden" id="contractType" name="contract.contractType" value="${contract.contractType}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">主合同编码:</label>
						<div class="col-md-3">
							<input id="masterContractCoding" name="contract.masterContractCoding" value="${contract.masterContractCoding}" data-prompt-position="topLeft" class="form-control" type="text" />
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
						<input id="contractCode" name="contract.contractCode" value="${contract.contractCode}" data-prompt-position="topLeft" class="form-control" type="hidden" />
						<label class="col-md-2 control-label"> 合同名称:</label>
						<div class="col-md-3">
							<input id="contractName" name="contract.contractName" value="${contract.contractName}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="projectId" type="hidden" name="contract.crmProject.id" value="${contract.crmProject.id}" />
								<input id="projectName" name="contract.crmProject.subject" value="${contract.crmProject.subject}" class="form-control" type="text" readonly="readonly" />
								<div class="input-group-btn">
									<button onclick="goChooseProject();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button onclick="$('#projectId').val('');$('#projectName').val('');" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
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
						<label class="col-md-2 control-label">合同签订日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="signDate" name="contract.signDate" value="${contract.signDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">合同开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="contractStartTime" name="contract.contractStartTime" value="${contract.contractStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="control-label col-md-2">合同结束日期:</label>
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
					<div class="form-group">
						<label class="col-md-2 control-label">合同总数量:</label>
						<div class="col-md-3">
							<input id="contractTotalQuantity" name="contract.contractTotalQuantity" value="${contract.contractTotalQuantity}" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" type="text" />
						</div>
						<label class="col-md-2 control-label">合同执行数量:</label>
						<div class="col-md-3">
							<input id="numberContractExecution" name="contract.numberContractExecution" value="${contract.numberContractExecution}" data-prompt-position="topLeft" class="form-control validate[custom[integer]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 启用阶段:</label>
						<div class="col-md-3">
							<select id="enableStageTypeId" name="contract.enableStageType.id" class="form-control validate[required]">
									<c:forEach items="${enableStageTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contract.enableStageType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">合同阶段组:</label>
						<div class="col-md-3">
							<select id="contractStageGroupTypeId" name="contract.contractStageGroupType.id" class="form-control validate[required]">
									<c:forEach items="${contractStageGroupTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contract.contractStageGroupType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
							</select>
						</div>
					</div>
					<legend>合同方</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"> 甲方:</label>
						<div class="col-md-3">
							<input id="firstParty" name="contract.firstParty" value="${contract.firstParty}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 乙方:</label>
						<div class="col-md-3">
							<%-- <input id="secondParty" name="contract.secondParty" value="${contract.secondParty}" data-prompt-position="topLeft" class="form-control" type="text" /> --%>
							<div class="input-group">
								<input id="secondPartyId" type="hidden" name="contract.secondPartyId" value="${contract.secondPartyId}" />
								<input id="secondPartyName" name="contract.secondParty" value="${contract.secondParty}" class="form-control validate[required]" type="text" readonly="readonly" />
								<div class="input-group-btn">
									<button onclick="goChooseSecondParty();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i>
									</button>
									<button onclick="$('#secondPartyId').val('');$('#secondPartyName').val('');$('#cardEntityPayMoney').val('');" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 甲方单位名称:</label>
						<div class="col-md-3">
							<input id="partyUnitName" name="contract.partyUnitName" value="${contract.partyUnitName}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 乙方单位名称:</label>
						<div class="col-md-3">
							<input id="bunitName" name="contract.bunitName" value="${contract.bunitName}" data-prompt-position="topLeft" class="form-control" type="text" />
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
						<label class="col-md-2 control-label">汇率:</label>
						<div class="col-md-3">
							<select id="exchangeRateId" name="contract.exchangeRate.id" class="form-control validate[required]">
								<c:forEach items="${exchangeRateList}" var="entity">
									<option value="${entity.id}" <c:if test="${contract.exchangeRate.id == entity.id}">selected="selected"</c:if>>${entity.exchangeRate}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 合同总金额:</label>
						<div class="col-md-3">
							<input id="totalAmount" name="contract.totalAmount" value="${contract.totalAmount}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 合同执行金额:</label>
						<div class="col-md-3">
							<input id="contractExecutionMoney" name="contract.contractExecutionMoney" value="${contract.contractExecutionMoney}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 预付款金额:</label>
						<div class="col-md-3">
							<input id="prepaymentAmount" name="contract.prepaymentAmount" value="${contract.prepaymentAmount}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 未结金额:</label>
						<div class="col-md-3">
							<input id="outstandingAmounts" name="contract.outstandingAmounts" value="${contract.outstandingAmounts}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 质保金计算方式:</label>
						<div class="col-md-3">
							<input id="retentionCalculation" name="contract.retentionCalculation" value="${contract.retentionCalculation}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 质保金开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="retentionsStartDate" name="contract.retentionsStartDate" value="${contract.retentionsStartDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 质保金结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="retentionEndDate" name="contract.retentionEndDate" value="${contract.retentionEndDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"> 质保金比例（%）:</label>
						<div class="col-md-3">
							<input id="retentionRatio" name="contract.retentionRatio" value="${contract.retentionRatio}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 质保金额度:</label>
						<div class="col-md-3">
							<input id="warrantyAmount" name="contract.warrantyAmount" value="${contract.warrantyAmount}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 保修期:</label>
						<div class="col-md-3">
							<input id="warranty" name="contract.warranty" value="${contract.warranty}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
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
													<li class="active"><a data-toggle="tab" href="#contractChildItemTab" onclick="contractChildItemTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">合同子项</span>
													</a></li>
													<li><a data-toggle="tab" href="#contractSubjectTab" onclick="contractSubjectTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">合同标的</span>
													</a></li>
													<li><a data-toggle="tab" href="#contractWarningTab" onclick="contractWarningTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">合同预警</span>
													</a></li>
													<li><a data-toggle="tab" href="#applicationFormTab" onclick="applicationFormTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">合同审批</span>
													</a></li>
													<li><a data-toggle="tab" href="#priceConditionsTab" onclick="priceConditionsTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">价格条件</span>
													</a></li>
													<c:if test="${contract.contractType == 3 || contract.contractType == 4}">
														<li><a data-toggle="tab" href="#backSectionPlanTab" onclick="backSectionPlanTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">回款条件</span>
														</a></li>
													</c:if>
													<li><a data-toggle="tab" href="#contractTemplateTab" onclick="priceConditionsTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">附件</span>
													</a></li>
													
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="contractChildItemTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="contractChildItemName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="contractChildItemTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#contractChildItemName').val('');contractChildItemTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addcontractChildItem();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="contractChildItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="contractSubjectTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="contractSubjectName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="contractSubjectTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#contractSubjectName').val('');contractSubjectTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addcontractSubject();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="contractSubjectTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="contractWarningTab">
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
													<div class="tab-pane no-padding" id="priceConditionsTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="priceConditionsName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="priceConditionsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#priceConditionsName').val('');priceConditionsTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addpriceConditions();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="priceConditionsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="backSectionPlanTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="searchCustomerName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="backSectionPlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#searchCustomerName').val('');backSectionPlanTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addbackSectionPlan();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="backSectionPlanTableId" class="table table-striped table-bordered table-hover" width="100%">
															<thead>
																<tr>
																	<th width="8%">编号</th>
																	<th>金额</th>
																	<th>计划回款日期</th>
																	<th>订单</th>
																	<th>客户</th>
																	<th>期次</th>
																	<th>是否回款</th>
																	<th>所有人</th>
																	<th width="8%">操作</th>
																</tr>
															</thead>
														</table>
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
							<button class="btn btn-default" type="button" onclick="loadContent('srm_contract','${nvix}/nvixnt/contract/vixntContractAction!goList.action');">
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
	var contractChildItemColumns =  [ {
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "类型",
		"width" : "50%",
		"data" : function(data) {
			return data.contractType;
		}
	}, {
		"title" : "状态",
		"width" : "20%",			
		"data" : function(data) {
			return data.contractStatus;
		}
	},{
		"title" : "内容",
		"width" : "20%",			
		"data" : function(data) {
			return data.theContract;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteContractChildItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var contractChildItemTable = initDataTable("contractChildItemTableId", "${nvix}/nvixnt/contract/vixntContractAction!getcontractChildItemList.action", contractChildItemColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#contractId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		};
	},10,"0");
	
	
	var contractSubjectColumns =  [ {
		"title" : "编号",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "标的编码",
		"data" : function(data) {
			return data.subjectCode;
		}
	}, {
		"title" : "标的名称",
		"data" : function(data) {
			return data.subjectName;
		}
	}, {
		"title" : "来源",
		"data" : function(data) {
			return data.source;
		}
	},{
		"title" : "存货分类编码",
		"data" : function(data) {
			return data.stockClassificationCode;
		}
	},{
		"title" : "对应存货编码",
		"data" : function(data) {
			return data.correspondingInventoryCode;
		}
	},{
		"title" : "项目大类",
		"data" : function(data) {
			return data.projectCatalog;
		}
	},{
		"title" : "存货规格型号",
		"data" : function(data) {
			return data.inventoriesSpecification;
		}
	},{
		"title" : "无税原币单价",
		"data" : function(data) {
			return data.nnTOCurrencyPrice;
		}
	},{
		"title" : "含税原币单价",
		"data" : function(data) {
			return data.ttIOriginalCurrencyPrice;
		}
	},{
		"title" : "无税原币金额",
		"data" : function(data) {
			return data.nnTaxAmountOriginalCurrency;
		}
	},{
		"title" : "含税原币金额",
		"data" : function(data) {
			return data.ttATOriginalCurrency;
		}
	},{
		"title" : "执行数量",
		"data" : function(data) {
			return data.executionQuantity;
		}
	},{
		"title" : "执行无税金额原币",
		"data" : function(data) {
			return data.eeTAOriginalCurrency;
		}
	},{
		"title" : "执行含税金额原币",
		"data" : function(data) {
			return data.eeTAIncLriginalCurrency;
		}
	}, {
		"title" : "操作",
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteContractSubjectById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var contractSubjectTable = initDataTable("contractSubjectTableId", "${nvix}/nvixnt/contract/vixntContractAction!getContractSubjectList.action", contractSubjectColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#contractId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		};
	},10,"0");
	
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
			return data.warnningTime;
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
	var contractWarningTable = initDataTable("contractWarningTableId", "${nvix}/nvixnt/contract/vixntContractAction!getContractWarningtList.action", contractWarningColumns, function(page, pageSize, orderField, orderBy) {
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
			return data.underTakeDate;
		}
	}, {
		"title" : "操作",
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteApplicationFormById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var applicationFormTable = initDataTable("applicationFormTableId", "${nvix}/nvixnt/contract/vixntContractAction!getApplicationFormList.action", applicationFormColumns, function(page, pageSize, orderField, orderBy) {
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
	var priceConditionsColumns =  [ {
		"title" : "编号",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "开始数量",
		"data" : function(data) {
			return data.startQuantity;
		}
	}, {
		"title" : "截止数量",
		"data" : function(data) {
			return data.cutoffQuantity;
		}
	}, {
		"title" : "折扣",
		"data" : function(data) {
			return data.discount;
		}
	},{
		"title" : "开始数量",
		"data" : function(data) {
			return data.numberFrom;
		}
	},{
		"title" : "结束数量",
		"data" : function(data) {
			return data.numberto;
		}
	},{
		"title" : "采购价格条件",
		"data" : function(data) {
			return data.purchasepriceCondition;
		}
	}, {
		"title" : "操作",
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePriceConditionsById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var priceConditionsTable = initDataTable("priceConditionsTableId", "${nvix}/nvixnt/contract/vixntContractAction!getPriceConditionsList.action", priceConditionsColumns, function(page, pageSize, orderField, orderBy) {
		var priceConditionsName = $('#priceConditionsName').val();
		priceConditionsName = Url.encode(priceConditionsName);
		var id = $("#contractId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":priceConditionsName
		};
	},10,"0");
	var backSectionPlanColumns = [ {
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			if(data.amount != null){
				return data.amount;
			}else{
				return 0;
			}
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			return data.backSectionPlanDateStr;
		}
		},{
		"orderable" : false,
		"data" : function(data) {
			return data.salesOrder == null ? "" : data.salesOrder.title;
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			return data.customerAccount == null ? "" : data.customerAccount.name;
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			return data.stageNumber;
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			if(data.backSectionPlanStatus == 1){
				return "<span class='label label-success'>是</span>";
			}else{
				return "<span class='label label-info'>否</span>";
			}
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			return data.owner == null ? "" : data.owner.name;
		}
		}, {
		"orderable" : false,
		"data" : function(data) {
			if (null != data.id) {
				var update = "<a class='btn btn-xs btn-default' onclick=\"addbackSectionPlan('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' onclick=\"deleteBackSectionPlanById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
			}
			return '';
		}
		} ];

		var backSectionPlanTable = initDataTable("backSectionPlanTableId", "${nvix}/nvixnt/contract/vixntContractAction!getBackSectionPlanSingleList.action", backSectionPlanColumns, function(page, pageSize, orderField, orderBy) {
			var contractId = $("#contractId").val();
			var name = $("#searchCustomerName").val();
			name = Url.encode(name);
			return {
			"page" : page,
			"pageSize" : pageSize,
			"orderField" : orderField,
			"orderBy" : orderBy,
			"contractId" : contractId,
			"searchName" : name
			};
		});
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
	var contractTemplateTable = initDataTable("contractTemplateTableId", "${nvix}/nvixnt/contract/vixntContractAction!getContractTemplateList.action", contractTemplateColumns, function(page, pageSize, orderField, orderBy) {
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
//保存子项
function addcontractChildItem(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateContractChildItem.action?contractId="+r[1]+"&id="+id,"contractChildItemForm","合同子项",750,350,contractChildItemTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateContractChildItem.action?contractId="+$("#contractId").val()+"&id="+id,"contractChildItemForm","合同子项",750,350,contractChildItemTable);
	}
}
function deleteContractChildItemById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deleteContractChildItemById.action?id=' + id, '是否删除合同子项?', contractChildItemTable);
}
//保存标的
function addcontractSubject(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateContractSubject.action?contractId="+r[1]+"&id="+id,"contractSubjectForm","合同标的",750,550,contractSubjectTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateContractSubject.action?contractId="+$("#contractId").val()+"&id="+id,"contractSubjectForm","合同标的",750,550,contractSubjectTable);
	}
}
function deleteContractSubjectById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deleteContractSubjectById.action?id=' + id, '是否删除合同标的?', contractSubjectTable);
}
//保存预警
function addcontractWarning(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateContractWarning.action?contractId="+r[1]+"&id="+id,"contractWarningForm","合同预警",750,350,contractWarningTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateContractWarning.action?contractId="+$("#contractId").val()+"&id="+id,"contractWarningForm","合同预警",750,350,contractWarningTable);
	}
}
function deleteContractWarningById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deleteContractWarningById.action?id=' + id, '是否删除合同预警?', contractWarningTable);
}
//保存审批
function addapplicationForm(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateApplicationForm.action?contractId="+r[1]+"&id="+id,"applicationFormForm","合同审批",750,350,applicationFormTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateApplicationForm.action?contractId="+$("#contractId").val()+"&id="+id,"applicationFormForm","合同审批",750,350,applicationFormTable);
	}
}
function deleteApplicationFormById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deleteApplicationFormById.action?id=' + id, '是否删除合同审批?', applicationFormTable);
}
//保存价格条件
function addpriceConditions(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdatePriceConditions.action?contractId="+r[1]+"&id="+id,"priceConditionsForm","价格条件",750,350,priceConditionsTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdatePriceConditions.action?contractId="+$("#contractId").val()+"&id="+id,"priceConditionsForm","价格条件",750,350,priceConditionsTable);
	}
}
function deletePriceConditionsById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deletePriceConditionsById.action?id=' + id, '是否删除价格条件?', priceConditionsTable);
}
$("#contractForm").validationEngine();
function saveOrUpdate() {
	if ($("#contractForm").validationEngine('validate')) {
		$("#contractForm").ajaxSubmit({
		type : "post",
		url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
		dataType : "text",
		success : function(id) {
			loadContent('srm_supplier', '${nvix}/nvixnt/contract/vixntContractAction!goList.action');
		}
		});
	} else {
		return false;
	}
};
//回款条件
function addbackSectionPlan(id){
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#contractId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateBackSectionPlan.action?id="+id,"backSectionPlanForm","回款条件",750,550,backSectionPlanTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/contract/vixntContractAction!goSaveOrUpdateBackSectionPlan.action?id="+id,"backSectionPlanForm","回款条件",750,550,backSectionPlanTable);
	}
}
function deleteBackSectionPlanById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/contract/vixntContractAction!deleteBackSectionPlanById.action?id=' + id, '是否删除回款计划?', backSectionPlanTable);
}
//上传附件
function fileChange() {
	if(!$("#contractId").val()){
		if ($("#contractForm").validationEngine('validate')) {
			$("#contractForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0]=="1"){
					$("#contractId").val(r[1]);
					uploadFileOrImage("contractFileForm", "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdateAttachments.action?id="+r[1], "fileToUpload", "file", function(data) {
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
		uploadFileOrImage("contractFileForm", "${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdateAttachments.action?id="+$("#contractId").val(), "fileToUpload", "file", function(data) {
			contractTemplateTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	}
};
function goChooseSecondParty(){
	var type = $("#contractType").val();
	if(type){
		if(type == 1 || type == 2){
			chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'secondParty');
		}else if(type == 3 || type == 4){
			chooseListData('${nvix}/nvixnt/contract/vixntContractAction!goChooseCustomer.action', 'single', '选择客户', 'secondParty');
		}else if(type == 5){
			
		}else if(type == 6){
			chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择员工', 'secondParty');
		}
	}
}
function goChooseProject(){
	chooseListData('${nvix}/nvixnt/contract/vixntContractAction!goChooseProject.action', 'single', '选择项目', 'project');
}
</script>