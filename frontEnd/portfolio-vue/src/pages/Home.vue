<template>
  <!-- Hero (기존) -->
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
        <div class="ratio ratio-1x1 rounded shadow-lg hero-box"></div>
      </div>
    </div>
  </section>

  <!-- ABOUT ME -->
  <section class="py-5 border-top">
    <div class="d-flex align-items-center gap-2 mb-4">
      <i class="bi bi-people fs-3 text-primary"></i>
      <h2 class="section-title m-0">ABOUT ME</h2>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
      <div class="col">
        <div class="d-flex align-items-start gap-3">
          <i class="bi bi-person fs-4 text-secondary"></i>
          <div>
            <div class="text-muted small">이름</div>
            <div class="fw-semibold">이주엽</div>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="d-flex align-items-start gap-3">
          <i class="bi bi-calendar-event fs-4 text-secondary"></i>
          <div>
            <div class="text-muted small">생년월일</div>
            <div class="fw-semibold">99.01.16</div>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="d-flex align-items-start gap-3" >
          <!-- i class="bi bi-geo-alt fs-4 text-secondary"></i-->
          <div>
            <div class="text-muted small"></div>
            <div class="fw-semibold"></div>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="d-flex align-items-start gap-3">
          <i class="bi bi-telephone fs-4 text-secondary"></i>
          <div>
            <div class="text-muted small">연락처</div>
            <div class="fw-semibold">010-8288-6297</div>
          </div>
        </div>
      </div>

      <div class="col">
        <div class="d-flex align-items-start gap-3">
          <i class="bi bi-envelope fs-4 text-secondary"></i>
          <div>
            <div class="text-muted small">이메일</div>
            <div class="fw-semibold">lcg0116@gmail.com</div>
          </div>
        </div>
      </div>

<!-- 추가하고 싶은 건 이 단락에 추가 -->

    </div>
  </section>

  <!-- SKILLS -->
  <section class="py-5 skills-section">
    <div class="d-flex align-items-center gap-2 mb-4">
      <i class="bi bi-book fs-3 text-dark"></i>
      <h2 class="section-title m-0">SKILLS</h2>
    </div>

    <div class="p-4 p-md-5 rounded-4 bg-body-tertiary shadow-sm">
      <div class="row gy-4 align-items-start">
        <!-- Language -->
        <div class="col-sm-3">
          <div class="d-flex align-items-center gap-2 fw-semibold">
            <i class="bi bi-braces fs-5"></i> Language
          </div>
        </div>
        <div class="col-sm-9">
          <div class="d-flex flex-wrap gap-2">
            <span class="chip dark">Java</span>
            <span class="chip">Python</span>
            <span class="chip">JavaScript</span>
          </div>
        </div>

        <!-- Frontend -->
        <div class="col-sm-3">
          <div class="d-flex align-items-center gap-2 fw-semibold">
            <i class="bi bi-window-stack fs-5"></i> Frontend
          </div>
        </div>
        <div class="col-sm-9">
          <div class="d-flex flex-wrap gap-2">
            <span class="chip dark">Vue 3</span>
          </div>
        </div>

        <!-- Backend -->
        <div class="col-sm-3">
          <div class="d-flex align-items-center gap-2 fw-semibold">
            <i class="bi bi-hdd-network fs-5"></i> Backend
          </div>
        </div>
        <div class="col-sm-9">
          <div class="d-flex flex-wrap gap-2">
            <span class="chip dark">Spring Boot</span>

          </div>
        </div>

        <!-- DB -->
        <div class="col-sm-3">
          <div class="d-flex align-items-center gap-2 fw-semibold">
            <i class="bi bi-database fs-5"></i> Database
          </div>
        </div>
        <div class="col-sm-9">
          <div class="d-flex flex-wrap gap-2">
            <span class="chip dark">MySQL</span>
          </div>
        </div>

        <!-- DevOps -->
        <div class="col-sm-3">
          <div class="d-flex align-items-center gap-2 fw-semibold">
            <i class="bi bi-layers fs-5"></i> DevOps
          </div>
        </div>
        <div class="col-sm-9">
          <div class="d-flex flex-wrap gap-2">
            <span class="chip dark">AWS EC2</span>
          </div>
        </div>
      </div>
    </div>
  </section>
