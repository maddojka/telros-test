package com.soroko.telrostest.repository;

import com.soroko.telrostest.entity.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This class consists SQL queries to create, read, update or delete image
 * from database
 *
 * @author yuriy.soroko
 */
@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {
    public Optional<Image> findByName(String name);
}
