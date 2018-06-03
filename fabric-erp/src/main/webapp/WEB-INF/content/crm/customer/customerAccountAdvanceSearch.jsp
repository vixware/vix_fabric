<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/crmAction!goCrmHome.action','bg_02');">客户管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/customer/crmCustomerAccountAdvanceSearchAction!goSearch.action','bg_02');">高级查询</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div class="addbox" style="margin-top: 0">
					<div class="addtitle">
						<span class="l"> <img width="16" height="16" style="vertical-align: middle" src="img/address_book.png">&nbsp;分类
						</span>
					</div>
					<ul class="navmenu">
						<li><a href="#" onclick="javascript:tab(10,1,'a',event)">客户管理</a></li>
						<li><a href="#" onclick="javascript:tab(10,2,'a',event)">销售跟踪</a></li>
						<li><a href="#" onclick="javascript:tab(10,3,'a',event)">合同/订单</a></li>
						<li><a href="#" onclick="javascript:tab(10,4,'a',event)">采购</a></li>
						<li><a href="#" onclick="javascript:tab(10,5,'a',event)">费用管理</a></li>
						<li><a href="#" onclick="javascript:tab(10,6,'a',event)">维修工单</a></li>
						<li><a href="#" onclick="javascript:tab(10,7,'a',event)">售后服务</a></li>
						<li><a href="#" onclick="javascript:tab(10,8,'a',event)">市场管理</a></li>
						<li><a href="#" onclick="javascript:tab(10,9,'a',event)">日程/行动</a></li>
						<li><a href="#" onclick="javascript:tab(10,10,'a',event)">库存</a></li>
					</ul>
				</div>
				<div class="addbox">
					<div class="addtitle">
						<span class="l"> <img width="16" height="16" style="vertical-align: middle" src="img/address_book.png">&nbsp;统计
						</span>
					</div>
					<ul class="navmenu">
						<li><a href="#">客户类型分布统计</a></li>
						<li><a href="#">客户类型分布统计</a></li>
						<li><a href="#">客户类型分布统计</a></li>
						<li><a href="#">客户类型分布统计</a></li>
						<li><a href="#">客户类型分布统计</a></li>
						<li><a href="#">客户类型分布统计</a></li>
						<li><a href="#">客户类型分布统计</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" id="a1" style="border: 0;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong> <a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a> <a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a> <a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 id="easyQuery" style="display: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询 <img border="0" alt="切换" src="img/switch.png"><a href="#" onclick="advancedQuery();">切换到简单查询</a>
					</h2>
					<h2 id="advanced" style="display: block;">
						<input type="text" size="30" name="" class="ipt">搜索 <img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;简单查询 <img border="0" alt="切换" src="img/switch.png"><a href="#" onclick="easySeach();">切换到高级查询</a>
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="5%" class="ar bggray">客户编码：</td>
							<td width="95%" colspan="3">
								<!-- <select name=""><option></option></select> -->
								<input name="" type="text" class="ipt" />
							</td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">客户名称：</td>
							<td width="95%" colspan="3"><input name="" type="text" class="ipt ipt30" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">更新日期：</td>
							<td width="95%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">金额：</td>
							<td width="95%" colspan="3">
								<!-- <select name=""><option></option></select> -->
								<input name="" type="text" class="ipt" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="ar bggray">付款方式：</td>
							<td width="95%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">已开发票：</td>
							<td width="95%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">外币备注：</td>
							<td width="95%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">用户组：</td>
							<td width="95%" colspan="3">
								<div id="tree_root"></div> <!-- <ul class="userlist">
                            	<li>
                                	<input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司</label>
                                    <ul>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    </ul>
                                </li>
                            	<li>
                                	<input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司</label>
                                    <ul>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    </ul>
                                </li>
                            	<li>
                                	<input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司</label>
                                    <ul>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    	<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
                                    </ul>
                                </li>
                            </ul> -->
							</td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">对应退货单：</td>
							<td width="95%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="5%" class="ar bggray">已开发票：</td>
							<td width="95%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a2" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析2</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a3" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析3</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a4" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析4</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a5" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析5</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a6" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析6</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a7" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析7</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a8" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析8</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a9" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析9</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="right_content" id="a10" style="border: 0; display: none;">
				<p>
					<b>使用下列分类和搜索，可以对部分数据进行系统分析10</b>
				</p>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·支票</a><a href="#">·现金</a><a href="#">·邮政汇款</a><a href="#">·电汇</a><a href="#">·网上银行</a><a href="#">·其他</a><a href="#">·新客户付款</a><a href="#">·老客户付款</a><a href="#">·押金</a><a href="#">·货款</a><a href="#">·服务费</a><a href="#">·未开发票</a><a
							href="#">·已开发票</a><a href="#">·无需开票</a><a href="#">·只列退款</a><a href="#">·不列退款</a>
					</p>
				</div>
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;高级查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">ID：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">客户：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查客户：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选客户：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">日期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">金额：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">付款方式：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />支票</label> <label><input name="" type="checkbox" value="" />现金</label> <label><input name="" type="checkbox" value="" />邮政汇款</label> <label><input name="" type="checkbox" value="" />电汇</label> <label><input name="" type="checkbox" value="" />网上银行</label> <label><input
									name="" type="checkbox" value="" />其他</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />无需开票</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">外币备注：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">用户组：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>公司
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">对应退货单：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><img src="img/list_icon_21.gif" width="23" height="23" style="vertical-align: middle" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">已开发票：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />新客户付款</label> <label><input name="" type="checkbox" value="" />老客户付款</label> <label><input name="" type="checkbox" value="" />押金</label> <label><input name="" type="checkbox" value="" />货款</label> <label><input name="" type="checkbox" value="" />服务器</label>
								<label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<script type="text/javascript">

function easySeach(){
	if($("#advanced").css("displya") === ""){
		$("#advanced").css("display","none");
		$("#easyQuery").css("display","block");
	}
}

function advancedQuery(){
	if($("#easyQuery").css("displya") === ""){
		$("#easyQuery").css("display","none");
		$("#advanced").css("display","block");
	}
}

var zTree;			
var setting = {
	async: {
		enable: true,
		url:"${vix}/crm/customer/crmCustomerAccountCategoryAction!findTreeToJson.action",
		autoParam:["id", "name=n", "level=lv"]
	},
	callback: {
		onClick: onClick
	}
};
function onClick(event, treeId, treeNode, clickFlag) {
	$("#selectId").val(treeNode.id);
	pager("start","${vix}/crm/customer/crmCustomerAccountAction!goListContent.action?id="+treeNode.id,'customerAccount');
}
zTree = $.fn.zTree.init($("#tree_root"), setting);
</script>