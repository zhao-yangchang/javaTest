package com.zhaoyc.javatest.spring.service.impl;

import com.zhaoyc.javatest.spring.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName GirlShopServiceImpl
 * @Description girls shop Service impl
 * @Author zhaoyangchang
 * @Date 2022/1/31 下午5:05
 * @Version 1.0.0
 */
@Service("girlShop")
@Slf4j
public class GirlShopServiceImpl implements ShopService {
    @Override
    public void buy() {
        log.info("girls shop");
    }
}
