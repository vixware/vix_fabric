/*
 * Copyright (C) 2013 VixSoft Inc.
 *
 * Licensed under the VixSoft License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at license.txt
 * 
 * Purpose:  统一处理针对全局设置参数具有一致过程的管理类
 * Author:   Jackie
 * Date:     2013.07.03
 * Version:  1.0
 *
 */
package com.vix.common.config;

import org.apache.commons.logging.Log;

public class VixGlobalParameterManager {
	
	
	/**
	 * 样例代码：
	 * 全局设置参数：VIX_GLOBAL_ISDEBUG
	 * 参数值：TRUE, FALSE
	 * 		如果参数值为TRUE时,要求所有的模块输出调试信息.FALSE时,则不再输出  
	 */
	public static void processDebugLog(Log log, String moduleName, String loginfo){
		// if( getVixGlobalParameter(VIX_GLOBAL_ISDEBUG)") {
		   log.debug(loginfo); 
		// }
	}
}
