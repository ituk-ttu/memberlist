<template lang="pug">
  .container.loading-container(v-if="member == null")
    p Laadin...
  .container(v-else)
    .form-group
      label.control-label Nimi:
      input.form-control.form-control-lg(v-model="member.name" required)
    .form-group
      label.control-label E-post:
      input.form-control.form-control-lg(v-model="member.email" required)
    .form-group
      label.control-label Isikukood:
      input.form-control.form-control-lg(v-model="member.personalCode" required)
    .form-group
      label.control-label Tudengikood:
      input.form-control.form-control-lg(v-model="member.studentCode" required)
    .form-group
      label.control-label Staatus
      select.form-control.form-control-lg(v-model="member.status" required)
        option(value="ACTIVE") Aktiivgrupp
        option(value="BOARD") Juhatus
        option(value="REPRESENTATIVE") Volikogu
        option(value="ALUMNI") Vilistlane
        option(value="GONE") Läinud
    .form-group
      button(type="button", v-on:click="save()").btn.btn-lg.btn-success Salvesta
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
    methods: {
      save: function () {
        let self = this
        let tmp = this.member
        this.member = null
        this.$http.put(this.$config.API_BASE + '/members/', tmp).then(res => { self.member = res.body })
      }
    },
    mounted: function () {
      if (this.$route.params.id !== 'add') {
        let self = this
        this.$http.get(this.$config.API_BASE + '/members/' + this.$route.params.id).then(res => { self.member = res.body })
      } else {
        this.member = {}
      }
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
