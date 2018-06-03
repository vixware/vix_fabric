<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训需求<span>> 培训需求申请 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goNeedApply.action');">
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
				<input type="hidden" id="applylId" name="apply.id" value="${apply.id}" />
				<input type="hidden" id="courseId" name="" value="" />
				<fieldset>
					<div class="form-group">
					    <label class="col-md-2 control-label"><span class="text-danger">*</span> 申请编号:</label>
						<div class="col-md-3">
							<input id="applicationNumber" name="apply.applicationNumber" value="${apply.applicationNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 申请名称:</label>
						<div class="col-md-3">
							<input id="applicationName" name="apply.applicationName" value="${apply.applicationName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 申请日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="applicationDate" name="apply.applicationDate" value="${apply.applicationDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'applicationDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>申请部门:</label>
						<div class="col-md-3">
							<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseOrgId" name="apply.departmet.id" value="${apply.departmet.id}"/>
								<input id="chooseSaleOrgDeptName" type="text" onfocus="saleOrgDeptShowMenu(); return false;" value="${apply.departmet.fullName}" class="form-control validate[required]" readonly="readonly" style="cursor:pointer;"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#chooseOrgId').val('');$('#chooseSaleOrgDeptName').val('');">
										清空
									</button>
								</div>
								<div id="saleOrgDeptMenuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
									<ul id="chooseSaleOrgDeptZtree" class="ztree"></ul>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>申请部门负责人:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="apply.noticePerson.id" value="${apply.noticePerson.id}"/>
								<input id="employeeName" name="notice.noticePerson.name" value="${apply.noticePerson.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor:pointer;" readonly="readonly"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#employeeId').val('');$('#employeeName').val('');">
										清空
									</button>
								</div>
							</div>
						</div>
					</div>
				   	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>建议培训机构:</label>
						<div class="col-md-3">
							<input id="proposedTrainingInstitutions" name="apply.proposedTrainingInstitutions" value="${apply.proposedTrainingInstitutions}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 建议培训讲师:</label>
						<div class="col-md-3">
							<input id="trainingInstructor" name="apply.trainingInstructor" value="${apply.trainingInstructor}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>联系方式:</label>
						<div class="col-md-3">
							<input id="contactway" name="apply.contactway" value="${apply.contactway}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 参与人数:</label>
						<div class="col-md-3">
							<input id="numberOfParticipants" name="apply.numberOfParticipants" value="${apply.numberOfParticipants}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="expectStartDate" name="apply.expectStartDate" value="${apply.expectStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'expectStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="expectEndDate" name="apply.expectEndDate" value="${apply.expectEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'expectEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">需求描述:</label>
						<div class="col-md-8">
							<textarea id="remarks" name="apply.remarks" class="form-control" rows="4">${apply.remarks}</textarea>
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">培训课程</span>
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
																	<input type="text" value="" id="courseName" placeholder="姓名" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#courseName').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goNeedApply.action');">
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
		"title" : "课程编码",
		"width" : "10%",
		"data" : function(data) {
			return data.courseCode;
		}
	}, {
		"title" : "课程名称",
		"width" : "10%",
		"data" : function(data) {
			return data.courseName;
		}
	}, {
		"title" : "课程类别",
		"width" : "10%",			
		"data" : function(data) {
			if (data.courseType == '1') {
				return "<span class='label label-info'>入职培训</span>";
			}else if (data.courseType == '2') {
				return "<span class='label label-primary'>技术课程</span>";
			}else if (data.courseType == '3') {
				return "<span class='label label-primary'>业务课程</span>";
			}else if(data.courseType == '4'){
				return "<span class='label label-primary'>管理课程</span>";
			}
			return "";
		}
	}, {
		"title" : "课程内容",
		"width" : "10%",			
		"data" : function(data) {
			return data.courseeducation;
		}
	}, {
		"title" : "需要课时",
		"width" : "10%",			
		"data" : function(data) {
			return data.needs;
		}
	}, {
		"title" : "课程费用",
		"width" : "10%",			
		"data" : function(data) {
			return data.courseFees;
		}
	}, {
		"title" : "课程性质",
		"width" : "10%",			
		"data" : function(data) {
			return data.natureCourse;
		}
	}, {
		"title" : "课程开始日期",
		"width" : "10%",			
		"data" : function(data) {
			return data.effectiveStartDateStr;
		}
	}, {
		"title" : "课程结束日期",
		"width" : "10%",			
		"data" : function(data) {
			return data.effectiveEndDateStr;
		}
	} ];
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixNeedAction!goChooseCourseList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var courseName = $('#courseName').val();
		courseName = Url.encode(courseName);
		var id = $("#applylId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"courseName":courseName
		};
	},10,"0");
	
	
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixNeedAction!saveOrUpdateApply.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixNeedAction!goNeedApply.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加培训课程*/
	function addTrainPlan(id){
		if(!$("#applylId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/hr/nvixNeedAction!saveOrUpdateApply.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == "1"){
							$("#applylId").val(r[1]);
							chooseListData('${nvix}/nvixnt/hr/nvixNeedAction!goChooseCourse.action', '', '选择培训讲师', 'course',function(){
								$.ajax({
									url : '${nvix}/nvixnt/hr/nvixNeedAction!createCourse.action?id='+ $("#courseId").val()+'&applylId='+ $("#applylId").val(),
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
			chooseListData('${nvix}/nvixnt/hr/nvixNeedAction!goChooseCourse.action', '', '选择培训讲师', 'course',function(){
				$.ajax({
					url : '${nvix}/nvixnt/hr/nvixNeedAction!createCourse.action?id='+ $("#courseId").val()+'&applylId='+ $("#applylId").val(),
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
	var saleOrgDeptShowMenu = initDropListTree("saleOrgDeptTreeMenu","${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action","chooseOrgId","chooseSaleOrgDeptName","chooseSaleOrgDeptZtree","saleOrgDeptMenuContent",null,function(treeNode){
		if(treeNode.treeType == "C"){
			layer.alert("请选择部门!");
			$("#chooseOrgId").val("");
			$("#chooseSaleOrgDeptName").val("");
		}
	});
	 
   function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixntUserAccountAction!goEmployeeChoose.action', 'single', '选择人员', 'employee');
	}
</script>