package com.mockaroo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockaroo.entities.Test_2;

@Repository
public interface Test2Repository extends JpaRepository<Test_2, Integer> {

}
