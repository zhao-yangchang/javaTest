package com.zhaoyc.javatest.spring.service.impl;

import com.zhaoyc.javatest.spring.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName BoyShopServiceImpl
 * @Description boys shop service impl
 * @Author zhaoyangchang
 * @Date 2022/1/31 下午5:05
 * @Version 1.0.0
 */
@Service("boyShop")
@Slf4j
public class BoyShopServiceImpl implements ShopService {
    @Override
    public void buy() {
        log.info("boys shop");
    }
}
