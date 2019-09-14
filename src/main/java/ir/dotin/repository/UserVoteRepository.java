package ir.dotin.repository;

import ir.dotin.model.UserVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserVoteRepository extends JpaRepository<UserVote, Long> {

    @Query("SELECT count(userVote.voteResult) FROM UserVote userVote where userVote.vote.id = :voteId and userVote.vote.finished=true")
    Integer getVotingResult(@Param("voteId") Long voteId);
}
