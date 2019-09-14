package ir.dotin.service;

import ir.dotin.dto.UserVoteDTO;
import ir.dotin.dto.VoteDTO;
import ir.dotin.model.Penalty;
import ir.dotin.model.User;
import ir.dotin.model.UserVote;
import ir.dotin.model.Vote;
import ir.dotin.repository.UserRepository;
import ir.dotin.repository.UserVoteRepository;
import ir.dotin.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserVoteRepository userVoteRepository;

    @Override
    public List<Vote> getAllActiveVotes() {
        return voteRepository.getActiveVotes();
    }

    @Override
    public void voting(UserVoteDTO userVoteDTO) {
        User user = userRepository.getOne(userVoteDTO.getUserId());
        Vote vote = voteRepository.getOne(userVoteDTO.getVoteId());
        UserVote userVote = new UserVote();
        userVote.setUser(user);
        userVote.setVote(vote);
        userVote.setVoteResult(userVoteDTO.getVoteResult());
        userVoteRepository.save(userVote);
    }

    @Override
    public void create(VoteDTO voteDTO) {
        Vote vote = new Vote();
        User user = new User();
        user.setUserId(voteDTO.getUserId());
        Penalty penalty = new Penalty();
        penalty.setPenaltyId(voteDTO.getPenaltyId());
        vote.setUser(user);
        vote.setPenalty(penalty);
        vote.setFinished(Boolean.FALSE);
        voteRepository.save(vote);
    }

    @Override
    public void finishVoting(Long voteId) {
        voteRepository.finishVoting(voteId);
    }

    @Override
    public Integer getVotingResult(Long voteId) {
        return userVoteRepository.getVotingResult(voteId);
    }
}
