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
                        "/readme/csn.md", "#",
                        List.of( // 추가 이미지들
                                 )
                ),
                new ProjectDto(
                        2L, "Portfolio WebSite",
                        "스프링과 뷰를 이용한 자기소개 웹사이트",
                        List.of("Java","Spring Boot","MySQL"),
                        "/images/projects/project2/project2-1.png",
                        "/readme/portfolio.md", "https://github.com/LeeJY0116/portfolioWebsite",
                        List.of(
                                "/images/projects/project2/project2-1.png",
                                "/images/projects/project2/project2-2.png"
                        )
                )
        );
    }
}
