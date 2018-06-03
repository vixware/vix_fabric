<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>活动计划<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainAction!goList.action');">
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
				<input type="hidden" id="trainPlanId" name="trainPlan.id" value="${trainPlan.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划主题:</label>
						<div class="col-md-3">
							<input id="applicationName" name="trainPlan.applicationName" value="${trainPlan.applicationName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划状态:</label>
						<div class="col-md-3">
							<select id="planStatus" name="trainPlan.planStatus" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainPlan.planStatus == "1"}'>selected="selected"</c:if>>执行</option>
								<option value="0" <c:if test='${trainPlan.planStatus == "0"}'>selected="selected"</c:if>>未执行</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划级别:</label>
						<div class="col-md-3">
							<select id="planLevel" name="trainPlan.planLevel" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainPlan.planLevel == "1"}'>selected="selected"</c:if>>新</option>
								<option value="2" <c:if test='${trainPlan.planLevel == "2"}'>selected="selected"</c:if>>低</option>
								<option value="3" <c:if test='${trainPlan.planLevel == "3"}'>selected="selected"</c:if>>中</option>
								<option value="4" <c:if test='${trainPlan.planLevel == "4"}'>selected="selected"</c:if>>高</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划性质:</label>
						<div class="col-md-3">
							<select id="planningNature" name="trainPlan.planningNature" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainPlan.planningNature == "1"}'>selected="selected"</c:if>>定向</option>
								<option value="0" <c:if test='${trainPlan.planningNature == "0"}'>selected="selected"</c:if>>非定向</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划类型:</label>
						<div class="col-md-3">
							<select id="planType" name="trainPlan.planType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainPlan.planType == "1"}'>selected="selected"</c:if>>短期计划</option>
								<option value="2" <c:if test='${trainPlan.planType == "2"}'>selected="selected"</c:if>>中期计划</option>
								<option value="3" <c:if test='${trainPlan.planType == "3"}'>selected="selected"</c:if>>长期计划</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>提出计划时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="proposedTime" name="trainPlan.proposedTime" value="${trainPlan.proposedTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'proposedTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}提出计划部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="hrplanOrgTreeMenu" class="input-group">
										<input id="hrplanOrgNames" name="trainPlan.org" type="text" onfocus="showHrplanOrg(); return false;" value="${trainPlan.org}" type="text" readonly="readonly" class="form-control validate[required]" />
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">培训计划明细</span>
													</a></li>
													<li><a data-toggle="tab" href="#orderDetailTab" onclick="supplierAttachmentsTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">附件</span>
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
																	<input type="text" value="" id="trainingWay" placeholder="名称" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#trainingWay').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainAction!goList.action');">
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
		"title" : "培训方式",
		"width" : "8%",
		"data" : function(data) {
			return data.trainingWay;
		}
	}, {
		"title" : "培训目标",
		"width" : "10%",
		"data" : function(data) {
			return data.trainingGoal;
		}
	}, {
		"title" : "培训内容",
		"width" : "10%",			
		"data" : function(data) {
			return data.trainingContent;
		}
	}, {
		"title" : "培训课程",
		"width" : "10%",			
		"data" : function(data) {
			return data.trainingCourse;
		}
	}, {
		"title" : "培训地点",
		"width" : "10%",			
		"data" : function(data) {
			return data.trainingSite;
		}
	}, {
		"title" : "培训机构",
		"width" : "10%",			
		"data" : function(data) {
			return data.trainingInstitutions;
		}
	}, {
		"title" : "师资来源",
		"width" : "10%",			
		"data" : function(data) {
			return data.sourcesOfTeachers;
		}
	}, {
		"title" : "课程学时",
		"width" : "5%",			
		"data" : function(data) {
			return data.curriculumClassHours;
		}
	}, {
		"title" : "计划开始时间",
		"width" : "8%",			
		"data" : function(data) {
			return data.planStartDateStr;
		}
	}, {
		"title" : "计划结束时间",
		"width" : "8%",			
		"data" : function(data) {
			return data.planEndDateStr;
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
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixTrainAction!getActivityDetailList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var trainingWay = $('#trainingWay').val();
		trainingWay = Url.encode(trainingWay);
		var id = $("#trainPlanId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"trainingWay":trainingWay
		};
	},10,"0");
	
	/*删除招聘明细*/
	function deletePlanDetailById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixTrainAction!deletePlanDetailById.action?id=' + id, '是否删除明细?', writePlanTable);
	}
	/*修改招聘明细*/
	function goSaveOrUpdatePlanDetail(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixTrainAction!goSaveOrUpdateActivityDetail.action?id='+id,"planItemForm","活动计划明细",800,470,writePlanTable);
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
	var supplierAttachmentsTable = initDataTable("supplierAttachmentsTableId", "${nvix}/nvixnt/hr/nvixTrainAction!getUploadersList.action", supplierAttachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var titleName = $('#titleName').val();
		titleName = Url.encode(titleName);
		var id = $("#trainPlanId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"titleName":titleName
		};
	},10,"0");
	
	/*删除*/
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixTrainAction!deleteUploaderById.action?id=' + id, '是否删除附件?', supplierAttachmentsTable);
	};
    
	/*上传附件*/
	function fileChange() {
		if(!$("#trainPlanId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateActivity.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#trainPlanId").val(r[1]);
						uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateUploaders.action?id="+r[1], "fileToUpload", "file", function(data) {
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
			uploadFileOrImage("supplierFileForm", "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateUploaders.action?id="+$("#trainPlanId").val(), "fileToUpload", "file", function(data) {
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
			url : "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateActivity.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加招聘计划明细*/
	function addWritePlanDetail(id){
		if(!$("#trainPlanId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateActivity.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#trainPlanId").val(r[1]);
						openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixTrainAction!goSaveOrUpdateActivityDetail.action?id=' + id + "&trainPlanId=" + $("#trainPlanId").val(),"planItemForm","培训计划明细",800,470,writePlanTable);
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
			openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixTrainAction!goSaveOrUpdateActivityDetail.action?id=' + id + "&trainPlanId=" + $("#trainPlanId").val(),"planItemForm","培训计划明细",800,470,writePlanTable);
		}
	};
	
	/** 初始化部门下拉列表树 */
	var showHrplanOrg = initDropListTree("hrplanOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "hrplanOrgNames", "hrplanOrgTree", "hrplanOrgMenu");
</script>