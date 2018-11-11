package sb.vodafone.test.vodafonetest.Exceptions;

@SuppressWarnings("serial")
public class PhoneNumberFormatException extends Exception { 
	public PhoneNumberFormatException(String errorMessage) {
        super(errorMessage);
    }
	
	public PhoneNumberFormatException(String errorMessage, Throwable err) {
        super(errorMessage);
    }
}
