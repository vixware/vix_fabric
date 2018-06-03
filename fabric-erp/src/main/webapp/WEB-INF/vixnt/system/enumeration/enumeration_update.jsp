<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="enumerationForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/system/nvixEnumerationAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="enumeration.id" value="${enumeration.id}" />
	<input type="hidden" id="enumCategoryId" name="enumeration.enumerationCategory.id" value="${categoryId}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>分类:</label>
			<s:if test="enumeration.enumerationCategory.id != null">
				<div class="col-md-4">
					<input id="name" value="${enumeration.enumerationCategory.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
				</div>
			</s:if>
			<s:else>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div id="enumTreeMenu" class="input-group">
								<input id="enumCategory" type="text" onfocus="showICMenu(); return false;" value="${enumeration.enumerationCategory.name}" type="text" class="form-control validate[required]"/>
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#parentEnumCategoryId').val('');$('#parentEnumCategory').val('');">清空</button>
								</div>
								<div id="enumTreeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
									<ul id="enumCategoryTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			<label class="col-md-2 control-label">编码:</label>
			<div class="col-md-4">
				<input id="enumerationCode" name="enumeration.enumerationCode" value="${enumeration.enumerationCode}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="enumeration.name" value="${enumeration.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>显示名称:</label>
			<div class="col-md-4">
				<input id="displayName" name="enumeration.displayName" value="${enumeration.displayName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
	<div id="enumValueDiv" class="jarviswidget jarviswidget-color-white">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>明细列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div style="margin: 5px;">
					<div class="form-group" style="margin: 0 0px;">
						<div class="col-md-3">
							<input type="text" value="" placeholder="名称" class="form-control" id="searchEnumValueName">
						</div>
						<button onclick="enumValueTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchEnumValueName').val('');enumValueTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
						<div class="listMenu-float-right">
							<button onclick="addEnumValue('');" type="button" class="btn btn-primary">
								<i class="glyphicon glyphicon-plus"></i>
								<s:text name="add" />
							</button>
						</div>
					</div>
				</div>
				<table id="enumValue" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th width="25%">显示名称</th>
							<th width="25%">值</th>
							<th width="15%">是否缺省</th>
							<th width="15%">状态</th>
							<th width="20%">操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	/** 初始化分类下拉列表树 */
	var showICMenu = initDropListTree("enumTreeMenu","${nvix}/nvixnt/system/nvixEnumerationCategoryAction!findTreeToJson.action","enumCategoryId","enumCategory","enumCategoryTree","enumTreeMenuContent");
	
	var enumValueColumns = [
   		{"name":"displayName","data" : function(data) {return data.displayName;}},
   		{"name":"value","data" : function(data) {return data.value;}},
   		{"name":"isDefault","data" : function(data) {return data.isDefault == '0' ? '否' : '是';}},
   		{"name":"enable","data" : function(data) {return data.enable == '0' ? '禁用' : '启用';}},
   		{"orderable":false,"data" : function(data) {
			if(data.id == null){return '';}
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdateEnumValue('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteEnumValueById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];

	var enumValueTable = initDataTable("enumValue","${nvix}/nvixnt/system/nvixEnumerationAction!getEnumValueJson.action",enumValueColumns,function(page,pageSize,orderField,orderBy){
		var parentId = $("#id").val();
	   	var enumValueName = $("#searchEnumValueName").val();
	   	enumValueName = Url.encode(enumValueName);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"parentId":parentId,"enumValueName":enumValueName};
	},'10','0','0');
	
	/** 添加联系人的回调方法，当客户信息未保存时，先保客户信息再添加联系人。 */
	function addEnumValue(){
		var id = $("#id").val();
		if(id == ''){
			if($('#enumerationForm').validationEngine('validate')){
				$("#enumerationForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(",");
						$("#id").val($.trim(r[0]));
						saveOrUpdateEnumValue('');
					}
				});
			}
		}else{
			saveOrUpdateEnumValue('');
		}
	}
	
	function saveOrUpdateEnumValue(id){
		var parentId = $("#id").val();
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixEnumerationAction!goSaveOrUpdateEnumValue.action?id=' + id +'&parentId=' + parentId,'enumValueForm',id == '' ? '新增明细' : '修改明细',750,350,enumValueTable,null,function(){
			enumValueTable.ajax.reload();
		});
	}
	
	function deleteEnumValueById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixEnumerationAction!deleteEnumValueById.action?id=' + id,'是否删除该条明细?', enumValueTable);
	};
</script>