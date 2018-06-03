<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
loadMenuContent('${vix}/inventory/inventoryMainAction!goMenuContent.action');
var name = "";
function loadName(){
	name = $('#name').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
var code = "";
function loadCode(){
	code = $('#code').val();
	code=Url.encode(code);
	code=Url.encode(code);
}
function goSaveOrUpdate(id,pageNo){
	$.ajax({
		  url:'${vix}/business/deliveryAreaAction!goSaveOrUpdate.action?id='+id+"&pageNo="+pageNo,
		  cache: false,
		  success: function(html){
		  	$("#mainContent").html(html);
		  }
	});
}
function searchForContent(){
	loadName();
	loadCode();
	pager("start","${vix}/business/deliveryAreaAction!goSingleList.action?code="+code+"&name="+name,'deliveryArea');
}
function resetForContent(){
	 $('#name').val('');
	 $('#code').val('');
}

//载入分页列表数据
pager("start","${vix}/business/deliveryAreaAction!goSingleList.action?1=1",'deliveryArea');
//排序 
function orderBy(orderField){
	var orderBy = $("#deliveryAreaOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/business/deliveryAreaAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy,'deliveryArea');
}

function expressFeeRulesPager(tag){
	pager(tag,"${vix}/business/deliveryAreaAction!goSingleList.action?1=1",'deliveryArea');
}
bindSearch();

//批量删除
function deleteEntitys() {
	var ids = '';
	$("[name='chkId']").each(function() {
		if ($(this).attr('checked')) {
			ids += $(this).val() + ",";
		}
	});
	$.ajax({
		url : '${vix}/business/deliveryAreaAction!deleteByIds.action?ids=' + ids,
		cache : false,
		success : function(html) {
			asyncbox.success(html, "提示信息", function(action) {
				pager("start", "${vix}/business/deliveryAreaAction!goSingleList.action?1=1", 'deliveryArea');
			});
		}
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">物流管理 </a></li>
				<li><a href="#">区域设置 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<a href="#" onclick="goSaveOrUpdate(0);"><span>新增</span>
	</div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b> 帮助</b>
				</strong>
				<p>区域设置</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
		</ul>
		<div>
			<label>编码:<input type="text" class="int" id="code" placeholder="编码"></label> <label>名称:<input type="text" class="int" name="" id="name" placeholder="名称"></label><label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="deliveryAreaList" var="c">
				<li><a href="#" onclick="goSaveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="deliveryAreaInfo"></b> <s:text name='cmn_to' /> <b class="deliveryAreaTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
					</div>
				</div>
				<div id="deliveryArea" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#" onclick="deleteEntitys();"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="merger" /> </a></li>
								<li><a href="#"><s:text name="export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
					<div>
						<span><a class="start" onclick="expressFeeRulesPager('start');"></a> </span> <span><a class="previous" onclick="expressFeeRulesPager('previous');"></a> </span> <em>(<b class="deliveryAreaInfo"></b> <s:text name='cmn_to' /> <b class="deliveryAreaTotalCount"></b>)
						</em> <span><a class="next" onclick="expressFeeRulesPager('next');"></a> </span> <span><a class="end" onclick="expressFeeRulesPager('end');"></a> </span>
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