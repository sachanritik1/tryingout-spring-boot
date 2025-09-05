package com.example.demo.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CreateJournalRequest {
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private String userId;
}
