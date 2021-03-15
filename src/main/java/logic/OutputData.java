package logic;

public class OutputData {
    private String statusMessage;
    private String resultMessage;

    public OutputData(String statusMessage, String resultMessage) {
        this.statusMessage = statusMessage;
        this.resultMessage = resultMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
