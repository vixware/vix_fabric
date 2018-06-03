<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
var dataConfigId = "";
function loadName(){
	name = $('#names').val();
	dataConfigId = $('#dataConfigId').val();
	//alert(name);
	//name=Url.encode(name);
	//name=Url.encode(name);
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
	loadName();
	/* if(dataConfigId=="-1"){
		alert("请选择配置项！");
		return;
	} */
	$.ajax({
		 url:'${vix}/common/security/dataColPolicyAction!goSaveOrUpdate.action?id='+id +"&name="+name+"&dataConfigId="+dataConfigId,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
	/** loadName();
	if(dataConfigId=="-1"){
		alert("请选择人事范围！");
		return;
	}
	$.ajax({
		  url:'${vix}/common/security/dataColPolicyAction!goSaveOrUpdate.action?id='+id +"&dataConfigId="+dataConfigId,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 280,
					title:"列级权限配置",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							$("#dataColForm").validationEngine('validate');
							var queryString = $('#dataColForm').formSerialize(); 
							$.post('${vix}/common/security/dataColPolicyAction!saveOrUpdate.action',
								queryString,
								function(result){
									showMessage(result);
									setTimeout("", 1000);
									pager("start","${vix}/common/security/dataColPolicyAction!goSingleList.action?name="+name,'dataCol');
								}
							 );
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
	*/
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
		  url:'${vix}/common/security/dataColPolicyAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			 	showMessage(result);
				setTimeout("", 1000);
				pager("start","${vix}/common/security/dataColPolicyAction!goSingleList.action?name="+name+"&dataConfigId="+dataConfigId,'dataCol');
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/security/dataColPolicyAction!goSingleList.action?name="+name+"&dataConfigId="+dataConfigId,'dataCol');
}
 

//载入分页列表数据
pager("start","${vix}/common/security/dataColPolicyAction!goSingleList.action?name="+name+"&dataConfigId="+dataConfigId,'dataCol');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#dataColOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/security/dataColPolicyAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&dataConfigId="+dataConfigId,'dataCol');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/security/dataColPolicyAction!goSingleList.action?name="+name+"&dataConfigId="+dataConfigId,'dataCol');
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
				<li><a href="#"><s:text name="system_control_org_sec_dataPly" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<%-- <a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add'/></span></a> --%>
			<a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="names"></label> <label>配置类别:<s:select id="dataConfigId" headerKey="" headerValue="--请选择--" list="%{dataColConfigList}" listValue="configName" listKey="id" multiple="false" theme="simple" value="%{dataConfigId}"></s:select></label> <label> <input type="button"
				value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" />
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="dataColInfo"></b> <s:text name='cmn_to' /> <b class="dataColTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="dataCol" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="dataColInfo"></b> <s:text name='cmn_to' /> <b class="dataColTotalCount"></b>)
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