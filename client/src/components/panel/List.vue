<template lang="pug">
  .container
    h2 Liikmed
      router-link(:to="{ name: 'Member', params: { id: 'add'} }").btn.btn-success.float-right Lisa
    table.table
      thead
        tr
          th(scope="col") ID
          th(scope="col") Nimi
          th(scope="col") Isikukood
          th(scope="col") tudengikood
          th(scope="col") E-mail
          th(scope="col") Staatus
          th
      tbody
        tr
          td(colspan="7"): input.form-control(v-model="search")
        tr(v-if="people == null")
          td(colspan="7").text-center Laadin...
        tr(v-for="person in searchedPeople" v-else)
          td {{ person.member.id }}
          td {{ person.member.name }}
          td {{ person.member.personalCode }}
          td {{ person.member.studentCode }}
          td {{ person.member.email }}
          td: member-status-label(:status="person.member.status")
          td
            router-link(:to="{ name: 'Member', params: { id: person.id } }").text-info Vaata
</template>

<script>
  import MemberStatusLabel from './MemberStatusLabel'
  export default {
    components: {MemberStatusLabel},
    name: 'List',
    data () {
      return {
        search: '',
        people: null
      }
    },
    mounted: function () {
      let self = this
      this.$http.get(this.$config.API_BASE + '/users').then(res => { self.people = res.body })
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
