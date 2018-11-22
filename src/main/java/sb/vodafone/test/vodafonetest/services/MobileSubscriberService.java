package sb.vodafone.test.vodafonetest.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import sb.vodafone.test.vodafonetest.entities.*;
import sb.vodafone.test.vodafonetest.interfaces.*;
import sb.vodafone.test.vodafonetest.Exceptions.*;
import sb.vodafone.test.vodafonetest.dl.MobileSubscriberSpecification;

@Service
public class MobileSubscriberService implements IMobileSubscriberService {

	@Autowired
	private IMobileSubscriberRepo _mobileSubscriberRepo;

	public String FormatNumberToE164(String numberToFormat) throws PhoneNumberFormatException {

		String _retval = "";

		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneProto = phoneUtil.parse(numberToFormat, "MT");
			if (phoneUtil.isValidNumber(phoneProto)
					&& phoneUtil.isPossibleNumberForType(phoneProto, PhoneNumberType.MOBILE))
				_retval = phoneUtil.format(phoneProto, PhoneNumberFormat.E164).substring(1);
			else
				throw new PhoneNumberFormatException("Mobile number is invalid");
		} catch (NumberParseException e) {
			throw new PhoneNumberFormatException("Error while parsing mobile number", e);
		}

		return _retval;
	}

	public boolean CheckObjectValidityWithDB(MobileSubscriber mobileSubscriberFromDB,
			MobileSubscriber mobileSubscriberReceived) throws NumberInvalidException {
		
		if (mobileSubscriberReceived != null && mobileSubscriberFromDB != null) 
		{			
			if (!mobileSubscriberReceived.getMsisdn().equals(mobileSubscriberFromDB.getMsisdn())
					|| mobileSubscriberReceived.getCustomer_id_owner() != mobileSubscriberFromDB.getCustomer_id_owner()
					|| mobileSubscriberReceived.getCustomer_id_user() != mobileSubscriberFromDB.getCustomer_id_user()) 
			{
				throw new NumberInvalidException("Data received not consistennt with data in the system");
			}
		}
		else		
			throw new NumberInvalidException("Invalid parameters");		
		
		return true;
		
	}

	public void setMmobileSubscriberRepository(IMobileSubscriberRepo mobileSubscriberRepo) {
		_mobileSubscriberRepo = mobileSubscriberRepo;
	}

	@Override
	public List<MobileSubscriber> GetAll() {
		return _mobileSubscriberRepo.findAll();
	}

	@Override
	public MobileSubscriber GetBySpecification(MobileSubscriber searchParameters) {	
		Specification<MobileSubscriber> spec = new MobileSubscriberSpecification(searchParameters);
		List<MobileSubscriber> _result = _mobileSubscriberRepo.findAll(spec);
		
		if(_result != null && !_result.isEmpty())
			return _result.get(0);
		else
			return null;
	}
	
	@Override
	public MobileSubscriber GetByID(Long subscriberID) {
		
		Optional<MobileSubscriber> _optMS = _mobileSubscriberRepo.findById(subscriberID);
		if (_optMS.isPresent())
			return _optMS.get();
		else
			return null;
	}

	@Override
	public MobileSubscriber Save(MobileSubscriber mobileSubscriber) throws PhoneNumberFormatException {
		mobileSubscriber.setMsisdn(FormatNumberToE164(mobileSubscriber.getMsisdn()));
		mobileSubscriber.setService_start_date(Instant.now().toEpochMilli());
		MobileSubscriber _savedMobileSubscriber = _mobileSubscriberRepo.save(mobileSubscriber);

		return _savedMobileSubscriber;
	}

	@Override
	public String Delete(MobileSubscriber mobileSubscriber)
			throws PhoneNumberFormatException, NumberInvalidException {

		MobileSubscriber _ms = GetByID(mobileSubscriber.getId());
		String _retval = "Deleted";
		if (_ms != null) {
			mobileSubscriber.setMsisdn(FormatNumberToE164(mobileSubscriber.getMsisdn()));
			CheckObjectValidityWithDB(_ms, mobileSubscriber);
			_ms.setService_type(mobileSubscriber.getService_type());
			_mobileSubscriberRepo.delete(_ms);
		}
		else
			_retval = null;

		return _retval;		
	}

	@Override
	public MobileSubscriber Update(MobileSubscriber mobileSubscriber)
			throws PhoneNumberFormatException, NumberInvalidException {
		MobileSubscriber _ms = GetByID(mobileSubscriber.getId());
		MobileSubscriber _savedMobileSubscriber = null;
		
		if (_ms != null) {
			mobileSubscriber.setMsisdn(FormatNumberToE164(mobileSubscriber.getMsisdn()));
			CheckObjectValidityWithDB(_ms, mobileSubscriber);
			_ms.setService_type(mobileSubscriber.getService_type());
			_savedMobileSubscriber = _mobileSubscriberRepo.save(_ms);
		}

		return _savedMobileSubscriber;	
	}

}
