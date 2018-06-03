<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训计划<span>> 汇总 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainAction!goAllTrainList.action');">
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
				<input type="hidden" id="trainCourseId" name="trainCourse.id" value="${trainCourse.id}" />
				<input type="hidden" id="planId" name="" value="" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 计划学时:</label>
						<div class="col-md-3">
							<input id="planHours" name="trainCourse.planHours" value="${trainCourse.planHours}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>每次课程费:</label>
						<div class="col-md-3">
							<input id="courseFees" name="trainCourse.courseFees" value="${trainCourse.courseFees}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 举办次数:</label>
						<div class="col-md-3">
							<input id="holdNumber" name="trainCourse.holdNumber" value="${trainCourse.holdNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>负责人:</label>
						<div class="col-md-3">
							<input id="leadings" name="trainCourse.leadings" value="${trainCourse.leadings}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				   	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="planStartDate" name="trainCourse.planStartDate" value="${trainCourse.planStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="planEndDate" name="trainCourse.planEndDate" value="${trainCourse.planEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remarks" name="trainCourse.remarks" class="form-control" rows="4">${trainCourse.remarks}</textarea>
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">填报计划</span>
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
																	<input type="text" value="" id="planName" placeholder="计划名称" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#planName').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainAction!goAllTrainList.action');">
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
		"title" : "计划名称",
		"width" : "10%",
		"data" : function(data) {
			return data.planName;
		}
	}, {
		"title" : "拟培训时间",
		"width" : "10%",			
		"data" : function(data) {
			return data.quasiTrainingTimeStr;
		}
	}, {
		"title" : "总费用",
		"width" : "10%",			
		"data" : function(data) {
			return data.totalCost;
		}
	}, {
		"title" : "课程费用",
		"width" : "10%",			
		"data" : function(data) {
			return data.courseTotalCost;
		}
	}, {
		"title" : "有效开始日期",
		"width" : "10%",			
		"data" : function(data) {
			return data.trainingStartDateStr;
		}
	}, {
		"title" : "有效结束日期",
		"width" : "10%",			
		"data" : function(data) {
			return data.trainingEndDateStr;
		}
	}, {
		"title" : "操作",
		"width" : "10%",	
		"data" : function(data) {
			var del =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteTrainPlanById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return del;
		}
	} ];
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixTrainAction!goWritePlanList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var planName = $('#planName').val();
		planName = Url.encode(planName);
		var id = $("#trainCourseId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"planName":planName
		};
	},10,"0");
	
	/*删除填报计划*/
	function deleteTrainPlanById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixTrainAction!deleteWritePlanById.action?id=' + id, '是否删除填报计划?', writePlanTable);
	}
	
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateAllTrain.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainAction!goAllTrainList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加填报计划*/
	function addTrainPlan(id){
		if(!$("#trainCourseId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/hr/nvixTrainAction!saveOrUpdateAllTrain.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == "1"){
							$("#trainCourseId").val(r[1]);
							chooseListData('${nvix}/nvixnt/hr/nvixTrainAction!goChooseWritePlan.action', '', '选择填报计划', 'plan',function(){
								$.ajax({
									url : '${nvix}/nvixnt/hr/nvixTrainAction!createWritePlan.action?id='+ $("#planId").val()+'&trainCourseId='+ $("#trainCourseId").val(),
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
			chooseListData('${nvix}/nvixnt/hr/nvixTrainAction!goChooseWritePlan.action', '', '选择填报计划', 'plan',function(){
				$.ajax({
					url : '${nvix}/nvixnt/hr/nvixTrainAction!createWritePlan.action?id='+ $("#planId").val()+'&trainCourseId='+ $("#trainCourseId").val(),
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