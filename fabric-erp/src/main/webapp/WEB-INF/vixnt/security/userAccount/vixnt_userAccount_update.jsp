<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="taskForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixntUserAccountAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="entityForm.id" value="${entity.id}" /> <input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 账号:</label>
			<div class="col-md-4">
				<input id="account" name="entityForm.account" value="${entity.account}" class="form-control validate[required,custom[onlyLetterNumber]]" type="text" onchange="checkUserAccount();" />
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
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 个性首页:</label>
			<div class="col-md-4">
				<select id="loginPage" name="entityForm.loginPage" class="form-control validate[required]">
					<s:iterator value="#request.lpList">
						<option value="${key}">${value}</option>
					</s:iterator>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 菜单显示:</label>
			<div class="col-md-4">
				<select id="userResourceReadType" name="entityForm.userResourceReadType" class="form-control validate[required]">
					<option value="C" <c:if test='${entity.userResourceReadType eq C}'>selected="selected"</c:if>>正常</option>
					<option value="U" <c:if test='${entity.userResourceReadType eq U}'>selected="selected"</c:if>>升级</option>
				</select>
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
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 职员:</label>
			<div class="col-md-10">
				<div class="input-group">
					<input type="hidden" id="employeeId" name="parentId" value="${entity.employee.id}" /> <input id="employeeName" name="" value="${entity.employee.name}" class="form-control validate[required]" type="text" readonly="readonly" />
					<div class="input-group-btn">
						<button onclick="chooseEmployee();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i>
						</button>
						<button onclick="$('#employeeId').val('');$('#employeeName').val('');" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 分配角色:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input type="hidden" id="roleId" name="addUserAccountRoleIds" value="${addUserAccountRoleIds }" /> <input id="roleName" value="${rolenames }" class="form-control" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseRole();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#roleId').val('');$('#roleName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	//分配角色
	function goChooseRole() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goChooseRole.action', 'single', '选择角色', 'role');
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
	//选择人员
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	};
</script>