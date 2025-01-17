package com.gapi.spring_rest_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuitarRepository extends JpaRepository<Guitar, Long> {
    List<Guitar> findByMake(String make);
}
