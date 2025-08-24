<script setup>
import { ref, watch, onMounted, onBeforeUnmount } from 'vue'

const props = defineProps({
  images: { type: Array, required: true }, // ['.../1.png', '.../2.png']
  start:  { type: Number, default: 0 },
  show:   { type: Boolean, default: false }
})
const emit = defineEmits(['close'])

const idx = ref(0)

const close = () => emit('close')
const prev  = () => (idx.value = (idx.value - 1 + props.images.length) % props.images.length)
const next  = () => (idx.value = (idx.value + 1) % props.images.length)

const onKey = (e) => {
  if (!props.show) return
  if (e.key === 'Escape') close()
  if (e.key === 'ArrowLeft') prev()
  if (e.key === 'ArrowRight') next()
}

watch(() => props.show, (v) => {
  if (v) {
    idx.value = props.start ?? 0
    document.body.style.overflow = 'hidden' // 배경 스크롤 잠금
  } else {
    document.body.style.overflow = ''
  }
})

onMounted(() => window.addEventListener('keydown', onKey))
onBeforeUnmount(() => {
  window.removeEventListener('keydown', onKey)
  document.body.style.overflow = ''
})
</script>

<template>
  <div v-if="show" class="lb-backdrop" @click.self="close">
    <div class="lb-modal">
      <!-- Header -->
      <header class="lb-header">
        <div class="lb-pager">{{ idx + 1 }} / {{ images.length }}</div>
        <button class="btn btn-light btn-sm lb-x" @click="close" aria-label="닫기">
          <i class="bi bi-x-lg"></i>
        </button>
      </header>

      <!-- Content -->
      <div class="lb-content">
        <img :src="images[idx]" alt="preview" class="lb-img" loading="lazy" />

        <!-- 좌우 플로팅 네비 (항상 화면 안) -->
        <div v-if="images.length > 1" class="lb-nav">
          <button class="btn btn-light btn-nav" @click.stop="prev" aria-label="이전">
            <i class="bi bi-chevron-left"></i>
          </button>
          <button class="btn btn-light btn-nav" @click.stop="next" aria-label="다음">
            <i class="bi bi-chevron-right"></i>
          </button>
        </div>
      </div>

      <!-- Footer -->
      <footer class="lb-footer">
        <div class="text-white-50 small">←/→ 로 이동, Esc 로 닫기</div>
      </footer>
    </div>
  </div>
</template>

<style scoped>
/* 배경 오버레이: 중앙 정렬 + 가장자리 패딩 */
.lb-backdrop{
  position:fixed; inset:0;
  background:rgba(0,0,0,.75);
  display:flex; align-items:center; justify-content:center;
  padding:24px; box-sizing:border-box;
  z-index:2000;
}

/* ✅ 팝업: “고정 크기 + 항상 화면 안” & 3단 그리드(헤더/콘텐츠/푸터) */
.lb-modal{
  width:  min(900px, calc(100vw - 48px));  /* 원하는 값으로 조정 가능 */
  height: min(560px, calc(100vh - 48px));
  display: grid;
  grid-template-rows: auto 1fr auto;       /* ← 핵심: 푸터 침범 방지 */
  background:#111; border-radius:16px; overflow:hidden;
  box-shadow:0 20px 60px rgba(0,0,0,.6);
}

/* 헤더/푸터는 이미지와 분리 */
.lb-header,.lb-footer{
  display:flex; align-items:center; gap:12px; padding:10px 12px;
  background:rgba(255,255,255,.06); color:#fff;
}
.lb-header{ border-top-left-radius:16px; border-top-right-radius:16px; }
.lb-footer{ border-bottom-left-radius:16px; border-bottom-right-radius:16px; }
.lb-pager{ font-size:.92rem; opacity:.9; }
.lb-x{ margin-left:auto; }

/* ✅ 콘텐츠 박스: 남는 공간 정확히 차지 + 오버플로 차단 */
.lb-content{
  position:relative;
  overflow:hidden;          /* ← 이미지가 여기 밖으로 못 나가게 */
  padding:8px;              /* 패딩을 유지해도 box-sizing으로 안전 */
  box-sizing:border-box;    /* 높이 계산에 패딩 포함 */
  display:grid; place-items:center;
  background:#000;
}
/* ✅ 이미지: 콘텐츠 박스 안에서만 맞추기 */
.lb-img{
  width:90%;
  height:100%;
  object-fit:contain;       /* 세로 긴 이미지도 레터박스 처리 */
  display:block;            /* 인라인 이미지의 baseline 여백 제거 */
  border-radius:8px;
  background:#111;
}

/* 중앙 좌/우 네비 버튼 (항상 접근 가능) */
.lb-nav{
  position:absolute; inset:0;
  display:flex; align-items:center; justify-content:space-between;
  pointer-events:none; padding:0 8px;
}
.btn-nav{ pointer-events:auto; }

/* 작은 화면 */
@media (max-width:480px){
  .lb-modal{
    --lb-w: 95vw;
    --lb-h: 80vh;
  }
}
</style>
