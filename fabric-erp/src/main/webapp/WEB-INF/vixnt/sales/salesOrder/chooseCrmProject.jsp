<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" style="padding: 6px;">
	<header style="height: 1px;border-color:#ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin:5px;">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" value="" placeholder="项目名称" class="form-control col-md-3" id="crmProjectName" />
						</div>
						<button onclick="chooseCrmProjectTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#crmProjectName').val('');chooseCrmProjectTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
			</div>
		   <table id="chooseCrmProject" class="table table-striped table-bordered table-hover" width="100%">
		   		<thead>			                
					<tr>
						<th width="10%">选择</th>
						<th width="20%">客户名称</th>
						<th width="20%">项目主题</th>
						<th width="20%">预计金额</th>
						<th width="15%">立项时间</th>
						<th width="15%">预计签单日期</th>
					</tr>
				</thead>
		    </table>
	    </div>
	</div>
</div>
<script type="text/javascript">
	var chooseCrmProjectColumns = [
		{"orderable":false,"data" : function(data) {
			return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.subject + "');\"/>";
		}},
		{"name":"customerAccount","data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}},
		{"name":"subject","data" : function(data) {return data.subject;}},
		{"name":"forecastMoney","data" : function(data) {return '￥' + data.forecastMoney;}},
		{"name":"projectEstablishDate","data" : function(data) {return data.projectEstablishDateStr;}},
		{"name":"forecastSignDate","data" : function(data) {return data.forecastSignDateStr;}}
	];
	var chooseCrmProjectTable = initDataTable("chooseCrmProject","${nvix}/nvixnt/nvixSalesOrderAction!getCrmProjectJson.action",chooseCrmProjectColumns,function(page,pageSize,orderField,orderBy){
	 	var subject = $("#crmProjectName").val();
	 	subject = Url.encode(subject);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"subject":subject};
	},10,'1','0');
	
	/** 页面加载完调用 */
	pageSetUp();
</script>