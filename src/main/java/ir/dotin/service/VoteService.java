package ir.dotin.service;

import ir.dotin.dto.UserVoteDTO;
import ir.dotin.dto.VoteDTO;
import ir.dotin.model.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getAllActiveVotes();

    void voting(UserVoteDTO userVoteDTO);

    void create(VoteDTO voteDTO);

    void finishVoting(Long voteId);

    Integer getVotingResult(Long voteId);
}
