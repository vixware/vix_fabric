<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>课程总结<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goCourseSummarizeList.action');">
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
				<input type="hidden" id="courseSummarizeId" name="courseSummarize.id" value="${courseSummarize.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 课程编码:</label>
						<div class="col-md-3">
							<input id="courseCode" name="courseSummarize.courseCode" value="${courseSummarize.courseCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 课程名称:</label>
						<div class="col-md-3">
							<input id="courseName" name="courseSummarize.courseName" value="${courseSummarize.courseName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>培训所属活动:</label>
						<div class="col-md-3">
							<input id="activity" name="courseSummarize.activity" value="${courseSummarize.activity}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>开课时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectiveStartDate" name="courseSummarize.effectiveStartDate" value="${courseSummarize.effectiveStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectiveStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectiveEndDate" name="courseSummarize.effectiveEndDate" value="${courseSummarize.effectiveEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectiveEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>课时:</label>
						<div class="col-md-3">
							<input id="needs" name="courseSummarize.needs" value="${courseSummarize.needs}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际费用:</label>
						<div class="col-md-3">
							<input id="courseFees" name="courseSummarize.courseFees" value="${courseSummarize.courseFees}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际培训渠道:</label>
						<div class="col-md-3">
							<input id="channel" name="courseSummarize.channel" value="${courseSummarize.channel}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际培训讲师:</label>
						<div class="col-md-3">
							<input id="teacher" name="courseSummarize.teacher" value="${courseSummarize.teacher}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 实际参加人数:</label>
						<div class="col-md-3">
							<input id="personNumber" name="courseSummarize.personNumber" value="${courseSummarize.personNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 出席率:</label>
						<div class="col-md-3">
							<input id="probability" name="courseSummarize.probability" value="${courseSummarize.probability}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 平均成绩:</label>
						<div class="col-md-3">
							<input id="score" name="courseSummarize.score" value="${courseSummarize.score}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 通过率:</label>
						<div class="col-md-3">
							<input id="throuth" name="courseSummarize.throuth" value="${courseSummarize.throuth}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">课程评价:</label>
						<div class="col-md-8">
							<textarea id="remarkss" name="courseSummarize.remarkss" class="form-control" rows="4">${courseSummarize.remarkss}</textarea>
						</div>
					</div>
					
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goCourseSummarizeList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixTrainProjectAction!saveOrUpdateCourseSummarize.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainProjectAction!goCourseSummarizeList.action');
			}
			});
		} else {
			return false;
		}
	};
   
	
</script>