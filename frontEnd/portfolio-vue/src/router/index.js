import { createRouter, createWebHistory } from "vue-router";
import Home from "@/pages/Home.vue";
import Projects from "@/pages/Projects.vue";
import Etc from "@/pages/Etc.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", name: "home", component: Home },
    { path: "/projects", name: "projects", component: Projects },
    { path: "/etc", name: "etc", component: Etc },
  ],
  scrollBehavior() {
    return { top: 0 };
  },
});

export default router;
