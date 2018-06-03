<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 系统管理 <span>> 用户反馈</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div id="addressInfoDiv" class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i> </span>
			<h2>反馈信息列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							内容:
							<input type="text" value="" placeholder="内容" class="form-control" id="searchName">
						</div>
						
						<button onclick="addressInfoTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');addressInfoTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
			    <table id="addressInfo" class="table table-striped table-bordered table-hover" width="100%">
			   		<thead>			                
						<tr>
							<th width="8%">编号</th>
							<th width="40%">反馈内容</th>
							<th width="10%">联系人</th>
							<th width="15%">电话</th>
							<th width="15%">邮箱</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
			    </table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var addressInfoColumns = [
		{"orderable" : false,"data" : function(data) {return "";}},
		{"data" : function(data) {return data.center;}},
		{"data" : function(data) {return data.person;}},
		{"data" : function(data) {return data.mobilePhone;}},
		{"orderable" : false,"data" : function(data) {return data.email;}},
		{"orderable" : false,"data" : function(data) {
			var update = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}}
	];
	var addressInfoTable = initDataTable("addressInfo","${nvix}/nvixnt/system/nvixntUserFeedbackAction!goSingleList.action",addressInfoColumns,function(page,pageSize,orderField,orderBy){
		var name = $("#searchName").val();
	 	name=Url.encode(name);
	 	var parentId = $("#selectId").val();
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"name":name,"parentId":parentId};
	});
	
	function saveOrUpdate(id){
		openSaveOrUpdateWindow('${nvix}/nvixnt/system/nvixntUserFeedbackAction!goSaveOrUpdate.action?id='+id,"userFeedbackForm","反馈信息",720,425,addressInfoTable);
	};
	
	function deleteById(id){
		deleteEntityByConfirm('${nvix}/nvixnt/system/nvixntUserFeedbackAction!deleteById.action?id='+id,'是否删除反馈信息?',addressInfoTable);
	}
	/** 页面加载完调用 */
	pageSetUp();
</script>