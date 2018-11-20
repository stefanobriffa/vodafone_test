package sb.vodafone.test.vodafonetest;
import org.junit.Test;

import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.entities.ServiceType;
import sb.vodafone.test.vodafonetest.interfaces.IMobileSubscriberRepo;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MobileSubscriberRepoTests {

	@Autowired
	private IMobileSubscriberRepo _mobileSubscriberRepo;

	    @Test
	    @Transactional
	    @Rollback(true)
	    public void Repo_AddNewMobile() {
	    	MobileSubscriber _ms = new MobileSubscriber("79030003", 1, 1,
			ServiceType.MOBILE_POSTPAID, (long) 389456789);

	    	_mobileSubscriberRepo.save(_ms);

	    	java.util.List<MobileSubscriber> _msList = _mobileSubscriberRepo.findAll();

	        assertEquals(1, _msList.size());	      
	        assertEquals("79030003", _msList.get(0).getMsisdn());
	    }

	    @Test
	    @Transactional
	    @Rollback(true)
	    public void Repo_DeleteMobile() {
	    	MobileSubscriber _ms = new MobileSubscriber("79030003", 1, 1,
			ServiceType.MOBILE_POSTPAID, (long) 389456789);

	    	_mobileSubscriberRepo.save(_ms);

	    	java.util.List<MobileSubscriber> _msList = _mobileSubscriberRepo.findAll();

	    	_mobileSubscriberRepo.delete(_msList.get(0));

	    	_msList = _mobileSubscriberRepo.findAll();
	        assertEquals(0, _msList.size());	      
	    }
	    
	    @Test
	    @Transactional
	    @Rollback(true)
	    public void Repo_UpdateMobile_ServicePlan() {
	    	MobileSubscriber _ms = new MobileSubscriber("79030003", 1, 1,
			ServiceType.MOBILE_POSTPAID, (long) 389456789);

	    	_mobileSubscriberRepo.save(_ms);

	    	java.util.List<MobileSubscriber> _msList = _mobileSubscriberRepo.findAll();

	    	_msList.get(0).setService_type(ServiceType.MOBILE_PREPAID);
	    	_mobileSubscriberRepo.save(_msList.get(0));

	    	_msList = _mobileSubscriberRepo.findAll();
	        assertEquals(1, _msList.size());
	        assertEquals(ServiceType.MOBILE_PREPAID, _msList.get(0).getService_type());	        
	    }
	    
}
