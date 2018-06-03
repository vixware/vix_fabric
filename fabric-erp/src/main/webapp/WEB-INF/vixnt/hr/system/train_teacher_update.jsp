<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训讲师<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goTeacherList.action');">
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
				<input type="hidden" id="trainingLecturerId" name="trainingLecturer.id" value="${trainingLecturer.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 讲师姓名:</label>
						<div class="col-md-3">
							<input id="lecturerName" name="trainingLecturer.lecturerName" value="${trainingLecturer.lecturerName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
						<div class="col-md-3">
							<input id="lecturerCode" name="trainingLecturer.lecturerCode" value="${trainingLecturer.lecturerCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>讲师性质:</label>
						<div class="col-md-3">
							<select id="lecturerNature" name="trainingLecturer.lecturerNature" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainingLecturer.lecturerNature == "1"}'>selected="selected"</c:if>>内部讲师</option>
								<option value="2" <c:if test='${trainingLecturer.lecturerNature == "2"}'>selected="selected"</c:if>>外部讲师</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>职位:</label>
						<div class="col-md-3">
							<input id="lecturerPosition" name="trainingLecturer.lecturerPosition" value="${trainingLecturer.lecturerPosition}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}所属部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="departmentTreeMenu" class="input-group">
										<input id="branches" name="trainingLecturer.branches" type="text" onfocus="showHrplanOrg(); return false;" value="${trainingLecturer.branches}" type="text" readonly="readonly" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#branches').val('');">清空</button>
										</div>
										<div id="departmentMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="departmentTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>讲师级别:</label>
						<div class="col-md-3">
							<select id="lecturerLevel" name="trainingLecturer.lecturerLevel" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainingLecturer.lecturerLevel == "1"}'>selected="selected"</c:if>>助理讲师</option>
								<option value="2" <c:if test='${trainingLecturer.lecturerLevel == "2"}'>selected="selected"</c:if>>讲师</option>
								<option value="3" <c:if test='${trainingLecturer.lecturerLevel == "3"}'>selected="selected"</c:if>>高级讲师</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>讲师类别:</label>
						<div class="col-md-3">
							<select id="lecturerType" name="trainingLecturer.lecturerType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainingLecturer.lecturerType == "1"}'>selected="selected"</c:if>>内聘讲师</option>
								<option value="2" <c:if test='${trainingLecturer.lecturerType == "2"}'>selected="selected"</c:if>>外聘讲师</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>费用:</label>
						<div class="col-md-3">
							<input id="lecturerCost" name="trainingLecturer.lecturerCost" value="${trainingLecturer.lecturerCost}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">资料简介:</label>
						<div class="col-md-8">
							<textarea id="lecturerIntroduction" name="trainingLecturer.lecturerIntroduction" class="form-control" rows="4">${trainingLecturer.lecturerIntroduction}</textarea>
						</div>
					</div>
					
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goTeacherList.action');">
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
		if ($("#planForm").validationEngine('validate')) {
			$("#planForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixTrainSystemAction!saveOrUpdateTeacher.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainSystemAction!goTeacherList.action');
			}
			});
		} else {
			return false;
		}
	};
   
	/** 初始化部门下拉列表树 */
	var showHrplanOrg = initDropListTree("departmentTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "branches", "departmentTree", "departmentMenu");
</script>