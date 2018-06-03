<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 售前管理 </span><span>> 解决方案 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectSolutionAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>解决方案</h2>
		</header>
		<div class="widget-body">
			<form id="projectSolutionForm" class="form-horizontal">
				<input type="hidden" id="id" name="projectSolution.id" value="${projectSolution.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="subject" name="projectSolution.subject" value="${projectSolution.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>提交日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="submitDate" name="projectSolution.submitDate" value="${projectSolution.submitDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'submitDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="customerId" name="projectSolution.customerAccount.id" value="${projectSolution.customerAccount.id}" /> 
								<input id="customerName" name="projectSolution.customerAccount.name" value="${projectSolution.customerAccount.name}" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCustomer();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label">对应机会:</label>
						<div class="col-md-3">
							<select id="saleChanceId" name="projectSolution.saleChance.id" class="form-control">
								<option value="">------请选择------</option>
								<c:forEach items="${saleChanceList}" var="entity">
									<option value="${entity.id}" <c:if test="${projectSolution.saleChance.id == entity.id}">selected="selected"</c:if>>${entity.subject}</option>
								</c:forEach>
							</select>
						</div>
						<%-- <label class="col-md-2 control-label"><span class="text-danger">*</span>项目:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="projectId" name="projectSolution.crmProject.id" value="${projectSolution.crmProject.id}" /> 
								<input id="projectName" name="projectSolution.crmProject.subject" value="${projectSolution.crmProject.subject}" onfocus="chooseCrmProject();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseCrmProject();">选择</span>
							</div>
						</div> --%>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">方案内容:</label>
						<div class="col-md-8">
							<textarea id="solutionContent" name="projectSolution.solutionContent" class="form-control" rows="4">${projectSolution.solutionContent}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">客户反馈:</label>
						<div class="col-md-8">
							<textarea id="customerFeedback" name="projectSolution.customerFeedback" class="form-control" rows="4">${projectSolution.customerFeedback}</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">附件列表:</label>
						<div class="col-md-8">
							<div class="jarviswidget">
								<header style="height: 1px; border-color: #ddd !important"></header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 5px;">
												<div class="listMenu-float-right">
													<input type="file" id="docToUpload" name="docToUpload" onchange="fileChange();" style="width: 100%;">
												</div>
											</div>
										</div>
										<table id="uploaderTableId" class="table table-striped table-bordered table-hover" width="100%">
											<thead>
												<tr>
													<th width="10%">编号</th>
													<th width="50%">附件名称</th>
													<th width="20%">上传时间</th>
													<th width="10%">下载次数</th>
													<th width="10%">操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-success" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectSolutionAction!goList.action');">
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
	$("#projectSolutionForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#projectSolutionForm").validationEngine('validate')) {
			$("#projectSolutionForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/nvixProjectSolutionAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				var r = result.split(":");
				if(r[0] != '0'){
					loadContent('', '${nvix}/nvixnt/nvixProjectSolutionAction!goList.action');
				}else{
					showMessage(r[1]);
				}
			}
			});
		}
	}
	
	/** 加载机会*/
	function loadSaleChance() {
		if ($("#customerId").val() != '') {
			$.ajax({
				url : '${nvix}/nvixnt/nvixCompetitorAction!loadSaleChance.action?id=' + $("#customerId").val(),
				cache : false,
				success : function(html) {
					var saleChanceId = $("#saleChanceId").val();
					$("#saleChanceId").html(html);
					/* if (saleChanceId != '') {
						$("#saleChanceId").val(saleChanceId);
					} */
				}
			});
		}
	}
	
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer',function(){
			loadSaleChance();
		});
	}
	
	function chooseCrmProject() {
		chooseListData('${nvix}/nvixnt/nvixCrmProjectAction!goChooseCrmProject.action', 'single', '选择项目', 'project');
	}
	
	// 附件列表
	var uploaderColumns = [ {
		"data" : function(data) {
			return "";
		}
	}, {
		"data" : function(data) {
			return data.fileName;
		}
	}, {
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"data" : function(data) {
			return data.downloadNum + '次';
		}
	}, {
		"data" : function(data) {
			var del =  "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			var download = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"downloadUploader('" + data.id + "');\" title='下载'><span class='txt-color-blue pull-right'><i class='fa fa-download'></i></span></a>";
			return del + "&nbsp;&nbsp;" + download;
		}
	} ];

	var uploaderTable = initDataTable("uploaderTableId", "${nvix}/nvixnt/nvixProjectSolutionAction!goUploaderList.action", uploaderColumns, function(page, pageSize, orderField, orderBy) {
		var id = $("#id").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"id" : id
		};
	});
	function fileChange() {
		if ($("#projectSolutionForm").validationEngine('validate')) {
			$("#projectSolutionForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/nvixProjectSolutionAction!saveOrUpdate.action?id=" + $("#id").val(),
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(":");
					if(r[0] != '0'){
						$("#id").val(r[1]);
						uploadFileOrImage("taskFileForm", "${nvix}/nvixnt/nvixProjectSolutionAction!saveOrUpdateUploader.action?id=" + r[1], "docToUpload", "file", function(data) {
							uploaderTable.ajax.reload();
							showMessage("文件上传成功!");
						});
					}else{
						showMessage(r[1]);
					}
				}
			});
		}
	};
	//删除附件
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixProjectSolutionAction!deleteUploaderById.action?id=' + id, '是否删除附件?', uploaderTable);
	};
	// 下载
	function downloadUploader(id) {
		var url = "${nvix}/nvixnt/nvixProjectSolutionAction!downloadUploader.action?id=" + id;
		window.open(url);
	};
</script>