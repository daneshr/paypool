package ir.dotin.repository;

import ir.dotin.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenaltyRepository extends JpaRepository<Penalty,Long> {
}
