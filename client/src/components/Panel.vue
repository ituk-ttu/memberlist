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
            router-link.nav-link(to="/") Home
          li.nav-item
            router-link.nav-link(to="/") Features
          li.nav-item
            router-link.nav-link(to="/") Pricing
          li.nav-item
            router-link.nav-link(to="/") About
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
