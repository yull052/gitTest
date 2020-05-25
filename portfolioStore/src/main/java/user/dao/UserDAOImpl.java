package user.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import user.bean.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;

	//	아이디 중복 체크
	@Override
	public UserDTO dupeId(String userId) {
		return sqlSession.selectOne("userSQL.dupeId", userId);
	}
	
	//	로그인
	@Override
	public UserDTO login(Map<String, String> map) {
		return sqlSession.selectOne("userSQL.login", map);
	}
	
	//	회원가입
	@Override
	public void join(UserDTO userDTO) {
		sqlSession.insert("userSQL.join", userDTO);
	}
	
	// 정보찾기
	@Override
	public String findInfo(UserDTO userDTO) {
		return sqlSession.selectOne("userSQL.findInfo", userDTO);
	}
	
	//	비밀번호 변경
	@Override
	public void chanPwd(UserDTO userDTO) {
		sqlSession.update("userSQL.chanPwd", userDTO);
	}
	


}
