package ro.master.pricingengine.beans;

public class SimpleRestResponse {
    private ResponseStatus status;
    private String message;

    public SimpleRestResponse() {
        status = ResponseStatus.OK;
    }

    public SimpleRestResponse(String message) {
        this.status = ResponseStatus.NOT_OK;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
