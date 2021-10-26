package exceptions;

public class NoProductFoundException extends Exception {
	public NoProductFoundException(String errMessage){
		super(errMessage);
	}
}
