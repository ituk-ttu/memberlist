package ee.ituk.memberlist.server.accesscollection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessCollectionRepository extends CrudRepository<AccessCollection, Long> {
}
