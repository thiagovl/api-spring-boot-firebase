package com.security.mssecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.mssecurity.security.AuthRequest;
import com.security.mssecurity.security.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	UserService service;
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwt;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	/**
	 * 
	 * @return Password encrypted
	 */
	@PostMapping("/pass")
	public String passw(@RequestBody String password) {
		return passwordEncoder.encode(password);
	}
	
	/**
	 * 
	 * @param email
	 * @return Authenticate
	 */
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password " + ex.getMessage());
        }
        return "Bearer " + jwt.generateToken(authRequest.getEmail());
    }
	
	
	/**
	 * 
	 * @return Message logout
	 */
	@GetMapping(value = "/logout", produces = "application/json")
	public ResponseEntity<String> logout() {
		String mensage = "Logout efetuado com sucesso!!!";
		return ResponseEntity.ok().body(mensage);
	}
}
