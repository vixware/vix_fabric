<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
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
 
$("#mountPointForm").validationEngine();
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
function saveOrUpdateMountPoint(){
	if($('#mountPointForm').validationEngine('validate')){
		$.post('${vix}/sales/config/mountPointAction!saveOrUpdate.action',
			{'mountPoint.id':$("#id").val(),
			  'mountPoint.name':$("#name").val(),
			  'mountPoint.code':$("#code").val(),
			  'mountPoint.workTime':$("#workTime").val(),
			  'mountPoint.address':$("#address").val(),
			  'mountPoint.phone':$("#phone").val(),
			  'mountPoint.mobie':$("#mobie").val(),
			  'mountPoint.email':$("#email").val(),
			  'mountPoint.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/config/mountPointAction!goList.action');
			}
		);
	}
}
 
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
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
				<li><a href="#"><img src="${vix}/common/img/crm/site.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">配置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/config/mountPointAction!goList.action');">装载点</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${mountPoint.id}" />
<div class="content">
	<form id="mountPointForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateMountPoint();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/config/mountPointAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="mountPoint.id > 0">
							${mountPoint.name}
						</s:if> <s:else>
							新增装载点
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">名称:</td>
								<td width="35%"><input id="name" name="mountPoint.name" value="${mountPoint.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">编码:</td>
								<td width="40%"><input id="code" name="mountPoint.code" value="${mountPoint.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">工作时间:</td>
								<td><input id="workTime" name="mountPoint.workTime" value="${mountPoint.workTime}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">地址信息:</td>
								<td><input id="address" name="mountPoint.address" value="${mountPoint.address}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">电话:</td>
								<td><input id="phone" name="mountPoint.phone" value="${mountPoint.phone}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">手机:</td>
								<td><input id="mobie" name="mountPoint.mobie" value="${mountPoint.mobie}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">电子邮箱:</td>
								<td><input id="email" name="mountPoint.email" value="${mountPoint.email}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">备注:</td>
								<td><input id="memo" name="mountPoint.memo" value="${mountPoint.memo}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
