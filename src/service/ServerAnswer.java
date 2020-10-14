package service;

public class ServerAnswer {
    private boolean isSuccess;
    private String feedback;

    public ServerAnswer(boolean isSuccess, String feedback) {
        this.isSuccess = isSuccess;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
