<template lang="pug">
  div.container
    table.table
      thead
        tr
          th(scope="col") ID
          th(scope="col") Nimi
          th(scope="col") Isikukood
          th(scope="col") E-mail
          th(scope="col") Staatus
      tbody
        tr(v-for="person in people")
          td {{ person.id }}
          td {{ person.name }}
          td {{ person.idCode }}
          td {{ person.email }}
          td {{ person.status }}
</template>

<script>
  export default {
    name: 'List',
    data () {
      return {
        people: [
          {
            id: 0,
            name: 'Kelly Garrett',
            idCode: '49510210324',
            email: 'kelly@ituk.ee',
            status: 'MEMBER'
          }
        ]
      }
    }
  }
</script>
