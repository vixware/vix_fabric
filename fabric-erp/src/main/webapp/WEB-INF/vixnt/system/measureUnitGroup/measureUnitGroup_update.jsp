<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="measureUnitGroupForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixntMeasureUnitGroupAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="measureUnitGroup.id" value="${measureUnitGroup.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-1 control-label"><span class="text-danger">*</span>公司:</label>
			<div class="col-md-4">
				<select id="organizationId" name="measureUnitGroup.organization.id" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${organizationList}" var="entity">
						<option value="${entity.id}" <c:if test="${measureUnitGroup.organization.id == entity.id}">selected="selected"</c:if>>${entity.orgName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-1 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="measureUnitGroup.code" value="${measureUnitGroup.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="measureUnitGroup.name" value="${measureUnitGroup.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-1 control-label"><span class="text-danger">*</span>类别:</label>
			<div class="col-md-4">
				<select id="type" name="measureUnitGroup.type" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<option value="1" <s:if test="%{measureUnitGroup.type == 1}">selected</s:if>>无换算率</option>
					<option value="2" <s:if test="%{measureUnitGroup.type == 2}">selected</s:if>>固定换算率</option>
					<option value="3" <s:if test="%{measureUnitGroup.type == 3}">selected</s:if>>浮动换算率</option>
				</select>
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>状态:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="measureUnitGroup.status == 1">active</s:if>"> <input type="radio" value="1" id="status" name="measureUnitGroup.status" class="form-control validate[required]" <s:if test="measureUnitGroup.status == 1">checked="checked"</s:if>>启用
					</label> <label class="btn btn-default <s:if test="measureUnitGroup.status == 0">active</s:if>"> <input type="radio" value="0" id="status" name="measureUnitGroup.status" <s:if test="measureUnitGroup.status == 0">checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
		</div>
	</fieldset>
	<div class="form-group">
		<label class="col-md-2 control-label"></label>
		<div class="col-md-12">
			<div id="" class="jarviswidget jarviswidget-color-white">
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i></span>
					<h2>计量单位组明细</h2>
				</header>
				<div>
					<div class="widget-body no-padding">
						<div style="margin: 5px;">
							<div class="form-group" style="margin: 0 0px;">
								<div class="listMenu-float-right">
									<button onclick="addMeasureUnitGroupDetail();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-plus"></i>
										<s:text name="add" />
									</button>
								</div>
							</div>
						</div>
						<table id="measureUnitGroupDetail" class="table table-striped table-bordered table-hover" width="100%">
							<thead>
								<tr>
									<th width="10%">编码</th>
									<th width="35%">名称</th>
									<th width="30%">换算率</th>
									<th width="15%">主计量单位</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	var measureUnitGroupDetailColumns = [ {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name == null ? '' : data.name;
	}
	}, {
	"name" : "rate",
	"data" : function(data) {
		return data.rate;
	}
	}, {
	"name" : "isMeasurementUnit",
	"data" : function(data) {
		return data.isMeasurementUnit == '1' ? '是' : '否';
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id == null) {
			return '';
		}
		var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateMeasureUnitGroupDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var measureUnitGroupDetailTable = initDataTable("measureUnitGroupDetail", "${nvix}/nvixnt/nvixntMeasureUnitGroupAction!getMeasureUnitGroupDetailJson.action", measureUnitGroupDetailColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $('#id').val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		'parentId' : parentId
		};
	});

	$("#measureUnitGroupForm").validationEngine();

	function addMeasureUnitGroupDetail() {
		var id = $("#id").val();
		if (id == '') {
			if ($('#measureUnitGroupForm').validationEngine('validate')) {
				$("#measureUnitGroupForm").ajaxSubmit({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					var r = result.split(",");
					$("#id").val($.trim(r[0]));
					saveOrUpdateMeasureUnitGroupDetail('');
				}
				});
			}
		} else {
			saveOrUpdateMeasureUnitGroupDetail('');
		}
	}

	/** 添加组明细*/
	function saveOrUpdateMeasureUnitGroupDetail(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntMeasureUnitGroupAction!goSaveOrUpdateMeasureUnitGroupDetail.action?id=' + id + '&parentId=' + $("#id").val(), 'measureUnitGroupDetailForm', '添加计量组明细', 720, 320, measureUnitGroupDetailTable, null, function() {
			measureUnitGroupDetailTable.ajax.reload();
		});
	}

	function deleteDetailById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntMeasureUnitGroupAction!deleteDetailById.action?id=' + id, '是否删该条明细?', measureUnitGroupDetailTable);
	}
</script>