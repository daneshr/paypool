package ir.dotin.model;

import javax.persistence.*;

@Entity
@Table(name = "user_vote")
public class UserVote {
    private Long id;
    private User user;
    private Vote vote;
    private Boolean voteResult;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "vote_id")
    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Column(name = "vote_result")
    public Boolean getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(Boolean voteResult) {
        this.voteResult = voteResult;
    }
}
