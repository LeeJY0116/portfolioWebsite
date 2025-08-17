<template>
  <nav class="navbar navbar-expand-lg bg-body-tertiary border-bottom">
    <div class="container">
      <RouterLink class="navbar-brand fw-semibold" to="/">LJ</RouterLink>

      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMain">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navMain">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item"><RouterLink class="nav-link" to="/">Home</RouterLink></li>
          <li class="nav-item"><RouterLink class="nav-link" to="/projects">Projects</RouterLink></li>
          <li class="nav-item"><RouterLink class="nav-link" to="/contact">Contact</RouterLink></li>
        </ul>
      </div>
    </div>
  </nav>
</template>
