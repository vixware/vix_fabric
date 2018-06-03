<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理 <span onclick="">&gt; 采购计划</span>
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
				<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');">
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
			<form class="form-horizontal" id="purchasePlanForm">
				<fieldset>
					<input type="hidden" id="purchasePlanId" name="purchasePlan.id" value="${purchasePlan.id}" /> 
					<input type="hidden" id="supplierId" name="purchasePlan.supplier.id" value="${purchasePlan.supplier.id}" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 单号:</label>
						<div class="col-md-3">
							<input id="code" name="purchasePlan.code" value="${purchasePlan.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="name" name="purchasePlan.purchasePlanName" value="${purchasePlan.purchasePlanName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">计划类型:</label>
						<div class="col-md-3">
							<select id="bizStyle" name="purchasePlan.style" class="form-control validate[required]" onchange='initdata();'>
									<option  value="">请选择</option>
									<option  value="1"<c:if test='${purchasePlan.style eq "1"}'>selected="selected"</c:if>>年计划</option>
									<option  value="2"<c:if test='${purchasePlan.style eq "2"}'>selected="selected"</c:if>>季计划</option>
									<option  value="3"<c:if test='${purchasePlan.style eq "3"}'>selected="selected"</c:if>>月计划</option>
								</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划时间:</label>
							<div id="d1" class="col-md-3">
								<input id="planTime" name="purchasePlan.planTime" type="text" value="${purchasePlan.planTime}" class="form-control" onClick="WdatePicker({dateFmt:'yyyy'})" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="%{purchasePlan.planTime}" />" />
							</div>
							<div id="d2" class="col-md-3" style="display: none;">
								<input id="planTime1" name="purchasePlan.planTime" type="text" value="${purchasePlan.planTime}"  class="form-control" onClick="WdatePicker({dateFmt:'yyyy年M季度', isQuarter:true, isShowOK:false,disabledDates:['....-0[5-9]-..','....-1[0-2]-..'], startDate:'%y-01-01' })" value="<s:date format="yyyy-MM-dd" name="%{purchasePlan.planTime}" />" />
							</div>
							<div id="d3" class="col-md-3" style="display: none;">
								<input id="planTime2" name="purchasePlan.planTime" type="text" value="${purchasePlan.planTime}"  class="form-control" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="<s:date format="yyyy-MM-dd" name="%{purchasePlan.planTime}" />" />
							</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 供应商:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="supplierName" value="${purchasePlan.supplier.name }" class="form-control validate[required]" type="text" readonly="readonly" />
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
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="purchasePlan.memo" class="form-control" rows="4">${purchasePlan.memo }</textarea>
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
													<li class="active"><a data-toggle="tab" href="#purchasePlanItemsTab" onclick="purchasePlanItemsTable.ajax.reload();"><span class="hidden-mobile hidden-tablet">计划明细</span>
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
																	<input type="text" value="" id="purchasePlanItemsName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="purchasePlanItemsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#purchasePlanItemsName').val('');purchasePlanItemsTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addPurchasePlanItems();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="purchasePlanItemsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
																<div class="listMenu-float-right">
																	<div class="form-group" style="margin: 0 5px;">
																		<div class="listMenu-float-right">
																			<input type="file" id="fileToUpload" name="fileToUpload" onchange="fileChange();" value="上传附件" style="width: 100%;">
																		</div>
																	</div>
																</div>
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
					<div class="form-group">
						<label class="control-label col-md-2">采购金额:</label>
						<div class="col-md-3">
							<input class="form-control" id="price" name="purchasePlan.price" value="${purchasePlan.price }" type="text">
						</div>
						<label class="control-label col-md-2">创建人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="purchasePlan.creator" value="${purchasePlan.creator }" type="text">
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
							<button class="btn btn-default" type="button" onclick="loadContent('purchase_purchasePlan','${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');">
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
	$(function(){
		initdata();
	})
	function initdata(){
		//设置单据类型选中(修改)
		if(!$("#createTime").val()){
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
		}
		var style = $("#bizStyle").val();
		if(style == "1"){
			$("#d1").attr("style",'');
			$("#d2").attr("style",'display:none;');
			$("#planTime").attr('name','purchasePlan.planTime');
			$("#planTime1").attr('name','');
			$("#planTime2").attr('name','');
			$("#d3").attr("style",'display:none;');
		}else if (style == "2"){
			$("#d1").attr("style",'display:none;');
			$("#d2").attr("style",'');
			$("#planTime").attr('name','');
			$("#planTime1").attr('name','purchasePlan.planTime');
			$("#planTime2").attr('name','');
			$("#d3").attr("style",'display:none;');  
		}else if (style == "3"){
			$("#d1").attr("style",'display:none;');
			$("#d2").attr("style",'display:none;');
			$("#d3").attr("style",'');  
			$("#planTime").attr('name','');
			$("#planTime1").attr('name','');
			$("#planTime2").attr('name','purchasePlan.planTime');
		}
	};
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	$("#purchasePlanForm").validationEngine();
	function saveOrUpdate(approval) {
		if ($("#purchasePlanForm").validationEngine('validate')) {
			$("#purchasePlanForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!saveOrUpdate.action?approval="+approval,
			dataType : "text",
			success : function(id) {
				loadContent('purchase_purchasePlan', '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	function deletePurchasePlanItemsById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchasePlanAction!deletepurchasePlanItem.action?id=' + id, '是否删除采购计划明细?', purchasePlanLineItemTable,"删除采购计划明细",function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!changePurchasePlanPrice.action?id=' + $("#purchasePlanId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					$("#amount").val(r[1]);
					$("#price").val(r[0]);
				}
			});
		});
	};
	var purchasePlanItemsColumns = [ {
		"title" : "编号",
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			return "&nbsp;";
		}
		}, {
		"title" : "商品编码",
		"orderable" : false,
		"data" : function(data) {
			return data.itemCode;
		}
		}, {
		"title" : "商品名称",
		"orderable" : false,
		"data" : function(data) {
			return data.itemName;
		}
		}/* , {
		"title" : "类型",
		"orderable" : false,
		"data" : function(data) {
			if(data.style == '1'){
				return	"<span class='label label-primary'>年计划</span>";
			}else if(data.style == '2'){
				return "<span class='label label-success'>季计划</span>";
			}else if(data.style == '3'){
				return "<span class='label label-wraning'>月计划</span>";
			} 
			return data.specification;
		}
		} */, {
		"title" : "规格型号",
		"orderable" : false,
		"data" : function(data) {
			return data.specification;
		}
		}, {
		"title" : "单位",
		"orderable" : false,
		"data" : function(data) {
			return data.unit;
		}
		}, {
		"title" : "单价",
		"orderable" : false,
		"data" : function(data) {
			return data.unitcost;
			}
		}, {
		"title" : "数量",
		"orderable" : false,
		"data" : function(data) {
			return data.amount;
		}
		}, {
		"title" : "金额",
		"orderable" : false,
		"data" : function(data) {
			return data.price;
		}
		}, {
		"title" : "操作",
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"addPurchasePlanItems('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePurchasePlanItemsById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		} ];
		var purchasePlanItemsTable = initDataTable("purchasePlanItemsTableId", "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goPurchasePlanItemsSingleList.action", purchasePlanItemsColumns, function(page, pageSize, orderField, orderBy) {
			var purchasePlanItemsName = $("#purchasePlanItemsName").val();
			title = Url.encode(purchasePlanItemsName);
			var purchasePlanId = $("#purchasePlanId").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"searchName" : purchasePlanItemsName,
			"id":purchasePlanId
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
		}, {
			"title" : "操作",
			"data" : function(data) {
				return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteAttachments('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			}
		} ];
		var attachmentsTable = initDataTable("attachmentsTableId", "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!getAttachmentsList.action", attachmentsColumns, function(page, pageSize, orderField, orderBy) {
			var attachmentsName = $('#attachmentsName').val();
			attachmentsName = Url.encode(attachmentsName);
			var purchasePlanId = $("#purchasePlanId").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"id" : purchasePlanId,
			"searchName":attachmentsName
			};
		},10,"0");
		//上传附件
		function fileChange() {
			var purchasePlanId = $('#purchasePlanId').val();
			if(!purchasePlanId){
				if ($("#purchasePlanForm").validationEngine('validate')) {
					$("#purchasePlanForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!saveOrUpdate.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0]=="1"){
							$("#purchasePlanId").val(r[1]);
							uploadFileOrImage("attachmentsForm", "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!saveOrUpdateAttachments.action?id="+r[1], "fileToUpload", "file", function(data) {
								attachmentsTable.ajax.reload();
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
				uploadFileOrImage("attachmentsForm", "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!saveOrUpdateAttachments.action?id="+$("#purchasePlanId").val(), "fileToUpload", "file", function(data) {
					attachmentsTable.ajax.reload();
					showMessage("文件上传成功!");
				});
			}
		};
		function addPurchasePlanItems(id, title) {
			var purchasePlanId = $('#purchasePlanId').val();
			if (purchasePlanId) {
				openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goSaveOrUpdatePurchasePlanItems.action?id=' + id + "&purchasePlanId=" + purchasePlanId, title, 850, 625);
			} else {
				if ($("#purchasePlanForm").validationEngine('validate')) {
					$("#purchasePlanForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!saveOrUpdate.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == 0){
							showMessage(r[1]);
							return;
						}else{
							showMessage(r[2]);
							$('#purchasePlanId').val(r[1]);
							openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goSaveOrUpdatePurchasePlanItems.action?id=' + id + "&purchasePlanId=" + r[1], title, 850, 625);	
						}
					}
					});
				} else {
					return false;
				}
			}
		};
		function deleteAttachments(id) {
			deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchasePlanAction!deleteUploaderById.action?id=' + id, '是否删除采购计划附件?', purchasePlanLineItemTable,'删除采购计划附件');
		}
		function approvalSalesOrder() {
			layer.confirm('订单提交审批后,将不可修改,确定要审批订单吗?', {
				btn : [ '确定', '取消' ]
			}, function(action) {
				layer.close(action);
				audit();
			}, function(action) {
				layer.close(action);
			});
		}
		function audit() {
			if ($("#purchasePlanForm").validationEngine('validate')) {
				$("#purchasePlanForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!audit.action",
				dataType : "text",
				success : function(result) {
					showMessage(result,'success');
					loadContent('purchase_purchasePlan', '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goList.action');
				}
				});
			} else {
				return false;
			}
		};
</script>