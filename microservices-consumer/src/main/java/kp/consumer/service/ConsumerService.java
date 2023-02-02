package kp.consumer.service;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import kp.common.Content;

/**
 * Hide the access to the microservice <b>producer-service</b> inside this local
 * service.
 * 
 */
@Service
public class ConsumerService {

	private final CircuitBreaker circuitBreaker;
	/**
	 * The URL uses the logical name of the <b>producer-service</b>.
	 */
	private static final String GET_CONTENT_URL = "http://PRODUCER-SERVICE/content";
	private static final Function<Throwable, Content> FALLBACK_FUNCTION = throwable -> new Content("fallback");

	/**
	 * The {@link RestTemplate}
	 */
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	/**
	 * Constructor.
	 * 
	 * @param circuitBreaker the {@link CircuitBreaker}
	 */
	public ConsumerService(CircuitBreaker circuitBreaker) {

		this.circuitBreaker = circuitBreaker;
	}

	/**
	 * Gets the {@link Content} from producer endpoint.
	 * 
	 * @return the {@link Content}
	 */
	public Content getContent() {

		final Supplier<Content> contentSupplier = () -> restTemplate.getForObject(GET_CONTENT_URL, Content.class);
		return circuitBreaker.run(contentSupplier, FALLBACK_FUNCTION);
	}

}