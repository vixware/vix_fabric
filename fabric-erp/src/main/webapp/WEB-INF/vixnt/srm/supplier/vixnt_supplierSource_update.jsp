<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-truck"></i> 供应商管理 <span onclick="">&gt; 供应商寻源</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('srm_supplier','${nvix}/nvixnt/vixntSupplierAction!goSourceList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>供应商</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="supplierForm">
				<input type="hidden" id="supplierId" name="supplier.id" value="${supplier.id}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="code" name="supplier.code" value="${supplier.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
						<div class="col-md-3">
							<input id="name" name="supplier.name" value="${supplier.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 拼音:</label>
						<div class="col-md-3">
							<input id="pinYin" name="supplier.pinYin" value="${supplier.pinYin}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 法人:</label>
						<div class="col-md-3">
							<input id="artificialPerson" name="supplier.artificialPerson" value="${supplier.artificialPerson}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 类型:</label>
						<div class="col-md-3">
							<select id="type" name="supplier.type" class="form-control validate[required]">
								<option value="GENERAL" <c:if test='${supplier.type eq "GENERAL"}'>selected="selected"</c:if>>普通供应商</option>
								<option value="AGREEMENT" <c:if test='${supplier.type eq "AGREEMENT"}'>selected="selected"</c:if>>协议供应商</option>
								<option value="OUTSOURCING" <c:if test='${supplier.type eq "OUTSOURCING"}'>selected="selected"</c:if>>委外供应商</option>
							</select>
						</div>
						<label class="col-md-2 control-label">所属行业:</label>
						<div class="col-md-3">
							<select id="industry" name="supplier.industry" class="form-control">
								<option value="1" <c:if test='${supplier.industry == "1"}'>selected="selected"</c:if>>零售</option>
								<option value="2" <c:if test='${supplier.industry == "2"}'>selected="selected"</c:if>>生产</option>
								<option value="3" <c:if test='${supplier.industry == "3"}'>selected="selected"</c:if>>服务</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">注册资金:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control validate[custom[number]]" id="registeredCapital" name="supplier.registeredCapital" value="${supplier.registeredCapital }" type="text"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="supplier.currencyType.id" class="form-control validate[required]">
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${supplier.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> <span class="text-danger">*</span>税号:
						</label>
						<div class="col-md-3">
							<input id="taxCode" name="supplier.taxCode" value="${supplier.taxCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> <span class="text-danger">*</span>分类:
						</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="treeMenu" class="input-group">
										<input id="supplierCategoryId" name="supplier.supplierCategory.id" value="${supplier.supplierCategory.id}" type="hidden"> <input id="supplierCategoryName" name="supplier.supplierCategory.name" type="text" onfocus="showICMenu(); return false;"
											value="${supplier.supplierCategory.name}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#supplierCategoryId').val('');$('#supplierCategoryName').val('');">清空</button>
										</div>
										<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="supplierCategoryTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<input id="contacts" name="supplier.contacts" value="${supplier.contacts}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 手机: </label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="cellphone" name="supplier.cellphone" value="${supplier.cellphone}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 电话:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="telephone" name="supplier.telephone" value="${supplier.telephone}" data-prompt-position="topLeft" class="form-control validate[custom[phone]]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"> 传真:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="portraiture" name="supplier.portraiture" value="${supplier.portraiture}" data-prompt-position="topLeft" class="form-control" type="text" /> <span class="input-group-addon"><i class="fa fa-fax"></i> </span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> <span class="text-danger">*</span>状态:
						</label>
						<div class="col-md-3">
							<select id="status" name="supplier.status" class="form-control validate[required]" disabled="disabled">
								<option value="3" <c:if test='${supplier.status == "3"}'>selected="selected"</c:if>>正式</option>
								<option value="1" <c:if test='${supplier.status == "1"}'>selected="selected"</c:if>>待建档</option>
								<option value="2" <c:if test='${supplier.status == "2"}'>selected="selected"</c:if>>待评估</option>
								<option value="4" <c:if test='${supplier.status == "4"}'>selected="selected"</c:if>>无效</option>
							</select>
						</div>
						<label class="col-md-2 control-label">等级:</label>
						<div class="col-md-3">
							<select id="supplierLevel" name="supplier.supplierLevel.id" class="form-control">
								<c:forEach items="${supplierLevelList}" var="entity">
									<option value="${entity.id}" <c:if test='${supplier.supplierLevel.id eq entity.id}'>selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="supplier.memo" class="form-control validate[chinese]" rows="4">${supplier.memo }</textarea>
						</div>
					</div>
					
					<legend>其他信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"> 负责采购员编码:</label>
						<div class="col-md-3">
							<input id="purchaserCode" name="supplier.purchaserCode" value="${supplier.purchaserCode}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 采购员:</label>
						<div class="col-md-3">
							<input id="purchaser" name="supplier.purchaser" value="${supplier.purchaser}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 成本中心编码:</label>
						<div class="col-md-3">
							<input id="costCenterCode" name="supplier.costCenterCode" value="${supplier.costCenterCode}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 成本中心:</label>
						<div class="col-md-3">
							<input id="costCenter" name="supplier.costCenter" value="${supplier.costCenter}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 委外仓库:</label>
						<div class="col-md-3">
							<input id="outSourcingWarehouse" name="supplier.outSourcingWarehouse" value="${supplier.outSourcingWarehouse}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 委外货位:</label>
						<div class="col-md-3">
							<input id="outSourcingPositon" name="supplier.outSourcingPositon" value="${supplier.outSourcingPositon}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">是否为委外供应:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isOutSourcing == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isOutSourcing" class="validate" <s:if test='supplier.isOutSourcing == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isOutSourcing == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isOutSourcing" <s:if test='supplier.isOutSourcing == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">是否为服务提供商:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isServiceProvider == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isServiceProvider" class="validate" <s:if test='supplier.isServiceProvider == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isServiceProvider == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isServiceProvider" <s:if test='supplier.isServiceProvider == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">是否为国外供应商:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isForeignSupplier == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isForeignSupplier" class="validate" <s:if test='supplier.isForeignSupplier == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isForeignSupplier == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isForeignSupplier" <s:if test='supplier.isForeignSupplier == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">是否直接提供产品:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isDirectltProvideProduct == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isDirectltProvideProduct class="validate" <s:if test='supplier.isDirectltProvideProduct == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isDirectltProvideProduct == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isDirectltProvideProduct" <s:if test='supplier.isDirectltProvideProduct == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">是否齐批接收:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isTotalBatchRecieve == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isTotalBatchRecieve" class="validate" <s:if test='supplier.isTotalBatchRecieve == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isTotalBatchRecieve == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isTotalBatchRecieve" <s:if test='supplier.isTotalBatchRecieve == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">是否单价接收:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isNeedUnitPrice == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isNeedUnitPrice" class="validate" <s:if test='supplier.isNeedUnitPrice == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isNeedUnitPrice == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isNeedUnitPrice" <s:if test='supplier.isNeedUnitPrice == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">是否进行IQC检验:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isNeedIQC == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isNeedIQC" class="validate" <s:if test='supplier.isNeedIQC == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isNeedIQC == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isNeedIQC" <s:if test='supplier.isNeedIQC == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label">超交处理方式:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.exceedDealType == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.exceedDealType" class="validate" <s:if test='supplier.exceedDealType == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.exceedDealType == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.exceedDealType" <s:if test='supplier.exceedDealType == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 超交百分比:</label>
						<div class="col-md-3">
							<input id="exceedPercent" name="supplier.exceedPercent" value="${supplier.exceedPercent}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 超交数量:</label>
						<div class="col-md-3">
							<input id="exceedAmount" name="supplier.exceedAmount" value="${supplier.exceedAmount}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">超交是否付款:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test='supplier.isPayByExceed == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplier.isPayByExceed" class="validate" <s:if test='supplier.isPayByExceed == "1"'>checked="checked"</s:if>>是
								</label> <label class="btn btn-default <s:if test='supplier.isPayByExceed == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplier.isPayByExceed" <s:if test='supplier.isPayByExceed == "0"'>checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group" id="attachmentsTabsId">
						<!-- <label class="col-md-2 control-label"></label> -->
						<div class="col-md-12">
							<div class="jarviswidget jarviswidget-color-darken">
								<header style="height: 1px; border-color: #ddd !important"></header>
								<div>
									<div id="salesOrderRightContent" class="widget-body no-padding">
										<div class="jarviswidget" id="salesOrderDetailTabs" style="margin: 0; padding: 12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
											<header>
												<ul class="nav nav-tabs pull-left in">
													<li class="active"><a data-toggle="tab" href="#orderDetailTab" onclick="supplierAttachmentsTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">附件</span>
													</a></li>
													<li><a data-toggle="tab" href="#supplierBankTab" onclick="supplierBankInfoTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">银行信息</span>
													</a></li>
													<li><a data-toggle="tab" href="#supplierAptitudeTab" onclick="supplierAptitudeInfoTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">资质信息</span>
													</a></li>
													<li><a data-toggle="tab" href="#supplierAccountingTab" onclick="supplierAccountingInfoTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">财务信息</span>
													</a></li>
													<li><a data-toggle="tab" href="#supplierCreditTab" onclick="supplierCreditTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">信用</span>
													</a></li>
													<li><a data-toggle="tab" href="#supplierIndicatorsTab" onclick="supplierIndicatorsTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">指标</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="orderDetailTab">
														<div id="orderDetailSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="searchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierAttachmentsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#searchName').val('');supplierAttachmentsTable.ajax.reload();" type="button" class="btn btn-default">
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
														<table id="supplierAttachmentsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="supplierBankTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="supplierBankInfoSearchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierBankInfoTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#supplierBankInfoSearchName').val('');supplierBankInfoTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addSupplierBankInfo();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="supplierBankInfoId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="supplierAptitudeTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="supplierAptitudeInfoSearchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierAptitudeInfoTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#supplierAptitudeInfoSearchName').val('');supplierAptitudeInfoTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addSupplierAptitudeInfo();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="supplierAptitudeInfoId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="supplierAccountingTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="supplierAccountingSearchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierAccountingInfoTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#supplierAccountingSearchName').val('');supplierAccountingInfoTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addSupplierAccounting();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="supplierAccountingId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="supplierCreditTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="supplierCreditSearchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierCreditTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#supplierCreditSearchName').val('');supplierCreditInfoTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addSupplierCredit();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="supplierCreditId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<div class="tab-pane no-padding" id="supplierIndicatorsTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="supplierIndicatorsSearchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierIndicatorsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#supplierIndicatorsSearchName').val('');supplierIndicatorsTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addSupplierIndicators();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="supplierIndicatorsId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button class="btn btn-success"  type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('srm_supplier','${nvix}/nvixnt/vixntSupplierAction!goSourceList.action');">
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
	/* $(function(){
		var status = $("#status").val();
		if(status == '1'){
			$("#attachmentsTabsId").attr("style","display: none;");
		}
	}); */
	pageSetUp();
	$("#supplierForm").validationEngine();
	function saveOrUpdate() {
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateSourceSupplier.action",
			dataType : "text",
			success : function(id) {
				loadContent('srm_supplier', '${nvix}/nvixnt/vixntSupplierAction!goSourceList.action');
			}
			});
		} else {
			return false;
		}
	};
	//选择人员
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	};
	var supplierAttachmentsColumns =  [ {
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "上传路径",
		"width" : "50%",
		"data" : function(data) {
			return data.name;
		}
	}, {
		"title" : "上传时间",
		"width" : "20%",			
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierAttachmentsTable = initDataTable("supplierAttachmentsTableId", "${nvix}/nvixnt/vixntSupplierAction!getAttachmentsList.action", supplierAttachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $('#searchName').val();
		searchName = Url.encode(searchName);
		var id = $("#supplierId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":searchName
		};
	},10,"0");
	
	
	var supplierBankInfoColumns =  [ {
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "开户银行代码",
		"width" : "50%",
		"data" : function(data) {
			return data.bankCode;
		}
	}, {
		"title" : "开户银行",
		"width" : "20%",			
		"data" : function(data) {
			return data.bankName;
		}
	},{
		"title" : "银行账号",
		"width" : "20%",			
		"data" : function(data) {
			return data.bankAccount;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteSupplierBankInfoById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierBankInfoTable = initDataTable("supplierBankInfoId", "${nvix}/nvixnt/vixntSupplierAction!getSupplierBankInfoList.action", supplierBankInfoColumns, function(page, pageSize, orderField, orderBy) {
		var supplierBankInfoSearchName = $('#supplierBankInfoSearchName').val();
		supplierBankInfoSearchName = Url.encode(supplierBankInfoSearchName);
		var id = $("#supplierId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":supplierBankInfoSearchName
		};
	},10,"0");
	
	var supplierAptitudeInfoColumns =  [ {
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "资质名称",
		"width" : "20%",
		"data" : function(data) {
			return data.name;
		}
	}, {
		"title" : "资质文件",
		"width" : "15%",
		"data" : function(data) {
			return data.files;
		}
	}, {
		"title" : "资质有效期",
		"width" : "10%",			
		"data" : function(data) {
			return data.validPeriod;
		}
	},{
		"title" : "资质描述",
		"width" : "30%",			
		"data" : function(data) {
			return data.description;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteSupplierAptitudeInfoById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierAptitudeInfoTable = initDataTable("supplierAptitudeInfoId", "${nvix}/nvixnt/vixntSupplierAction!getSupplierAptitudeInfoList.action", supplierAptitudeInfoColumns, function(page, pageSize, orderField, orderBy) {
		var supplierAptitudeInfoSearchName = $('#supplierAptitudeInfoSearchName').val();
		supplierAptitudeInfoSearchName = Url.encode(supplierAptitudeInfoSearchName);
		var id = $("#supplierId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":supplierAptitudeInfoSearchName
		};
	},10,"0");
	var supplierAccountingInfoColumns =  [ {
		"title" : "编号",
		"width" : "5%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "总账类型",
		"width" : "10%",
		"data" : function(data) {
			return data.generalAccountCatalog;
		}
	}, {
		"title" : "付款条件",
		"width" : "10%",
		"data" : function(data) {
			return data.payCondition;
		}
	}, {
		"title" : "付款类型",
		"width" : "10%",			
		"data" : function(data) {
			return data.payType;
		}
	},{
		"title" : "付款方式",
		"width" : "10%",			
		"data" : function(data) {
			return data.payStyle;
		}
	},{
		"title" : "付款对象",
		"width" : "10%",			
		"data" : function(data) {
			return data.payTarget;
		}
	},{
		"title" : "付款期限",
		"width" : "10%",			
		"data" : function(data) {
			return data.limited;
		}
	},{
		"title" : "发票抬头",
		"width" : "10%",			
		"data" : function(data) {
			return data.invoiceHeader;
		}
	},{
		"title" : "增值税开户银行",
		"width" : "10%",			
		"data" : function(data) {
			return data.vaBank;
		}
	},{
		"title" : "审核人",
		"width" : "10%",			
		"data" : function(data) {
			return data.checked;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteAccountingById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierAccountingInfoTable = initDataTable("supplierAccountingId", "${nvix}/nvixnt/vixntSupplierAction!getSupplierAccountingInfoList.action", supplierAccountingInfoColumns, function(page, pageSize, orderField, orderBy) {
		var supplierAccountingSearchName = $('#supplierAccountingSearchName').val();
		supplierAccountingSearchName = Url.encode(supplierAccountingSearchName);
		var id = $("#supplierId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":supplierAccountingSearchName
		};
	},10,"0");
	var supplierCreditColumns =  [ {
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "信用等级",
		"width" : "10%",
		"data" : function(data) {
			return data.creditLevel;
		}
	}, {
		"title" : "信用额度",
		"width" : "12%",
		"data" : function(data) {
			return data.creditAmount;
		}
	}, {
		"title" : "折扣率",
		"width" : "8%",			
		"data" : function(data) {
			return data.discount;
		}
	},{
		"title" : "付款条件",
		"width" : "10%",			
		"data" : function(data) {
			return data.payCondition;
		}
	},{
		"title" : "最后交易金额",
		"width" : "10%",			
		"data" : function(data) {
			return data.lastDealAmount;
		}
	},{
		"title" : "最后交易日期",
		"width" : "10%",			
		"data" : function(data) {
			return data.lastDealTimeStr;
		}
	}, {
		"title" : "操作",
		"width" : "5%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteCreditInfoById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierCreditTable = initDataTable("supplierCreditId", "${nvix}/nvixnt/vixntSupplierAction!getSupplierCreditInfoList.action", supplierCreditColumns, function(page, pageSize, orderField, orderBy) {
		var supplierCreditSearchName = $('#supplierCreditSearchName').val();
		supplierCreditSearchName = Url.encode(supplierCreditSearchName);
		var id = $("#supplierId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":supplierCreditSearchName
		};
	},10,"0");
	var supplierIndicatorsColumns =  [ {
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
		return "";
		}
	}, {
		"title" : "指标名称",
		"width" : "20%",
		"data" : function(data) {
			return "";
		}
	}, {
		"title" : "权重",
		"width" : "20%",
		"data" : function(data) {
			return "";
		}
	}, {
		"title" : "配额数量",
		"width" : "20%",			
		"data" : function(data) {
			return "";
		}
	},{
		"title" : "金额限度",
		"width" : "20%",			
		"data" : function(data) {
			return "";
		}
	}, {
		"title" : "操作",
		"width" : "10%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteIndicatorsById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierIndicatorsTable = initDataTable("supplierIndicatorsId", "${nvix}/nvixnt/vixntSupplierAction!getSupplierIndicatorsList.action", supplierIndicatorsColumns, function(page, pageSize, orderField, orderBy) {
		var supplierIndicatorsSearchName = $('#supplierIndicatorsSearchName').val();
		supplierIndicatorsSearchName = Url.encode(supplierIndicatorsSearchName);
		var id = $("#supplierId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":supplierIndicatorsSearchName
		};
	},10,"0");
//删除附件
function deleteUploaderById(id) {
	deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!deleteUploaderById.action?id=' + id, '是否删除附件?', supplierAttachmentsTable);
};
function fileChange() {
	if(!$("#supplierId").val()){
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0]=="1"){
					$("#supplierId").val(r[1]);
					uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateAttachments.action?id="+r[1], "fileToUpload", "file", function(data) {
						supplierAttachmentsTable.ajax.reload();
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
		uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateAttachments.action?id="+$("#supplierId").val(), "fileToUpload", "file", function(data) {
			supplierAttachmentsTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	}
};
//保存银行
function addSupplierBankInfo(id){
	if(!$("#supplierId").val()){
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#supplierId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierBankInfo.action?supplierId="+r[1]+"&id="+id,"supplierBankInfoForm","银行信息",750,350,supplierBankInfoTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierBankInfo.action?supplierId="+$("#supplierId").val()+"&id="+id,"supplierBankInfoForm","银行信息",750,350,supplierBankInfoTable);
	}
}
function deleteSupplierBankInfoById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!deleteSupplierBankInfoById.action?id=' + id, '是否删除银行信息?', supplierBankInfoTable);
}
//保存资质
function addSupplierAptitudeInfo(id){
	if(!$("#supplierId").val()){
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#supplierId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierAptitudeInfo.action?supplierId="+r[1]+"&id="+id,"supplierAptitudeInfoForm","银行信息",750,350,supplierAptitudeInfoTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierAptitudeInfo.action?supplierId="+$("#supplierId").val()+"&id="+id,"supplierAptitudeInfoForm","银行信息",750,350,supplierAptitudeInfoTable);
	}
}
function deleteSupplierAptitudeInfoById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!deleteAptitudeInfoById.action?id=' + id, '是否删除资质信息?', supplierAptitudeInfoTable);
}
//保存财务信息
function addSupplierAccounting(id){
	if(!$("#supplierId").val()){
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#supplierId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierAccountingInfo.action?supplierId="+r[1]+"&id="+id,"supplierAccountingInfoForm","财务信息",750,450,supplierAccountingInfoTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierAccountingInfo.action?supplierId="+$("#supplierId").val()+"&id="+id,"supplierAccountingInfoForm","财务信息",750,450,supplierAccountingInfoTable);
	}
}
function deleteAccountingById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!deleteAccountingById.action?id=' + id, '是否删除财务信息?', supplierAccountingInfoTable);
}
//保存信用信息
function addSupplierCredit(id){
	if(!$("#supplierId").val()){
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#supplierId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierCreditInfo.action?supplierId="+r[1]+"&id="+id,"supplierCreditForm","财务信息",750,350,supplierCreditTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierCreditInfo.action?supplierId="+$("#supplierId").val()+"&id="+id,"supplierCreditForm","财务信息",750,350,supplierCreditTable);
	}
}
function deleteCreditInfoById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!deleteCreditInfoById.action?id=' + id, '是否删除信用信息?', supplierCreditTable);
}
//保存指标
function addSupplierIndicators(id){
	if(!$("#supplierId").val()){
		if ($("#supplierForm").validationEngine('validate')) {
			$("#supplierForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntSupplierAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#supplierId").val(r[1]);
						openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierIndicators.action?supplierId="+r[1]+"&id="+id,"supplierIndicatorsForm","财务信息",750,350,supplierIndicatorsTable);
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
		openSaveOrUpdateWindow("${nvix}/nvixnt/vixntSupplierAction!goSaveOrUpdateSupplierIndicators.action?supplierId="+$("#supplierId").val()+"&id="+id,"supplierIndicatorsForm","财务信息",750,350,supplierIndicatorsTable);
	}
}
function deleteIndicatorsById(id){
	deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAction!deleteIndicatorsById.action?id=' + id, '是否删除指标信息?', supplierIndicatorsTable);
}
var showICMenu = initDropListTree("treeMenu","${nvix}/nvixnt/vixntSupplierCategoryAction!findTreeToJson.action","supplierCategoryId","supplierCategoryName","supplierCategoryTree","treeMenuContent");
</script>