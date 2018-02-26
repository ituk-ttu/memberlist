package ee.ituk.memberlist.server.door;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Door {

    @Id
    @GeneratedValue
    private final long id;
    @Getter
    private String name;

}
