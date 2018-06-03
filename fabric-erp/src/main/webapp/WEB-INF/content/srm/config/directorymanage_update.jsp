<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/sys_precisionControl.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">供应商关系管理 </a></li>
				<li><a href="#">基本设置</a></li>
				<li><a href="#">产品目录管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/srm/directoryManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>产品目录管理 </b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
											<td><input name="" type="text" size="30" />&nbsp;<input name="" type="button" value="选择" class="btn" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">规格：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">数量：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">计量单位：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">价格：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">库存组织：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">库存地址：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">描述：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">供应商：</td>
											<td colspan="3"><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher" style="display: none;">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">退货情况：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">拒收情况：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">按时交货率：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">数量可靠性：</td>
											<td><input name="" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">服务评价：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">信用等级：</td>
											<td><div class="no-border">
													<div id="comboCreditLevel" class="combo"></div>
												</div></td>

											<script type="text/javascript">
												var dd = [];
												for (var i = 1; i <= 5; i++)
													dd.push({
													code : i + '',
													name : 'Employee ' + i
													});
												var cfg = {
												keyField : 'code',
												displayField : 'name',
												multiSelect : false,
												width : 200,
												boxWidth : 200,
												cols : [ {
												field : 'code',
												width : '28%'
												}, {
												field : 'name',
												width : '70%'
												} ],
												data : dd
												};
												var cfg1 = $.extend({}, cfg);
												var cb1 = $('#comboCreditLevel').mac('combo', cfg1);
												$('#get1').click(function() {
													alert(cb1.selected);
												});
												$('#set1').click(function() {
													cb1.select(2);
												});
												var cfg2 = $.extend({}, cfg);
												cfg2.multiSelect = true;
											</script>

										</tr>
									</table>

								</dd>
							</dl>
						</div>
						<div style="margin: 15px 70px; display: none;">
							<textarea id="content" name="content"></textarea>
							<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
							<script type="text/javascript">
								var editor = KindEditor.create('textarea[name="content"]', {
								basePath : '${vix}/plugin/KindEditor/',
								width : 1100,
								height : 300,
								cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
								uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
								fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
								allowFileManager : true
								});
							</script>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />基本项</a></li>
							<li><a onclick="javascript:tab(6,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />库存项</a></li>
							<li><a onclick="javascript:tab(6,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />采购项</a></li>
							<li><a onclick="javascript:tab(6,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />销售项</a></li>
							<li><a onclick="javascript:tab(6,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />成本项</a></li>
							<li><a onclick="javascript:tab(6,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />质量项</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<dd>
							<br />
							<table>
								<tr>
									<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">规格：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">类型：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">净重：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">毛重：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">重量单位：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">上级财务：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">单位体积：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">批文注号：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">合格证号：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">许可证号：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
								</tr>
							</table>
							<br />
						</dd>
					</div>
					<div id="a2" style="display: none;">
						<dd>
							<br />
							<table>
								<tr>
									<td align="right">${vv:varView("vix_mdm_item")}编码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">${vv:varView("vix_mdm_item")}名称：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">最高库存：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">最低库存：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">安全库存：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">默认仓库：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">默认货位：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">替换${vv:varView("vix_mdm_item")}：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">损耗率：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">库存分类：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">盘点周期：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">序列号：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">条形码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">呆滞积压：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">质量检验：</td>
									<td colspan="3"><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
								</tr>
							</table>
							<br />
						</dd>
					</div>
					<div id="a3" style="display: none;">
						<dd>
							<br />
							<table>
								<tr>
									<td align="right">编码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">不足交货容差：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">超量交货容差：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">最小交货数量：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">交货时间偏差：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">收货处理时间：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">配额：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">制造商：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">采购类型：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">允许提前天数：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">允许滞后天数：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">接货仓库：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
								</tr>
							</table>
							<br />
						</dd>
					</div>
					<div id="a4" style="display: none;">
						<dd>
							<br />
							<table>
								<tr>
									<td align="right">编码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">名称：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">销售组织：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">交货工厂：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">最小订购：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">最小发货：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">税率：</td>
									<td colspan="3"><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
								</tr>
							</table>
							<br />
						</dd>
					</div>
					<div id="a5" style="display: none;">
						<dd>
							<br />
							<table>
								<tr>
									<td align="right">编码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">名称：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">计价方式：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">计划价格：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">销售价格：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">参考成本：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">参考售价：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">退税率：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">默认仓库：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">默认货位：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">供应商：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">采购人：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
								</tr>
							</table>
							<br />
						</dd>
					</div>
					<div id="a6" style="display: none;">
						<dd>
							<br />
							<table>
								<tr>
									<td align="right">编码：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">名称：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">质量要求：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">检验说明：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">质检周期：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">周期天数：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">入库检验：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">出库检验：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">退货检验：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">抽检量：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">检验等级：</td>
									<td><input name="" type="text" size="30" /></td>
									<td align="right">取样标准：</td>
									<td><input name="" type="text" size="30" /></td>
								</tr>
								<tr>
									<td align="right">备注：</td>
									<td colspan="3"><textarea class="example" rows="1" style="width: 600px"></textarea></td>
								</tr>
							</table>
							<br />
						</dd>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>