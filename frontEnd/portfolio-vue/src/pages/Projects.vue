<script setup>
import { ref, onMounted } from "vue";
import ReadmeModal from '@/components/ReadmeModal.vue'
import axios from "axios";
import ImageLightbox from '@/components/ImageLightbox.vue'


const readmeOpen = ref(false)
const readmeTitle = ref('')
const readmeSrc = ref('')

const openReadme = (p) => {
  readmeTitle.value = `${p.title} – README.md`
  // p.readmeUrl이 있으면 사용, 없으면 id 기반 기본 경로
  readmeSrc.value = p.readmeUrl || `/readme/${p.id}.md`
  readmeOpen.value = true
}

const base = import.meta.env.VITE_API_BASE;
console.log("BASE =", import.meta.env.VITE_API_BASE);
const projects = ref([]);
const status = ref("loading"); // 'loading' | 'ok' | 'error'

// 라이트박스 상태
const lightboxOpen = ref(false)
const lightboxImages = ref([])
const lightboxStart = ref(0)

const openLightbox = (p, start = 0) => {
  // p.images 배열이 있으면 사용, 없으면 imageUrl 하나만
  lightboxImages.value = (p.images && p.images.length) ? p.images : (p.imageUrl ? [p.imageUrl] : [])
  if (!lightboxImages.value.length) return
  lightboxStart.value = start
  lightboxOpen.value = true
}

onMounted(async () => {
  try {
    const { data } = await axios.get(
      import.meta.env.VITE_API_BASE + "/api/projects",
      {
        // 캐시 무효화가 필요하면:
        headers: { "Cache-Control": "no-cache" },
      },
    );
    projects.value = data;
    status.value = "ok";
  } catch (e) {
    console.error("[Projects] API error:", e);
    status.value = "error";
  }
});
</script>

<template>
  <section class="py-4">
    <h2 class="h4 mb-4">Featured Projects</h2>
    <div v-if="status === 'loading'">Loading…</div>
    <div v-else-if="status === 'error'" class="text-muted">
      데이터를 불러오지 못했습니다.
    </div>
    <div v-else class="row g-4">
      <div v-for="p in projects" :key="p.id" class="col-md-6 col-lg-4">
        <article class="card h-100 shadow-sm">
          <img
            v-if="p.imageUrl"
            :src="p.imageUrl"
            class="card-img-top"
            style="aspect-ratio:16/9; object-fit:cover; cursor: zoom-in;"
            alt="cover"
            loading="lazy"
            @click="openLightbox(p, 0)"
            @error="e => (e.target.style.display='none')"
          />
          <div class="card-body">
            <h3 class="h6 card-title">{{ p.title }}</h3>
            <p class="card-text text-muted small">{{ p.description }}</p>
            <div class="d-flex flex-wrap gap-1 mb-2">
              <span
                v-for="t in p.tags"
                :key="t"
                class="badge text-bg-light border"
                >{{ t }}</span>
            </div>
            <div class="d-flex flex-wrap gap-2 mt-2">
              <button
                type="button"
                class="btn btn-chip btn-sm d-inline-flex align-items-center gap-2"
                @click="openReadme(p)"
              >
                <i class="bi bi-journal-text"></i><span>README</span>
              </button>
              <a
                v-if="(p.codeUrl && p.codeUrl.length >1)"
                :href="p.codeUrl"
                target="_blank"
                class="btn btn-chip btn-sm d-inline-fles align-items-center gap-2"
                >
                <i class="bi bi-code"></i>
                Code
                </a>
                <!-- 이미지: 라이트박스 열기 -->
                <button
                  v-if="(p.images && p.images.length)"
                  type="button"
                  class="btn btn-chip btn-sm d-inline-flex align-items-center gap-2"
                  @click="openLightbox(p, 0)"
                >
                  <i class="bi bi-image"></i>
                  <span>이미지</span>
                </button>
            </div>
          </div>
        </article>
      </div>
    </div>
    <!-- 라이트박스 -->
    <ImageLightbox
      :images="lightboxImages"
      :show="lightboxOpen"
      :start="lightboxStart"
      @close="lightboxOpen=false"
      />

    <ReadmeModal
      :show="readmeOpen"
      :title="readmeTitle"
      :src="readmeSrc"
      @close="readmeOpen = false"
      />
  </section>
</template>

<style scoped>
.btn-chip {
  --bs-btn-bg: white;
  --bs-btn-color: #111;
  --bs-btn-border-color: rgba(0,0,0,.2);
  --bs-btn-hover-bg: #f8f9fa;
  --bs-btn-hover-border-color: rgba(0,0,0,.35);
  --bs-btn-active-bg: #eef1f4;
  --bs-btn-padding-x: .3rem;
  --bs-btn-padding-y: .1rem;
  border-radius:10px;
  font-weight: 600;
  box-shadow: 0 2px 6px rgba(0,0,0,.04);
}
.btn-chip .bi { font-size: 1rem; line-height: 1; }
</style>