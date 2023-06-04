package Model;

import java.math.BigInteger;
import de.mkammerer.argon2.Argon2Factory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Argon2 {
	public String argon2(String str){
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,5*1024,2);
		/* String myPassword = "ThisIsMyPassword"; */
		String encodedPassword = encoder.encode(str);
		/*
		 * System.out.println(encodedPassword); boolean validPassword =
		 * encoder.matches(myPassword, encodedPassword);
		 * System.out.println(validPassword);
		 */
		return encodedPassword;
	}
	

}