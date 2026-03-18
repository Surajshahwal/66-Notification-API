package in.ashokit.repo;

import in.ashokit.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPwd(String email, String pwd);
}
