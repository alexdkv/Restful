package com.example.Restful.web;

import com.example.Restful.model.dto.AddSupplementDTO;
import com.example.Restful.model.dto.SupplementDTO;
import com.example.Restful.service.impl.SupplementServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SupplementController {
    private final SupplementServiceImpl supplementService;

    public SupplementController(SupplementServiceImpl supplementService) {
        this.supplementService = supplementService;
    }

    @PostMapping("/supplement")
    public ResponseEntity<SupplementDTO> createSupplement(@RequestBody AddSupplementDTO addSupplementDTO){
        SupplementDTO supplementDTO = supplementService.createSupplement(addSupplementDTO);
        return ResponseEntity.ok(supplementDTO);
    }

    @GetMapping("supplement/{id}")
    public ResponseEntity<SupplementDTO> singleSupplement(@PathVariable("id") Long id){
        return ResponseEntity.ok(supplementService.getById(id));
    }

    @GetMapping("/supplements")
    public ResponseEntity<PagedModel<SupplementDTO>> getAllSupplements(
            @PageableDefault
                            (size = 3,
                            sort = "id")Pageable pageable){
        return ResponseEntity.ok(supplementService.getAllSupplements(pageable));
    }

    @DeleteMapping("supplement/{id}")
    public ResponseEntity<SupplementDTO> deleteSupplementById(@PathVariable("id")Long id){
        supplementService.deleteSupplement(id);
        return ResponseEntity.noContent().build();
    }
}
