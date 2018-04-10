<template lang="pug">
  .container.loading-container(v-if="user == null")
    icon(name="cog" scale="3" spin)
  .container(v-else)
    h1 {{ user.member.name }}
    p
      strong Isikukood: &nbsp
      | {{ user.member.personalCode }}
    p
      strong Tudengikood: &nbsp
      | {{ user.member.studentCode }}
    p
      strong E-mail: &nbsp
      | {{ user.member.email }}
    p
      strong Staatus: &nbsp
      member-status-label(:status="user.member.status")
    h3 Ajalugu
    table.table
      thead
        tr
          th Nimi
          th Isikukood
          th Tudengikood
          th Email
          th Staatus
      tbody
        tr(v-for="version in history")
          td {{ version.name }}
          td {{ version.personalCode }}
          td {{ version.studentCode }}
          td {{ version.email }}
          td: member-status-label(:status="version.status")
</template>

<script>
  import MemberStatusLabel from './MemberStatusLabel'

  export default {
    components: {MemberStatusLabel},
    name: 'Member',
    data () {
      return {
        user: null,
        history: []
      }
    },
    mounted: function () {
      let self = this
      this.$http.get(this.$config.API_BASE + '/users/' + this.$route.params.id).then(res => {
        self.user = res.body
        let member = self.user.member.previousVersion
        while (member != null) {
          self.history.push(member)
          member = member.previousVersion
        }
      })
    }
  }
</script>

<style>
  .loading-container {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 100%;
  }
</style>
