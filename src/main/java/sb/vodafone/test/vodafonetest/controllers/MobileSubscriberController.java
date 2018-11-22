package sb.vodafone.test.vodafonetest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.*;

import sb.vodafone.test.vodafonetest.VodafoneTestApplication;
import sb.vodafone.test.vodafonetest.Exceptions.NumberInvalidException;
import sb.vodafone.test.vodafonetest.Exceptions.NumberNotFoundException;
import sb.vodafone.test.vodafonetest.Exceptions.PhoneNumberFormatException;
import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.entities.SearchCriteria;
import sb.vodafone.test.vodafonetest.services.*;

@RestController
public class MobileSubscriberController {
	@Autowired
	private MobileSubscriberService msService;
	
	public void setMobileSubscriberService(MobileSubscriberService mobileSubscriberService) {
		this.msService = mobileSubscriberService;
	}

	@GetMapping("/api/mobileSubscribers/GetAll")
	public List<MobileSubscriber> GetAll() {
		List<MobileSubscriber> _subscribers = msService.GetAll();
		return _subscribers;		
	}

	@PostMapping("/api/mobileSubscribers/Search")
	public ResponseEntity<?> Search(@RequestBody SearchCriteria searchCriteria) throws PhoneNumberFormatException, NumberNotFoundException {		
		if(searchCriteria != null)
		{
			String _msisdn = searchCriteria.getMsisdn();
			
			searchCriteria.setMsisdn((_msisdn != null && !_msisdn.isEmpty()) ? msService.FormatNumberToE164(_msisdn) : "");
			MobileSubscriber _searchParam = new MobileSubscriber();
			_searchParam.setMsisdn(searchCriteria.getMsisdn());
			_searchParam.setCustomer_id_user(searchCriteria.getCustomer_id_user());
			_searchParam.setCustomer_id_owner(searchCriteria.getCustomer_id_owner());
			_searchParam.setId(searchCriteria.getId());

			MobileSubscriber _result = msService.GetBySpecification(_searchParam);
			
			if (_result != null) {
				return ResponseEntity.ok(_result);
			}
			else
				throw new NumberNotFoundException("Mobile not found");
		}
		else
			return ResponseEntity.badRequest().body("No Search Criteria Specified");			
	}

	@PostMapping("/api/MobileSubscriber/Add")
	@ResponseBody
	public ResponseEntity<?> Add(@Valid @RequestBody MobileSubscriber subscriber) throws PhoneNumberFormatException {		
		if(subscriber != null)
		{
			MobileSubscriber _savedMobileSubscriber = msService.Save(subscriber);
			return ResponseEntity.ok(_savedMobileSubscriber);
		}
		else
			return ResponseEntity.badRequest().body("No mobile subscriber Specified");
	}

	@DeleteMapping("/api/mobileSubscribers/Delete")
	public ResponseEntity<?> Delete(@RequestBody MobileSubscriber mobileSubscriber) throws PhoneNumberFormatException, NumberInvalidException, NumberNotFoundException {
		if(mobileSubscriber != null)
		{
			String _status = msService.Delete(mobileSubscriber);
			if (_status != null) {
				return ResponseEntity.ok("Subscriber deleted");
			}
			else
				throw new NumberNotFoundException("Mobile not found");
		}
		else
			return ResponseEntity.badRequest().body("No mobile subscriber Specified");			
	}

	@PutMapping("/api/mobileSubscribers/Update")
	public ResponseEntity<?> Update(@RequestBody MobileSubscriber mobileSubscriber) throws NumberNotFoundException, PhoneNumberFormatException, NumberInvalidException {
		if(mobileSubscriber != null)
		{
			MobileSubscriber _savedMobileSubscriber = msService.Update(mobileSubscriber);
			if (_savedMobileSubscriber != null) {
				return ResponseEntity.ok(_savedMobileSubscriber);
			}
			else
				throw new NumberNotFoundException("Mobile not found");
		}
		else
			return ResponseEntity.badRequest().body("No mobile subscriber Specified");			
		
	}
}

