<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>文件调查<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goQuessionList.action');">
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
				<input type="hidden" id="questionId" name="question.id" value="${question.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 问卷名称:</label>
						<div class="col-md-3">
							<input id="questionName" name="question.questionName" value="${question.questionName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>${vv:varView('vix_mdm_item')}发送部门:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div id="departmentTreeMenu" class="input-group">
										<input id="questionOrg" name="question.questionOrg" type="text" onfocus="showHrplanOrg(); return false;" value="${question.questionOrg}" type="text" readonly="readonly" style="cursor:pointer;" class="form-control validate[required]" />
										<div class="input-group-btn">
											<button type="button" class="btn btn-default" onclick="$('#questionOrg').val('');">清空</button>
										</div>
										<div id="departmentMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
											<ul id="departmentTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 问卷分数:</label>
						<div class="col-md-3">
							<input id="scole" name="question.scole" value="${question.scole}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 问卷来源:</label>
						<div class="col-md-3">
							<input id="resourse" name="question.resourse" value="${question.resourse}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否使用:</label>
						<div class="col-md-3">
							<select id="isUse" name="question.isUse" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${question.isUse == "1"}'>selected="selected"</c:if>>是</option>
								<option value="2" <c:if test='${question.isUse == "2"}'>selected="selected"</c:if>>否</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 建立时间:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="builtDate" name="question.builtDate" value="${question.builtDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'builtDateStr'});">
								<i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">文件内容:</label>
						<div class="col-md-8">
							<textarea id="questionContent" name="question.questionContent" class="form-control" rows="4">${question.questionContent}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainProjectAction!goQuessionList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixTrainProjectAction!saveOrUpdateQuession.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainProjectAction!goQuessionList.action');
			}
			});
		} else {
			return false;
		}
	};
   
	/** 初始化部门下拉列表树 */
	var showHrplanOrg = initDropListTree("departmentTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "", "questionOrg", "departmentTree", "departmentMenu");
</script>