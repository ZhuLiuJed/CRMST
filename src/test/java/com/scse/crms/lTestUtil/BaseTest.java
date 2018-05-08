package com.scse.crms.lTestUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**


 *
 配置spring和junit整合，junit启动时加载springIOC容器 


 */

//告诉unit spring配置文件
@ContextConfiguration(locations={"classpath:spring/applicationContext-service.xml",
								"classpath:spring/applicationContext-dao.xml",
								"classpath:spring/applicationContext-transaction.xml",
								"classpath:spring/springmvc.xml"})

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)  
public class BaseTest {
      @Test
      public void setuplocations(){
    	  
      }
}

