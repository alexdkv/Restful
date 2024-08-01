package com.example.Restful.service;

import com.example.Restful.model.dto.AddSupplementDTO;
import com.example.Restful.model.dto.SupplementDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface SupplementService {

    SupplementDTO createSupplement(AddSupplementDTO addSupplementDTO);

    SupplementDTO getById(Long id);

    PagedModel<SupplementDTO> getAllSupplements(Pageable pageable);

    void deleteSupplement(Long id);
}
