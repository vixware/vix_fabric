<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>活动总结<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivitySummarizeList.action');">
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
				<input type="hidden" id="activitySummarizeId" name="activitySummarize.id" value="${activitySummarize.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 活动名称:</label>
						<div class="col-md-3">
							<input id="activityName" name="activitySummarize.activityName" value="${activitySummarize.activityName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 活动状态:</label>
						<div class="col-md-3">
							<select id="activitystatus" name="activitySummarize.activitystatus" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${activitySummarize.activitystatus == "1"}'>selected="selected"</c:if>>活动开始</option>
								<option value="2" <c:if test='${activitySummarize.activitystatus == "2"}'>selected="selected"</c:if>>活动结束</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="planStartDate" name="activitySummarize.planStartDate" value="${activitySummarize.planStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="planEndDate" name="activitySummarize.planEndDate" value="${activitySummarize.planEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'planEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际开始日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectiveStartDate" name="activitySummarize.effectiveStartDate" value="${activitySummarize.effectiveStartDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectiveStartDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际结束日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="effectiveEndDate" name="activitySummarize.effectiveEndDate" value="${activitySummarize.effectiveEndDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'effectiveEndDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>计划费用:</label>
						<div class="col-md-3">
							<input id="courseFees" name="activitySummarize.courseFees" value="${activitySummarize.courseFees}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>实际费用:</label>
						<div class="col-md-3">
							<input id="cost" name="activitySummarize.cost" value="${activitySummarize.cost}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>总结日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="summarizeDate" name="activitySummarize.summarizeDate" value="${activitySummarize.summarizeDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'summarizeDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-2 control-label">活动总结:</label>
						<div class="col-md-8">
							<textarea id="remarkss" name="activitySummarize.remarkss" class="form-control" rows="4">${activitySummarize.remarkss}</textarea>
						</div>
					</div>
					
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivitySummarizeList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixTrainProjectAction!saveOrUpdateActivitySummarize.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainProjectAction!goActivitySummarizeList.action');
			}
			});
		} else {
			return false;
		}
	};
   
	
</script>