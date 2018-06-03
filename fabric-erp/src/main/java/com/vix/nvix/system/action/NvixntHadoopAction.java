package com.vix.nvix.system.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 大数据分析
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntHadoopAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String goList1() {
		return "goList1";
	}
	public String goList2() {
		return "goList2";
	}
	public String goList3() {
		return "goList3";
	}
}
