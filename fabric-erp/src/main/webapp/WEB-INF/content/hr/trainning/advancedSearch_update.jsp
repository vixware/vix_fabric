<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/toolbar.css" rel="stylesheet" type="text/css" />
<link href="css/tree.css" rel="stylesheet" type="text/css" />
<link href="css/base/jquery.ui.all.css" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='css/fullcalendar.print.css' media='print' />
<link href="css/skin_01.css" type="text/css" id="skin" rel="stylesheet">
<link href="css/font_02.css" type="text/css" id="font" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/WdatePicker.js" type="text/javascript"></script>
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript" language="JavaScript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript" language="JavaScript"></script>
<!-- <script src="js/bar.js" type="text/javascript"></script> -->
<script src="js/all.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script>
	$ (function (){
		$ ('#follow').scrollFollow ({
		offset : 50 ,
		container : 'followBox'
		});
	});
	function showPop (ele){
		console.log (ele , ({
		left : $ (ele).offset ().left - $ ('#addtodo').width () ,
		top : $ (ele).offset ().top - 36
		}));
		$ ('#addtodo').css ({
		left : $ (ele).offset ().left - $ ('#addtodoid').outerWidth () ,
		top : $ (ele).offset ().top - 50
		});
		$ ('#addtodoid').show ();
	}
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />打印</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />帮助</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/ma_system.png" alt="" />人力资源</a></li>
				<li><a href="#">组织管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/advancedSearchAction!goSaveOrUpdate.action');">高级查询</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
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
						<span class="l"><img width="16" height="16" style="vertical-align: middle" src="img/common_listx16.png">&nbsp;分类</span>
					</div>
					<ul class="navmenu">
						<li><a href="#" onclick="javascript:tab(10,1,'a',event)">部门查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,2,'a',event)">岗位查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,3,'a',event)">员工查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,4,'a',event)">后备人才查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,5,'a',event)">业务单据查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,6,'a',event)">薪资查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,7,'a',event)">考勤记录查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,8,'a',event)">培训查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,9,'a',event)">招聘查询</a></li>
						<li><a href="#" onclick="javascript:tab(10,10,'a',event)">合同查询</a></li>
					</ul>
				</div>
				<div class="addbox">
					<div class="addtitle">
						<span class="l"><img width="16" height="16" style="vertical-align: middle" src="img/common_listx16.png">&nbsp;统计目录</span>
					</div>
					<ul class="navmenu">
						<li><a href="#">员工统计</a></li>
						<li><a href="#">学历统计</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" id="a1" style="border: 0;">
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;部门查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">部门编码：</td>
							<td width="40%" colspan="3"><select name=""><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">所属组织：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查组织：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选部门：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">启用时间：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">部门名称：</td>
							<td width="40%" colspan="3"><select name=""><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">是否为代管部门：</td>
							<td width="40%" colspan="3"><label><input name="" type="checkbox" value="" />是</label> <label><input name="" type="checkbox" value="" />否</label> <label><input name="" type="checkbox" value="" />【未选】</label></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">上级负责人：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">组织单元类型：</td>
							<td width="40%" colspan="3">
								<ul class="userlist">
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>基准部门
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>销售办公室
									</label>
										<ul>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
											<li><label><input name="" type="checkbox" value="" /> BOSS</label></li>
										</ul></li>
									<li><input name="" type="checkbox" value="" class="ca" /><label class="fileico"> <b class="file-ico"></b>销售组
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
					</table>
				</div>
			</div>
			<div class="right_content" id="a2" style="border: 0; display: none;">

				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;岗位查询
					</h2>
				</div>
				<div class="task_list borderlist ipmn" style="border: 0; margin: 0; padding: 0;">
					<table width="100%" style="margin-bottom: 0;">
						<tr>
							<td width="10%" class="ar bggray">岗位名称：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">上级岗位：</td>
							<td width="40%" colspan="3"><input name="" type="text" class="ipt ipt90" /><br />1.直接查岗位：<input name="" type="text" class="ipt" /> <input type="button" class="btn" value="查询" /> 2.浏览选岗位i：<a href="javascript:;"><img src="img/icon_19.gif" style="vertical-align: middle" /></a></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">岗位有效期：</td>
							<td width="40%" colspan="3">从：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00&nbsp;&nbsp;&nbsp;&nbsp;到：<input type="text" rel="yyyy-MM-dd HH:00" name="" class="ipt time_aoto_input">凌晨00:00
							</td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">所属部门：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
						</tr>
						<tr>
							<td width="10%" class="ar bggray">所属职务：</td>
							<td width="40%" colspan="3"><select name=""><option></option></select><input name="" type="text" class="ipt" /></td>
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;员工查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;后备人才查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;业务单据查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;薪资查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;考勤记录查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;培训查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;招聘查询
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
				<div class="np_left_title">
					<h2 style="float: none;">
						<img src="img/icon_19.gif" style="vertical-align: middle" />&nbsp;合同查询
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
	// 左右拖动
	$ (document).ready (function (){
		$ (function (){
			if ($ ("#left").length){
				$ ("#left").resizable ({
				maxHeight : 680 ,
				minHeight : 680 ,
				maxWidth : 400 ,
				minWidth : 200 ,
				handles : 'e'
				});
			}
		});
	});
	// 弹出层
	$ (document).ready (function (){
		$.fx.speeds._default = 1000;
		$ (function (){
			$ ("#dialog").dialog ({
			autoOpen : false ,
			modal : true
			});
			$ ("#text").click (function (){
				$ ("#dialog").dialog ("open");
				return false;
			});
		});
	});
</script>