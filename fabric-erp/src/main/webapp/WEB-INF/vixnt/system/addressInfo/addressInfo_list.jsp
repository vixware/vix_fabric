<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 基础设置 <span>> 地址管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
				<button class="btn btn-primary" type="button" onclick="$('#fileToUpload').click();">
					<i class="glyphicon glyphicon-import"></i>&nbsp;导入
				</button>
			</div>
		</div>
	</div>
	<section id="widget-grid">
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div id="addressInfoDiv" class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i> </span>
						<h2>地址信息列表</h2>
					</header>
					<div style="overflow:hidden;position:relative;padding:0;">
						<article style="width:230px;float:left;position:absolute;height:515px;overflow-x:hidden;overflow-y:auto;height:522px;">
							<ul id="tree" class="ztree"></ul>
							<input type="hidden" id="selectId" name="selectId" value=""/>
							<input type="hidden" id="selectIdTreeId" name="selectIdTreeId" value=""/>
							<script type="text/javascript">
								<!--
								var zTree;			
								var setting = {
									async: {
										enable: true,
										url:"${nvix}/nvixnt/system/nvixntAddressInfoAction!findTreeToJson.action",
										autoParam:["id", "name=n", "level=lv"]
									},
									callback: {
										onClick: onClick
									}
								};
								function onClick(event, treeId, treeNode, clickFlag) {
									$("#selectId").val(treeNode.id);
									$("#selectIdTreeId").val(treeNode.tId);
									addressInfoTable.ajax.reload();
								}
								zTree = $.fn.zTree.init($("#tree"), setting);
								//-->
							</script>
						</article>
						<div class="widget-body no-padding" style="margin:0 0 0 240px;border-left:1px solid #ccc;height:522px;">
							<div>
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										名称:
										<input type="text" value="" placeholder="名称" class="form-control" id="searchName">
									</div>
									
									<button onclick="addressInfoTable.ajax.reload();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchName').val('');addressInfoTable.ajax.reload();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
						    <table id="addressInfo" class="table table-striped table-bordered table-hover" width="100%">
						   		<thead>			                
									<tr>
										<th width="8%">编号</th>
										<th width="25%">名称</th>
										<th width="10%">首字母</th>
										<th width="10%">顺序</th>
										<th width="35%">备注</th>
										<th width="10%">操作</th>
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
	var addressInfoColumns = [
		{"orderable" : false,"data" : function(data) {return "";}},
		{"name" : "name","data" : function(data) {return data.name;}},
		{"name" : "firstLetter","data" : function(data) {return data.firstLetter;}},
		{"name" : "orderBy","data" : function(data) {return data.orderBy;}},
		{"orderable" : false,"data" : function(data) {return data.memo;}},
		{"orderable" : false,"data" : function(data) {
			var update = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	var addressInfoTable = initDataTable("addressInfo","${nvix}/nvixnt/system/nvixntAddressInfoAction!goSingleListJson.action",addressInfoColumns,function(page,pageSize,orderField,orderBy){
		var name = $("#searchName").val();
	 	name=Url.encode(name);
	 	var parentId = $("#selectId").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"name":name,"parentId":parentId};
	});
	
	function saveOrUpdate(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntAddressInfoAction!goSaveOrUpdate.action?id='+id+'&parentId='+$("#selectId").val(),"addressInfoForm","地址信息",720,340,addressInfoTable);
	};
	
	function deleteById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntAddressInfoAction!deleteById.action?id='+id,'是否删除地址信息?',addressInfoTable);
	}
	
	function importXlsFile() {
		$.ajaxFileUpload({
			url : '${nvix}/nvixnt/system/nvixntAddressInfoAction!importAddressInfo.action',// 用于文件上传的服务器端请求地址
			secureuri : true,// 是否安全提交,设置为true;设置为false，则出现乱码
			fileElementId : 'fileToUpload',
			dataType : 'text',// 返回值类型 ,可以使xml、text、json、html
			success : function(data, status) {// 服务器成功响应处理函数
				addressInfoTable.ajax.reload();
				showMessage("导入成功!", 'success');
			},
			error : function(data, status, e) {// 服务器响应失败处理函数
				showMessage("上传错误!", 'error');
			}
		});
	};

	/** 页面加载完调用 */
	pageSetUp();
</script>