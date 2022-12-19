package bootiful.ruby;

import org.graalvm.polyglot.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> ruby() {
        return event -> {
            try (var polyglot = Context.newBuilder().build()) {
                polyglot.eval("ruby", " puts 'hello' ");
            }
        };
    }
}
