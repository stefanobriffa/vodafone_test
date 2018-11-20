package sb.vodafone.test.vodafonetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VodafoneTestApplication {
	
	public static void main(String[] args) {		
		
		SpringApplication.run(VodafoneTestApplication.class, args);
	}
	
	//@Bean
	//public CommonsRequestLoggingFilter requestLoggingFilter() {
	//    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	//    loggingFilter.setIncludeClientInfo(true);
	//    loggingFilter.setIncludeQueryString(true);
	//    loggingFilter.setIncludePayload(true);
	//    loggingFilter.setIncludeHeaders(true);
	//    return loggingFilter;
//	}
}
