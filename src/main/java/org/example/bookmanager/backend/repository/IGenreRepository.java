package org.example.bookmanager.backend.repository;

import org.example.bookmanager.backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IGenreRepository extends JpaRepository<Genre, Long> {}
