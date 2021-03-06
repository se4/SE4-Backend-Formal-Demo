package nju.se4.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * @author sec-group
 */
@SpringBootApplication
public class DemoApplication {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(DemoApplication.class, args);
		try {
			Mock.initDB();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}
}
