<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="customerAccountId" value="${customerAccountId}" />
<input type="hidden" id="itemId" value="${itemId}" />
<input type="hidden" id="startTime" value="${startTime}" />
<input type="hidden" id="endTime" value="${endTime}" />
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget jarviswidget-color-darken" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							<input type="text" value="" placeholder="" class="form-control col-md-3" id="chooseCustomerName" />
						</div>
						<button onclick="chooseCustomerTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#chooseCustomerName').val('');chooseCustomerTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
			</div>
			<table id="chooseCustomer" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="5%">选择</th>
						<th width="8%">编码</th>
						<th width="8%">名称</th>
						<th width="35%">业绩考评方式</th>
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
			return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "','" + data.performanceEvaluationMethod.name + "');\"/>";
		}},
		{"name":"name","data" : function(data) {return data.code;}},
		{"name":"name","data" : function(data) {return data.name;}},
		{"name":"name","data" : function(data) {
			if(data.performanceEvaluationMethod != null){
				if(data.performanceEvaluationMethod.name == "1"){
					return "<a href='javascript:void(0);'><span class='label label-warning'>定额金额完成百分比</span></a>";
				}else if(data.performanceEvaluationMethod.name == "2"){
					return "<a href='javascript:void(0);'><span class='label label-warning'>实际销售金额</span></a>";
				}else if(data.performanceEvaluationMethod.name == "3"){
					return "<a href='javascript:void(0);'><span class='label label-warning'>定额数量完成百分比</span></a>";
				}else if(data.performanceEvaluationMethod.name == "4"){
					return "<a href='javascript:void(0);'><span class='label label-warning'>实际销售数量</span></a>";
				}else if(data.performanceEvaluationMethod.name == "5"){
					return "<a href='javascript:void(0);'><span class='label label-warning'>毛利</span></a>";
				}
			}
		return ""}}
	];
	
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseCustomerTable = initDataTable("chooseCustomer","${nvix}/nvixnt/nvixntCommissionPlanAction!goSingleListCommissionPlanItem.action",chooseCustomerColumns,function(page,pageSize,orderField,orderBy){
	 	var customerAccountId = $("#customerAccountId").val();
	 	var itemId = $("#itemId").val();
	 	var name = $("#chooseCustomerName").val();
	 	var startTime = $("#startTime").val();
	 	var endTime = $("#endTime").val();
	 	name=Url.encode(name);
		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"customerAccountId":customerAccountId,"itemId":itemId,"startTime":startTime,"endTime":endTime};
	}, 10, selectType, isGenerateIndex);
	function radioChange(entityName,id,name,type){
		parent.$('#' + entityName + 'Id').val(id);
		parent.$('#' + entityName + 'Name').val(name);
		parent.$('#' + entityName + 'Type').val(type);
	}
	/** 页面加载完调用 */
	pageSetUp();
</script>