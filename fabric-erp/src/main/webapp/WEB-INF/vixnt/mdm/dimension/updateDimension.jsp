<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="dimensionForm" class="form-horizontal" style="padding:40px;" action="${nvix}/nvixnt/mdm/nvixDimensionAction!saveOrUpdate.action">
	<input type="hidden" id="id" name = "dimension.id" value="${dimension.id}"/>
	<input type="hidden" id="itemCatalogId" name="dimension.itemCatalog.id" value="${dimension.itemCatalog.id}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">商品分类:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div id="treeMenu" class="input-group">
							<input id="itemCatalogName" type="text" onfocus="showMenu(); return false;" value="${dimension.itemCatalog.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]"/>
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
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="dimension.name" value="${dimension.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>顺序:</label>
			<div class="col-md-4">
				<input id="orderBy" name="dimension.orderBy" value="${dimension.orderBy}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
			</div>
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-4">
				<input id="memo" name="dimension.memo" value="${dimension.memo}" class="form-control" type="text"/>
			</div>
		</div>
		<div class="jarviswidget jarviswidget-color-white">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>维度明细列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin:5px;">
						<div class="form-group" style="margin: 0 5px;">
							<div class="col-md-3">
								<input type="text" value="" placeholder="名称" class="form-control" id="searchDimensionDetailName">
							</div>
							<button onclick="ddTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchDimensionDetailName').val('');ddTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
							<div class="listMenu-float-right">
								<button onclick="saveOrUpdateDetail('');" type="button" class="btn btn-primary">
									<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
								</button>
							</div>
						</div>
					</div>
				    <table id="dimensionDetail" class="table table-striped table-bordered table-hover" width="100%">
				   		<thead>			                
							<tr>
								<th width="10%">编号</th>
								<th width="25%">名称</th>
								<th width="40%">顺序</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
				    </table>
			 	</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	var dimensionDetailColumns = [
		{"orderable" : false,"data" : function(data) {return "";}},
		{"name" : "name","data" : function(data) {return data.name;}},
		{"name" : "orderBy","data" : function(data) {return data.orderBy;}},
		{"orderable" : false,"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"openDimensionDetailWindow('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteDimensionDetail('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	       
   	var ddTable = initDataTable("dimensionDetail","${nvix}/nvixnt/mdm/nvixDimensionAction!goSingleListJson.action",dimensionDetailColumns,function(page,pageSize,orderField,orderBy){
   		var name = $("#searchDimensionDetailName").val();
	 	name=Url.encode(name);
	 	var id = $("#id").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"dimensionId":id,"name":name};
   	},5);
   	
   	/** 弹出添加或修改维度明细的窗口 */
	function openDimensionDetailWindow(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixDimensionAction!goSaveOrUpdateDimensionDetail.action?id='+id,"dimensionDetailForm","维度明细管理",480,300,ddTable,function(){
			$("#dimensionId").val($("#id").val());
		});
	}
	
	/** 保存维度明细 */
	function saveOrUpdateDetail(id){
		var dimensionId = $("#id").val();
		if(dimensionId == '' || dimensionId == '0'){
			if($('#dimensionForm').validationEngine('validate')){
				$("#dimensionForm").attr('action','${nvix}/nvixnt/mdm/nvixDimensionAction!saveOrUpdateInner.action');
				$("#dimensionForm").ajaxSubmit({
		    		type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						var backId = $.trim(r[0]);
						if(backId == "0"){
							layer.alert(r[1]);
						}else{
							$("#id").val(backId);
							$("#dimensionForm").attr('action','${nvix}/nvixnt/mdm/nvixDimensionAction!saveOrUpdate.action');
							openDimensionDetailWindow(id);
						}
					}
				});
			}
		}else{
			openDimensionDetailWindow(id);
		}
	}

	/** 删除维度明细 */
	function deleteDimensionDetail(id){
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixDimensionAction!deleteDimensionDetailById.action?id='+id,'是否删除分类下的该维度明细?',ddTable);
	}
	
	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu","${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action","itemCatalogId","itemCatalogName","itemCatalogTree","menuContent");
	
	$("#dimensionForm").validationEngine();
</script>