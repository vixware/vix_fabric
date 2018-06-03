<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw"></i>票据管理<span>> 票据 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/money/nvixCollectionMoneyAction!goNoteList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>票据</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="employeeForm">
				<input type="hidden" id="employeeId" name="employee.id" value="${employee.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 收到日期:</label>
						<div class="col-md-3">
							<input id="name" name="employee.name" value="${employee.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 结算方式:</label>
						<div class="col-md-3">
							<input id="staffJobNumber" name="employee.staffJobNumber" value="${employee.staffJobNumber}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 票据编码:</label>
						<div class="col-md-3">
							<input id="subordinatePosition" name="employee.subordinatePosition" value="${employee.subordinatePosition}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 票据种类:</label>
						<div class="col-md-3">
							<select id="employeeType" name="employee.employeeType" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${employee.employeeType == "1"}'>selected="selected"</c:if>>在职</option>
								<option value="0" <c:if test='${employee.employeeType == "0"}'>selected="selected"</c:if>>后备</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">承兑银行:</label>
						<div class="col-md-3">
							<input id="residenceAddress" name="employee.residenceAddress" value="${employee.residenceAddress}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 承兑单位:</label>
						<div class="col-md-3">
							<input id="birthplace" name="employee.birthplace" value="${employee.birthplace}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 背书单位:</label>
						<div class="col-md-3">
							<input id="idNumber" name="employee.idNumber" value="${employee.idNumber}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 背书金额:</label>
						<div class="col-md-3">
							<input id="idNumber" name="employee.idNumber" value="${employee.idNumber}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 票据面值:</label>
						<div class="col-md-3">
							<input id="professional" name="employee.professional" value="${employee.professional}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 票面利率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="telephone" name="employee.telephone" value="${employee.telephone}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>签发日期:</label>
						<div class="col-md-3">
							<input id="national" name="" value="" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 到期日:</label>
						<div class="col-md-3">
							<input id="national" name="employee.national" value="${employee.national}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>收款人:</label>
						<div class="col-md-3">
							<input id="national" name="" value="" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"> 收款人银行:</label>
						<div class="col-md-3">
							<input id="national" name="" value="" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>收款人账号:</label>
						<div class="col-md-3">
							<input id="national" name="" value="" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 部门:</label>
						<div class="col-md-3">
							<input id="currentResidence" name="employee.currentResidence" value="${employee.currentResidence}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 业务员:</label>
						<div class="col-md-3">
							<select id="qualificationsCode" name="employee.qualificationsCode" class="form-control validate[required]">
							    <option value="">请选择</option>
								<option value="1" <c:if test='${employee.qualificationsCode == "1"}'>selected="selected"</c:if>>高中</option>
								<option value="2" <c:if test='${employee.qualificationsCode == "2"}'>selected="selected"</c:if>>中专</option>
								<option value="3" <c:if test='${employee.qualificationsCode == "3"}'>selected="selected"</c:if>>大专</option>
								<option value="4" <c:if test='${employee.qualificationsCode == "4"}'>selected="selected"</c:if>>本科</option>
								<option value="5" <c:if test='${employee.qualificationsCode == "5"}'>selected="selected"</c:if>>硕士</option>
								<option value="6" <c:if test='${employee.qualificationsCode == "6"}'>selected="selected"</c:if>>博士</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">摘要:</label>
						<div class="col-md-8">
							<textarea id="memo" name="employee.memo" class="form-control" rows="4">${employee.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/money/nvixCollectionMoneyAction!goNoteList.action');">
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
    pageSetUp();
    var supplierAttachmentsColumns =  [ {
		"title" : "编号",
		"width" : "5%",
		"data" : function(data) {
		return "";
		}
    }, {
		"title" : "附件名称",
		"width" : "15%",
		"data" : function(data) {
			return data.fileName;
		}
	}, {
		"title" : "上传路径",
		"width" : "40%",
		"data" : function(data) {
			return data.filePath;
		}
	}, {
		"title" : "上传时间",
		"width" : "20%",			
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
	}, {
		"title" : "操作",
		"width" : "15%",	
		"data" : function(data) {
			return "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteUploaderById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		}
	} ];
	var supplierAttachmentsTable = initDataTable("supplierAttachmentsTableId", "${nvix}/nvixnt/hr/nvixNameBookAction!getUploadersList.action", supplierAttachmentsColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $('#searchName').val();
		searchName = Url.encode(searchName);
		var id = $("#employeeId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"id" : id,
		"searchName":searchName
		};
	},10,"0");
	
	/*删除*/
	function deleteUploaderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixNameBookAction!deleteUploaderById.action?id=' + id, '是否删除附件?', supplierAttachmentsTable);
	};
	
	$("#employeeForm").validationEngine();
	function saveOrUpdate() {
		if ($("#employeeForm").validationEngine('validate')) {
			$("#employeeForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/hr/nvixNameBookAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/hr/nvixNameBookAction!goEmployeeList.action');
			}
			});
		} else {
			return false;
		}
	};
	
	/** 初始化部门下拉列表树 */
	var showEmplpyeeOrg = initDropListTree("emplpyeeOrgTreeMenu", "${nvix}/nvixnt/wxpOrganizationUnitAction!findOrgAndUnitTreeToJson.action", "emplpyeeOrgIds", "emplpyeeOrgNames", "emplpyeeOrgTree", "emplpyeeOrgMenu");
	//弹窗方式初始化部门
	function chooseItemCatagory() {
		var ids = $("#objectExpandId").val();
		chooseListData('${nvix}/nvixnt/mdm/nvixntItemAction!goChooseMultiItemCatalog.action', 'multi', '选择分类', null, function() {
			var ItemCatagory = chooseMap.valueSetWithClear().split(":");
			if (ItemCatagory != '' && ItemCatagory.length == 2) {
				$('#itemCatalogIds').val(ItemCatagory[0]);
				$('#temCatalogNames').val(ItemCatagory[1]);
			} else {
				layer.alert("请选择商品类型!");
			}
		});
	}
	
</script>