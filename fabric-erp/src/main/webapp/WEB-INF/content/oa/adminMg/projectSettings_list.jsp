<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

function deleteEntity(id){
	asyncbox.confirm('确定要删除该计划类型么?','提示信息',function(action){
		if(action == 'ok'){
			$.ajax({
				  url:'${vix}/oa/projectSettingsAction!deleteById.action?id='+id,
				  cache: false,
				  success: function (result){
						showMessage (result);
						setTimeout ("" , 1000);
						loadContent ('${vix}/oa/projectSettingsAction!goListType.action');
					}
			});
		}
	});
}


function saveOrUpdate(id){
	$.ajax({
		  url:"${vix}/oa/projectSettingsAction!goSaveOrUpdate.action?id="+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 80,
					title:"计划类型",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#brandForm').validationEngine('validate')){
								$.post('${vix}/oa/projectSettingsAction!saveOrUpdate.action',
									 {'workPlanType.id':$("#id").val(),									  
									  'workPlanType.orderNumber':$("#orderNumber").val(),						  
									  'workPlanType.name':$("#name").val()						  
									},
									 function (result){
										showMessage (result);
										setTimeout ("" , 1000);
										loadContent ('${vix}/oa/projectSettingsAction!goListType.action');
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/oa/oa_plan.png" alt="" /> <s:text name="oa_xtbg" /></a></li>
				<li><a href="#"><s:text name="oa_administrative_civil" /></a></li>
				<li><a href="#"><s:text name="工作计划" /></a></li>
				<li><a href="#"><s:text name="工作计划类型设置" /></a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name='新建计划类型' /></span></a>
		</p>
	</div>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>内容：<input name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <strong id="lb_search_advanced" class="zh">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>计划名称：<input name="" type="text" class="int" /></label> <label>开始时间：<input name="" type="text" class="int" /></label>
		</div>
	</div>

	<!-- 1 -->
	<div class="box">
		<div id="right">
			<div id="newtab1">
				<div>
					<div class="pagelist drop clearfix">
						<ul>
							<li class="tp"><a href="#">选项</a>
								<ul>
									<li><a href="#">删除</a></li>
									<li><a href="#">邮件</a></li>
									<li><a href="#">批量更新</a></li>
									<li><a href="#">合并</a></li>
									<li><a href="#">添加到目标列表</a></li>
									<li><a href="#">导出</a></li>
								</ul></li>
						</ul>
						<strong>选中:0</strong>
						<div>
							<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
						</div>
					</div>

					<div class="table">
						<table class="list">
							<tr>
								<th width="50">
									<div class="list_check">
										<div class="drop">
											<label><input name="" type="checkbox" value="" /></label>
											<ul>
												<li><a href="#">所有</a></li>
												<li><a href="#">其他</a></li>
												<li><a href="#">式样</a></li>
												<li><a href="#">关闭</a></li>
											</ul>
										</div>
									</div>
								</th>
								<th width="150"><s:text name="序号" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
								<th width="680"><s:text name="类型名称" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
								<th><s:text name="oa_operating" /><a href="#"><img src="${vix}/common/img/arrow.gif" alt="" /></a></th>
							</tr>
							<%int count = 0;%>
							<s:iterator value="pager.resultList" var="entity" status="s">
								<%count++;%>
								<tr>
									<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
									<td><a href="#" style="color: gray;">${entity.orderNumber}</a></td>
									<td><a href="#" style="color: gray;">${entity.name}</a></td>
									<td>
										<div class="untitled">
											<s:if test="tag != 'choose'">
												<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='修改'/>"> <img src="${vix}/common/img/icon_19.gif" />
												</a>
											</s:if>
											<a href="#" onclick="deleteEntity('${entity.id}');" title="删除"> <img src="${vix}/common/img/icon_12.png" />
											</a>
										</div>
									</td>
								</tr>
							</s:iterator>
							<%
							/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
							com.vix.core.web.Pager pager = (com.vix.core.web.Pager) request.getAttribute("pager");
							count = pager.getPageSize() - count;
							request.setAttribute("count", count);
						%>
							<c:forEach begin="1" end="${count}">
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="pagelist drop clearfix">
						<ul>
							<li class="ed"><a href="#">选项</a>
								<ul>
									<li><a href="#">删除</a></li>
									<li><a href="#">邮件</a></li>
									<li><a href="#">批量更新</a></li>
									<li><a href="#">合并</a></li>
									<li><a href="#">添加到目标列表</a></li>
									<li><a href="#">导出</a></li>
								</ul></li>
						</ul>
						<strong>选中:0</strong>
						<div>
							<span><a class="start"></a></span> <span><a class="previous"></a></span> <em>(1-20 到 29)</em> <span><a class="next"></a></span> <span><a class="end"></a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	//面包屑
	if($('.sub_menu').length){
		$("#breadCrumb").jBreadCrumb();
	}
	loadMenuContent('${vix}/oa/main/oaMainAction!goMenuContent.action');
	
	bindSearch();
	function getTotalHeight(){
		if($.browser.msie){return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;}
		else {return self.innerHeight;}
	}
	
	function getTotalHeight(){
		if($.browser.msie){return document.compatMode == "CSS1Compat"? document.documentElement.clientHeight : document.body.clientHeight;}
		else {return self.innerHeight;}
	}
	
	if($('.listlib').length){
		$('.listlib').each(function(){
			$('strong',$('li',$(this)).first()).first().next('ul').css('display','block');
			$('strong',$('li',$(this)).first()).first().addClass('current');
		});
		$('.listlib li').each(function(){
			var $_this = $(this);
			if($('ul',this).length){
				if(!$_this.next('li').length&&!$_this.prev('li').length){
			 		$('strong',$_this).first().css('cursor','default').toggleClass('current');
			 		$('strong',$_this).first().next('ul').css('display','block');
				}
				$('strong',$_this).first().css('cursor','pointer').click(function(){
					$(this).toggleClass('current');
			 		$('ul',$_this).first().slideToggle();
			 	});
			}
		});
		$('.listlib strong,.listlib span').hover(function(){$(this).addClass('al_hover');},function(){$(this).removeClass('al_hover');})
	}

	
	if($('.w_bfb').length>0){
		$('.w_bfb').each(function(){
			$(this).append('<div class="bfb" style="width:'+$('span',this).text()+'"></div>');
		})
	}
	
	$('.login .btn').hover(
	  function () {$(this).css('margin','2px 0 0 51px');},
	  function () {$(this).css('margin','0 0 0 50px');}
	);
	$('#list_switch_search').toggle(
	  function () {
		  $(this).addClass("current");
		  $('#list_search_bar').slideUp('fast');
		},
	  function () {
		  $(this).removeClass("current");
		  $('#list_search_bar').slideDown('fast');
		}
	);
	$('#list_left h2').toggle(
	  function () {
		  $(this).next().slideDown('fast');
		  $(this).addClass("current");
		},
	  function () {
		  $(this).next().slideUp('fast');
		  $(this).removeClass("current");
		}
	);
	$('.version').hover(
	  function () {$(this).children('ul').slideDown('fast');},
	  function () {$(this).children('ul').slideUp('fast');}
	);
	$('.version li').hover(
	  function () {$(this).addClass("hover");},
	  function () {$(this).removeClass("hover");}
	);
	$('.version li').click(
	  function () {
		$('.version b').text($(this).text()); 
	});
	//table
	$("table tr").mouseover(function(){
		$(this).addClass("over");}).mouseout(function(){
			$(this).removeClass("over");})
	$("table tr:even").addClass("alt");
	//switch
	$("#list_switch").toggle(
	  function () {
		$("#list_content").removeClass("list_bg");
		$("#list_switch").addClass("off")
		$("#list_left").addClass("switch")
		$("#list_right").addClass("switch")
	  },
	  function () {
		$("#list_content").addClass("list_bg")
		$("#list_switch").removeClass("off")
		$("#list_left").removeClass("switch")
		$("#list_right").removeClass("switch")
	  });
	
	$(".untitled b img").click(function () {
		var $_this = $(this).parent().parent(".untitled");
		$_this.css({ "position": "relative"});
		$_this.children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $_this.offset();
		if(( bh - pt.top) < 165){$_this.children('.popup').css({ "bottom": "0"});}else{$_this.children('.popup').css({ "top": "-7px"});};
	  });
	  $(".untitled span img").bind('mouseover',function () {
		var $_this = $(this).parent().parent(".untitled");
		$_this.css({ "position": "relative"});
		$_this.children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $_this.offset();
		if(( bh - pt.top) < 165){$_this.children('.popup').css({ "bottom": "0"});}else{$_this.children('.popup').css({ "top": "-7px"});};
	  });
	  $(".untitled").bind('mouseleave',function(){
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
	  });
	  
	  
	$(".close").click(function () {
		$(this).parent().parent().css({ "display": "none"});
		return false;
	});

</script>