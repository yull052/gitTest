package user.dao;

import java.util.Map;

import user.bean.UserDTO;

public interface UserDAO {

	public UserDTO dupeId(String userId);

	public void join(UserDTO userDTO);

	public UserDTO login(Map<String, String> map);

	public String findInfo(UserDTO userDTO);

	public void chanPwd(UserDTO userDTO);

}
