<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" value="" placeholder="项目名称" class="form-control col-md-3" id="chooseCustomerName" />
						</div>
						<button onclick="chooseProjectTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#chooseCustomerName').val('');chooseProjectTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
			</div>
			<table id="chooseCustomer" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%">选择</th>
						<th width="45%">项目主题</th>
						<th width="45%">立项日期</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");
	var chooseCustomerColumns = [
		{"orderable":false,"data" : function(data) {
			return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.subject + "');\"/>";
		}},
		{"name":"subject","data" : function(data) {return data.subject}},
		{"name":"projectEstablishDate","data" : function(data) {return data.projectEstablishDateStr;}}
	];
	
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseProjectTable = initDataTable("chooseCustomer","${nvix}/nvixnt/nvixCrmProjectAction!goListContent.action",chooseCustomerColumns,function(page,pageSize,orderField,orderBy){
	 	var name = $("#chooseCustomerName").val();
	 	name=Url.encode(name);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"title":name};
	}, 10, selectType, isGenerateIndex);
	
	/** 页面加载完调用 */
	pageSetUp();
</script>