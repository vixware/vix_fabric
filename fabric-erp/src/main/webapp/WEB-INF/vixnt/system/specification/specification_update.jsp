<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="specificationForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/system/nvixSpecificationAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="specification.id" value="${specification.id}" />
	<input type="hidden" id="specificationTableName" name = "specification.specificationTableName" value="${specification.specificationTableName}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">商品分类:</label>
			<div class="col-md-3">
				<div class="row">
					<div class="col-sm-12">
						<div id="treeMenu" class="input-group">
							<input id="itemCatalogId" type="hidden" name="specification.itemCatalog.id" value="${specification.itemCatalog.id}" class="form-control"/>
							<input id="itemCatalogName" name="specification.itemCatalog.name"  onfocus="showMenu(); return false;" value="${specification.itemCatalog.name}" type="text" class="form-control"/>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#itemCatalogId').val('');$('#itemCatalogName').val('');">
									清空
								</button>
							</div>
							<div id="menuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
								<ul id="itemCatalogTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-3">
				<input id="code" name="specification.code" value="${specification.code}" type="text" data-prompt-position="topLeft" class="form-control validate[required]"/> 
			</div>
		</div>
		<div class="form-group">
			 <label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-3">
				<input id="name" name="specification.name" value="${specification.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]"/>
			</div>
			<label class="col-md-2 control-label">顺序:</label>
			<div class="col-md-3">
				<input id="orderBy" name="specification.orderBy" value="${specification.orderBy}" type="text" data-prompt-position="topLeft" class="form-control validate[custom[integer]]"/> 
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-3">
				<input id="memo" name="specification.memo" value="${specification.memo}" type="text" class="form-control"/> 
			</div>
			<label class="col-md-2 control-label">规格表:</label>
			<div class="col-md-3">
				<input value="${specification.specificationTableName}" readonly="readonly" type="text" class="form-control"/> 
			</div>
		</div>
		<div class="form-group" id="itemDiv">
						<div class="col-md-12">
							<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>规格明细</h2>
								</header>
								<div>
									<div class="widget-body no-padding">
										<div style="margin: 5px;">
											<div class="form-group" style="margin: 0 0px;">
												<div class="col-md-3">
													<input type="text" value="" placeholder="名称" class="form-control" id="searchSpecDetailName">
												</div>
												<button onclick="specDetailTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
													<i class="glyphicon glyphicon-search"></i> 检索
												</button>
												<button onclick="$('#searchItem').val('');specDetailTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
													<i class="glyphicon glyphicon-repeat"></i> 清空
												</button>
												<div class="listMenu-float-right">
													<button onclick="saveOrUpdateDetail('');" type="button" class="btn btn-primary">
														<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
													</button>
												</div>
											</div>
										</div>
										<table id="specificationDetail" class="table table-striped table-bordered table-hover" width="100%">
											<thead>			                
												<tr>
													<th width="8%">编号</th>
													<th width="12%">编码</th>
													<th width="25%">名称</th>
													<th width="10%">顺序</th>
													<th width="20%">备注</th>
													<th width="15%">操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
	</fieldset>
</form>
<script type="text/javascript">
var specDetailColumns = [
		{"orderable":false,"data" : function(data) {return "";}},
		{"name":"code","data" : function(data) {return data.code;}},
		{"name":"name","data" : function(data) {return data.name;}},
		{"name":"orderBy","data" : function(data) {return data.orderBy;}},
		{"name":"memo","data" : function(data) {return data.memo;}},
		{"orderable":false,"data" : function(data) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdateDetail('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteSpecificationDetail('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
   	var specDetailTable = initDataTable("specificationDetail","${nvix}/nvixnt/system/nvixSpecificationAction!goSingleSpecificationDetail.action",specDetailColumns,function(page,pageSize,orderField,orderBy){
   		var name = $("#searchSpecDetailName").val();
   		name=Url.encode(name);
   		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":$("#id").val(),"name":name};
   	},6);
	
	/** 添加联系人的回调方法，当客户信息未保存时，先保客户信息再添加联系人。 */
	function addspecificationDetail(){
		var id = $("#id").val();
		if(id == ''){
			if($('#specificationForm').validationEngine('validate')){
				$("#specificationForm").ajaxSubmit({
					type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(",");
						$("#id").val($.trim(r[0]));
						saveOrUpdateDetail('');
					}
				});
			}
		}else{
			saveOrUpdateDetail('');
		}
	}
	
	/** 保存规格明细 */
	function saveOrUpdateDetail(id){
		var specificationId = $("#id").val();
		//alert(specificationId);
		if(!specificationId){
			if($('#specificationForm').validationEngine('validate')){
				$("#specificationForm").attr('action','${nvix}/nvixnt/system/nvixSpecificationAction!saveOrUpdateInner.action');
				$("#specificationForm").ajaxSubmit({
		    		type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						$("#id").val($.trim(result));
					}
				});
				$("#specificationForm").attr('action','${nvix}/nvixnt/system/nvixSpecificationAction!saveOrUpdate.action');
				//openSpecificationDetailWindow(id);
				openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixSpecificationAction!goSaveOrUpdateSpecificationDetail.action?id='+$("#id").val()+"&detailId="+id,"specificationDetailForm","规格明细管理",480,380,specDetailTable);
			}
		}else{
			//openSpecificationDetailWindow(id);
			openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixSpecificationAction!goSaveOrUpdateSpecificationDetail.action?id='+$("#id").val()+"&detailId="+id,"specificationDetailForm","规格明细管理",480,380,specDetailTable);
		}
	}
	function deleteDetailById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixspecificationAction!deleteDetailById.action?id=' + id,'是否删除该条明细?', specificationDetailTable);
	};
	var showMenu = initDropListTree("treeMenu","${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action","itemCatalogId","itemCatalogName","itemCatalogTree","menuContent");
</script>