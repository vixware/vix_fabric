<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<input type="hidden" id="id" name="id" value="${precisionControl.id}" />
<input type="hidden" id="selectId" name="selectId" />
<!-- left -->
<div class="npcontent clearfix">
	<div id="por_right" class="np_l_r clearfix" style="margin: 15px 0 0 0;">
		<div id="lnp1">
			<ul class="np_tab clearfix">
				<li class="current"><a href="javascript:;" onclick="javascript:tab(6,1,'np',event)">运行参数设置</a></li>
			</ul>
			<div class="np_clist" id="np1">
				<div class="nt">
					<div class="nt_line">
						<table width="100%" class="lineh30">
							<tr>
								<td>存货数量小数位</td>
								<td><input type="text" id="stockSizes" name="stockSizes" value="${ precisionControl.stockSizes}" class="ipt time_aoto_input wb30"></td>
								<td>存货单价小数位</td>
								<td><input type="text" id="stockPrice" name="stockPrice" value="${ precisionControl.stockPrice}" class="ipt time_aoto_input wb30"></td>
							</tr>
							<tr>
								<td>开票单价小数位</td>
								<td><input type="text" id="noteTheUnitPrice" name="noteTheUnitPrice" value="${ precisionControl.noteTheUnitPrice}" class="ipt time_aoto_input wb30"></td>
								<td>件数小数位</td>
								<td><input type="text" id="amount" name="amount" value="${ precisionControl.amount}" class="ipt time_aoto_input wb30"></td>
							</tr>
							<tr>
								<td>换算率小数位</td>
								<td><input type="text" id="conversiorate" name="conversiorate" value="${ precisionControl.conversiorate}" class="ipt time_aoto_input wb30"></td>
								<td>税率小数位</td>
								<td><input type="text" id="taxRate" name="taxRate" value="${ precisionControl.taxRate}" class="ipt time_aoto_input wb30"></td>
							</tr>
						</table>
					</div>
				</div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
					<tr>
						<td colspan="5" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdate($('#selectId').val());" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="button" value="取消" class="btn" /></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</div>