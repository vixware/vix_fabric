<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 联系人管理 </span><span>> 联系人 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<c:if test="${source == 'contactPerson'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixContactPersonAction!goList.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'customer'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goSaveOrUpdate.action?id=${parentId}&customerAccountType=enterPrise');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>联系人</h2>
		</header>
		<div class="widget-body">
			<form id="contactPersonForm" class="form-horizontal" >
				<input id="source" value="${source}" type="hidden" />
				<input id="id" name="contactPerson.id" value="${contactPerson.id }" type="hidden" />
				<s:if test="parentId != null">
					<input id="parentId" name="contactPerson.customerAccount.id" value="${parentId}" type="hidden" />
				</s:if>
				<s:else>
					<%-- <input id="contactPersonTypeId" name="contactPerson.contactPersonType.id" value="${contactPerson.contactPersonType.id}" type="hidden" /> --%>
					<input id="customerId" name="contactPerson.customerAccount.id" value="${contactPerson.customerAccount.id}" type="hidden" />
					<input id="credentialTypeId" name="contactPerson.credentialType.id" value="${contactPerson.credentialType.id}" type="hidden" />
					<input id="credentialCode" name="contactPerson.credentialCode" value="${contactPerson.credentialCode}" type="hidden" />
				</s:else>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>隶属客户:</label>
						<s:if test="contactPerson.customerAccount.id != null">
							<div class="col-md-3">
								<input id="name" value="${contactPerson.customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
							</div>
						</s:if>
						<s:else>
							<div class="col-md-3">
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group">
											<input id="customerName" type="text" value="${contactPerson.customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" style="cursor: pointer;" readonly="readonly" onfocus="chooseCustomer();" />
											<div class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="$('#customerId').val('');$('#customerName').val('');">清空</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</s:else>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>联系人分类:</label>
						<div class="col-md-3">
							<select id="contactPersonTypeId" name="contactPerson.contactPersonType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${contactPersonTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${contactPerson.contactPersonType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>姓名:</label>
						<div class="col-md-3">
							<input id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>性别:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="contactPerson.sex == 1">active</s:if>"> <input type="radio" value="1" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 1">checked="checked"</s:if> class="validate[required]">男
								</label> <label class="btn btn-default <s:if test="contactPerson.sex == 0">active</s:if>"> <input type="radio" value="0" id="sex" name="contactPerson.sex" <s:if test="contactPerson.sex == 0">checked="checked"</s:if>>女
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>联系人类型:</label>
						<div class="col-md-3">
							<select id="crmContactTypeId" name="contactPerson.crmContactType.id" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${crmContactTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${contactPerson.crmContactType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>负责业务:</label>
						<div class="col-md-3">
							<input id="presideBusiness" name="contactPerson.presideBusiness" value="${contactPerson.presideBusiness}" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>手机1:</label>
						<div class="col-md-3">
							<input id="mobile" name="contactPerson.mobile" value="${contactPerson.mobile}" data-prompt-position="topLeft" class="form-control validate[required,custom[phone]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>邮件:</label>
						<div class="col-md-3">
							<input id="email" name="contactPerson.email" value="${contactPerson.email}" data-prompt-position="topLeft" class="form-control validate[required,custom[email]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">手机2:</label>
						<div class="col-md-3">
							<input id="phone" name="contactPerson.phone" value="${contactPerson.phone}" class="form-control validate[custom[phone]]" type="text" />
						</div>
						<label class="col-md-2 control-label">称谓:</label>
						<div class="col-md-3">
							<input id="callTitle" name="contactPerson.callTitle" value="${contactPerson.callTitle}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">部门:</label>
						<div class="col-md-3">
							<input id="department" name="contactPerson.department" value="${contactPerson.department}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">职务:</label>
						<div class="col-md-3">
							<input id="position" name="contactPerson.position" value="${contactPerson.position}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">工作电话:</label>
						<div class="col-md-3">
							<input id="directPhone" name="contactPerson.directPhone" value="${contactPerson.directPhone}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">传真:</label>
						<div class="col-md-3">
							<input id="fax" name="contactPerson.fax" value="${contactPerson.fax}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">生日:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="birthday" name="contactPerson.birthday" value="${contactPerson.birthdayStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label">家庭电话:</label>
						<div class="col-md-3">
							<input id="phoneHome" name="contactPerson.phoneHome" value="${contactPerson.phoneHome}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">微信:</label>
						<div class="col-md-3">
							<input id="msnAccount" name="contactPerson.msnAccount" value="${contactPerson.msnAccount}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">QQ:</label>
						<div class="col-md-3">
							<input id="qqAccount" name="contactPerson.qqAccount" value="${contactPerson.qqAccount}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">SKYPE:</label>
						<div class="col-md-3">
							<input id="skypeAccount" name="contactPerson.skypeAccount" value="${contactPerson.skypeAccount}" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label">旺旺:</label>
						<div class="col-md-3">
							<input id="wangAccount" name="contactPerson.wangAccount" value="${contactPerson.wangAccount}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">创建人:</label>
						<div class="col-md-3">
							<input id="createdBy" name="contactPerson.createdBy" value="${contactPerson.createdBy}" class="form-control" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label">创建日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="dateEntered" name="contactPerson.dateEntered" value="${contactPerson.dateEnteredStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'dateEntered'});"><i
									class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">爱好:</label>
						<div class="col-md-8">
							<input id="hobby" name="contactPerson.hobby" value="${contactPerson.hobby}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">家庭住址:</label>
						<div class="col-md-8">
							<input id="address" name="contactPerson.address" value="${contactPerson.address}" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="contactPerson.memo" class="form-control" >${contactPerson.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<c:if test="${source == 'contactPerson'}">
								<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixContactPersonAction!goList.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</c:if>
							<c:if test="${source == 'customer'}">
								<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goSaveOrUpdate.action?id=${parentId}&customerAccountType=enterPrise');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</c:if>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#contactPersonForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#contactPersonForm").validationEngine('validate')) {
			var mobile = $("#mobile").val();
			if (mobile && !/^0?1[3|4|5|7|8][0-9]\d{8}$/.test(mobile)) {
				layer.alert('手机1输入有误，请您重新输入');
				return;
			}
			var phone = $("#phone").val();
			if (phone && !/^0?1[3|4|5|7|8][0-9]\d{8}$/.test(phone)) {
				layer.alert('手机2输入有误，请您重新输入');
				return;
			}
			$("#contactPersonForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixContactPersonAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				var source = $("#source").val();
				if(source == 'contactPerson'){
					loadContent('', '${nvix}/nvixnt/nvixContactPersonAction!goList.action');
				}else{
					loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goSaveOrUpdate.action?id='+$("#parentId").val()+'&customerAccountType=enterPrise');
				}
			}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
</script>