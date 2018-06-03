<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训需求征集通知<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goList.action');">
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
				<input type="hidden" id="noticeId" name="notice.id" value="${notice.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="noticeTheName" name="notice.noticeTheName" value="${notice.noticeTheName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 截止时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="finalTime" name="notice.finalTime" value="${notice.finalTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'finalTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="noticeStartTime" name="notice.noticeStartTime" value="${notice.noticeStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'noticeStartTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="noticeEndTime" name="notice.noticeEndTime" value="${notice.noticeEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'noticeEndTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>通知部门:</label>
						<div class="col-md-3">
							<div id="saleOrgDeptTreeMenu" class="input-group col-md-12">
								<input type="hidden" id="chooseOrgId" name="notice.departmet.id" value="${notice.departmet.id}"/>
								<input id="chooseSaleOrgDeptName" type="text" onfocus="saleOrgDeptShowMenu(); return false;" value="${notice.departmet.fullName}" class="form-control validate[required]" readonly="readonly" style="cursor:pointer;"/>
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span>通知人员:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="employeeId" name="notice.noticePerson.id" value="${notice.noticePerson.id}"/>
								<input id="employeeName" name="notice.noticePerson.name" value="${notice.noticePerson.name}" onfocus="chooseEmployee();" data-prompt-position="topLeft" class="form-control validate[required]" style="cursor:pointer;" type="text" readonly="readonly"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#employeeId').val('');$('#employeeName').val('');">
										清空
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"  ><span class="text-danger" >*</span>${vv:varView('vix_mdm_item')}拟定部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="hrplanOrgTreeMenu" class="input-group">
										<input id="hrplanOrgNames" name="notice.departmentOrPerson" type="text" onfocus="showHrplanOrg(); return false;" value="${notice.departmentOrPerson}" type="text" style="cursor:pointer;" readonly="readonly" class="form-control validate[required]" />
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
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 拟定人:</label>
						<div class="col-md-3">
							<input id="uploadPersonName" name="notice.uploadPersonName" value="${notice.uploadPersonName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				    <legend>通知内容</legend>
					<div class="form-group">
					<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
						  <textarea id="noticeContent" name="notice.noticeContent">${notice.noticeContent}</textarea>
						  <script type="text/javascript" src="${nvix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
						        var editor = KindEditor.create('#noticeContent',
										{
											width:"100%",
											height:300,
											cssPath : '${nvix}/plugin/KindEditor/plugins/code/prettify.css',
											uploadJson : '${nvix}/nvixnt/hr/nvixRecruitStaffAction!uploadFile.action',
											allowFileManager : true 
										}
									);
						  </script>
					  </div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixNeedAction!goList.action');">
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
	$("#planForm").validationEngine();
	function saveOrUpdate() {
		editor.sync();
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixNeedAction!saveOrUpdateNotice.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixNeedAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/** 初始化部门下拉列表树 */
	var showHrplanOrg = initDropListTree("hrplanOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "hrplanOrgNames", "hrplanOrgTree", "hrplanOrgMenu");
	
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