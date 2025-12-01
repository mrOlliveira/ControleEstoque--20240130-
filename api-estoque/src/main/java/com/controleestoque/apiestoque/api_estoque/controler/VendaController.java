package com.controleestoque.api_estoque.controller;

import com.controleestoque.api_estoque.dto.ItemVendaDTO;
import com.controleestoque.api_estoque.model.Venda;
import com.controleestoque.api_estoque.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<?> registrarVenda(@RequestBody VendaRequest request) {
        try {
            Venda venda = vendaService.registrarVenda(request.getClienteId(), request.getItens());
            return ResponseEntity.status(HttpStatus.CREATED).body(venda);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

// Esta classe fica NO MESMO ARQUIVO, abaixo do controller
class VendaRequest {
    private Long clienteId;
    private List<ItemVendaDTO> itens;

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<ItemVendaDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaDTO> itens) { this.itens = itens; }
}