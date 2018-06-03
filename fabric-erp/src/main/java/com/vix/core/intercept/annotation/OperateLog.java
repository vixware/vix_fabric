package com.vix.core.intercept.annotation;


public @interface OperateLog {
    
    public enum OperateLogType  {
        /** 浏览模块记录 */
        HISTORY,
        /** 操作日志 */
        Operate_Log
    };
    
    /**
     * @Title: opreateType
     * @Description: 操作类型
     * his  操作历史
     * log  操作记录
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    OperateLogType opreateType() default OperateLogType.HISTORY;
    
    /**
     * @Title: actionUrl
     * @Description: 本action的 包名 action 方法名的结合  比如 /user/userAction!goList.action
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    String actionMethodUrl() default "";
}
