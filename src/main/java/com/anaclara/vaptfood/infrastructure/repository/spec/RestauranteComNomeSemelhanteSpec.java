package com.anaclara.vaptfood.infrastructure.repository.spec;

import com.anaclara.vaptfood.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@AllArgsConstructor // anot do lombok que cria um construtor que recebe e atribui variaveis
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

    private static final long serialVersionUID = 1L;

    private String nome;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        return builder.like(root.get("nome"), "%" + nome + "%");
    }

}
