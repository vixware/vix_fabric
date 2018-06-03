<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训课程<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goList.action');">
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
				<input type="hidden" id="courseId" name="course.id" value="${course.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 课程编码:</label>
						<div class="col-md-3">
							<input id="courseCode" name="course.courseCode" value="${course.courseCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 课程名称:</label>
						<div class="col-md-3">
							<input id="courseName" name="course.courseName" value="${course.courseName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>课程类别:</label>
						<div class="col-md-3">
							<select id="courseType" name="course.courseType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${course.courseType == "1"}'>selected="selected"</c:if>>入职培训</option>
								<option value="2" <c:if test='${course.courseType == "2"}'>selected="selected"</c:if>>技术课程</option>
								<option value="3" <c:if test='${course.courseType == "3"}'>selected="selected"</c:if>>业务课程</option>
								<option value="4" <c:if test='${course.courseType == "4"}'>selected="selected"</c:if>>管理课程</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>需要课时:</label>
						<div class="col-md-3">
							<input id="needs" name="course.needs" value="${course.needs}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>课程费用:</label>
						<div class="col-md-3">
							<input id="courseFees" name="course.courseFees" value="${course.courseFees}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>需要性质:</label>
						<div class="col-md-3">
							<input id="natureCourse" name="course.natureCourse" value="${course.natureCourse}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>有效开始时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectiveStartDate" name="course.effectiveStartDate" value="${course.effectiveStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectiveStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>有效结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectiveEndDate" name="course.effectiveEndDate" value="${course.effectiveEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectiveEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">课程内容:</label>
						<div class="col-md-8">
							<textarea id="courseeducation" name="course.courseeducation" class="form-control" rows="4">${course.courseeducation}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remarkss" name="course.remarkss" class="form-control" rows="4">${course.remarkss}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixTrainSystemAction!saveOrUpdateCourse.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainSystemAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};

</script>