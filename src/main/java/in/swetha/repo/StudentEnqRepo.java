package in.swetha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.swetha.entity.StudentEnq;

public interface StudentEnqRepo extends JpaRepository<StudentEnq, Integer> {

}
