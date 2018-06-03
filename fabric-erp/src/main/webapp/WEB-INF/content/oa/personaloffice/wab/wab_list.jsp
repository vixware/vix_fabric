<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<script src="js/page_01.js" type="text/javascript"></script>
<script type="text/javascript">
/* 搜索功能 */
var name1 = "";
var name = "";
var company ="";
var mobileno ="";
var qq ="";
function loadName(){
	name1 = $('#nameS').val();
	name1=Url.encode(name1);
	name1=Url.encode(name1);
}
function loadWabname(){
	name = $('#wab_name').val();
	name = Url.encode(name);
	name = Url.encode(name);
}
function loadWabcompany(){
	company = $('#wab_company').val();
	company = Url.encode(company);
	company = Url.encode(company);
}
function loadWablangCode(){
	langCode = $('#wab_langCode').val();
	langCode = Url.encode(langCode);
	langCode = Url.encode(langCode);
}
function loadWabposition(){
	position = $('#wab_position').val();
	position = Url.encode(position);
	position = Url.encode(position);
}
function loadWabcalls(){
	calls = $('#wab_calls').val();
	calls = Url.encode(calls);
	calls = Url.encode(calls);
}
function loadWabemail(){
	email = $('#wab_email').val();
	email = Url.encode(email);
	email = Url.encode(email);
}
function loadWabmobileno(){
	mobileno = $('#wab_mobileno').val();
	mobileno = Url.encode(mobileno);
	mobileno = Url.encode(mobileno);
}
function loadWabqq(){
	qq = $('#wab_qq').val();
	qq = Url.encode(qq);
	qq = Url.encode(qq);
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
		$("#wab_name").val("");
		$("#wab_company").val("");
		$("#wab_langCode").val("");
		$("#wab_position").val("");
		$("#wab_calls").val("");
		$("#wab_email").val("");
		$("#wab_mobileno").val("");
		$("#wab_qq").val("");
	}
}

/*搜索*/
function searchForContent(i){
	loadName();
	if(i == 0){
		pager("start","${vix}/oa/wabAction!goSearchList.action?i="+i+"&mobileno="+name1,'wab');
	}
}

//高级搜索
function goSearch() {
	$.ajax({
	url : '${vix}/oa/wabAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start", "${vix}/oa/wabAction!goSearchList.action?name=" + $('#name').val() 
						+ "&company=" + $('#company').val()
						+ "&langCode=" + $('#langCode').val()
						+ "&position=" + $('#position').val()
						+ "&calls=" + $('#calls').val()
						+ "&email=" + $('#email').val()
						+ "&mobileno=" + $('#mobileno').val()
						+ "&qq=" + $('#qq').val(), 'wab');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};

$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').animate({height: 'toggle', opacity: 'toggle'},500,function(){$('#number').css('overflow','visible');});
	return false;
});

$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});

$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});

