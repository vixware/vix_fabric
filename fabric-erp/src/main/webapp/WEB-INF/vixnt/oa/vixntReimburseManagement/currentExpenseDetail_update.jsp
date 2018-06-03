<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="currentExpenseDetailForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntReimburseAction!saveOrUpdateCurrentExpenseDetail.action">
	<input type="hidden" id="id" name="currentExpenseDetail.id" value="${currentExpenseDetail.id}" /> 
	<input type="hidden" id="travelExpenseId" name="currentExpenseDetail.travelExpense.id" value="${currentExpenseDetail.travelExpense.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 岗位:</label>
			<div class="col-md-4">
				<select id="orgPositionId" name="currentExpenseDetail.orgPosition.id" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${orgPositionList}" var="entity">
						<option value="${entity.id}" <c:if test="${currentExpenseDetail.orgPosition.id == entity.id}">selected="selected"</c:if>>${entity.posName}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 区域:</label>
			<div class="col-md-4">
				<select id="areaLevelId" name="currentExpenseDetail.areaLevel.id" class="form-control validate[required]" onblur="check();">
					<option value="">------请选择------</option>
					<c:forEach items="${areaLevelList}" var="entity">
						<option value="${entity.id}" <c:if test="${currentExpenseDetail.areaLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 报销标准:</label>
			<div class="col-md-4">
				<input id="expensesStandard" name="currentExpenseDetail.expensesStandard" value="${currentExpenseDetail.expensesStandard}" class="form-control validate[required]" type="text" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开支时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="expensesDate" name="currentExpenseDetail.expensesDate" value="${currentExpenseDetail.expensesDate}" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'expensesDate'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 月份:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="expensesMonth" name="currentExpenseDetail.expensesMonth" value="${currentExpenseDetail.expensesMonth}" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'expensesMonth'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">报销金额:</label>
			<div class="col-md-4">
				<input id="expensesAmountDetail" name="currentExpenseDetail.expensesAmountDetail" value="${currentExpenseDetail.expensesAmountDetail}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 备注:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="memo" name="currentExpenseDetail.memo">${currentExpenseDetail.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#currentExpenseDetailForm").validationEngine();
	function check(){
		var orgPositionId = $("#orgPositionId").val();
		var areaLevelId = $("#areaLevelId").val();
		if(!orgPositionId){
			layer.alert("请选择岗位!");
			return;
		}
		if(!areaLevelId){
			layer.alert("请选择出差区域!");
			return;
		}
		$.ajax({
			url:'${nvix}/nvixnt/vixntReimburseAction!checkTransportCosts.action?orgPositionId='+orgPositionId+'&areaLevelId='+areaLevelId+'&type=3',
			cache : false,
			success : function(json) {
				$("#expensesStandard").val(json);
			}
		})
	}
</script>
