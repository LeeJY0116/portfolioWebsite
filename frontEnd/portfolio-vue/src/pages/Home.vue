<template>
  <section class="py-5">
    <div id="home-export" ref="exportRef">

      <!-- Introduce -->
    <div class="introduce-banner rounded-4 p-4 p-md-5 mb-4">
      <div class="row align-items-center">
        <div class="col-lg-7">
            <span class="badge text-bg-dark mb-3">Backend Developer</span>
            <h1 class="display-6 fw-bold mb-3">
              성장하는 개발자 이주엽입니다
            </h1>
            <p class="text-muted mb-4">
              안녕하세요.<br>
              끊임없이 배우고 성장하는 개발자 이주엽입니다.
            </p>
            <div class="d-flex gap-2">
              <RouterLink class="btn btn-primary" to="/projects">View Projects</RouterLink>
            <div class="d-flex gap-2">
              <button class="btn btn-danger" @click="exportPDF" :disabled="generating">
                <i class="bi bi-filetype-pdf me-1"></i>
                {{ generating ? '내보내는 중...' : 'Download PDF' }}
              </button>
            </div>
            </div>
          </div>
          <div class="col-lg-5 mt-4 mt-lg-0">
            <div class="ratio ratio-1x1 rounded hero-box"></div>
          </div>
        </div>
      </div>

      <!-- ABOUT ME -->
      <section class="py-5 border-top aboutMe-section">
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
              <i class="bi bi-geo-alt fs-4 text-secondary"></i>
              <div>
                <div class="text-muted small">학력</div>
                <div class="fw-semibold">청주대학교(학사)</div>
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
            <div class="col-sm-3">
              <div class="d-flex align-items-center gap-2 fw-semibold">
                <i class="bi bi-person-vcard fs-5"></i> Certificates
              </div>
            </div>
            <div class="col-sm-9">
              <div class="d-flex flex-wrap gap-2">
                <span class="chip dark">정보처리기사</span>
                <span class="chip">SQLD(SQL 개발자)</span>
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
</div>
    <!-- PROJECTS (하이라이트) -->
    <section class="py-5 border-top">
      <div class="d-flex align-items-center gap-2 mb-2">
        <i class="bi bi-link-45deg fs-3 text-primary"></i>
        <h2 class="section-title m-0">PROJECTS</h2>
      </div>
      <p class="text-muted small mb-4 ">주요 프로젝트만 보기<span class='text-primary'>(개발 예정)</span></p>

      <div class="row g-4">
        <div v-for="mp in miniProjects" :key="mp.id" class="col-md-6">
          <article class="card shadow-sm rounded-4 h-100 p-3">
            <div class="d-flex align-items-center justify-content-between mb-2">
              <span class="badge text-bg-danger">{{ mp.badge }}</span>
              <span class="text-muted small">{{ mp.period }}</span>
            </div>
            <h3 class="h5 fw-bold mb-2">{{ mp.title }}</h3>
            <p class="text-muted small mb-3">{{ mp.brief }}</p>
            <p class="text-muted small mb-3">{{ mp.brief2 }}</p>
            <p class="text-muted small mb-3">{{ mp.brief3 }}</p>
            <p class="text-muted small mb-3">{{ mp.brief4 }}</p>

            <div class="d-flex flex-wrap gap-2 mb-3">
              <span v-for="t in mp.tags" :key="t" class="chip">{{ t }}</span>
            </div>

            <div class="d-flex gap-2">
              <RouterLink :to="mp.to || '/projects'"
              class="btn btn-chip btn-sm d-inline-flex align-items-center gap-2">
              <i class="bi bi-book"></i>
              자세히
              </RouterLink>
            </div>
          </article>
        </div>
      </div>
    </section>
  </section>
</template>

<script setup>
import { ref } from 'vue'
import html2canvas from 'html2canvas'
import jsPDF from 'jspdf'

const exportRef = ref(null)
const generating = ref(false)

