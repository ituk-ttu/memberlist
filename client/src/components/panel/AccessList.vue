<template lang="pug">
  .container
    h2 Ligipääsuõigused
    table.table(v-if="doors != null && people != null")
      thead
        tr
          th Liige
          th(v-for="door in doors") {{ door.name }}
      tbody
        tr(v-for="user in people")
          td: strong {{ user.member.name }}
          td(v-for="door in doors")
            icon.text-success(name="check" v-if="accessContainsDoor(user.accessCollection.doors, door)")
            icon.text-muted(name="times" v-else)
    icon(name="cog" scale="3" spin v-else)
</template>

<script>
  export default {
    name: 'AccessList',
    data () {
      return {
        search: '',
        people: null,
        doors: null
      }
    },
    mounted: function () {
      let self = this
      this.$http.get(this.$config.API_BASE + '/users').then(res => { self.people = res.body })
      this.$http.get(this.$config.API_BASE + '/doors').then(res => { self.doors = res.body })
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
    computed: {
      searchedPeople () {
        return this.people.filter(person => person.member.name === null ||
                                            person.member.personalCode === null ||
                                            person.member.email === null ||
                                            person.member.name.toLowerCase().includes(this.search.toLowerCase()) ||
                                            person.member.personalCode.toLowerCase().includes(this.search.toLowerCase()) ||
                                            person.member.email.toLowerCase().includes(this.search.toLowerCase()))
      }
    }
  }
</script>
