package com.example.Restful.service.impl;

import com.example.Restful.model.dto.AddSupplementDTO;
import com.example.Restful.model.dto.SupplementDTO;
import com.example.Restful.model.entity.Supplement;
import com.example.Restful.repository.SupplementRepository;
import com.example.Restful.service.SupplementService;
import com.example.Restful.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class SupplementServiceImpl implements SupplementService {
    private final SupplementRepository supplementRepository;
    private final ModelMapper modelMapper;

    public SupplementServiceImpl(SupplementRepository supplementRepository, ModelMapper modelMapper) {
        this.supplementRepository = supplementRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SupplementDTO createSupplement(AddSupplementDTO addSupplementDTO) {
        Supplement supplement = supplementRepository.save(mapAddDTOToEntity(addSupplementDTO));
        return mapSupplementToDTO(supplement);

    }

    private SupplementDTO mapSupplementToDTO(Supplement supplement){
        return modelMapper.map(supplement, SupplementDTO.class);
    }
    private Supplement mapAddDTOToEntity(AddSupplementDTO addSupplementDTO){
        return modelMapper.map(addSupplementDTO, Supplement.class);
    }

    @Override
    public SupplementDTO getById(Long id) {
        return supplementRepository
                .findById(id)
                .map(this::mapSupplementToDTO)
                .orElseThrow(ObjectNotFoundException::new);
    }


    @Override
    public PagedModel<SupplementDTO> getAllSupplements(Pageable pageable){
        return new PagedModel<>(supplementRepository
                .findAll(pageable)
                .map(this::mapSupplementToDTO)

        );
    }

    @Override
    public void deleteSupplement(Long id){
        supplementRepository.deleteById(id);
    }
}
