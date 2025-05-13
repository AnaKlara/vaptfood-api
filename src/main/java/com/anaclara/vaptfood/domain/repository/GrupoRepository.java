package com.anaclara.vaptfood.domain.repository;

import com.anaclara.vaptfood.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
