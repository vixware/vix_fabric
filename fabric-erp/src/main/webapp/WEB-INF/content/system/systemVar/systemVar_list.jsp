<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#varCode').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/system/systemVarAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
};
function deleteByIds(){
	var ids = '';
	$("[name='chkId']").each(function(){  
		if($(this).attr('checked')){
			ids+=$(this).val()+",";  
		}
	});
	asyncbox.success(ids,"选中的id");
}

function deleteById(id){
	asyncbox.confirm('确定要删除该常量么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/system/systemVarAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					  showMessage(result);
					  setTimeout("", 1000);
						pager("start","${vix}/system/systemVarAction!goSingleList.action?varCode="+name,'systemVar');
				  }
			});
		}
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/system/systemVarAction!goSingleList.action?varCode="+name,'systemVar');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/system/systemVarAction!goSingleList.action?varCode="+name,'systemVar');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/system/systemVarAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&varCode="+name,'systemVar');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/systemVarAction!goSingleList.action?varCode="+name,'systemVar');
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/system/systemVarAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 450,
					height : 265,
					title:"编辑",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#systemVarForm').validationEngine('validate')){
								var queryString = $('#systemVarForm').formSerialize(); 
								$.post('${vix}/system/systemVarAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										//loadContent('${vix}/system/systemVarAction!goList.action');
										pager("start","${vix}/system/systemVarAction!goSingleList.action?varCode="+name,'systemVar');
									}
								 );
							}else {
								return false;
							}
						}
						},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};

</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb systemVar">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_base" /></a></li>
				<li><a href="#">系统常量管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /> </span> </a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b> </strong>
				<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the
					Communist Party of China. Containing</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_unapproved_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_by_plan" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_approval_in" /> </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_content" /><input type="text" class="int" id="varCode"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();">
			</label>
		</div>
	</div>
	<%-- <div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="activityBudgetList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div> --%>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="systemVarInfo"></b> <s:text name='cmn_to' /> <b class="systemVarTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="systemVar" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="systemVarInfo"></b> <s:text name='cmn_to' /> <b class="systemVarTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
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