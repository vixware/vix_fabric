<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/plugin/highcharts/modules/exporting.js"></script>
<script src="${vix}/plugin/highcharts/modules/funnel.js"></script>
<script src="${vix}/plugin/highcharts/highcharts.js"></script>
<script src="${vix}/plugin/highcharts/highcharts-more.js"></script>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
function chooseCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 520,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerAccountName").val(result[1]);
								loadSaleFunnel();
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function loadSaleFunnel() {
	$("#saleFunnelContent").empty();
	$("#saleFunnelContent").html("<div id='saleFunnelContainer' style='height:500px;'></div>");
	$.ajax({
		  url:'${vix}/crm/salechance/saleChanceAction!loadFunnel.action?id='+$("#customerAccountId").val()+"&categoryId="+$("#customerAccountCategoryId").val(),
		  cache: false,
		  success: function(d){
			  $('#saleFunnelContainer').highcharts({
				    chart: {
				    	type: 'funnel',
				        marginRight: 100 
				    },
				    exporting: {
			            enabled: false
			        },
				    title: {
				        text: '销售漏斗',
				        x: -50 
				    },
				    plotOptions: {
				        series: {
				            dataLabels: {
				                enabled: true,
				                color: 'black',
				                softConnector: true
				            },
				            neckWidth: '30%',
				            neckHeight: '25%'
				        }
				    },
				    legend: {
				        enabled: false
				    },
				    series: eval(d)
				});
		  }
	});
}
loadSaleFunnel();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/project.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">项目管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goSaleFunnel.action');">销售漏斗</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="saleFunnelData">
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<input type="hidden" id="customerAccountId"> <input type="hidden" id="customerAccountCategoryId"> <label><s:text name="cmn_name" /><input type="text" class="int" id="customerAccountName" style="width: 180px;"></label> <label><input type="button" value="选择客户" class="btn" onclick="chooseCustomerAccount();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="$('#customerAccountCategoryId').val('');loadSaleFunnel();zTree.cancelSelectedNode();">根目录</a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;
				var setting = {
					async: {
						enable: true,
						url:"${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#customerAccountCategoryId").val(treeNode.id);
					loadSaleFunnel();
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<!-- left -->
		<div id="right">
			<div id="saleFunnelContent" class="right_content"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>