package ir.dotin.rest;

import ir.dotin.model.Penalty;
import ir.dotin.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/penalty")
public class PenaltyController {

    @Autowired
    private PenaltyService penaltyService;

    @PostMapping(value = "/{userId}/{penaltyId}")
    public ResponseEntity punishment(@PathVariable Long userId, @PathVariable Long penaltyId) {
        penaltyService.punishment(userId,penaltyId);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody Penalty penalty) {
        penaltyService.addPenalty(penalty);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping(value = "/change/{penaltyId}/{penaltyAmount}")
    public ResponseEntity changePenaltyAmount(@PathVariable Long penaltyId, @PathVariable Double penaltyAmount){
        penaltyService.changePenaltyAmount(penaltyId,penaltyAmount);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(value = "/pay/{userId}/{amount}")
    public ResponseEntity pay(@PathVariable Long userId, @PathVariable Double amount){
        penaltyService.payForPenalty(userId,amount);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List> getPenalties(){
        return ResponseEntity.accepted().body(penaltyService.getAllPenalties());
    }
}

