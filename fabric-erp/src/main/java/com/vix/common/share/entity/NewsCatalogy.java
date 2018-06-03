package com.vix.common.share.entity;


/**
 * @ClassName: NewsCatalogy
 * @Description: 新闻分类
 * @author wangmingchen
 * @date 2012-7-19 下午4:32:14
 *
 */
public class NewsCatalogy extends BaseEntity {
 
    private String name;

    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name;
    }
    
}
