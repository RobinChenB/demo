package com.liyulin.design.patterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * 购物实现类1
 *
 * @author liyulin
 * @version 1.0 2013-9-6 下午8:52:39
 */
@Slf4j
public class ShopImpl1 implements Shop {

	public void shopping() {
		log.info("ShopImpl1......");
	}

}
