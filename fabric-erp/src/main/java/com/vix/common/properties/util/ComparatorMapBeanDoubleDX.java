package com.vix.common.properties.util;

import java.util.Comparator;
/**
 * 对含Double类型的MapBeanDouble进行排序     
 * @author guojiangpeng 
 *  //ComparatorMapBeanDouble_DX  中的DX是倒序的意思（从大到小）
 */
//说明  ComparatorMapBeanDoubleB_DX 和  ComparatorMapBeanDouble_DX 的不同：ComparatorMapBeanDouble_DX是针对‘MapBean’操作；而  ComparatorMapBeanDoubleB_DX是针对‘MapBeanDouble’操作
public class ComparatorMapBeanDoubleDX implements Comparator<Object>{

	@Override
	public int compare(Object o1,Object o2) {
		MapBeanDouble e1=(MapBeanDouble)o1;
		MapBeanDouble e2=(MapBeanDouble)o2;
		if(e1.getValue()>e2.getValue()){     
            return -1;
        }else if(e1.getValue()==e2.getValue()){
            return 0;
        }else{
            return 1;
        }
	}
}
