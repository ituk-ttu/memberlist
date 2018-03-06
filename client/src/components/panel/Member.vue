<template lang="pug">
  .container.loading-container(v-if="member == null")
    p Laadin...
  .container(v-else)
    .form-group
      label.control-label Nimi:
      input.form-control.form-control-lg(v-model="member.name")
    .form-group
      label.control-label E-post:
      input.form-control.form-control-lg(v-model="member.email")
    .form-group
      label.control-label Isikukood:
      input.form-control.form-control-lg(v-model="member.personalCode")
    .form-group
      label.control-label Tudengikood:
      input.form-control.form-control-lg(v-model="member.studentCode")
    .form-group
      label.control-label Staatus
      select.form-control.form-control-lg(v-model="member.status")
        option(value="ACTIVE") Aktiivgrupp
        option(value="BOARD") Juhatus
        option(value="REPRESENTATIVE") Volikogu
        option(value="ALUMNI") Vilistlane
        option(value="GONE") Läinud
    .form-group
      button(type="button").btn.btn-lg.btn-success Salvesta
      button(type="button").btn.btn-outline-info.float-right Taasta
</template>

<script>
  import MemberStatusLabel from './MemberStatusLabel'
  export default {
    components: {MemberStatusLabel},
    name: 'List',
    data () {
      return {
        member: null
      }
    },
    mounted: function () {
      let self = this
      this.$http.get(this.$config.API_BASE + '/members/' + this.$route.params.id).then(res => { self.member = res.body })
    }
  }
</script>

<style>
  .loading-container {
    display:flex;
    justify-content:center;
    align-items:center;
    text-align: center;
    height: 100%;
  }
</style>
