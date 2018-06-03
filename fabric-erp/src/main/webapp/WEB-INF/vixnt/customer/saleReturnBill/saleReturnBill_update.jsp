<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售中管理 </span><span>>退款记录 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixSaleReturnBillAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>退款记录</h2>
		</header>
		<div class="widget-body">
			<form id="saleReturnBillForm" class="form-horizontal" >
				<input type="hidden" id="id" name="saleReturnBill.id" value="${saleReturnBill.id}" /> 
				<input type="hidden" id="isDeleted" name="saleReturnBill.isDeleted" value="${saleReturnBill.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="name" name="saleReturnBill.name" value="${saleReturnBill.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="saleReturnBill.customerAccount.id" value="${saleReturnBill.customerAccount.id}" /> 
								<input id="customerName" name="saleReturnBill.customerAccount.name" value="${saleReturnBill.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly"/> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>金额:</label>
						<div class="col-md-3">
							<input id="returnAmount" name="saleReturnBill.returnAmount" value="${saleReturnBill.returnAmount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>货币类型:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="saleReturnBill.currencyType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleReturnBill.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>退款日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="rbDate" name="saleReturnBill.rbDate" value="${saleReturnBill.rbDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'rbDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="saleReturnBill.memo" class="form-control" rows="4">${saleReturnBill.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSaleReturnBillAction!goList.action');">
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
	$("#saleReturnBillForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#saleReturnBillForm").validationEngine('validate')) {
			$("#saleReturnBillForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixSaleReturnBillAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[1]);
					loadContent('', '${nvix}/nvixnt/nvixSaleReturnBillAction!goList.action');
				}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
</script>