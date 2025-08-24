<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const visible = ref(false)

let ticking = false
const onScroll = () => {
  if (ticking) return
  ticking = true
  requestAnimationFrame(() => {
    visible.value = window.scrollY > 400 // 400px 넘으면 표시
    ticking = false
  })
}

const toTop = () => window.scrollTo({ top: 0, behavior: 'smooth' })

onMounted(() => {
  window.addEventListener('scroll', onScroll, { passive: true })
  onScroll()
})
onBeforeUnmount(() => window.removeEventListener('scroll', onScroll))
</script>

<template>
  <button
    class="btn btn-primary back-to-top shadow-lg"
    :class="{ 'is-visible': visible }"
    @click="toTop"
    aria-label="맨 위로"
  >
    <i class="bi bi-arrow-up"></i>
  </button>
</template>

<style scoped>
.back-to-top {
  position: fixed;
  background-color: black;
  border-color: black;
  right: 20px;
  bottom: 24px;
  width: 44px;
  height: 44px;
  border-radius: 35%;
  display: grid;
  place-items: center;
  z-index: 1050;              /* 네비 위에 떠 있게 */
  opacity: 0;
  transform: translateY(8px);
  pointer-events: none;
  transition: opacity .18s ease, transform .18s ease;
}
.back-to-top.is-visible {
  opacity: 1;
  transform: translateY(0);
  pointer-events: auto;
}

/* 모션 최소화 환경 배려 */
@media (prefers-reduced-motion: reduce) {
  .back-to-top { transition: none; }
}
</style>
