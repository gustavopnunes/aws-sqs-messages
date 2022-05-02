# AWS SQS SENDER AND RECEIVER

#### Simple service for produce and consume sqs messages

### HOW TO USE:

 - With maven properly installed, get the values on your aws account and put the following environment variables in your bash, according to your system: 
    * AWS_ACCES_KEY="your_user_access_key"
    * AWS_SECRET_KEY="your_user_secret_key"
    * QUEUE_NAME="your_queue_name"
    * AWS_ACCOUNT_ID="your_aws_account_id"
    * QUEUE_URL="your_queue_arn"


 - Inside the project directory, run the following command: 
```
./start.sh
```
In case of errors, give the script the appropriate execute permissions: 
```
chmod +x start.sh
```

## DIFFERENCES BETWEEN SNS AND SQS: 

SNS (Simple Notification Service) is a service for distributing notifications that works through topics, in which interested parties subscribe by email, sms, lambda functions, http, among others. When a message is triggered for the topic, all subscribers receive it simply, quickly and efficiently.

On the other hand, SQS (Simple Queue Service) is a passive message queue service, that is, while in SNS messages are sent to all subscribers immediately after being triggered, in SQS these messages are accumulated in a queue that will wait for the interested parties. to consume.

---
# PT - BR
### COMO USAR: 
 - Com o maven instalado, obtenha os valores na sua conta aws e insira-os nas seguintes variáveis de ambiente de acordo com seu sistema operacional:
    * AWS_ACCES_KEY="sua_access_key"
    * AWS_SECRET_KEY="sua_secret_key"
    * QUEUE_NAME="nome_da_sua_fila"
    * AWS_ACCOUNT_ID="sua_account_id"
    * QUEUE_URL="url_da_sua_fila"

 
- Navegue até o diretório do projeto e rode o seguinte comando: 
```
./start.sh
```
  
Em caso de erros, dê ao script as permissões de execução necessárias: 
```
chmod +x start.sh
```

## DIFERENÇAS ENTRE SNS E SQS

SNS (Simple Notification Service) é um serviço para distribuição de notificações que funciona através de tópicos, nos quais as partes interessadas se inscrevem por email, sms, funções lambda, http, entre outros. Quando uma mensagem é disparada para o tópico, todos os inscritos a recebem de maneira simples, rápida e eficiente.

Já o SQS (Simple Queue Service) é um serviço de fila de mensagens passivo, ou seja, enquanto no SNS as mensagens são enviadas a todos os inscritos logo após disparadas, no SQS essas mensagens são acumuladas em uma fila que irá aguardar as partes interessadas a consumirem. 