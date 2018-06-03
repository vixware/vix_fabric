<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/core/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//面包屑
	if($('.sub_menu').length){
		$("#breadCrumb").jBreadCrumb();
	}
	var j =1;
	function itemSave(href){
		$(".jd li a").each(function(i){
			$(this).attr("class", "");
		});
		$(href).attr("class", "current");
		j =href.id;
	}
	function step(){
		if(j <6) j ++;
		var next =document.getElementById(j);
		itemSave(next);
	}
	function stepup(){
		if(j >1 &&j <=6) j --;
		var next =document.getElementById(j);
		itemSave(next);
	}
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">主数据管理</a></li>
				<li><a href="#">主数据维护</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');">${vv:varView("vix_mdm_item")}</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="deleteByIds(0,$('#selectId').val());"><span><s:text name='cmn_delete' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTieleFixd">
				<span class="no_line"> <a onclick="saveOrUpdate();" href="javascript:void(0);"><img width="22" height="22" alt="" src="img/icon_add.gif"></a> <a onclick="showDataGrid();" href="javascript:void(0);"><img width="22" height="22" alt="" src="img/icon_add.gif"></a> <a onclick="showTreeGrid();" href="javascript:void(0);"><img
						width="22" height="22" alt="" src="img/icon_add.gif"></a> <a href="#"><img width="22" height="22" alt="" src="img/icon_add.gif"></a> <a href="#"><img width="22" height="22" alt="" src="img/icon_add.gif"></a> <em style="float: none" class="ms"><a href="#"><img width="22" height="22" alt="" src="img/icon_add.gif"></a>
						<ul>
							<li><a href="#"><img src="img/icon_10.png" alt="">信息</a>
								<ul>
									<li><a href="#"><img src="img/icon_10.png" alt="">信息</a>
										<ul>
											<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
											<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
											<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
											<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
										</ul></li>
									<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
									<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
									<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
								</ul></li>
							<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
							<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
							<li><a href="#"><img src="img/icon_10.png" alt="">信息</a></li>
						</ul> </em>
				</span> <strong> <b> <s:if test="item.id > 0">
							${item.name}
						</s:if> <s:else>
							新增${vv:varView("vix_mdm_item")}
						</s:else>
				</b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div id="wizard" class="swMain">
					<ul class="clearfix jd jd6">
						<li><a id="1" href="#" onclick="itemSave(this);" class="current">1 基本信息<span></span></a></li>
						<li><a id="2" href="#" onclick="itemSave(this);">2 邮件激活<span></span></a></li>
						<li><a id="3" href="#" onclick="itemSave(this);">3 信息登记<span></span></a></li>
						<li><a id="4" href="#" onclick="itemSave(this);">4公众号信息<span></span></a></li>
						<li><a id="5" href="#" onclick="itemSave(this);">5 信息登记<span></span></a></li>
						<li><a id="6" href="#" onclick="itemSave(this);">6公众号信息</a></li>
					</ul>
					<div class="order_table" id="1">
						<table style="border: none;">
							<tr>
								<td align="right">活动名称：</td>
								<td><input name="activityName" id="activityName" type="text" size="30" value="${hrRecruitactivitity.activityName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">活动地点：</td>
								<td><input name="activityAddress" id="activityAddress" type="text" size="30" value="${hrRecruitactivitity.activityAddress }" /></td>
							</tr>
							<tr>
								<td align="right">招聘对象：</td>
								<td><input name="recruitingObject" id="recruitingObject" type="text" size="30" value="${hrRecruitactivitity.recruitingObject }" /></td>

							</tr>
							<tr>
								<td align="right">注意事项：</td>
								<td colspan="3"><textarea id="mattersNeedAttention" name="mattersNeedAttention" class="example" rows="3" style="width: 600px">${hrRecruitactivitity.mattersNeedAttention }</textarea></td>
							</tr>
						</table>
					</div>
					<div class="order_table" id="2">
						<table style="border: none;">
							<tr>
								<td align="right">活动名称：</td>
								<td><input name="activityName" id="activityName" type="text" size="30" value="${hrRecruitactivitity.activityName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">活动地点：</td>
								<td><input name="activityAddress" id="activityAddress" type="text" size="30" value="${hrRecruitactivitity.activityAddress }" /></td>
							</tr>
						</table>
					</div>
				</div>
			</dd>
		</dl>
	</div>
	<!--oder-->
	<div class="sub_menu sub_menu_bot">
		<div class="drop">
			<p>
				<a href="#" class="buttonNext" onclick="step();">下一步</a> <a href="#" class="buttonNext" onclick="stepup();">上一步</a><a href="#" onclick="saveOrUpdateCustomerAccount()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?pageNo=${pageNo}');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a
					href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
			</p>
			<ul>
				<li><a href="#"><span>帮助</span></a>
					<ul>
						<li><a href="#">帮助</a></li>
						<li><a href="#">帮助</a></li>
						<li><a href="#">帮助</a></li>
						<li><a href="#">帮助</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>