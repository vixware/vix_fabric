<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/* 内容 */
var typeId = "";
function loadTypeId(){
	typeId = $('#typeId').val();
	typeId=Url.encode(typeId);
	typeId=Url.encode(typeId);
}
var typeName = "";
function loadTypeName(){
	typeName = $('#typeName').val();
	typeName=Url.encode(typeName);
	typeName=Url.encode(typeName);
}

function downloadItemcats(channelDistributorId){
	$.ajax({
	  url:'${vix}/business/itemcatsDownloadAction!goDownloadItemcats.action?channelDistributorId='+channelDistributorId,
	  cache: false,
	  success: function(){
	    setTimeout("", 1000);
	    loadContent('${vix}/business/itemcatsDownloadAction!goList.action');
	  }
});
};

//载入分页列表数据
pager("start","${vix}/business/itemcatsDownloadAction!goSingleList.action?1=1",'goodsType');
//排序 
function orderBy(orderField){
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/itemcatsDownloadAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&1=1",'goodsType');
}

function currentPager(tag){
	pager(tag,"${vix}/business/itemcatsDownloadAction!goSingleList.action?1=1",'goodsType');
}

/* 搜索 */
function searchForContent(tag){
	loadTypeId();
	loadTypeName();
	pager("start","${vix}/business/itemcatsDownloadAction!goSingleList.action?1=1&typeId="+typeId+"&typeName="+typeName,'goodsType');
}
function resetForContent(tag){
	$("#typeId").val("");
	$("#typeName").val("");
}

//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	deleteByIds(ids);
}
function deleteByIds(ids) {
		$.ajax({
		url : '${vix}/business/itemcatsDownloadAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/business/itemcatsDownloadAction!goSingleList.action?1=1", 'goodsType');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理</a></li>
				<li><a href="#">商品类目管理 </a></li>
				<li><a href="#">商品类目列表 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
		<ul>
			<li><a href="#"><span>类目下载</span> </a>
				<ul>
					<li><a href="#" onclick="downloadItemcats(0);">全部 </a></li>
					<s:if test="channelDistributorList.size > 0">
						<s:iterator value="channelDistributorList" var="entity" status="s">
							<li><a href="javascript:;" onclick="downloadItemcats('${entity.id}');">${entity.name}</a></li>
						</s:iterator>
					</s:if>
				</ul></li>
		</ul>
		</p>
	</div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>类目管理</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>编码:<input type="text" class="int" id="typeId">
			</label> <label>名称:<input type="text" class="int" id="typeName">
			</label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent(0)"> </label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="goodsTypeList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.typeId}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="goodsTypeInfo"></b> <s:text name='cmn_to' /> <b class="goodsTypeTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="goodsType" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="goodsTypeInfo"></b> <s:text name='cmn_to' /> <b class="goodsTypeTotalCount"></b>)
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