<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="reorderPointId" name="reorderPointId" value="${reorderPoint.id}" />
<input type="hidden" id="itemId" name="itemId" value="${itemId}" />
<div class="npcontent clearfix">
	<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
		<div id="lnp1">
			<ul class="np_tab clearfix">
				<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">再订货点规则设置</a></li>
			</ul>
			<div class="np_clist" id="np1">
				<div class="nt_line">
					<div class="nt">
						<table>
							<tr>
								<td align="right">正常到货天数:</td>
								<td><input type="text" class="ipt" name="normalDeliveryDays" id="normalDeliveryDays" value="${reorderPoint.normalDeliveryDays }" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td align="right">每日销量:</td>
								<td><input type="text" class="ipt" name="daySales" id="daySales" value="${reorderPoint.daySales }" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td align="right">安全库存:</td>
								<td><input type="text" class="ipt" name="safetyStock" id="safetyStock" value="${reorderPoint.safetyStock }" style="width: 200px;" /></td>
							</tr>
							<tr>
								<td align="right">再订货点:</td>
								<td><input type="text" class="ipt" name="reorderPoint" id="reorderPoint" value="${reorderPoint.reorderPoint }" style="width: 200px;" /><span><font style="color: red">(再订货点=正常到货天数*每日销量+安全库存)</font></span></td>
							</tr>
						</table>
					</div>
				</div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
					<tr>
						<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdate();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="button" value="取消" class="btn" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>