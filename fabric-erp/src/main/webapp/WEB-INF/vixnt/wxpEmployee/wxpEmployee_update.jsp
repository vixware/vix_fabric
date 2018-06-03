<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-user"></i> 系统管理 <span onclick="">&gt; 员工管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/wxpEmployeeAction!goList.action');">
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
				<input type="hidden" id="employeeId" name="emp.id" value="${emp.id}" /> <input type="hidden" id="headImgUrl" name="emp.headImgUrl" value="${emp.headImgUrl}" /> <input type="hidden" id="organizationId" name="emp.organizationUnit.id" value="${emp.organizationUnit.id}" /> <input type="hidden"
					id="deptId" name="deptId" value="${deptId}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 员工职号:</label>
						<div class="col-md-3">
							<input id="staffJobNumber" name="emp.staffJobNumber" value="${emp.staffJobNumber}" data-prompt-position="centerRight" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 姓名:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="name" name="emp.name" value="${emp.name}" data-prompt-position="centerRight" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa fa-user"></i> </span>
							</div>
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
						<label class="col-md-2 control-label"> 身份证号码:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="idNumber" name="emp.idNumber" value="${emp.idNumber}" data-prompt-position="centerRight" class="form-control validate[custom[chinaId]]" type="text" /> <span class="input-group-addon"><i class="fa fa-credit-card"></i> </span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 部门:</label>
						<div class="col-md-3">
							<input id="organizationUnitName" name="emp.organizationUnit.fs" value="${emp.organizationUnit.fs}" data-prompt-position="centerRight" class="form-control" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label"> 联系电话:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="telephone" name="emp.telephone" value="${emp.telephone}" data-prompt-position="centerRight" class="form-control validate[custom[phone]]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i> </span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 微信:</label>
						<div class="col-md-3">
							<input id="weixinid" name="emp.weixinid" value="${emp.weixinid}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 邮箱:</label>
						<div class="col-md-3">
							<input id="email" name="emp.email" value="${emp.email}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 微信企业号账号:</label>
						<div class="col-md-3">
							<input id="userId" name="emp.userId" value="${emp.userId}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 地址:</label>
						<div class="col-md-3">
							<input id="residenceAddress" name="emp.residenceAddress" value="${emp.residenceAddress}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 民族:</label>
						<div class="col-md-3">
							<input id="national" name="emp.national" value="${emp.national}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 籍贯:</label>
						<div class="col-md-3">
							<input id="birthplace" name="emp.birthplace" value="${emp.birthplace}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 学历:</label>
						<div class="col-md-3">
							<input id="qualificationsCode" name="emp.qualificationsCode" value="${emp.qualificationsCode}" data-prompt-position="centerRight" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 密级:</label>
						<div class="col-md-3">
							<select id="declassified" name="emp.declassified" class="form-control validate[required]">
								<option value="1" <c:if test='${emp.declassified eq 1}'>selected="selected"</c:if>>1级</option>
								<option value="2" <c:if test='${emp.declassified eq 2}'>selected="selected"</c:if>>2级</option>
								<option value="3" <c:if test='${emp.declassified eq 3}'>selected="selected"</c:if>>3级</option>
								<option value="4" <c:if test='${emp.declassified eq 4}'>selected="selected"</c:if>>4级</option>
								<option value="4" <c:if test='${emp.declassified eq 5}'>selected="selected"</c:if>>5级</option>
								<option value="4" <c:if test='${emp.declassified eq 6}'>selected="selected"</c:if>>6级</option>
								<option value="4" <c:if test='${emp.declassified eq 7}'>selected="selected"</c:if>>7级</option>
								<option value="4" <c:if test='${emp.declassified eq 8}'>selected="selected"</c:if>>8级</option>
								<option value="4" <c:if test='${emp.declassified eq 9}'>selected="selected"</c:if>>9级</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 是否可以查看全部数据:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="emp.isViewData == 0">active</s:if>"> <input type="radio" value="0" id="isViewData" name="emp.isViewData" <s:if test="emp.isViewData == 0">checked="checked"</s:if>>否
								</label> <label class="btn btn-default <s:if test="emp.isViewData == 1">active</s:if>"> <input type="radio" value="1" id="isViewData" name="emp.isViewData" <s:if test="emp.isViewData == 1">checked="checked"</s:if>>是
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="emp.memo" class="form-control" rows="4">${emp.memo }</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/wxpEmployeeAction!goList.action');">
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
			url : "${nvix}/nvixnt/wxpEmployeeAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/wxpEmployeeAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>