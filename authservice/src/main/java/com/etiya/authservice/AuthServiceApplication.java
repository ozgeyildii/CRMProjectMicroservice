package com.etiya.authservice;

import com.etiya.common.annotations.EnableSecurity;
import io.jsonwebtoken.io.Encoders;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSecurity
public class AuthServiceApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(AuthServiceApplication.class, args);
       /* KeyGenerator keyGenerator =  KeyGenerator.getInstance("HmacSHA256");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();
        String base64= Encoders.BASE64.encode(secretKey.getEncoded());
        System.out.println(base64);*/
	}

}
