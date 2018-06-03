<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 客户关系管理 <span>> 联系人管理 </span><span>> 纪念日 </span>
			</h1>
		</div>
		<div class="col-lg-8 text-align-right">
			<ul id="u-sparks" class="clearfix">
				<li class="u-sparks-info">
					<a href="javascript:void(0);" onclick="goMemorialDayList();">
						<img src="${nvix}/vixntcommon/base/images/clock.png">
						<div class="u-sparks-text">
							<s:if test="memorialDayNum != null">${memorialDayNum}</s:if>
							<s:else>0</s:else>
							<span>纪念日</span>
						</div> 
					</a>
				</li>
			</ul>
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
				<div class="jarviswidget jarviswidget-color-darken" id="chanceAndTrackingHead" data-widget-editbutton="false">
					<header>
						<span class="widget-icon"> <i class="fa fa-table"></i>
						</span>
						<h2>纪念日列表</h2>
					</header>
					<div>
						<div class="widget-body no-padding">
							<div id="chanceAndTrackingSearchForm">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										客户:<input type="text" value="" placeholder="客户姓名" class="form-control" id="searchCustomerName">
									</div>
									<button onclick="memorialDayTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#searchCustomerName').val('');memorialDayTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
							<table id="memorialDayTableId" class="table table-striped table-bordered table-hover" width="100%">
								<thead>
									<tr>
										<th width="8%">编号</th>
										<th>纪念日类型</th>
										<th>客户</th>
										<th>联系人</th>
										<th>纪念日</th>
										<th>下一次提醒</th>
										<th>创建日期</th>
										<th width="8%">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script type="text/javascript">
	var memorialDayColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "memorialDayType",
	"data" : function(data) {
		if(data.memorialDayType != null){
			return "<span class='label label-info'>"+data.memorialDayType.name+"</span>";
		}
	}
	}, {
	"name" : "customerAccount",
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"name" : "contactPerson",
	"data" : function(data) {
		return data.contactPerson == null ? '' : data.contactPerson.name;
	}
	}, {
	"name" : "date",
	"data" : function(data) {
		return data.dateStr;
	}
	},{
	"name" : "date",
	"data" : function(data) {
		return data.nextDateStr;
	}
	}, {
	"name" : "createTime",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if (null != data.id) {
			var update = "<a class='btn btn-xs btn-default' onclick=\"saveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var memorialDayTable = initDataTable("memorialDayTableId", "${nvix}/nvixnt/nvixMemorialDayAction!goListContent.action", memorialDayColumns, function(page, pageSize, orderField, orderBy) {
		var cName = $("#searchCustomerName").val();
		cName = Url.encode(cName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"customerName" : cName,
		};
	});

	//更新
	function saveOrUpdate(id) {
		goSaveOrUpdateEntity('${nvix}/nvixnt/nvixMemorialDayAction!goSaveOrUpdate.action?id=' + id);
	}

	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixMemorialDayAction!deleteById.action?id=' + id, '是否删除纪念日?', memorialDayTable);
	}
	// 弹出纪念日
	function goMemorialDayList(){
		openWindowForShow("${nvix}/nvixnt/nvixMemorialDayAction!goMemorialDayList.action", '纪念日列表');
	}

	pageSetUp();
</SCRIPT>