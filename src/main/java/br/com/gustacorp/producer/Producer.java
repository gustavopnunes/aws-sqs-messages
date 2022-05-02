package br.com.gustacorp.producer;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.Scanner;

public class Producer {

    public void sendMessage(){
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
        String queue = System.getenv("QUEUE_NAME");
        String awsAccountId = System.getenv("AWS_ACCOUNT_ID");
        String queueUrl = System.getenv("QUEUE_URL");
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a mensagem a ser enviada para a fila: ");
        String message = input.nextLine();
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();

        try {
            sqsClient.sendMessage(sendMessageRequest);
            System.out.println("Mensagem enviada!");
            }
        catch (Exception exception){
            System.out.println("Erro ao enviar mensagem! Verifique suas variaveis de ambiente");
            System.out.println("Erro: " + exception);
        }
    }
}

