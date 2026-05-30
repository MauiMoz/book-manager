package org.example.bookmanager.backend.repository;

import org.example.bookmanager.backend.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPublisherRepository extends JpaRepository<Publisher, Long> {}
