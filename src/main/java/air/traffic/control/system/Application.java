package air.traffic.control.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application launching class
 * 
 * @author momintariq
 *
 */
@SpringBootApplication
public class Application {

	/**
	 * Launches the application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
