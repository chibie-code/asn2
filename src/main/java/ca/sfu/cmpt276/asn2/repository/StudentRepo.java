package ca.sfu.cmpt276.asn2.repository;

import ca.sfu.cmpt276.asn2.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// <Student, Integer> == <Student, ID typ>
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    // converts the "camelCase" method name to SQL command
    List<Student> findByUid(int uid);
}