<!-- ARCHIVING (full-bleed, 어둡게) -->
<section class="arch-bleed arch-section py-5 my-4">
  <div class="container">
    <div class="d-flex align-items-center gap-2 mb-4">
      <i class="bi bi-archive fs-3 text-light"></i>
      <h2 class="section-title text-white m-0">ARCHIVING</h2>
    </div>

    <div class="row g-4">
      <div v-for="item in archives" :key="item.id" class="col-md-6">
        <a :href="item.url" target="_blank" class="text-decoration-none">
          <div class="card arch-card h-100 p-4">
            <div class="d-flex align-items-start gap-3">
              <!-- 로고/아이콘 -->
              <template v-if="item.image">
                <img :src="item.image" alt="logo" class="arch-logo rounded" />
              </template>
              <template v-else>
                <i :class="['bi', item.icon || 'bi-journal-richtext', 'fs-1', 'text-dark']"></i>
              </template>

              <!-- 텍스트 -->
              <div class="flex-grow-1">
                <div class="h5 mb-1 fw-bold text-dark">{{ item.title }}</div>
                <div class="text-primary small">{{ item.linkLabel }}</div>
                <div class="text-muted small mt-2">{{ item.description }}</div>
              </div>
            </div>

            <!-- 전체 카드 클릭 가능하게 -->
            <span class="stretched-link"></span>
          </div>
        </a>
      </div>
    </div>
  </div>
</section>
</template>

<script setup>
const archives = [
  {
    id: 'github',
    title: 'GitHub',
    url: 'https://github.com/LeeJY0116',
    linkLabel: 'https://github.com/LeeJY0116',
    description: '',
    icon: 'bi-github'
  },
]
</script>


<style scoped>
/* Hero 오른쪽 상자 그라디언트 */
.hero-box {
  background: linear-gradient(135deg, #6366f1, #22d3ee);
}

/* 섹션 타이틀 */
.section-title {
  font-weight: 800;
  letter-spacing: .08em;
  border-bottom: 3px solid rgba(var(--bs-dark-rgb), .15);
  padding-bottom: .25rem;
}

/* Skills 배경(노란 강조) */
.skills-section {
  background: linear-gradient(180deg, white 0%, #f3c623 100%);
  border-radius: 1rem;
  padding-left: 1rem;
  padding-right: 1rem;
}

/* 라운드 컨테이너 */
.rounded-4 { border-radius: 1rem !important; }

/* 칩 스타일 */
.chip {
  display: inline-block;
  padding: .38rem .7rem;
  border-radius: 999px;
  font-weight: 600;
  background: var(--bs-light);
  border: 1px solid rgba(0,0,0,.06);
}
.chip.dark {
  background: #111;
  color: #fff;
  border-color: #111;
}

/* 반응형 여백 보정 */
@media (min-width: 992px) {
  .skills-section { margin-left: -12px; margin-right: -12px; }
}

/* 컨테이너 밖으로 배경을 꽉 채우는 full-bleed 섹션 */
.arch-bleed {
  position: relative;
  left: 50%;
  right: 50%;
  margin-left: -50vw;
  margin-right: -50vw;
  width: 100vw;
}

/* 어두운 배경 */
.arch-section { background: #1f1f1f; }

/* 타이틀 밑줄 (기존 .section-title와 톤 맞춤) */
.section-title {
  font-weight: 800;
  letter-spacing: .08em;
  border-bottom: 3px solid rgba(255,255,255,.2);
  padding-bottom: .25rem;
}

/* 카드 스타일 */
.arch-card {
  background: #f8f9fa;
  border: 0;
  border-radius: 18px;
  box-shadow: 0 8px 20px rgba(0,0,0,.08);
  transition: transform .2s ease, box-shadow .2s ease;
}
.arch-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 14px 28px rgba(0,0,0,.12);
}

/* 로고 사이즈 */
.arch-logo {
  width: 56px;
  height: 56px;
  object-fit: contain;
}

</style>
