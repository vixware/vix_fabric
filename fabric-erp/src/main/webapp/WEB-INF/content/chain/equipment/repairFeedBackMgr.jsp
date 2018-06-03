暂时不用了


<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdate(id) {
		var title = '填写设备报修单';
		var url = '${vix}/chain/mmxEquipmentAction!repairMgrEdit.action';
		if (id != null) {
			title = '编辑设备报修单';
			if (id != null)
				url += '?id=' + id;
		}
		var newPageId = _tabShow(title, url, '_p_saveOrUpdate');
	}

	$(function() {
		//载入tab数据
		_load_tab_page_content();
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b></b>
				</strong>
				<p></p>
			</div>
		</div>
		<ul class="dropdown_search_group">
			<!-- <li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /><s:text name="cmn_index"/></a></li> -->
			<li class="fly"><a href="#" id="s_repairStatus" column="repairStatus" search_page_id="tab_home" data_type="integer" filter_type="eq">维修状态</a>
				<ul>
					<li><a parent="s_repairStatus" value="" class="ds_link">全部状态</a></li>
					<li><a parent="s_repairStatus" value="0" class="ds_link">维修申请</a></li>
					<li><a parent="s_repairStatus" value="1" class="ds_link">正在维修</a></li>
					<li><a parent="s_repairStatus" value="2" class="ds_link">维修完成</a></li>
					<li><a parent="s_repairStatus" value="3" class="ds_link">放弃维修</a></li>
				</ul></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="eqTitle" class="int more" placeholder="设备名称" id="nameS"></label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<div class="grid_search search_advanced">
			<label> 设备编号 <input name="eqCode" type="text" class="int" />
			</label> <label> 门店 <input name="mendian" type="text" class="int" />
			</label> <label> 门店编号 <input name="mendianCode" type="text" class="int" />
			</label> <label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /> <input type="button" class="btn reset" value="<s:text name='cmn_reset'/>" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<!--  暂时没有数据，具体需要以设么逻辑去罗列快捷数据，可以不浪费资源
        	<s:iterator value="categoryList" var="c">
        		<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.name}</a></li>
        	</s:iterator>
 -->
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/chain/mmxEquipmentAction!repairFeedBackMgrListContent.action"> <img src="img/mail.png" alt="" /> 报修记录
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