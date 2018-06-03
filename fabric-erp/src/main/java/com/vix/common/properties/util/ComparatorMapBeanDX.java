package com.vix.common.properties.util;

import java.util.Comparator;
/**
 * 对含Double类型的MapBean进行排序
 * @author guojiangpeng 
 *  //ComparatorMapBeanDouble_DX  中的DX是倒序的意思（从大到小）
 */
//说明  ComparatorMapBeanDoubleB_DX 和  ComparatorMapBeanDouble_DX 的不同：ComparatorMapBeanDouble_DX是针对‘MapBean’操作；而  ComparatorMapBeanDoubleB_DX是针对‘MapBeanDouble’操作
public class ComparatorMapBeanDX implements Comparator<Object>{

	@Override
	public int compare(Object o1,Object o2) {
		MapBean e1=(MapBean)o1;
		MapBean e2=(MapBean)o2;
		if(Double.parseDouble(e1.getValue())>Double.parseDouble(e2.getValue())){     
            return -1;
        }else if(Double.parseDouble(e1.getValue())==Double.parseDouble(e2.getValue())){
            return 0;
        }else{
            return 1;
        }
	}
}
