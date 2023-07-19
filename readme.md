# Spring JMS Demo - Send Message and Receive Message in Fifo Queue

1. Update the SQS endpoint and queue name in SQSConfig.java
2. Create a new queuq
```bash
aws sqs create-queue --queue-name example.fifo --attributes '{"VisibilityTimeout":"60","FifoQueue":"true","ContentBasedDeduplication":"true","DeduplicationScope":"messageGroup","FifoThroughputLimit":"perMessageGroupId"}'
Or
awslocal sqs create-queue --queue-name example.fifo --attributes '{"VisibilityTimeout":"60","FifoQueue":"true","ContentBasedDeduplication":"true","DeduplicationScope":"messageGroup","FifoThroughputLimit":"perMessageGroupId"}'
```
3. Run [SpringJMSSendMessage.java](src%2Fmain%2Fjava%2Fsqs%2FSpringJMSSendMessage.java) to send message with same group id
4. Run [SpringJMSReceiveMessage.java](src%2Fmain%2Fjava%2Fsqs%2FSpringJMSReceiveMessage.java) to receive message
5. Verify the log of receive

Connect to AWS SQS
 - The second message will not be processed if the first message has not been processed.
```text
# log

DefaultMessageListenerContainer-5 1689768861280 receive,Group ID: Default, start..
DefaultMessageListenerContainer-5 1689768913178 receive,Group ID: Default ,done
DefaultMessageListenerContainer-4 1689768913182 receive,Group ID: Default, start..
DefaultMessageListenerContainer-4 1689768963529 receive,Group ID: Default ,done
DefaultMessageListenerContainer-4 1689768963543 receive,Group ID: Default, start..
DefaultMessageListenerContainer-4 1689769013939 receive,Group ID: Default ,done
...
```
Connect to LocalStack
 - The second message will also be processed if the first one is not finished.
```text
# log

start receive
DefaultMessageListenerContainer-4 1689768077073 receive,Group ID: Default, start..
DefaultMessageListenerContainer-5 1689768077309 receive,Group ID: Default, start..
DefaultMessageListenerContainer-1 1689768078329 receive,Group ID: Default, start..
DefaultMessageListenerContainer-3 1689768078438 receive,Group ID: Default, start..
DefaultMessageListenerContainer-2 1689768080545 receive,Group ID: Default, start..
DefaultMessageListenerContainer-4 1689768127150 receive,Group ID: Default ,done
DefaultMessageListenerContainer-4 1689768127150 receive,Group ID: Default, start..
DefaultMessageListenerContainer-5 1689768127339 receive,Group ID: Default ,done
DefaultMessageListenerContainer-5 1689768127340 receive,Group ID: Default, start..
DefaultMessageListenerContainer-1 1689768128372 receive,Group ID: Default ,done
DefaultMessageListenerContainer-1 1689768128372 receive,Group ID: Default, start..
DefaultMessageListenerContainer-3 1689768128480 receive,Group ID: Default ,done
DefaultMessageListenerContainer-3 1689768128480 receive,Group ID: Default, start..
DefaultMessageListenerContainer-2 1689768130584 receive,Group ID: Default ,done
```