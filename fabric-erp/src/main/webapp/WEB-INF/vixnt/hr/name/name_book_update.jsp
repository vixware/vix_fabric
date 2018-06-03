<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>员工信息管理<span>> 员工管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNameBookAction!goEmployeeList.action');">
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
				<input type="hidden" id="employeeId" name="employee.id" value="${employee.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 姓名:</label>
						<div class="col-md-3">
							<input id="name" name="employee.name" value="${employee.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 工号:</label>
						<div class="col-md-3">
							<input id="staffJobNumber" name="employee.staffJobNumber" value="${employee.staffJobNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 职务:</label>
						<div class="col-md-3">
							<input id="subordinatePosition" name="employee.subordinatePosition" value="${employee.subordinatePosition}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="emplpyeeOrgTreeMenu" class="input-group">
										<input id="emplpyeeOrgIds" name="employee.organizationUnit.id" type="hidden" value="${employee.organizationUnit.id}" />
										<input id="emplpyeeOrgNames" name="employee.organizationUnit.name" type="text" onfocus="showEmplpyeeOrg(); return false;" value="${employee.organizationUnit.name}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#emplpyeeOrgIds').val('');$('#emplpyeeOrgNames').val('');">清空</button>
										</div>
										<div id="emplpyeeOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="emplpyeeOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">户籍地址:</label>
						<div class="col-md-3">
							<input id="residenceAddress" name="employee.residenceAddress" value="${employee.residenceAddress}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 出生地:</label>
						<div class="col-md-3">
							<input id="birthplace" name="employee.birthplace" value="${employee.birthplace}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 身份证号:</label>
						<div class="col-md-3">
							<input id="idNumber" name="employee.idNumber" value="${employee.idNumber}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 员工类型:</label>
						<div class="col-md-3">
							<select id="employeeType" name="employee.employeeType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${employee.employeeType == "1"}'>selected="selected"</c:if>>在职</option>
								<option value="0" <c:if test='${employee.employeeType == "0"}'>selected="selected"</c:if>>后备</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 专业:</label>
						<div class="col-md-3">
							<input id="professional" name="employee.professional" value="${employee.professional}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 电话:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="telephone" name="employee.telephone" value="${employee.telephone}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>性别:</label>
						<div class="col-md-3">
							<select id="gender" name="employee.gender" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${employee.gender == "1"}'>selected="selected"</c:if>>男</option>
								<option value="0" <c:if test='${employee.gender == "0"}'>selected="selected"</c:if>>女</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 民族:</label>
						<div class="col-md-3">
							<input id="national" name="employee.national" value="${employee.national}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 现住址:</label>
						<div class="col-md-3">
							<input id="currentResidence" name="employee.currentResidence" value="${employee.currentResidence}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 学历:</label>
						<div class="col-md-3">
							<select id="qualificationsCode" name="employee.qualificationsCode" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${employee.qualificationsCode == "1"}'>selected="selected"</c:if>>高中</option>
								<option value="2" <c:if test='${employee.qualificationsCode == "2"}'>selected="selected"</c:if>>中专</option>
								<option value="3" <c:if test='${employee.qualificationsCode == "3"}'>selected="selected"</c:if>>大专</option>
								<option value="4" <c:if test='${employee.qualificationsCode == "4"}'>selected="selected"</c:if>>本科</option>
								<option value="5" <c:if test='${employee.qualificationsCode == "5"}'>selected="selected"</c:if>>硕士</option>
								<option value="6" <c:if test='${employee.qualificationsCode == "6"}'>selected="selected"</c:if>>博士</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 婚姻状况:</label>
						<div class="col-md-3">
							<select id="isMarriage" name="employee.isMarriage" class="form-control">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${employee.isMarriage == "1"}'>selected="selected"</c:if>>已婚</option>
								<option value="0" <c:if test='${employee.isMarriage == "0"}'>selected="selected"</c:if>>未婚</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="employee.memo" class="form-control" rows="4">${employee.memo}</textarea>
						</div>
					</div>
					<div class="form-group" id="attachmentsTabsId">
						<div class="col-md-12">
							<div class="jarviswidget jarviswidget-color-darken">
								<header style="height: 1px; border-color: #ddd !important"></header>
								<div>
									<div id="salesOrderRightContent" class="widget-body no-padding">
										<div class="jarviswidget" id="salesOrderDetailTabs" style="margin: 0; padding: 12px 12px 12px 12px;" data-widget-togglebutton="false" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
											<header>
												<ul class="nav nav-tabs pull-left in">
													<li class="active"><a data-toggle="tab" href="#orderDetailTab" onclick="supplierAttachmentsTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">附件</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
													<div class="tab-pane no-padding active" id="orderDetailTab">
														<div id="orderDetailSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="searchName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierAttachmentsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#searchName').val('');supplierAttachmentsTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class="listMenu-float-right">
																	<div class="form-group" style="margin: 0 5px;">
																		<div class="listMenu-float-right">
																			<input type="file" id="fileToUpload" name="fileToUpload" onchange="fileChange();" value="上传附件" style="width: 100%;">
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<table id="supplierAttachmentsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
												</div>
											</div>
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNameBookAction!goEmployeeList.action');">
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
    pageSetUp();
    var supplierAttachmentsColumns =  [ {
		"title" : "编号",
		"width" : "5%",
		"data" : function(data) {
		return "";
		}
    }, {
		"title" : "附件名称",
		"width" : "15%",
		"data" : function(data) {
			return data.fileName;
		}
	}, {
		"title" : "上传路径",
		"width" : "40%",
		"data" : function(data) {
			return data.filePath;
		}
	}, {
		"title" : "上传时间",
		"width" : "20%",			
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierAttachmentsTable = initDataTable("supplierAttachmentsTableId", "${nvix}/nvixnt/hr/nvixNameBookAction!getUploadersList.action", supplierAttachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $('#searchName').val();
		searchName = Url.encode(searchName);
		var id = $("#employeeId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":searchName
		};
	},10,"0");
	
	/*删除*/
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixNameBookAction!deleteUploaderById.action?id=' + id, '是否删除附件?', supplierAttachmentsTable);
	};
    
	/*上传附件*/
	function fileChange() {
		if(!$("#employeeId").val()){
			if ($("#employeeForm").validationEngine('validate')) {
				$("#employeeForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixNameBookAction!saveOrUpdate.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#employeeId").val(r[1]);
						uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixNameBookAction!saveOrUpdateUploaders.action?id="+r[1], "fileToUpload", "file", function(data) {
							supplierAttachmentsTable.ajax.reload();
							showMessage("文件上传成功!");
						});
					}else{
						showMessage(r[1]);
						return;
					}
				}
				});
			} else {
				return false;
			}
		}else{
			uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixNameBookAction!saveOrUpdateUploaders.action?id="+$("#employeeId").val(), "fileToUpload", "file", function(data) {
				supplierAttachmentsTable.ajax.reload();
				showMessage("文件上传成功!");
			});
		}
	};
	
	$("#employeeForm").validationEngine();
	function saveOrUpdate() {
		if ($("#employeeForm").validationEngine('validate')) {
			$("#employeeForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixNameBookAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixNameBookAction!goEmployeeList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/** 初始化部门下拉列表树 */
	var showEmplpyeeOrg = initDropListTree("emplpyeeOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "emplpyeeOrgIds", "emplpyeeOrgNames", "emplpyeeOrgTree", "emplpyeeOrgMenu");
	//弹窗方式初始化部门
	function chooseItemCatagory() {
		var ids = $("#objectExpandId").val();
		chooseListData('${nvix}/nvixnt/mdm/nvixntItemAction!goChooseMultiItemCatalog.action', 'multi', '选择分类', null, function() {
			var ItemCatagory = chooseMap.valueSetWithClear().split(":");
			if (ItemCatagory != '' && ItemCatagory.length == 2) {
				$('#itemCatalogIds').val(ItemCatagory[0]);
				$('#temCatalogNames').val(ItemCatagory[1]);
			} else {
				layer.alert("请选择商品类型!");
			}
		});
	}
	
</script>