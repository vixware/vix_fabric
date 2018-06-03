<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-user"></i>培训资料<span>> 新增 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goDataList.action');">
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
				<input type="hidden" id="trainingDataId" name="trainingData.id" value="${trainingData.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 资料编码:</label>
						<div class="col-md-3">
							<input id="datumCode" name="trainingData.datumCode" value="${trainingData.datumCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 资料名称:</label>
						<div class="col-md-3">
							<input id="datumName" name="trainingData.datumName" value="${trainingData.datumName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>资料类别:</label>
						<div class="col-md-3">
							<select id="datumType" name="trainingData.datumType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${trainingData.datumType == "1"}'>selected="selected"</c:if>>培训资料</option>
								<option value="2" <c:if test='${trainingData.datumType == "2"}'>selected="selected"</c:if>>课程资料</option>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>资料作者:</label>
						<div class="col-md-3">
							<input id="datumauthor" name="trainingData.datumauthor" value="${trainingData.datumauthor}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
				    <div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>资料费用:</label>
						<div class="col-md-3">
							<input id="datumCost" name="trainingData.datumCost" value="${trainingData.datumCost}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>出版单位:</label>
						<div class="col-md-3">
							<input id="publishingUnit" name="trainingData.publishingUnit" value="${trainingData.publishingUnit}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>存放位置:</label>
						<div class="col-md-3">
							<input id="storageLocation" name="trainingData.storageLocation" value="${trainingData.storageLocation}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">资料简介:</label>
						<div class="col-md-8">
							<textarea id="profile" name="trainingData.profile" class="form-control" rows="4">${trainingData.profile}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="remarksss" name="trainingData.remarksss" class="form-control" rows="4">${trainingData.remarksss}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/hr/nvixTrainSystemAction!goDataList.action');">
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
			url : "${nvix}/nvixnt/hr/nvixTrainSystemAction!saveOrUpdateData.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixTrainSystemAction!goDataList.action');
			}
			});
		} else {
			return false;
		}
	};

</script>