<servlet>
       <servlet-name>CXFServlet</servlet-name>
       <servlet-class>org.apache.cxf.transport.servlet.CXFServlet</servlet-class>
       <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
       <servlet-name>CXFServlet</servlet-name>
       <url-pattern>/services/*</url-pattern>
    </servlet-mapping>
    
    
    
    <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:jaxws="http://cxf.apache.org/jaxws"
        xsi:schemaLocation="
                http://www.springframework.org/schema/beans 
                http://www.springframework.org/schema/beans/spring-beans.xsd
                http://www.springframework.org/schema/context
                http://www.springframework.org/schema/context/spring-context.xsd
                http://cxf.apache.org/jaxws 
                http://cxf.apache.org/schemas/jaxws.xsd">
        
                <import resource="classpath:META-INF/cxf/cxf.xml" />  <!-- 这些xml文件在cxf-2.5.0.jar的META-INF目录下-->
                <!--<import resource="classpath:META-INF/cxf/cxf-extension-soap.xml" />
                        警告提示已经废弃了cxf-extension-soap.xml文件-->
                <import resource="classpath:META-INF/cxf/cxf-servlet.xml" />

                
                <!-- 这里配置服务接口，后面描述
                
                    id：指在spring配置的bean的ID.

                    Implementor:指明具体的实现类.

                    Address:指明这个web service的相对地址
                 -->
                    
                    <!-- 考生登录接口配置 -->
                    <!-- 这里service中DAo层的注入必须写在该配置文件中，因为客户端只能看到该配置文件 -->
                      <bean id="examineeLoginServiceImpl" class="xidian.sl.service.impl.webService.ExamineeLoginServiceImpl" >
                        <property name="examineeLoginDAO" ref="examineeLoginDAO"></property>
                    </bean>
                    <jaxws:endpoint id="examineeLoginService" 
                            implementor="#examineeLoginServiceImpl"
                            address="/examineeLogin" />
                           
                            
                      <!-- 考试开始接口配置 -->
                      <bean id="examStartServiceImpl" class="xidian.sl.service.impl.webService.ExamStartServiceImpl" >
                          <property name="examStartDAO" ref="examStartDAO"></property>
                      </bean>
                    <jaxws:endpoint id="examStartService" 
                            implementor="#examStartServiceImpl"
                            address="/examStart" />
                      
                      
                      <!-- 接受答案处理 -->
                      <bean id="answerManageServiceImpl" class="xidian.sl.service.impl.webService.AnswerManageServiceImpl" >
                          <property name="answerManageDAO" ref="answerManageDAO"></property>
                      </bean>
                    <jaxws:endpoint id="answerManageService" 
                            implementor="#answerManageServiceImpl"
                            address="/answerManage" />
                      
                      
                      
                  <!-- 开启tomcat服务器 ，访问http://localhost:8080/WebExam/services/zipTest?wsdl 
                      http://localhost:8080/WebExam是本项目的访问地址

                    services是由于web.xml配置所得，zipTest是由于Spring配置文件中的address属性所得
                  
                  -->
                  
        </beans>
        
        
        <?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:aop="http://www.springframework.org/schema/aop" 
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
           http://www.springframework.org/schema/context 
           http://www.springframework.org/schema/context/spring-context-2.5.xsd
           http://www.springframework.org/schema/aop 
           http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
           http://www.springframework.org/schema/tx 
           http://www.springframework.org/schema/tx/spring-tx-2.5.xsd">
           
    <!-- 配置数据源,导入c3p0-0.9.1.2.jar,mysql-connector-java-5.1.7-bin.jar -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
        destroy-method="close">
        <property name="driverClass">
            <value>net.sourceforge.jtds.jdbc.Driver</value>
        </property>
        <property name="jdbcUrl">
            <value>jdbc:jtds:sqlserver://localhost:9433/web_exam</value>
        </property>
        <property name="user">
            <value>sa</value>
        </property>
        <property name="password">
            <value>123</value>
        </property><!--
        初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3 
        --><property name="initialPoolSize" value="1"/><!--
        连接池中保留的最小连接数。
        --><property name="minPoolSize" value="1"/><!--    
        连接池中保留的最大连接数。Default: 15 
        --><property name="maxPoolSize" value="300"/><!--
        最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 
        --><property name="maxIdleTime" value="60"/><!--    
        当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 
        --><property name="acquireIncrement" value="5"/><!--    
        每60秒检查所有连接池中的空闲连接。Default: 0 
        --><property name="idleConnectionTestPeriod" value="60"/><!--

    
    
        C3P0连接池设定，hibernate自带的连接池性能不行，而去使用第三方软件，如C3P0  
             连接池的最小连接数   
         <property name="hibernate.c3p0.min_size" value = "5"></property>  
             最大连接数   
         <property name="hibernate.c3p0.max_size" value = "30"></property>  
             连接超时时间  
         <property name="hibernate.c3p0.timeout" value = "1800"></property>  
             statemnets缓存大小   
         <property name="hibernate.c3p0.max_statements" value = "100"></property>  
              每隔多少秒检测连接是否可正常使用 ；最后在网上找到一个办法。为hibernate配置连接池，推荐用c3p0，然后配置c3p0的反空闲设置 idle_test_period，（只要小于MySQL的wait timeout即可，这句话后经证实不一定）。    
         <property name="hibernate.c3p0.idle_test_period" value = "121"></property>  
             当池中的连接耗尽的时候，一次性增加的连接数量,默认为3   
         <property name="hibernate.c3p0.acquire_increment" value = "1"></property>  
         <property name="hibernate.c3p0.validate" value = "true"></property>  
    --></bean>
    
    <bean name="hibernateProperties"
        class="org.springframework.beans.factory.config.PropertiesFactoryBean">
        <property name="properties">
            <props>
                
                <prop key="hibernate.dialect">org.hibernate.dialect.SQLServerDialect</prop>
                <!--<prop key="hibernate.hbm2ddl.auto">update</prop> 
                --><prop key="hibernate.cache.provider_class">
                    org.hibernate.cache.NoCacheProvider
                </prop>
                <!--<prop key="hibernate.show_sql">true</prop>
            --></props>
        </property>
    </bean>
    
     <!--定义了Hibernate的SessionFactory -->
    <bean id="sessionFactory"
        class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="hibernateProperties" ref="hibernateProperties" />
        <property name="mappingLocations">
            <list>
                <value>classpath*:xidian/sl/entity/*.hbm.xml</value>
                
            </list>
        </property>
    </bean>
    


    <!-- 事务配置 -->
    <bean id="transactionManager"
        class="org.springframework.orm.hibernate3.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>
    
    <bean id="transactionInterceptor" class="org.springframework.transaction.interceptor.TransactionInterceptor">
        <!--  事务拦截器bean需要依赖注入一个事务管理器 -->
        <property name="transactionManager" ref="transactionManager"/>
        <property name="transactionAttributes">
            <!--  下面配置事务属性-->
            <props>
            
            <!--PROPAGATION_REQUIRE规则表示:在bean中所有以get开头的方法,当抛出异   
                常时,自动回滚,并只读，其他异常自动回滚-->  
            
                   <!-- 事务处理，如果没有跟这里匹配的名，系统会默认是readOnly -->
                   <prop key="get*">PROPAGATION_REQUIRED,readOnly</prop>
                <prop key="*">PROPAGATION_REQUIRED,-Exception</prop>
                <prop key="*">ISOLATION_SERIALIZABLE</prop>  
            </props>
        </property>
    </bean>
    
    
    <!-- 定义BeanNameAutoProxyCreator-->
    <bean class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
        <!--  指定对满足哪些bean name的bean自动生成业务代理 -->
        <property name="beanNames">
            <!--  下面是所有需要自动创建事务代理的bean-->
            <list>
                <value>*Service</value>
                <value>*DAO</value>
            </list>
            <!--  此处可增加其他需要自动创建事务代理的bean-->
        </property>
        <!--  下面定义BeanNameAutoProxyCreator所需的事务拦截器-->
        <property name="interceptorNames">
            <list>
                <!-- 此处可增加其他新的Interceptor -->
                <value>transactionInterceptor</value> 
            </list>
        </property>
    </bean>
    <!-- 事务配置结束 -->
    
</beans>


package xidian.sl.service.webService;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
//@SOAPBinding(style = Style.RPC)这个必须要加否则在客户端加载服务时会出现参数的错误
public interface ExamStartService {
    /*
     * 客户端传过来的应该是学生的学号和考试的名字
     * **/
    public String startExam(@WebParam(name = "stuNum")String stuNum, 
            @WebParam(name = "examName")String examName, @WebParam(name = "sign")String sign, @WebParam(name = "ipaddr")String ipaddr)throws Exception;
    
    
}


