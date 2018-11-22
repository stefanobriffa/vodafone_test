package sb.vodafone.test.vodafonetest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.entities.ServiceType;

public class MobileSubscriberValidationTests {

	private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void ValidMobileSubscriber() {
    	MobileSubscriber _ms1 =  new MobileSubscriber("79030004", 1, 1,
    			ServiceType.MOBILE_POSTPAID, (long) 389456789);
        Set<ConstraintViolation<MobileSubscriber>> violations = validator.validate(_ms1);
        Assert.assertTrue(violations.isEmpty());
    }
    
    @Test
    public void InvalidMobileSubscriber() {
    	MobileSubscriber _ms1 =  new MobileSubscriber();
    	
    	_ms1.setCustomer_id_owner(0);
    	_ms1.setCustomer_id_user(1);
    	_ms1.setMsisdn("79030003");
    	_ms1.setService_type(ServiceType.MOBILE_PREPAID);
        Set<ConstraintViolation<MobileSubscriber>> violations = validator.validate(_ms1);
        Assert.assertFalse(violations.isEmpty());
    }    
}
