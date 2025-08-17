<script setup>
import { ref } from 'vue'
import axios from 'axios'

const name = ref(''), email = ref(''), message = ref('')
const status = ref('')

const submitForm = async () => {
  status.value = '전송 중...'
  try {
    // Spring 연결 시:
    // await axios.post(import.meta.env.VITE_API_BASE + '/api/contact', { name: name.value, email: email.value, message: message.value })
    // 임시: 메일 링크로 대체
    window.location.href = `mailto:your@email.com?subject=Portfolio%20Contact%3A%20${encodeURIComponent(name.value)}&body=${encodeURIComponent(message.value)}`
    status.value = '이메일 클라이언트로 이동했습니다.'
    name.value = email.value = message.value = ''
  } catch {
    status.value = '전송 실패. 잠시 후 다시 시도해주세요.'
  }
}
</script>

<template>
  <section class="py-4">
    <h2 class="h4 mb-4">Contact</h2>
    <form class="row g-3" @submit.prevent="submitForm">
      <div class="col-md-6">
        <label class="form-label">Name</label>
        <input v-model="name" class="form-control" required>
      </div>
      <div class="col-md-6">
        <label class="form-label">Email</label>
        <input v-model="email" type="email" class="form-control" required>
      </div>
      <div class="col-12">
        <label class="form-label">Message</label>
        <textarea v-model="message" rows="5" class="form-control" required></textarea>
      </div>
      <div class="col-12 d-flex align-items-center gap-3">
        <button class="btn btn-primary">Send</button>
        <span class="text-muted small">{{ status }}</span>
      </div>
    </form>
  </section>
</template>
