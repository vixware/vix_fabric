<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 文件管理 <span onclick="">&gt; 我的文件</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<c:if test="${source == 'file'}">
					<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixFileAction!goFile.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
				<c:if test="${source == 'home'}">
					<button class="btn btn-default" type="button" onclick="loadContent('mid_index','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
						<i class="fa fa-rotate-left"></i> 返回
					</button>
				</c:if>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>我的文件</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="workLogForm">
				<fieldset>
					<input type="hidden" id="id" name="communication.id" value="${communication.id}" /> <input type="hidden" id="employeeIds" name="employeeIds" value="${employeeIds }" />
					<div class="form-group">
						<label class="col-md-2 control-label"> <span class="text-danger">*</span> 查阅人:
						</label>
						<div class="col-md-8">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input id="employeeNames" class="form-control validate[required]" value="${employeeNames }" data-role="tagsinput">
										<div class="input-group-btn">
											<button onclick="chooseEmployee();" type="button" class="btn btn-info">
												<i class="glyphicon glyphicon-plus"></i> 选择
											</button>
											<button onclick="$('#employeeIds').val('');$('#employeeNames').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 留言内容:
						</label>
						<div class="col-md-8">
							<textarea id="logContent" name="communication.logContent" class="form-control">${communication.logContent }</textarea>
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
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 5px;">
												<div class="listMenu-float-right">
													<input type="file" id="docToUpload" name="docToUpload" onchange="fileChange();" style="width: 100%;">
												</div>
											</div>
										</div>
										<table id="uploaderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<c:if test="${source == 'file'}">
								<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixFileAction!goFile.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</c:if>
							<c:if test="${source == 'home'}">
								<button class="btn btn-default" type="button" onclick="loadContent('mid_index','${nvix}/nvixnt/vixNtIndexAction!goListContent.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</c:if>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	var uploaderColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"title" : "编号",
	"data" : function(data) {
		return "";
	}
	}, {
	"width" : "50%",
	"name" : "fileName",
	"title" : "附件名称",
	"data" : function(data) {
		return data.fileName;
	}
	}, {
	"title" : "上传时间",
	"width" : "30%",
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
	}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/nvixFileAction!goUploaderList.action?id=${communication.id}", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
	//上传附件
	function fileChange() {
		uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/nvixFileAction!saveOrUpdateUploader.action?id=${communication.id}", "docToUpload", "file", function(data) {
			uploaderTable.ajax.reload();
			showMessage("文件上传成功!");
		});
	};

	function chooseEmployee() {
		chooseListData('${nvix}/nvixnt/nvixFileAction!goEmployeeChoose.action', 'multi', '选择人员', null, function() {
			var emp = chooseMap.valueSetWithClear().split(":");
			if (emp != '' && emp.length == 2) {
				$('#employeeIds').val(emp[0]);
				$('#employeeNames').val(emp[1]);
			} else {
				layer.alert("请选择人员!");
			}
		});
	};
	//删除附件
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixFileAction!deleteUploaderByIds.action?ids=' + id, '是否删除附件?', uploaderTable);
	};
	//保存日报
	$("#workLogForm").validationEngine();
	function saveOrUpdate() {
		if ($("#workLogForm").validationEngine('validate')) {
			$("#workLogForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixFileAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/nvixFileAction!goFile.action');
			}
			});
		} else {
			return false;
		}
	};
	pageSetUp();
</script>