package com.devsuperior.dsvendas.service;

import com.devsuperior.dsvendas.dto.SaleDTO;

import com.devsuperior.dsvendas.dto.SaleSuccessDTO;
import com.devsuperior.dsvendas.dto.SaleSumDTO;
import com.devsuperior.dsvendas.entities.Sale;

import com.devsuperior.dsvendas.repositories.SaleRepository;

import com.devsuperior.dsvendas.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SaleService {

    @Autowired
    SaleRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Transactional(readOnly = true)                               //busca paginada
    public Page<SaleDTO> findAll(Pageable pageable) { // o objeto Pageable serve para fazer a paginacao nas buscas
        sellerRepository.findAll(); //macete para evitar realizar a busca repetitiva de Seller
        Page<Sale> result = repository.findAll(pageable);
        return result.map( x -> new SaleDTO(x));
    }

    @Transactional(readOnly = true)
    public List<SaleSumDTO> amountGroupedBySeller(){
        return repository.amountGroupedBySeller();
    }

    @Transactional(readOnly = true)
    public List<SaleSuccessDTO> successGroupedBySeller(){
        return repository.successGroupedBySeller();
    }
}
