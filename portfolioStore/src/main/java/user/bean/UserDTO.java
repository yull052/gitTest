package user.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data

public class UserDTO {
	
	private String userId; 
	private String userPwd;
	private String userName;
	private String userPhone;
	private String userEmail;
	
}
