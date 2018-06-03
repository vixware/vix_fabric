<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="main" role="main">
	<div id="ribbon">
		<span class="ribbon-button-alignment"> <span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh" rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true"> <i class="fa fa-refresh"></i>
		</span>
		</span>
		<ol class="breadcrumb">
			<li>首页</li>
			<li>返回</li>
		</ol>
	</div>
	<div id="content">
		<div class="row">
			<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
				<h1 class="page-title txt-color-blueDark">
					<i class="fa fa-table fa-fw "></i> 工作台 <span>> 设备资源管理 > 台账 </span>
				</h1>
			</div>
			<div class="col-lg-8 text-align-right">
				<div class="page-title">
					<a data-toggle="modal" href="#testid">
						<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
							<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
						</button>
					</a>
				</div>
			</div>
		</div>
		<section id="" class="">
			<div class="row">
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="jarviswidget jarviswidget-color-darken" id="ledger" data-widget-editbutton="false">
						<header>
							<span class="widget-icon"> <i class="fa fa-table"></i></span>
							<h2>设备列表</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div id="ledgerSearchForm">
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											设备名称:<input type="text" value="" placeholder="设备名称" class="form-control" id="searchNameA"> 品牌名称:<input type="text" value="" placeholder="品牌名称" class="form-control" id="searchNameB">
										</div>
										<button onclick="contactPersonTable.ajax.reload();" type="button" class="btn btn-info">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchNameA').val('');$('#searchNameB').val('');contactPersonTable.ajax.reload();" type="button" class="btn btn-default">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
								<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
									<thead>
										<tr>
											<th width="5%">编号</th>
											<th width="10%">设备名称</th>
											<th width="10%">设备编码</th>
											<th width="10%">设备类别</th>
											<th width="10%">租赁价格</th>
											<th width="10%">性能状态</th>
											<th width="10%">租赁状态</th>
											<th width="10%">预约状态</th>
											<th width="10%">预约日期</th>
											<th width="10%">操作</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>1</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>2</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>3</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>4</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>5</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>6</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>7</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>8</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>9</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>10</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>11</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
										<tr>
											<td>12</td>
											<td>佳能60D</td>
											<td>P_60D_001</td>
											<td>摄影器材</td>
											<td>300/天</td>
											<td>良好</td>
											<td>可租赁</td>
											<td>未预约</td>
											<td>00/00/00</td>
											<td><a class='btn btn-xs btn-default' onclick=\ "saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a> <a class='btn btn-xs btn-default' onclick=\ "deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</article>
			</div>
		</section>
	</div>
</div>
<!-- <script type="text/javascript">

var contactPersonColumns = [
	{"data" : function(data) {return "";}},
	{"data" : function(data) {return data.customerAccount.name;}},
	{"data" : function(data) {return data.lastName + data.firstName;}},
	{"data" : function(data) {return data.presideBusiness;}},
	{"data" : function(data) {return data.email;}},
	{"data" : function(data) {return data.fax;}},
	{"data" : function(data) {return data.mobile;}},
	{"data" : function(data) {return data.mobile;}},
	{"data" : function(data) {return data.mobile;}},
	{"data" : function(data) {
		var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('"+data.id+"');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}}
];

/* var contactPersonTable = initDataTable("contactPerson","${nvix}/nvixnt/nvixContactPersonAction!goListContent.action",contactPersonColumns,function(page,pageSize,orderField,orderBy){
	var nameA = $("#searchNameA").val();
	var nameB = $("#searchNameB").val();
 	nameA=Url.encode(nameA);
 	nameB=Url.encode(nameB);
	return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy};
}); */
   	
//更新
function saveOrUpdate(id){
	var pageNo = $("#contactPersonPageNoHidden").val();
	$.ajax({
		url : '${nvix}/nvixnt/nvixContactPersonAction!goSaveOrUpdate.action?id=' + id + "&categoryId=" + $("#selectId").val() + "&pageNo=" + pageNo,
		cache: false,
		success: function(html){
			alert(111);
		}
	});
}
	
pageSetUp();
</SCRIPT> -->
<script type="text/javascript">
$(document).ready(function() {
	pageSetUp();
	/* BASIC ;*/
		var responsiveHelper_dt_basic = undefined;
		var breakpointDefinition = {
			tablet : 1024,
			phone : 480
		};
		$('#dt_basic').dataTable({
			"sDom": "t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
			"autoWidth" : true,
			"preDrawCallback" : function() {
				if (!responsiveHelper_dt_basic) {
					responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
				}
			},
			"rowCallback" : function(nRow) {
				responsiveHelper_dt_basic.createExpandIcon(nRow);
			},
			"drawCallback" : function(oSettings) {
				responsiveHelper_dt_basic.respond();
			}
		});
	/* END BASIC */
})

//更新

function saveOrUpdate(id) {
	openSaveOrUpdateWindow('${nvix}/nvixnt/nvixAssetManagementAction!goSaveOrUpdate.action?id=' + id, "dimensionForm", "新增设备", 750, 350, null);
};
</script>