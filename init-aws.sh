#!/bin/bash

TOPIC="process_document"
QUEUE_NAME="process_document_queue"
ENDPOINT_URL="http://localhost:4566"
REGION="us-east-1"
BUCKET_NAME="document-processor"


aws sqs create-queue --queue-name "$QUEUE_NAME" --endpoint-url "$ENDPOINT_URL" --region "$REGION"

QUEUE_URL=$(aws sqs get-queue-url --queue-name "$QUEUE_NAME" --endpoint-url "$ENDPOINT_URL" --region "$REGION" --output text --query 'QueueUrl')
QUEUE_ARN=$(aws sqs get-queue-attributes --queue-url "$QUEUE_URL" --attribute-name QueueArn --endpoint-url "$ENDPOINT_URL" --region "$REGION" --output text --query 'Attributes.QueueArn')

TOPIC_ARN=$(aws sns create-topic --name "$TOPIC" --endpoint-url "$ENDPOINT_URL" --region "$REGION" --output text --query 'TopicArn')
aws sns subscribe --topic-arn "$TOPIC_ARN" --protocol sqs --notification-endpoint "$QUEUE_ARN" --endpoint-url "$ENDPOINT_URL" --region "$REGION" --attributes RawMessageDelivery=true

aws s3api create-bucket --bucket "$BUCKET_NAME" --endpoint-url "$ENDPOINT_URL" --region "$REGION"