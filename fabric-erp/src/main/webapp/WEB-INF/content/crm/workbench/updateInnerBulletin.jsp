<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#innerBulletinForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
function saveOrUpdateInnerBulletin(){
	if($('#innerBulletinForm').validationEngine('validate')){
		$.post('${vix}/crm/workbench/innerBulletinAction!saveOrUpdate.action',
			{'innerBulletin.id':$("#id").val(),
			  'innerBulletin.title':$("#title").val(),
			  'innerBulletin.isTop':$(":radio[name=isTop][checked]").val(),
			  'innerBulletin.createDate':$("#createDate").val(),
			  'innerBulletin.content': editor.html()
			},
			function(result){
				loadContent('${vix}/crm/workbench/innerBulletinAction!goList.action');
			}
		);
	}
}
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
var editor = KindEditor.create('textarea[name="content"]',{basePath:'${vix}/plugin/KindEditor/',width:700,height:240});
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/bulletin.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">工作台</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/workbench/innerBulletinAction!goList.action');">内部公告</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${innerBulletin.id}" />
<input type="hidden" id="createDate" name="createDate" value="${innerBulletin.createDate}" />
<div class="content">
	<form id="innerBulletinForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateInnerBulletin();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/workbench/innerBulletinAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="innerBulletin.id > 0">
							${innerBulletin.title}
						</s:if> <s:else>
							新增公告
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">主题:</td>
								<td width="35%"><input id="title" name="innerBulletin.title" value="${innerBulletin.title}" type="text" /></td>
								<td width="10%" align="right">是否顶置:</td>
								<td width="40%"><s:if test="innerBulletin.isTop == 0">
										<input type="radio" id="isTop1" name="isTop" value="1" />顶置
									<input type="radio" id="isTop2" name="isTop" value="0" checked="checked" />不顶置
								</s:if> <s:elseif test="innerBulletin.isTop == 1">
										<input type="radio" id="isTop1" name="isTop" value="1" checked="checked" />顶置
									<input type="radio" id="isTop2" name="isTop" value="0" />不顶置
								</s:elseif> <s:else>
										<input type="radio" id="isTop1" name="isTop" value="1" />顶置
									<input type="radio" id="isTop2" name="isTop" value="0" />不顶置
								</s:else></td>
							</tr>
							<tr height="2px;">
								<td colspan="4"></td>
							</tr>
							<tr>
								<td align="right">内容:&nbsp;</td>
								<td colspan="3"><textarea id="content" name="content">
									<s:if test="filePath == ''">
										${innerBulletin.content}
									</s:if>
									<s:else>
										<jsp:include page="${filePath}" />
									</s:else>
								</textarea></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
