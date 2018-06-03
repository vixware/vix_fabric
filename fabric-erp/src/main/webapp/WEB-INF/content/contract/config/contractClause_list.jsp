<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script type="text/javascript">
var name = "";
var contractCode = "";
var clauseContent ="";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadContractCode(){
	contractCode = $('#contract_contractCode').val();
	contractCode=Url.encode(contractCode);
	contractCode=Url.encode(contractCode);
}
function loadClauseContent(){
	clauseContent = $('#contract_clauseContent').val();
	clauseContent=Url.encode(clauseContent);
	clauseContent=Url.encode(clauseContent);
}

/*判断搜索内容是否为空*/
function validateSearch(temp){
	if(null == temp || "" == temp){
		return false;
	}
	return true;
}

/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#contract_contractCode").val("");
		$("#contract_clauseContent").val("");
	}
}

/*改变搜索按钮的显示*/
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	loadContractCode();
	loadClauseContent();
	if(i == 0){
		pager("start","${vix}/contract/contractClauseAction!goSearchList.action?i="+i+"&searchContent="+name,'brand');
	}
	else{
		pager("start","${vix}/contract/contractClauseAction!goSearchList.action?i="+i+"&name="+contractCode+"&clauseContent="+clauseContent,'brand');
	}
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

//状态
function ctmStatus(status){
	orderStatus = status;
	pager("start","${vix}/contract/contractClauseAction!goSingleList.action?name="+status,'brand');
}

//最近使用
function srmRecent(rencentDate){
	pager("start","${vix}/contract/contractClauseAction!goSingleList.action?updateTime="+rencentDate,'brand');
}

function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/contract/contractClauseAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 280,
					title:"合同条款",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/contract/contractClauseAction!saveOrUpdate.action',
									 {'contractClause.id':$("#id").val(),
									  'contractClause.contractCode':$("#contractCode").val(),
									  'contractClause.clauseContent':$("#clauseContent").val(),
									  'contractClause.type':$("#type").val(),
									  'contractClause.mode':$("#mode").val()									  									  
									},
									function(result){
										asyncbox.open({
											modal:true,
											width : 320,
											height : 150,
											title:"<s:text name='合同条款'/>",
											html:result,
											callback : function(action){
												pager("current","${vix}/contract/contractClauseAction!goSingleList.action?name="+name,'brand');
											},
											btnsbar : $.btn.OKCANCEL
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
		  url:'${vix}/contract/contractClauseAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"提示信息",function(action){
				pager("start","${vix}/contract/contractClauseAction!goSingleList.action?name="+name,'brand');
			});
		  }
	});
}
 
loadName();
//载入分页列表数据
pager("start","${vix}/contract/contractClauseAction!goSingleList.action?name="+name,'brand');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#brandOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/contract/contractClauseAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name,'brand');
}

bindSearch();
function currentPager(tag){
	loadName();
	pager(tag,"${vix}/contract/contractClauseAction!goSingleList.action?name="+name,'brand');
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
loadMenuContent('${vix}/contract/top/contractMainAction!goMenuContent.action');
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ctm_contract.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="ctm_agreement" /></a></li>
				<li><a href="#"><s:text name="ctm_initial_settings" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/contract/contractClauseAction!goList.action');"><s:text name="ctm_contract_clause" /></a></li>
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
		<%-- <ul>
			<li><a href="#" id="numBtn"><img src="img/icon_10.png" alt="" /><s:text name="cmn_index"/></a></li>
			<li class="fly">
				<a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_mode"/></a>
				<ul>
					<li><a href="#" onclick="ctmStatus('1')"><img alt="" src="img/icon_10.png"><s:text name="cmn_unapproved_plan"/></a></li>
					<li><a href="#" onclick="ctmStatus('2')"><img alt="" src="img/icon_10.png"><s:text name="cmn_approval_by_plan"/></a></li>
					<li><a href="#" onclick="ctmStatus('3')"><img alt="" src="img/icon_10.png"><s:text name="cmn_approval_in"/></a></li>
				</ul>
			</li>
			<li class="fly">
				<a href="#"><img alt="" src="img/icon_10.png"><s:text name="cmn_recently_used"/></a>
				<ul>
					<li><a href="#" onclick="srmRecent('T1')"><img alt="" src="img/icon_10.png"><s:text name="cmn_three_days"/></a></li>
					<li><a href="#" onclick="srmRecent('T2')"><img alt="" src="img/icon_10.png"><s:text name="cmn_seven_days"/></a></li>
					<li><a href="#" onclick="srmRecent('T3')"><img alt="" src="img/icon_10.png"><s:text name="cmn_month"/></a></li>
					<li><a href="#" onclick="srmRecent('T4')"><img alt="" src="img/icon_10.png"><s:text name="cmn_three_months"/></a></li>
				</ul>
			</li>
		</ul> --%>
		<%-- <div>
			<label>填写内容:<input id="nameS" name="" type="text" class="int" /></label>
			<label>
				<input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" />
				<input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" /></label>
			<strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search'/></strong>
		</div>
		<div class="search_advanced">
			<label><s:text name='编号'/>:<input id="contract_contractCode" name="" type="text" class="int" /></label>
			<label><s:text name='条款内容'/>:<input id="contract_clauseContent" name="" type="text" class="int" /></label>		
			<label>
				<input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" />
				<input name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="contractClauseList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">${c.clauseContent}</a></li>
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
						<li class="tp"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a></span> <span><a class="end" onclick="currentPager('end');"></a></span>
					</div>
				</div>
				<div id="brand" class="table"></div>
				<div class="pagelist drop">
					<ul>
						<li class="ed"><a href="#"><s:text name='cmn_choose' /></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /></a></li>
								<li><a href="#"><s:text name='cmn_mail' /></a></li>
								<li><a href="#"><s:text name="cmn_merger" /></a></li>
								<li><a href="#"><s:text name="cmn_export" /></a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span></strong>
					<div>
						<span><a class="start" onclick="currentPager('start');"></a></span> <span><a class="previous" onclick="currentPager('previous');"></a></span> <em>(<b class="brandInfo"></b> <s:text name='cmn_to' /> <b class="brandTotalCount"></b>)
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