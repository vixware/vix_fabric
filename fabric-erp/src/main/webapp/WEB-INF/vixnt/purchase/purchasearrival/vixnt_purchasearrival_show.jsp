<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-shopping-cart"></i> 采购管理<span onclick="">&gt; 采购到货</span>
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
				<button class="btn btn-success" type="button" onclick="loadContent('','${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goSourceList.action?id='+$('#purchaseArrivalId').val());">
					<i class="fa fa-retweet"></i> 追溯
				</button>
				<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="print();">
					<i class="fa fa-print"></i> 打印
				</button>
				<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
					<i class="fa fa-envelope-o"></i> 发送邮件
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>采购到货</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="purchaseArrivalForm">
				<fieldset>
					<input type="hidden" id="purchaseArrivalId" name="purchaseArrival.id" value="${purchaseArrival.id}" /> 
					<div class="form-group">
						<label class="col-md-2 control-label"> 编码:</label>
						<div class="col-md-3">
							<input id="code" name="purchaseArrival.code" value="${purchaseArrival.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label">主题:</label>
						<div class="col-md-3">
							<input id="name" name="purchaseArrival.name" value="${purchaseArrival.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">采购人:</label>
						<div class="col-md-3">
							<input id="purchasePerson" name="purchaseArrival.purchasePerson" value="${purchaseArrival.purchasePerson}" type="text" class="form-control">
						</div>
						<label class="col-md-2 control-label"> 供应商:</label>
						<div class="col-md-3">
							<input type="hidden" id="supplierId" name="purchaseArrival.supplierId" value="${purchaseArrival.supplierId}" />
							<input type="hidden" id="supplierCode" name="purchaseArrival.supplierCode" value="${purchaseArrival.supplierCode}" />
							<input id="supplierName" name="purchaseArrival.supplierName" value="${purchaseArrival.supplierName}" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>交货日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="deliveryDate" name="purchaseArrival.deliveryDate" value="${purchaseArrival.deliveryDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd ',el:'deliveryDate'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="purchaseArrival.memo" class="form-control" rows="4">${purchaseArrival.memo }</textarea>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>采购订单明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="名称" class="form-control" id="searchItemName">
										</div>
										<button onclick="purchaseArrivalItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchItemName').val('');purchaseArrivalItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</div>
								</div>
								<table id="purchaseArrivalItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">合计:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="totalAmount" name="purchaseArrival.totalAmount" value="${purchaseArrival.totalAmount}" type="text" readonly="readonly"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-md-2">创建人:</label>
						<div class="col-md-3">
							<input class="form-control" id="creator" name="purchaseArrival.creator" value="${purchaseArrival.creator}" type="text">
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('before');">
								<i class="fa fa-chevron-left"></i> 上一条
							</button>
							<button class="btn btn-primary" type="button" onclick="goBeforeOrAfter('after');">
								<i class="fa fa-chevron-right"></i> 下一条
							</button>
							<button class="btn btn-success" type="button" onclick="loadContent('','${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goSourceList.action?id='+$('#purchaseArrivalId').val());">
								<i class="fa fa-retweet"></i> 追溯
							</button>
							<button class="btn bg-color-blueDark txt-color-white" type="button" onclick="print();">
								<i class="fa fa-print"></i> 打印
							</button>
							<button class="btn bg-color-orange txt-color-white" type="button" onclick="">
								<i class="fa fa-envelope-o"></i> 发送邮件
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goList.action');">
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
	var purchaseArrivalItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
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
	}, */ {
	"title" : "单价",
	"orderable" : false,
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "数量",
	"orderable" : false,
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "单位",
	"orderable" : false,
	"data" : function(data) {
		return data.unit;
	}
	}];
	var purchaseArrivalItemTable = initDataTable("purchaseArrivalItemTableId", "${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goPurchaseArrivalItemList.action", purchaseArrivalItemColumns, function(page, pageSize, orderField, orderBy) {
		var searchItemName = $("#searchItemName").val();
		searchItemName = Url.encode(searchItemName);
		var id = $("#purchaseArrivalId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchItemName,
		"id":id
		};
	});

	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	$("#purchaseArrivalForm").validationEngine();
	function saveOrUpdate(approval) {
		if ($("#purchaseArrivalForm").validationEngine('validate')) {
			$("#purchaseArrivalForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!saveOrUpdate.action?approval="+approval,
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	function goSaveOrUpdatePurchaseArrivalItem(id, title) {
		if ($("#purchaseArrivalForm").validationEngine('validate')) {
			$("#purchaseArrivalForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				var r = result.split(":");
				if(r[0] == "0"){
					$("#purchaseArrivalId").val(r[1]);
					showMessage(r[2]);
					openWindowForShow('${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goSaveOrUpdatePurchaseArrivalItem.action?id=' + id + "&purchaseArrivalId=" + purchaseArrivalId, title, 850, 675);
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
	
	function deletePurchaseArrivalItem(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!deletePurchaseArrivalItem.action?id=' + id, '是否删除到货订单明细?', purchaseArrivalItemTable,"删除到货订单明细",function(){
			$.ajax({
				url : '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!getOrderItemTotal.action?id=' + $("#purchaseArrivalId").val(),
				cache : false,
				success : function(json) {
					$("#totalAmount").val(json);
				}
			});
		});
	};
	function approvalSalesOrder(){
		layer.confirm('订单提交审批后,将不可修改,确定要审批订单吗?',{
			btn:['确定','取消']
		},function(action){
			layer.close(action);
			saveOrUpdate('approval');
		},function(action){
			layer.close(action);
		});
	}
	function goBeforeOrAfter(tag){
		goSaveOrUpdateEntity('${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!goShowBeforeAndAfter.action?str='+tag+'&id='+$('#purchaseArrivalId').val());
	}
	var LODOP;
	function print(){
		$.ajax({
			url : '${nvix}/nvixnt/purchase/vixntPurchaseArrivalAction!printPurchaseArrival.action?id='+$("#purchaseArrivalId").val(),
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
</script>