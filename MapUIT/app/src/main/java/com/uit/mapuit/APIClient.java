package com.uit.mapuit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoREkwZ2hyVlJvaE5zVy1wSXpZeDBpT2lHMzNlWjJxV21sRk4wWGE1dWkwIn0.eyJleHAiOjE3MDEwNzk1MDksImlhdCI6MTcwMDk5MzEwOSwianRpIjoiMWVjNzRjMmUtZjE0YS00Y2RlLWJiNDctNjc3ODk5ODEyNmRmIiwiaXNzIjoiaHR0cHM6Ly91aW90Lml4eGMuZGV2L2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiIxYmIwZTFkYy03MjYwLTRlNzAtYTkxYi0yNGEzNGFlNjBmZmUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJvcGVucmVtb3RlIiwic2Vzc2lvbl9zdGF0ZSI6ImU3MmE1ZjNjLTVjNDAtNGRhMS04YTVhLWI5MWNhNDQ2MmFlYiIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cHM6Ly91aW90Lml4eGMuZGV2Il0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJjcmVhdGUtcmVhbG0iLCJkZWZhdWx0LXJvbGVzLW1hc3RlciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJvcGVucmVtb3RlIjp7InJvbGVzIjpbIndyaXRlOmxvZ3MiLCJ3cml0ZTphc3NldHMiLCJyZWFkIiwid3JpdGU6YWRtaW4iLCJyZWFkOm1hcCIsInJlYWQ6bG9ncyIsInJlYWQ6YXNzZXRzIiwid3JpdGU6dXNlciIsInJlYWQ6dXNlcnMiLCJ3cml0ZTpydWxlcyIsInJlYWQ6cnVsZXMiLCJyZWFkOmluc2lnaHRzIiwid3JpdGU6YXR0cmlidXRlcyIsIndyaXRlIiwid3JpdGU6aW5zaWdodHMiLCJyZWFkOmFkbWluIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiJlNzJhNWYzYy01YzQwLTRkYTEtOGE1YS1iOTFjYTQ0NjJhZWIiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJtYWkgY3VvbmciLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJtYWljdW9uZyIsImdpdmVuX25hbWUiOiJtYWkiLCJmYW1pbHlfbmFtZSI6ImN1b25nIiwiZW1haWwiOiJtYWljdW9uZzg3MDlAZ21haWwuY29tIn0.nEZrpRTkVQhJRvImmYRslY1g_-Xt6kEwHIBVbFvE5_Uz2G-F7W9NpFtv9aGZJdEi_TeCpPZk7iLry_ZCq8VmHBeBeZap2EtyqbNY402vAWO2XPuIArclrony-HlG9aPOGlEKNHRvx_d95E3EOVRVOLpT12ZPTTHL_7B4JMwLXZ-fOC2ZkydCSs3KdtBsV4UlIFXrLdFXzl0sc2K_DZftVBcA-XfHXoaBXojVWkvXIoXyvbbkqfjaipYosxWAzTUJYxRIupl2CJ6txVk3aqsRU7fXFPb3pogbSnD4YhWgxlivLeeZkcAcN3YXYv8v6jUX1PchQ8x8kL9CoRdtEJ5Heg";
    public OkHttpClient getUnsafeOkHttpClient() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            builder.addInterceptor(chain -> {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .addHeader("Content-Type", "application/json");

                if (originalRequest.body() != null) {
                    long contentLength = originalRequest.body().contentLength();
                    requestBuilder.addHeader("Content-Length", String.valueOf(contentLength));
                }

                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Retrofit getClient() {
        OkHttpClient client = getUnsafeOkHttpClient();
        return new Retrofit.Builder()
                .baseUrl("https://uiot.ixxc.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