const exportPDF = async () => {
  if (!exportRef.value || generating.value) return
  generating.value = true
  try {
    const el = exportRef.value

    // 화면 배경색까지 포함, 2x 스케일로 선명하게
    const canvas = await html2canvas(el, {
      scale: 3,
      backgroundColor: '#ffffff',
      useCORS: true,
      // 레이아웃이 좁아지지 않게 현재 뷰포트 폭을 기준으로 렌더
      windowWidth: document.documentElement.scrollWidth
    })

    const imgData = canvas.toDataURL('image/png')

    // A4 세팅 (mm)
    const pdf = new jsPDF('p', 'mm', 'a4')
    const pageW = pdf.internal.pageSize.getWidth()
    const pageH = pdf.internal.pageSize.getHeight()
    const margin = 10
    const usableW = pageW - margin * 2
    const imgH = (canvas.height / canvas.width) * usableW

    // 첫 페이지
    let position = margin
    pdf.addImage(imgData, 'PNG', margin, position, usableW, imgH)

    // 여러 페이지 분할
    let heightLeft = imgH - (pageH - margin * 2)
    while (heightLeft > 0) {
      pdf.addPage()
      position = margin - heightLeft
      pdf.addImage(imgData, 'PNG', margin, position, usableW, imgH)
      heightLeft -= (pageH - margin * 2)
    }

    pdf.save('LeeJuYeob-Portfolio.pdf')
  } catch (e) {
    console.error('[PDF] export error:', e)
    alert('PDF 생성 중 오류가 발생했습니다.')
  } finally {
    generating.value = false
  }
}

const archives = [
  {
    id: 'github',
    title: 'GitHub',
    url: 'https://github.com/LeeJY0116',
    linkLabel: 'https://github.com/LeeJY0116',
    description: '',
    icon: 'bi-github'
  },
  {
    id: 'portfolio',
    title: 'DownLoad PortFolio',
    url: '/downloads/LeeJuYeob-Portfolio.pdf',
    linkLabel: '다운로드 (Notion PDF)',
    description: '',
    icon: 'bi-journal-arrow-up',
    download: true,
    filename: 'LeeJuYeob-Portfolio'
  },
]

const miniProjects = [
  {
    id: 'portfolio',
    badge: '1人 개인 프로젝트',
    period: '2025.08',
    title: '포트폴리오 웹사이트',
    brief: '• 세상에 하나뿐인 나만의 포트폴리오 웹 사이트 개발',
    brief2: '• Spring Boot와 Vue.js를 사용하여 프론트와 백을 함께 경험',
    brief3: '',
    tags: ['Spring Boot', 'Vue.js'],
    demo: 'https://it-eldorado.com',
    to: '/projects'
  },
  {
    id: 'cns',
    badge: '3人 팀 프로젝트',
    period: '2022.06',
    title: '편의점 위치 공유 및 커뮤니티(CSN)',
    brief: '• 조별과제로 하게 된 웹사이트 구현',
    brief2: '• Django프레임워크를 이용하여 개발',
    brief3: '• 최초 웹 개발 경험(HTML,CSS,JS,Django)',
    brief4: '• 최초 협업 경험(Git)',
    tags: ['Python', 'Django', 'KaKaoMap API'],
    to: '/projects'
  },
]

</script>


<style scoped>
#badge {
background: white;
}

.introduce-banner{
    position: relative;
    background-image: url('/images/introduce/introduce-bg.jpg');
    background-size: cover;
    background-position: center;
    box-shadow: 0 16px 40px rgba(0,0,0,.06);
    isolation: isolate;
}

.introduce-banner::before{
    content:"";
    position:absolute; inset:0;
    background: rgba(255,255,255,.72);
    backdrop-filter: blur(1px);
    z-index: 0;
}

.introduce-banner > * {position: relative; z-index:1; }

/* 섹션 타이틀 */
.section-title {
  font-weight: 800;
  letter-spacing: .08em;
  border-bottom: 3px solid rgba(var(--bs-dark-rgb), .15);
  padding-bottom: .25rem;
}

.aboutMe-section {
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

/* PDF엔 BackToTop 같은 고정 버튼 숨기기 */
@media print {
  .back-to-top { display: none !important; }
}

</style>
