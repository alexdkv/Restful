package com.example.Restful.service;

import com.example.Restful.model.dto.AddSupplementDTO;
import com.example.Restful.model.dto.SupplementDTO;
import com.example.Restful.model.entity.Supplement;
import com.example.Restful.repository.SupplementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SupplementService {
    private final SupplementRepository supplementRepository;
    private final ModelMapper modelMapper;

    public SupplementService(SupplementRepository supplementRepository, ModelMapper modelMapper) {
        this.supplementRepository = supplementRepository;
        this.modelMapper = modelMapper;
    }


    public SupplementDTO createSupplement(AddSupplementDTO addSupplementDTO) {
        Supplement supplement = supplementRepository.save(map(addSupplementDTO));
        return map(supplement);

    }

    private SupplementDTO map(Supplement supplement){
        return modelMapper.map(supplement, SupplementDTO.class);
    }
    private Supplement map(AddSupplementDTO addSupplementDTO){
        return modelMapper.map(addSupplementDTO, Supplement.class);
    }
}
