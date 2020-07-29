import cn.huiounet.pojo.address.AddressTip;
import cn.huiounet.service.AddressSysService;
import cn.huiounet.service.AddressTipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


/**
 * @author yd
 * @version 1.0
 * @date 2019/12/17 13:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext-*.xml")
public class OrderTest {

    @Autowired
    private AddressTipService addressTipService;

    @Test
    public void test_(){


    }




}
