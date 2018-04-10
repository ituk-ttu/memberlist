package ee.ituk.memberlist.server.accesscollection;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "collections")
public class AccessCollectionController {
    private AccessCollectionService collectionService;

    public AccessCollectionController (AccessCollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @PostMapping(value = "add")
    public AccessCollection addCollection(@RequestBody AccessCollection collection) {
        return collectionService.addCollection(collection);
    }

    @GetMapping(value = "")
    public Iterable<AccessCollection> getAllCollections() {
        return collectionService.getAllCollections();
    }

    @GetMapping(value = "{id}")
    public AccessCollection getCollectionById(@PathVariable(value = "id") long id) {
        return collectionService.getCollectionById(id);
    }

    @PutMapping
    public AccessCollection saveByCollectionId(@RequestBody AccessCollection collection) {
        return collectionService.addCollection(collection);
    }
}
