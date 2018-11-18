package sb.vodafone.test.vodafonetest.interfaces;

import java.util.List;

import sb.vodafone.test.vodafonetest.Exceptions.NumberInvalidException;
import sb.vodafone.test.vodafonetest.Exceptions.PhoneNumberFormatException;
import sb.vodafone.test.vodafonetest.entities.*;

public interface IMobileSubscriberService {
	public List<MobileSubscriber> GetAll();

	public MobileSubscriber GetByID(Long subscriberID);

	public MobileSubscriber GetBySpecification(MobileSubscriber searchParameters);
	
	public MobileSubscriber Save(MobileSubscriber mobileSubscriber) throws PhoneNumberFormatException;

	public String Delete(MobileSubscriber mobileSubscriber) throws PhoneNumberFormatException, NumberInvalidException;

	public MobileSubscriber Update(MobileSubscriber mobileSubscriber) throws PhoneNumberFormatException, NumberInvalidException;
}
