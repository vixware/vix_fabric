<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="calculationForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixContactPersonAction!saveOrUpdate.action" method="post" enctype="multipart/form-data">
	<input id="id" name="contactPerson.id" value="${contactPerson.id }" type="hidden" />
	<fieldset>
		<h6>工时计算方法</h6>
		<div class="form-group">
			<label class="col-md-3 control-label">工时计算取整:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-3">
				<select id="currencyTypeId" name="salesOrder.currencyType.id" class="form-control">
					<option value="">------请选择------</option>
					<%-- <c:forEach items="${currencyTypeList}" var="entity"> --%>
					<%-- <option value="${entity.id}" <c:if test="${salesOrder.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option> --%>
					<option value="四舍五入">四舍五入</option>
					<option value="去尾">去尾</option>
					<option value="进一">进一</option>
					<%-- </c:forEach> --%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">加班计算取整:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-3">
				<select id="currencyTypeId" name="salesOrder.currencyType.id" class="form-control">
					<option value="">------请选择------</option>
					<%-- <c:forEach items="${currencyTypeList}" var="entity"> --%>
					<%-- <option value="${entity.id}" <c:if test="${salesOrder.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option> --%>
					<option value="四舍五入">四舍五入</option>
					<option value="去尾">去尾</option>
					<option value="进一">进一</option>
					<%-- </c:forEach> --%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">迟到早退取整:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-3">
				<select id="currencyTypeId" name="salesOrder.currencyType.id" class="form-control">
					<option value="">------请选择------</option>
					<%-- <c:forEach items="${currencyTypeList}" var="entity"> --%>
					<%-- <option value="${entity.id}" <c:if test="${salesOrder.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option> --%>
					<option value="四舍五入">四舍五入</option>
					<option value="去尾">去尾</option>
					<option value="进一">进一</option>
					<%-- </c:forEach> --%>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">请假计算取整:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-1 control-label"></label>
			<div class="col-md-3">
				<select id="currencyTypeId" name="salesOrder.currencyType.id" class="form-control">
					<option value="">------请选择------</option>
					<%-- <c:forEach items="${currencyTypeList}" var="entity"> --%>
					<%-- <option value="${entity.id}" <c:if test="${salesOrder.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option> --%>
					<option value="四舍五入">四舍五入</option>
					<option value="去尾">去尾</option>
					<option value="进一">进一</option>
					<%-- </c:forEach> --%>
				</select>
			</div>
		</div>
		<h6>其他计算</h6>
		<div class="form-group">
			<label class="col-md-3 control-label">计算保留小数:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">位</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">上班时间在：</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-1 control-label">~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">时段工时在：</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
			</div>
			<label class="col-md-1 control-label">~&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">迟到早退精确到秒:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">迟到早退精确到秒:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">套班不成功列出打卡记录:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//表单校验
	$("#calculationForm").validationEngine();
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/taskAndPlanAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	}
</script>