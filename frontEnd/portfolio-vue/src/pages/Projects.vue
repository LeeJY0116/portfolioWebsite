<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const projects = ref([
  {
    id: 1, title: 'Campus Community & Map',
    description: '주변 편의점 공유 + 커뮤니티',
    tags: ['Django','Kakao Map','PostgreSQL'],
    imageUrl: 'https://picsum.photos/seed/p1/800/400',
    demoUrl: '#', codeUrl: '#'
  },
  {
    id: 2, title: 'Data Crawler & ETL',
    description: '크롤링/정제/적재 배치 파이프라인',
    tags: ['Java','Spring Batch','MySQL'],
    imageUrl: 'https://picsum.photos/seed/p2/800/400',
    demoUrl: '#', codeUrl: '#'
  }
])

// Spring 연결 시 이렇게 교체:
// onMounted(async () => {
//   const { data } = await axios.get(import.meta.env.VITE_API_BASE + '/api/projects')
//   projects.value = data
// })
</script>

<template>
  <section class="py-4">
    <h2 class="h4 mb-4">Featured Projects</h2>
    <div class="row g-4">
      <div v-for="p in projects" :key="p.id" class="col-md-6 col-lg-4">
        <article class="card h-100">
          <img v-if="p.imageUrl" :src="p.imageUrl" class="card-img-top" alt="cover">
          <div class="card-body">
            <h3 class="h6 card-title">{{ p.title }}</h3>
            <p class="card-text text-muted small">{{ p.description }}</p>
            <div class="d-flex flex-wrap gap-1 mb-2">
              <span v-for="t in p.tags" :key="t" class="badge text-bg-light border">{{ t }}</span>
            </div>
            <div class="d-flex gap-3">
              <a v-if="p.demoUrl" :href="p.demoUrl" target="_blank" class="link-primary small">Demo</a>
              <a v-if="p.codeUrl" :href="p.codeUrl" target="_blank" class="link-primary small">Code</a>
            </div>
          </div>
        </article>
      </div>
    </div>
  </section>
</template>
