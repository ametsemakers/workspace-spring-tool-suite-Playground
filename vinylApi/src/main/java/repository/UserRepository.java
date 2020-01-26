package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	List<User> findByLastname(String lastname);
}
