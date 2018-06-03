<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="ecProductPackageForm" class="form-horizontal" style="padding:40px;" action="${nvix}/nvixnt/mdm/nvixEcproductPackageAction!saveOrUpdate.action">
	<input type="hidden" id="id" name = "itemPackage.id" value="${itemPackage.id}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="itemPackage.code" value="${itemPackage.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="itemPackage.name" value="${itemPackage.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
		</div>
		<div class="jarviswidget jarviswidget-color-white">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>商品列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin:5px;">
						<div class="form-group" style="margin: 0 5px;">
							<div class="col-md-3">
								<input type="text" value="" placeholder="名称" class="form-control" id="searchEcProductName">
							</div>
							<button onclick="ddTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchEcProductName').val('');ddTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
							<div class="listMenu-float-right">
								<button onclick="chooseListData('${nvix}/nvixnt/mdm/nvixEcproductPackageAction!goChooseEcProduct.action?status=1','multi','选择商品',null,addEcProductCallBack,860,560);" type="button" class="btn btn-primary">
									<i class="glyphicon glyphicon-plus"></i><s:text name="add"/>
								</button>
							</div>
						</div>
					</div>
				    <table id="ecProductPackageDetail" class="table table-striped table-bordered table-hover" width="100%">
				   		<thead>			                
							<tr>
								<th width="10%">编号</th>
								<th width="65%">名称</th>
								<th width="15%">价格</th>
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
	/** 添加选中的商品 */
	function addEcProduct(){
		var ecProductIds = chooseMap.valueIdSet();
		if(ecProductIds != ""){
			$.ajax({
				url:'${nvix}/nvixnt/mdm/nvixEcproductPackageAction!addEcProductToPackage.action?ecProductIds='+ecProductIds+"&id="+$("#id").val(),
				cache: false,
				success: function(html){
					showMessage(html);
					ddTable.ajax.reload();
				}
			});
		}else{
			layer.alert("请选择商品!");
		}
	}
	
	/** 选择商品的回调方法，当分类未保存时，先保存分类再添加商品。 */
	function addEcProductCallBack(){
		var id = $("#id").val();
		if(id == ''){
			if($('#ecProductPackageForm').validationEngine('validate')){
				$("#ecProductPackageForm").attr('action','${nvix}/nvixnt/mdm/nvixEcproductPackageAction!saveOrUpdateInner.action');
				
				$("#ecProductPackageForm").ajaxSubmit({
		    		type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(result) {
						var r = result.split(":");
						$("#id").val($.trim(r[0]));
						$("#ecProductPackageForm").attr('action','${nvix}/nvixnt/mdm/nvixEcproductPackageAction!saveOrUpdate.action');
						addEcProduct();
					}
				});
			}
		}else{
			addEcProduct();
		}
	}
	
	/** 删除商品 */
	function deleteEcProductPackageDetail(id){
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixEcproductPackageAction!removeEcProductFromPackage.action?id='+id,'是否删除商品包下的该商品?',ddTable);
	}

	var ecProductPackageDetailColumns = [
		{"orderable" : false,"data" : function(data) {return "";}},
		{"name" : "name","data" : function(data) {return data.name;}},
		{"name" : "price","data" : function(data) {return data.price;}},
		{"orderable" : false, "data" : function(data) {
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteEcProductPackageDetail('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return del;
		}}
	];
	       
   	var ddTable = initDataTable("ecProductPackageDetail","${nvix}/nvixnt/mdm/nvixEcproductPackageAction!goEcProductSingleListJson.action",ecProductPackageDetailColumns,function(page,pageSize,orderField,orderBy){
   		var name = $("#searchEcProductName").val();
	 	name=Url.encode(name);
	 	var id = $("#id").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"id":id,"name":name};
   	});
	
	$("#ecProductPackageForm").validationEngine();
</script>