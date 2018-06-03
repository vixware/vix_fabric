package com.vix.nvix.common.base.hql;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.vix.common.properties.util.MapBean;
import com.vix.common.properties.util.MyTool;
/** SqlReturnDataHandle 一般是把通过手写sql语句返回的List<Object[]> dataList 进行数据处理，SqlReturnDataHandle为多个‘...DataServiceImpl’统计服务，相当于工具类的存在；
 * 所以每个方法不要轻易修改内容，可以新写方法！
 *  (相当于工具类在使用，每个方法被重复调用,所以不要轻易改方法内容)
 *  **/
@Component
@Scope("prototype")
public class SqlReturnDataHandle{
	
	/** 处理结果集 List<Object[]> dataList  这里一般只返回第一列的第一个数据  Integer类型   @throws ParseException **/
	public Integer queryNumToInteger(List<Object[]> dataList) throws ParseException {
		Integer num = 0;
		if(dataList !=null && dataList.size()>0){
			num =  Integer.parseInt(dataList.get(0)[0].toString());
		}
		return num;
	}
	/** 处理结果集 List<Object[]> dataList  这里一般只返回第一列的第一个数据   Double 类型   @throws ParseException **/
	public Double queryNumToDouble(List<Object[]> dataList) throws ParseException {
		Double num = 0.0;
		if(dataList !=null && dataList.size()>0){
			num =  Double.parseDouble(dataList.get(0)[0].toString());
		}
		return num;
	}
	/** 处理结果集 List<Object[]> dataList  这里一般只返回第一列的第1个数据和第一列的第2个数据    都是Double 类型   @throws ParseException **/
	public Double[] queryNumToDoubleTwoValues(List<Object[]> dataList) throws ParseException {
		Double numA = 0.0 , numB = 0.0;
		if(dataList !=null && dataList.size()>0  ){
			Object[] objects = dataList.get(0);
			if(objects !=null && objects.length>=2 && objects[0] !=null && objects[1] !=null ) {
				numA =  Double.parseDouble(objects[0].toString());
				numB =  Double.parseDouble(objects[1].toString());
			}
		}
		return new Double[]{numA,numB};
	}
	/** 处理结果集 List<Object[]> dataList  返回两个数据  Integer在前，Double在后    @throws ParseException **/
	public Object[] queryNumToIntegerAndDouble(List<Object[]> dataList) throws ParseException {
		Object[] arr = new Object[2];
		Integer numA = 0;
		Double numB = 0.0;
		if(dataList !=null && dataList.size()>0){
			numA =  Integer.parseInt(dataList.get(0)[0].toString());
			numB =  Double.parseDouble(dataList.get(0)[1].toString());
		}
		arr[0]=numA;
		arr[1]=numB;
		return arr;
	}
	/** 处理结果集 List(Object[]) dataList  返回两个数据  时间在前，Integer在后  
	 *  例如对sql结果处理：SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d') AS M,Ymd count(*) AS sl FROM pur_plan where 1=1
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param timeArr  必须是 ArrayList(String)类型，第2个参数，并且这里的 timeArr是‘2018-01-01，2018-01-02’年月日格式的时间集合
	 * @return 返回valueArr 也是ArrayList(String)，返回查询出来对应那天应是多少数值；
	 * @throws ParseException **/
	public ArrayList<String> queryViewByYmdTimeAndInteger(List<Object[]> dataList,ArrayList<String> timeArr) throws ParseException {
		ArrayList<String> valueArr = new ArrayList<String>();
		ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(timeArr);
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dayArr.size();x++){
				String hkey = dayArr.get(x).getKey();
				for(int y=0;y<dataList.size();y++){
					String tkey = dataList.get(y)[0].toString();
					if(hkey.equals(tkey)){
						dayArr.set(x, new MapBean(hkey,dataList.get(y)[1].toString()));
					}
				}
			}
		}
		for(int x=0;x<dayArr.size();x++){
			valueArr.add(dayArr.get(x).getValue());
		}
		return valueArr;
	}
	/** 处理结果集 List(Object[]) dataList  返回两个数据  时间在前，Double金钱在后  注意：（已经进行四舍五入保留2位小数，返回String类型）
	 *  例如对sql结果处理：SELECT DATE_FORMAT(CREATETIME, '%Y-%m-%d') AS M,IFNULL(sum(totalAmount), 0) AS sl FROM pur_plan where 1=1
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param timeArr  必须是 ArrayList(String)类型，第2个参数，并且这里的 timeArr是‘2018-01-01，2018-01-02’年月日格式的时间集合
	 * @return 返回valueArr 也是ArrayList(String)，返回查询出来对应那天应是多少钱；，（已经进行四舍五入保留2位小数，返回String类型）
	 * @throws ParseException **/
	public ArrayList<String> queryViewByYmdTimeAndDouble(List<Object[]> dataList,ArrayList<String> timeArr) throws ParseException {
		ArrayList<String> valueArr = new ArrayList<String>();
		ArrayList<MapBean> dayArr = MyTool.getMapBeanZeroArray_ByDateStringArray(timeArr);
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dayArr.size();x++){
				String hkey = dayArr.get(x).getKey();
				for(int y=0;y<dataList.size();y++){
					String tkey = dataList.get(y)[0].toString();
					if(hkey.equals(tkey)){
						dayArr.set(x, new MapBean(hkey,dataList.get(y)[1].toString()));
					}
				}
			}
		}
		for(int x=0;x<dayArr.size();x++){
			/** 注意：（已经进行四舍五入保留2位小数，返回String类型） **/
			valueArr.add(MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(dayArr.get(x).getValue())));
		}
		return valueArr;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，int整型num在后
	 *  例如//查询产品数量top10，sql一般注意查询顺序，前面是字符串类型name后面是整数int类型num,如 select many.ITEMNAME as sqlStrname,,IFNULL(sum(many.AMOUNT),0) as sqlIntnum from ...
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如"10"  
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer queryTopViewBysqlStrnameAndsqlIntnum(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				valueArr.add(dataList.get(x)[1].toString());
			}
		}
		 Integer topNumInt = Integer.parseInt(topNum);
		 if( nameArr.size() < topNumInt ){
			 for(int x=0;x<topNumInt;x++){
				 nameArr.add("");
				 valueArr.add("0");
				 if(nameArr.size() == topNumInt){
					 break;
				 }
			 }
		 }
		 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
			 String temp = nameArr.get(end);
			 nameArr.set(end, nameArr.get(start));
			 nameArr.set(start, temp);
		 }
		 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
			 String temp = valueArr.get(end);
			 valueArr.set(end, valueArr.get(start));
			 valueArr.set(start, temp);
		 }
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，Double类型num在后    (注意：数值四舍五入保留2位小数)
	 *  例如//查询产品数量top10，sql一般注意查询顺序，前面是字符串类型name后面是Double类型num,如 select many.ITEMNAME as sqlStrname,,IFNULL(sum(many.AMOUNT),0) as sqlIntnum from ...
	 *  <br>&nbsp;&nbsp;&nbsp;[queryTopViewBysqlStrnameAndsqlDoublenum方法只用于竖状top柱图显示,从上往下数据值递减,name数组在y轴上]     <br>
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如"10"  
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer queryTopViewBysqlStrnameAndsqlDoublenum(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				valueArr.add(dataList.get(x)[1].toString());
			}
		}
		 Integer topNumInt = Integer.parseInt(topNum);
		 if( nameArr.size() < topNumInt ){
			 for(int x=0;x<topNumInt;x++){
				 nameArr.add("");
				 valueArr.add("0");
				 if(nameArr.size() == topNumInt){
					 break;
				 }
			 }
		 }
		 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
			 String temp = nameArr.get(end);
			 nameArr.set(end, nameArr.get(start));
			 nameArr.set(start, temp);
		 }
		 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
			 String temp = valueArr.get(end);
			 valueArr.set(end, valueArr.get(start));
			 valueArr.set(start, temp);
		 }
		  /** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
		for(int x=0;x<valueArr.size();x++){
			valueArr.set(x, MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(valueArr.get(x))));//数值四舍五入保留2位小数
		}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，Double类型num在后    (注意：数值四舍五入保留2位小数)
	 *  例如//查询产品数量top10，sql一般注意查询顺序，前面是字符串类型name后面是Double类型num,如 select many.ITEMNAME as sqlStrname,,IFNULL(sum(many.AMOUNT),0) as sqlnum from ...order by sqlnum desc limit 0,10
	 *  <br>&nbsp;&nbsp;&nbsp;[topXaxisBysqlStrnameAndsqlDoublenum方法只用于横状top柱图显示,从左往右数据值递减,name数组在x轴上]  一般是bar柱图   <br>
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如"10"  
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer topBarXaxisBysqlStrnameAdsqlDounum(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				valueArr.add(dataList.get(x)[1].toString());
			}
		}
		/** [补齐0;当top10查询数据只有3条时,把剩余的用空字符串和0补齐top10] **/
		 Integer topNumInt = Integer.parseInt(topNum);
		 if( nameArr.size() < topNumInt ){
			 for(int x=0;x<topNumInt;x++){
				 nameArr.add("");
				 valueArr.add("0");
				 if(nameArr.size() == topNumInt){
					 break;
				 }
			 }
		 }
		 /** [/补齐0;当top10查询数据只有3条时,把剩余的用空字符串和0补齐top10] **/
		/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
		for(int x=0;x<valueArr.size();x++){
			valueArr.set(x, MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(valueArr.get(x))));//数值四舍五入保留2位小数
		}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前,Double类型num在中间,string类型groupbyid在后面；3列数据    (注意：数值四舍五入保留2位小数)
	 * 例如：select t.name,count(m.id) as sqlnum,m.saleperson_id from sale_salesorder m inner join hr_org_employee t on m.saleperson_id = t.id and t. name <> '' group by m.saleperson_id order by sqlnum desc limit 0,10
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如"10"  
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer topBysqlstrnameAndsqldounumAndgroupbyid(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();/** nameArr 用于存放Top视图的 名称集合  **/
		ArrayList<String> valueArr = new ArrayList<String>();/** valueArr 用于存放Top视图的 数值集合  **/
		ArrayList<String> groupByIdArr = new ArrayList<String>();/** groupByIdArr 用于存放Top视图的 groupByid字符串集合  **/
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				valueArr.add(dataList.get(x)[1].toString());
				groupByIdArr.add(dataList.get(x)[2].toString());
			}
		}
		 Integer topNumInt = Integer.parseInt(topNum);
		 if( nameArr.size() < topNumInt ){
			 for(int x=0;x<topNumInt;x++){
				 nameArr.add("");
				 valueArr.add("0");
				 groupByIdArr.add("");
				 if(nameArr.size() == topNumInt){
					 break;
				 }
			 }
		 }
		 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
			 String temp = nameArr.get(end);
			 nameArr.set(end, nameArr.get(start));
			 nameArr.set(start, temp);
		 }
		 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
			 String temp = valueArr.get(end);
			 valueArr.set(end, valueArr.get(start));
			 valueArr.set(start, temp);
		 }
		 for (int start = 0, end = groupByIdArr.size() - 1; start < end; start++, end--) {
			 String temp = groupByIdArr.get(end);
			 groupByIdArr.set(end, groupByIdArr.get(start));
			 groupByIdArr.set(start, temp);
		 }
		  /** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
		for(int x=0;x<valueArr.size();x++){
			valueArr.set(x, MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(valueArr.get(x))));//数值四舍五入保留2位小数
		}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"groupArr\":" + gosn.toJson(groupByIdArr) );   
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，Double类型num在后    (注意：数值四舍五入保留2位小数)
	 *  如：select name as sqlname,ifnull(sum(totalamount), 0) as sqlnum from pur_order  group by id order by sqlnum desc  			
	 *  <br><H1><font color="deeppink">【注意_新需求:根据desc sql排行拿出<P>①[top1.name,top2.name,top3.name...top10.name]数组;</P> <P>②[top1.value,top2.value,top3.value...top10.value]保留2位小数数组;</P> 
	 *  <P>③[top1.value+top2.value+top3.value...+top10.value=前top10value的和值doubleA];</P> <P>④[top11.value+top12.value+top13.value...top最后.value=top11及其以后value的和值doubleB];</P>
	 *  <P>⑤最终用于页面top柱图和top饼图同时显示.</P> 】</font><H1><br>
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如topNum 就是分界线top10,也可以自定义指定; 
	 * @return 返回 Map<String, Object>
	 * @throws ParseException **/
	public Map<String, Object> queryTopBarAndTopPieBySqlStrnameAndSqlDoublenum(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();//nameArr:①[top1.name,top2.name,top3.name...top10.name]数组;
		ArrayList<String> valueArr = new ArrayList<String>();//valueArr:②[top1.value,top2.value,top3.value...top10.value]保留2位小数数组;
		Double doubleA = 0.0;//doubleA:③[top1.value+top2.value+top3.value...+top10.value=前top10value的和值doubleA];
		Double doubleB = 0.0;//doubleB:④[top11.value+top12.value+top13.value...top最后.value=top11及其以后value的和值doubleB];
		/** 下面开始计算得出符合需求的nameArr,valueArr,doubleA,doubleB **/
		Integer topNumInt = Integer.parseInt(topNum);
		if(dataList !=null && dataList.size()>0){
				Double tempA = 0.0 , tempB = 0.0 ;
				 int index = 0;
				 for(int x=0;x<topNumInt;x++){
					try {
						nameArr.add(dataList.get(x)[0].toString());//类目名
						valueArr.add(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(   dataList.get(x)[1].toString()   ))  );//钱
						tempA += Double.parseDouble(dataList.get(x)[1].toString());
						index = x;
					}catch (Exception e) {
						break;
					}
				 }
				 doubleA = tempA;
				 for(int x = (index+1);x<dataList.size();x++) {
					 tempB += Double.parseDouble(dataList.get(x)[1].toString());
				 }
				 doubleB = tempB;
	}
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 returnMap.put("nameArr", nameArr);
		 returnMap.put("valueArr", valueArr);
		 returnMap.put("doubleA", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   doubleA   )) );
		 returnMap.put("doubleB", MyTool.formatObjToNumStr(MyTool.roundingTwoDecimal(   doubleB   )) );
		 return returnMap;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，Double类型num在后    (注意：数值四舍五入保留2位小数)
	 *  如：select name as sqlname,ifnull(sum(totalamount), 0) as sqlnum from pur_order  group by id order by sqlnum desc  			【做饼图在‘其他top11top12的和占比多少’的形式就不要加limit sql语句了】
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param otherTopNum 必须是显示top几的数字，string类型  如otherTopNum是"10"，含义是当数据超过10时，把(top10+top11+top12+...)的和展示在top10位置上；  
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer queryPieChartTopBySqlStrnameAndSqlDoublenum(List<Object[]> dataList,String otherTopNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		Integer otherTopNumInt = Integer.parseInt(otherTopNum);
		Integer otherTopNumIntSmall = otherTopNumInt-1;
		if(dataList !=null && dataList.size()>0){
			if( dataList.size() <=otherTopNumIntSmall ){
				for(int x=0;x<dataList.size();x++){
					String money = dataList.get(x)[1].toString();//钱
					String name = dataList.get(x)[0].toString();//类目名
					nameArr.add(name);
					valueArr.add(money);
				}
			}else{
				Double temp = 0.0;
				 for(int x=0;x<otherTopNumIntSmall;x++){
				 	String money = dataList.get(x)[1].toString();//钱
					String name = dataList.get(x)[0].toString();//类目名
					nameArr.add(name);
					valueArr.add(money);
				 }
				 if(dataList.size() == otherTopNumInt ){
				 	String money = dataList.get(otherTopNumIntSmall)[1].toString();//钱
					String name = dataList.get(otherTopNumIntSmall)[0].toString();//类目名
					nameArr.add(name);
					valueArr.add(money);
				 }else{
					 for(int x=otherTopNumIntSmall;x<dataList.size();x++){
						 	String money = dataList.get(x)[1].toString();//钱
							temp += Double.parseDouble(money);
					 }
					 valueArr.add(temp+"");
					 nameArr.add("其他");
				 }
			}
	}
		/** 一般对于echartsPie饼图传过去小数数组,1.00它也只会显示1;**/
		/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
		for(int x=0;x<valueArr.size();x++){
			valueArr.set(x, MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(valueArr.get(x))));//数值四舍五入保留2位小数
		}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，Double类型num在后    
	 *  例如//查询产品数量top10，sql一般注意查询顺序，前面是字符串类型name后面是Double类型num,如 select many.ITEMNAME as sqlStrname,,IFNULL(sum(many.AMOUNT),0) as sqlnum from ...order by sqlnum desc limit 0,10
	 *  <br>&nbsp;&nbsp;&nbsp;[topPieBysqlStrnameAdsqlDounumNoOtherTopNum方法只用于pie饼图显示,特别是本方法没有对超过top10的其他进行合并处理,    没有otherTopNum 这个功能!]   <br>
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/  
	public StringBuffer topPieBysqlStrnameAdsqlDounumNoOtherTopNum(List<Object[]> dataList) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				/** 一般对于echartsPie饼图传过去小数数组,1.00它也只会显示1;**/
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				String value = dataList.get(x)[1].toString();
				valueArr.add(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(  value ))    );//数值四舍五入保留2位小数
			}
		}
		Gson gosn = new Gson();
		StringBuffer returnBufferJson = new StringBuffer();
		returnBufferJson.append("{"+"\"nameArr\":" + gosn.toJson(nameArr));
		returnBufferJson.append(",\"valueArr\":" + valueArr.toString() + "}" );
		return returnBufferJson;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前,Double类型num在中间,string类型groupbyid在后面；3列数据    (注意：数值四舍五入保留2位小数)
	 * 例如：select t.name,count(m.id) as sqlnum,m.saleperson_id from sale_salesorder m inner join hr_org_employee t on m.saleperson_id = t.id and t. name <> '' group by m.saleperson_id order by sqlnum desc limit 0,10
	 * @param dataList 必须是 List(Object[])类型，第1个参数，[这里没有超过top10后把top11,top12进行合并的处理]
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer topPieBynameAdnumAdgroupbyidNoOtherTopNum(List<Object[]> dataList) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();/** nameArr 用于存放Top视图的 名称集合  **/
		ArrayList<String> valueArr = new ArrayList<String>();/** valueArr 用于存放Top视图的 数值集合  **/
		ArrayList<String> groupByIdArr = new ArrayList<String>();/** groupByIdArr 用于存放Top视图的 groupByid字符串集合  **/
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				String value = dataList.get(x)[1].toString();
				valueArr.add(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(  value ))    );//数值四舍五入保留2位小数
				groupByIdArr.add(dataList.get(x)[2].toString());
			}
		}
		Gson gosn = new Gson();
		return new StringBuffer("{"+"\"nameArr\":" + gosn.toJson(nameArr)+",\"groupArr\":" + gosn.toJson(groupByIdArr) + ",\"valueArr\":" + valueArr.toString() + "}");
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前,Double类型num在中间,string类型groupbyid在后面；3列数据    (注意：数值四舍五入保留2位小数)
	 * 例如：select t.name,count(m.id) as sqlnum,m.saleperson_id from sale_salesorder m inner join hr_org_employee t on m.saleperson_id = t.id and t. name <> '' group by m.saleperson_id order by sqlnum desc limit 0,10
	 * @param dataList 必须是 List(Object[])类型，第1个参数，<br><H1><font color="deeppink">[注意：超过top10，top11,top12....合并为top10处理  && 取出每行数据数值的和totalDouble]</font><H1><br>
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer topPieBynameAdnumAdgroupbyHasOtherTopNumAdTotalNum(List<Object[]> dataList,String otherTopNum) throws ParseException {
		ArrayList<String> nameArrPieTen = new ArrayList<String>();/** nameArr 用于存放Top视图的 名称集合  **/
		ArrayList<String> valueArrPieTen = new ArrayList<String>();/** valueArr 用于存放Top视图的 数值集合  **/
		ArrayList<String> groupByIdArr = new ArrayList<String>();/** groupByIdArr 用于存放Top视图的 groupByid字符串集合  **/
		otherTopNum = (StringUtils.isNotEmpty(otherTopNum)) ? otherTopNum : "10";//默认取其他top10
		Integer otherTopNumInt = Integer.parseInt(otherTopNum);
		Integer otherTopNumIntSmall = otherTopNumInt-1;
		String totalDoubleStr = "0.0";//储存和值,str数字类型
		if(dataList !=null && dataList.size()>0){
			Double totalDouble = 0.0;//储存和值,double类型
			if( dataList.size() <=otherTopNumIntSmall ){
				for(int x=0;x<dataList.size();x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( dataList.get(x)[1].toString() ));//钱
					String name = dataList.get(x)[0].toString();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
					totalDouble += Double.parseDouble(money);
					groupByIdArr.add(   dataList.get(x)[2].toString()   );
				}
			}else{
				Double temp = 0.0;
				 for(int x=0;x<otherTopNumIntSmall;x++){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( dataList.get(x)[1].toString() ));//钱
					String name = dataList.get(x)[0].toString();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
					totalDouble += Double.parseDouble(money);
					groupByIdArr.add(   dataList.get(x)[2].toString()   );
				 }
				 if(dataList.size() == otherTopNumInt ){
					String money = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo( dataList.get(otherTopNumIntSmall)[1].toString() ));//钱
					String name = dataList.get(otherTopNumIntSmall)[0].toString();//类目名
					nameArrPieTen.add(name);
					valueArrPieTen.add(money);
					totalDouble += Double.parseDouble(money);
					groupByIdArr.add(   dataList.get(otherTopNumIntSmall)[2].toString()   );
				 }else{
					 for(int x=otherTopNumIntSmall;x<dataList.size();x++){
						 Double money = Double.parseDouble(dataList.get(x)[1].toString());//钱
						 temp += money;
						 totalDouble += money;
						 groupByIdArr.add(   dataList.get(x)[2].toString()   );
					 }
					 valueArrPieTen.add( MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(temp+"")) );//钱
					 nameArrPieTen.add("其他");
				 }
			}
			totalDoubleStr = MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(totalDouble+""));//保留2位小数,如果是科学计数法,就转化为数字型str
			totalDoubleStr = "\""+totalDoubleStr+"\"";
		}
		Gson gosn = new Gson();
 return new StringBuffer("{"+"\"nameArrPieTen\":" + gosn.toJson(nameArrPieTen)+",\"groupArr\":" + gosn.toJson(groupByIdArr) + ",\"valueArrPieTen\":" + valueArrPieTen.toString() + ",\"totalDouble\":"+totalDoubleStr+"}");
	}
	/** 
	 * 例如：select a.name,count(*) from ( select....)a where a.name is not null and a.name <> ''
	 * <br><H1><font color="deeppink"> 查询数据返回json,第一列是字符串name,第二列是数字num这里用小数接收并保留2位小数</font><H1><br>
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer queryJsonByNamestrAdNumstr(List<Object[]> dataList) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();/** valueArr 用于存放Top视图的 数值集合  **/
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				/** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
				String value = dataList.get(x)[1].toString();
				valueArr.add(  MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(  value ))    );//数值四舍五入保留2位小数
			}
		}
		Gson gosn = new Gson();
		return new StringBuffer("{"+"\"nameArr\":" + gosn.toJson(nameArr)+",\"valueArr\":" + valueArr.toString() + "}");
	}
	/** 
	 *  例如 select m.name,m.id from CRM_B_SALEACTIVITY m where 1=1 AND ...
	 *  <br><H1><font color="deeppink">  第一个参数是name 字符串处理,第二个参数是id 字符串处理 返回json如 {"objNameArr":["广告","专题"],"objIdArr":["IDa9f","IDb5T"]} </font><H1><br>
	 * @param 第一个参数是name,第二个参数是id
	 * @return 返回StringBuffer 含有数据json串
	 * @throws ParseException **/
	public StringBuffer queryObjectNameAdIdArrJson(List<Object[]> dataList) throws ParseException {
		ArrayList<String> objNameArr = new ArrayList<String>();
		ArrayList<String> objIdArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				objNameArr.add(dataList.get(x)[0].toString());
				objIdArr.add(dataList.get(x)[1].toString());
			}
		}
		Gson gosn = new Gson();
		return new StringBuffer("{"+"\"objNameArr\":" + gosn.toJson(objNameArr)+",\"objIdArr\":" + gosn.toJson(objIdArr) + "}");
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，Double类型num在后    (注意：数值四舍五入保留2位小数)
	 *  例如//查询产品数量top10，sql一般注意查询顺序，前面是字符串类型name后面是Double类型num,如 select many.ITEMNAME as sqlStrname,,IFNULL(sum(many.AMOUNT),0) as sqlIntnum from ...
	 * <br><H1><font color="deeppink">查询到的数据用map装入返回</font><H1><br>
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如"10"  
	 * @return 返回Map<String, Object>
	 * @throws ParseException **/
	public Map<String, Object> topSqlStrnameAndsqlDoublenumReturnMap(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				valueArr.add(dataList.get(x)[1].toString());
			}
		}
		 Integer topNumInt = Integer.parseInt(topNum);
		 if( nameArr.size() < topNumInt ){
			 for(int x=0;x<topNumInt;x++){
				 nameArr.add("");
				 valueArr.add("0");
				 if(nameArr.size() == topNumInt){
					 break;
				 }
			 }
		 }
		 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
			 String temp = nameArr.get(end);
			 nameArr.set(end, nameArr.get(start));
			 nameArr.set(start, temp);
		 }
		 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
			 String temp = valueArr.get(end);
			 valueArr.set(end, valueArr.get(start));
			 valueArr.set(start, temp);
		 }
		  /** 因为是钱或者数量有可能是小数 ， 所有要四舍五入保留2位小数**/
		for(int x=0;x<valueArr.size();x++){
			valueArr.set(x, MyTool.formatObjToNumStr(MyTool.roundingTwoDecimalTwo(valueArr.get(x))));//数值四舍五入保留2位小数
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("nameArr", nameArr);
		returnMap.put("valueArr", valueArr);
		return returnMap;
	}
	/** 处理结果集 List(Object[]) dataList  与sql语句对应，string类型name在前，int整型num在后
	 *  例如//查询产品数量top10，sql一般注意查询顺序，前面是字符串类型name后面是整数int类型num,如 select many.ITEMNAME as sqlStrname,,IFNULL(sum(many.AMOUNT),0) as sqlIntnum from ...
	 * <br><H1><font color="deeppink">查询到的数据用map装入返回</font><H1><br>
	 * @param dataList 必须是 List(Object[])类型，第1个参数，
	 * @param topNum 必须是显示top几的数字，string类型  如"10"  
	 * @return 返回Map<String, Object> 
	 * @throws ParseException **/
	public Map<String, Object> topSqlStrnameAndsqlIntnumReturnMap(List<Object[]> dataList,String topNum) throws ParseException {
		ArrayList<String> nameArr = new ArrayList<String>();
		ArrayList<String> valueArr = new ArrayList<String>();
		if(dataList !=null && dataList.size()>0){
			for(int x=0;x<dataList.size();x++){
				nameArr.add(dataList.get(x)[0].toString());
				valueArr.add(dataList.get(x)[1].toString());
			}
		}
		 Integer topNumInt = Integer.parseInt(topNum);
		 if( nameArr.size() < topNumInt ){
			 for(int x=0;x<topNumInt;x++){
				 nameArr.add("");
				 valueArr.add("0");
				 if(nameArr.size() == topNumInt){
					 break;
				 }
			 }
		 }
		 for (int start = 0, end = nameArr.size() - 1; start < end; start++, end--) {
			 String temp = nameArr.get(end);
			 nameArr.set(end, nameArr.get(start));
			 nameArr.set(start, temp);
		 }
		 for (int start = 0, end = valueArr.size() - 1; start < end; start++, end--) {
			 String temp = valueArr.get(end);
			 valueArr.set(end, valueArr.get(start));
			 valueArr.set(start, temp);
		 }
		 Map<String, Object> returnMap = new HashMap<String, Object>();
		 returnMap.put("nameArr", nameArr);
		 returnMap.put("valueArr", valueArr);
		 return returnMap;
	}
}