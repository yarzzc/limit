import com.yarzz.limit.TestController;
import com.yarzz.limit.RateLimiterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RateLimiterApplication.class)
public class test {

	@Resource
	private TestController testController;

	@Test
	public void test1(){
		testController.test1("lisi");
	}
}
