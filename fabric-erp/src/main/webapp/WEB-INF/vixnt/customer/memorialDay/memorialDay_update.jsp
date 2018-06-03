<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 联系人管理 </span><span>> 纪念日 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixMemorialDayAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>纪念日</h2>
		</header>
		<div class="widget-body">
			<form id="memorialDayForm" class="form-horizontal">
				<input type="hidden" id="id" name="memorialDay.id" value="${memorialDay.id}" /> 
				<input type="hidden" id="createTime" name="memorialDay.createTime" value="${memorialDay.createTimeStr}" /> 
				<input type="hidden" id="updateTime" name="memorialDay.updateTime" value="${memorialDay.updateTimeStr}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="memorialDay.customerAccount.id" value="${memorialDay.customerAccount.id}" /> 
								<input id="customerName" name="memorialDay.customerAccount.name" value="${memorialDay.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>联系人:</label>
						<div class="col-md-3">
							<select id="contactPersonId" name="memorialDay.contactPerson.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonList}" var="entity">
									<option value="${entity.id}" <c:if test="${memorialDay.contactPerson.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>纪念日类型:</label>
						<div class="col-md-3">
							<select id="memorialDayTypeId" name="memorialDay.memorialDayType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${memorialDayTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${memorialDay.memorialDayType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>纪念日:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="date" name="memorialDay.date" value="${memorialDay.dateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'date'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="memorialDay.memo" class="form-control" rows="4">${memorialDay.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixMemorialDayAction!goList.action');">
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
	$("#memorialDayForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#memorialDayForm").validationEngine('validate')) {
			$("#memorialDayForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixMemorialDayAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/nvixMemorialDayAction!goList.action');
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
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadContactPerson();
		});
	}
</script>