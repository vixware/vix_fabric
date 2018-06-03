<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售中管理 </span><span>>开票记录 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixSalesInvoiceAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>开票记录</h2>
		</header>
		<div class="widget-body">
			<form id="salesInvoiceForm" class="form-horizontal" >
				<input type="hidden" id="id" name="salesInvoice.id" value="${salesInvoice.id}" /> 
				<input type="hidden" id="isDeleted" name="salesInvoice.isDeleted" value="${salesInvoice.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开票内容:</label>
						<div class="col-md-3">
							<input id="name" name="salesInvoice.name" value="${salesInvoice.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>票据类型:</label>
						<div class="col-md-3">
							<select id="saleInvoiceType" name="salesInvoice.saleInvoiceType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${saleInvoiceTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesInvoice.saleInvoiceType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="salesInvoice.customerAccount.id" value="${salesInvoice.customerAccount.id}" /> 
								<input id="customerName" name="salesInvoice.customerAccount.name" value="${salesInvoice.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">销售订单:</label>
						<div class="col-md-3">
							<select id="salesOrderId" name="salesInvoice.salesOrder.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${salesOrderList}" var="entity">
									<option value="${entity.id}" <c:if test="${salesInvoice.salesOrder.id == entity.id}">selected="selected"</c:if>>${entity.title}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>票据金额:</label>
						<div class="col-md-3">
							<input id="amount" name="salesInvoice.amount" value="${salesInvoice.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label">发票号码:</label>
						<div class="col-md-3">
							<input id="code" name="salesInvoice.code" value="${salesInvoice.code}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>税率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="tax" name="salesInvoice.tax" value="${salesInvoice.tax}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div> --%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开票日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="salesInvoice.createTime" value="${salesInvoice.createTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">期次:</label>
						<div class="col-md-3">
							<input id="issue" name="salesInvoice.issue" value="${salesInvoice.issue}" data-prompt-position="topLeft" class="form-control validate[custom[integer],min[1]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">是否回款:</label>
						<div class="col-md-3">
							<select id="isBackSection" name="salesInvoice.isBackSection" class="form-control">
								<option value="">------请选择------</option>
								<option value="0" <c:if test="${salesInvoice.isBackSection == 0}">selected="selected"</c:if>>未回款</option>
								<option value="1" <c:if test="${salesInvoice.isBackSection == 1}">selected="selected"</c:if>>已回款</option>
							</select>
						</div>
						<label class="col-md-2 control-label">回款计划:</label>
						<div class="col-md-3">
							<select id="backSectionPlan" name="salesInvoice.backSectionPlan" class="form-control">
								<option value="">------请选择------</option>
								<option value="0" <c:if test="${salesInvoice.backSectionPlan == 0}">selected="selected"</c:if>>未制定回款计划</option>
								<option value="1" <c:if test="${salesInvoice.backSectionPlan == 1}">selected="selected"</c:if>>已制定回款计划</option>
								<option value="2" <c:if test="${salesInvoice.backSectionPlan == 2}">selected="selected"</c:if>>已回款无须计划</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">经手人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="salesInvoice.employee.id" value="${salesInvoice.employee.id}" /> 
								<input id="employeeName" name="salesInvoice.employee.name" value="${salesInvoice.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="salesInvoice.memo" class="form-control" rows="4">${salesInvoice.memo}</textarea>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>发票明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin:5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="商品名称" class="form-control" id="searchItemName">
												</div>
												<button onclick="salesInvoiceItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left" >
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchItemName').val('');salesInvoiceItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right" >
													<button onclick="addSalesInvoiceItem();" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
													</button>
												</div>
											</div>
										</div>
									    <table id="salesInvoiceItemTableId" class="table table-striped table-bordered table-hover" width="100%">
									   		<thead>			                
												<tr>
													<th width="10%">编号</th>
													<th>商品名称</th>
													<th>商品编码</th>
													<th>数量</th>
													<th>单价</th>
													<th width="20%">操作</th>
												</tr>
											</thead>
									    </table>
								 	</div>
								</div>
							</div>
						</div>
					</div> --%>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSalesInvoiceAction!goList.action');">
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
	$("#salesInvoiceForm").validationEngine();
	
	var salesInvoiceItemColumns = [
		{"orderable":false,"data" : function(data) {return '&nbsp;'}},
		{"orderable":false,"data" : function(data) {return data.itemName;}},
		{"orderable":false,"data" : function(data) {return data.itemCode;}},
		{"orderable":false,"data" : function(data) {return data.amount;}},
		{"orderable":false,"data" : function(data) {return data.price;}},
		{"orderable":false,"data" : function(data) {
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateSalesInvoiceItem('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteDetailById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
              	             	                         	             	
  	var salesInvoiceItemTable = initDataTable("salesInvoiceItemTableId","${nvix}/nvixnt/nvixSalesInvoiceAction!getSalesInvoiceItemJson.action",salesInvoiceItemColumns,function(page,pageSize,orderField,orderBy){
		var name = $('#searchItemName').val();
		name = Url.encode(name);
 		var id = $("#id").val();
 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
 	},10,'1','1');
  	
  	function addSalesInvoiceItem(){
		var id = $("#id").val();
		if(id == ''){
			if($('#salesInvoiceForm').validationEngine('validate')){
				$("#salesInvoiceForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/nvixSalesInvoiceAction!saveOrUpdate.action?id=" + $("#id").val(),
					contentType : "application/x-www-form-urlencoded; charset=utf-8",
					success : function(result) {
						var r = result.split(":");
						if(r[0] != '0'){
							$("#id").val($.trim(r[0]));
							saveOrUpdateSalesInvoiceItem('0');
						}
					}
				});
			}
		}else {
			saveOrUpdateSalesInvoiceItem('0');
		}
   	}
   	
   	/** 添加发运计划明细*/
   	function saveOrUpdateSalesInvoiceItem(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixSalesInvoiceAction!addSalesInvoiceItem.action?id=' + id,'salesInvoiceItemForm','发票明细',720,350,salesInvoiceItemTable,function(){
			$("#salesInvoiceId").val($("#id").val());
		},function(){
			salesInvoiceItemTable.ajax.reload();
		});
   	}
   	
  //删除
	function deleteDetailById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixSalesInvoiceAction!deleteSalesInvoiceItemById.action?id='+id,'是否删除发票明细?',salesInvoiceItemTable);
	}

	function saveOrUpdate(id) {
		//表单验证
		if ($("#salesInvoiceForm").validationEngine('validate')) {
			$("#salesInvoiceForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixSalesInvoiceAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[1]);
					loadContent('', '${nvix}/nvixnt/nvixSalesInvoiceAction!goList.action');
				}
			});
		}
	}
	
	/** 加载销售订单 */
	function loadSalesOrder() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixSalesInvoiceAction!loadSalesOrder.action?id=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var salesOrderId = $("#salesOrderId").val();
				$("#salesOrderId").html(html);
				/* if (contactPersonId != '') {
					$("#contactPersonId").val(contactPersonId);
				} */
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadSalesOrder();
		});
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>