package sb.vodafone.test.vodafonetest.Exceptions;

@SuppressWarnings("serial")
public class NumberNotFoundException extends Exception { 
	public NumberNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
