<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理<span onclick="">&gt; 采购申请</span>
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
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>采购申请</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="purchaseApplyForm">
				<fieldset>
					<input type="hidden" id="purchaseApplyId" name="purchaseApply.id" value="${purchaseApply.id}" /> 
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="code" name="purchaseApply.code" value="${purchaseApply.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="purchaseApply.name" value="${purchaseApply.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">申请人:</label>
						<div class="col-md-3">
							<input id="requirePerson" name="purchaseApply.requirePerson" value="${purchaseApply.requirePerson}" type="text" class="form-control">
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 申请日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="purchaseApply.createTime" value="${purchaseApply.createTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',el:'createTime'});">
								<i class="fa fa-calendar"></i></span>
							</div>
							<%-- <div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input type="hidden" id="supplierId" name="purchaseApply.supplierId" value="${purchaseApply.supplierId}" />
										<input type="hidden" id="supplierCode" name="purchaseApply.supplierCode" value="${purchaseApply.supplierCode}" />
										<input id="supplierName" name="purchaseApply.supplierName" value="${purchaseApply.supplierName}" class="form-control validate[required]" type="text" readonly="readonly" />
										<div class="input-group-btn">
											<button onclick="goChooseSupplier();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#supplierId').val('');$('#supplierName').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div> --%>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>币种:</label>
						<div class="col-md-3">
							<select id="currency" name="salesOrder.currency" class="form-control">
								<c:forEach items="${currencyTypeList}" var="entity">
									 <option value="${entity.name}" <c:if test="${salesOrder.currency eq entity.name}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
						<div class="col-md-3">
							<select id="status" name="status" disabled="disabled" class="form-control">
									<option value="">请选择</option>
									<option value="S1">待确认</option>
									<option value="S2">审批中</option>
									<option value="S3">已发货</option>
									<option value="S4">已完成</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="purchaseApply.memo" class="form-control" rows="4">${purchaseApply.memo }</textarea>
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
													<li class="active"><a data-toggle="tab" href="#purchasePlanItemsTab" onclick="purchasePlanItemsTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">申请明细</span>
													</a></li>
													<li><a data-toggle="tab" href="#attachmentsTab" onclick="attachmentsTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">附件</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body active" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="purchasePlanItemsTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
																</div>
																<button onclick="purchaseApplyItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#searchItemName').val('');purchaseApplyItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
															</div>
														</div>
														<table id="purchaseApplyItemTableId" class="table table-striped table-bordered table-hover" width="100%">
													<thead>
														<tr>
															<th data-options="field:'itemCode',width:80,align:'center'">编码</th>
															<th data-options="field:'itemName',width:120,align:'center'">名称</th>
															<th data-options="field:'skuCode',align:'center',width:120,editor:'text'">SKU码</th>
															<th data-options="field:'barCode',align:'center',width:120,editor:'text'">BAR码</th>
															<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
															<th data-options="field:'unit',width:80,align:'center'">主计量单位</th>
															<th data-options="field:'amount',width:60,align:'center'">数量</th>
															<th data-options="field:'price',width:60,align:'center'">无税单价</th>
															<th data-options="field:'taxRate',width:60,align:'center'">税率</th>
															<th data-options="field:'total',width:60,align:'center'">含税总价</th>
															<th data-options="field:'requireTime',width:80,align:'center'">需求日期</th>
															<th data-options="field:'supplier',width:120,align:'center'">建议供应商</th>
														</tr>
													</thead>
												</table>
													</div>
													<div class="tab-pane no-padding" id="attachmentsTab">
														<div id="orderDeliveryPlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="attachmentsName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="attachmentsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#attachmentsName').val('');attachmentsTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
															</div>
														</div>
														<table id="attachmentsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
												</div>
											</div>	
										</div>
									</div>
								</div>
							</div>
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
				<div class="form-actions">
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
	var purchaseApplyItemColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"data" : function(data) {
		return data.barCode;
	}
	}, {
	"data" : function(data) {
		return data.specification;
	}
	},  {
	"orderable" : false,
	"data" : function(data) {
		return data.unit;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.price;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.taxRate;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.total;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.requireTimeStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.supplier;
	}
	} ];
	var purchaseApplyItemTable = initDataTable("purchaseApplyItemTableId", "${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goSingleListDetails.action", purchaseApplyItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var purchaseApplyId = $("#purchaseApplyId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchItemName,
		"purchaseApplyId":purchaseApplyId
		};
	});
	
	var attachmentsColumns =  [ {
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
	}];
	var attachmentsTable = initDataTable("attachmentsTableId", "${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goSingleListAttachments.action", attachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var attachmentsName = $('#attachmentsName').val();
		attachmentsName = Url.encode(attachmentsName);
		var purchasePlanId = $("#purchaseApplyId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"purchaseApplyId" : purchasePlanId,
		"searchName":attachmentsName
		};
	},10,"0");
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	$("#purchaseApplyForm").validationEngine();
	function saveOrUpdate(approval) {
		if ($("#purchaseApplyForm").validationEngine('validate')) {
			$("#purchaseApplyForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!saveOrUpdate.action?approval="+approval,
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdatepurchaseApplyItem(id, title) {
		if ($("#purchaseApplyForm").validationEngine('validate')) {
			$("#purchaseApplyForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0] == "1"){
					$("#purchaseApplyId").val(r[2]);
					showMessage(r[1]);
					openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchaseApplyAction!goSaveOrUpdatePurchaseApplyDetails.action?id=' + id + "&purchaseApplyId=" + purchaseApplyId, title, 850, 675);
				}else{
					showMessage(r[1]);
					return;
				}
			}
			});
		} else {
			return false;
		}
	};
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
</script>