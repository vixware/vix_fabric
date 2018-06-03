<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="userAccountForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/vixntSupplierAccountAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="entityForm.id" value="${entity.id}" /> 
	<input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value="${addUserAccountRoleIds }" /> 
	<input type="hidden" id="parentId" name="parentId" value="${parentId}" /> 
	<input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds"
		value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 账号:</label>
			<div class="col-md-4">
				<input id="account" name="entityForm.account" value="${entity.account}" class="form-control validate[required]" type="text" onchange="checkUserAccount();" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否禁用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="entity.enable == 1">active</s:if>"> <input type="radio" id="status1" name="entityForm.enable" data-prompt-position="topLeft" class="validate[required]" <s:if test="entity.enable == 1"> checked="checked"</s:if> value="1" />否
					</label> <label class="btn btn-default <s:if test="entity.enable == 0">active</s:if>"> <input type="radio" id="status2" name="entityForm.enable" data-prompt-position="topLeft" class="validate[required]" <s:if test="entity.enable == 0"> checked="checked"</s:if> value="0" />是
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 密码:</label>
			<div class="col-md-4">
				<input id="password" name="entityForm.password" value="${entity.initpassword}" class="form-control validate[required]" type="password" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 确认密码:</label>
			<div class="col-md-4">
				<input id="passwordConfirm" name="entityForm.passwordConfirm" value="${entity.initpassword}" class="form-control validate[required]" type="password" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-4">
				<input id="taskStartTime" name="entityForm.startTime" value="${entity.startTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
			<div class="col-md-4">
				<input id="taskEndTime" name="entityForm.endTime" value="${entity.endTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 分配角色:</label>
			<div class="col-md-7">
				<input id="rolename" value="${rolenames }" class="form-control" type="text" readonly="readonly" />
			</div>
			<div class="col-md-3">
				<button onclick="goChooseRole();" type="button" class="btn btn-info">
					<i class="glyphicon glyphicon-search"></i> 选择
				</button>
				<button onclick="$('#employeeNames').val('');" type="button" class="btn btn-default">
					<i class="glyphicon glyphicon-repeat"></i> 清空
				</button>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//分配角色
	function goChooseRole() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goChooseRole.action', 'multi', '选择角色', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#addUserAccountRoleIds').val(emp[0]);
				$('#rolename').val(emp[1]);
			} else {
				layer.alert("请选择角色!");
			}
		});
	};

	function checkUserAccount() {
		$.ajax({
		url : '${nvix}/nvixnt/nvixntUserAccountAction!checkUserAccount.action?account=' + $('#account').val() + "&id=" + $('#id').val(),
		cache : false,
		success : function(error) {
			if (error != '') {
				layer.alert(error);
			}
		}
		});
	}
</script>