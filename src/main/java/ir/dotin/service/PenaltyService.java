package ir.dotin.service;

import ir.dotin.model.Penalty;

import java.util.List;

public interface PenaltyService {

    void punishment(Long userId, Long penaltyId);

    void addPenalty(Penalty penalty);

    void changePenaltyAmount(Long penaltyId, Double newPenaltyAmount);

    List<Penalty> getAllPenalties();

    void payForPenalty(Long userId, Double amount);

}
