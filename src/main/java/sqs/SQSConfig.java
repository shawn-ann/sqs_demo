package sqs;

public class SQSConfig {
    // http://localhost:4566
    // https://sqs.us-east-1.amazonaws.com
    public static final String serviceEndpoint = "http://localhost:4566";
    // 000000000000
    // 000759498753
    public static final String account = "000000000000";

    public static final String queueName = "example.fifo";
    public static final String queueURL = serviceEndpoint + "/" + account + "/" + queueName;
}
