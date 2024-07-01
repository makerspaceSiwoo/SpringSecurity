package user.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import user.security.domain.Users;

@Mapper
public interface UserDao {
	@Select("select * from users where id = #{id}")
	Users findById(String id);
}
