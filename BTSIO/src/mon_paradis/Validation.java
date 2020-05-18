package mon_paradis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	public static boolean emailValidation( String email) {
		
		 boolean status= false;
		 
		 String email_pattern = "^\\w{6,30}[@]+(gmail|hotmail|yahoo|outlook).[\\w]{2,3}$";
		 Pattern pattern = Pattern.compile(email_pattern);
		 Matcher matcher =pattern.matcher(email);
	       if(matcher.matches()){
	    	   status = true;
	       }else{
	    	  
	    	   status = false;
	       }
	       
	       
	       return status;
	}

	public static boolean Validation(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
		
	
	
}
