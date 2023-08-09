package kz.manap.flightstatusapi.repositories;

import kz.manap.flightstatusapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByCode(String code);
}
