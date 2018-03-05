<template lang="pug">
  .container
    h2 Liikmed
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
          td: member-status-label(:status="person.status")
</template>

<script>
  import MemberStatusLabel from './MemberStatusLabel'
  export default {
    components: {MemberStatusLabel},
    name: 'List',
    data () {
      return {
        people: [
          {
            'id': 1,
            'name': 'Ennis Bille',
            'email': 'ebille0@google.com.hk',
            'idCode': '71944763602',
            'status': 'REPRESENTATIVE'
          },
          {
            'id': 2,
            'name': 'Carolann Goodisson',
            'email': 'cgoodisson1@sakura.ne.jp',
            'idCode': '44626972479',
            'status': 'BOARD'
          },
          {
            'id': 3,
            'name': 'Constance Manon',
            'email': 'cmanon2@exblog.jp',
            'idCode': '22543853391',
            'status': 'GONE'
          },
          {
            'id': 4,
            'name': 'Ronica Pennaman',
            'email': 'rpennaman3@issuu.com',
            'idCode': '75826420824',
            'status': 'REPRESENTATIVE'
          },
          {
            'id': 5,
            'name': 'Koral Pearn',
            'email': 'kpearn4@fastcompany.com',
            'idCode': '18339583650',
            'status': 'REPRESENTATIVE'
          },
          {
            'id': 6,
            'name': 'Kip Creffield',
            'email': 'kcreffield5@phpbb.com',
            'idCode': '42762882791',
            'status': 'ALUMNI'
          },
          {
            'id': 7,
            'name': 'Garold Fillan',
            'email': 'gfillan6@phoca.cz',
            'idCode': '08411861044',
            'status': 'BOARD'
          },
          {
            'id': 8,
            'name': 'Rosemonde Fetherston',
            'email': 'rfetherston7@youku.com',
            'idCode': '00893410626',
            'status': 'REPRESENTATIVE'
          },
          {
            'id': 9,
            'name': 'Fanni Fermin',
            'email': 'ffermin8@amazon.co.jp',
            'idCode': '35198227150',
            'status': 'GONE'
          },
          {
            'id': 10,
            'name': 'Barbara Grestye',
            'email': 'bgrestye9@ucsd.edu',
            'idCode': '78581573775',
            'status': 'REPRESENTATIVE'
          }
        ]
      }
    }
  }
</script>
