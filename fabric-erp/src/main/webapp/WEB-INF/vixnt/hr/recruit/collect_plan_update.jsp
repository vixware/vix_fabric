<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>招聘计划<span>> 汇总 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitPlanAction!goCollectPlanList.action');">
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
				<input type="hidden" id="plansummaryId" name="plansummary.id" value="${plansummary.id}" />
				<input type="hidden" id="hrplanId" name="" value="" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 招聘职务:</label>
						<div class="col-md-3">
							<input id="recruitment" name="plansummary.recruitment" value="${plansummary.recruitment}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}招聘部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="departmentTreeMenu" class="input-group">
										<input id="recruitmentDepartment" name="plansummary.recruitmentDepartment" type="text" onfocus="showHrplanOrg(); return false;" value="${plansummary.recruitmentDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#recruitmentDepartment').val('');">清空</button>
										</div>
										<div id="departmentMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="departmentTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 发布状态:</label>
						<div class="col-md-3">
							<select id="releaseState" name="plansummary.releaseState" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${plansummary.releaseState == "1"}'>selected="selected"</c:if>>发布</option>
								<option value="0" <c:if test='${plansummary.releaseState == "0"}'>selected="selected"</c:if>>不发布</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 发布方式:</label>
						<div class="col-md-3">
							<select id="releaseMode" name="plansummary.releaseMode" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${plansummary.releaseMode == "1"}'>selected="selected"</c:if>>内部</option>
								<option value="2" <c:if test='${plansummary.releaseMode == "2"}'>selected="selected"</c:if>>外部</option>
								<option value="3" <c:if test='${plansummary.releaseMode == "3"}'>selected="selected"</c:if>>内部和外部</option>
							</select>
						</div>
					</div>
				   	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>发布时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="releaseTime" name="plansummary.releaseTime" value="${plansummary.releaseTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'releaseTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>审核人:</label>
						<div class="col-md-3">
							<input id="auditPerson" name="plansummary.auditPerson" value="${plansummary.auditPerson}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remarks" name="plansummary.remarks" class="form-control" rows="4">${regular.remarks}</textarea>
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">招聘计划</span>
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
																	<input type="text" value="" id="programName" placeholder="计划主题" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#programName').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitPlanAction!goCollectPlanList.action');">
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
		"title" : "计划主题",
		"width" : "10%",
		"data" : function(data) {
			return data.programName;
		}
	}, {
		"title" : "提出计划部门",
		"width" : "10%",
		"data" : function(data) {
			return data.responsibleForOrgUnit;
		}
	}, {
		"title" : "组织部门",
		"width" : "10%",			
		"data" : function(data) {
			return data.org;
		}
	}, {
		"title" : "计划人",
		"width" : "10%",			
		"data" : function(data) {
			return data.schemer;
		}
	}, {
		"title" : "计划提出时间",
		"width" : "10%",			
		"data" : function(data) {
			return data.proposedTimeStr;
		}
	}, {
		"title" : "计划生效时间",
		"width" : "10%",			
		"data" : function(data) {
			return data.effectTimeStr;
		}
	}, {
		"title" : "操作",
		"width" : "10%",	
		"data" : function(data) {
			var del =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteTrainPlanById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return del;
		}
	} ];
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixRecruitPlanAction!goTrainPlanList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var programName = $('#programName').val();
		programName = Url.encode(programName);
		var id = $("#plansummaryId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"programName":programName
		};
	},10,"0");
	
	/*删除培训计划*/
	function deleteTrainPlanById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitPlanAction!deleteTrainPlanById.action?id=' + id, '是否删除招聘计划?', writePlanTable);
	}
	
	/*修改招聘明细*/
	function goSaveOrUpdatePlanDetail(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitPlanAction!goSaveOrUpdatePlanDetail.action?planDetailId='+id,"planItemForm","招聘计划明细",800,470,writePlanTable);
	};
	
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdateCollect.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixRecruitPlanAction!goCollectPlanList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加培训计划*/
	function addTrainPlan(id){
		if(!$("#plansummaryId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/hr/nvixRecruitPlanAction!saveOrUpdateCollect.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == "1"){
							$("#plansummaryId").val(r[1]);
							chooseListData('${nvix}/nvixnt/hr/nvixRecruitPlanAction!goChooseTrainPlan.action', '', '选择招聘计划', 'hrplan',function(){
								$.ajax({
									url : '${nvix}/nvixnt/hr/nvixRecruitPlanAction!createTrainPlan.action?id='+ $("#hrplanId").val()+'&plansummaryId='+ $("#plansummaryId").val(),
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
			chooseListData('${nvix}/nvixnt/hr/nvixRecruitPlanAction!goChooseTrainPlan.action', '', '选择招聘计划', 'hrplan',function(){
				$.ajax({
					url : '${nvix}/nvixnt/hr/nvixRecruitPlanAction!createTrainPlan.action?id='+ $("#hrplanId").val()+'&plansummaryId='+ $("#plansummaryId").val(),
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
	var showHrplanOrg = initDropListTree("departmentTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "recruitmentDepartment", "departmentTree", "departmentMenu");
</script>