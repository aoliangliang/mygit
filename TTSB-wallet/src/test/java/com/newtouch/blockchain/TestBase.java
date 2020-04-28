package com.newtouch.blockchain;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.newtouch.blockchain.starter.Starter;

/**
 * junit 测试，方便开关
 * @Package com.newtouch.blockchain.test
 * @ClassName: TestBase
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
@Transactional
@Rollback(false)
public abstract class TestBase {
	public void test() {
	}
}
