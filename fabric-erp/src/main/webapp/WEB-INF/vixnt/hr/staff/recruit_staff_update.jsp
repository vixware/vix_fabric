<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 招聘管理 <span onclick="">&gt; 招聘征集</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>招聘征集</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="recruitStaffId" name="recruitStaff.id" value="${recruitStaff.id}" />
				<fieldset>
				<legend>基本信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
						<div class="col-md-3">
							<input id="noticeTheName" name="recruitStaff.noticeTheName" value="${recruitStaff.noticeTheName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}通知部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="noticeTreeMenu" class="input-group">
										<input id="companyOrDepartment" name="recruitStaff.companyOrDepartment" type="text" onfocus="showNoticeOrg(); return false;" value="${recruitStaff.companyOrDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#companyOrDepartment').val('');">清空</button>
										</div>
										<div id="noticeMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="noticeTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}发送部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="sendTreeMenu" class="input-group">
										<input id="sendObj" name="recruitStaff.sendObj" type="text" onfocus="showSendOrg(); return false;" value="${recruitStaff.sendObj}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#sendObj').val('');">清空</button>
										</div>
										<div id="sendMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="sendTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					 	<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="noticeStartTime" name="recruitStaff.noticeStartTime" value="${recruitStaff.noticeStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'noticeStartTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="noticeEndTime" name="recruitStaff.noticeEndTime" value="${recruitStaff.noticeEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'noticeEndTimeStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
				 	<legend>通知内容</legend>
					<div class="form-group">
					<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
						  <textarea id="noticeContent" name="recruitStaff.noticeContent">${recruitStaff.noticeContent}</textarea>
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixRecruitStaffAction!saveOrUpdateCollect.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixRecruitStaffAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
	/** 初始化部门下拉列表树 */
	var showNoticeOrg = initDropListTree("noticeTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "companyOrDepartment", "noticeTree", "noticeMenu");  
	var showSendOrg = initDropListTree("sendTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "sendObj", "sendTree", "sendMenu");  

</script>