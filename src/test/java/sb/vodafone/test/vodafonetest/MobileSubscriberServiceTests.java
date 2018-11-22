package sb.vodafone.test.vodafonetest;

import org.junit.Test;

import sb.vodafone.test.vodafonetest.Exceptions.NumberInvalidException;
import sb.vodafone.test.vodafonetest.Exceptions.PhoneNumberFormatException;
import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.entities.ServiceType;
import sb.vodafone.test.vodafonetest.services.MobileSubscriberService;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MobileSubscriberServiceTests {

	@Test
	public void FormatNumberToE164_validNo() throws PhoneNumberFormatException {
		MobileSubscriberService _service = new MobileSubscriberService();
		String _formattedNo = _service.FormatNumberToE164("79030003");
		Assert.assertEquals("35679030003", _formattedNo);
	}

	@Test(expected = PhoneNumberFormatException.class)
	public void FormatNumberToE164_invalidNo() throws PhoneNumberFormatException {
		MobileSubscriberService _service = new MobileSubscriberService();
		_service.FormatNumberToE164("7979");
	}

	@Test
	public void FormatNumberToE164_invalidNo_checkMsg() throws PhoneNumberFormatException {
		MobileSubscriberService _service = new MobileSubscriberService();
		String _msg = "";
		try {
			_service.FormatNumberToE164("7979");
		} catch (PhoneNumberFormatException e) {
			_msg = e.getMessage();
		}

		Assert.assertEquals("Mobile number is invalid", _msg);
	}

	@Test(expected = PhoneNumberFormatException.class)
	public void FormatNumberToE164_nullNo() throws PhoneNumberFormatException {
		MobileSubscriberService _service = new MobileSubscriberService();
		_service.FormatNumberToE164(null);
	}

	@Test
	public void FormatNumberToE164_nullNo_checkMsg() throws PhoneNumberFormatException {
		MobileSubscriberService _service = new MobileSubscriberService();
		String _msg = "";
		try {
			_service.FormatNumberToE164(null);
		} catch (PhoneNumberFormatException e) {
			_msg = e.getMessage();
		}

		Assert.assertEquals("Error while parsing mobile number", _msg);		
	}

	@Test(expected = NumberInvalidException.class)
	public void CheckObjectValidityWithDB_BothParamsNull() throws PhoneNumberFormatException, NumberInvalidException {
		MobileSubscriberService _service = new MobileSubscriberService();
		    
    	boolean _retval= _service.CheckObjectValidityWithDB(null, null);
		Assert.assertTrue(_retval);
	}
	
	@Test(expected = NumberInvalidException.class)
	public void CheckObjectValidityWithDB_OneParamNull() throws PhoneNumberFormatException, NumberInvalidException {
		MobileSubscriberService _service = new MobileSubscriberService();
		
		MobileSubscriber _ms1 =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
		
    	boolean _retval= _service.CheckObjectValidityWithDB(_ms1, null);
		Assert.assertTrue(_retval);
	}
	
	@Test(expected = NumberInvalidException.class)
	public void CheckObjectValidityWithDB_Invalid() throws PhoneNumberFormatException, NumberInvalidException {
		MobileSubscriberService _service = new MobileSubscriberService();

		MobileSubscriber _ms1 =  new MobileSubscriber("79030004", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
     
    	MobileSubscriber _ms2 =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
    	
    	_service.CheckObjectValidityWithDB(_ms1, _ms2);
	}
	
	@Test()
	public void CheckObjectValidityWithDB_Valid() throws PhoneNumberFormatException, NumberInvalidException {
		MobileSubscriberService _service = new MobileSubscriberService();

		MobileSubscriber _ms1 =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
     
    	MobileSubscriber _ms2 =  new MobileSubscriber("79030003", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
    	
    	boolean _retval= _service.CheckObjectValidityWithDB(_ms1, _ms2);
		Assert.assertTrue(_retval);
	}

}
