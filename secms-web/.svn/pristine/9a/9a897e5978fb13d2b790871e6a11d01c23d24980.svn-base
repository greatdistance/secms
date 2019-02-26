package com.xa.xaufe.secmsweb;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SecmsWebApplication.class)
public class BaseSpringTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    //执行任何单元测试之前都会先执行@Before
    @Before
    public void setUp() throws Exception {
        this.setTime(System.currentTimeMillis());
        logger.info("==> 测试开始执行 <==");
    }

    @After
    public void tearDown() throws Exception {
        logger.info("==> 测试执行完成，耗时：{} <==",
                System.currentTimeMillis() - this.getTime() + "毫秒");
    }

    @Test
    public void test() {

    }
}

