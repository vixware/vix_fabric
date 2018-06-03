<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>> 售前管理 </span><span>>销售线索 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixSaleLeadAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>销售线索</h2>
		</header>
		<div class="widget-body">
			<form id="saleLeadForm" class="form-horizontal">
				<input type="hidden" id="id" name="saleLead.id" value="${saleLead.id}" /> 
				<input type="hidden" id="isDeleted" name="saleLead.isDeleted" value="${saleLead.isDeleted}" /> 
				<fieldset>
					<legend>线索信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>线索主题:</label>
						<div class="col-md-3">
							<input id="subject" name="saleLead.subject" value="${saleLead.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发现时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="findOutTime" name="saleLead.findOutTime" value="${saleLead.findOutTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'findOutTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="saleLead.customerAccount.id" value="${saleLead.customerAccount.id}" /> 
								<input id="customerName" name="saleLead.customerAccount.name" value="${saleLead.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="saleLead.contactPerson.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleLead.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">项目:</label>
						<div class="col-md-3">
							<select id="crmProjectId" name="saleLead.crmProject.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${crmProjectList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleLead.crmProject.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<legend>基本情况</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>头衔:</label>
						<div class="col-md-3">
							<input id="title" name="saleLead.title" value="${saleLead.title}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>部门:</label>
						<div class="col-md-3">
							<input id="department" name="saleLead.department" value="${saleLead.department}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div> --%>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>创建人:</label>
						<div class="col-md-3">
							<input id="createdBy" name="saleLead.createdBy" value="${saleLead.createdBy}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>类型:</label>
						<div class="col-md-3">
							<input id="type" name="saleLead.type" value="${saleLead.type}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>提供人:</label>
						<div class="col-md-3">
							<input id="sourcePerson" name="saleLead.sourcePerson" value="${saleLead.sourcePerson}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>来源:</label>
						<div class="col-md-3">
							<input id="leadSource" name="saleLead.leadSource" value="${saleLead.leadSource}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>概率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="probability" name="saleLead.probability" value="${saleLead.probability}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">客户需求:</label>
						<div class="col-md-3">
							<input id="requirement" name="saleLead.requirement" value="${saleLead.requirement}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">输入日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dateEntered" name="saleLead.dateEntered" value="${saleLead.dateEnteredStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dateEntered'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">修改日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dateModified" name="saleLead.dateModified" value="${saleLead.dateModifiedStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dateModified'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">下一步计划:</label>
						<div class="col-md-3">
							<input id="nextStepPlan" name="saleLead.nextStepPlan" value="${saleLead.nextStepPlan}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-3">
							<input id="memo" name="saleLead.memo" value="${saleLead.memo}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<legend>费用信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label">金额:</label>
						<div class="col-md-3">
							<input id="amount" name="saleLead.amount" value="${saleLead.amount}" data-prompt-position="topLeft" class="form-control validate[custom[number]]" type="text" />
						</div>
						<label class="col-md-2 control-label">货币类型:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="saleLead.currencyType.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${saleLead.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">关闭日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dateClosed" name="saleLead.dateClosed" value="${saleLead.dateClosedStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dateClosed'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixSaleLeadAction!goList.action');">
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
	$("#saleLeadForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#saleLeadForm").validationEngine('validate')) {
			$("#saleLeadForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixSaleLeadAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixSaleLeadAction!goList.action');
			}
			});
		}
	}

	/** 加载联系人*/
	function loadContactPerson() {
		if ($("#customerId").val() != '') {
			$.ajax({
			url : '${nvix}/nvixnt/nvixContactPersonAction!loadContactPersonOption.action?parentId=' + $("#customerId").val(),
			cache : false,
			success : function(html) {
				var contactPersonId = $("#contactPersonId").val();
				$("#contactPersonId").html(html);
				if (contactPersonId != '') {
					$("#contactPersonId").val(contactPersonId);
				}
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
			loadContactPerson();
			loadCrmProject();
		});
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
</script>