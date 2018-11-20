package sb.vodafone.test.vodafonetest;
import org.junit.Test;

import sb.vodafone.test.vodafonetest.Exceptions.PhoneNumberFormatException;
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

   //private MobileSubscriberService _service;
   
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
	  }
	  catch(PhoneNumberFormatException e){
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
		  }
		  catch(PhoneNumberFormatException e){
			  _msg = e.getMessage();
		  }
			  
		  Assert.assertEquals("Error while parsing mobile number", _msg);
   }
}
