<template>
  <div>
    <NavBar />
    <main class="container py-4">
      <RouterView />
    </main>
    <FooterBar />
  </div>
</template>

<script setup>
import NavBar from "@/components/NavBar.vue";
import FooterBar from "@/components/FooterBar.vue";
</script>
