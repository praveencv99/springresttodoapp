package com.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoAppRepository extends JpaRepository<Task, Long> {

}
