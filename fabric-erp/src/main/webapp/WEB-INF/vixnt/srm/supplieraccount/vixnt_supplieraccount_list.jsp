<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-puzzle-piece"></i> <a style="cursor: pointer;">供应链管理</a><span>> 连锁经营管理 </span><span>> 账号管理 </span>
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
	<section id="" class="">
		<div style="overflow: hidden; position: relative; padding: 0;">
			<article style="width: 260px; float: left; margin-right: 20px">
				<div class="tree-box">
					<h2>组织架构</h2>
					<ul id="tree" class="ztree" style="height: 470px; overflow-x: hidden; overflow-y: auto;"></ul>
					<input type="hidden" id="selectId" name="selectId" value="" /><input type="hidden" id="selectTreeType" name="selectTreeType" value="" />
					<script type="text/javascript">
						var zTree;
						var setting = {
						async : {
						enable : true,
						url : "${nvix}/nvixnt/vixntSupplierAccountAction!findSupplierTreeToJson.action",
						autoParam : [ "id", "treeType" ]
						},
						callback : {
							onClick : onClick
						}
						};
						function onClick(event, treeId, treeNode, clickFlag) {
							$("#selectId").val(treeNode.id);
							$("#selectTreeType").val(treeNode.treeType);
							userAccountTable.ajax.reload();
						}
						zTree = $.fn.zTree.init($("#tree"), setting);
					</script>
				</div>
			</article>
			<article style="overflow: hidden; zoom: 1;">
				<div class="jarviswidget">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>账号列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										姓名: <input type="text" value="" class="form-control" id="employeeName">
									</div>
									<button onclick="userAccountTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#employeeName').val('');userAccountTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="userAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	pageSetUp();
	var userAccountColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		if (data.employee != null) {
			return data.employee.name;
		}
		return "";
	}
	}, {
	"title" : "账号",
	"data" : function(data) {
		return data.account;
	}
	}, {
	"title" : "是否登陆限制",
	"data" : function(data) {
		if (data.enable == 0) {
			return "<span class='label label-info'>禁用</span>";
		} else {
			return "<span class='label label-primary'>激活</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "','修改');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteShelf('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;
	}
	} ];

	var userAccountTable = initDataTable("userAccountTableId", "${nvix}/nvixnt/vixntSupplierAccountAction!goSingleList.action", userAccountColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#selectId").val();
		var treeType = $("#selectTreeType").val();
		var employeeName = $("#employeeName").val();
		employeeName = Url.encode(employeeName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"treeType" : treeType,
		"employeeName" : employeeName
		};
	}, 10, '0');
	function deleteShelf(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntSupplierAccountAction!deleteById.action?id=' + id, '是否删除账号?', userAccountTable);
	};
	$("#userAccountForm").validationEngine();
	function goSaveOrUpdate(id, title) {
		if (id != null && id != '') {
			openSaveOrUpdateWindow('${nvix}/nvixnt/vixntSupplierAccountAction!goSaveOrUpdate.action?id=' + id, "userAccountForm", title, 900, 475, userAccountTable);
		} else {
			if ($("#selectId").val() != null && $("#selectId").val() != '' && $("#selectTreeType").val() == 'E') {
				openSaveOrUpdateWindow('${nvix}/nvixnt/vixntSupplierAccountAction!goSaveOrUpdate.action?parentId=' + $('#selectId').val() + "&id=" + id, "userAccountForm", title, 900, 475, userAccountTable);
			} else {
				layer.alert("请选择员工!");
			}
		}
	};
</script>