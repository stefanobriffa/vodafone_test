package sb.vodafone.test.vodafonetest.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.*;

import sb.vodafone.test.vodafonetest.Exceptions.NumberInvalidException;
import sb.vodafone.test.vodafonetest.Exceptions.NumberNotFoundException;
import sb.vodafone.test.vodafonetest.Exceptions.PhoneNumberFormatException;
import sb.vodafone.test.vodafonetest.entities.MobileSubscriber;
import sb.vodafone.test.vodafonetest.services.*;

@RestController
public class MobileSubscriberController {
	@Autowired
	private MobileSubscriberService msService;

	public void setMobileSubscriberService(MobileSubscriberService mobileSubscriberService) {
		this.msService = mobileSubscriberService;
	}

	@GetMapping("/api/mobileSubscribers")
	public List<MobileSubscriber> GetAll() {
		List<MobileSubscriber> _subscribers = msService.GetAll();
		return _subscribers;
	}

	@GetMapping("/api/mobileSubscribers/{subscriberId}/{subscriberNo}/{customerId}/{ownerId}")
	public MobileSubscriber GetByID(@PathVariable(name="subscriberId") Long subscriberId,
			@PathVariable(name="subscriberNo") String subscriberNo,
			@PathVariable(name="customerId") Integer customerId,
			@PathVariable(name="ownerId") Integer ownerId) {
		
		
		return msService.GetByID(subscriberId);
	}

	@PostMapping("/api/mobileSubscribers")
	public ResponseEntity<?> Add(@Valid @RequestBody MobileSubscriber subscriber) throws PhoneNumberFormatException {
		MobileSubscriber _savedMobileSubscriber = msService.Save(subscriber);
		return ResponseEntity.ok(_savedMobileSubscriber);
	}

	@DeleteMapping("/api/mobileSubscribers")
	public ResponseEntity<?> Delete(@RequestBody MobileSubscriber mobileSubscriber) throws PhoneNumberFormatException, NumberInvalidException, NumberNotFoundException {
		String _status = msService.Delete(mobileSubscriber);
		if (_status != null) {
			return ResponseEntity.ok("Subscriber deleted");
		}
		else
			throw new NumberNotFoundException("Mobile not found");
	}

	@PutMapping("/api/mobileSubscribers")
	public ResponseEntity<?> Update(@RequestBody MobileSubscriber mobileSubscriber) throws NumberNotFoundException, PhoneNumberFormatException, NumberInvalidException {
		MobileSubscriber _savedMobileSubscriber = msService.Update(mobileSubscriber);
		if (_savedMobileSubscriber != null) {
			return ResponseEntity.ok(_savedMobileSubscriber);
		}
		else
			throw new NumberNotFoundException("Mobile not found");
	}
}

