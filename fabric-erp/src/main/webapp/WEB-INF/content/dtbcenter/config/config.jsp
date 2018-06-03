<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i><a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a> </i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_option.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<a href="#"><s:text name="ldm_config" /> </a>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span><span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_left_title">
			<h2>配送中心设置</h2>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="position: relative;">
			<!-- <div id="por_switch"></div> -->
			<div id="lnp1">
				<ul class="np_tab clearfix">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">规划参数</a></li>
					<!-- <li><a href="javascript:;" onclick="javascript:tab(6,2,'np',event)">运行参数</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np',event)">费用设定</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np',event)">结算参数</a></li> -->
				</ul>
				<div class="np_clist" id="np1">
					<div class="nt">
						<div class="nt_title">基础选项</div>
						<div class="nt_line">
							<table>
								<tbody>
									<tr class="alt">
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
									</tr>
									<tr class="">
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="nt">
						<div class="nt_title">入库单是否自动带入单价</div>
						<div class="nt_line">
							<table>
								<tbody>
									<tr class="alt">
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
									</tr>
									<tr class="">
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<!-- <div class="nt">
						<div class="nt_title">select</div>
						<div class="nt_line">
							<table>
								<tbody>
									<tr class="alt">
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单</option>
										</select></td>
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单</option>
										</select></td>
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单</option>
										</select></td>
									</tr>
									<tr>
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单</option>
										</select></td>
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单</option>
										</select></td>
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单</option>
										</select></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> -->
					<!-- <div class="nt">
						<div class="nt_title">text</div>
						<div class="nt_line">
							<table>
								<tbody>
									<tr class="alt">
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
									</tr>
									<tr>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> -->
					<!-- <div class="nt">
						<div class="nt_title">fprm</div>
						<div class="nt_line">
							<table>
								<tbody>
									<tr class="alt">
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
										<td class="tr">显示最近</td>
										<td><select name="">
												<option>订单订单订单</option>
										</select></td>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
									</tr>
									<tr>
										<td class="tr"></td>
										<td><label><input style="" name="" value="" type="radio">普通业务必须要有订单</label></td>
										<td class="tr"></td>
										<td><label><input style="" name="" value="" type="checkbox">普通业务必须要有订单</label></td>
										<td class="tr">显示最近</td>
										<td><input name="" value="" class="ipt" type="text"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="nt">
						<div class="nt_title">文字</div>
						<div class="nt_line">
							<p>Node.js是一套用来编写高性能网络服务器的JavaScript工具包，一系列的变化由此开始。比较独特的是，Node.js会假设是在POSIX环境下运行它Linux 或 Mac OS X。如果是在Windows下，那就需要安装MinGW以获得一个仿POSIX的环境。在Node中，Http是首要的。Node为创建http服务器作了优化，所以在网上看到的大部分示例和库都是集中在web上(http框架、模板库等）。</p>
							<div class="textr">
								<a href="javascript:;" class="abtn"><span>了解详情</span> </a>
							</div>
						</div>
					</div> -->
				</div>
				<div class="np_clist" id="np2" style="display: none;">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
						<tbody>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist" id="np3" style="display: none;">
					<div id="projcontent">
						<div id="announce" class="fr">
							<a onclick="javascript:$('#newprojannounce').show();" class="navLinkSmall" href="javascript:;"><u>新建公告</u> </a>
						</div>
						<div class="hide" id="newprojannounce">
							<form class="formparam" onsubmit="javascript:$('#newprojannounce').hide(); return false;" method="POST" name="">
								<div class="txtSmall pl5 pb3 pT20">
									<img border="0" align="absmiddle" class="zpsort_desc" src="img/spacer.gif">&nbsp;<b>新建公告</b>
								</div>
								<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="txtSmall addformdiv">
									<tbody>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td width="25%" valign="top" align="right" class="addformlbl">公告标题 :</td>
											<td valign="top" class="addformlbl"><input type="text" class="addformtextField w95per" name=""></td>
										</tr>
										<tr>
											<td valign="top" align="right" class="addformlbl pt10">描述 :</td>
											<td valign="top" class="pb3 pt10 addformlbl"><textarea class="addformtextField w95per h100" name=""></textarea></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="addformlbl pb3"><span class="txtBold">备注:</span><span class="p5 txtDisabled">只有最新的公告或更新的公告在仪表板显示。</span></td>
										</tr>
									</tbody>
								</table>
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td width="25%">&nbsp;</td>
											<td width="75%" align="left" class=" pt5"><input type="submit" class="buttonNew w100px" name="" value="提交"> &nbsp; <input type="button" onclick="$('#newprojannounce').hide();" class="buttonCancel w70" value="取消"></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
						<br>
						<div id="projbody">
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>

							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="np_clist lineh30" id="np4" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">是否</td>
							<td width="50%">爱好</td>
						</tr>
						<tr>
							<td><input id="radio2" name="group0" type="radio" value="1" class="validate[required] radio" />是 <input id="radio3" name="group0" type="radio" value="0" class="validate[required] radio" />否</td>
							<td><input type="checkbox" value="1" id="maxcheck4" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;读书&nbsp;&nbsp; <input type="checkbox" value="2" id="maxcheck5" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;登山&nbsp;&nbsp; <input type="checkbox" value="3" id="maxcheck6" name="group2"
								class="validate[maxCheckbox[2]] checkbox">&nbsp;游泳&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td width="50%">是否</td>
							<td width="50%">爱好</td>
						</tr>
						<tr>
							<td><input id="radio2" name="group0" type="radio" value="1" class="validate[required] radio" />是 <input id="radio3" name="group0" type="radio" value="0" class="validate[required] radio" />否</td>
							<td><input type="checkbox" value="1" id="maxcheck4" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;读书&nbsp;&nbsp; <input type="checkbox" value="2" id="maxcheck5" name="group2" class="validate[maxCheckbox[2]] checkbox">&nbsp;登山&nbsp;&nbsp; <input type="checkbox" value="3" id="maxcheck6" name="group2"
								class="validate[maxCheckbox[2]] checkbox">&nbsp;游泳&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np5" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np6" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="lnp2" style="display: none;">
				<ul class="np_tab">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np2',event)">Users Status</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np2',event)">Announcement</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np2',event)">add btn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np2',event)">addbtn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,5,'np2',event)">addbtn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,6,'np2',event)">Latest Activities</a></li>
				</ul>
				<div class="np_clist" id="np21">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
						<tbody>

							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist" id="np22" style="display: none;">
					<div id="projcontent">
						<div id="announce" class="fr">
							<a onclick="javascript:$('#newprojannounce').show();" class="navLinkSmall" href="javascript:;"><u>新建公告</u> </a>
						</div>
						<div class="hide" id="newprojannounce">
							<form class="formparam" onsubmit="javascript:$('#newprojannounce').hide(); return false;" method="POST" name="">
								<div class="txtSmall pl5 pb3 pT20">
									<img border="0" align="absmiddle" class="zpsort_desc" src="img/spacer.gif">&nbsp;<b>新建公告</b>
								</div>
								<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="txtSmall addformdiv">
									<tbody>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td width="25%" valign="top" align="right" class="addformlbl">公告标题 :</td>
											<td valign="top" class="addformlbl"><input type="text" class="addformtextField w95per" name=""></td>
										</tr>
										<tr>
											<td valign="top" align="right" class="addformlbl pt10">描述 :</td>
											<td valign="top" class="pb3 pt10 addformlbl"><textarea class="addformtextField w95per h100" name=""></textarea></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="addformlbl pb3"><span class="txtBold">备注:</span><span class="p5 txtDisabled">只有最新的公告或更新的公告在仪表板显示。</span></td>
										</tr>
									</tbody>
								</table>
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td width="25%">&nbsp;</td>
											<td width="75%" align="left" class=" pt5"><input type="submit" class="buttonNew w100px" name="" value="提交"> &nbsp; <input type="button" onclick="$('#newprojannounce').hide();" class="buttonCancel w70" value="取消"></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
						<br>
						<div id="projbody">
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>

							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="np_clist lineh30" id="np23" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np24" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np25" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist" id="np26" style="display: none;">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tbody>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>今天的</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpstatus">status</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">What's this?</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:51 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-13</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">08:00 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-12</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:45 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-11</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">aeffvvrv</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task list</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">adf</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-07</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:27 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task list</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">New Task</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpproject">project</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">Test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="lnp3" style="display: none;">
				<ul class="np_tab">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np3',event)">Announcement</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np3',event)">add btn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np3',event)">addbtn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np3',event)">addbtn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,5,'np3',event)">Latest Activities</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,6,'np3',event)">Users Status</a></li>
				</ul>
				<div class="np_clist" id="np31">
					<div id="projcontent">
						<div id="announce" class="fr">
							<a onclick="javascript:$('#newprojannounce').show();" class="navLinkSmall" href="javascript:;"><u>新建公告</u> </a>
						</div>
						<div class="hide" id="newprojannounce">
							<form class="formparam" onsubmit="javascript:$('#newprojannounce').hide(); return false;" method="POST" name="">
								<div class="txtSmall pl5 pb3 pT20">
									<img border="0" align="absmiddle" class="zpsort_desc" src="img/spacer.gif">&nbsp;<b>新建公告</b>
								</div>
								<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="txtSmall addformdiv">
									<tbody>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td width="25%" valign="top" align="right" class="addformlbl">公告标题 :</td>
											<td valign="top" class="addformlbl"><input type="text" class="addformtextField w95per" name=""></td>
										</tr>
										<tr>
											<td valign="top" align="right" class="addformlbl pt10">描述 :</td>
											<td valign="top" class="pb3 pt10 addformlbl"><textarea class="addformtextField w95per h100" name=""></textarea></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="addformlbl pb3"><span class="txtBold">备注:</span><span class="p5 txtDisabled">只有最新的公告或更新的公告在仪表板显示。</span></td>
										</tr>
									</tbody>
								</table>
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td width="25%">&nbsp;</td>
											<td width="75%" align="left" class=" pt5"><input type="submit" class="buttonNew w100px" name="" value="提交"> &nbsp; <input type="button" onclick="$('#newprojannounce').hide();" class="buttonCancel w70" value="取消"></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
						<br>
						<div id="projbody">
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>

							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="np_clist lineh30" id="np32" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np33" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np34" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist" id="np35" style="display: none;">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tbody>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>今天的</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpstatus">status</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">What's this?</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:51 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-13</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">08:00 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-12</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:45 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-11</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">aeffvvrv</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task list</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">adf</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-07</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:27 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task list</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">New Task</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpproject">project</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">Test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist" id="np36" style="display: none;">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
						<tbody>

							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="lnp4" style="display: none;">
				<ul class="np_tab">
					<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np4',event)">add btn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,2,'np4',event)">addbtn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,3,'np4',event)">addbtn</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,4,'np4',event)">Latest Activities</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,5,'np4',event)">Users Status</a></li>
					<li><a href="javascript:;" onclick="javascript:tab(6,6,'np4',event)">Announcement</a></li>
				</ul>
				<div class="np_clist lineh30" id="np41">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np42" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist lineh30" id="np43" style="display: none;">
					<table width="100%">
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">任务</td>
						</tr>
						<tr>
							<td colspan="2"><textarea class="logtimeaddtext h70 man"></textarea></td>
						</tr>
						<tr>
							<td colspan="2">任务列表</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<optgroup label="内部的">
										<option value="">adf&nbsp;(None)</option>
										<option value="">常规</option>
									</optgroup>
									<optgroup label="外部的">
										<option value="">常规</option>
									</optgroup>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">谁是负责人？</td>
						</tr>
						<tr>
							<td colspan="2"><select class="logtimeaddselect" style="width: 50%;">
									<option value="">任意成员</option>
									<optgroup label="项目成员">
										<option value="">我</option>
									</optgroup>
							</select>&nbsp;<a href="#">分配多个</a></td>
						</tr>
						<tr>
							<td width="50%">开始日期</td>
							<td width="50%">结束日期</td>
						</tr>
						<tr>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
							<td><input type="text" class="ipt time_aoto_input wb80" value=""></td>
						</tr>
						<tr>
							<td colspan="2">优先级</td>
						</tr>
						<tr>
							<td colspan="2"><select style="width: 50%;">
									<option value="None">空</option>
									<option value="Low">低</option>
									<option value="Medium">中</option>
									<option value="High">高</option>
							</select></td>
						</tr>
					</table>
				</div>
				<div class="np_clist" id="np44" style="display: none;">
					<table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tbody>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>今天的</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpstatus">status</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">What's this?</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:51 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-13</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">08:00 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-12</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">06:45 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-11</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">aeffvvrv</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task list</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">adf</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:42 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Uploaded by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:41 </span></td>
							</tr>
							<tr>
								<td valign="top" nowrap="nowrap" colspan="7" class="txtSmallBlack11 pt6 h30"><div class="dashdate w70">
										<b>09-07</b>
									</div></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="bdrbtm4 pt3 pb3"><div class="zptask">Task</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">阿苏大发送</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="dashstatusmsg pl5 pt6 bdrbtm4"><a class="projlinksmall" href="#"><i>ritchiechu</i> </a></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:27 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zptask">Task list</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">New Task</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
							</tr>
							<tr>
								<td width="50" valign="top" align="right" class="pt3 bdrbtm4 pb3"><div class="zpproject">project</div></td>
								<td valign="top" class="pl5 pb5 pt6 bdrbtm4 titleminmax2"><a class="taskDescSmall" href="#">Test</a></td>
								<td valign="top" align="right" class="pl5 pt6 txtDisabled11 pr5 bdrbtm4 userminmax2">Added by</td>
								<td valign="top" class="pl5 pt6 pr3 bdrbtm4 userminmax3"><span><a class="projlinksmall" href="#">ritchiechu</a> </span></td>
								<td valign="top" class="pl3 pt6 bdrbtm4 timeminmax3"><span class="txtDisabled11">10:26 </span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist" id="np45" style="display: none;">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
						<tbody>

							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
							<tr>
								<td width="30" valign="top" class="b1dot p50100"><span><img width="30" height="30" border="0" align="absmiddle" src="img/face_2.jpg"> </span></td>
								<td valign="top" class="dashstatusmsg p5035 b1dot w88per"><a class="dashtdnone" href="#">ritchiechu</a>&nbsp;-&nbsp;What's this?&nbsp;<span class="txtDisabled">09-18-2012 06:51 上午</span> <img align="absmiddle" title="评论" alt="评论" class="zpcomment cursor" src="img/spacer.gif">
									<div class="plbox hide">
										<table width="80%" cellspacing="0" cellpadding="0" class="dashCmtBox">
											<tbody>
												<tr>
													<td colspan="3" class="p5"><form onsubmit="javascript:$(this).parents('div.plbox').hide(); return false;" class="formparam" method="POST">
															<input type="text" value="" class="addformtextField h30 w100per">
														</form></td>
												</tr>
											</tbody>
										</table>
									</div></td>
								<td width="20" class="b1dot" valign="top" align="right"><div class="close_sts hide">
										<img align="absmiddle" title="Delete this status" alt="Delete this status" class="zptrash cpointer" onclick="javascript:$(this).parents('tr').remove();" src="img/spacer.gif">
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="np_clist" id="np46" style="display: none;">
					<div id="projcontent">
						<div id="announce" class="fr">
							<a onclick="javascript:$('#newprojannounce').show();" class="navLinkSmall" href="javascript:;"><u>新建公告</u> </a>
						</div>
						<div class="hide" id="newprojannounce">
							<form class="formparam" onsubmit="javascript:$('#newprojannounce').hide(); return false;" method="POST" name="">
								<div class="txtSmall pl5 pb3 pT20">
									<img border="0" align="absmiddle" class="zpsort_desc" src="img/spacer.gif">&nbsp;<b>新建公告</b>
								</div>
								<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="txtSmall addformdiv">
									<tbody>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td width="25%" valign="top" align="right" class="addformlbl">公告标题 :</td>
											<td valign="top" class="addformlbl"><input type="text" class="addformtextField w95per" name=""></td>
										</tr>
										<tr>
											<td valign="top" align="right" class="addformlbl pt10">描述 :</td>
											<td valign="top" class="pb3 pt10 addformlbl"><textarea class="addformtextField w95per h100" name=""></textarea></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="addformlbl pb3"><span class="txtBold">备注:</span><span class="p5 txtDisabled">只有最新的公告或更新的公告在仪表板显示。</span></td>
										</tr>
									</tbody>
								</table>
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td width="25%">&nbsp;</td>
											<td width="75%" align="left" class=" pt5"><input type="submit" class="buttonNew w100px" name="" value="提交"> &nbsp; <input type="button" onclick="$('#newprojannounce').hide();" class="buttonCancel w70" value="取消"></td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
						<br>
						<div id="projbody">
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>

							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
							<div class="showannounce">
								<div name="" class="projannouncementtitle">adsfasdf</div>
								<div name="" class="txtSmall taj lh20">asdfasdfasd</div>
								<div class="txtDisabled">由&nbsp;ritchiechu&nbsp;在&nbsp;09-18-2012 10:23 上午</div>
								<br>
								<div>
									<span><a class="optionsLink" onclick="javascript:saveOrUpdate();" href="javascript:;">编辑</a> </span>&nbsp;<span class="separator">&nbsp;</span>&nbsp; <a class="optionsLink" href="javascript:;" onclick="javascript:$(this).parents('.showannounce').remove(); return false;">删除</a>
								</div>
								<div class="bdrbtm mb10 pb5"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
				<tr>
					<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" value="取消" class="btn" /></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>