package com.liyulin.skills.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * stream实例
 *
 * @author liyulin
 * @date 2018年10月15日下午11:15:55
 */
@Slf4j
public class StreamTest {

	@Test
	public void testRemoveSuffixWithRela() {
		String[] tableNames = { "t_user", "t_user_rela", "t_product", "t_product_rela" };
		String relaSuffix = "rela";
		tableNames = Stream.of(tableNames).filter(name -> (!name.endsWith(relaSuffix))).toArray(String[]::new);
		Stream.of(tableNames).forEach(log::info);
	}
	
	@Test
	public void testParallelStream() {
		int count = 100;
		List<Integer> data = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			data.add(i);
		}

		// fork/join并行处理
		data.parallelStream().forEach(item -> {
			log.info("{}", item);
		});
	}

}
