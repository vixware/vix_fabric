<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw"></i>科目账<span>> 余额表</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>余额表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<form role="search" class="navbar-form navbar-left">
					<div class="form-group">
						科目名称: <input type="text" value="" class="form-control" id="name" style="width: 250px;">
					</div>
					<button onclick="vehicleTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button onclick="$('#name').val('');vehicleTable.ajax.reload();" type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</form>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr style="background:#efefef;">
							<th rowspan="2" style="text-align:center;width:200px;height:10px; line-height:40px;">科目编码</th>
							<th rowspan="2" style="text-align:center;width:200px;height:10px; line-height:40px;">科目名称</th>
							<th colspan="2" style="text-align:center;">期初 余额</th>
							<th colspan="2" style="text-align:center;">本期发生</th>
							<th colspan="2" style="text-align:center;">期末余额</th>
						</tr>
						<tr style="background:#efefef;">
							<th style="text-align:center;">借方</th>
							<th style="text-align:center;">贷方</th>
							<th style="text-align:center;">借方</th>
							<th style="text-align:center;">贷方</th>
							<th style="text-align:center;">借方</th>
							<th style="text-align:center;">贷方</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1131</td>
							<td>现金</td>
							<td>8000</td>
							<td>6000</td>
							<td>5000</td>
							<td>4000</td>
							<td>8000</td>
							<td>8000</td>
						</tr>
						<tr>
							<td>1131</td>
							<td>现金</td>
							<td>8000</td>
							<td>6000</td>
							<td>5000</td>
							<td>4000</td>
							<td>8000</td>
							<td>8000</td>
						</tr>
						<tr>
							<td>1131</td>
							<td>现金</td>
							<td>8000</td>
							<td>6000</td>
							<td>5000</td>
							<td>4000</td>
							<td>8000</td>
							<td>8000</td>
						</tr>
						<tr>
							<td>1131</td>
							<td>现金</td>
							<td>8000</td>
							<td>6000</td>
							<td>5000</td>
							<td>4000</td>
							<td>8000</td>
							<td>8000</td>
						</tr>
						<tr>
							<td>1131</td>
							<td>现金</td>
							<td>8000</td>
							<td>6000</td>
							<td>5000</td>
							<td>4000</td>
							<td>8000</td>
							<td>8000</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var vehicleTable = initDataTable("employeeTableId", "${nvix}/nvixnt/hr/nvixNameBookAction!goSingleList.action", vehicleColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#name").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name
		};
	}, 10);
    
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/hr/nvixNameBookAction!deleteById.action?id=' + id, '是否删除该员工信息?', vehicleTable);
	};
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/hr/nvixNameBookAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	
</script>