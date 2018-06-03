<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 甄选录用<span onclick="">&gt; 资料筛选</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goSelectDataList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>新增资料筛选</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="hrDataSelectionId" name="hrDataSelection.id" value="${hrDataSelection.id}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 应聘人姓名:</label>
						<div class="col-md-3">
							<input id="candidateName" name="hrDataSelection.candidateName" value="${hrDataSelection.candidateName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 应聘人编号:</label>
						<div class="col-md-3">
							<input id="nameNumber" name="hrDataSelection.nameNumber" value="${hrDataSelection.nameNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
					    <label class="col-md-2 control-label"><span class="text-danger">*</span> 年龄:</label>
						<div class="col-md-3">
							<input id="age" name="hrDataSelection.age" value="${hrDataSelection.age}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 性别:</label>
						<div class="col-md-3">
							<select id="sex" name="hrDataSelection.sex" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrDataSelection.sex == "1"}'>selected="selected"</c:if>>男</option>
								<option value="2" <c:if test='${hrDataSelection.sex == "2"}'>selected="selected"</c:if>>女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 指定应聘类型:</label>
						<div class="col-md-3">
							<select id="employeeType" name="hrDataSelection.employeeType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrDataSelection.employeeType == "1"}'>selected="selected"</c:if>>初级应聘人员</option>
								<option value="2" <c:if test='${hrDataSelection.employeeType == "2"}'>selected="selected"</c:if>>部门刷选人员</option>
								<option value="3" <c:if test='${hrDataSelection.employeeType == "3"}'>selected="selected"</c:if>>用人部门淘汰人员</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 指定应聘职位:</label>
						<div class="col-md-3">
							<input id="employmentObjective" name="hrDataSelection.employmentObjective" value="${hrDataSelection.employmentObjective}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}应聘部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="forOrgTreeMenu" class="input-group">
										<input id="applicantsDepartment" name="hrDataSelection.applicantsDepartment" type="text" onfocus="showForplanOrg(); return false;" value="${hrDataSelection.applicantsDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#applicantsDepartment').val('');">清空</button>
										</div>
										<div id="forlanOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="forplanOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘渠道:</label>
						<div class="col-md-3">
							<select id="employeeChannel" name="hrDataSelection.employeeChannel" class="form-control validate[required]">
							    <option value="">请选择</option>
							    <option value="0" <c:if test='${hrDataSelection.employeeChannel == "0"}'>selected="selected"</c:if>>校招</option>
								<option value="1" <c:if test='${hrDataSelection.employeeChannel == "1"}'>selected="selected"</c:if>>智联招聘</option>
								<option value="2" <c:if test='${hrDataSelection.employeeChannel == "2"}'>selected="selected"</c:if>>Boss直聘</option>
								<option value="3" <c:if test='${hrDataSelection.employeeChannel == "3"}'>selected="selected"</c:if>>中关村人才交流中心</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 笔试成绩:</label>
						<div class="col-md-3">
							<input id="writtenTestResults" name="hrDataSelection.writtenTestResults" value="${hrDataSelection.writtenTestResults}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"> 面试成绩:</label>
						<div class="col-md-3">
							<input id="resultsOfInterview" name="hrDataSelection.resultsOfInterview" value="${hrDataSelection.resultsOfInterview}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				 	<div class="form-group">
						<label class="col-md-2 control-label"> 推荐人:</label>
						<div class="col-md-3">
							<input id="recommendMan" name="hrDataSelection.recommendMan" value="${hrDataSelection.recommendMan}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
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
																	<input type="text" value="" id="titleName" placeholder="名称" class="form-control" />
																</div>
																<button onclick="supplierAttachmentsTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#titleName').val('');supplierAttachmentsTable.ajax.reload();" type="button" class="btn btn-default">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixInterviewAction!goSelectDataList.action');">
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
			return data.titleName;
		}
	}, {
		"title" : "上传路径",
		"width" : "40%",
		"data" : function(data) {
			return data.path;
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
	var supplierAttachmentsTable = initDataTable("supplierAttachmentsTableId", "${nvix}/nvixnt/hr/nvixInterviewAction!getUploadersList.action", supplierAttachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var titleName = $('#titleName').val();
		titleName = Url.encode(titleName);
		var id = $("#hrDataSelectionId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"titleName":titleName
		};
	},10,"0");
	
	/*删除*/
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixInterviewAction!deleteUploaderById.action?id=' + id, '是否删除附件?', supplierAttachmentsTable);
	};
	
	/*上传附件*/
	function fileChange() {
		if(!$("#hrDataSelectionId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixInterviewAction!saveOrUpdateData.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#hrDataSelectionId").val(r[1]);
						uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixInterviewAction!saveOrUpdateUploaders.action?id="+r[1], "fileToUpload", "file", function(data) {
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
			uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixInterviewAction!saveOrUpdateUploaders.action?id="+$("#hrDataSelectionId").val(), "fileToUpload", "file", function(data) {
				supplierAttachmentsTable.ajax.reload();
				showMessage("文件上传成功!");
			});
		}
	};
		
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixInterviewAction!saveOrUpdateData.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixInterviewAction!goEmployList.action');
			}
			});
		} else {
			return false;
		}
	};
	/** 初始化部门下拉列表树 */
	var showForplanOrg = initDropListTree("forOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "applicantsDepartment", "forplanOrgTree", "forlanOrgMenu"); 
</script>