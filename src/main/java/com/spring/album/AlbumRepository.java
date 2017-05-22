package com.spring.album;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
     List<Album> findByLanguage(String language);

}
