<template>
  <div class="hero">
    <div>
      <h1 class="display-3">{{ msg }}</h1>
      <p class="lead">Sistema de Controle de Estoque</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'dashboard',
  data () {
    return {
      msg: 'Figure Store'
    }
  }
}
</script>

<style>
  .hero {
    height: 90vh;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
  }
  .hero .lead {
    font-weight: 200;
    font-size: 1.5rem;
  }
</style>
