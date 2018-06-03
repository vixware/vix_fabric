<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

function saveOrUpdate(id,parentId){
	if(id>0){
		_tabShow('工单信息','${vix}/eam/workOrdersAction!lbWorkOrderMgrDetail.action?id='+id,'_p_saveOrUpdate');
	}else{
		_tabShow('工单创建','${vix}/eam/workOrdersAction!gdCreate.action','_p_saveOrUpdate');
	}
}



$(function(){
	//载入tab数据
	_load_tab_page_content();
});


</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
	<div class="drop">
		<p>
			<input name="" type="button" onclick="saveOrUpdate(0,$('#selectId').val());" class="btn" value="<s:text name="cmn_add"/>" />
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input name="eqName" type="text" class="int more" placeholder="工单编号 " id="nameS"></label> <label> <input name="searchBtn" id="searchBtn" onclick="goSearchEquipment()" type="button" class="btn" value="<s:text name='cmn_search'/>" /> <input name="" type="button" onclick="javascript:return clearSearch()" class="btn"
				value="<s:text name='cmn_reset'/>" />
			</label> <strong id="lb_search_advanced"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="grid_search search_advanced">
			<label> 设备编号 <input name="eqCode" type="text" class="int" />
			</label> <label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /> <input type="button" class="btn reset" value="<s:text name='cmn_reset'/>" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!--  暂时没有数据，具体需要以设么逻辑去罗列快捷数据，可以不浪费资源
        	<s:iterator value="categoryList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
 -->
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/eam/workOrdersAction!lbWorkOrderMgrList.action"> <img src="img/mail.png" alt="" /> 工单列表
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
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

<script type="text/javascript"> 
<!--
//-->
</script>