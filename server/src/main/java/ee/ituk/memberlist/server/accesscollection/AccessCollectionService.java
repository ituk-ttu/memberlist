package ee.ituk.memberlist.server.accesscollection;

import org.springframework.stereotype.Service;

@Service
public class AccessCollectionService {
    private AccessCollectionRepository collectionRepository;

    public AccessCollectionService(AccessCollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public AccessCollection addCollection(AccessCollection collection) {
        return collectionRepository.save(collection);
    }

    public AccessCollection getCollectionById(long id) {
        return collectionRepository.findOne(id);
    }

    public Iterable<AccessCollection> getAllCollections() {
        return collectionRepository.findAll();
    }

    public void deleteCollection(long id) {
        collectionRepository.delete(id);
    }

}
