<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/crm/customer/memorialDayAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 260,
					title:"纪念日",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#memorialDayForm').validationEngine('validate')){
								$.post('${vix}/crm/customer/memorialDayAction!saveOrUpdate.action',
									 {'memorialDay.id':$("#memorialDayId").val(),
									  'memorialDay.customerAccount.id':$("#customerAccountId").val(),
									  'memorialDay.contactPerson.id':$("#contactPersonId").val(),
									  'memorialDay.memorialDayType.id':$("#memorialDayTypeId").val(),
									  'memorialDay.dateType':$(":radio[name=dateType][checked]").val(),
									  'memorialDay.date':$("#mdDate").val(),
									  'memorialDay.memo':$("#memo").val()
									},
									function(result){
										asyncbox.success(result,"提示信息",function(action){
											pager("start","${vix}/crm/customer/memorialDayAction!goListContent.action?name="+name,'memorialDay');
										});
									}
								);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
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
	$.ajax({
		  url:'${vix}/crm/customer/memorialDayAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/crm/customer/memorialDayAction!goListContent.action?name="+name,'memorialDay');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/crm/customer/memorialDayAction!goListContent.action?name="+name,'memorialDay');
}

function reset(){
	$('#nameS').val('');
}

loadName();
//载入分页列表数据
pager("start","${vix}/crm/customer/memorialDayAction!goListContent.action?name="+name,'memorialDay');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#memorialDayOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/crm/customer/memorialDayAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'memorialDay');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/crm/customer/memorialDayAction!goListContent.action?name="+name,'memorialDay');
}

$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/crm/project/crmProjectAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/memorialDay.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">联系人管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/memorialDayAction!goList.action');">纪念日管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>客户<input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="reset();"></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="memorialDayInfo"></b> <s:text name='cmn_to' /> <b class="memorialDayTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="memorialDay" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_email' /></a></li>
								<li><a href="#"><s:text name="merger" /></a></li>
								<li><a href="#"><s:text name="export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="memorialDayInfo"></b> <s:text name='cmn_to' /> <b class="memorialDayTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
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