<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-user"></i> 供应商管理 <span>> 基础数据管理 </span><span>> 角色管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div>
		</div>
	</div>
	<div class="jarviswidget" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>角色列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input id="searchtaskname" type="text" value="" class="form-control">
						</div>
						<button onclick="wxpRoleTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchtaskname').val('');wxpRoleTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="wxpRoleTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var wxpRoleColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "名称",
	"width" : "40%",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "备注",
	"width" : "40%",
	"data" : function(data) {
		return data.memo;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var authority = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"toAuthority('" + data.id + "');\" title='授权'><span class='txt-color-blue pull-right'><i class='fa fa-shield'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + authority + "&nbsp;&nbsp;" + del;
	}
	} ];

	var wxpRoleTable = initDataTable("wxpRoleTableId", "${nvix}/nvixnt/nvixntRoleAction!goSupplierRoleList.action", wxpRoleColumns, function(page, pageSize, orderField, orderBy) {
		var searchtaskname = $("#searchtaskname").val();
		searchtaskname = Url.encode(searchtaskname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"roleName" : searchtaskname
		};
	});

	$("#wxpRoleForm").validationEngine();
	//新增角色
	function saveOrUpdate(id) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/nvixntRoleAction!goSaveOrUpdateSupplierRole.action?id=' + id, "wxpRoleForm", "新增角色", 750, 350, wxpRoleTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntRoleAction!deleteById.action?id=' + id, '是否删除角色?', wxpRoleTable);
	};
	//角色授权
	function toAuthority(id) {
		var mycars = new Array("关闭")
		$.ajax({
		url : '${nvix}/nvixnt/nvixntRoleAction!goChooseShopAuthority.action',
		data : "id=" + id,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : "分配权限",
			area : [ '575px', '475px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			btn2 : function(index, layero) {
				layer.close(index);
			}
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
		});
	};

	function saveForAuthority() {

		var zTreeTmp = $.fn.zTree.getZTreeObj("tree_root");

		//判断左侧树选中是否有变化
		var changedNodes = zTreeTmp.getChangeCheckedNodes();
		var isChangedChecked = false;
		if (changedNodes.length > 0) {
			isChangedChecked = true;
		}
		//获取左侧树全部选中的节点
		if (!isChangedChecked) {
			asyncbox.alert("授权没有修改!", "提示信息");
			return;
		}

		var nodes = zTreeTmp.getCheckedNodes(true);
		var objIdsArray = new Array();
		for (var i = 0, l = nodes.length; i < l; i++) {
			objIdsArray.push(nodes[i].id);
		}
		//类型
		var bizTypeTmp = $("#authorityBizType").val();

		$.post('${nvix}/nvixnt/nvixntRoleAction!saveForAuthority.action', {
		roleId : '${id}',
		bizType : bizTypeTmp,
		menuIds : objIdsArray.join(),
		checkedMenuId : $("#selectId").val(),
		isChangCheckMenu : isChangedChecked

		}, function(result) {
			asyncbox.success(result, "提示信息", function(action) {
			});
		});
	};
</SCRIPT>