<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售中管理 </span><span>>费用支出 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixExpensesAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>费用支出</h2>
		</header>
		<div class="widget-body">
			<form id="expensesForm" class="form-horizontal" >
				<input type="hidden" id="id" name="expenses.id" value="${expenses.id}" /> 
				<input type="hidden" id="isDeleted" name="expenses.isDeleted" value="${expenses.isDeleted}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>金额:</label>
						<div class="col-md-3">
							<input id="amount" name="expenses.amount" value="${expenses.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>票据张数:</label>
						<div class="col-md-3">
							<input id="num" name="expenses.num" value="${expenses.num}" data-prompt-position="topLeft" class="form-control validate[required,custom[integer]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>费用类型:</label>
						<div class="col-md-3">
							<select id="expenseTypeId" name="expenses.expenseType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${expenseTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${expenses.expenseType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>支出日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="expensesDate" name="expenses.expensesDate" value="${expenses.expensesDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'expensesDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>支出单号:</label>
						<div class="col-md-3">
							<input id="expensesBillNumber" name="expenses.expensesBillNumber" value="${expenses.expensesBillNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div> --%>
					</div>
					<div class="form-group">
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>发票号:</label>
						<div class="col-md-3">
							<input id="invoice" name="expenses.invoice" value="${expenses.invoice}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div> --%>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="expenses.customerAccount.id" value="${expenses.customerAccount.id}" /> 
								<input id="customerName" name="expenses.customerAccount.name" value="${expenses.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<select id="crmProjectId" name="expenses.crmProject.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${crmProjectList}" var="entity">
									<option value="${entity.id}" <c:if test="${expenses.crmProject.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>业务员:</label>
						<div class="col-md-3">
							<input type="hidden" id="employeeId" name="expenses.employee.id" value="${expenses.employee.id}" /> 
							<input id="employeeName" name="expenses.employee.name" value="${expenses.employee.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>货币类型:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="expenses.currencyType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${expenses.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">用途:</label>
						<div class="col-md-8">
							<textarea id="purpose" name="expenses.purpose" class="form-control" rows="4">${expenses.purpose}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="expenses.memo" class="form-control" rows="4">${expenses.memo}</textarea>
						</div>
					</div>
					<%-- <div class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>费用支出明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin:5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="商品名称" class="form-control" id="searchItemName">
												</div>
												<button onclick="expensesDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left" >
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchItemName').val('');expensesDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right" >
													<button onclick="addExpensesDetail();" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
													</button>
												</div>
											</div>
										</div>
									    <table id="expensesDetailTableId" class="table table-striped table-bordered table-hover" width="100%">
									   		<thead>			                
												<tr>
													<th width="10%">编号</th>
													<th>费用项目</th>
													<th>商品名称</th>
													<th>金额</th>
													<th>规格</th>
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
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixExpensesAction!goList.action');">
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
	$("#expensesForm").validationEngine();
	
	var expensesDetailColumns = [
   		{"orderable":false,"data" : function(data) {return '&nbsp;'}},
   		{"orderable":false,"data" : function(data) {return data.expensesItem;}},
   		{"orderable":false,"data" : function(data) {return data.goodsName;}},
   		{"orderable":false,"data" : function(data) {return data.amount;}},
   		{"orderable":false,"data" : function(data) {return data.specification;}},
   		{"orderable":false,"data" : function(data) {
   			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateExpensesDetail('" + data.id + "','accessories');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
   			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteDetailById('" + data.id + "','accessories');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
   			return update + "&nbsp;&nbsp;" + del;
   		}}
   	];
                 	             	                         	             	
	var expensesDetailTable = initDataTable("expensesDetailTableId","${nvix}/nvixnt/nvixExpensesAction!getExpensesDetailJson.action",expensesDetailColumns,function(page,pageSize,orderField,orderBy){
		var name = $('#searchItemName').val();
		name = Url.encode(name);
    	var id = $("#id").val();
   		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   	},10,'1','1');
     	
	function addExpensesDetail(){
   		var id = $("#id").val();
   		if(id == ''){
   			if($('#expensesForm').validationEngine('validate')){
   				$("#expensesForm").ajaxSubmit({
   					type : "post",
   					url : "${nvix}/nvixnt/nvixExpensesAction!saveOrUpdate.action?id=" + $("#id").val(),
   					contentType : "application/x-www-form-urlencoded; charset=utf-8",
   					success : function(result) {
   						var r = result.split(":");
   						if(r[0] != '0'){
   							$("#id").val($.trim(r[0]));
   							saveOrUpdateExpensesDetail('0');
   						}
   					}
   				});
   			}
   		}else {
   			saveOrUpdateExpensesDetail('0');
   		}
      	}
      	
      	/** 添加发运计划明细*/
      	function saveOrUpdateExpensesDetail(id){
   		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixExpensesAction!addExpensesDetail.action?id=' + id,'expensesDetailForm','费用支出明细',720,350,expensesDetailTable,function(){
   			$("#expensesId").val($("#id").val());
   		},function(){
   			expensesDetailTable.ajax.reload();
   		});
      	}
      	
     //删除
   	function deleteDetailById(id){
   		deleteEntityByConfirm('${nvix}/nvixnt/nvixExpensesAction!deleteExpensesDetailById.action?id='+id,'是否删除费用支出明细?',expensesDetailTable);
   	}

	function saveOrUpdate(id) {
		//表单验证
		if ($("#expensesForm").validationEngine('validate')) {
			$("#expensesForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixExpensesAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					showMessage(r[1]);
					loadContent('', '${nvix}/nvixnt/nvixExpensesAction!goList.action');
				}
			});
		}
	}
	
	// 加载项目
	function loadCrmProject() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixCrmProjectAction!loadCrmProject.action?id=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var crmProjectId = $("#crmProjectId").val();
				$("#crmProjectId").html(html);
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadCrmProject();
		});
	}
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>