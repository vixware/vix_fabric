<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-globe"></i> <a>会员营销</a><span>> 优惠券管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpdate('','新增');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i>
			</span>
			<h2>优惠券方案列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div id="">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							方案名称: <input type="text" value="" class="form-control" id="selectName">
						</div>
						<button onclick="invWarehouseTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#selectName').val('');invWarehouseTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="invWarehouseTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var invWarehouseColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "方案名称",
	"data" : function(data) {
		return "<a href='javascript:void(0);' onclick=\"show('" + data.id + "','查看');\" title='查看'>"+data.name+"</a>";
	}
	}, {
	"title" : "面额",
	"data" : function(data) {
		return data.amount;
	}
	}, {
	"title" : "数量",
	"data" : function(data) {
		return data.quantity;
	}
	}, {
	"title" : "发券方式",
	"data" : function(data) {
		if(data.endmode == "1"){
			return "<span class='label label-success'>直接发放</span>";
		}else if(data.endmode == "2"){
			return "<span class='label label-info'>会员领取</span>";
		}else if(data.endmode == "3"){
			return "<span class='label label-warning'>引擎发放</span>";
		}
		return "";
	}
	}, {
	"title" : "创建日期",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "方案状态",
	"data" : function(data) {
		if(data.status == "1"){
			return "<span class='label label-info'>未发放</span>";
		}else if(data.status == "2"){
			return "<span class='label label-success'>已发放</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "','查看');\" title='查看'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		var createCoupon = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"createCoupon('" + data.id + "');\" title='发放'><span class='txt-color-blue pull-right'><i class='fa fa-send'></i></span></a>";
		if(data.status == "2"){
			return show;
		}else{
			return update+"&nbsp;&nbsp;" +createCoupon + "&nbsp;&nbsp;" + del;
		}
	}
	} ];

	var invWarehouseTable = initDataTable("invWarehouseTableId", "${nvix}/nvixnt/nvixCouponManagementAction!goSingleList.action", invWarehouseColumns, function(page, pageSize, orderField, orderBy) {
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
		deleteEntityByConfirm('${nvix}/nvixnt/nvixCouponManagementAction!deleteCouponById.action?id=' + id, '是否删除?', invWarehouseTable);
	};
	function sendCoupon(id) {
		updateEntityByConfirm('${nvix}/nvixnt/vixntDispenseCouponAction!sendCoupon.action?id=' + id, '是否发放?', invWarehouseTable);
	};

	function goSaveOrUpdate(id) {
		/*  $.ajax({
		url : '${nvix}/nvixnt/nvixCouponManagementAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});  */
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixCouponManagementAction!goSaveOrUpdate.action?id=' + id,"couponPlanForm","优惠券方案",800,670,invWarehouseTable);
	};
	function show(id){
		openWindowForShow('${nvix}/nvixnt/nvixCouponManagementAction!show.action?id=' + id,"优惠券方案",800,670);
	}
	//生成优惠券
	function createCoupon(id){
		updateEntityByConfirm('${nvix}/nvixnt/nvixCouponManagementAction!createCoupon.action?id=' + id,"是否发放!",invWarehouseTable)
	}
</script>