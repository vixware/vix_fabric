<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-dollar"></i> 会员消费管理<span>> 积分记录 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<!-- <div class="page-title">
				<a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a>
			</div> -->
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>积分记录列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							会员姓名: <input type="text" value="" class="form-control" id="searchName">
							手机号: <input type="text" value="" class="form-control" id="phone">
						</div>
						<button onclick="memberTagTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');$('#phone').val('');memberTagTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="areaLevelTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var memberTagColumns = [ {
		"orderable" : false,
		"title" : "编号",
		"width" : "10%",
		"data" : function(data) {
			return "";
		}
		}, {
		"title" : "会员姓名",
		"data" : function(data) {
			return data.customerName;
		}
		}, {
		"title" : "会员手机号",
		"data" : function(data) {
			return data.customerPhone;
		}
		}, {
		"title" : "积分来源",
		"data" : function(data) {
			return data.pointSource;
		}
		}, {
		"title" : "来源操作",
		"data" : function(data) {
			return data.operation;
		}
		}, {
		"title" : "积分值",
		"data" : function(data) {
			return data.pointValue;
		}
		}, {
		"title" : "积分类型",
		"data" : function(data){
			if(data.pointType == "1"){
				return "<span class='label label-info'>增加</span>";
			}else if(data.pointType == "2"){
				return "<span class='label label-info'>减少</span>";
			}
			return "";
		}
		}, {
		"title" : "操作日期",
		"data" : function(data) {
			return data.createTimeTimeStr;
		}
		}];

		var memberTagTable = initDataTable("areaLevelTableId", "${nvix}/nvixnt/integralManagementsubsidiaryAction!goSingleList.action", memberTagColumns, function(page, pageSize, orderField, orderBy) {
			var searchName = $("#searchName").val();
			searchName = Url.encode(searchName);
			var phone = $("#phone").val();
			return {
			"page" : page,
			"pageSize" : pageSize,
			"searchName" : searchName,
			"phone" : phone
			};
		});
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntMemberLevelManagementAction!goSaveOrUpdate.action?id=' + id, "memberTagForm", title, 750,450, memberTagTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntMemberLevelManagementAction!deleteById.action?id=' + id, '是否删除会员等级?', memberTagTable);
	};
	pageSetUp();
</SCRIPT>