package ee.ituk.memberlist.server.access;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends CrudRepository<Access, Long> {
}
