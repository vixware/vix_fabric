<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input id="pendingOrderId" type="hidden" value="">
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>纪念日列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div style="margin: 5px;">
				</div>
				<table id="memorialDayListTableId" class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th width="8%">编号</th>
							<th>纪念日类型</th>
							<th>客户</th>
							<th>联系人</th>
							<th>纪念日</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var memorialDayColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		if(data.memorialDayType != null){
			return "<span class='label label-info'>"+data.memorialDayType.name+"</span>";
		}
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.customerAccount == null ? '' : data.customerAccount.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.contactPerson == null ? '' : data.contactPerson.name;
	}
	}, {
	"orderable" : false,
	"data" : function(data) {
		return data.dateStr;
	}
	}];

	var memorialDayTable = initDataTable("memorialDayListTableId", "${nvix}/nvixnt/nvixMemorialDayAction!goMemorialDayListContent.action", memorialDayColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});
</script>