/* 保存个人通讯簿 */
function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/wabAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 540,
					height : 460,
					title:"通讯簿",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/oa/wabAction!saveOrUpdate.action',
									 {'wab.id':$("#id").val(),
									  'wab.name':$("#name").val(),
									  'wab.mobileno':$("#mobileno").val(),
									  'wab.mobileno1':$("#mobileno1").val(),
									  'wab.mobileno2':$("#mobileno2").val(),
									  'wab.mobileno3':$("#mobileno3").val(),
									  'wab.mobileno4':$("#mobileno4").val(),
									  'wab.mobileno5':$("#mobileno5").val(),
									  'wab.langCode':$("#langCode").val(),
									  'wab.calls':$("#calls").val(),
									  'wab.calls1':$("#calls1").val(),
									  'wab.calls2':$("#calls2").val(),
									  'wab.calls3':$("#calls3").val(),
									  'wab.calls4':$("#calls4").val(),
									  'wab.calls5':$("#calls5").val(),
									  'wab.email':$("#email").val(),
									  'wab.email1':$("#email1").val(),
									  'wab.email2':$("#email2").val(),
									  'wab.email3':$("#email3").val(),
									  'wab.email4':$("#email4").val(),
									  'wab.email5':$("#email5").val(),
									  'wab.qq':$("#qq").val(),
									  'wab.qq1':$("#qq1").val(),
									  'wab.qq2':$("#qq2").val(),
									  'wab.qq3':$("#qq3").val(),
									  'wab.qq4':$("#qq4").val(),
									  'wab.qq5':$("#qq5").val(),
									  'wab.custom':$("#custom").val(),
									  'wab.custom1':$("#custom1").val(),
									  'wab.custom2':$("#custom2").val(),
									  'wab.custom3':$("#custom3").val(),
									  'wab.custom4':$("#custom4").val(),
									  'wab.custom5':$("#custom5").val(),
									  'wab.custom6':$("#custom6").val(),
									  'wab.custom7':$("#custom7").val(),
									  'wab.custom8':$("#custom8").val(),
									  'wab.custom9':$("#custom9").val(),
									  'wab.company':$("#company").val(),
									  'wab.position':$("#position").val(),
									  'wab.code':$("#code").val(),
									  'wab.startTime':$("#startTime").val(),
									  'wab.pubType':$('input:radio[name=pubType]').val(),
									  'wab.parentCategory.id':$("#parentId").val(),
									  'wab.wabtype':$('input:radio[name=wabtype]').val(),		
									  'wab.memo':$("#memo").val()
									},
									function(result){
										asyncbox.open({
											modal:true,
											width : 320,
											height : 50,
											title:"<s:text name='通讯簿'/>",
											html:result,
											callback : function(action){
												pager("current","${vix}/oa/wabAction!goSingleList.action?name="+name,'wab');
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


	
function deleteById(id){
	$.ajax({
		  url:'${vix}/oa/wabAction!deleteById.action?id='+id,
		  cache: false,
		  success: function(html){
			asyncbox.success(html,"<s:text name='cmn_message'/>",function(action){
				var treeNode = zTree.getNodeByTId($("#selectIdTreeId").val());
				if(null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes(treeNode, "refresh");
				pager("start","${vix}/oa/wabAction!goSingleList.action?name="+name,'wab');
			});
		  }
	});
}

function searchForRightContent(){
	loadName();
	pager("start","${vix}/oa/wabAction!goSingleList.action?name="+name,'wab');
}
 

 
 
loadName();
//载入分页列表数据
pager("start","${vix}/oa/wabAction!goSingleList.action?name="+name,'wab');
function loadRoot(){
	$('#name').val("");
	$('#selectId').val("");
	pager("start","${vix}/oa/wabAction!goSingleList.action?name=",'wab');
}

//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#wabOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/wabAction!goSingleList.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'wab');
}

bindSearch();
bindSwitch();
var orderStatus = '';
function currentPager(tag){
	loadCode();
	pager(tag,"${vix}/oa/wabAction!goSingleList.action?name="+name+'&orderStatus='+orderStatus,'wab');
}
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});


function showOrder(id){
	$.ajax({
		  url:'${vix}/oa/wabAction!showOrder.action?id='+id,
		  cache: false,
		  success: function(html){
			  $("#mainContent").html(html);
		  }
	});
}


//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');

$(function(){
/*
	$('.tc_texticon').live('click',function(){
		if($.trim($(this).text()) == '+'){
			$(this).closest('td').append('<div class="tc_copay">'+$(this).closest('.tc_copay').html()+'</div>');
			$('.tc_texticon',$(this).closest('td')).text('-');
			$('.tc_texticon:last',$(this).closest('td')).text('+');
		}else if($.trim($(this).text()) == '-'){
			$(this).closest('.tc_copay').remove();
		}
	});
*/
	
	$('.show_line').live('click',function(){
		$(this).closest('tr').next('tr.change_line').toggle();
	});
});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa/oa_wab.png" alt="" /> 协同办公</a></li>
				<li><a href="#">个人办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/wabAction!goList.action?pageNo=${pageNo}');">通讯簿</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="javascript:void(0);" onclick="saveOrUpdate(0);"><span><s:text name='cmn_add' /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<div>
			<label>手机号: <input type="text" class="int" id="nameS" style="width: 150px;"> <input type="button" value="搜索" class="btn" onclick="searchForContent(0);" />
			</label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent(0);" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/oa/wabAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/oa/wabAction!goSingleList.action?parentId="+treeNode.id,"wab");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><img src="img/common_listx16.png" alt="" />
					<s:text name="通讯列表" /></li>
				</ul>
			</div>
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<%-- <ul>
						<li class="tp">
							<a href="#"><s:text name='cmn_operate'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_add'/></a></li>
								<li><a href="#"><s:text name='cmn_update'/></a></li>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#">Actions</a></li>
								<li><a href="#">Actions</a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount1">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="wabPager('start','wab');"></a></span> <span><a class="previous" onclick="wabPager('previous','wab');"></a></span> <em>(<b class="wabInfo"></b> <s:text name='cmn_to' /> <b class="wabTotalCount"></b>)
						</em> <span><a class="next" onclick="wabPager('next','wab');"></a></span> <span><a class="end" onclick="wabPager('end','wab');"></a></span>
					</div>
				</div>
				<div id="wab" class="table"></div>
				<div class="pagelist drop">
					<%-- <ul>
						<li class="ed">
							<a href="#"><s:text name='cmn_choose'/></a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete'/></a></li>
								<li><a href="#"><s:text name='cmn_mail'/></a></li>
								<li><a href="#"><s:text name="cmn_merger"/></a></li>
								<li><a href="#"><s:text name="cmn_export"/></a></li>
							</ul>
						</li>
					</ul>
					<strong><s:text name="cmn_selected"/>:<span id="selectCategoryCount2">0</span></strong> --%>
					<div>
						<span><a class="start" onclick="wabPager('start','wab');"></a></span> <span><a class="previous" onclick="wabPager('previous','wab');"></a></span> <em>(<b class="wabInfo"></b> <s:text name='cmn_to' /> <b class="wabTotalCount"></b>)
						</em> <span><a class="next" onclick="wabPager('next','wab');"></a></span> <span><a class="end" onclick="wabPager('end','wab');"></a></span>
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