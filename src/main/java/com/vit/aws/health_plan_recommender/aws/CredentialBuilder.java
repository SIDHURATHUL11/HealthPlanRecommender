package com.vit.aws.health_plan_recommender.aws;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

public class CredentialBuilder {

    public static StaticCredentialsProvider getCredentials() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(Credentials.ACCESS_KEY.value, Credentials.SECRET_KEY.value));
    }

    public enum Credentials {
        ACCESS_KEY("AKIASFUIRWKCWLUSCAVM"),
        SECRET_KEY("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");

        public final String value;
        Credentials(String value) {
            this.value = value;
        }
    }
}
