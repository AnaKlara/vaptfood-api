package com.anaclara.vaptfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // o spring não deve ser levada em conta para fins de instanciação
public interface CustomJpaRepository<T,ID>  extends JpaRepository<T,ID> {

    Optional<T> buscarPrimeiro();
}
