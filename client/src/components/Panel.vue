<template lang="pug">
  div
    nav.navbar.navbar-expand-lg.navbar-dark.bg-primary
      router-link.navbar-brand(to="/") MemberList | ITÜK
      button.navbar-toggler(type="button", data-toggle="collapse", data-target="#navPanel", aria-controls="navPanel",
      aria-expanded="false", aria-label="Toggle navigation")
        span.navbar-toggler-icon
      #navPanel.collapse.navbar-collapse
        ul.navbar-nav.mr-auto
          li.nav-item.active
            router-link.nav-link(to="/") List
          li.nav-item
            router-link.nav-link(to="/") Minu andmed
          li.nav-item
            router-link.nav-link(to="/") Logi
        ul.navbar-nav.ml-auto
          li.nav-item.dropdown
            a#navbarDropdown.nav-link.dropdown-toggle(data-toggle="dropdown" aria-haspopup="true" aria-expanded="false")
              | Dropdown link
            .dropdown-menu(aria-labelledby="navbarDropdown")
              router-link.dropdown-item(to="/") Action
              router-link.dropdown-item(to="/") Another action
              router-link.dropdown-item(to="/") Something else here
    router-view
</template>

<script>
  export default {
    name: 'Panel',
    data () {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    }
  }
</script>

<style scoped>
  .navbar {
    margin-bottom: 20px;
  }
</style>
