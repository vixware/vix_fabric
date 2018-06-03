<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="objectExpandForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/system/nvixObjectExpandAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="objectExpand.id" value="${objectExpand.id}" />
	<input type="hidden" id="isGenerateTable" name ="objectExpand.isGenerateTable" value="${objectExpand.isGenerateTable}"/>
	<input type="hidden" id="specTableIsGenerate" name ="objectExpand.specTableIsGenerate" value="${objectExpand.specTableIsGenerate}"/>
	<input type="hidden" id="code" name="objectExpand.code" value="${objectExpand.code}"/>
	<input type="hidden" id="isReference" name="objectExpand.isReference" value="${objectExpand.isReference}"/>
	<input type="hidden" id="foreignKey" name="objectExpand.foreignKey" value="${objectExpand.foreignKey}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="objectExpand.name" value="${objectExpand.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>扩展对象:</label>
			<div class="col-md-4">
				<select id="extendedObjectCode" name="objectExpand.extendedObjectCode" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<s:iterator value="expandTypeConstantMap">
						<option value="${key}" <s:if test="objectExpand.extendedObjectCode == key">selected='selected'</s:if>><s:text name="getText(value)"/></option>
					</s:iterator>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="objectExpand.status == 1">active</s:if>">
						<input type="radio" value="1" id="status" name="objectExpand.status" class="validate[required]" <s:if test="objectExpand.status == 1">checked="checked"</s:if>>激活
					</label>
					<label class="btn btn-default <s:if test="objectExpand.status == 0">active</s:if>">
						<input type="radio" value="0" id="status" name="objectExpand.status" <s:if test="objectExpand.status == 0">checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-4">
				<textarea rows="3" id="memo" name="objectExpand.memo" value="${objectExpand.memo}" class="form-control">${objectExpand.memo}</textarea>
			</div>
		</div>
	</fieldset>
	<div id="objectExpandDiv" class="jarviswidget jarviswidget-color-white">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>明细列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div style="margin: 5px;">
					<div class="form-group" style="margin: 0 0px;">
						<div class="col-md-3">
							<input type="text" value="" placeholder="名称" class="form-control" id="detailName">
						</div>
						<button onclick="objectExpandDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#detailName').val('');objectExpandDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
						<div class="listMenu-float-right">
							<button onclick="addObjectExpandDetail('');" type="button" class="btn btn-primary">
								<i class="glyphicon glyphicon-plus"></i>
								<s:text name="add" />
							</button>
						</div>
					</div>
				</div>
				<table id="objectExpandDetail" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th width="10%">显示名称</th>
							<th width="10%">对象类型</th>
							<th width="10%">扩展表名</th>
							<th width="10%">列名</th>
							<th width="10%">列类型</th>
							<th width="10%">默认值</th>
							<th width="10%">是否显示</th>
							<th width="10%">是否必填</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	var objectExpandDetailColumns = [
   		{"name":"displayName","data" : function(data) {return data.displayName;}},
   		{"name":"objectExpand","data" : function(data) {return data.objectExpand == null ? '' : data.objectExpand.name;}},
   		{"name":"expandTableName","data" : function(data) {return data.expandTableName == null ? '' : data.expandTableName;}},
   		{"name":"fieldName","data" : function(data) {return data.fieldName;}},
   		{"name":"fieldType","data" : function(data) { 
   			if(data.fieldType == 'text'){return '文本';}
   			if(data.fieldType == 'date'){return '日期';}
   			if(data.fieldType == 'number'){return '数字';}
   			if(data.fieldType == 'select'){return '下拉列表';}
   			if(data.fieldType == 'checkbox'){return '多选';}
   			if(data.fieldType == 'radio'){return '单选';}
   			return '';
   		}},
   		{"name":"defaultValue","data" : function(data) {return data.defaultValue == '' ? '无默认值' : data.defaultValue;}},
   		{"name":"isShow","data" : function(data) {return data.isShow == '1' ? '是' : '否';}},
   		{"name":"isRequired","data" : function(data) {return data.isRequired == '1' ? '是' : '否';}},
   		{"orderable":false,"data" : function(data) {
			if(data.id == null){return '';}
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteDetailById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];

	var objectExpandDetailTable = initDataTable("objectExpandDetail","${nvix}/nvixnt/system/nvixObjectExpandAction!getObjectExpandDetailJson.action",objectExpandDetailColumns,function(page,pageSize,orderField,orderBy){
		var parentId = $("#id").val();
	   	var detailName = $("#detailName").val();
	   	detailName = Url.encode(detailName);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"parentId":parentId,"detailName":detailName};
	},'10','0','0');
	
	/** 添加联系人的回调方法，当客户信息未保存时，先保客户信息再添加联系人。 */
	function addObjectExpandDetail(){
		var id = $("#id").val();
		if(id == ''){
			if($('#objectExpandForm').validationEngine('validate')){
				$("#objectExpandForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						$("#id").val($.trim(r[0]));
						saveOrUpdateDetail('');
					}
				});
			}
		}else{
			saveOrUpdateDetail('');
		}
	}
	
	function saveOrUpdateDetail(id){
		var parentId = $("#id").val();
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixObjectExpandAction!goSaveOrUpdateDetail.action?id=' + id +'&parentId=' + parentId,'objectExpandDetailForm',id == '' ? '新增明细' : '修改明细',750,490,objectExpandDetailTable,null,function(){
			objectExpandDetailTable.ajax.reload();
		});
	}
	
	function deleteDetailById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixObjectExpandAction!deleteDetailById.action?id=' + id,'是否删除该条明细?', objectExpandDetailTable);
	};
</script>