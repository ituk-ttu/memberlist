<template lang="pug">
  .container.loading-container(v-if="user == null")
    icon(name="cog" scale="3" spin)
  .container(v-else)
    h1 {{ user.member.name }}
      router-link.btn.btn-warning.float-right(:to="{ name: 'EditMember', params: { id: user.id } }"
          v-if="$parent.token.status === 'BOARD'") Muuda
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
        tr(v-for="version in userHistory")
          td {{ version.name }}
          td {{ version.personalCode }}
          td {{ version.studentCode }}
          td {{ version.email }}
          td: member-status-label(:status="version.status")
    h3 Ligipääsuõigused
    table.table(v-if="doors != null")
      thead
        tr
          th(v-for="door in doors") {{ door.name }}
      tbody
        tr(v-for="version in accessHistory")
          td(v-for="door in doors")
            icon.text-success(name="check" v-if="accessContainsDoor(version.doors, door)")
            icon.text-muted(name="times" v-else)
    icon(name="cog" scale="1.5" spin v-else)
</template>

<script>
  import MemberStatusLabel from './MemberStatusLabel'

  export default {
    components: {MemberStatusLabel},
    name: 'Member',
    data () {
      return {
        user: null,
        userHistory: [],
        accessHistory: [],
        doors: null
      }
    },
    methods: {
      accessContainsDoor: function (access, door) {
        for (let i = 0; i < access.length; i++) {
          if (door.name === access[i].name) {
            return true
          }
        }
        return false
      }
    },
    mounted: function () {
      let self = this
      this.$http.get(this.$config.API_BASE + '/users/' + this.$route.params.id).then(res => {
        self.user = res.body
        let member = self.user.member.previousVersion
        while (member != null) {
          self.userHistory.push(member)
          member = member.previousVersion
        }
        let accessCollection = self.user.accessCollection
        while (accessCollection != null) {
          self.accessHistory.push(accessCollection)
          accessCollection = accessCollection.previousVersion
        }
      })
      this.$http.get(this.$config.API_BASE + '/doors').then(res => {
        self.doors = res.body
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
