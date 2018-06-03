<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-book"></i> 招聘管理 <span onclick="">&gt; 高级人才招聘</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSuperTalentList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>高级人才招聘</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="planForm">
				<input type="hidden" id="hrSeniorId" name="hrSenior.id" value="${hrSenior.id}" />
				<fieldset>
				<legend>基本信息</legend>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 姓名:</label>
						<div class="col-md-3">
							<input id="applicantsName" name="hrSenior.applicantsName" value="${hrSenior.applicantsName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					    <label class="col-md-2 control-label"><span class="text-danger">*</span> 应聘职位:</label>
						<div class="col-md-3">
							<input id="employmentObjective" name="hrSenior.employmentObjective" value="${hrSenior.employmentObjective}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 最高学历:</label>
							<div class="col-md-3">
							<select id="highestDegree" name="hrSenior.highestDegree" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrSenior.highestDegree == "1"}'>selected="selected"</c:if>>本科</option>
								<option value="2" <c:if test='${hrSenior.highestDegree == "2"}'>selected="selected"</c:if>>硕士</option>
								<option value="3" <c:if test='${hrSenior.highestDegree == "3"}'>selected="selected"</c:if>>博士</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}应聘部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="applyTreeMenu" class="input-group">
										<input id="applicantsDepartment" name="hrSenior.applicantsDepartment" type="text" onfocus="showApplyOrg(); return false;" value="${hrSenior.applicantsDepartment}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#applicantsDepartment').val('');">清空</button>
										</div>
										<div id="applyMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="applyTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>推荐职位:</label>
						<div class="col-md-3">
							<input id="recommendedPositionName" name="hrSenior.recommendedPositionName" value="${hrSenior.recommendedPositionName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>评价人姓名:</label>
						<div class="col-md-3">
							<input id="appraiser" name="hrSenior.appraiser" value="${hrSenior.appraiser}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>招聘阶段:</label>
						<div class="col-md-3">
							<select id="recruitmentStage" name="hrSenior.recruitmentStage" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrSenior.recruitmentStage == "1"}'>selected="selected"</c:if>>日常寻访联系阶段</option>
								<option value="2" <c:if test='${hrSenior.recruitmentStage == "2"}'>selected="selected"</c:if>>准备入职联系阶段</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>评价日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="evaluationDate" name="hrSenior.evaluationDate" value="${hrSenior.evaluationDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'evaluationDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">结论:</label>
						<div class="col-md-3">
							<select id="conclusion" name="hrSenior.conclusion" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${hrSenior.conclusion == "1"}'>selected="selected"</c:if>>通过</option>
								<option value="2" <c:if test='${hrSenior.conclusion == "2"}'>selected="selected"</c:if>>未通过</option>
								<option value="3" <c:if test='${hrSenior.conclusion == "3"}'>selected="selected"</c:if>>待议</option>
							</select>
						</div>
					</div>
				 	<legend>评语</legend>
					<div class="form-group">
					<label class="col-md-2 control-label"></label>
						<div class="col-md-8">
						  <textarea id="comment" name="hrSenior.comment">${hrSenior.comment}</textarea>
						  <script type="text/javascript" src="${nvix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
						        var editor = KindEditor.create('#comment',
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
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSuperTalentList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixRecruitStaffAction!saveOrUpdateSuperTalent.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixRecruitStaffAction!goSuperTalentList.action');
			}
			});
		} else {
			return false;
		}
	};
	/** 初始化部门下拉列表树 */
	var showApplyOrg = initDropListTree("applyTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "applicantsDepartment", "applyTree", "applyMenu");  
</script>