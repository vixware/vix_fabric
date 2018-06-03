/**
 * 
 */
package com.vix.nvix.schedule.adapter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vix.common.scheduling.entity.Calendars;
import com.vix.nvix.common.base.service.IVixntBaseService;
import com.vix.nvix.schedule.adapter.CalendarsAdapter;

/**
 * @author zhanghaibing
 * 
 * @date 2014-5-22
 */
@Controller("defaultCalendarsAdapter")
public class DefaultCalendarsAdapter implements CalendarsAdapter {
	@Autowired
	public IVixntBaseService vixntBaseService;

	@Override
	public void updateCalendars(Calendars calendars) throws Exception {
		vixntBaseService.merge(calendars);
	}
}
