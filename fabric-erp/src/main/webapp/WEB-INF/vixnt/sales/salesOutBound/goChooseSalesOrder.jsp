<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}"/>
<fieldset>
	<div class="col-sm-12" style="text-align: left; padding:10px 5px 0px 15px;">
		<div id="saleOrderItemDiv" class="jarviswidget jarviswidget-color-white">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i> </span>
				<h2>列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin:5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-4">
								<input type="text" value="" placeholder="主题" class="form-control" id="searchItemName">
							</div>
							<button onclick="chooseItemTable.ajax.reload();" type="button" class="btn btn-info" >
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#searchItemName').val('');chooseItemTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</div>
					</div>
				    <table id="chooseItem" class="table table-striped table-bordered table-hover" width="100%">
				   		<thead>
							<tr>
								<th width="10%">选择</th>
								<th width="20%">编码</th>
								<th width="20%">主题</th>
								<th width="20%">创建日期</th>
								<th width="15%">客户</th>
							</tr>
						</thead>
				    </table>
			 	</div>
			</div>
		</div>
	</div>
</fieldset>
<script type="text/javascript" >
	var chooseItemColumns = [
			{"orderable":false,"data" : function(data) {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.title + "');\"/>";
			}},
	   		{"data" : function(data) {return data.code;}},
	   		{"data" : function(data) {return data.title;}},
	   		{"data" : function(data) {return data.billDateStr;}},
	   		{"data" : function(data) {return data.customerAccount == null ? '' : data.customerAccount.name;}}	   	
		];

	var chooseItemTable = initDataTable("chooseItem","${nvix}/nvixnt/nvixSalesOrderAction!goListContentJson.action",chooseItemColumns,function(page,pageSize,orderField,orderBy){
 		var name = $("#searchItemName").val();
 	 	name=Url.encode(name);
 	 	var categoryId = $("#chooseItemCategoryId").val();
 	 	var id = $("#id").val();
 		return {"page" : page ,"pageSize" : pageSize,"orderField":orderField,"orderBy":orderBy,"name":name,"categoryId":categoryId,"id":id};
 	},10,'1','0');
</script>