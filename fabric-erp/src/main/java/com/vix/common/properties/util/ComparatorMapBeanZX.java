package com.vix.common.properties.util;

import java.util.Comparator;
/**
 * 对含Double类型的MapBean进行排序
 * @author guojiangpeng 
 *  //ComparatorMapBeanDouble_ZX  中的ZX是正序的意思（从小到大）
 */
public class ComparatorMapBeanZX implements Comparator<Object>{

	@Override
	public int compare(Object o1,Object o2) {
		MapBean e1=(MapBean)o1;
		MapBean e2=(MapBean)o2;
		if(Double.parseDouble(e1.getValue())>Double.parseDouble(e2.getValue())){     
            return 1;
        }else if(Double.parseDouble(e1.getValue())==Double.parseDouble(e2.getValue())){
            return 0;
        }else{
            return -1;
        }
	}
}
