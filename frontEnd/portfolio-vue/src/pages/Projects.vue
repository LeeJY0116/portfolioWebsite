<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";
const base = import.meta.env.VITE_API_BASE;
console.log("BASE =", import.meta.env.VITE_API_BASE);
const projects = ref([]);
const status = ref("loading"); // 'loading' | 'ok' | 'error'

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
            alt="cover"
          />
          <div class="card-body">
            <h3 class="h6 card-title">{{ p.title }}</h3>
            <p class="card-text text-muted small">{{ p.description }}</p>
            <div class="d-flex flex-wrap gap-1 mb-2">
              <span
                v-for="t in p.tags"
                :key="t"
                class="badge text-bg-light border"
                >{{ t }}</span
              >
            </div>
            <div class="d-flex gap-3">
              <a
                v-if="p.demoUrl"
                :href="p.demoUrl"
                target="_blank"
                class="link-primary small"
                >Demo</a
              >
              <a
                v-if="p.codeUrl"
                :href="p.codeUrl"
                target="_blank"
                class="link-primary small"
                >Code</a
              >
            </div>
          </div>
        </article>
      </div>
    </div>
  </section>
</template>
