<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i>客户关系管理 <span>>客户管理  </span><span>>客户列表 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goList.action?syncTag=' +$('#type').val());">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>
				<s:if test="customerAccountType == 'enterPrise'">
					企业客户<small>（<span class="text-danger">*</span>表示必填）</small>
				</s:if>
				<s:if test="customerAccountType == 'internal'">
					内部客户<small>（<span class="text-danger">*</span>表示必填）</small>
				</s:if>
				<s:if test="customerAccountType == 'personal'">
					个人客户<small>（<span class="text-danger">*</span>表示必填）</small>
				</s:if>
			</h2>
		</header>
		<div class="widget-body">
			<form id="customerAccountForm" class="form-horizontal">
				<input type="hidden" id="customerId" name="customerAccount.id" value="${customerAccount.id}" /> 
				<input type="hidden" id="isHighSea" name="customerAccount.isHighSea" value="${customerAccount.isHighSea}" /> 
				<input type="hidden" id="type" name="customerAccount.type" value="${customerAccount.type}" /> 
				<input type="hidden" id="isDeleted" name="customerAccount.isDeleted" value="${customerAccount.isDeleted}" />
				<input type="hidden" id="isReceive" name="customerAccount.isReceive" value="${customerAccount.isReceive}" />
				<input type="hidden" id="createTime" name="customerAccount.createTime" value="${customerAccount.createTimeStr}" />
				<input type="hidden" id="updateTime" name="customerAccount.updateTime" value="${customerAccount.updateTimeStr}" />
				<s:if test="customerAccountType == 'personal'">
					<input type="hidden" id="cpId" name="contactPerson.id" value="${contactPerson.id}" />
					<input type="hidden" id="isDeleted" name="contactPerson.isDeleted" value="${contactPerson.isDeleted}" />
					<input type="hidden" id="source" value="${source}" />
					<s:if test="source =='member'">
						<input type="hidden" id="contactPerson.isBlack" vlaue="${contactPerson.isBlack}" />
						<input type="hidden" id="contactPerson.blackReason" vlaue="${contactPerson.blackReason}" />
					</s:if>
					<s:else>
						<input type="hidden" id="lastName" name="contactPerson.lastName" value="${contactPerson.lastName}" />
						<input type="hidden" id="sex" name="contactPerson.sex" value="${contactPerson.sex}" />
						<input type="hidden" id="crmContactTypeId" name="contactPerson.crmContactType.id" value="${contactPerson.crmContactType.id}" />
						<input type="hidden" id="presideBusiness" name="contactPerson.presideBusiness" value="${contactPerson.presideBusiness}" />
						<input type="hidden" id="callTitle" name="contactPerson.callTitle" value="${contactPerson.callTitle}" />
						<input type="hidden" id="company" name="contactPerson.company" value="${contactPerson.company}" />
						<input type="hidden" id="department" name="contactPerson.department" value="${contactPerson.department}" />
						<input type="hidden" id="position" name="contactPerson.position" value="${contactPerson.position}" />
						<input type="hidden" id="fax" name="contactPerson.fax" value="${contactPerson.fax}" />
						<input type="hidden" id="phoneHome" name="contactPerson.phoneHome" value="${contactPerson.phoneHome}" />
						<input type="hidden" id="msnAccount" name="contactPerson.msnAccount" value="${contactPerson.msnAccount}" />
						<input type="hidden" id="qqAccount" name="contactPerson.qqAccount" value="${contactPerson.qqAccount}" />
						<input type="hidden" id="skypeAccount" name="contactPerson.skypeAccount" value="${contactPerson.skypeAccount}" />
						<input type="hidden" id="wangAccount" name="contactPerson.wangAccount" value="${contactPerson.wangAccount}" />
						<input type="hidden" id="createdBy" name="contactPerson.createdBy" value="${contactPerson.createdBy}" />
						<input type="hidden" id="dateEntered" name="contactPerson.dateEntered" value="${contactPerson.dateEntered}" />
					</s:else>
				</s:if>
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户分类:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="treeMenu" class="input-group">
										<input id="customerAccountCategoryId" name="customerAccount.customerAccountCategory.id" type="hidden" value="${customerAccount.customerAccountCategory.id}" /> 
										<input id="customerAccountCategoryName" type="text" onfocus="showICMenu(); return false;" value="${customerAccount.customerAccountCategory.name}" type="text" class="form-control validate[required]" style="cursor: pointer;" readonly="readonly" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#customerAccountCategoryId').val('');$('#customerAccountCategoryName').val('');">清空</button>
										</div>
										<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="customerAccountCategoryTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label">所有者:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="customerAccount.employee.id" value="${customerAccount.employee.id}" /> 
								<input id="employeeName" name="customerAccount.employee.name" value="${customerAccount.employee.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseEmployee();">选择</span>
							</div>
						</div>
					</div>
					<s:if test="customerAccountType != 'personal'">
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
							<div class="col-md-3">
								<input id="code" name="customerAccount.code" value="${customerAccount.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>客户名称:</label>
							<div class="col-md-3">
								<input id="name" name="customerAccount.name" value="${customerAccount.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
						</div>
					</s:if>
					<s:if test="customerAccountType == 'internal'">
						<div class="form-group">
							<label class="col-md-2 control-label">客户类型:</label>
							<div class="col-md-3">
								<input value="内部客户" class="form-control" type="text" readonly="readonly" />
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>隶属部门:</label>
							<div class="col-md-3">
								<div class="row">
									<div class="col-sm-12">
										<div id="orgUnitTreeMenu" class="input-group">
											<input id="internalUnitId" name="customerAccount.internalUnitId" value="${customerAccount.internalUnitId}" type="hidden" /> 
											<input id="internalUnitType" name="customerAccount.internalUnitType" value="${customerAccount.internalUnitType}" type="hidden" /> 
											<input id="internalUnitName" name="customerAccount.internalUnitName" type="text" onfocus="showOrgUnitMenu(); return false;" value="${customerAccount.internalUnitName}" type="text" class="form-control validate[required]" />
											<div class="input-group-btn">
												<button type="button" class="btn btn-default" onclick="$('#internalUnitId').val('');$('#internalUnitName').val('');$('#internalUnitType').val('');">清空</button>
											</div>
											<div id="OrgUnitContent" class="OrgUnitContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
												<ul id="customerAccountOrgUnitTree" class="ztree"></ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<!-- 个人客户处理区 -->
					<s:if test="customerAccountType == 'personal'">
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
							<div class="col-md-3">
								<input id="code" name="customerAccount.code" value="${customerAccount.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>姓名:</label>
							<div class="col-md-3">
								<input id="name" name="contactPerson.name" value="${contactPerson.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>手机:</label>
							<div class="col-md-3">
								<input id="directPhone" name="contactPerson.directPhone" value="${contactPerson.directPhone}" data-prompt-position="topLeft" class="form-control validate[required,custom[phone]]" type="text" />
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>简称:</label>
							<div class="col-md-3">
								<input id="shorterName" name="customerAccount.shorterName" value="${customerAccount.shorterName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
						</div>
						<s:if test="source =='member'">
							<div class="form-group">
								<label class="col-md-2 control-label">标识:</label>
								<div class="col-md-3">
									<input id="tag" name="contactPerson.tag" value="${contactPerson.tag}" class="form-control" type="text" />
								</div>
								<label class="col-md-2 control-label">标题:</label>
								<div class="col-md-3">
									<input id="title" name="contactPerson.title" value="${contactPerson.title}" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">外部编号:</label>
								<div class="col-md-3">
									<input id="outNumber" name="contactPerson.outNumber" value="${contactPerson.outNumber}" class="form-control" type="text" />
								</div>
								<label class="col-md-2 control-label">积分密码:</label>
								<div class="col-md-3">
									<input id="integratePassword" name="contactPerson.integratePassword" value="${contactPerson.integratePassword}" class="form-control" type="text" />
								</div>
							</div>
						</s:if>
					</s:if>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>热点客户:</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="customerAccount.isHotCustomer == 1">active</s:if>"> 
									<input type="radio" value="1" id="isHotCustomer" name="customerAccount.isHotCustomer" <s:if test="customerAccount.isHotCustomer == 1">checked="checked"</s:if> class="validate[required]">是
								</label> 
								<label class="btn btn-default <s:if test="customerAccount.isHotCustomer == 0">active</s:if>"> 
									<input type="radio" value="0" id="isHotCustomer" name="customerAccount.isHotCustomer" <s:if test="customerAccount.isHotCustomer == 0">checked="checked"</s:if>>否
								</label>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>热点程度:</label>
						<div class="col-md-3">
							<select id="hotLevelId" name="customerAccount.hotLevel.id" data-prompt-position="topLeft" class="form-control validate[required]">
								<option value="">------请选择------</option>
								<c:forEach items="${hotLevelList}" var="entity">
									<option value="${entity.id}" <c:if test="${customerAccount.hotLevel.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<s:if test="customerAccountType == 'personal'">
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>性别:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="customerAccount.sex == 1">active</s:if>"> 
										<input type="radio" value="1" id="sex" name="customerAccount.sex" <s:if test="customerAccount.sex == 1">checked="checked"</s:if> class="validate[required]">男
									</label> 
									<label class="btn btn-default <s:if test="customerAccount.sex == 0">active</s:if>"> 
										<input type="radio" value="0" id="sex" name="customerAccount.sex" <s:if test="customerAccount.sex == 0">checked="checked"</s:if>>女
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>客户种类:</label>
							<div class="col-md-3">
								<select id="customerTypeId" name="customerAccount.customerType.id" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">------请选择------</option>
									<c:forEach items="${customerTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.customerType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>生日:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="birthday" name="contactPerson.birthday" value="${contactPerson.birthdayStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'birthday'});"><i
										class="fa fa-calendar"></i></span>
								</div>
							</div>
							<label class="col-md-2 control-label">阶段:</label>
							<div class="col-md-3">
								<select id="customerStageId" name="customerAccount.customerStage.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${customerStageList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.customerStage.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">等级标识:</label>
							<div class="col-md-3">
								<input id="levelId" name="customerAccount.levelId" value="${customerAccount.levelId}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">积分历史:</label>
							<div class="col-md-3">
								<input id="pointHistory" name="customerAccount.pointHistory" value="${customerAccount.pointHistory}" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">冻结积分:</label>
							<div class="col-md-3">
								<input id="pointFreeze" name="customerAccount.pointFreeze" value="${customerAccount.pointFreeze}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">可用积分:</label>
							<div class="col-md-3">
								<input id="point" name="customerAccount.point" value="${customerAccount.point}" class="form-control" type="text" />
							</div>
						</div>
						<s:if test="source =='member'">
							<div class="form-group">
								<label class="col-md-2 control-label">是否已婚:</label>
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="contactPerson.isMarriage == 1">active</s:if>"> <input type="radio" value="1" id="isMarriage" name="contactPerson.isMarriage" <s:if test="contactPerson.isMarriage == 1">checked="checked"</s:if> class="validate[required]">是
									</label> <label class="btn btn-default <s:if test="contactPerson.isMarriage == 0">active</s:if>"> <input type="radio" value="0" id="isMarriage" name="contactPerson.isMarriage" <s:if test="contactPerson.isMarriage == 0">checked="checked"</s:if>>否
									</label>
								</div>
								<label class="col-md-2 control-label">是否允许联系:</label>
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="contactPerson.isAllowConnect == 1">active</s:if>"> <input type="radio" value="1" id="isAllowConnect" name="contactPerson.isAllowConnect" <s:if test="contactPerson.isAllowConnect == 1">checked="checked"</s:if> class="validate[required]">是
									</label> <label class="btn btn-default <s:if test="contactPerson.isAllowConnect == 0">active</s:if>"> <input type="radio" value="0" id="isAllowConnect" name="contactPerson.isAllowConnect" <s:if test="contactPerson.isAllowConnect == 0">checked="checked"</s:if>>否
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">教育程度:</label>
								<div class="col-md-3">
									<input id="education" name="contactPerson.education" value="${contactPerson.education}" class="form-control" type="text" />
								</div>
								<label class="col-md-2 control-label">收入水平:</label>
								<div class="col-md-3">
									<input id="incomeLevel" name="contactPerson.incomeLevel" value="${contactPerson.incomeLevel}" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">客户身份:</label>
								<div class="col-md-3">
									<input id="identity" name="contactPerson.identity" value="${contactPerson.identity}" class="form-control" type="text" />
								</div>
							</div>
						</s:if>
						<h6>联系方式</h6>
						<div class="form-group">
							<label class="col-md-2 control-label">邮箱:</label>
							<div class="col-md-3">
								<input id="email" name="contactPerson.email" value="${contactPerson.email}" class="form-control validate[custom[email]]" type="text" />
							</div>
							<label class="col-md-2 control-label">工作电话:</label>
							<div class="col-md-3">
								<input id="mobile" name="contactPerson.mobile" value="${contactPerson.mobile}" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">QQ:</label>
							<div class="col-md-3">
								<input id="qqAccount" name="customerAccount.qqAccount" value="${customerAccount.qqAccount}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">微信:</label>
							<div class="col-md-3">
								<input id="msnAccount" name="customerAccount.msnAccount" value="${customerAccount.msnAccount}" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">淘宝旺旺:</label>
							<div class="col-md-3">
								<input id="wangAccount" name="customerAccount.wangAccount" value="${customerAccount.wangAccount}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">SKYPE:</label>
							<div class="col-md-3">
								<input id="skypeAccount" name="customerAccount.skypeAccount" value="${customerAccount.skypeAccount}" class="form-control" type="text" />
							</div>
						</div>
						<h6>商务信息</h6>
						<div class="form-group">
							<label class="col-md-2 control-label">来源:</label>
							<div class="col-md-3">
								<select id="customerSourceId" name="customerAccount.customerSource.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${customerSourceList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.customerSource.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label">关系等级:</label>
							<div class="col-md-3">
								<select id="relationshipClassId" name="customerAccount.relationshipClass.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${relationshipClassList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.relationshipClass.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>价值评估:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="customerAccount.valueAssessment == 1">active</s:if>"> <input type="radio" value="1" id="valueAssessment" name="customerAccount.valueAssessment" <s:if test="customerAccount.valueAssessment == 1">checked="checked"</s:if> class="validate[required]">高
									</label> <label class="btn btn-default <s:if test="customerAccount.valueAssessment == 2">active</s:if>"> <input type="radio" value="2" id="valueAssessment" name="customerAccount.valueAssessment" <s:if test="customerAccount.valueAssessment == 2">checked="checked"</s:if>>中
									</label> <label class="btn btn-default <s:if test="customerAccount.valueAssessment == 3">active</s:if>"> <input type="radio" value="3" id="valueAssessment" name="customerAccount.valueAssessment" <s:if test="customerAccount.valueAssessment == 3">checked="checked"</s:if>>低
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>信用等级:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="customerAccount.creditLevel == 1">active</s:if>"> <input type="radio" value="1" id="creditLevel" name="customerAccount.creditLevel" <s:if test="customerAccount.creditLevel == 1">checked="checked"</s:if> class="validate[required]">高
									</label> <label class="btn btn-default <s:if test="customerAccount.creditLevel == 2">active</s:if>"> <input type="radio" value="2" id="creditLevel" name="customerAccount.creditLevel" <s:if test="customerAccount.creditLevel == 2">checked="checked"</s:if>>中
									</label> <label class="btn btn-default <s:if test="customerAccount.creditLevel == 3">active</s:if>"> <input type="radio" value="3" id="creditLevel" name="customerAccount.creditLevel" <s:if test="customerAccount.creditLevel == 3">checked="checked"</s:if>>低
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">联系人类型:</label>
							<div class="col-md-3">
								<select id="contactPersonTypeId" name="contactPerson.contactPersonType.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${contactPersonTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contactPerson.contactPersonType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">证件类型:</label>
							<div class="col-md-3">
								<select id="credentialTypeId" name="contactPerson.credentialType.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${credentialTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${contactPerson.credentialType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label">证件号码:</label>
							<div class="col-md-3">
								<input id="credentialCode" name="contactPerson.credentialCode" value="${contactPerson.credentialCode}" class="form-control" type="text" />
							</div>
						</div>
					</s:if>
					<!-- 个人客户处理区 -->
					<s:if test="customerAccountType != 'personal'">
						<div class="form-group">
							<label class="col-md-2 control-label">英文名称:</label>
							<div class="col-md-3">
								<input id="englishName" name="customerAccount.englishName" value="${customerAccount.englishName}" data-prompt-position="topLeft" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">简称:</label>
							<div class="col-md-3">
								<input id="shorterName" name="customerAccount.shorterName" value="${customerAccount.shorterName}" data-prompt-position="topLeft" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">检索词:</label>
							<div class="col-md-3">
								<input id="indexWord" name="customerAccount.indexWord" value="${customerAccount.indexWord}" class="form-control" type="text" />
							</div>
							<label class="col-md-2 control-label">商标:</label>
							<div class="col-md-3">
								<input id="trademark" name="customerAccount.trademark" value="${customerAccount.trademark}" class="form-control" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>价值评估:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="customerAccount.valueAssessment == 1">active</s:if>"> 
										<input type="radio" value="1" id="valueAssessment" name="customerAccount.valueAssessment" <s:if test="customerAccount.valueAssessment == 1">checked="checked"</s:if> class="validate[required]">高
									</label> 
									<label class="btn btn-default <s:if test="customerAccount.valueAssessment == 2">active</s:if>"> 
										<input type="radio" value="2" id="valueAssessment" name="customerAccount.valueAssessment" <s:if test="customerAccount.valueAssessment == 2">checked="checked"</s:if>>中
									</label> 
									<label class="btn btn-default <s:if test="customerAccount.valueAssessment == 3">active</s:if>"> 
										<input type="radio" value="3" id="valueAssessment" name="customerAccount.valueAssessment" <s:if test="customerAccount.valueAssessment == 3">checked="checked"</s:if>>低
									</label>
								</div>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>信用等级:</label>
							<div class="col-md-3">
								<div data-toggle="buttons" class="btn-group">
									<label class="btn btn-default <s:if test="customerAccount.creditLevel == 1">active</s:if>"> 
										<input type="radio" value="1" id="creditLevel" name="customerAccount.creditLevel" <s:if test="customerAccount.creditLevel == 1">checked="checked"</s:if> class="validate[required]">高
									</label> 
									<label class="btn btn-default <s:if test="customerAccount.creditLevel == 2">active</s:if>"> 
										<input type="radio" value="2" id="creditLevel" name="customerAccount.creditLevel" <s:if test="customerAccount.creditLevel == 2">checked="checked"</s:if>>中
									</label> 
									<label class="btn btn-default <s:if test="customerAccount.creditLevel == 3">active</s:if>"> 
										<input type="radio" value="3" id="creditLevel" name="customerAccount.creditLevel" <s:if test="customerAccount.creditLevel == 3">checked="checked"</s:if>>低
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>客户种类:</label>
							<div class="col-md-3">
								<select id="customerTypeId" name="customerAccount.customerType.id" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">------请选择------</option>
									<c:forEach items="${customerTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.customerType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>关系等级:</label>
							<div class="col-md-3">
								<select id="relationshipClassId" name="customerAccount.relationshipClass.id" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">------请选择------</option>
									<c:forEach items="${relationshipClassList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.relationshipClass.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>账户类型:</label>
							<div class="col-md-3">
								<select id="accountTypeId" name="customerAccount.accountType.id" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">------请选择------</option>
									<c:forEach items="${accountTypeList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.accountType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>所属行业:</label>
							<div class="col-md-3">
								<select id="industryId" name="customerAccount.industry.id" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">------请选择------</option>
									<c:forEach items="${industryList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.industry.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span>人员规模:</label>
							<div class="col-md-3">
								<select id="staffScaleId" name="customerAccount.staffScale.id" data-prompt-position="topLeft" class="form-control validate[required]">
									<option value="">------请选择------</option>
									<c:forEach items="${staffScaleList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.staffScale.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span>省份:</label>
							<div class="col-md-3">
								<select id="provinceId" name="customerAccount.province.id" data-prompt-position="topLeft" class="form-control validate[required]" onchange="loadCity();">
									<option value="">------请选择------</option>
									<c:forEach items="${provinceList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.province.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">城市:</label>
							<div class="col-md-3">
								<select id="cityId" name="customerAccount.city.id" data-prompt-position="topLeft" class="form-control" onchange="loadDistrict();">
									<option value="">------请选择------</option>
									<c:forEach items="${cityList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.city.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label">区县:</label>
							<div class="col-md-3">
								<select id="districtId" name="customerAccount.district.id" data-prompt-position="topLeft" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${districtList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.district.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">年收入:</label>
							<div class="col-md-3">
								<input id="annualRevenue" name="customerAccount.annualRevenue" value="${customerAccount.annualRevenueStr}" class="form-control validate[custom[number]]" type="text" />
							</div>
							<label class="col-md-2 control-label">电话传真:</label>
							<div class="col-md-3">
								<input id="phoneFax" name="customerAccount.phoneFax" value="${customerAccount.phoneFax}" class="form-control validata[custom[onlyNumberSp]]" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">来源:</label>
							<div class="col-md-3">
								<select id="customerSourceId" name="customerAccount.customerSource.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${customerSourceList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.customerSource.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-md-2 control-label">阶段:</label>
							<div class="col-md-3">
								<select id="customerStageId" name="customerAccount.customerStage.id" class="form-control">
									<option value="">------请选择------</option>
									<c:forEach items="${customerStageList}" var="entity">
										<option value="${entity.id}" <c:if test="${customerAccount.customerStage.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</s:if>
					<div class="form-group">
						<s:if test="customerAccountType != 'personal'">
							<label class="col-md-2 control-label">公司简介:</label>
							<div class="col-md-3">
								<textarea rows="3" id="companyBrief" name="customerAccount.companyBrief" class="form-control">${customerAccount.companyBrief}</textarea>
							</div>
							<label class="col-md-2 control-label">公司LOGO:</label>
							<div class="col-md-3">
								<div style="float: left; display: inline;">
									<img id="mainPic" onerror="$('#mainPic').attr('src','${nvix}/vixntcommon/base/img/demo/64x64.png')" src="${customerAccount.logo}" width="30px" height="30px" onmouseover="$('#mainPicBig').attr('style','position: absolute;padding:-50px;z-index:9999;');" onmouseout="$('#mainPicBig').attr('style','display:none;');" /> <img
										id="mainPicBig" onerror="$('#mainPicBig').attr('src','${nvix}/vixntcommon/base/img/demo/64x64.png')" src="${customerAccount.logo}" width="100" height="100" style="display: none; position: absolute;" /> &nbsp;&nbsp;
								</div>
								<input type="hidden" id="customerLogo" name="customerAccount.logo" value="${customerAccount.logo}" >
								<input type="file" id="fileToUpload" name="fileToUpload" onchange="imageChange();" style="width: 80%;">
							</div>
						</s:if>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea rows="3" id="memo" name="customerAccount.memo"  class="form-control">${customerAccount.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<s:if test="customerAccountType != 'personal'">
					<div class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
							<div class="jarviswidget jarviswidget-color-white">
								<div>
									<div class="widget-body no-padding">
										<div class="jarviswidget">
											<header role="heading">
												<ul class="nav nav-tabs pull-left in">
													<li class="active">
														<a data-toggle="tab" href="#contactPersonTab" onclick="contactPersonTable.ajax.reload();"> <i class="fa fa-list-alt"></i> 
															<span class="hidden-mobile hidden-tablet">联系人</span>
														</a>
													</li>
													<li>
														<a data-toggle="tab" href="#customerAddressTab" onclick="customerAddressTable.ajax.reload();"> <i class="fa fa-list-alt"></i> 
															<span class="hidden-mobile hidden-tablet">客户地址</span>
														</a>
													</li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="contactPersonTab">
														<div style="margin: 5px;">
															<div class="form-group" style="margin: 0 0px;">
																<div class="col-md-3">
																	<input type="text" value="" placeholder="联系人姓名" class="form-control" id="searchContactPersonName">
																</div>
																<button onclick="contactPersonTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#searchContactPersonName').val('');contactPersonTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class="listMenu-float-right">
																	<button onclick="addContactPerson();" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="contactPerson" class="table table-striped table-bordered table-hover" width="100%">
															<thead>
																<tr>
																	<th width="5%">编号</th>
																	<th width="15%">联系人姓名</th>
																	<th width="20%">负责业务</th>
																	<th width="15%">邮件</th>
																	<th width="10%">电话</th>
																	<th width="10%">联系人类型</th>
																	<th width="15%">操作</th>
																</tr>
															</thead>
														</table>
													</div>
													<div class="tab-pane no-padding" id="customerAddressTab">
														<div id="customerAddressSearchForm" style="margin:5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class=" listMenu-float-right" >
																	<button onclick="saveOrUpdateCustomerAddress('');" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
																	</button>
																</div>
															</div>
														</div>
														<table id="customerAddressTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</s:if>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixCustomerAction!goList.action?syncTag=' +$('#type').val());">
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

	var contactPersonColumns = [
	   		{"orderable":false,"data" : function(data) {return "";}},
	   		{"orderable":false,"data" : function(data) {return data.lastName;}},
	   		{"orderable":false,"data" : function(data) {return data.presideBusiness;}},
	   		{"orderable":false,"data" : function(data) {return data.email;}},
	   		{"orderable":false,"data" : function(data) {return data.mobile;}},
	   		{"orderable":false,"data" : function(data) {return "<span class='label label-info'>"+data.crmContactTypeName+"</span>";}},
	   		{"orderable":false,"data" : function(data) {
				if(data.id == null){return '';}
				var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateContactPerson('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteContactPersonById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return update + "&nbsp;&nbsp;" + del;
			}}
		];

	var contactPersonTable = initDataTable("contactPerson","${nvix}/nvixnt/nvixCustomerAction!getContactPersonListJson.action",contactPersonColumns,function(page,pageSize,orderField,orderBy){
		var parentId = $("#customerId").val();
	   	var name = $("#searchContactPersonName").val();
	 	name = Url.encode(name);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"parentId":parentId,"name":name};
	});
	
	/** 保存客户信息 */
	$("#customerAccountForm").validationEngine();
	var syncTag = $("#customerAccountType").val();
	function saveOrUpdate(id){
		//表单验证
		if($('#customerAccountForm').validationEngine('validate')){
			if(syncTag != '' && syncTag != 'undefined'){
				$("#customerAccountForm").ajaxSubmit({
					type: "post",
					url:"${nvix}/nvixnt/nvixCustomerAction!saveOrUpdate.action",
					contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						showMessage(r[0]);
						loadContent('','${nvix}/nvixnt/nvixCustomerAction!goList.action?syncTag=' + $("#type").val());
					}
				});
			}
		}
	}
	
	/** 添加联系人的回调方法，当客户信息未保存时，先保客户信息再添加联系人。 */
	function addContactPerson(){
		var id = $("#customerId").val();
		if(id == ''){
			if($('#customerAccountForm').validationEngine('validate')){
				if(syncTag != '' && syncTag != 'undefined'){
					$("#customerAccountForm").ajaxSubmit({
						type: "post",
						url : "${nvix}/nvixnt/nvixCustomerAction!saveOrUpdate.action",
			    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
						success : function(result) {
							var r = result.split(":");
							$("#customerId").val($.trim(r[1]));
							saveOrUpdateContactPerson('');
						}
					});
				}
			}
		}else{
			saveOrUpdateContactPerson('');
		}
	}
	
	/** 添加联系人*/
	function saveOrUpdateContactPerson(id){
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixContactPersonAction!goSaveOrUpdate.action?id=' + id + '&parentId=' + $("#customerId").val() + '&source=customer');
	}
	
	/** 删除联系人*/
	function deleteContactPersonById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/nvixContactPersonAction!deleteById.action?id='+id,'是否删除该联系人?',contactPersonTable);
	}
	
	/** 初始化分类下拉列表树 */
	var showICMenu = initDropListTree("treeMenu","${nvix}/nvixnt/nvixCustomerAction!findTreeToJson.action","customerAccountCategoryId","customerAccountCategoryName","customerAccountCategoryTree","menuContent");
	
	/** 初始化所属部门下拉列表树 */
	var showOrgUnitMenu = initDropListTree("orgUnitTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","internalUnitId","internalUnitName","customerAccountOrgUnitTree","OrgUnitContent");
	
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
	
	function loadCity(){
		var provinceId = $("#provinceId").val();
		$("#cityId").load("${nvix}/nvixnt/system/nvixntAddressInfoAction!loadSubAddressInfo.action?parentId="+provinceId);
		$("#districtId").val("");
	}
	
	function loadDistrict(){
		var cityId = $("#cityId").val();
		$("#districtId").load("${nvix}/nvixnt/system/nvixntAddressInfoAction!loadSubAddressInfo.action?parentId="+cityId);
	}
	
	function imageChange() {
		if($('#customerAccountForm').validationEngine('validate')){
			if(syncTag != '' && syncTag != 'undefined'){
				$("#customerAccountForm").ajaxSubmit({
					type: "post",
					url : "${nvix}/nvixnt/nvixCustomerAction!saveOrUpdate.action",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						$("#customerId").val($.trim(r[1]));
						uploadFileOrImage("imageForm", "${nvix}/nvixnt/nvixCustomerAction!saveOrUpdatePicture.action?id=" + $("#customerId").val(), "fileToUpload", "image", function(data) {
							var d = data.split(",");
							if (d[0] == '0') {
								showMessage(d[1]);
							} else {
								$('#customerId').val(d[0]);
								$("#customerLogo").val(d[1]);
								$("#mainPic").attr("src", d[1]);
								$("#mainPicBig").attr("src", d[1]);
								showMessage("图片上传成功!");
							}
						});
					}
				});
			}
		}
	};
	
	// 客户地址
	var customerAddressColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "客户名称",
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccountName;
	}
	}, {
	"title" : "省",
	"name" : "province",
	"data" : function(data) {
		return data.province.name;
	}
	}, {
	"title" : "市",
	"name" : "city",
	"data" : function(data) {
		return data.city.name;
	}
	}, {
	"title" : "区",
	"name" : "region",
	"data" : function(data) {
		return data.district.name;
	}
	}, {
	"title" : "地址",
	"name" : "address",
	"data" : function(data) {
		return data.address;
	}
	},{
	"title" : "邮政编码",
	"name" : "postCode",
	"data" : function(data) {
		return data.postCode;
	}
	}, {
	"title" : "是否默认",
	"name" : "isDefault",
	"data" : function(data) {
		if(data.isDefault == '1'){
			return "<span class='label label-success'>是</span>";
		}else{
			return "<span class='label label-info'>否</span>";
		}
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdateCustomerAddress('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var customerAddressTable = initDataTable("customerAddressTableId", "${nvix}/nvixnt/nvixCustomerAddressAction!goListContent.action", customerAddressColumns, function(page, pageSize, orderField, orderBy) {
		var customerId = $("#customerId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"customerId" : customerId
		};
	});

	function saveOrUpdateCustomerAddress(id) {
		var customerId = $("#customerId").val();
		if(customerId == ''){
			if($('#customerAccountForm').validationEngine('validate')){
				if(syncTag != '' && syncTag != 'undefined'){
					$("#customerAccountForm").ajaxSubmit({
						type: "post",
						url : "${nvix}/nvixnt/nvixCustomerAction!saveOrUpdate.action",
			    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
						success : function(result) {
							var r = result.split(":");
							$("#customerId").val($.trim(r[1]));
							openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAddressAction!goSaveOrUpdate.action?id=' + id + "&customerId=" + customerId, 'customerAddressForm', id == '' ? '新增客户地址' : '修改客户地址', 650, 350, customerAddressTable);
						}
					});
				}
			}
		}else{
			openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCustomerAddressAction!goSaveOrUpdate.action?id=' + id + "&customerId=" + customerId, 'customerAddressForm', id == '' ? '新增客户地址' : '修改客户地址', 650, 350, customerAddressTable);
		}
	}
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCustomerAddressAction!deleteById.action?id=' + id, '是否删除该客户地址?', customerAddressTable);
	};
	
	/** 页面加载完调用 */
	pageSetUp();
</script>