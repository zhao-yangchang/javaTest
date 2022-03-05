package spring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import spring.service.ShopService;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class BoyShopServiceImplTest {

    //@Resource(name = "boyShop")
    @Autowired
    @Qualifier("boyShopServiceImpl")
    private ShopService shopService;

    @Test
    void buy() {

        log.info("buy start");
        shopService.buy();

    }
}
