package com.example.demo.repository;

import com.example.demo.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
