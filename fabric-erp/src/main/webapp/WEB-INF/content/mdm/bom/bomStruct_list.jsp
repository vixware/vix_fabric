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
		  url:'${vix}/mdm/bom/bomStructAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 920,
					height : 420,
					title:"Bom结构",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#bomStructForm').validationEngine('validate')){
								$.post('${vix}/mdm/bom/bomStructAction!saveOrUpdate.action',
									 {'bomStruct.id':$("#id").val(),
									  'bomStruct.configItemBomName':$("#configItemBomName").val(),
									  'bomStruct.item.id':$("#itemId").val(),
									  'bomStruct.bomType':$("#bomType").val(),
									  'bomStruct.bomClass':$("#bomClass").val(),
									  'bomStruct.parentPartAttritionRate':$("#parentPartAttritionRate").val(),
									  'bomStruct.specification':$("#specification").val(),
									  'bomStruct.version':$("#version").val(),
									  'bomStruct.versionDate':$("#versionDate").val(),
									  'bomStruct.versionDescription':$("#versionDescription").val(),
									  'bomStruct.defaultBaseAmount':$("#defaultBaseAmount").val(),
									  'bomStruct.productConfigureModel':$("#productConfigureModel").val(),
									  'companyId':$("#selectId").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											pager("current","${vix}/mdm/bom/bomStructAction!goListContent.action?configItemBomName="+name,'bomStruct');
										});
									}
								 );
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

function saveOrUpdateBomNode(url,id){
	loadContent(url+"?id="+id);
}
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
 
function deleteById(id){
	$.ajax({
		  url:'${vix}/mdm/bom/bomStructAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/mdm/bom/bomStructAction!goListContent.action?configItemBomName="+name,'bomStruct');
			});
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/mdm/bom/bomStructAction!goListContent.action?configItemBomName="+name,'bomStruct');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/mdm/bom/bomStructAction!goListContent.action?configItemBomName="+name,'bomStruct');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#bomStructOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/mdm/bom/bomStructAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&configItemBomName="+name,'bomStruct');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/mdm/bom/bomStructAction!goListContent.action?configItemBomName="+name,'bomStruct');
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src=${vix}/common/img/mdm/itemList.png alt="" />系统管理</a></li>
				<li><a href="#">基础数据管理</a></li>
				<li><a href="#">${vv:varView('vix_mdm_item')}管理</a></li>
				<li><a href="#">${vv:varView('vix_mdm_item')}清单</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/bom/bomStructAction!goList.action');">${vv:varView('vix_mdm_item')}清单维护</a></li>
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
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_category" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_category" /></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png">
						<s:text name="information" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" /></label> <strong id="lb_search_advanced"><s:text
					name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><s:text name="cmn_name" /><input type="text" class="int" name=""></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn"
				name=""></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<div class="left_content" style="height: 500px;">
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;
            	var setting = {
	            	async : {
		            	enable : true,
		            	url:"${vix}/drp/distributionSystemRelationShipAction!findOrgAndUnitTreeToJson.action",
		                autoParam : [ "id", "treeType" ]
	                },
	                callback : {
	                	onClick : onClick
	                }
	            };
                function onClick(event,treeId,treeNode,clickFlag) {
                	$("#selectId").val(treeNode.id);
                	pager('start',"${vix}/mdm/bom/bomStructAction!goListContent.action?companyId="+treeNode.id,'bomStruct');
                }
                zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
			<input type="hidden" id="selectId" />
		</div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="bomStructInfo"></b> <s:text name='cmn_to' /> <b class="bomStructTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="bomStruct" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="bomStructInfo"></b> <s:text name='cmn_to' /> <b class="bomStructTotalCount"></b>)
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