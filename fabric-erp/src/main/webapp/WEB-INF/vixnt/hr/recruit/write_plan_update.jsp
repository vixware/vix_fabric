<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>招聘计划<span>> 填报 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitPlanAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>基本信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="planId" name="hrplan.id" value="${hrplan.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划主题:</label>
						<div class="col-md-3">
							<input id="programName" name="hrplan.programName" value="${hrplan.programName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 规划性质:</label>
						<div class="col-md-3">
							<input id="planningNature" name="hrplan.planningNature" value="${hrplan.planningNature}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}提出计划部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="hrplanOrgTreeMenu" class="input-group">
										<input id="hrplanOrgNames" name="hrplan.org" type="text" onfocus="showHrplanOrg(); return false;" value="${hrplan.org}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#hrplanOrgNames').val('');">清空</button>
										</div>
										<div id="hrplanOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="hrplanOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}组织部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="forOrgTreeMenu" class="input-group">
										<input id="responsibleForOrgUnit" name="hrplan.responsibleForOrgUnit" type="text" onfocus="showForplanOrg(); return false;" value="${hrplan.responsibleForOrgUnit}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#responsibleForOrgUnit').val('');">清空</button>
										</div>
										<div id="forlanOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="forplanOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划状态:</label>
						<div class="col-md-3">
							<select id="planStatus" name="hrplan.planStatus" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrplan.planStatus == "1"}'>selected="selected"</c:if>>已提交</option>
								<option value="2" <c:if test='${hrplan.planStatus == "2"}'>selected="selected"</c:if>>未提交</option>
								<option value="3" <c:if test='${hrplan.planStatus == "3"}'>selected="selected"</c:if>>已审核</option>
								<option value="4" <c:if test='${hrplan.planStatus == "4"}'>selected="selected"</c:if>>未审核</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 覆盖地区:</label>
						<div class="col-md-3">
							<select id="coverageArea" name="hrplan.coverageArea" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrplan.coverageArea == "1"}'>selected="selected"</c:if>>东北</option>
								<option value="2" <c:if test='${hrplan.coverageArea == "2"}'>selected="selected"</c:if>>华北</option>
								<option value="3" <c:if test='${hrplan.coverageArea == "3"}'>selected="selected"</c:if>>华南</option>
								<option value="4" <c:if test='${hrplan.coverageArea == "4"}'>selected="selected"</c:if>>华中</option>
								<option value="5" <c:if test='${hrplan.coverageArea == "5"}'>selected="selected"</c:if>>西北</option>
							</select>
						</div>
					</div>
				   	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>提出计划时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="proposedTime" name="hrplan.proposedTime" value="${hrplan.proposedTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'proposedTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>生效时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectTime" name="hrplan.effectTime" value="${hrplan.effectTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划人:</label>
						<div class="col-md-3">
							<input id="schemer" name="hrplan.schemer" value="${hrplan.schemer}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">招聘明细</span>
													</a></li>
													<li><a data-toggle="tab" href="#orderDetailTab" onclick="supplierAttachmentsTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">简历附件</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
												   <!--增加招聘明细-->
												   <div class="tab-pane no-padding active" id="writePlanTab">
														<div id="writePlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="projectContent" placeholder="名称" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#projectContent').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addWritePlanDetail('');" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="writePlanTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
													</div>
													<!--增加招聘附件-->
													<div class="tab-pane no-padding" id="orderDetailTab">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitPlanAction!goList.action');">
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
    var writePlanTableColumns =  [ {
		"title" : "编号",
		"width" : "5%",
		"data" : function(data) {
		return "";
		}
    }, {
		"title" : "计划内容",
		"width" : "10%",
		"data" : function(data) {
			return data.projectContent;
		}
	}, {
		"title" : "招聘职位",
		"width" : "10%",
		"data" : function(data) {
			return data.theJob;
		}
	}, {
		"title" : "职位说明",
		"width" : "10%",			
		"data" : function(data) {
			return data.jobDescription;
		}
	}, {
		"title" : "工作职责",
		"width" : "10%",			
		"data" : function(data) {
			return data.operatingDuty;
		}
	}, {
		"title" : "负责人",
		"width" : "5%",			
		"data" : function(data) {
			return data.leadingOfficial;
		}
	}, {
		"title" : "工作地点",
		"width" : "10%",			
		"data" : function(data) {
			return data.jobAddress;
		}
	}, {
		"title" : "招聘要求",
		"width" : "10%",			
		"data" : function(data) {
			return data.recruitmentRequirements;
		}
	}, {
		"title" : "费用预算",
		"width" : "5%",			
		"data" : function(data) {
			return data.expenseBudget;
		}
	}, {
		"title" : "计划开始时间",
		"width" : "8%",			
		"data" : function(data) {
			return data.plannedStartTimeStr;
		}
	}, {
		"title" : "计划结束时间",
		"width" : "8%",			
		"data" : function(data) {
			return data.plannedEndTimeStr;
		}
	}, {
		"title" : "操作",
		"width" : "9%",	
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdatePlanDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePlanDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
	} ];
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixRecruitPlanAction!getPlanDetailList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var projectContent = $('#projectContent').val();
		projectContent = Url.encode(projectContent);
		var id = $("#planId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"projectContent":projectContent
		};
	},10,"0");
	
	/*删除招聘明细*/
	function deletePlanDetailById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitPlanAction!deletePlanDetailById.action?id=' + id, '是否删除明细?', writePlanTable);
	}
	/*修改招聘明细*/
	function goSaveOrUpdatePlanDetail(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitPlanAction!goSaveOrUpdatePlanDetail.action?id='+id,"planItemForm","招聘计划明细",800,470,writePlanTable);
	};
	
	<!--附件-->
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
	var supplierAttachmentsTable = initDataTable("supplierAttachmentsTableId", "${nvix}/nvixnt/hr/nvixRecruitPlanAction!getUploadersList.action", supplierAttachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var titleName = $('#titleName').val();
		titleName = Url.encode(titleName);
		var id = $("#planId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"titleName":titleName
		};
	},10,"0");
	
	/*删除*/
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitPlanAction!deleteUploaderById.action?id=' + id, '是否删除附件?', supplierAttachmentsTable);
	};
    
	/*上传附件*/
	function fileChange() {
		if(!$("#planId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdatePlan.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#planId").val(r[1]);
						uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdateUploaders.action?id="+r[1], "fileToUpload", "file", function(data) {
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
			uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdateUploaders.action?id="+$("#planId").val(), "fileToUpload", "file", function(data) {
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
			url : "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdatePlan.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixRecruitPlanAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加招聘计划明细*/
	function addWritePlanDetail(id){
		if(!$("#planId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdatePlan.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#planId").val(r[1]);
						openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitPlanAction!goSaveOrUpdatePlanDetail.action?id=' + id + "&hrplanId=" + $("#planId").val(),"planItemForm","招聘计划明细",800,470,writePlanTable);
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
			openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitPlanAction!goSaveOrUpdatePlanDetail.action?id=' + id + "&hrplanId=" + $("#planId").val(),"planItemForm","招聘计划明细",800,470,writePlanTable);
		}
	};
	
	/** 初始化部门下拉列表树 */
	var showHrplanOrg = initDropListTree("hrplanOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "hrplanOrgNames", "hrplanOrgTree", "hrplanOrgMenu");
	var showForplanOrg = initDropListTree("forOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "responsibleForOrgUnit", "forplanOrgTree", "forlanOrgMenu");
</script>