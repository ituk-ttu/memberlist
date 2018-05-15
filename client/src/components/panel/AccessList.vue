<template lang="pug">
  .container
    h2 Ligipääsuõigused
      router-link.btn.btn-success.float-right(:to="{name: 'AddRoom'}") Lisa ruum
    table.table(v-if="doors != null && people != null")
      thead
        tr
          th Liige
          th(v-for="door in doors") {{ door.name }}
          th
      tbody
        tr(v-for="user in people")
          td: router-link(:to="{ name: 'Member', params: {'id': user.id } }"): strong {{ user.member.name }}
          td(v-for="door in doors" v-if="editing !== user.id")
            icon.text-success(name="check" v-if="accessContainsDoor(user.accessCollection.doors, door)")
            icon.text-muted(name="times" v-else)
          td(v-for="door in newDoors" v-if="editing === user.id")
            .form-check: input.form-check-input(type="checkbox" v-model="door.enabled")
          td(v-if="editing !== user.id"): a(v-on:click.prevent="edit(user)" href="#"): span.text-warning Muuda
          td(v-if="editing === user.id")
            a(v-on:click.prevent="save()" href="#"): span.text-success Salvesta
            | &nbsp;&nbsp;&nbsp;
            a(v-on:click.prevent="editing = null" href="#"): span.text-danger Tühitsa
    icon(name="cog" scale="3" spin v-else)
</template>

<script>
  export default {
    name: 'AccessList',
    data () {
      return {
        search: '',
        people: null,
        doors: null,
        editing: null,
        newDoors: []
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
      },
      edit: function (user) {
        this.editing = null
        this.newDoors = []
        this.doors.forEach(door => this.newDoors.push({
          id: door.id,
          name: door.name,
          enabled: this.accessContainsDoor(user.accessCollection.doors, door)
        }))
        this.editing = user.id
      },
      save: function () {
        let self = this
        let allowedDoors = []
        this.newDoors.forEach(door => {
          if (door.enabled) {
            allowedDoors.push({
              id: door.id,
              name: door.name
            })
          }
        })
        this.$http.put(this.$config.API_BASE + '/users/access/' + this.editing, { doors: allowedDoors }).then(res => {
          this.$http.get(this.$config.API_BASE + '/users').then(res => {
            self.people = res.body
            self.editing = null
          })
        })
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
