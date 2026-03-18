package in.ashokit.repo;


import in.ashokit.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    public RoleEntity findByName(String name);
}
