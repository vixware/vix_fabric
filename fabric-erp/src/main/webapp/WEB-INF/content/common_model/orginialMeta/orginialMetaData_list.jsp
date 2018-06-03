<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>

<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name= Url.encode(name);
	name = encodeURI(name);
}
function saveOrUpdate(id){
	$.ajax({
		  url:'${vix}/common/model/orginialBaseMetaDataAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			$("#mainContent").html(html);
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
		  url:'${vix}/common/model/orginialBaseMetaDataAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(result){
			  showMessage(result);
			  setTimeout("", 1000);
				pager("start","${vix}/common/model/orginialBaseMetaDataAction!goSingleList.action?metaDataName="+name,'metaDataCategory');
		  }
	});
}


/**
 * 初始化系统元数据
 */
function initHbmMetadata(){
	$.ajax({
		  url:'${vix}/common/model/orginialBaseMetaDataAction!initSystemMetadata.action',
		  cache: false,
		  success: function(result){
			  showMessage(result);
			  setTimeout("", 1000);
				searchForContent();
		  }
	});
}

function searchForContent(){
	loadName();
	pager("start","${vix}/common/model/orginialBaseMetaDataAction!goSingleList.action?metaDataName="+name,'metaDataCategory');
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/common/model/orginialBaseMetaDataAction!goSingleList.action?metaDataName="+name,'metaDataCategory');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#metaDataCategoryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/common/model/orginialBaseMetaDataAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&metaDataName="+name,'metaDataCategory');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/common/model/orginialBaseMetaDataAction!goSingleList.action?metaDataName="+name,'metaDataCategory');
}


/**
 * 上传
 */
function importXlsFile(){
	$.ajaxFileUpload({
		url : '${vix}/common/model/orginialBaseMetaDataAction!importFile.action',//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status){ //服务器成功响应处理函数
			var data = $.parseJSON(data);
			if(data.success=='1'){
				showMessage(data.msg);
				setTimeout("", 1000);
				searchForContent();
			}else{
				if(typeof(data.error) != 'undefined'){
					if(data.error != ''){
						showErrorMessage(data.error);
						setTimeout("", 1000);
					}else{
						showMessage(data.msg);
						setTimeout("", 1000);
					}
				}
			}
         },
         error : function(data, status, e){//服务器响应失败处理函数
        	 showErrorMessage("上传错误!");
        	 setTimeout("", 1000);
         }
 	});
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
				<li><a href="#"><img src="${vix}/common/img/system/cmn_metadata.png" alt="" />
					<s:text name="system_control" /></a></li>
				<li><a href="#"><s:text name="system_control_basedata" /></a></li>
				<li><a href="#"><s:text name="system_control_basedata_meta" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a> <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete' /></span></a> <a href="#" onclick="$('#fileToUpload').click()"><span>导入</span></a>
			<!-- <a href="#" onclick="initHbmMetadata();"><span>初始化系统元数据</span></a> -->
		</p>
	</div>
</div>

<input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />

<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<%-- <div class="untitled">
			<b><img alt="" src="img/icon_11.png"></b>
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
			<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_category"/></a></li>
			<li class="fly">
				<a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_category"/></a>
				<ul>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
					<li><a href="#"><img alt="" src="img/icon_10.png"><s:text name="information"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> </label>
		</div>
		<%-- <div class="search_advanced">
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
			<label><s:text name="cmn_name"/><input type="text" class="int" name=""></label>
			<label><s:text name="cmn_my_item"/><input type="checkbox" value="" name=""></label>
			<label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name=""><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name=""></label>
		</div> --%>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable" style="height: 500px;">
			<div id="switch_box" class="switch_btn"></div>
			<%--	
			<div class="left_content" style="height:500px;">
				<div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div> --%>

			<div class="left_content" style="height: 500px;">
				<%-- <div style="padding-left:4px;"><img src="${vix}/common/img/file.png" style="padding-right:5px;"/><a href="#" onclick="loadRoot();"><s:text name='rootDirectory'/></a></div> --%>
				<div id="tree_root" class="ztree" style="padding: 0; height: 500px; overflow: scroll;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/common/model/orginialBaseMetaDataAction!findTreeToJson.action",
						autoParam:["id"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/common/model/orginialBaseMetaDataAction!goSingleList.action?categoryId="+treeNode.id+"&metaDataName="+name,'metaDataCategory');
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="metaDataCategoryInfo"></b> <s:text name='cmn_to' /> <b class="metaDataCategoryTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="metaDataCategory" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="metaDataCategoryInfo"></b> <s:text name='cmn_to' /> <b class="metaDataCategoryTotalCount"></b>)
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