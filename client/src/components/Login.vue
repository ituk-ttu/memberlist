<template lang="pug">
  .container
    .card.border-secondary
      .card-header Login
      .card-body
        .form-group
          input.form-control
        .form-group
          button.btn.btn-success.btn-block Go
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        email: ''
      }
    }
  }
</script>

<style scoped>
  .container {
    display:flex;
    justify-content:center;
    align-items:center;
    text-align: center;
    height: 100%;
  }
</style>
