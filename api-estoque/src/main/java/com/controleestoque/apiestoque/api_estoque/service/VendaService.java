package com.controleestoque.api_estoque.service;

import com.controleestoque.api_estoque.dto.ItemVendaDTO;
import com.controleestoque.api_estoque.model.*;
import com.controleestoque.api_estoque.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemVendaRepository itemVendaRepository;

    // Construtor (Spring injeta automaticamente)
    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        ProdutoRepository produtoRepository,
                        ItemVendaRepository itemVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    @Transactional
    public Venda registrarVenda(Long clienteId, List<ItemVendaDTO> itens) {
        // 1. Busca cliente
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // 2. Cria venda
        Venda venda = new Venda();
        venda.setCliente(cliente);

        // 3. Processa itens
        for (ItemVendaDTO itemDTO : itens) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            Estoque estoque = produto.getEstoque();
            if (estoque == null || estoque.getQuantidade() < itemDTO.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // 4. Baixa no estoque
            estoque.setQuantidade(estoque.getQuantidade() - itemDTO.getQuantidade());

            // 5. Cria item de venda
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDTO.getQuantidade());
            itemVenda.setPrecoUnitario(produto.getPreco());

            venda.getItens().add(itemVenda);
        }

        // 6. Salva venda
        return vendaRepository.save(venda);
    }
}