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
		  url:'${vix}/system/objectExpandAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 680,
					height : 540,
					title:"扩展类型",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#objectExpandForm').validationEngine('validate')){
								$.post('${vix}/system/objectExpandAction!saveOrUpdate.action',
									 {'objectExpand.id':$("#id").val(),
									  'objectExpand.code':$("#code").val(),
									  'objectExpand.name':$("#name").val(),
									  'objectExpand.extendedObjectCode':$("#extendedObjectCode").val(),
									  'objectExpand.foreignKey':$("#foreignKey").val(),
									  'objectExpand.isReference':$("#isReference").val(),
									  'objectExpand.isGenerateTable':$("#isGenerateTable").val(),
									  'objectExpand.specTableIsGenerate':$("#specTableIsGenerate").val(),
									  'objectExpand.expandTableName':$("#expandTableName").html(),
									  'objectExpand.status':$(":radio[name=status][checked]").val(),
									  'objectExpand.memo':$("#memo").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											pager("current","${vix}/system/objectExpandAction!goSingleList.action?name="+name,'objectExpand');
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

function deleteById(id){
	var count = 0;
	$("[name='chkId']").each(function(){  
		count += 1;  
	});
	$.ajax({
		  url:'${vix}/system/objectExpandAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='vix_message'/>",function(action){
				pager("current","${vix}/system/objectExpandAction!goSingleList.action?name="+name,'objectExpand');
			});
		  }
	});
}
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
function searchForContent(){
	loadName();
	pager("start","${vix}/system/objectExpandAction!goSingleList.action?name="+name,'objectExpand');
}
 
bindSearch();
loadName();
//载入分页列表数据
pager("start","${vix}/system/objectExpandAction!goSingleList.action?name="+name,'objectExpand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#objectExpandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("current","${vix}/system/objectExpandAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'objectExpand');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/system/objectExpandAction!goSingleList.action?name="+name,'objectExpand');
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
//生成商品扩展字段
function createOrUpdateTable(id){
	asyncbox.confirm('确定要生成数据表吗?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$.ajax({
				url:'${vix}/system/objectExpandAction!createOrUpdateTable.action?id='+id,
				cache: false,
				success: function(html){
					asyncbox.success(html,"<s:text name='vix_message'/>");
					pager('current',"${vix}/system/objectExpandAction!goSingleList.action?name=",'objectExpand');
				}
			});
		}
	});
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
				<li><a href="#"><img src="${vix}/common/img/mdm/objectType.png" alt="" />系统管理</a></li>
				<li><a href="#">系统管理</a></li>
				<li><a href="#">基础数据管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/system/objectExpandAction!goList.action');">对象类型</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete' /></span></a>
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
		                url:"${vix}/common/select/commonSelectOrgAction!findOrgAndUnitTreeToJson.action",
		                autoParam:["treeId","treeType"]
	                },
	                callback : {
	                	onClick : onClick
	                }
	            };
                function onClick(event,treeId,treeNode,clickFlag) {
                	$("#selectId").val(treeNode.id);
                	pager("start","${vix}/system/objectExpandAction!goSingleList.action?companyCode="+treeNode.id,'objectExpand');
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="objectExpandInfo"></b> <s:text name='cmn_to' /> <b class="objectExpandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="objectExpand" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="objectExpandInfo"></b> <s:text name='cmn_to' /> <b class="objectExpandTotalCount"></b>)
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