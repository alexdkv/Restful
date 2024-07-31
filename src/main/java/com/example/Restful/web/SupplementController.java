package com.example.Restful.web;

import com.example.Restful.model.dto.AddSupplementDTO;
import com.example.Restful.model.dto.SupplementDTO;
import com.example.Restful.service.SupplementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplementController {
    private final SupplementService supplementService;

    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @PostMapping("/supplement")
    public ResponseEntity<SupplementDTO> createSupplement(@RequestBody AddSupplementDTO addSupplementDTO){
        SupplementDTO supplementDTO = supplementService.createSupplement(addSupplementDTO);
        return ResponseEntity.ok(supplementDTO);
    }
}
