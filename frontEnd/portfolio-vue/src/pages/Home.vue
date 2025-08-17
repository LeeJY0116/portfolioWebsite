<template>
  <section class="py-5">
    <div class="row align-items-center">
      <div class="col-lg-7">
        <span class="badge text-bg-primary mb-3">Java / Spring Developer</span>
        <h1 class="display-5 fw-bold mb-3">
          만들고 성장하는 개발자 <span class="text-primary">이주엽</span>입니다
        </h1>
        <p class="text-muted mb-4">
          Spring Boot와 SQL 중심으로 확장 가능한 백엔드를 만들고,
          REST API와 보안을 고려한 설계를 지향합니다.
        </p>
        <div class="d-flex gap-2">
          <RouterLink class="btn btn-primary" to="/projects">View Projects</RouterLink>
          <a class="btn btn-outline-secondary" href="/resume.pdf" download>Download CV</a>
        </div>
      </div>
      <div class="col-lg-5 mt-4 mt-lg-0">
        <div class="ratio ratio-1x1 rounded" style="background: linear-gradient(135deg,#6366f1,#22d3ee)"></div>
      </div>
    </div>
  </section>

  <section class="py-5 border-top">
    <h2 class="h4 mb-3">What I value</h2>
    <ul class="list-unstyled row g-3">
      <li class="col-md-6">· 가독성과 일관성을 갖춘 코드</li>
      <li class="col-md-6">· 문제 정의와 빠른 피드백 루프</li>
      <li class="col-md-6">· 데이터 기반의 의사결정</li>
      <li class="col-md-6">· 사용자 경험을 해치지 않는 보안</li>
    </ul>
  </section>
</template>
