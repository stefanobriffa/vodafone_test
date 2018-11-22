package sb.vodafone.test.vodafonetest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.entities.ServiceType;
import sb.vodafone.test.vodafonetest.interfaces.IMobileSubscriberRepo;
import sb.vodafone.test.vodafonetest.services.MobileSubscriberService;

@RunWith(SpringRunner.class)
public class MobileSubscriberIntegrationTests {
	@TestConfiguration
    static class MobileSubscriberTestContextConfiguration {
		@Bean
        public MobileSubscriberService mobileSubscriberServiceMock() {
            return new MobileSubscriberService();
        }						
    }        

    @Autowired
    private MobileSubscriberService mobileSubscriberServiceMock;
 
    @MockBean
    private IMobileSubscriberRepo mockRepository;       
    
    @Test
    public void MobileSubscriberService_GetByID() {
    	MobileSubscriber _ms =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
     
    	Mockito.when(mockRepository.findById((long) 1)).thenReturn(Optional.of(_ms));  
    	              
        MobileSubscriber _msReturned = mobileSubscriberServiceMock.GetByID((long)1);
        Assert.assertEquals("79030003", _msReturned.getMsisdn());
    }    
    
    @Test
    public void MobileSubscriberService_GetAll() {
    	MobileSubscriber _ms1 =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
     
    	MobileSubscriber _ms2 =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
    	
    	List<MobileSubscriber> _mobileList = new ArrayList<>();
    	_mobileList.add(_ms1);
    	_mobileList.add(_ms2);        
    	
    	Mockito.when(mockRepository.findAll()).thenReturn(_mobileList);  
    	              
    	List<MobileSubscriber> _msListReturned = mobileSubscriberServiceMock.GetAll();
        Assert.assertEquals(2, _msListReturned.size());
    }    
}
