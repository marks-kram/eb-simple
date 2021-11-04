package de.test.beanstalk.simple.model;

public class RemovedResponse {

    private String message;

    public RemovedResponse(String uuid) {
        this.message = "removed successfully. uuid: " + uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
