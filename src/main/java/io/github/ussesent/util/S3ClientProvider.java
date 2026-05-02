package io.github.ussesent.util;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

public class S3ClientProvider {

    private static final String ACCESS_KEY;
    private static final String SECRET_KEY;
    private static final String ENDPOINT;
    private static final String REGION;

    static {
        Properties props = new Properties();
        try (InputStream in = S3ClientProvider.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ACCESS_KEY = props.getProperty("s3.access_key", "");
        SECRET_KEY = props.getProperty("s3.secret_key", "");
        ENDPOINT = props.getProperty("s3.endpoint", "https://storage.yandexcloud.net");
        REGION = props.getProperty("s3.region", "ru-central1");
    }

    public static S3Client getClient() {
        return S3Client.builder()
                .endpointOverride(URI.create(ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
                .region(Region.of(REGION))
                .serviceConfiguration(S3Configuration.builder()
                        .pathStyleAccessEnabled(true)
                        .build())
                .build();
    }
}