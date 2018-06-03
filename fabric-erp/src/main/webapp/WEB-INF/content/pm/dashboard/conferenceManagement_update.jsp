<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript"> 
<!-- 
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	var p = $('#test').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh:function(){
			alert('before refresh');
		}
	});
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
				<li><a href="#"><img src="img/pm_meeting.png" alt="" />供应链</a></li>
				<li><a href="#">项目管理</a></li>
				<li><a href="#">会议管理</a></li>
				<li><a href="#">新增会议管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateCustomer();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a href="#"><img
							width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
							src="${vix}/common/img/dt_print.png"></a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/pm/conferenceManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增会议</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">标题：</td>
											<td><input id="name" name="BO_SPR_CMNM_name" type="text" size="30" /></td>
											<td align="right">地址：</td>
											<td><input id="shortName" name="BO_SPR_CMNM_shortName" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">创建时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th align="right">开始时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">结束时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">状态：</td>
											<td><select name="BO_SPR_CMNM_catalog" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">次数：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
											<th align="right">修改时间：</th>
											<td><input id="retentionsStartDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="retentionsStartDate" value="<fmt:formatDate value='${contract.retentionsStartDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="30" /> <img onclick="showTime('retentionsStartDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">取消会议：</td>
											<td><select name="BO_SPR_CMNM_catalogs" style="width: 50"><option>请选择</option></select></td>
											<td align="right">参与者：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">目标：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
											<td align="right">议程：</td>
											<td><input id="pinYin" name="BO_SPR_CMNM_pinYin" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">附件:</td>
											<td><input name="attach" type="FILE" id="attach" size="30"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->