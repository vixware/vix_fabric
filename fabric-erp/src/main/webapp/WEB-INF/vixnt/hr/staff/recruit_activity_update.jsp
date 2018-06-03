<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>招聘活动<span>> 招聘活动 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goActivityList.action');">
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
				<input type="hidden" id="hrRecruitactivitityId" name="hrRecruitactivitity.id" value="${hrRecruitactivitity.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 活动名称:</label>
						<div class="col-md-3">
							<input id="activityName" name="hrRecruitactivitity.activityName" value="${hrRecruitactivitity.activityName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 活动地点:</label>
						<div class="col-md-3">
							<input id="activityAddress" name="hrRecruitactivitity.activityAddress" value="${hrRecruitactivitity.activityAddress}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					
				   	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>活动开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="activitystartDate" name="hrRecruitactivitity.activitystartDate" value="${hrRecruitactivitity.activitystartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'activitystartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>活动结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="activityendDate" name="hrRecruitactivitity.activityendDate" value="${hrRecruitactivitity.activityendDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'activityendDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>招聘对象:</label>
						<div class="col-md-3">
							<input id="recruitingObject" name="hrRecruitactivitity.recruitingObject" value="${hrRecruitactivitity.recruitingObject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">注意事项:</label>
						<div class="col-md-8">
							<textarea id="mattersNeedAttention" name="hrRecruitactivitity.mattersNeedAttention" class="form-control" rows="4">${hrRecruitactivitity.mattersNeedAttention}</textarea>
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">招聘活动明细</span>
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
																	<input type="text" value="" id="job" placeholder="职位" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#job').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
																	<i class="glyphicon glyphicon-repeat"></i> 清空
																</button>
																<div class=" listMenu-float-right">
																	<button onclick="addActivityDetail('');" type="button" class="btn btn-primary">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goActivityList.action');">
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
		"title" : "组织部门",
		"width" : "10%",
		"data" : function(data) {
			return data.orgDepartment;
		}
	}, {
		"title" : "招聘职位",
		"width" : "10%",
		"data" : function(data) {
			return data.job;
		}
	}, {
		"title" : "发布类型",
		"width" : "5%",			
		"data" : function(data) {
			if (data.publicationType == '1') {
				return "<span class='label label-info'>内部</span>";
			} else if (data.publicationType == '2') {
				return "<span class='label label-primary'>外部</span>";
			}else if (data.publicationType == '3') {
				return "<span class='label label-primary'>内部和外部</span>";
			}
			return "";
		}
	}, {
		"title" : "发布状态",
		"width" : "7%",			
		"data" : function(data) {
			if (data.publicationStruts == '1') {
				return "<span class='label label-info'>已发布</span>";
			} else if (data.publicationStruts == '2') {
				return "<span class='label label-primary'>未发布</span>";
			}
			return "";
		}
	}, {
		"title" : "有效期限",
		"width" : "8%",			
		"data" : function(data) {
			return data.lifeSpaStr;
		}
	}, {
		"title" : "工作地点",
		"width" : "10%",			
		"data" : function(data) {
			return data.workingPlace;
		}
	}, {
		"title" : "招聘在编人数",
		"width" : "10%",			
		"data" : function(data) {
			return data.recruitmentInTheSeriesNumber;
		}
	}, {
		"title" : "招聘非在编人数",
		"width" : "10%",			
		"data" : function(data) {
			return data.recruitmentOfTheUnofficialNumber;
		}
	}, {
		"title" : "招聘要求",
		"width" : "8%",			
		"data" : function(data) {
			return data.recruitmentRequirements;
		}
	}, {
		"title" : "工作职责",
		"width" : "8%",			
		"data" : function(data) {
			return data.operatingDuty;
		}
	}, {
		"title" : "操作",
		"width" : "9%",	
		"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdateActivityDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePlanDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
	} ];
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixRecruitStaffAction!getActivityDetailList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var job = $('#job').val();
		job = Url.encode(job);
		var id = $("#hrRecruitactivitityId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"job":job
		};
	},10,"0");
	
	/*删除招聘活动明细*/
	function deletePlanDetailById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixRecruitStaffAction!deleteActivityDetailById.action?id=' + id, '是否删除明细?', writePlanTable);
	}
	/*修改招聘活动明细*/
	function goSaveOrUpdateActivityDetail(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSaveOrUpdateActivityDetail.action?id='+id,"planItemForm","招聘活动明细",800,470,writePlanTable);
	};
	
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixRecruitStaffAction!saveOrUpdateActivity.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixRecruitStaffAction!goActivityList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加招聘活动明细*/
	function addActivityDetail(id){
		if(!$("#hrRecruitactivitityId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/hr/nvixRecruitStaffAction!saveOrUpdateActivity.action",
				dataType : "text",
				success : function(result) {
					var r = result.split(":");
					if(r[0]=="1"){
						$("#hrRecruitactivitityId").val(r[1]);
						openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSaveOrUpdateActivityDetail.action?id=' + id + "&hrRecruitactivitityId=" + $("#hrRecruitactivitityId").val(),"planItemForm","招聘活动明细",800,470,writePlanTable);
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
			openSaveOrUpdateWindow('${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSaveOrUpdateActivityDetail.action?id=' + id + "&hrRecruitactivitityId=" + $("#hrRecruitactivitityId").val(),"planItemForm","招聘活动明细",800,470,writePlanTable);
		}
	};
	
</script>