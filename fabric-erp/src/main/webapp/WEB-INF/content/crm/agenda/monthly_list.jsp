<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function loadWeeklyContent(tag,monthNumber){
	var url = '';
	if(tag == 'newtab1'){
		url = '${vix}/crm/agenda/monthlyAction!goTab1ListContent.action?monthNumber='+monthNumber;
	}
	if(tag == 'newtab2'){
		url = '${vix}/crm/agenda/monthlyAction!goTab2ListContent.action?monthNumber='+monthNumber+"&employeeId="+$("#empId").val();
	}
	$.ajax({
		  url:url,
		  cache: false,
		  success: function(html){
			  $("#"+tag).html(html);
		  }
	});
}
loadWeeklyContent('newtab1','');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/daily.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/agenda/dailyAction!goList.action');">工作日报</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/agenda/monthlyAction!goList.action');">撰写月报</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:tab(2,1,'newtab',event)"><img alt="" src="img/icon_10.png">写月报</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:tab(2,2,'newtab',event);loadWeeklyContent('newtab2','');"><img alt="" src="img/icon_10.png">看月报</a></li>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1" class="npcontent clearfix"></div>
			<div id="newtab2" style="display: none;"></div>
			<input type="hidden" id="empId" name="empId" />
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>