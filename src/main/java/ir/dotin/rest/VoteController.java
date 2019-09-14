package ir.dotin.rest;

import ir.dotin.dto.UserVoteDTO;
import ir.dotin.dto.VoteDTO;
import ir.dotin.model.Vote;
import ir.dotin.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/get")
    public ResponseEntity<List> getActiveVotes(){
        return ResponseEntity.accepted().body(voteService.getAllActiveVotes());
    }

    @PutMapping(value = "/voting", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity voting(@RequestBody UserVoteDTO userVote){
        voteService.voting(userVote);
        return ResponseEntity.accepted().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody VoteDTO voteDTO){
        voteService.create(voteDTO);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping(value = "/finish/{voteId}")
    public ResponseEntity finishVoting(@PathVariable Long voteId){
        voteService.finishVoting(voteId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/result/{voteId}")
    public ResponseEntity<Integer> getVotingResult(@PathVariable Long voteId){
        return ResponseEntity.accepted().body(voteService.getVotingResult(voteId));
    }
}
