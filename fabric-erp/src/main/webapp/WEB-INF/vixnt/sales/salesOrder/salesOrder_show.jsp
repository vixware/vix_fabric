<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt; 销售订单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
					<i class="fa fa-chevron-left"></i> 上一条
				</button>
				<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
					<i class="fa fa-chevron-right"></i> 下一条
				</button>
				<button class="btn btn-success" type="button" onclick="loadContent('','${nvix}/nvixnt/nvixSalesOrderAction!goSourceList.action?id='+$('#id').val());">
					<i class="fa fa-retweet"></i> 追溯
				</button>
				<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="printSalesOrder();">
					<i class="fa fa-print"></i> 打印
				</button>
				<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
					<i class="fa fa-envelope-o"></i> 发送邮件
				</button>
				<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSalesOrderAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>订单</h2>
		</header>
		<div class="widget-body">
			<form id="salesOrderForm" class="form-horizontal" action="${nvix}/nvixnt/nvixSalesOrderAction!saveOrUpdate.action">
				<input type="hidden" id="id" name="salesOrder.id" value="${salesOrder.id}"/>
				<input type="hidden" id="isDeleted" name="salesOrder.isDeleted" value="${salesOrder.isDeleted}"/>
				<input type="hidden" id="groupCode" name="salesOrder.groupCode" value="${salesOrder.groupCode}"/>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
						<div class="col-md-3">
							<input id="code" name="salesOrder.code" value="${salesOrder.code}" onchange="salesOrderFieldChanged(this);" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="title" name="salesOrder.title" value="${salesOrder.title}" onchange="salesOrderFieldChanged(this);" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>单据日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="billDate" name="salesOrder.billDate" value="${salesOrder.billDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'billDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<s:if test="tag == 'fromQuote'">
							<label class="col-md-2 control-label">单据来源:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input class="form-control required" type="text" placeholder="销售报价单" readonly="readonly"/>
									<span class="input-group-addon" style="cursor:pointer;" onclick="chooseSalesQuotation();">取单</span>
								</div>
							</div>
						</s:if>
						<s:else>
							<label class="col-md-2 control-label">单据来源:</label>
							<div class="col-md-3">
								<input class="form-control required" type="text" placeholder="无" readonly="readonly"/>
							</div>
						</s:else>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="salesOrder.customerAccount.id" value="${salesOrder.customerAccount.id}"/>
								<input id="customerName" name="salesOrder.customerAccount.name" value="${salesOrder.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor:pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="salesOrder.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesOrder.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="crmProjectId" name="salesOrder.crmProject.id" value="${salesOrder.crmProject.id}"/>
								<input id="crmProjectName" name="salesOrder.crmProject.subject" value="${salesOrder.crmProject.subject}" onfocus="chooseListData('${nvix}/nvixnt/nvixSalesOrderAction!goChooseCrmProject.action','','选择项目','crmProject');" class="form-control" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor:pointer;" onclick="chooseListData('${nvix}/nvixnt/nvixSalesOrderAction!goChooseCrmProject.action','','选择项目','crmProject');">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">结款方式:</label>
						<div class="col-md-3">
							<select id="payType" name="salesOrder.payType" class="form-control">
								<option value="">------请选择------</option>
								<option value="0" <c:if test="${salesOrder.payType == 0}">selected="selected"</c:if>>现款现货</option>
								<option value="1" <c:if test="${salesOrder.payType == 1}">selected="selected"</c:if>>货到付款</option>
								<option value="2" <c:if test="${salesOrder.payType == 2}">selected="selected"</c:if>>先款后货</option>
								<option value="3" <c:if test="${salesOrder.payType == 3}">selected="selected"</c:if>>账期收款</option>
								<option value="4" <c:if test="${salesOrder.payType == 4}">selected="selected"</c:if>>月结</option>
								<option value="5" <c:if test="${salesOrder.payType == 5}">selected="selected"</c:if>>季结</option>
								<option value="6" <c:if test="${salesOrder.payType == 6}">selected="selected"</c:if>>年结</option>
							</select>
						</div>
						<%-- <label class="col-md-2 control-label">业务类型:</label>
						<div class="col-md-3">
							<input type="hidden" id="bizType" name="salesOrder.bizType" value="${salesOrder.bizType}" />
							<select id="bizTypeId" class="form-control" onchange="$('#bizType').val($('#bizTypeId').val());">
								<option value="">------请选择------</option>
								<c:forEach items="${billsTypeList}" var="entity">
									 <option value="${entity.typeName}" <c:if test="${salesOrder.bizType == entity.typeName}">selected="selected"</c:if>>${entity.typeName}</option>
								</c:forEach>
							</select>
						</div> --%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="salesOrder.currencyType.id" class="form-control">
								<c:forEach items="${currencyTypeList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesOrder.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">地域:</label>
						<div class="col-md-3">
							<select id="regionalId" name="salesOrder.regional.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${regionalList}" var="entity">
									 <option value="${entity.id}" <c:if test="${salesOrder.regional.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>销售组织:</label>
						<div class="col-md-3">
							<div id="saleOrgTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseSaleOrgId" name="salesOrder.saleOrg.id" value="${salesOrder.saleOrg.id}"/>
								<input id="chooseSaleOrgName" type="text" onfocus="saleOrgShowMenu(); return false;" value="${salesOrder.saleOrg.fullName}" class="form-control"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#chooseSaleOrgId').val('');$('#chooseSaleOrgName').val('');">
										清空
									</button>
								</div>
								<div id="saleOrgMenuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
									<ul id="chooseSaleOrgZtree" class="ztree"></ul>
								</div>
							</div>
						</div> --%>
						<label class="col-md-2 control-label">对应机会:</label>
						<div class="col-md-3">
							<select id="saleChanceId" name="salesOrder.saleChance.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleChanceList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesOrder.saleChance.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">计划发运日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryTimeInPlan" name="salesOrder.deliveryTimeInPlan" value="${salesOrder.deliveryTimeInPlanStr}" class="form-control" onchange="salesOrderFieldChanged(this);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'deliveryTimeInPlan'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">承诺日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="promiseTime" name="salesOrder.promiseTime" value="${salesOrder.promiseTimeStr}" class="form-control" onchange="salesOrderFieldChanged(this);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'promiseTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">过账日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="postingTime" name="salesOrder.postingTime" value="${salesOrder.postingTimeStr}" class="form-control" onchange="salesOrderFieldChanged(this);" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'postingTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
						<div class="col-md-3" data-toggle="buttons" class="btn-group">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="salesOrder.status == 1">active</s:if>">
									<input type="radio" value="1" id="isMarriage" name="salesOrder.status" <s:if test="salesOrder.status == 1">checked="checked"</s:if> class="validate[required]">激活
								</label>
								<label class="btn btn-default <s:if test="salesOrder.status == 0">active</s:if>">
									<input type="radio" value="0" id="isMarriage" name="salesOrder.status" <s:if test="salesOrder.status == 0">checked="checked"</s:if>>禁用
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>业务员:</label>
						<div class="col-md-3">
								<input type="hidden" id="chooseSalePersonId" name="salesOrder.salePerson.id" value="${salesOrder.salePerson.id}"/>
								<input id="chooseSalePersonName" name="salesOrder.salePerson.name" value="${salesOrder.salePerson.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="startTime" name="salesOrder.startTime" value="${salesOrder.startTimeStr}" onchange="salesOrderFieldChanged(this);" data-prompt-position="topLeft" class="form-control validate[required,past[#salesOrder.startTime],custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="endTime" name="salesOrder.endTime" value="${salesOrder.endTimeStr}" onchange="salesOrderFieldChanged(this);" data-prompt-position="topLeft" class="form-control validate[required,future[#salesOrder.endTime],custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"/>
								<span class="input-group-addon"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<textarea rows="3" id="memo" name="salesOrder.memo" value="${salesOrder.memo}" class="form-control">${salesOrder.memo}</textarea> 
						</div>
					</div>
					<br/>
					<div class="jarviswidget jarviswidget-color-darken">
						<header style="height: 1px; border-color: #ddd !important"></header>
						<div>
							<div id="salesOrderRightContent" class="widget-body no-padding">
								<div class="jarviswidget" id="salesOrderDetailTabs" style="margin: 0;padding:12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
									<header>
										<ul class="nav nav-tabs pull-left in">
											<li class="active">
												<a data-toggle="tab" href="#orderDetailTab" onclick="orderDetailTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">明细</span> 
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#orderDeliveryPlanTab" onclick="orderDeliveryPlanTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">发运计划</span> 
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#orderInvoiceTab" onclick="orderInvoiceTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">发票</span> 
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#accessoriesTab" onclick="accessoriesTable.ajax.reload();">
													<i class="fa fa-list-alt"></i>
													<span class="hidden-mobile hidden-tablet">附件</span> 
												</a>
											</li>
										</ul>
									</header>
									<div class="widget-body" style="padding: 0;">
										<div class="tab-content">
											<div class="tab-pane no-padding active" id="orderDetailTab">
												<div id="orderDetailSearchForm" style="margin:5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="orderDetailSearchName" placeholder="物料名称" class="form-control"/>
														</div>
														<button onclick="orderDetailTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('orderDetailSearchName',orderDetailTable);" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</div>
												</div>
												<table id="orderDetail" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane no-padding" id="orderDeliveryPlanTab">
												<div id="orderDeliveryPlanSearchForm" style="margin:5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="orderDeliveryPlanSearchName" placeholder="收货人" class="form-control"/>
														</div>
														<button onclick="orderDeliveryPlanTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('orderDeliveryPlanSearchName',orderDeliveryPlanTable);" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</div>
												</div>
												<table id="orderDeliveryPlan" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane no-padding" id="orderInvoiceTab">
												<div id="orderInvoiceSearchForm" style="margin:5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="orderInvoiceSearchName" placeholder="发票抬头" class="form-control"/>
														</div>
														<button onclick="orderInvoiceTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('orderInvoiceSearchName',orderInvoiceTable);" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</div>
												</div>
												<table id="orderInvoice" class="table table-striped table-bordered table-hover" width="100%"></table>
											</div>
											<div class="tab-pane no-padding" id="accessoriesTab">
												<div id="accessoriesSearchForm" style="margin:5px;">
													<div class="form-group" style="margin: 0 5px;">
														<div class="col-md-3">
															<input type="text" value="" id="accessoriesSearchName" placeholder="附件名称" class="form-control"/>
														</div>
														<button onclick="accessoriesTable.ajax.reload();" type="button" class="btn btn-info">
															<i class="glyphicon glyphicon-search"></i> 检索
														</button>
														<button onclick="clearSearchCondition('accessoriesSearchName',accessoriesTable);" type="button" class="btn btn-default">
															<i class="glyphicon glyphicon-repeat"></i> 清空
														</button>
													</div>
												</div>
												<table id="accessories" class="table table-striped table-bordered table-hover" width="100%" ></table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">小计:</label>
						<div class="col-md-1">
							<s:if test="salesOrder.amount != null">
								<input id="amount" name="salesOrder.amount" value="${salesOrder.amount}" class="form-control" type="text" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="amount" name="salesOrder.amount" value="0" class="form-control" type="text" readonly="readonly"/>
							</s:else>
						</div>
						<label class="col-md-1 control-label">运费:</label>
						<div class="col-md-1">
							<s:if test="salesOrder.freight != null">
								<input id="freight" name="salesOrder.freight" value="${salesOrder.freight}" class="form-control" type="text" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="freight" name="salesOrder.freight" value="0" class="form-control" type="text" readonly="readonly"/>
							</s:else>
						</div>
						<label class="col-md-1 control-label">税率:</label>
						<div class="col-md-1">
							<s:if test="salesOrder.taxRate != null">
								<input id="taxRate" name="salesOrder.taxRate" value="${salesOrder.taxRate}" class="form-control" type="text" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="taxRate" name="salesOrder.taxRate" value="0" class="form-control" type="text" readonly="readonly"/>
							</s:else>
						</div>
						<label class="col-md-1 control-label">税额:</label>
						<div class="col-md-1">
							<s:if test="salesOrder.tax != null">
								<input id="tax" name="salesOrder.tax" value="${salesOrder.tax}" class="form-control" type="text" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="tax" name="salesOrder.tax" value="0" class="form-control" type="text" readonly="readonly"/>
							</s:else>
						</div>
						<label class="col-md-1 control-label">总金额:</label>
						<div class="col-md-1">
							<s:if test="salesOrder.amountTotal != null">
								<input id="amountTotal" name="salesOrder.amountTotal" value="${salesOrder.amountTotal}" class="form-control" type="text" readonly="readonly"/>
							</s:if>
							<s:else>
								<input id="amountTotal" name="salesOrder.amountTotal" value="0" class="form-control" type="text" readonly="readonly"/>
							</s:else>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>审批信息</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<table id="flowApprovalOpinionTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top:15px;">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
								<i class="fa fa-chevron-left"></i> 上一条
							</button>
							<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
								<i class="fa fa-chevron-right"></i> 下一条
							</button>
							<button class="btn btn-success" type="button" onclick="loadContent('','${nvix}/nvixnt/purchase/vixntPurchaseReturnAction!goSourceList.action?id='+$('#purchaseReturnId').val());">
								<i class="fa fa-retweet"></i> 追溯
							</button>
							<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="printSalesOrder();">
								<i class="fa fa-print"></i> 打印
							</button>
							<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
								<i class="fa fa-envelope-o"></i> 发送邮件
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSalesOrderAction!goList.action');">
								<i class="fa fa-rotate-left"></i>返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#salesOrderForm").validationEngine();
	var orderDetailColumns = [
			{"title":"条形码","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.barCode;}},
			{"title":"SKU","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.skuCode;}},
			{"title":"编码","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.code;}},
			{"title":"名称","width":"6%","orderable":false,"data" : function(data) {return data.item == null ? '' : data.item.name;}},
			{"title":"规格型号","width":"6%","orderable":false,"data" : function(data) {return data.specification;}},
			{"title":"主计量单位","width":"6%","orderable":false,"data" : function(data) {return data.unit == null ? '' : data.unit;}},
			{"title":"数量","width":"6%","orderable":false,"data" : function(data) {return data.amount;}},
			{"title":"含税单价","width":"6%","orderable":false,"data" : function(data) {return data.taxPrice == null ? "￥ 0" : ("￥" + data.taxPrice);}},
			{"title":"金额","width":"6%","orderable":false,"data" : function(data) {return data.price == null ? "￥ 0" : ("￥" + data.price);}},
			{"title":"无税单价","width":"6%","orderable":false,"data" : function(data) {return data.netPrice == null ? "￥ 0" : ("￥" + data.netPrice);}},
			{"title":"无税金额","width":"6%","orderable":false,"data" : function(data) {return data.netTotal == null ? "￥ 0" : ("￥" + data.netTotal);}},
			{"title":"税率","width":"6%","orderable":false,"data" : function(data) {return data.tax == null ? "0&nbsp;%" : (data.tax + "&nbsp;%");}},
			{"title":"折扣率","width":"6%","orderable":false,"data" : function(data) {return data.discount == null ? "0&nbsp;%" : (data.discount + "&nbsp;%");}},
			{"title":"预发货日期","width":"6%","orderable":false,"data" : function(data) {return data.requireDateStr;}},
			{"title":"备注","width":"6%","orderable":false,"data" : function(data) {return data.memo;}}
		];
	             	
   	var orderDetailTable = initDataTable("orderDetail","${nvix}/nvixnt/nvixSalesOrderAction!getSaleOrderItemJson.action",orderDetailColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#orderDetailSearchName').val();
   			name = Url.encode(name);
   	 		var id = $("#id").val();
   	 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   		},10,'1','0');
	             	
	var orderDeliveryPlanColumns = [
			{"title":"编号","width":"5%","orderable":false,"data" : function(data) {return '&nbsp;'}},
   			{"title":"订单编码","width":"10%","orderable":false,"data" : function(data) {return data.salesOrder == null ? '' : data.salesOrder.code;}},
   			{"title":"发运时间","width":"10%","orderable":false,"data" : function(data) {return data.deliveryTimeStr;}},
   			{"title":"国家","width":"10%","orderable":false,"data" : function(data) {return data.country;}},
   			{"title":"省","width":"10%","orderable":false,"data" : function(data) {return data.province;}},
   			{"title":"城市","width":"10%","orderable":false,"data" : function(data) {return data.city;}},
   			{"title":"目的地","width":"15%","orderable":false,"data" : function(data) {return data.target;}},
   			{"title":"承运人","width":"10%","orderable":false,"data" : function(data) {return data.carrier;}},
   			{"title":"收货人","width":"10%","orderable":false,"data" : function(data) {return data.reciever;}}
		];
	             	             	
	var orderDeliveryPlanTable = initDataTable("orderDeliveryPlan","${nvix}/nvixnt/nvixSalesOrderAction!getSalesDeliveryPlanJson.action",orderDeliveryPlanColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#orderDeliveryPlanSearchName').val();
			name = Url.encode(name);
			var id = $("#id").val();
			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
		},10,'1','1');
	                	
	var orderInvoiceColumns = [
			{"title":"编号","width":"10%","orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"title":"名称","width":"20%","orderable":false,"data" : function(data) {return data.title;}},
			{"title":"纳税人识别号","width":"20%","orderable":false,"data" : function(data) {return data.taxpayerPlayer;}},
			{"title":"发票金额","width":"20%","orderable":false,"data" : function(data) {return data.ticketAmount;}},
			{"title":"发票数量","width":"20%","orderable":false,"data" : function(data) {return data.ticketCount;}}
		];
	                        	             	
	var orderInvoiceTable = initDataTable("orderInvoice","${nvix}/nvixnt/nvixSalesOrderAction!getSalesTicketJson.action",orderInvoiceColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#orderInvoiceSearchName').val();
			name = Url.encode(name);
			var id = $("#id").val();
			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
		},10,'1','1');
	             	
	var accessoriesColumns = [
			{"title":"编号","width":"5%","orderable":false,"data" : function(data) {return '&nbsp;'}},
			{"title":"名称","width":"30%","orderable":false,"data" : function(data) {return "<a href='javascript:void(0);' title='下载附近' onclick=\"downloadUploader('" + data.id + "');\">"+data.fileName+"</a>";}},
			{"title":"类型","width":"20%","orderable":false,"data" : function(data) {return data.type;}},
			{"title":"下载次数","width":"30%","orderable":false,"data" : function(data) {
				if(data.downloadNum != null && data.downloadNum > 0){
					return data.downloadNum+"次";
				}else{
					return "0次";
				}
			}}
		];
	             	                         	             	
	var accessoriesTable = initDataTable("accessories","${nvix}/nvixnt/nvixSalesOrderAction!getAttachFileJson.action",accessoriesColumns,function(page,pageSize,orderField,orderBy){
			var name = $('#accessoriesSearchName').val();
			name = Url.encode(name);
			var id = $("#id").val();
			return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
		},10,'1','1');
	function goBeforeOrAfter(tag){
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixSalesOrderAction!goShowBeforeAndAfter.action?str='+tag+'&id='+$('#id').val());
	}
	var LODOP;
	function printSalesOrder(){
		$.ajax({
		url : '${nvix}/nvixnt/nvixSalesOrderAction!goPrintOrder.action?id='+$("#id").val(),
		cache : false,
		success : function(html) {
			LODOP = getLodop();
			LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
			LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
			LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
			LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
			LODOP.SET_PRINT_PAGESIZE(3, "240mm", "45mm", "");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
			LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
			/* LODOP.PRINT(); */
			LODOP.PREVIEW();
		}
		});
	}
	var flowApprovalOpinionColumns = [ {
		"title" : "编号",
		"width" : "5%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "审批意见",
		"data" : function(data) {
			return data.opinion;
		}
		}, {
		"title" : "审批人",
		"data" : function(data) {
			return data.approvalPersonName;
		}
		}, {
		"title" : "审批时间",
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		} ];
		var flowApprovalOpinionTable = initDataTable("flowApprovalOpinionTableId", "${nvix}/nvixnt/vixntInboundWarehouseAction!getFlowApprovalOpinion.action", flowApprovalOpinionColumns, function(page, pageSize, orderField, orderBy) {
			var id = $("#id").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"id" : id
			};
		});
</script>