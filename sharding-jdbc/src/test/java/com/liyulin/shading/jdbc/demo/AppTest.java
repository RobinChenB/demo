package com.liyulin.shading.jdbc.demo;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.shading.jdbc.demo.base.BaseEntity;
import com.liyulin.shading.jdbc.demo.entity.ProductInfoEntity;
import com.liyulin.shading.jdbc.demo.enums.DelStateEnum;
import com.liyulin.shading.jdbc.demo.mapper.ProductInfoBaseMapper;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {
	
	@Autowired
	private ProductInfoBaseMapper productInfoBaseMapper;
	
	@Before
	public void setBefore() {
//		Example example = new Example(ProductInfoEntity.class);
//		example.createCriteria().andBetween(BaseEntity.Columns.ADD_TIME.getProperty(), "2019-06-03 00:00:00", "2019-06-05 00:00:00");
//		productInfoBaseMapper.deleteByExample(example);
	}
	
	@Test
	public void testInsert() {
		ProductInfoEntity entity = new ProductInfoEntity();
		entity.setId(2000L);
		entity.setName("iphone");
		entity.setSellPrice(1000L);
		entity.setStock(2000L);
		entity.setAddTime(new Date());
		entity.setDelState(DelStateEnum.NORMAL.getDelState());
		int count = productInfoBaseMapper.insertSelective(entity);
		
		Assertions.assertThat(count).isEqualTo(1);
	}
	
	@Test
	public void testSelect() {
		Example example = new Example(ProductInfoEntity.class);
		example.createCriteria().andBetween(BaseEntity.Columns.ADD_TIME.getProperty(), "2019-06-03 00:00:00",
				"2019-06-05 00:00:00");
		List<ProductInfoEntity> list = productInfoBaseMapper.selectByExample(null);
	}
	
}