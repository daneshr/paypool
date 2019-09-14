package ir.dotin.repository;

import ir.dotin.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("select vote from Vote vote where vote.finished = 0")
    List getActiveVotes();

    @Transactional
    @Modifying
    @Query("UPDATE Vote vote set vote.finished=true WHERE vote.voteId=:voteId")
    void finishVoting(@Param("voteId") Long voteId);

}
