package com.dyenigma;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试主类，其他测试类继承该类,如果想要执行以后回滚，使用@Transactional和@Rollback注解
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransformerApplicationTests {
}
