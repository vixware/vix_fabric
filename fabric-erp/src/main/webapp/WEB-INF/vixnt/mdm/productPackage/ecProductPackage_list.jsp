<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cube"></i> 商品管理<span>> 商品包管理 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<div class="col-xs-12 col-sm-6 col-md-6 col-lg-12 text-align-right">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>	
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i> </span>
				<h2>商品包列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								名称:
								<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
							</div>
							
							<button onclick="ecProductPackageTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchName').val('');ecProductPackageTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
				    <table id="ecProductPackage" class="table table-striped table-bordered table-hover" width="100%">
				   		<thead>			                
							<tr>
								<th width="10%">编号</th>
								<th width="25%">编码</th>
								<th width="55%">名称</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
				    </table>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
	var ecProductPackageColumns = [
		{"orderable" : false,"data" : function(data) {return "";}},
		{"name" : "code","data" : function(data) {return data.code;}},
		{"name" : "name","data" : function(data) {return data.name;}},
		{"orderable" : false,"data" : function(data) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	var ecProductPackageTable = initDataTable("ecProductPackage","${nvix}/nvixnt/mdm/nvixEcproductPackageAction!goSingleList.action",ecProductPackageColumns,function(page,pageSize,orderField,orderBy){
		var name = $("#searchName").val();
	 	name=Url.encode(name);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"name":name};
	});
	
	function saveOrUpdate(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/mdm/nvixEcproductPackageAction!goSaveOrUpdate.action?id='+id,"ecProductPackageForm","商品包",720,460,ecProductPackageTable);
	};
	
	function deleteById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/mdm/nvixEcproductPackageAction!deleteById.action?id='+id,'是否删除商品包?',ecProductPackageTable);
	}
	
	/** 页面加载完调用 */
	pageSetUp();
</script>