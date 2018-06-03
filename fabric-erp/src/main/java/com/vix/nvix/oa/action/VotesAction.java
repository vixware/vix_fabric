package com.vix.nvix.oa.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

/**
 * 
 * @ClassName: VotesAction
 * @Description: 投票
 * @author bobchen
 * @date 2016年1月12日 上午10:18:46
 *
 */
@Controller
@Scope("prototype")
public class VotesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSaveOrUpdate() {
		return "goSaveOrUpdate";
	}

}
