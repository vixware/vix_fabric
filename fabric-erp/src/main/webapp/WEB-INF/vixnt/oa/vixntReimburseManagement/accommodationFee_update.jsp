<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="accommodationFeeForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntReimburseAction!saveOrUpdateAccommodationFee.action">
	<input type="hidden" id="id" name="accommodationFee.id" value="${accommodationFee.id}" /> 
	<input type="hidden" id="travelExpenseId" name="accommodationFee.travelExpense.id" value="${accommodationFee.travelExpense.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 岗位:</label>
			<div class="col-md-4">
				<select id="orgPositionId" name="accommodationFee.orgPosition.id" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${orgPositionList}" var="entity">
						<option value="${entity.id}" <c:if test="${accommodationFee.orgPosition.id == entity.id}">selected="selected"</c:if>>${entity.posName}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 出差区域:</label>
			<div class="col-md-4">
				<select id="areaLevelId" name="accommodationFee.areaLevel.id" class="form-control validate[required]" onblur="check();">
					<option value="">------请选择------</option>
					<c:forEach items="${areaLevelList}" var="entity">
						<option value="${entity.id}" <c:if test="${accommodationFee.areaLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 住宿标准:</label>
			<div class="col-md-4">
				<input id="lodgingFee" name="accommodationFee.lodgingFee" value="${accommodationFee.lodgingFee}" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 住宿地点:</label>
			<div class="col-md-4">
				<input id="accomodationsPlaces" name="accommodationFee.accomodationsPlaces" value="${accommodationFee.accomodationsPlaces}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">房价:</label>
			<div class="col-md-4">
				<input id="housePrices" name="accommodationFee.housePrices" value="${accommodationFee.housePrices}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label">天数:</label>
			<div class="col-md-4">
				<input id="days" name="accommodationFee.days" value="${accommodationFee.days}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="startAccomodationsTime" name="accommodationFee.startAccomodationsTime" value="${accommodationFee.startAccomodationsTime}" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startAccomodationsTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="endAccomodationsTime" name="accommodationFee.endAccomodationsTime" value="${accommodationFee.endAccomodationsTime}" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endAccomodationsTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 消费金额:</label>
			<div class="col-md-4">
				<input id="cost" name="accommodationFee.cost" value="${accommodationFee.cost}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 备注:</label>
			<div class="col-md-10">
				<textarea class="form-control" rows="4" id="memo" name="accommodationFee.memo">${accommodationFee.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#accommodationFeeForm").validationEngine();
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
			url:'${nvix}/nvixnt/vixntReimburseAction!checkTransportCosts.action?orgPositionId='+orgPositionId+'&areaLevelId='+areaLevelId+'&type=2',
			cache : false,
			success : function(json) {
				$("#lodgingFee").val(json);
			}
		})
	}
</script>
