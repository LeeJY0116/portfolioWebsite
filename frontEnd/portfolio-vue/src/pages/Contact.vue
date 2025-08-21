<script setup>
import { ref } from "vue";
import axios from "axios";

const name = ref("");
const email = ref("");
const message = ref("");
const website = ref(""); // 허니팟(숨김)
const sending = ref(false);
const status = ref("");

const submitForm = async () => {
  if (sending.value) return;
  sending.value = true;
  status.value = "전송 중...";

  try {
    // 프록시를 설정했다면 '/api/contact'로, 아니면 base 붙이기
    // const base = import.meta.env.VITE_API_BASE
    // await axios.post(`${base}/api/contact`, { name: name.value, email: email.value, message: message.value, website: website.value })

    await axios.post("/api/contact", {
      // ← vite.config.js에 proxy가 있으면 이 라인 그대로 OK
      name: name.value,
      email: email.value,
      message: message.value,
      website: website.value,
    });

    status.value = "성공적으로 전송되었습니다!";
    name.value = email.value = message.value = "";
  } catch (e) {
    console.error("[Contact] API error:", e);
    status.value = "전송 실패. 잠시 후 다시 시도해주세요.";
  } finally {
    sending.value = false;
  }
};
</script>

<template>
  <section class="py-4">
    <h2 class="h4 mb-4">Contact</h2>
    <form class="row g-3" @submit.prevent="submitForm" novalidate>
      <div class="col-md-6">
        <label class="form-label">Name</label>
        <input v-model="name" class="form-control" required />
      </div>
      <div class="col-md-6">
        <label class="form-label">Email</label>
        <input v-model="email" type="email" class="form-control" required />
      </div>
      <div class="col-12">
        <label class="form-label">Message</label>
        <textarea
          v-model="message"
          rows="5"
          class="form-control"
          required
        ></textarea>
      </div>

      <!-- 허니팟(봇 차단) – 화면에서 숨기기 -->
      <div class="d-none">
        <input v-model="website" autocomplete="off" tabindex="-1" />
      </div>

      <div class="col-12 d-flex align-items-center gap-3">
        <button class="btn btn-primary" :disabled="sending">Send</button>
        <span class="text-muted small">{{ status }}</span>
      </div>
    </form>
  </section>
</template>
