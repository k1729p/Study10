package kp.consumer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import kp.common.Content;
import kp.consumer.service.ConsumerService;

/**
 * The {@link ConsumerController} tests using the {@link MockMvc}.
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ConsumerControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConsumerService consumerService;

	/**
	 * The {@link Resilience4JCircuitBreakerFactory}
	 */
	@MockBean
	Resilience4JCircuitBreakerFactory circuitBreakerFactory;

	private static final boolean VERBOSE = false;

	private static final String GET_CONTENT_PATH = "/content";
	private static final String CONTENT_TEXT = "ABC";

	/**
	 * The constructor.
	 */
	ConsumerControllerTests() {
		super();
	}

	/**
	 * Should get the {@link Content}s.<br>
	 * Tests a <b>GET</b> request.
	 * 
	 * @throws Exception the {@link Exception}
	 */
	@Test
	void shouldGetContent() throws Exception {
		// GIVEN
		Mockito.when(consumerService.getContent()).thenReturn(new Content(CONTENT_TEXT));
		final MockHttpServletRequestBuilder requestBuilder = get(GET_CONTENT_PATH)
				.accept(MediaType.APPLICATION_JSON_VALUE);
		// WHEN
		final ResultActions resultActions = mockMvc.perform(requestBuilder);
		// THEN
		if (VERBOSE) {
			resultActions.andDo(print());
		}
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		resultActions.andExpect(jsonPath("$.text").value(CONTENT_TEXT));
	}
}
