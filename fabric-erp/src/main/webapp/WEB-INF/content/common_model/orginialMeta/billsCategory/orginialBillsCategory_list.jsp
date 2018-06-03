<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var categoryName = "";
function loadName(){
	categoryName = $('#categoryName').val();
	categoryName= Url.encode(categoryName);
	categoryName = encodeURI(categoryName);
}
/* function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/common/security/orginialBillsCategoryAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}; */

function deleteById(id){
	asyncbox.confirm('确定要删除该单据分类么?','<s:text name='cmn_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/common/model/orginialBillsCategoryAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(result){
					  showMessage(result);
					  setTimeout("", 1000);
						pager("start","${vix}/common/model/orginialBillsCategoryAction!goSingleList.action?categoryName="+categoryName,'orginialBillsCategory');
				  }
			});
		}
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/model/orginialBillsCategoryAction!goSingleList.action?categoryName="+categoryName,'orginialBillsCategory');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/common/model/orginialBillsCategoryAction!goSingleList.action?categoryName="+categoryName,'orginialBillsCategory');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/model/orginialBillsCategoryAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&categoryName="+categoryName,'orginialBillsCategory');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/model/orginialBillsCategoryAction!goSingleList.action?categoryName="+categoryName,'orginialBillsCategory');
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/model/orginialBillsCategoryAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 450,
					height : 275,
					title:"单据分类编辑",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#orginialBillsCategoryForm').validationEngine('validate')){
								var queryString = $('#orginialBillsCategoryForm').formSerialize(); 
								$.post('${vix}/common/model/orginialBillsCategoryAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										pager("start","${vix}/common/model/orginialBillsCategoryAction!goSingleList.action?categoryName="+categoryName,'orginialBillsCategory');
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
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_base" /></a></li>
				<li><a href="#">单据分类</a></li>
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
			<label><s:text name="cmn_name" /><input type="text" class="int" id="categoryName"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orginialBillsCategoryInfo"></b> <s:text name='cmn_to' /> <b class="orginialBillsCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="orginialBillsCategory" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orginialBillsCategoryInfo"></b> <s:text name='cmn_to' /> <b class="orginialBillsCategoryTotalCount"></b>)
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