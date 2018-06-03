<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-institution"></i> 连锁管理<span>> 独立店铺管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增门店
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>门店列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="selectName">
						</div>
						<button onclick="channelDistributorTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#selectName').val('');channelDistributorTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="channelDistributorTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var channelDistributorColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"name" : "name",
	"width" : "23%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "类型",
	"name" : "type",
	"width" : "10%",
	"data" : function(data) {
		if (data.type == 1) {
			return "渠道";
		} else if (data.type == 2) {
			return "经销商";
		} else if (data.type == 3) {
			return "代理商";
		} else if (data.type == 4) {
			return "门店";
		}
		return "";
	}
	}, {
	"title" : "电话",
	"name" : "telephone",
	"width" : "10%",
	"data" : function(data) {
		return data.telephone;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.status == 0) {
			return "<span class='label label-primary'>启用</span>";
		} else if (data.status == 1) {
			return "<span class='label label-info'>禁用</span>";
		}
		return "";
	}
	}, {
	"title" : "创建时间",
	"name" : "createTimeTimeStr",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"width" : "12%",
	"orderable" : false,
	"data" : function(data) {

		var start = "<div class=\"btn-group\"><button class=\"btn btn-success btn-xs dropdown-toggle\" data-toggle=\"dropdown\"><i class=\"fa fa-gear\"></i> 操作  <span class=\"caret\"></span></button><ul class=\"dropdown-menu pull-right\">";
		var update = "<li><a href='javascript:void(0);' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'> <span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span>修改</a></li>";
		var show = "<li><a href='javascript:void(0);' onclick=\"goShow('" + data.id + "');\" title='查看'> <span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span>查看</a></li>";
		var bindweixinsite = "<li><a href='javascript:void(0);' onclick=\"goWeixinSite('" + data.id + "','微信公众号');\" title='微信公众号'> <span class='txt-color-blue pull-right'><i class='fa fa-weixin'></i></span>微信公众号</a></li>";
		var message = "<li><a href='javascript:void(0);' onclick=\"goMessageSet('" + data.id + "','短信设置');\" title='短信设置'> <span class='txt-color-blue pull-right'><i class='fa fa-envelope-o'></i></span>短信设置</a></li>";
		var employee = "<li><a href='javascript:void(0);' onclick=\"goSaveOrUpdateStorePerson('" + data.id + "','创建管理员');\" title='创建管理员'> <span class='txt-color-blue pull-right'><i class='fa fa-user'></i></span>创建管理员</a></li>";
		var del = "<li><a href='javascript:void(0);' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span>删除</a></li>";
		var end = "</ul></div>";

		return start + update + show + bindweixinsite + message + employee + del + end;

	}
	} ];

	var channelDistributorTable = initDataTable("channelDistributorTableId", "${nvix}/nvixnt/vixntAloneShopAction!goSingleList.action", channelDistributorColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectName" : selectName
		};
	}, 10, '0');

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntAloneShopAction!deleteById.action?id=' + id, '是否删除?', channelDistributorTable);
	};

	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAloneShopAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	function goWeixinSite(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAloneShopAction!goWeixinSite.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	function goShow(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAloneShopAction!goShow.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	function goMessageSet(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAloneShopAction!goMessageSet.action?parentId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};

	function goSaveOrUpdateStorePerson(id) {
		$.ajax({
		url : '${nvix}/nvixnt/vixntAloneShopAction!goSaveOrUpdateStorePerson.action?parentId=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
</script>