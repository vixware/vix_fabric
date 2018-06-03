<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#indexPdcName').val();
	name= Url.encode(name);
	name = encodeURI(name);
}
function saveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/common/security/indexPdcAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
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
	asyncbox.confirm('确定要删除该配置么?','<s:text name='cmn_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/common/security/indexPdcAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(result){
					  showMessage(result);
					  setTimeout("", 1000);
					  pager("start","${vix}/common/security/indexPdcAction!goSingleList.action?name="+name,'indexPdc');
				  }
			});
		}
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/security/indexPdcAction!goSingleList.action?name="+name,'indexPdc');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/common/security/indexPdcAction!goSingleList.action?name="+name,'indexPdc');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/security/indexPdcAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'indexPdc');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/security/indexPdcAction!goSingleList.action?name="+name,'indexPdc');
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/security/indexPdcAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 650,
					height : 275,
					title:"配置编辑",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#indexPdcForm').validationEngine('validate')){
								var queryString = $('#indexPdcForm').formSerialize(); 
								$.post('${vix}/common/security/indexPdcAction!saveOrUpdate.action',
									queryString,
									function(result){
										showMessage(result);
										setTimeout("", 1000);
										pager("start","${vix}/common/security/indexPdcAction!goSingleList.action?name="+name,'indexPdc');
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
		<i> <a href="#"><img alt="" src="${vix}/common/img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="${vix}/common/img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_org" /></a></li>
				<li><a href="#"><s:text name="system_control_org_sec" /></a></li>
				<li><a href="#">首页面数据项配置</a></li>
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
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="indexPdcName"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();">
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
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a> <%-- <ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul> --%></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="indexPdcInfo"></b> <s:text name='cmn_to' /> <b class="indexPdcTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="indexPdc" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="indexPdcInfo"></b> <s:text name='cmn_to' /> <b class="indexPdcTotalCount"></b>)
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