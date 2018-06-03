<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-institution"></i> 连锁管理 <span>> 连锁店铺管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_storeperson','${nvix}/nvixnt/vixntDistributionSystemAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>员工信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="employeeForm">
				<input type="hidden" id="employeeId" name="emp.id" value="${emp.id}" /> <input type="hidden" id="channelDistributorId" name="emp.channelDistributor.id" value="${emp.channelDistributor.id}" />
				<fieldset>
					<legend>基本信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 员工类型:</label>
						<div class="col-md-3">
							<select id="userType" name="emp.userType" class="form-control">
								<option value="0" <c:if test='${emp.userType eq 0}'>selected="selected"</c:if>>管理员</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 姓名:</label>
						<div class="col-md-3">
							<input id="name" name="emp.name" value="${emp.name}" data-prompt-position="centerRight" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系电话:</label>
						<div class="col-md-3">
							<input id="telephone" name="emp.telephone" value="${emp.telephone}" data-prompt-position="centerRight" class="form-control validate[required,custom[phone]]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 身份证号码:</label>
						<div class="col-md-3">
							<input id="idNumber" name="emp.idNumber" value="${emp.idNumber}" data-prompt-position="centerRight" class="form-control validate[custom[chinaId]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 性别:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="emp.gender == 0">active</s:if>"> <input type="radio" value="0" id="gender" name="emp.gender" <s:if test="emp.gender == 0">checked="checked"</s:if>>男
								</label> <label class="btn btn-default <s:if test="emp.gender == 1">active</s:if>"> <input type="radio" value="1" id="gender" name="emp.gender" <s:if test="emp.gender == 1">checked="checked"</s:if>>女
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"> 学历:</label>
						<div class="col-md-3">
							<input id="qualificationsCode" name="emp.qualificationsCode" value="${emp.qualificationsCode}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 民族:</label>
						<div class="col-md-3">
							<input id="national" name="emp.national" value="${emp.national}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 籍贯:</label>
						<div class="col-md-3">
							<input id="birthplace" name="emp.birthplace" value="${emp.birthplace}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 户籍地址:</label>
						<div class="col-md-3">
							<input id="residenceAddress" name="emp.residenceAddress" value="${emp.residenceAddress}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="emp.memo" class="form-control" rows="4">${emp.memo }</textarea>
						</div>
					</div>
					<legend>账号信息:</legend>
					<input type="hidden" id="id" name="entityForm.id" value="${entity.id}" /> <input type="hidden" id="roleId" name="addUserAccountRoleIds" value="${addUserAccountRoleIds }" /> <input type="hidden" id="parentId" name="parentId" value="${parentId}" /> <input type="hidden"
						id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 账号:</label>
						<div class="col-md-3">
							<input id="account" name="entityForm.account" value="${entity.account}" class="form-control validate[required]" type="text" onchange="checkUserAccount();" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>是否禁用:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="entity.enable == 1">active</s:if>"> <input type="radio" id="status1" name="entityForm.enable" data-prompt-position="topLeft" class="validate[required]" <s:if test="entity.enable == 1"> checked="checked"</s:if> value="1" />否
								</label> <label class="btn btn-default <s:if test="entity.enable == 0">active</s:if>"> <input type="radio" id="status2" name="entityForm.enable" data-prompt-position="topLeft" class="validate[required]" <s:if test="entity.enable == 0"> checked="checked"</s:if> value="0" />是
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 密码:</label>
						<div class="col-md-3">
							<input id="password" name="entityForm.password" value="${entity.initpassword}" class="form-control validate[required]" type="password" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 确认密码:</label>
						<div class="col-md-3">
							<input id="passwordConfirm" name="entityForm.passwordConfirm" value="${entity.initpassword}" class="form-control validate[required]" type="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
						<div class="col-md-3">
							<input id="taskStartTime" name="entityForm.startTime" value="${entity.startTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束时间:</label>
						<div class="col-md-3">
							<input id="taskEndTime" name="entityForm.endTime" value="${entity.endTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 分配角色:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="roleName" value="${rolenames }" class="form-control" type="text" readonly="readonly" />
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
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_storeperson','${nvix}/nvixnt/vixntDistributionSystemAction!goList.action');">
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
	$("#employeeForm").validationEngine();
	function saveOrUpdate() {
		if ($("#employeeForm").validationEngine('validate')) {
			$("#employeeForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntDistributionSystemAction!saveOrUpdateStorePerson.action",
			dataType : "text",
			success : function(id) {
				loadContent('drp_storeperson', '${nvix}/nvixnt/vixntDistributionSystemAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	//分配角色
	function goChooseRole() {
		chooseListData('${nvix}/nvixnt/nvixntRoleAction!goChooseShopRole.action', 'single', '选择角色', 'role');
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