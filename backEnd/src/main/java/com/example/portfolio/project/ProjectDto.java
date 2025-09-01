package com.example.portfolio.project;
import java.util.List;

public record ProjectDto(
        Long id,
        String title,
        String description,
        List<String> tags,
        String imageUrl,
        String readmeUrl,
        String codeUrl,
        List<String> images
) {}