<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理 <span onclick="">&gt; 销售发货</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<s:if test="isAllowAudit == 1">
					<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
						<i class="fa fa-save"></i> &nbsp;提交审批
					</button>
				</s:if>
				<button class="btn btn-default" type="button" onclick="loadContent('mid_salesDeliveryOrder','${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>退货单</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="deliveryDocumentForm">
				<fieldset>
					<input type="hidden" id="sourceClassName" name="deliveryDocument.sourceClassName" value="${deliveryDocument.sourceClassName}"/>
				<input type="hidden" id="sourceBillCode" name="deliveryDocument.sourceBillCode" value="${deliveryDocument.sourceBillCode}"/>
					<input type="hidden" id="deliveryDocumentId" name="deliveryDocument.id" value="${deliveryDocument.id}" /> 
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 发货单编码:</label>
						<div class="col-md-3">
							<input id="code" name="deliveryDocument.code" value="${deliveryDocument.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="deliveryDocument.customerAccount.id" value="${deliveryDocument.customerAccount.id}"/>
								<input id="customerName" value="${deliveryDocument.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor:pointer;" onclick="">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发运地:</label>
						<div class="col-md-3">
							<input id="source" name="deliveryDocument.source" value="${deliveryDocument.source}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>目的地:</label>
						<div class="col-md-3">
							<input id="target" name="deliveryDocument.target" value="${deliveryDocument.target}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>目的地:</label>
						<div class="col-md-3">
							<input id="target" name="deliveryDocument.target" value="${deliveryDocument.target}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div> --%>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>业务员:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="chooseSalePersonId" name="deliveryDocument.salePerson.id" value="${deliveryDocument.salePerson.id}"/>
								<input id="chooseSalePersonName" name="deliveryDocument.salePerson.name" value="${deliveryDocument.salePerson.name}" onfocus="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalePerson');" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'chooseSalePerson');">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>交货时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryTime" name="deliveryDocument.deliveryTime" value="${deliveryDocument.deliveryTimeTimeStr }" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'returnTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">		
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发货时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="shippedDate" name="deliveryDocument.shippedDate" value="${deliveryDocument.shippedDateTimeStr }" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'returnTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="deliveryDocument.memo" class="form-control" rows="4">${deliveryDocument.memo}</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget jarviswidget-color-white">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>退货明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
										</div>
										<button onclick="deliveryDocumentItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchProductName').val('');deliveryDocumentItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="goSaveOrUpdateDeliveryDocumentItem('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="新增明细" />
											</button>
										</div>
									</div>
								</div>
								<table id="deliveryDocumentItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">总金额:</label>
						<div class="col-md-3">
							<input id="amount" name="deliveryDocument.amount" value="${deliveryDocument.amount}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly"/>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<s:if test="isAllowAudit == 1">
								<button type="button" class="btn bg-color-blueLight txt-color-white" onclick="approvalSalesOrder('');">
									<i class="fa fa-save"></i> &nbsp;提交审批
								</button>
							</s:if>
							<button class="btn btn-default" type="button" onclick="loadContent('mid_salesDeliveryOrder','${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');">
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
	var deliveryDocumentItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}编码",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}名称",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "规格型号",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "${vv:varView('vix_mdm_item')}类型",
	"name" : "itemType",
	"data" : function(data) {
		return data.itemType;
	}
	}, {
	"title" : "发货数量",
	"name" : "count",
	"data" : function(data) {
		return data.count;
	}
	}, {
	"title" : "金额",
	"name" : "taxTotal",
	"data" : function(data) {
		return data.taxTotal;
	}
	}, {
	"title" : "单价",
	"name" : "unitcost",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "操作",
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateSaleReturnFormItem('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteSaleReturnFormItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var deliveryDocumentItemTable = initDataTable("deliveryDocumentItemTableId", "${nvix}/nvixnt/nvixntDeliveryDocumentAction!goSingleDeliveryDocumentItemList.action", deliveryDocumentItemColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#deliveryDocumentId").val();
		var searchProductName = $("#searchProductName").val();
		searchProductName = Url.encode(searchProductName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchProductName" : searchProductName,
		"id" : id
		};
	});

	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseWarehouse.action', 'single', '选择仓库', 'warehouse');
	};
	//保存入库单
	$("#deliveryDocumentForm").validationEngine();
	function saveOrUpdate() {
		if ($("#deliveryDocumentForm").validationEngine('validate')) {
			$("#deliveryDocumentForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixntDeliveryDocumentAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdateDeliveryDocumentItem(id, title) {
		if (id) {
			openWindowForShow('${nvix}/nvixnt/nvixntDeliveryDocumentAction!goSaveOrUpdateDeliveryDocumentItem.action?id=' + id, title, 850, 675);
		} else {
			if ($("#deliveryDocumentForm").validationEngine('validate')) {
				$("#deliveryDocumentForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixntDeliveryDocumentAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "0"){
						$("#deliveryDocumentId").val(r[2]);
						openWindowForShow('${nvix}/nvixnt/nvixntDeliveryDocumentAction!goSaveOrUpdateDeliveryDocumentItem.action?id=' + id, title, 850, 675);
					}else{
						showMessage(r[1]);
						return;
					}
				}
				});
			} else {
				return false;
			}
		}
	};
	function saveOrUpdateSaleReturnFormItem() {
		if ($("#deliveryDocumentId").val() != '' && $("#deliveryDocumentId").val() != null) {
			$.post('${nvix}/nvixnt/nvixntDeliveryDocumentAction!saveOrUpdateSaleReturnFormItem.action', {
			'id' : $("#deliveryDocumentId").val(),
			'itemcode' : $("#scanitemcode").val()
			}, function(result) {
				deliveryDocumentItemTable.ajax.reload();
				$("#scanitemcode").val('');
				$("#totalAmount").val(result);
			});
		} else {
			if ($("#deliveryDocumentForm").validationEngine('validate')) {
				$("#deliveryDocumentForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixntDeliveryDocumentAction!saveOrUpdate.action",
				dataType : "text",
				success : function(id) {
					$.post('${nvix}/nvixnt/nvixntDeliveryDocumentAction!saveOrUpdateSaleReturnFormItem.action', {
					'id' : id,
					'itemcode' : $("#scanitemcode").val()
					}, function(result) {
						deliveryDocumentItemTable.ajax.reload();
						$("#scanitemcode").val('');
						$("#totalAmount").val(result);
					});
				}
				});
			} else {
				return false;
			}
		}
	};
	
	function deleteSaleReturnFormItemById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntDeliveryDocumentAction!deleteDeliveryDocumentItemById.action?id=' + id, '是否删除发货单明细?', deliveryDocumentItemTable,"删除发货单明细",function(){
			$.ajax({
				url : '${nvix}/nvixnt/nvixntDeliveryDocumentAction!calculateAmount.action?id=' + $("#deliveryDocumentId").val(),
				cache : false,
				success : function(json) {
					$("#amount").val(json);
				}
			});
		});
	};
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
	function audit() {
		if ($("#deliveryDocumentForm").validationEngine('validate')) {
			$("#deliveryDocumentForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixntDeliveryDocumentAction!audit.action",
			dataType : "text",
			success : function(id) {
				showMessage(id,'success');
				loadContent('', '${nvix}/nvixnt/nvixntDeliveryDocumentAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
</script>