package ee.ituk.memberlist.server.member;

import ee.ituk.memberlist.server.access.AccessCollection;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private final long id;
    private String name;
    private String persoalCode;
    private String studentCode;
    private String email;
    private MemberStatus status;
    private String cardNr;
    private String phoneNr;
    private LocalDate dateOfJoining;
    private AccessCollection accessCollection;

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersoalCode() {
        return persoalCode;
    }

    public void setPersoalCode(String persoalCode) {
        this.persoalCode = persoalCode;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }

    public String getCardNr() {
        return cardNr;
    }

    public void setCardNr(String cardNr) {
        this.cardNr = cardNr;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }


    public AccessCollection getAccessCollection() {
        return accessCollection;
    }

    public void modifyAccessCollection() {
       //TODO: mõelda mis oleks kõige prarem viis selle tegemiseks ja realiseerida
    }
}
