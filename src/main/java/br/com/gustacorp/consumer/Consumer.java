package br.com.gustacorp.consumer;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

public class Consumer {
    public void readMessages() {
        String queue = System.getenv("QUEUE_NAME");
        String awsAccountId = System.getenv("AWS_ACCOUNT_ID");

        AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return System.getenv("AWS_ACCESS_KEY");
                    }

                    @Override
                    public String secretAccessKey() {
                        return System.getenv("AWS_SECRET_KEY");
                    }
                };
            }
        };

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();

        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName(queue)
                .queueOwnerAWSAccountId(awsAccountId)
                .build();
        GetQueueUrlResponse result = sqsClient.getQueueUrl(request);

        List<Message> messageList = receiveMessages(sqsClient, result.queueUrl());

        for (Message msg : messageList) {
            System.out.println("Mensagem: " + msg.body());
        }

        deleteMessages(sqsClient, result.queueUrl(), messageList);
        }

        public static List<Message> receiveMessages(SqsClient sqsClient, String queueUrl){
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .waitTimeSeconds(20)
                .maxNumberOfMessages(5)
                .build();
            return sqsClient.receiveMessage(receiveMessageRequest).messages();

    }

    public static void deleteMessages(SqsClient sqsClient, String queueUrl,  List<Message> messages) {
        for (Message message : messages) {
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(message.receiptHandle())
                    .build();
            sqsClient.deleteMessage(deleteMessageRequest);
        }
    }
}
