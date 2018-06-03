<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-tasks"></i> <a onclick="loadContent('sys_objectExpand','${nvix}/nvixnt/system/nvixObjectExpandAction!goList.action');">系统管理</a> <span>>引用字典管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;<s:text name="add"/>
					</button>
				</a>
			</div>
		</div>
	</div>
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>组织架构</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /> <input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
							async : {
							enable : true,
							url:"${nvix}/nvixnt/system/nvixObjectExpandAction!findOrgAndUnitTreeToJson.action",
							autoParam : [ "id", "treeType" ]
							},
							callback : {
								onClick : onClick
							}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							schedulingTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i></span>
						<h2>扩展对象列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div>
								<form role="search" class="navbar-form navbar-left">
									名称: 
									<div class="form-group">
										<input type="text" value="" class="form-control" id="searchObjectExpandName">
									</div>
									<button onclick="objectExpandTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchObjectExpandName').val('');objectExpandTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="objectExpandTable" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var objectExpandColumns = [ 
		{"orderable":false,"title" : "编号","width" : "10%","data" : function(data) {return "&nbsp;";}},
		{"title" : "名称","name":"name","width" : "20%","data" : function(data) {return data.name;}},
		{"title" : "扩展表名称","name":"expandTableName","width" : "20%","data" : function(data) {return data.expandTableName;}},
		{"title" : "是否已使用","name":"isReference","width" : "20%","data" : function(data) {
			if(data.isReference != null && data.isReference != ""){
				return data.isReference == '1' ? '已用' : '未用';
			}
			return '';
		}},
		{"title" : "分类状态","name":"status","width" : "20%","data" : function(data) {
			if(data.status != null && data.status != ""){
				return data.status == '1' ? '激活' : '禁用';
			}
			return '';
		}},
		{"orderable":false,"title" : "操作","width" : "10%","data" : function(data) {
			if(data.id != null){
				var createTable = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"createTable('" + data.id + "');\" title='建表'><span class='txt-color-blue pull-right'><i class='fa fa-database'></i></span></a>";
				var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
				var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
				return createTable + "&nbsp;&nbsp;" + update + "&nbsp;&nbsp;" + del;
			}
			return '';
		}}
	];

	var objectExpandTable = initDataTable("objectExpandTable","${nvix}/nvixnt/system/nvixObjectExpandAction!getObjectExpandJson.action",objectExpandColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchObjectExpandName").val();
		return {"page" : page,"pageSize" : pageSize,"orderField" : orderField,"orderBy" : orderBy,"searchName" : searchName};
	});
	
	function saveOrUpdate(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixObjectExpandAction!goSaveOrUpdate.action?id=' + id,'objectExpandForm',id == '' ? '新增扩展类型':'修改扩展类型',950,600,objectExpandTable,null,function(){
			objectExpandTable.ajax.reload();
		});
	}
	
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixObjectExpandAction!deleteById.action?id=' + id,'是否删除该扩展对象?', objectExpandTable);
	};
	
	//生成扩展字段
	function createTable(id){
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixObjectExpandAction!createOrUpdateTable.action?id='+id,'确定要生成数据表吗?', objectExpandTable);
	}
	
</script>