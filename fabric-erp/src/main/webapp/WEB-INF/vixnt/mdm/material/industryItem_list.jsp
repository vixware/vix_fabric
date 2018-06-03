<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<input type="hidden"  id="itemIds" name="itemIds" />
	<input type="hidden"  id="itemNames" name="itemNames" />
	<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 物料管理 <span>>物料列表 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<%-- <button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;
					<s:text name="add" />
				</button> --%>
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp; 新增物料
					</button>
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-gear"></i>&nbsp;其他&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="uploadItem()">同步物料</a></li>
						<li><a href="javascript:void(0);" onclick="$('#fileToUpload').click()">导入物料</a></li>
						<li><a href="javascript:void(0);" onclick="exportExcel()">导出物料</a></li>
						<li><a href="javascript:void(0);" onclick="downloadTemplate()">模板下载</a></li>
					</ul>
				</div>
				<!-- <button class="btn btn-primary" type="button" onclick="$('#fileToUpload').click()">
						<i class="glyphicon glyphicon-import"></i>&nbsp;导入商品
					</button>
					<button class="btn btn-primary" type="button" onclick="exportExcel()">
						<i class="glyphicon glyphicon-export"></i>&nbsp;导出商品
					</button>
					<button class="btn btn-primary" type="button" onclick="downloadTemplate()">
						<i class="glyphicon glyphicon-save"></i>&nbsp;模板下载
					</button> -->
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>物料分类</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							itemsTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>物料列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									编码:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="iCode">
									</div>
									名称:
									<div class="form-group">
										<input type="text" value="" class="form-control" id="iName">
									</div>
									<button onclick="itemsTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#iCode').val('');$('#iName').val('');itemsTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="itemTable" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="10%">
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default">
													选择 <i class="fa fa-caret-down"></i>
												</button>
												<ul class="dropdown-menu js-status-update pull-left">
													<li><a id="all" onclick="changeDataTableSelect('itemTable','all');" href="javascript:void(0);">全选</a></li>
													<li><a id="reverse" onclick="changeDataTableSelect('itemTable','reverse');" href="javascript:void(0);">反选</a></li>
													<li><a id="cancle" onclick="changeDataTableSelect('itemTable','cancle');" href="javascript:void(0);">取消</a></li>
												</ul>
											</div>
										</th>
										<th>编码</th>
										<th>名称</th>
										<th>规格型号</th>
										<th>价格</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	chooseMap.setColumnName("name");
	var itemsColumns = [ {
	"orderable" : false,
	/* "title" : "编号",
	"width" : "5%", */
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	/* "title" : "编码",
	"width" : "15%", */
	"data" : function(data) {
		return data.code;
	}
	}, {
	/* "title" : "名称",
	"width" : "20%", */
	"data" : function(data) {
		return data.name;
	}
	}, {
	/* "title" : "服务内容",
	"width" : "30%", */
	"data" : function(data) {
		return data.specification;
	}
	}, {
	/* "title" : "价格",
	"width" : "10%", */
	"data" : function(data) {
		return data.price == null ? '' : data.price;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.status == 'T') {
			return "<span class='label label-info'>启用</span>";
		} else if (data.status == 'F') {
			return "<span class='label label-primary'>禁用</span>";
		}
		return "";
	}
	}, {
	"orderable" : false,
	"title" : "操作",
	"width" : "15%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var uploadItem = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"uploadItemById('" + data.id + "');\" title='同步'><span class='txt-color-blue pull-right'><i class='fa fa-cloud'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;"+ uploadItem +"&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var itemsTable = initDataTable("itemTable", "${nvix}/nvixnt/mdm/nvixntMaterialAction!getItemJson.action", itemsColumns, function(page, pageSize, orderField, orderBy) {
		var itemCode = $("#iCode").val();
		itemCode = Url.encode(itemCode);
		var itemName = $("#iName").val();
		itemName = Url.encode(itemName);
		var treeId = $("#selectId").val();
		treeId = Url.encode(treeId);
		var treeType = $("#selectTreeType").val();
		treeType = Url.encode(treeType);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemName" : itemName,
		"itemCode" : itemCode,
		"treeId" : treeId,
		"treeType" : treeType
		};
	},10,"2","1");

	function editSchedul(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixntMaterialAction!goSaveOrUpdate.action?id=' + id, 'itemsForm', '新增', 720, 420, itemsTable, null, function() {
			itemsTable.ajax.reload();
		});
	}

	function saveOrUpdate(id) {
		var categoryId = $("#selectId").val();
		if(id){
			goSaveOrUpdateEntity('${nvix}/nvixnt/mdm/nvixntMaterialAction!goSaveOrUpdate.action?id=' + id + '&categoryId=' + categoryId);
		}else{
			if(categoryId){
				goSaveOrUpdateEntity('${nvix}/nvixnt/mdm/nvixntMaterialAction!goSaveOrUpdate.action?id=' + id + '&categoryId=' + categoryId);
			}else{
				layer.alert("请选择分类!");
				return;
			}
		}
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixntMaterialAction!deleteById.action?id=' + id, '是否删除该商品?', itemsTable);
	};
	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${nvix}/nvixnt/mdm/nvixntMaterialAction!importFile.action',// 用于文件上传的服务器端请求地址
		secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',
		dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
		success : function(data, status) {// 服务器成功响应处理函数
			itemsTable.ajax.reload();
			showMessage("导入成功!", 'success');
		},
		error : function(data, status, e) {// 服务器响应失败处理函数
			showMessage("上传错误!", 'error');
		}
		});
	};
	function downloadTemplate() {
		var url = "${nvix}/nvixnt/mdm/nvixntMaterialAction!downloadTemplate.action";
		window.open(url);
	};
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${nvix}/nvixnt/mdm/nvixntMaterialAction!exportItemExcel.action";
		form.submit();
	};
	function uploadItem(){
		var items = chooseMap.valueSetWithClear().split(":");
		if (items != '' && items.length == 2) {
			$('#itemIds').val(items[0]);
			$('#itemNames').val(items[1]);
			$.ajax({
				url : '${nvix}/nvixnt/mdm/nvixntMaterialAction!uploadItem.action?itemIds=' + items[0],
				cache : false,
				success : function(result) {
					var r = result.split(':');
					if(r[0] == "0"){
						showMessage(r[1]);
						//loadContent('mdm_item', '${nvix}/nvixnt/mdm/nvixntMaterialAction!goList.action');
					}else{
						layer.alert(r[1]);
					}
				}
			});
		} else {
			layer.alert("请选择商品!");
		}
	}
	function uploadItemById(id){
		if(id){
			$.ajax({
				url : '${nvix}/nvixnt/mdm/nvixntMaterialAction!uploadItem.action?id=' + id,
				cache : false,
				success : function(result) {
					var r = result.split(':');
					if(r[0] == "0"){
						showMessage(r[1]);
						//loadContent('mdm_item', '${nvix}/nvixnt/mdm/nvixntMaterialAction!goList.action');
					}else{
						layer.alert(r[1]);
					}
				}
			});
		}else{
			layer.alert("请选择物料!");
		}
	}
</script>