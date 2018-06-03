<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-dollar"></i> 会员消费管理<span>> 兑换记录 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<!-- <a data-toggle="modal" href="#">
					<button class="btn btn-primary" type="button" onclick="saveOrUpdate('','新增');">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增
					</button>
				</a> -->
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>兑换列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							兑换人: <input type="text" value="" class="form-control" id="searchName">
						</div>
						<button onclick="memberTagTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#searchName').val('');memberTagTable.ajax.reload();" type="button" class="btn btn-default">
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
		"width" : "5%",
		"data" : function(data) {
			return "";
		}
		}, {
		"title" : "兑换人",
		"width" : "15%",
		"data" : function(data) {
			return data.customerName;
		}
		}, {
		"title" : "消耗积分",
		"width" : "15%",
		"data" : function(data) {
			return data.integralAmount;
		}
		}/* , {
		"title" : "商品名称",
		"width" : "15%",
		"data" : function(data) {
			return data.itemName;
		}
		} */, {
		"title" : "兑换金额",
		"width" : "15%",
		"data" : function(data) {
			return data.money+"元";
		}
		}, {
		"title" : "兑换日期",
		"width" : "15%",
		"data" : function(data) {
			return data.changeDateStr;
		}
		}];

		var memberTagTable = initDataTable("areaLevelTableId", "${nvix}/nvixnt/nvixIntegraChangeHistory!goSingleList.action", memberTagColumns, function(page, pageSize, orderField, orderBy) {
			var searchName = $("#searchName").val();
			searchName = Url.encode(searchName);
			return {
			"page" : page,
			"pageSize" : pageSize,
			"searchName" : searchName
			};
		});
	function saveOrUpdate(id, title) {
		openSaveOrUpdateWindow('${nvix}/nvixnt/vixntIntegralRulesSetAction!goSaveOrUpdate.action?id=' + id, "memberTagForm", title, 750,450, memberTagTable);
	};
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntMemberLevelManagementAction!deleteById.action?id=' + id, '是否删除会员等级?', memberTagTable);
	};
	pageSetUp();
</SCRIPT>