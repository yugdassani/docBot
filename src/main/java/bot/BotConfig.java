package bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotConfig {

	public static void main(String[] args) {
		SpringApplication.run(BotConfig.class, args);
		String command = "curl 'https://api.api.ai/api/query?v=20150910&query=hi&lang=en&sessionId=3f6cdf3a-ed1d-4b9d-a109-2912f4d473f2&timezone=2017-07-21T15:31:18+0530' -H 'Authorization:Bearer 1296bdc0d7d4417bbe4a83125e6eb980'";
		Runtime.getRuntime.exec(command);
	}

}
