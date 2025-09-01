<script setup>
import { ref, watch, onMounted, onBeforeUnmount } from 'vue'
import { marked } from 'marked'
import DOMPurify from 'dompurify'

const props = defineProps({
  show: { type: Boolean, default: false },
  title: { type: String, default: 'README.md' },
  src: { type: String, default: '' },        // README 파일 URL
  markdown: { type: String, default: '' }    // (선택) 바로 문자열로 전달 시
})
const emit = defineEmits(['close'])

const html = ref('<p class="text-muted">불러오는 중…</p>')

const load = async () => {
  try {
    if (props.markdown) {
      html.value = DOMPurify.sanitize(marked.parse(props.markdown))
      return
    }
    if (!props.src) {
      html.value = '<p class="text-muted">README 소스가 없습니다.</p>'
      return
    }
    const res = await fetch(props.src, { cache: 'no-store' })
    if (!res.ok) throw new Error('not ok')
    const md = await res.text()
    html.value = DOMPurify.sanitize(marked.parse(md))
  } catch {
    html.value = '<p class="text-danger">README를 불러오지 못했습니다.</p>'
  }
}

watch(() => props.show, v => {
  if (v) { load(); document.body.style.overflow='hidden' }
  else   { document.body.style.overflow='' }
})

const onKey = (e) => { if (props.show && e.key === 'Escape') emit('close') }
onMounted(() => window.addEventListener('keydown', onKey))
onBeforeUnmount(() => window.removeEventListener('keydown', onKey))
</script>

<template>
  <div v-if="show" class="rdm-backdrop" @click.self="emit('close')">
    <div class="rdm-modal">
      <header class="rdm-header">
        <div class="rdm-title">{{ title }}</div>
      </header>

      <div class="rdm-body markdown-body" v-html="html" />

      <footer class="rdm-footer">
      </footer>
    </div>
  </div>
</template>

<style scoped>
/* 오버레이 */
.rdm-backdrop{ position:fixed; inset:0; background:rgba(0,0,0,.6);
  display:grid; place-items:center; z-index:2100; padding:24px; }
/* 모달 박스 */
.rdm-modal{ width:min(920px, 95vw); height:min(86vh, 880px); background:#fff;
  border-radius:12px; box-shadow:0 24px 60px rgba(0,0,0,.35);
  display:grid; grid-template-rows:auto 1fr auto; overflow:hidden; }
/* 헤더/푸터 */
.rdm-header,.rdm-footer{ display:flex; align-items:center; gap:12px;
  padding:10px 12px; background:black; }
.rdm-title{ font-weight:700; color:white;}
/* 본문(스크롤) */
.rdm-body{ overflow:auto; padding:18px 22px; }

/* 간단 GitHub 스타일 */
.markdown-body h1{ font-size:1.5rem; border-bottom:1px solid #e5e7eb; padding-bottom:.4rem; margin-top:.2rem; }
.markdown-body h2{ font-size:1.25rem; margin-top:1.4rem; }
.markdown-body h3{ font-size:1.1rem; margin-top:1.2rem; }
.markdown-body p, .markdown-body li{ line-height:1.7; }
.markdown-body code{ background:#f6f8fa; border:1px solid #e5e7eb; border-radius:6px; padding:.1rem .35rem; }
.markdown-body pre{ background:#0f172a; color:#e2e8f0; border-radius:8px; padding:12px 14px; overflow:auto; }
.markdown-body table{ border-collapse:collapse; width:100%; }
.markdown-body th, .markdown-body td{ border:1px solid #e5e7eb; padding:.5rem .6rem; }
</style>
