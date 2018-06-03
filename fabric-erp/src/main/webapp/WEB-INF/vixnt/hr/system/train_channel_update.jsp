<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训体系<span>> 新增培训渠道 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goChannelList.action');">
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
				<input type="hidden" id="channelId" name="channel.id" value="${channel.id}" />
				<input type="hidden" id="trainingLecturerId" name="" value="" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 渠道名称:</label>
						<div class="col-md-3">
							<input id="channelName" name="channel.channelName" value="${channel.channelName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 渠道费用:</label>
						<div class="col-md-3">
							<input id="channelCost" name="channel.channelCost" value="${channel.channelCost}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 所在省份:</label>
						<div class="col-md-3">
							<input id="province" name="channel.province" value="${channel.province}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 所在城市:</label>
						<div class="col-md-3">
							<input id="city" name="channel.city" value="${channel.city}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				   	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 联系方式:</label>
						<div class="col-md-3">
							<input id="contactInformation" name="channel.contactInformation" value="${channel.contactInformation}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>渠道类型:</label>
						<div class="col-md-3">
							<select id="channelType" name="channel.channelType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${channel.channelType == "1"}'>selected="selected"</c:if>>内部渠道</option>
								<option value="2" <c:if test='${channel.channelType == "2"}'>selected="selected"</c:if>>外部渠道</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">描述:</label>
						<div class="col-md-8">
							<textarea id="channelDescription" name="channel.channelDescription" class="form-control" rows="4">${channel.channelDescription}</textarea>
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
												    <li class="active"><a data-toggle="tab" href="#writePlanTab" onclick="writePlanTable.ajax.reload();"> <span class="hidden-mobile hidden-tablet">培训讲师</span>
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
																	<input type="text" value="" id="lecturerName" placeholder="姓名" class="form-control" />
																</div>
																<button onclick="writePlanTable.ajax.reload();" type="button" class="btn btn-info">
																	<i class="glyphicon glyphicon-search"></i> 检索
																</button>
																<button onclick="$('#lecturerName').val('');writePlanTable.ajax.reload();" type="button" class="btn btn-default">
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goChannelList.action');">
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
		"title" : "姓名",
		"width" : "10%",
		"data" : function(data) {
			return data.lecturerName;
		}
	}, {
		"title" : "职位",
		"width" : "10%",
		"data" : function(data) {
			return data.lecturerPosition;
		}
	}, {
		"title" : "部门",
		"width" : "10%",			
		"data" : function(data) {
			return data.branches;
		}
	}, {
		"title" : "讲师级别",
		"width" : "10%",			
		"data" : function(data) {
			if (data.lecturerLevel == '1') {
				return "<span class='label label-info'>助理讲师</span>";
			}else if (data.lecturerLevel == '2') {
				return "<span class='label label-primary'>讲师</span>";
			}else if (data.lecturerLevel == '2') {
				return "<span class='label label-primary'>高级讲师</span>";
			}
			return "";
		}
	}, {
		"title" : "讲师类别",
		"width" : "10%",			
		"data" : function(data) {
			if (data.lecturerType == '1') {
				return "<span class='label label-info'>内聘讲师</span>";
			}else if (data.lecturerType == '2') {
				return "<span class='label label-primary'>外聘讲师</span>";
			}
			return "";
		}
	}, {
		"title" : "费用",
		"width" : "10%",			
		"data" : function(data) {
			return data.lecturerCost;
		}
	}, {
		"title" : "简介",
		"width" : "13%",			
		"data" : function(data) {
			return data.lecturerIntroduction;
		}
	}, {
		"title" : "操作",
		"width" : "7%",	
		"data" : function(data) {
			var del =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteTrainPlanById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return del;
		}
	} ];
	var writePlanTable = initDataTable("writePlanTableId", "${nvix}/nvixnt/hr/nvixTrainSystemAction!goChooseTeacherList.action", writePlanTableColumns, function(page, pageSize, orderField, orderBy) {
		var lecturerName = $('#lecturerName').val();
		lecturerName = Url.encode(lecturerName);
		var id = $("#channelId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"lecturerName":lecturerName
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
			url : "${nvix}/nvixnt/hr/nvixTrainSystemAction!saveOrUpdateChannel.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainSystemAction!goChannelList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/**增加培训讲师*/
	function addTrainPlan(id){
		if(!$("#channelId").val()){
			if ($("#planForm").validationEngine('validate')) {
				$("#planForm").ajaxSubmit({
					type : "post",
					url : "${nvix}/nvixnt/hr/nvixTrainSystemAction!saveOrUpdateChannel.action",
					dataType : "text",
					success : function(result) {
						var r = result.split(":");
						if(r[0] == "1"){
							$("#channelId").val(r[1]);
							chooseListData('${nvix}/nvixnt/hr/nvixTrainSystemAction!goChooseTeacher.action', '', '选择培训讲师', 'trainingLecturer',function(){
								$.ajax({
									url : '${nvix}/nvixnt/hr/nvixTrainSystemAction!createTeacher.action?id='+ $("#trainingLecturerId").val()+'&channelId='+ $("#channelId").val(),
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
			chooseListData('${nvix}/nvixnt/hr/nvixTrainSystemAction!goChooseTeacher.action', '', '选择培训讲师', 'trainingLecturer',function(){
				$.ajax({
					url : '${nvix}/nvixnt/hr/nvixTrainSystemAction!createTeacher.action?id='+ $("#trainingLecturerId").val()+'&channelId='+ $("#channelId").val(),
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
	
</script>