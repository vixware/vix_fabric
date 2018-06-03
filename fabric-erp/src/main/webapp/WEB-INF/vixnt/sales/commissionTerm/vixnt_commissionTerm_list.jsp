<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-pencil-square-o fa-fw "></i> 销售管理 <span>> 佣金条件 </span>
			</h1>
		</div>
		<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
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
				<div class="jarviswidget" id="commissionTermHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>佣金条件列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="commissionTermSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										商品:<input type="text" value=""  class="form-control" id="searchTitle"> 客户:<input type="text" value="" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="commissionTermTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchTitle').val('');$('#searchCustomerName').val('');commissionTermTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="commissionTerm" class="table table-striped table-bordered table-hover" width="100%">
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var commissionTermColumns = [ {
	"orderable" : false,
	"title":"编号",
	"width":"5%",
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title":"编码",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title":"名称",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title":"类型",
	"data" : function(data) {
		return data.type;
	}
	}, {
	"title":"数量单位",
	"data" : function(data) {
		return data.amountUnit;
	}
	}, {
	"title":"金额单位",
	"data" : function(data) {
		return data.sumUnit;
	}
	}, {
	"title":"从",
	"data" : function(data) {
		return data.from;
	}
	}, {
	"title":"到",
	"data" : function(data) {
		return data.to;
	}
	}, {
	"title":"返款比率",
	"data" : function(data) {
		return data.returnTerm;
	}
	}, {
	"title":"返款金额",
	"data" : function(data) {
		return data.returnAmount;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update +"&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var commissionTermTable = initDataTable("commissionTerm", "${nvix}/nvixnt/nvixntCommissionTermAction!goSingleList.action", commissionTermColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#searchCustomerName").val();
		var title = $("#searchTitle").val();
		name = Url.encode(name);
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"title" : title,
		"name" : name
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixntCommissionTermAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntCommissionTermAction!deleteById.action?id=' + id, '是否删除佣金条件?', commissionTermTable);
	}
	pageSetUp();
</script>