package sb.vodafone.test.vodafonetest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import junit.framework.Assert;

import javax.ws.rs.core.MediaType;

import sb.vodafone.test.vodafonetest.controllers.MobileSubscriberController;
import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.entities.ServiceType;
import sb.vodafone.test.vodafonetest.interfaces.IMobileSubscriberRepo;
import sb.vodafone.test.vodafonetest.services.MobileSubscriberService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MobileSubscriberController.class, secure = false)
public class MobileSubscriberControllerIntegrationTests {

	@MockBean
	private MobileSubscriberService mobileSubscriberService;

	@MockBean
    private IMobileSubscriberRepo mockRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@TestConfiguration
    static class MobileSubscriberTestContextConfiguration {
		@Bean
        public MobileSubscriberService mobileSubscriberService() {
            return new MobileSubscriberService();
        }						
    }   
	@Test
	public void MobileSubscriberController_GetAll() throws Exception {

		MobileSubscriber _ms1 = new MobileSubscriber("79030003", 1, 1, ServiceType.MOBILE_POSTPAID, (long) 389456789);

		MobileSubscriber _ms2 = new MobileSubscriber("79030004", 2, 2, ServiceType.MOBILE_POSTPAID, (long) 389456789);

		List<MobileSubscriber> _mobileList = new ArrayList<>();
		_mobileList.add(_ms1);
		_mobileList.add(_ms2);

		Mockito.when(mobileSubscriberService.GetAll()).thenReturn(_mobileList);		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/mobileSubscribers/GetAll")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"msisdn\":\"79030003\",\"customer_id_owner\":1,\"customer_id_user\":1,\"service_type\":\"MOBILE_POSTPAID\",\"service_start_date\":389456789,\"id\":null},{\"msisdn\":\"79030004\",\"customer_id_owner\":2,\"customer_id_user\":2,\"service_type\":\"MOBILE_POSTPAID\",\"service_start_date\":389456789,\"id\":null}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void MobileSubscriberController_Add_BadRequest() throws Exception {

		String expected = "";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/mobileSubscribers/Add")
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void MobileSubscriberController_Add_ValidRequest() throws Exception {

		String expected  = "{\"msisdn\":\"79030003\",\"customer_id_owner\":1,\"customer_id_user\":1,\"service_type\":\"MOBILE_POSTPAID\",\"service_start_date\":389456789,\"id\":null}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/mobileSubscribers/Add")
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void MobileSubscriberController_Update_BadRequest() throws Exception {

		String expected = "";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/mobileSubscribers/Update")
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}	
	
	@Test
	public void MobileSubscriberController_Delete_BadRequest() throws Exception {

		String expected = "";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/mobileSubscribers/Delete")
				.accept(MediaType.APPLICATION_JSON).content(expected)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}	
}
