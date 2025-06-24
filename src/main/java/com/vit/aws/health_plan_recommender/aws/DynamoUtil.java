package com.vit.aws.health_plan_recommender.aws;

import com.vit.aws.health_plan_recommender.model.NutritionRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamoUtil {

    DynamoDbClient client;

    public DynamoUtil() {
        client = DynamoDbClient.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(CredentialBuilder.getCredentials())
                .build();
    }

    public Map<String, String> getDynamoDBItem(String tableName, String primaryKey, String sortKey) {

        HashMap<String, AttributeValue> keyToGet = new HashMap<>();

        keyToGet.put("primaryKey", AttributeValue.builder()
                .s(primaryKey).build());
        keyToGet.put("sortKey", AttributeValue.builder()
                .s(sortKey).build());

        GetItemRequest request = GetItemRequest.builder()
                .key(keyToGet)
                .tableName(tableName)
                .build();
        Map<String, String> resultMap = new HashMap<>();
        try {
            Map<String, AttributeValue> returnedItem = client.getItem(request).item();

            if (returnedItem != null) {
                Set<String> keys = returnedItem.keySet();
                for (String key1 : keys) {
                    if (!key1.equals("primaryKey") && !key1.equals("sortKey")) {
                        resultMap.put(key1, returnedItem.get(key1).s());
                    }
                }
            }

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return resultMap;
    }

    public void createNutritionPlan(String tableName,
                                    NutritionRequest nutritionRequest, String topicArn) {

        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("primaryKey", AttributeValue.builder().s(nutritionRequest.getName()).build());
        itemValues.put("sortKey", AttributeValue.builder().s(nutritionRequest.getEmailId()).build());
        itemValues.put("age", AttributeValue.builder().n(String.valueOf(nutritionRequest.getAge())).build());
        itemValues.put("gender", AttributeValue.builder().s(nutritionRequest.getGender()).build());
        itemValues.put("healthIssue", AttributeValue.builder().s(nutritionRequest.getHealthIssue().toString()).build());
        itemValues.put("topicArn", AttributeValue.builder().s(topicArn).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();

        try {
            PutItemResponse response = client.putItem(request);
            System.out.println(tableName + " was successfully updated. The request id is "
                    + response.responseMetadata().requestId());

        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The Amazon DynamoDB table \"%s\" can't be found.\n", tableName);
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            System.exit(1);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
