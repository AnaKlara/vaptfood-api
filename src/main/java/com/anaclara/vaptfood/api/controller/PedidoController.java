package com.anaclara.vaptfood.api.controller;

import com.anaclara.vaptfood.api.assembler.PedidoInputDisassembler;
import com.anaclara.vaptfood.api.assembler.PedidoModelAssembler;
import com.anaclara.vaptfood.api.assembler.PedidoResumoModelAssembler;
import com.anaclara.vaptfood.api.model.PedidoModel;
import com.anaclara.vaptfood.api.model.PedidoResumoModel;
import com.anaclara.vaptfood.api.model.input.PedidoInput;
import com.anaclara.vaptfood.domain.exception.EntidadeNaoEncontradaException;
import com.anaclara.vaptfood.domain.exception.NegocioException;
import com.anaclara.vaptfood.domain.model.Pedido;
import com.anaclara.vaptfood.domain.model.Usuario;
import com.anaclara.vaptfood.domain.repository.PedidoRepository;
import com.anaclara.vaptfood.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

        return pedidoModelAssembler.toModel(pedido);
    }

}
