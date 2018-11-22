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
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {				
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setMaxPayloadLength(10000);
	    loggingFilter.setIncludeHeaders(false);
	    loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
	    return loggingFilter;
	}		
}
