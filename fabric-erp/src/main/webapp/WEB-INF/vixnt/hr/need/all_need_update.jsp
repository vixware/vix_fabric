<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>需求汇总<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goAllNeedList.action');">
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
				<input type="hidden" id="demandSummaryId" name="demandSummary.id" value="${demandSummary.id}" />
				<input type="hidden" id="applyId" name="" value="" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 汇总名称:</label>
						<div class="col-md-3">
							<input id="summaryName" name="demandSummary.summaryName" value="${demandSummary.summaryName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 汇总日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="summaryDate" name="demandSummary.summaryDate" value="${demandSummary.summaryDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'summaryDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}汇总部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="forOrgTreeMenu" class="input-group">
										<input id="summaryDepartmentORPeople" name="demandSummary.summaryDepartmentORPeople" type="text" onfocus="showForplanOrg(); return false;" value="${demandSummary.summaryDepartmentORPeople}" type="text" style="cursor:pointer;" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#summaryDepartmentORPeople').val('');">清空</button>
										</div>
										<div id="forlanOrgMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="forplanOrgTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 汇总数量:</label>
						<div class="col-md-3">
							<input id="summaryNumber" name="demandSummary.summaryNumber" value="${demandSummary.summaryNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训讲师:</label>
						<div class="col-md-3">
							<input id="trainingInstructor" name="demandSummary.trainingInstructor" value="${demandSummary.trainingInstructor}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="summaryStartDate" name="demandSummary.summaryStartDate" value="${demandSummary.summaryStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'summaryStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 培训结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="summaryEndDate" name="demandSummary.summaryEndDate" value="${demandSummary.summaryEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'summaryEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">汇总描述:</label>
						<div class="col-md-8">
							<textarea id="summaryDescription" name="demandSummary.summaryDescription" class="form-control" rows="4">${demandSummary.summaryDescription}</textarea>
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">培训申请</span>
													</a></li>
												</ul>
											</header>
											<div class="widget-body" style="padding: 0;">
												<div class="tab-content">
												   <!--增加培训讲师-->
												   <div class="tab-pane no-padding active" id="writePlanTab">
														<div id="writePlanSearchForm" style="margin: 5px;">
															<div class="form-group" style="margin: 0 5px;">
																<div class="col-md-3">
																	<input type="text" value="" id="applicationName" placeholder="姓名" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#applicationName').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addTrainPlan('');" type="button" class="btn btn-primary">
																		<i class="glyphicon glyphicon-plus"></i>
																		<s:text name="add" />
																	</button>
																</div>
															</div>
														</div>
														<table id="writePlanTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goAllNeedList.action');">
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
		"title" : "申请名称",
		"width" : "8%",
		"data" : function(data) {
			return data.applicationName;
		}
	}, {
		"title" : "申请部门",
		"width" : "10%",
		"data" : function(data) {
			if (data.departmet != null) {
				return data.departmet.fullName;
			}else{
				return "";
			}
		}
	}, {
		"title" : "申请日期",
		"width" : "10%",			
		"data" : function(data) {
			return data.applicationDateStr;
		}
	}, {
		"title" : "建议培训机构",
		"width" : "10%",			
		"data" : function(data) {
			return data.proposedTrainingInstitutions;
		}
	}, {
		"title" : "参与人数",
		"width" : "10%",			
		"data" : function(data) {
			return data.numberOfParticipants;
		}
	}, {
		"title" : "联系方式",
		"width" : "10%",			
		"data" : function(data) {
			return data.contactway;
		}
	}, {
		"title" : "开始日期",
		"width" : "8%",			
		"data" : function(data) {
			return data.expectStartDateStr;
		}
	}, {
		"title" : "结束日期",
		"width" : "8%",			
		"data" : function(data) {
			return data.expectEndDateStr;
		}
	
	} ];
    
    var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixNeedAction!goChooseApplyList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var applicationName = $('#applicationName').val();
		applicationName = Url.encode(applicationName);
		var id = $("#demandSummaryId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"applicationName":applicationName
		};
	},10,"0");
	
    $("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixNeedAction!saveOrUpdateAllNeed.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixNeedAction!goAllNeedList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加培训讲师*/
	function addTrainPlan(id){
		if(!$("#demandSummaryId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/hr/nvixNeedAction!saveOrUpdateAllNeed.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == "1"){
							$("#demandSummaryId").val(r[1]);
							chooseListData('${nvix}/nvixnt/hr/nvixNeedAction!goChooseApply.action', '', '选择培训申请', 'apply',function(){
								$.ajax({
									url : '${nvix}/nvixnt/hr/nvixNeedAction!createApply.action?id='+ $("#applyId").val()+'&demandSummaryId='+ $("#demandSummaryId").val(),
									cache : false,
									success : function(result) {
										var r = result.split(":");
										if(r[0] == "0"){
											showMessage(r[1]);
											return;
										}else{
											writePlanTable.ajax.reload();
											showMessage("添加成功!");
										}
									}
								}); 
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
			chooseListData('${nvix}/nvixnt/hr/nvixNeedAction!goChooseApply.action', '', '选择培训申请', 'apply',function(){
				$.ajax({
					url : '${nvix}/nvixnt/hr/nvixNeedAction!createApply.action?id='+ $("#applyId").val()+'&demandSummaryId='+ $("#demandSummaryId").val(),
					cache : false,
					success : function(result) {
						var r = result.split(":");
						if(r[0] == "0"){
							showMessage(r[1]);
							return;
						}else{
							writePlanTable.ajax.reload();
							showMessage("添加成功!");
						}
					}
				}); 
			});
		}
	};
	/** 初始化部门下拉列表树 */
	var showForplanOrg = initDropListTree("forOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "summaryDepartmentORPeople", "forplanOrgTree", "forlanOrgMenu");