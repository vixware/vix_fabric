<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="form-group" id="itemDiv">
<input type="hidden" id="clipId" name="customerAccountClip.id" value="${customerAccountClip.id}" />
	<div class="col-md-10">
		<div id="contactPersonDiv" class="jarviswidget jarviswidget-color-white">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>服务项目</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-3">
								<input type="text" value="" placeholder="服务项目" class="form-control" id="searchItem">
							</div>
							<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchItem').val('');itemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
							<div class="listMenu-float-right">
								<button onclick="addItems();" type="button" class="btn btn-primary">
									<i class="glyphicon glyphicon-plus"></i>
									<s:text name="add" />
								</button>
							</div>
						</div>
					</div>
					<table id="itemTableId" class="table table-striped table-bordered table-hover" width="100%">
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
var itemTableColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "编码",
	"width" : "10%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "服务内容",
	"width" : "15%",
	"data" : function(data) {
		return data.itemServiceContent;
	}
	}, {
	"title" : "服务次数",
	"width" : "15%",
	"data" : function(data) {
		return data.number;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"addItems('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var itemTable = initDataTable("itemTableId", "${nvix}/nvixnt/nvixCustomerAccountAction!getItemSingleList.action", itemTableColumns, function(page, pageSize, orderField, orderBy) {
		var searchItem = $("#searchItem").val();
		searchItem = Url.encode(searchItem);
		var clipId = $("#clipId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"clipId" : clipId,
		"searchItem" : searchItem
		};
	});
</script>