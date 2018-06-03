package com.vix.nvix.drp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.item.entity.OrderDetaiVo;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 销售利润计算
 * 
 * @类全名 com.vix.nvix.drp.action.VixntStoreSalesMarginAction
 *
 * @author zhanghaibing
 *
 * @date 2016年9月21日
 */
@Controller
@Scope("prototype")
public class VixntStoreSalesMarginAction extends VixntBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("salesDate");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, OrderDetaiVo.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 */
	public void updateOrderDetail() throws Exception {
		Map<String, Object> p = new HashMap<String, Object>();
		List<OrderDetaiVo> orderDetaiVoList = vixntBaseService.getOrderDetaiVoList(p);
		if (orderDetaiVoList != null && orderDetaiVoList.size() > 0) {
			for (OrderDetaiVo orderDetaiVo : orderDetaiVoList) {
				if (orderDetaiVo != null) {
					Map<String, Object> itemparams = new HashMap<String, Object>();
					itemparams.put("itemcode", orderDetaiVo.getItemcode());
					itemparams.put("salesDate", orderDetaiVo.getSalesDate());
					StringBuilder itemhql = standingBookHqlProvider.findOrderDetaiVo(itemparams);
					OrderDetaiVo o = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
					if (o != null && StringUtils.isNotEmpty(o.getId())) {
					} else {
						initEntityBaseController.initEntityBaseAttribute(orderDetaiVo);
						orderDetaiVo = vixntBaseService.merge(orderDetaiVo);
					}
				}
			}
		}
	}
}