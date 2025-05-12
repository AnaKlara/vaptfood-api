package com.anaclara.vaptfood.core.jackson;

import com.anaclara.vaptfood.api.model.mixin.CidadeMixin;
import com.anaclara.vaptfood.api.model.mixin.CozinhaMixin;
import com.anaclara.vaptfood.api.model.mixin.RestauranteMixin;
import com.anaclara.vaptfood.domain.model.Cidade;
import com.anaclara.vaptfood.domain.model.Cozinha;
import com.anaclara.vaptfood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }

}
