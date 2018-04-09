<template lang="pug">
  .container
    .card.border-secondary
      .card-header Logi sisse
      .card-body(v-if="status === 'WAITING'")
          form(v-on:submit.prevent="login()")
            .form-group
              label.control-label E-mail
              input.form-control(v-model="email")
            .form-group
              button.btn.btn-success.btn-block(type="submit") Logi sisse
      .card-body(v-else-if="status === 'LOGGING_IN'")
        .form-group
          label.control-label E-mail
          input.form-control.disabled(disabled)
        .form-group
          button.btn.btn-success.btn-block.disabled: icon(name="cog" spin)
      .card-body(v-else)
        .form-group
          p Vaata oma postkasti!
        .form-group
          button.btn.btn-success.btn-block.disabled: icon(name="check")
</template>

<script>
  export default {
    name: 'Login',
    data () {
      return {
        email: '',
        status: 'WAITING'
      }
    },
    methods: {
      login: function () {
        let self = this
        this.status = 'LOGGING_IN'
        this.$http.post(this.$config.API_BASE + '/auth', { email: this.email }).then(res => { self.status = 'SUCCESS' })
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
