<template lang="pug">
  .container.loading-container(v-if="user == null")
    icon(name="cog" scale="3" spin)
  .container(v-else)
    .form-group
      label.control-label Nimi:
      input.form-control.form-control-lg(v-model="user.member.name" required)
    .form-group
      label.control-label E-post:
      input.form-control.form-control-lg(v-model="user.member.email" required)
    .form-group
      label.control-label Isikukood:
      input.form-control.form-control-lg(v-model="user.member.personalCode" required)
    .form-group
      label.control-label Tudengikood:
      input.form-control.form-control-lg(v-model="user.member.studentCode" required)
    .form-group
      label.control-label Staatus
      select.form-control.form-control-lg(v-model="user.member.status" required)
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
        user: null
      }
    },
    methods: {
      save: function () {
        let self = this
        let tmp = this.user
        this.user = null
        delete tmp.member.id
        this.$http.put(this.$config.API_BASE + '/users/update/' + tmp.id, tmp.member).then(res => { self.user = res.body })
      }
    },
    mounted: function () {
      if (this.$route.params.id !== 'add') {
        let self = this
        this.$http.get(this.$config.API_BASE + '/users/' + this.$route.params.id).then(res => { self.user = res.body })
      } else {
        this.user = {}
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
