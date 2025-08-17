package com.example.portfolio.project;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173") // 개발용 CORS 허용
public class ProjectController {

    @GetMapping
    public List<ProjectDto> list() {
        return List.of(
                new ProjectDto(
                        1L, "Campus Community & Map",
                        "주변 편의시설 공유 + 커뮤니티",
                        List.of("Django","Kakao Map","PostgreSQL"),
                        "https://picsum.photos/seed/p1/800/400",
                        "#", "#"
                ),
                new ProjectDto(
                        2L, "Data Crawler & ETL",
                        "크롤링/정제/적재 배치 파이프라인",
                        List.of("Java","Spring Batch","MySQL"),
                        "https://picsum.photos/seed/p2/800/400",
                        "#", "#"
                )
        );
    }
}