package xidian.sl.service.impl.webService;

import java.util.List;
import java.util.Random;

import javax.jws.WebService;

import xidian.sl.dao.webService.ExamStartDAO;
import xidian.sl.entity.Exam;
import xidian.sl.entity.Examinee;
import xidian.sl.entity.Paper;
import xidian.sl.service.webService.ExamStartService;
import xidian.sl.util.FormatDate;
@WebService(endpointInterface = "xidian.sl.service.webService.ExamStartService")
public class ExamStartServiceImpl implements ExamStartService {
    
    private ExamStartDAO examStartDAO;

    public ExamStartDAO getExamStartDAO() {
        return examStartDAO;
    }

    public void setExamStartDAO(ExamStartDAO examStartDAO) {
        this.examStartDAO = examStartDAO;
    }

    /**
     * @author shenliang 2011-11-29
     * 客户端点击开始考试后的处理（客户端传过来的是考生的学号，和本场考试的考试名）
     * 1.###先根据客户端传过来的参数sign{"false","true"}，false表示在客户端还没有压缩包的存在，true表示已经有压缩包的存在
     * 若为false，则先判断该考生是刚开始考生还是再次的请求（查询该考生表中是否已经存在开始考试的时间），若无，则为第一次考试，
     * 将开始考试的时间更新进去，并返回success,若已经有考试开始时间，则表示该考生已更换电脑，
     * 则需要将该考生原先的试卷包和考试的剩余时间发送过去，
     * 若为true，表示该考试已开始考生并且是有压缩包的，此时服务器端只需要将该考试的剩余时间进行返回即可
     * @throws Exception 
     * 
     * */
    public String startExam(String stuNum, String examName, String sign, String ipaddr) throws Exception {
        System.out.println("成功了！    "+"学号="+stuNum+" 考试="+examName+" 标记="+sign+" ip="+ipaddr);
        //查找该场考试
        List<Exam> examList = examStartDAO.getExamList(examName);
        //考试的名字必须是唯一的
        Exam exam = examList.get(0);
        //判断该场考试是否有ip范围的限制
        String ipAddr = exam.getExamIpaddr();
        if(!"未设定".equals(ipAddr)){
            String[] ips = ipAddr.split("-");         //将ip地址切分为开始和结束ip
            Boolean boo = isBetween(ipaddr, ips[0], ips[1]);
            if(boo == false){
                //考试机所属ip不在指定的范围
                System.out.println("不合法的ip地址");
                return "wrongfulIP";
            }else{
                System.out.println("合法的ip地址");
            }
        }
        
        //查找该考生
        List<Examinee> examineeList = examStartDAO.getExamineeByNumAndExamName(stuNum, examName);
        //该集合中只可能有一条记录
        Examinee examinee = examineeList.get(0);
        //此处还要判断该考生是否已经完成考试了 防止第二次重复考试
        if(!"???".equals(examinee.getExamEndtime())){
            return "finished";
        }
        
        if("false".equals(sign)){                         //false表示客户端还没有压缩包
            System.out.println("进入false");
            //查询该考生的开始考试时间是否存在
            if( !"???".equals(examinee.getExamStartime()) ){                  //该考生已经开始考试，但中途更换了电脑
                System.out.println("已经开始考试了，但是换机子了");
                //更新考生表，将机子的变换加1
                examStartDAO.updateChangeComNum(examinee);
                //将ip更新进去
                examinee.setExamineeIpaddr(ipaddr);
                examStartDAO.updateExaminee(examinee);
                
                String examLong = exam.getExamLong();  //获得考试的时长
                //则将剩余的时间返回,先计算使用了的时间，返回的是一个字符串，分#秒
                String examUserdTime = FormatDate.examRemainTime(examinee.getExamStartime(), FormatDate.getFormateDateAll());  //param:1.考试开始时间，2.现在的时间
                String[] splitTime = examUserdTime.split("#");
                //这里先忽略秒
                String examRemainTime = String.valueOf(Integer.valueOf(examLong) - Integer.valueOf(splitTime[0]));
                /**
                 * 将下载的操作交给客户端处理了，这边只需要传递试卷名参数给客户端即可
                 * */
                //将该考生原先的压缩包再次发送,更加该考生得到该试卷包
                //String paperPath = examinee.getPaper().getZipPath()+examinee.getPaper().getZipName();
                
                //调用FileDownload类中的downloadFile()方法
                //FileDownload.downloadFile(paperPath);
                
                //返回的  剩余时间#试卷名
                StringBuilder str = new StringBuilder(examRemainTime);
                str.append("#");
                str.append(examinee.getPaper().getZipName());
                return str.toString();   //返回考试的剩余时间
            }else{
                System.out.println("未开始过考试");
                //更新ip
                examinee.setExamineeIpaddr(ipaddr);
                examStartDAO.updateExaminee(examinee);
                
                //得到本场考试的所有试卷编号
                List<Integer> paperIdList = examStartDAO.getPaperId(examName);
                //产生一个随机编号
                Random random = new Random();
                int rand = random.nextInt(paperIdList.size());        //利用nextInt（产生0到指定值之间的） 产生随机数
                int paperId = paperIdList.get(rand);                //list.get()返回列表中指定位置的元素，题目的编号
                System.out.println("随机编号："+ paperId);
                //将相应的试卷包的id更新到考生表中
                examStartDAO.UpdateExamZipAndStartTime(stuNum, examName, paperId, FormatDate.getFormateDateAll());
                //得到试卷，
                Paper paper = examStartDAO.getPaperById(paperId);
                /**
                 * 将下载的操作交给客户端处理了，这边只需要传递试卷名参数给客户端即可
                 * */
                //String paperPath = paper.getZipPath()+paper.getZipName();  //获得试卷的地址
                
                //调用FileDownload类中的downloadFile()方法
                //FileDownload.downloadFile(paperPath);
                
                //返回的        success#试卷名
                StringBuilder str = new StringBuilder("success");
                str.append("#");
                str.append(paper.getZipName());
                return str.toString();
            }
        }else{
            System.out.println("已经开始过考试了，现在重新开始了");
            //这里就不需要更新ip
            
            String examLong = exam.getExamLong();  //获得考试的时长
            //则将剩余的时间返回,先计算使用了的时间，返回的是一个字符串，分#秒
            String examUserdTime = FormatDate.examRemainTime(examinee.getExamStartime(), FormatDate.getFormateDateAll());  //param:1.考试开始时间，2.现在的时间
            String[] splitTime = examUserdTime.split("#");
            //这里先忽略秒
            String examRemainTime = String.valueOf(Integer.valueOf(examLong) - Integer.valueOf(splitTime[0]));
            
            return examRemainTime;           //只需返回考试的剩余时间即可
        }
    }
    
    //判断ip的范围
    public static boolean isBetween(String ip, String start, String end){
        //注意一下用点切割时要注意转义
        String[] ipArray = ip.split("\\.");
        String[] startArray = start.split("\\."); 
        String[] endArray = end.split("\\."); 
        //以上是将三个ip都以点进行切分
        System.out.println(ipArray.length);
        if(ipArray.length != 4){
            return false;      
        }           
        
        long ipLong=((long)((((Integer.parseInt(ipArray[0])<< 8)+Integer.parseInt(ipArray[1]))<< 8)+ 
                Integer.parseInt(ipArray[2]))<< 8)+Integer.parseInt(ipArray[3]); 
        long startLong=((long)((((Integer.parseInt(startArray[0])<< 8)+Integer.parseInt(startArray[1]))<< 8)+ 
                Integer.parseInt(startArray[2]))<< 8)+Integer.parseInt(startArray[3]); 
        long endLong=((long)((((Integer.parseInt(endArray[0])<< 8)+Integer.parseInt(endArray[1]))<< 8)+ 
                Integer.parseInt(endArray[2]))<< 8)+Integer.parseInt(endArray[3]);
        return ipLong>= startLong&& ipLong<= endLong; 
}
        
        
}