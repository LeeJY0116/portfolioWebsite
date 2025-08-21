import { createRouter, createWebHistory } from "vue-router";
import Home from "@/pages/Home.vue";
import Projects from "@/pages/Projects.vue";
import Contact from "@/pages/Contact.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", name: "home", component: Home },
    { path: "/projects", name: "projects", component: Projects },
    { path: "/contact", name: "contact", component: Contact },
  ],
  scrollBehavior() {
    return { top: 0 };
  },
});

export default router;
