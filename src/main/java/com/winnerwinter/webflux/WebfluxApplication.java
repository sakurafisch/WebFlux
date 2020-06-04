package com.winnerwinter.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@SpringBootApplication
public class WebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@RestController
	class FluxController {
		@GetMapping("flux")
		public Flux<String> greeting() {
			return Flux.just("Hello Flux");
		}
	}

	@RestController
	class MonoController {
		@GetMapping("mono")
		public Mono<String> mono() {
			return Mono.just("Hello Mono");
		}
	}

	@RestController
	class SseController {
		@GetMapping(value = "sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
		public Flux<String> sse() {
			return Flux.interval(Duration.ofMillis(1000)).map(val -> " -> " + val);
		}
	}
}
