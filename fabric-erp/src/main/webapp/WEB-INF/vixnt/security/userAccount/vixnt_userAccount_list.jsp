<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa icon-iconfont-user"></i> 系统管理 <span>> 账号管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="goSaveOrUpate('','新增账号');">
					<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>账号列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							账号: <input type="text" value="" class="form-control" id="searchtaskname">
						</div>
						<div class="form-group">
							姓名: <input type="text" value="" class="form-control" id="username">
						</div>
						<button onclick="wxpUserAccountTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchtaskname').val('');$('#username').val('');wxpUserAccountTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="wxpWeixinSiteTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var wxpUserAccountColumns = [ {
	"orderable" : false,
	"title" : "编号",
	"width" : "5%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "账号",
	"width" : "10%",
	"data" : function(data) {
		return data.account;
	}
	}, {
	"title" : "姓名",
	"width" : "10%",
	"data" : function(data) {
		if (data.employee != null && data.employee.name != null) {
			return data.employee.name;
		}
		return "";
	}
	}, {
	"title" : "部门",
	"width" : "15%",
	"data" : function(data) {
		if (data.employee != null && data.employee.organizationUnit != null) {
			return data.employee.organizationUnit.fs;
		}
		return "";
	}
	}, {
	"title" : "公司",
	"width" : "25%",
	"data" : function(data) {
		if (data.employee != null && data.employee.organizationUnit != null && data.employee.organizationUnit.organization != null) {
			return data.employee.organizationUnit.organization.briefName;
		}
		return "";
	}
	}, {
	"title" : "创建时间",
	"width" : "15%",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "状态",
	"width" : "10%",
	"data" : function(data) {
		if (data.enable == 0) {
			return "<span class='label label-danger'>禁用</span>";
		} else if (data.enable == 1) {
			return "<span class='label label-success'>激活</span>";
		}
		return "";
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		var set = "";
		if (data.enable == '1') {
			set = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"disabledAccountById('" + data.id + "');\" title='禁用'><span class='txt-color-blue pull-right'><i class='fa fa-times-circle-o'></i></span></a>";
		} else {
			set = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"enableAccountById('" + data.id + "');\" title='启用'><span class='txt-color-blue pull-right'><i class='fa fa-times-circle-o'></i></span></a>";
		}
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpate('" + data.id + "','修改账号');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + set + "&nbsp;&nbsp;" + del;
	}
	} ];
	var wxpUserAccountTable = initDataTable("wxpWeixinSiteTableId", "${nvix}/nvixnt/nvixntUserAccountAction!goSingleList.action", wxpUserAccountColumns, function(page, pageSize, orderField, orderBy) {
		var searchtaskname = $("#searchtaskname").val();
		var username = $("#username").val();
		username = Url.encode(username);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"username" : username,
		"account" : searchtaskname
		};
	});

	//禁用
	function disabledAccountById(id) {
		updateEntityByConfirm('${nvix}/nvixnt/nvixntUserAccountAction!disabledAccountById.action?id=' + id, '确定禁用吗?', wxpUserAccountTable);
	};
	//启用
	function enableAccountById(id) {
		updateEntityByConfirm('${nvix}/nvixnt/nvixntUserAccountAction!enableAccountById.action?id=' + id, '确定启用吗?', wxpUserAccountTable);
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntUserAccountAction!deleteById.action?id=' + id, '是否删除账号?', wxpUserAccountTable);
	};
	//表单校验
	$("#taskForm").validationEngine();
	//创建账号
	function goSaveOrUpate(id, title) {
		var url = "${nvix}/nvixnt/nvixntUserAccountAction!goSaveOrUpdate.action?id=" + id;
		var mycars = new Array("确定", "取消")
		$.ajax({
		url : url,
		cache : false,
		success : function(html) {
			layer.open({
			type : 1,
			title : title,
			area : [ 850 + 'px', 500 + 'px' ],
			closeBtn : 1,
			content : html,
			btn : mycars,
			yes : function(index, layero) {
				if ($("#password").val() != $("#passwordConfirm").val()) {
					layer.alert("密码和确认密码不一致!");
				} else {
					if ($("#taskForm").validationEngine('validate')) {
						$("#taskForm").ajaxSubmit({
						type : "post",
						url : "${nvix}/nvixnt/nvixntUserAccountAction!saveOrUpdate.action",
						dataType : "text",
						success : function(html) {
							layer.close(index);
							showMessage(html, 'success');
							wxpUserAccountTable.ajax.reload();
						}
						});
					}
				}
			},
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
</script>