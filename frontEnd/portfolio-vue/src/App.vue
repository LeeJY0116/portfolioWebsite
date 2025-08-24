<template>
  <div>
    <NavBar />
    <main class="container py-4">
      <RouterView />
    </main>
    <FooterBar />
    <BackToTop />
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import FooterBar from "@/components/FooterBar.vue";
import BackToTop from '@/components/BackToTop.vue'
</script>
