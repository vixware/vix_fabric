<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />主数据管理</a></li>
				<li><a href="#">主数据维护</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action');">客户</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,'enterPrise');"><span>新增企业客户</span></a> <a href="#" onclick="saveOrUpdate(0,'personal');"><span>新增个人客户</span></a> <a href="#" onclick="deleteByIds();"><span><s:text name='cmn_delete' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div style="margin: 0;" class="addbox">
					<div class="addtitle">百家姓</div>
					<ul class="pxlist">
						<li><a href="#" class="xst">A</a></li>
						<li><a href="#" class="xst">B</a></li>
						<li><a href="#" class="xst">C</a> <a href="#">柴</a> <a href="#">蔡</a> <a href="#">陈</a> <a href="#">迟</a> <a href="#">崔</a></li>
						<li><a href="#" class="xst">D</a> <a href="#">杜</a> <a href="#">戴</a> <a href="#">丁</a> <a href="#">邓</a> <a href="#">朵</a> <a href="#">端</a></li>
						<li><a href="#" class="xst">E</a></li>
						<li><a href="#" class="xst">F</a> <a href="#">樊</a> <a href="#">方</a> <a href="#">付</a> <a href="#">房</a> <a href="#">范</a></li>
						<li><a href="#" class="xst">G</a> <a href="#">古</a> <a href="#">勾</a> <a href="#">龚</a></li>
						<li><a href="#" class="xst">H</a> <a href="#">胡</a> <a href="#">贺</a> <a href="#">黄</a> <a href="#">华</a> <a href="#">韩</a> <a href="#">何</a></li>
						<li><a href="#" class="xst">I</a></li>
						<li><a href="#" class="xst">J</a> <a href="#">冀</a> <a href="#">金</a> <a href="#">姜</a> <a href="#">季</a> <a href="#">技</a></li>
						<li><a href="#" class="xst">K</a> <a href="#">孔</a></li>
						<li><a href="#" class="xst">L</a> <a href="#">刘</a> <a href="#">李</a></li>
						<li><a href="#" class="xst">M</a> <a href="#">马</a> <a href="#">毛</a></li>
						<li><a href="#" class="xst">N</a></li>
						<li><a href="#" class="xst">O</a></li>
						<li><a href="#" class="xst">P</a> <a href="#">彭</a></li>
						<li><a href="#" class="xst">Q</a> <a href="#">秦</a></li>
						<li><a href="#" class="xst">R</a></li>
						<li><a href="#" class="xst">S</a> <a href="#">史</a> <a href="#">孙</a></li>
						<li><a href="#" class="xst">T</a> <a href="#">天</a></li>
						<li><a href="#" class="xst">U</a></li>
						<li><a href="#" class="xst">V</a></li>
						<li><a href="#" class="xst">W</a> <a href="#">王</a> <a href="#">吴</a> <a href="#">武</a></li>
						<li><a href="#" class="xst">X</a></li>
						<li><a href="#" class="xst">Y</a> <a href="#">杨</a></li>
						<li><a href="#" class="xst">Z</a> <a href="#">赵</a> <a href="#">张</a> <a href="#">周</a> <a href="#">曾</a> <a href="#">朱</a></li>
					</ul>
				</div>
				<div class="addbox">
					<div class="addtitle clearfix">
						<span class="l">提示：</span>
					</div>
					<ol class="gray">
						<li>点击字幕（英文姓名）</li>
						<li>点击汉字（姓氏）</li>
						<li>在百家姓应用异常时，“整理”可恢复正常</li>
					</ol>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: 0;">
				<table width="100%">
					<tr>
						<td width="50%" style="vertical-align: top"><table class="list">
								<tbody>
									<tr>
										<th><img src="img/address_book.png" style="vertical-align: middle;" /> 本月纪念日</th>
									</tr>
									<tr>
										<td>【2013-05-06】【王心蕊<img src="img/file.png" />】
										</td>
									</tr>
									<tr>
										<td>【2013-05-06】【王心蕊<img src="img/file.png" />】
										</td>
									</tr>
								</tbody>
							</table></td>
						<td width="50%" style="vertical-align: top"><table class="list">
								<tbody>
									<tr>
										<th><img src="img/address_book.png" style="vertical-align: middle;" /> 下月纪念日</th>
									</tr>
									<tr>
										<td>【2013-05-06】【王心蕊<img src="img/file.png" />】
										</td>
									</tr>
									<tr>
										<td>【2013-05-06】【王心蕊<img src="img/file.png" />】
										</td>
									</tr>
									<tr>
										<td>【2013-05-06】【王心蕊<img src="img/file.png" />】
										</td>
									</tr>
									<tr>
										<td>【2013-05-06】【王心蕊<img src="img/file.png" />】
										</td>
									</tr>
								</tbody>
							</table></td>
					</tr>
				</table>
				<div class="fllist">
					<p>
						<strong><img class="icon" src="img/list_icon_24.gif">数据分类：</strong><a href="#">·全部数据</a><a href="#">·男</a><a href="#">·女</a><a href="#">·主联系人</a><a href="#">·个人客户</a><a href="#">·7天来跟踪（没有日程/任务/历史行动）的联系人</a><a href="#">·100天未来跟踪</a><a href="#">·本月纪念日联系人</a><a href="#">·下月纪念日联系人</a><br />&nbsp;
					</p>
					<p>
						<strong><img src="img/list_icon_24.gif" class="icon">快速查询：</strong>&nbsp;联系人姓名：<input type="text" value="" class="ipt" name=""> <input type="button" value="查询" class="btn" name="">
					</p>
					<p class="gray">提示：联系人姓名自动支持拼音字头查询</p>
				</div>
				<div class="table">
					<table class="list">
						<tbody>
							<tr>
								<th width="50"><div class="list_check">
										<div class="drop">
											<label> <input type="checkbox" value="" name="" style="">
											</label>
											<ul>
												<li><a href="#">所有</a></li>
												<li><a href="#">其他</a></li>
												<li><a href="#">式样</a></li>
												<li><a href="#">关闭</a></li>
											</ul>
										</div>
									</div></th>
								<th>ID</th>
								<th>姓名</th>
								<th>照片</th>
								<th>工作电话</th>
								<th>移动电话</th>
								<th>邮件地址</th>
								<th>对应客户</th>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>1</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>2</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>3</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>4</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>5</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>6</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>7</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>8</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>9</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" value="" name="" style=""></td>
								<td>10</td>
								<td>吴盟</td>
								<td></td>
								<td>010-69955151</td>
								<td>13356656565<img src="img/sphone.png" /></td>
								<td><a href="mailto:276106549@qq.com"><img src="img/mail_4.png" />276106549@qq.com</a></td>
								<td>【海风房地产<img src="img/file.png" />】
								</td>
							</tr>
						</tbody>
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
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<!-- content -->
