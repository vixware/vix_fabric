<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 门店管理 <span onclick="">&gt; 独立门店管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntAloneShopAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>门店</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="channelDistributorForm">
				<input type="hidden" id="channelDistributorId" name="channelDistributor.id" value="${channelDistributor.id}" /> 
				<input type="hidden" id="shopType" name="channelDistributor.shopType" value="${channelDistributor.shopType}" /> 
				<input type="hidden" id="isHasParentChannelDistributor" name="isHasParentChannelDistributor" value="${channelDistributor.isHasParentChannelDistributor}" /> 
				<input type="hidden" id="employeeId" name="channelDistributor.employee.id" value="${channelDistributor.employee.id}" /> 
				<fieldset>
					<legend>基本信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="code" name="channelDistributor.code" value="${channelDistributor.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
						<div class="col-md-3">
							<input id="name" name="channelDistributor.name" value="${channelDistributor.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 简称:</label>
						<div class="col-md-3">
							<input id="shortName" name="channelDistributor.shortName" value="${channelDistributor.shortName}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 法人:</label>
						<div class="col-md-3">
							<input id="artificialPerson" name="channelDistributor.artificialPerson" value="${channelDistributor.artificialPerson}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 负责人: </label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="employeeName" value="${channelDistributor.employee.name }" class="form-control" type="text" readonly="readonly" />
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
						</div>
						<label class="col-md-2 control-label"> 邮箱:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="email" name="channelDistributor.email" value="${channelDistributor.email}" data-prompt-position="topLeft" class="form-control validate[custom[email]]" type="text" /> <span class="input-group-addon"><i class="fa fa-envelope-o"></i> </span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系人:</label>
						<div class="col-md-3">
							<input id="contacts" name="channelDistributor.contacts" value="${channelDistributor.contacts}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系电话:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="telephone" name="channelDistributor.telephone" value="${channelDistributor.telephone}" data-prompt-position="topLeft" class="form-control validate[required,custom[phone]]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 所属行业:</label>
						<div class="col-md-3">
							<select id="industry" name="channelDistributor.industry" class="form-control">
								<option value="1" <c:if test='${channelDistributor.industry eq "1"}'>selected="selected"</c:if>>商贸流通</option>
								<option value="2" <c:if test='${channelDistributor.industry eq "2"}'>selected="selected"</c:if>>生产</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 员工人数:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="employeeCounts" name="channelDistributor.employeeCounts" value="${channelDistributor.employeeCounts }" type="text" class="form-control validate[custom[integer]]"> <span class="input-group-addon">(个)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 类型:</label>
						<div class="col-md-3">
							<select id="type" name="channelDistributor.type" class="form-control">
								<option value="1" <c:if test='${channelDistributor.type eq 1}'>selected="selected"</c:if>>渠道</option>
								<option value="2" <c:if test='${channelDistributor.type eq 2}'>selected="selected"</c:if>>经销商</option>
								<option value="3" <c:if test='${channelDistributor.type eq 3}'>selected="selected"</c:if>>代理商</option>
								<option value="4" <c:if test='${channelDistributor.type eq 4}'>selected="selected"</c:if>>门店</option>
							</select>
						</div>
						<label class="control-label col-md-2"> 注册资金:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control validate[custom[integer]]" id="registeredCapital" name="channelDistributor.registeredCapital" value="${channelDistributor.registeredCapital }" type="text"> <span class="input-group-addon">(¥)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 币种:</label>
						<div class="col-md-3">
							<select id="currencyTypeId" name="channelDistributor.currencyType.id" class="form-control">
								<c:forEach items="${currencyTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${channelDistributor.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> <span class="text-danger">*</span>是否启用:
						</label>
						<div class="col-md-3">
							<div data-toggle="buttons" class="btn-group">
								<label class="btn btn-default <s:if test="channelDistributor.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="channelDistributor.status" <s:if test="channelDistributor.status == 0">checked="checked"</s:if> class="validate[required]">是
								</label> <label class="btn btn-default <s:if test="channelDistributor.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="channelDistributor.status" <s:if test="channelDistributor.status == 1">checked="checked"</s:if>>否
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 地址:</label>
						<div class="col-md-8">
							<input id="address" name="channelDistributor.address" value="${channelDistributor.address}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<legend>其他信息:</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"> 营业执照编号:</label>
						<div class="col-md-3">
							<input id="permit" name="channelDistributor.permit" value="${channelDistributor.permit}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 税号:</label>
						<div class="col-md-3">
							<input id="taxCode" name="channelDistributor.taxCode" value="${channelDistributor.taxCode}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 开户银行:</label>
						<div class="col-md-3">
							<input id="openingBank" name="channelDistributor.openingBank" value="${channelDistributor.openingBank}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 银行账号:</label>
						<div class="col-md-3">
							<input id="bankAccount" name="channelDistributor.bankAccount" value="${channelDistributor.bankAccount}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 地图链接:</label>
						<div class="col-md-3">
							<input id="maplink" name="channelDistributor.maplink" value="${channelDistributor.maplink}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 所属地区:</label>
						<div class="col-md-3">
							<input id="region" name="channelDistributor.region" value="${channelDistributor.region}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="channelDistributor.memo" class="form-control" rows="4">${channelDistributor.memo }</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('drp_distributionsystem','${nvix}/nvixnt/vixntAloneShopAction!goList.action');">
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
	//选择人员
	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	};
	$("#channelDistributorForm").validationEngine();
	function saveOrUpdate() {
		if ($("#channelDistributorForm").validationEngine('validate')) {
			$("#channelDistributorForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntAloneShopAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntAloneShopAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>