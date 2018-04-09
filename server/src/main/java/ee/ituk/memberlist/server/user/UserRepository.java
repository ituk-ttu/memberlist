package ee.ituk.memberlist.server.user;

import ee.ituk.memberlist.server.member.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByMemberEmail(String email);
}
