package sample.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sample.ws.service.Hello_HelloPort_Client;

@SpringBootApplication
public class SampleWsApplication {
  public static void main(String[] args) throws Exception {
    if ("true".equals(System.getenv("client_mode"))) {
      Hello_HelloPort_Client.main(args);
    } else {
      SpringApplication.run(SampleWsApplication.class, args);
    }
  }
}
