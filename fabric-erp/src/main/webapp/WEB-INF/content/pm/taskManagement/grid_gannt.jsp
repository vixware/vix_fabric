<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/toolbar.css" rel="stylesheet" type="text/css" />
<link href="css/tree.css" rel="stylesheet" type="text/css" />
<link href="css/base/jquery.ui.all.css" rel="stylesheet">
<link href="css/asyncBox/asyncbox.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/gantt.css" />
<link rel="stylesheet" href="css/bootstrap.css" />
<link href="css/skin_01.css" type="text/css" id="skin" rel="stylesheet">
<link href="css/font_02.css" type="text/css" id="font" rel="stylesheet">
<link rel='stylesheet' type='text/css' href='css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='css/fullcalendar.print.css' media='print' />
<link rel="stylesheet" href="css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title">
<link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css">
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/all.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/page_02.js" type="text/javascript"></script>
<script src="js/list.js" type="text/javascript"></script>


<script src="${vix}/common/js/jquery.fn.gantt.js"></script>
<script src="${vix}/common/js/bootstrap-tooltip.js"></script>
<script src="${vix}/common/js/bootstrap-popover.js"></script>
<script src="${vix}/common/js/prettify.js"></script>
<script src="${vix}/common/js/gantt.js"></script>

<!--calender JS-->
<script type='text/javascript' src='js/jquery-ui-1.8.17.custom.min.js'></script>
<script type='text/javascript' src='js/fullcalendar.min.js'></script>
<script type='text/javascript' src='js/external-dragging.js'></script>

<!--jqGrid JS-->
<script type="text/javascript" src="js/grid.locale-en.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="js/jqGrid-test.js"></script>

<script type="text/javascript">
	$ ("table tr").mouseover (function (){
		$ (this).addClass ("over");
	}).mouseout (function (){
		$ (this).removeClass ("over");
	});
	$ ("table tr:even").addClass ("alt");
	if ($ ('.an').length > 0){
		$ ('.an').click (function (){
			var $_pare = $ (this).parents (".addbox")
			$ (".anbox" , $_pare).toggle ();
			$ (this).toggleClass ("anopen");
		});
	}
	//面包屑
	if ($ ('.sub_menu').length){
		$ ("#breadCrumb").jBreadCrumb ();
	}
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_order.png" alt="" />项目管理</a></li>
				<li><a href="#">任务管理</a></li>
				<li><a href="#">甘特图</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul class="c_head_tabbtn">
			<li class="current"><a href="javascript:void(0);" onclick="javascript:tab(4,1,'newtab',event)"><img alt="" src="img/icon_10.png">数据分类</a></li>
			<li><a href="javascript:void(0);" onclick="$('#newtab2').removeAttr('style').css('visibility','visible');javascript:tab(4,2,'newtab',event)"><img alt="" src="img/icon_10.png">数据分类</a></li>
			<li><a href="javascript:void(0);" onclick="javascript:tab(4,4,'newtab',event);"><img alt="" src="img/icon_10.png">数据分类</a></li>
		</ul>
		<div>
			<label>内容:<input name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <strong id="search_advanced" class="zh">高级搜索</strong>
		</div>
		<div class="search_advanced">
			<label>名称<input name="" type="text" class="int" /></label> <label>我的项目<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label> <label>名称<input name="" type="text" class="int" /></label> <label>我的项目<input name=""
				type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div id="newtab1">
				<div class="gantt"></div>
			</div>
			<div id="newtab2" style="visibility: hidden; height: 0; overflow: hidden;">
				<div class="external">
					<div id='external-events'>
						<h4>Draggable Events</h4>
						<div class='external-event'>My Event 1</div>
						<div class='external-event'>My Event 2</div>
						<div class='external-event'>My Event 3</div>
						<div class='external-event'>My Event 4</div>
						<div class='external-event'>My Event 5</div>
						<p>
							<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove after drop</label>
						</p>
					</div>
					<div id="calendar"></div>
				</div>
			</div>
			<div id="newtab4" style="display: none;">
				<div class="right_content">
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
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="list">
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
								<th>&nbsp;</th>
								<th>供应链名称<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
								<th>所属城市<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
								<th>计费国<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
								<th>联系电话<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
								<th>用户名<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
								<th>&nbsp;</th>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td><input name="" type="checkbox" value="" /></td>
								<td><img src="img/icon_edit.png" alt="" title="提示：123" class="hand" /></td>
								<td><b>Tortoise Corp</b></td>
								<td>Sunnyvale</td>
								<td>USA</td>
								<td>(989)247-068392</td>
								<td><em>Sally Bronsen</em></td>
								<td>
									<div class="untitled">
										<span><img src="img/icon_untitled.png" alt="" /></span>
										<div class="popup">
											<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
											</strong>
											<p>Launched in January 1998, People's Daily Online is a website built by People's Daily, the official newspaper of the Communist Party of China. Containing</p>
										</div>
									</div>
								</td>
							</tr>
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
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>