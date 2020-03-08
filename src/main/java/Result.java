import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private String candidateName;
    private Integer candidateVotes;

    public Result(String candidate_name, Integer candidate_votes) {
        this.candidateName = candidate_name;
        this.candidateVotes = candidate_votes;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Integer getCandidateVotes() {
        return candidateVotes;
    }

    public void setCandidateVotes(Integer candidateVotes) {
        this.candidateVotes = candidateVotes;
    }
}
