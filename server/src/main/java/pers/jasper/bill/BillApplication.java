package pers.jasper.bill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
public class BillApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillApplication.class, args);
	}

}
