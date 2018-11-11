package sb.vodafone.test.vodafonetest.Exceptions;

@SuppressWarnings("serial")
public class NumberInvalidException extends Exception { 
	public NumberInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
