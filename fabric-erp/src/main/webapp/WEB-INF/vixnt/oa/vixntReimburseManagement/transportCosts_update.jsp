<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="transportCostsForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntReimburseAction!saveOrUpdateTransportCosts.action">
	<input type="hidden" id="id" name="transportCosts.id" value="${transportCosts.id}" /> 
	<input type="hidden" id="transportCostsTravelExpenseId" name="transportCosts.travelExpense.id" value="${transportCosts.travelExpense.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 岗位:</label>
			<div class="col-md-4">
				<select id="orgPositionId" name="transportCosts.orgPosition.id" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${orgPositionList}" var="entity">
						<option value="${entity.id}" <c:if test="${transportCosts.orgPosition.id == entity.id}">selected="selected"</c:if>>${entity.posName}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 出差区域:</label>
			<div class="col-md-4">
				<select id="areaLevelId" name="transportCosts.areaLevel.id" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${areaLevelList}" var="entity">
						<option value="${entity.id}" <c:if test="${transportCosts.areaLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 交通工具:</label>
			<div class="col-md-4">
				<select id="transportId" name="transportCosts.transport.id" class="form-control validate[required]" onblur="check();">
					<option value="">------请选择------</option>
					<c:forEach items="${transportList}" var="entity">
						<option value="${entity.id}" <c:if test="${transportCosts.transport.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 报销标准:</label>
			<div class="col-md-4">
				<input id="reimbursementAmount" name="transportCosts.reimbursementAmount" value="${transportCosts.reimbursementAmount}" class="form-control validate[required]" readonly="readonly" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 出发地:</label>
			<div class="col-md-4">
				<input id="fromPlace" name="transportCosts.fromPlace" value="${transportCosts.fromPlace}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 目的地:</label>
			<div class="col-md-4">
				<input id="toPlace" name="transportCosts.toPlace" value="${transportCosts.toPlace}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 乘坐时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="usingTime" name="transportCosts.usingTime" value="${transportCosts.usingTime}" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'usingTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 费用:</label>
			<div class="col-md-4">
				<input id="cost" name="transportCosts.cost" value="${transportCosts.cost}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 备注:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="memo" name="transportCosts.memo">${transportCosts.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#transportCostsForm").validationEngine();
	function check(){
		var transportId = $("#transportId").val();
		var orgPositionId = $("#orgPositionId").val();
		var areaLevelId = $("#areaLevelId").val();
		if(!transportId){
			layer.alert("请选择交通工具!");
			return;
		}
		if(!orgPositionId){
			layer.alert("请选择岗位!");
			return;
		}
		if(!areaLevelId){
			layer.alert("请选择出差区域!");
			return;
		}
		$.ajax({
			url:'${nvix}/nvixnt/vixntReimburseAction!checkTransportCosts.action?transportId='+transportId+'&orgPositionId='+orgPositionId+'&areaLevelId='+areaLevelId+'&type=1',
			cache : false,
			success : function(json) {
				$("#reimbursementAmount").val(json);
			}
		})
	}
</script>
