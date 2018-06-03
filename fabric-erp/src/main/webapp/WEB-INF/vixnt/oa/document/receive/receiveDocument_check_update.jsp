<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 公文管理 <span>> 收文管理 </span><span>> 收文审核</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-danger" type="button" onclick="nopass('${receiveDocument.id}');">
					<i class="fa fa-times"></i> 驳回
				</button>
				<button class="btn btn-success" type="button" onclick="pass('${receiveDocument.id}');">
					<i class="fa fa-check"></i> 通过
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/document/nvixReceiveDocumentAction!goCheckList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>收文审核</h2>
		</header>
		<div class="widget-body">
			<form id="receiveDocumentForm" class="form-horizontal">
				<input type="hidden" id="id" name="receiveDocument.id" value="${receiveDocument.id}" /> 
				<input type="hidden" id="status" name="receiveDocument.status" value="${receiveDocument.status}" /> 
				<fieldset>
					<legend>审核信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>审核人:</label>
						<div class="col-md-3">
							<input id="approver" name="approver" value="${receiveDocument.approver}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>审核日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="checkDate" name="checkDate" value="${receiveDocument.checkDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'checkDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>审核信息:</label>
						<div class="col-md-8">
							<textarea id="checkMemo" name="checkMemo" class="form-control" rows="4">${receiveDocument.checkMemo}</textarea>
						</div>
					</div>
					<legend>收文信息</legend>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>收文类型:</label>
						<div class="col-md-3">
							<select id="receiveTypeId" name="receiveDocument.receiveType.id" class="form-control validate[required]" disabled="disabled">
								<option value="">------请选择------</option>
								<c:forEach items="${receiveTypeList}" var="entity">
									<option value="${entity.id}" <c:if test="${receiveDocument.receiveType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">秘密等级:</label>
						<div class="col-md-3">
							<select id="secretGradeId" name="receiveDocument.secretGrade.id" class="form-control" disabled="disabled">
								<option value="">------请选择------</option>
								<c:forEach items="${secretGradeList}" var="entity">
									<option value="${entity.id}" <c:if test="${receiveDocument.secretGrade.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">紧急程度:</label>
						<div class="col-md-3">
							<select id="urgentDegreeId" name="receiveDocument.urgentDegree.id" class="form-control" disabled="disabled">
								<option value="">------请选择------</option>
								<c:forEach items="${urgentDegreeList}" var="entity">
									<option value="${entity.id}" <c:if test="${receiveDocument.urgentDegree.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>来文标题:</label>
						<div class="col-md-3">
							<input id="title" name="receiveDocument.title" value="${receiveDocument.title}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>收文编号:</label>
						<div class="col-md-3">
							<input id="code" name="receiveDocument.code" value="${receiveDocument.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>收文日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="receiveDate" name="receiveDocument.receiveDate" value="${receiveDocument.receiveDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" type="text" readonly="readonly" /> 
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>来文单位:</label>
						<div class="col-md-3">
							<input type="hidden" id="unitId" name="receiveDocument.organizationUnit.id" value="${receiveDocument.organizationUnit.id}" /> 
							<input id="unitName" type="text" value="${receiveDocument.organizationUnit.fs}" type="text" readonly="readonly" class="form-control validate[required]" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>来文字号:</label>
						<div class="col-md-3">
							<input id="word" name="receiveDocument.word" value="${receiveDocument.word}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">主题词:</label>
						<div class="col-md-3">
							<input id="subject" name="receiveDocument.subject" value="${receiveDocument.subject}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
						</div>
						<label class="col-md-2 control-label">创建人:</label>
						<div class="col-md-3">
							<input id="creator" name="receiveDocument.creator" value="${receiveDocument.creator}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="receiveDocument.memo" class="form-control" rows="4" readonly="readonly">${receiveDocument.memo}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>内容:</label>
						<div class="col-md-8">
							<textarea id="mainDocumentContent" name="receiveDocument.mainContent" class="form-control validate[required]" readonly="readonly" >${receiveDocument.mainContent}</textarea> 
					  	</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 附件列表: </label>
						<div class="col-md-8">
							<div id="" class="jarviswidget">
								<header>
									<span class="widget-icon"><i class="fa fa-table"></i></span>
									<h2>附件</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="uploaderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions" style="margin-top: 15px;">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-danger" type="button" onclick="nopass('${receiveDocument.id}');">
								<i class="fa fa-times"></i> 驳回
							</button>
							<button class="btn btn-success" type="button" onclick="pass('${receiveDocument.id}');">
								<i class="fa fa-check"></i> 通过
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/document/nvixReceiveDocumentAction!goCheckList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript" src="${nvix}/plugin/KindEditor/kindeditor-min.js"></script> 
<script type="text/javascript">
	$("#receiveDocumentForm").validationEngine();
	var contents = KindEditor.create('#mainDocumentContent',{
		basePath:'${nvix}/plugin/KindEditor/',
		width:1060,
		height:300,
		cssPath : '${nvix}/plugin/KindEditor/plugins/code/prettify.css',
		uploadJson : '${nvix}/plugin/KindEditor/jsp/upload_json.jsp',
		fileManagerJson : '${nvix}/plugin/KindEditor/jsp/file_manager_json.jsp',
		allowFileManager : true,
		readonlyMode : true
		}
	);

	function pass(id) {
		//表单验证
		if ($("#receiveDocumentForm").validationEngine('validate')) {
			var checkMemo = $("#checkMemo").val();
			$("#receiveDocumentForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/document/nvixReceiveDocumentAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					showMessage(result);
					loadContent('', '${nvix}/nvixnt/document/nvixReceiveDocumentAction!goList.action');
				}
			});
		}
	}
	
	var uploaderColumns = [ {
		"width" : "10%",
		"orderable" : false,
		"title" : "编号",
		"data" : function(data) {
			return "";
		}
		}, {
		"width" : "50%",
		"orderable" : false,
		"title" : "附件名称",
		"data" : function(data) {
			return data.name;
		}
		}, {
		"title" : "上传时间",
		"width" : "30%",
		"orderable" : false,
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		}, {
		"title" : "操作",
		"width" : "10%",
		"orderable" : false,
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"downloadUploader('" + data.id + "');\" title='下载'><span class='txt-color-blue pull-right'><i class='fa fa-download'></i></span></a>";
		}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/document/nvixReceiveDocumentAction!goUploaderList.action?id="+$("#id").val(), uploaderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	// 下载附件
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/document/nvixReceiveDocumentAction!downloadUploader.action?id=" + id;
		window.open(url);
	};
</script>