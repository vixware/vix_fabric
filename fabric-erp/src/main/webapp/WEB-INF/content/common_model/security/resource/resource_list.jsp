<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#resourceName').val();
	name= Url.encode(name);
	name = encodeURI(name);
}
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/security/resourceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 280,
					title:"资源",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($("#resourceForm").validationEngine('validate')){
								if(confirm("确定要保存资源吗?")){
									var queryString = $('#resourceForm').formSerialize(); 
									$.post('${vix}/common/security/resourceAction!saveOrUpdate.action',
										queryString,
										function(result){
											showMessage(result);
											setTimeout("", 1000);
											pager("start","${vix}/common/security/resourceAction!goSingleList.action?searchResourceName="+name,'resource');
										}
									 );
								}
							}else{
								return false;
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
		  url:'${vix}/common/security/resourceAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			  showMessage(result);
			  setTimeout("", 1000);
				pager("start","${vix}/common/security/resourceAction!goSingleList.action?searchResourceName="+name,'resource');
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/security/resourceAction!goSingleList.action?searchResourceName="+name,'resource');
}
 

//载入分页列表数据
pager("start","${vix}/common/security/resourceAction!goSingleList.action?searchResourceName="+name,'resource');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#resourceOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/security/resourceAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&searchResourceName="+name,'resource');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/security/resourceAction!goSingleList.action?searchResourceName="+name,'resource');
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/system/sec_menu.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec_resource" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
			<%-- <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete'/></span></a> --%>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img alt="" src="${vix}/common/img/icon_11.png"></b>
			<div class="popup">
				<strong>
					<i class="close"><a href="#"></a></i>
					<i><a href="#"></a></i>
					<i><a href="#"></a></i>
					<b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="${vix}/common/img/icon_10.png" alt="" /><s:text name="cmn_index"/></a></li>
			<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="cmn_category"/></a></li>
			<li class="fly">
				<a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="cmn_category"/></a>
				<ul>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="${vix}/common/img/icon_10.png"><s:text name="information"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="resourceName" value=""></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" />
			</label>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="resourceInfo"></b> <s:text name='cmn_to' /> <b class="resourceTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="resource" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="resourceInfo"></b> <s:text name='cmn_to' /> <b class="resourceTotalCount"></b>)
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