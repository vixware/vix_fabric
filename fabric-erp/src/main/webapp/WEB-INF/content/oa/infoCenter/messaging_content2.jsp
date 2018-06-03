<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

</script>
<div id="list_content" class="list_content list_bg">
	<div id="list_left">

		<h2>
			<img src="${vix}/common/img/icon_more.gif" alt="" />
			<s:text name="oa_unacknowledged_alert" />
		</h2>
		<ul>
			<li><a href="#">未确定记录</a></li>
		</ul>
		<h2>
			<img src="${vix}/common/img/icon_more.gif" alt="" />
			<s:text name="oa_received_reminder" />
		</h2>
		<ul>
			<li><a href="#">查看已经接受提醒</a></li>
		</ul>
		<h2>
			<img src="${vix}/common/img/icon_more.gif" alt="" />
			<s:text name="oa_sent_reminder" />
		</h2>
		<ul>
			<li><a href="#">已经发送提醒</a></li>
		</ul>
		<h2>
			<img src="${vix}/common/img/icon_more.gif" alt="" />
			<s:text name="oa_remind_queries" />
		</h2>
	</div>
	<!-- left -->
	<div id="list_right">
		<div id="list_switch"></div>
		<div id="list_switch_search"></div>
		<div class="list_search">
			<div class="list_table">
				<table>
					<tr>
						<th><s:text name="oa_sender" /></th>
						<th><s:text name="oa_content" /></th>
						<th><s:text name="oa_transmission_time" /></th>
						<th><s:text name="oa_operating" /></th>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center"><a href="#" style="color: gray;">啸天犬</a></td>
						<td align="center"><a href="#" style="color: gray;">国庆长假通知</a></td>
						<td align="center"><a href="#" style="color: gray;">2013年3月18日</a></td>
						<td align="center">
							<div class="untitled">
								<s:if test="tag != 'choose'">
									<a href="#" onclick="saveOrUpdate('${entity.id}');" title="<s:text name='oa_reply'/>"> <img src="${vix}/common/img/icon_19.gif" />
									</a>
								</s:if>
								<span><img src="${vix}/common/img/icon_untitled.png" alt="" /></span>
								<div class="popup">
									<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
									</strong>
									<p>帮助</p>
								</div>
							</div>
						</td>
					</tr>
				</table>
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
</div>