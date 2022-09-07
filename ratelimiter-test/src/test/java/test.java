import com.yarzzc.limit.TestController;
import com.yarzzc.limit.RateLimiterApplication;
import com.yarzzc.limit.mapstruce.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RateLimiterApplication.class)
public class test {

	@Resource
	private TestController testController;

	@Test
	public void test1(){
		testController.test1("李四");
	}

	@Test
	public void test2(){
		testController.test2("赵六");
	}

	@Test
	public void test3(){
		UserPo userPo = UserPo.builder()
				.id(1L)
				.gmtCreate(new Date())
				.buyerId(666L)
				.userNick("测试mapstruct")
				.userVerified("ok")
				.age(18L)
				.build();
		System.out.println("1234" + userPo);
		UserEntity userEntity = IPersonMapper.INSTANCE.po2entity(userPo);
		System.out.println(userEntity);
	}

	@Test
	public void test4(){
		System.out.println("-----------testTime-----start------");
		int times = 50000000;
		final long springStartTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			UserPo userPo = UserPo.builder()
					.id(1L)
					.gmtCreate(new Date())
					.buyerId(666L)
					.userNick("测试123")
					.userVerified("ok")
					.build();
			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(userPo, userEntity);
		}
		final long springEndTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			UserPo userPo = UserPo.builder()
					.id(1L)
					.gmtCreate(new Date())
					.buyerId(666L)
					.userNick("测试123")
					.userVerified("ok")
					.build();
			UserEntity userEntity = IPersonMapper.INSTANCE.po2entity(userPo);
		}
		final long mapstructEndTime = System.currentTimeMillis();
		System.out.println("BeanUtils use time=" + (springEndTime - springStartTime) / 1000 + "秒" +
				"; Mapstruct use time=" + (mapstructEndTime - springEndTime) / 1000 + "秒");
		System.out.println("-----------testTime-----end------");
	}

	@Test
	public void test5(){
		List<User> users = new ArrayList<>();
		User build = User.builder()
				.id(11L)
				.userNick("zhangsan")
				.build();
		User build1 = User.builder()
				.id(12L)
				.userNick("lisi")
				.build();
		users.add(build);
		users.add(build1);

		UserBo userBo = UserBo.builder()
				.id(1l)
				.gmtCreate(new Date())
				.buyerId(666L)
				.user(users)
				.age(12L)
				.userVerified("ok")
				.build();
		users.forEach(item -> {
			UserPo userPo = IPersonMapper.INSTANCE.boToPo(item, userBo);
			System.out.println(userPo.toString());
		});

	}
}
