<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-fw fa-cube"></i> 商品管理 <span>&gt; 商品拆装</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>商品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品名称: <input type="text" value="" class="form-control" id="mytitle" style="width: 250px;">
						</div>
						<button onclick="stockRecordsTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');stockRecordsTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="stockRecordsTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th>编号</th>
							<th>商品编码</th>
							<th>商品名称</th>
							<th>价格</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var stockRecordsColumns = [ {
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"width" : "26%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"width" : "26%",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/vixntProductDisassemblyAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title
		};
	});
	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntProductDisassemblyAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntProductDisassemblyAction!deleteById.action?id=' + id, '是否删除商品?', stockRecordsTable);
	};
</script>