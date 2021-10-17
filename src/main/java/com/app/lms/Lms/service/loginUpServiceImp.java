package com.app.lms.Lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.app.lms.Lms.Repository.signUpRepository;
import com.app.lms.Lms.configuration.JwtTokenUtil;
import com.app.lms.Lms.exceptions.EmptyPasswordException;
import com.app.lms.Lms.exceptions.IncorectCredientialsException;
import com.app.lms.Lms.exceptions.UserMissingInformationException;
import com.app.lms.Lms.model.signUpModel;
import com.app.lms.Lms.model.signUpResponse;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

@Service
public class loginUpServiceImp implements LoginService {

	private signUpRepository signupRepository;
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	private ApiUtils apiUtils;

	@Autowired
	public loginUpServiceImp(signUpRepository signupRepository, ApiUtils apiUtils) {
		Assert.notNull(signupRepository, "signupRepository must not be null!");
		Assert.notNull(apiUtils, "ApiUtils must not be null!");
		this.signupRepository = signupRepository;
		this.apiUtils = apiUtils;
	}

	@Override
	public ResponseEntity<signUpResponse> loginUser(signUpModel loginBody, HttpServletRequest request) {

		if (null == loginBody.getUsername() || loginBody.getUsername().length() == 0) {
			throw new UserMissingInformationException();
		}

		else if (null == loginBody.getPassword() || loginBody.getPassword().length() == 0) {
			throw new EmptyPasswordException();
		}

		

		else if  (null != signupRepository.findUser(loginBody.getUsername()) && 
				passwordEncoder.matches(loginBody.getPassword(), (signupRepository.findUser(loginBody.getUsername()).getPassword()))){
			

			try {
				authenticate(loginBody.getUsername(), loginBody.getPassword());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

			
			
			final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(loginBody.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			signUpModel user = signupRepository.findUser(loginBody.getUsername());
			signUpResponse responseBody = new signUpResponse();
			responseBody.setUserID(user.getUserID());
			responseBody.setLoginId(user.getLoginId());
			responseBody.setCreatedAt(user.getCreatedAt());
			responseBody.setUsername(user.getUsername());
			responseBody.setToken(token);
			responseBody.setCode("SUCCESS");
			responseBody.setMessage("User Found");
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Location", contactUrlHelper(user, request));

			return new ResponseEntity<signUpResponse>(responseBody, responseHeaders, HttpStatus.OK);
		}
		
		else {
			throw new IncorectCredientialsException();
		}
	}

	/*
	 * Private Methods
	 */

	private String contactUrlHelper(signUpModel signUpBody, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();

		resourcePath.append(request.getRequestURL());
		resourcePath.append("/");
		resourcePath.append(signUpBody.getUserID());

		return resourcePath.toString();
	}
	
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}