<template lang="pug">
  div.panel
    nav.navbar.navbar-expand-lg.navbar-dark.bg-primary
      router-link.navbar-brand(:to="token.status === 'BOARD' ? { name: 'List' } : { name: 'Member', params: { id: token.sub } }") MemberList | ITÜK
      button.navbar-toggler(type="button", data-toggle="collapse", data-target="#navPanel", aria-controls="navPanel",
      aria-expanded="false", aria-label="Toggle navigation")
        span.navbar-toggler-icon
      #navPanel.collapse.navbar-collapse
        ul.navbar-nav.mr-auto
          li.nav-item
            router-link.nav-link(:to="{ name: 'List' }" v-if="token.status === 'BOARD'") List
          li.nav-item
            router-link.nav-link(:to="{ name: 'List' }") Minu andmed
          li.nav-item
            router-link.nav-link(:to="{ name: 'AccessList' }" v-if="token.status === 'BOARD'") Ligipääsuõigused
          li.nav-item
            router-link.nav-link(:to="{ name: 'List' }" v-if="token.status === 'BOARD'") Logi
        ul.navbar-nav.ml-auto
          li.nav-item
            router-link.nav-link(:to="{ name: 'Login' }") Logi välja ({{ token.name }})

    router-view
</template>

<script>
  export default {
    name: 'Panel',
    data () {
      return {
        token: null
      }
    },
    methods: {
      retrieveTokenData: function () {
        this.token = this.$jwtDec.decode(localStorage.getItem('accessToken'))
      }
    },
    beforeMount: function () {
      if (localStorage.getItem('accessToken') == null) {
        this.$router.push({ name: 'Login' })
      }
      this.retrieveTokenData()
      this.$on('refreshToken', () => { this.retrieveTokenData() })
    }
  }
</script>

<style scoped>
  .navbar {
    margin-bottom: 20px;
  }
  .panel {
    min-height: 100%;
  }
</style>
