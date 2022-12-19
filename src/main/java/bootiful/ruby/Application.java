package bootiful.ruby;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;
import org.graalvm.polyglot.Source;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> ruby() {
        return event -> {
            try (var engine = Engine.create()) {
                var source = Source.create("ruby", "21 + 21");
                try (var context = Context.newBuilder().engine(engine).build()) {
                    var v = context.eval(source).asInt();
                    Assert.state(v == 42, "the value is 42");
                    System.out.println("the result : "   + v );
                }
            }
        };
    }
}
