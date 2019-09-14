package ir.dotin.dto;

public class UserVoteDTO {
    private Long userId;
    private Long voteId;
    private Boolean voteResult;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Boolean getVoteResult() {
        return voteResult;
    }

    public void setVoteResult(Boolean voteResult) {
        this.voteResult = voteResult;
    }
}
