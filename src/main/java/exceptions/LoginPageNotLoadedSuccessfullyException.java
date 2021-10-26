package exceptions;

public class LoginPageNotLoadedSuccessfullyException extends Exception {
	public LoginPageNotLoadedSuccessfullyException(String errMessage){
		super(errMessage);
	}
}
