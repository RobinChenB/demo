package com.liyulin.mocktest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.liyulin.mocktest.util.MockitoUtil;
import com.liyulin.mocktest.util.MockitoUtil.MockDto;

import lombok.extern.slf4j.Slf4j;

/**
 * SpringBoot单元测试基类
 *
 * @author liyulin
 * @date 2019年4月22日上午12:25:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public abstract class AbstractUnitTest {

	@Autowired
	protected WebApplicationContext applicationContext;
	protected static MockMvc mockMvc = null;
	
	@Before
	public void initMock() {
		if (mockMvc == null) {
			mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		}
	}

	@After
	public void after() {
		MockDto mockDto = null;
		while ((mockDto = MockitoUtil.getMockCache().poll()) != null) {
			MockitoUtil.setMockAttribute(mockDto.getTargetObject(), applicationContext.getBean(mockDto.getMockClass()),
					MockitoUtil.MockTypeEnum.MOCK_AFTER);
		}
	}

	protected static void initMocks(Object targetObject, Object mockObject) {
		MockitoUtil.setMockAttribute(targetObject, mockObject, MockitoUtil.MockTypeEnum.MOCK_BEFORE);
	}
	
	
	protected <T> T postJson(String url, Object req, TypeReference<T> typeReference) throws Exception {
		String requestBody = JSON.toJSONString(req);
		log.info("test.requestBody={}", requestBody);
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(requestBody)
					.accept(MediaType.APPLICATION_JSON_UTF8)
				).andReturn();

		String content = result.getResponse().getContentAsString();
		log.info("test.result={}", content);

		return JSON.parseObject(content, typeReference);
	}
	
}