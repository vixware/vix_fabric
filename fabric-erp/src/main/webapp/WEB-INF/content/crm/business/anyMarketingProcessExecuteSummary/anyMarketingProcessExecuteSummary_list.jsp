<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function exportExcel() {
		form = document.getElementById("exportMD");
		form.action = "${vix}/crm/business/allMarketingProcessExecuteSummaryAction!exportTakeStockExcel.action";
		form.submit();
	}
	bindSearch();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" /> 供应链 </a></li>
				<li><a href="#">会员交互管理 </a></li>
				<li><a href="#">营销中心</a></li>
				<li><a href="#">智能营销</a></li>
				<li><a href="#">营销流程执行汇总</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="exportExcel();"><span>导出</span> </a>
		</p>
	</div>
</div>
<form action="${vix}/crm/business/allMarketingProcessExecuteSummaryAction!exportTakeStockExcel.action" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="编码"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content" id="a1">
				<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
					<script type="text/javascript">
						$('#dlAddress2').datagrid({
						url : '${vix}/crm/business/allMarketingProcessExecuteSummaryAction!getLogJson.action',
						width : 'auto',
						height : 450,
						nowrap : true,
						autoRowHeight : false,
						striped : true,
						collapsible : true,
						sortName : 'id',
						sortOrder : 'desc',
						pagination : true,
						remoteSort : true,
						rownumbers : true,
						fitColumns : true,
						pageSize : 10,
						idField : 'id',
						frozenColumns : [ [ {
						field : 'id',
						title : '编号',
						width : 100,
						hidden : true,
						align : 'center'
						}, {
						field : 'name',
						title : '主题',
						width : 250,
						align : 'center',
						sortable : true
						} ] ],
						columns : [ [ {
						field : 'createTime',
						title : '运行时间',
						width : 100,
						align : 'center',
						sortable : true
						}, {
						field : 'customerAccount',
						title : '会员姓名',
						width : 100,
						align : 'center',
						sortable : true,
						formatter : function(value, rec, index) {
							var measureUnitName = '';
							if (value != null) {
								measureUnitName = value.name;
							}
							return measureUnitName;
						}
						}, {
						field : 'membershipSubdivision',
						title : '会员细分',
						width : 100,
						align : 'center',
						sortable : true,
						formatter : function(value, rec, index) {
							var measureUnitName = '';
							if (value != null) {
								measureUnitName = value.name;
							}
							return measureUnitName;
						}
						} ] ]
						});
					</script>
					<div style="padding: 8px;">
						<table id="dlAddress2"></table>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>