<template lang="pug">
  .container
    h2 {{ member.name }}
    p
      strong E-mail:
      |  {{ member.email }}
    p
      strong Isikukood:
      |  {{ member.idCode }}
    p
      strong Staatus:
      |  {{ member.status }}
</template>

<script>
  export default {
    name: 'List',
    data () {
      return {
        member: {
          'id': 1,
          'name': 'Ennis Bille',
          'email': 'ebille0@google.com.hk',
          'idCode': '71944763602',
          'status': 'REPRESENTATIVE_BODY'
        }
      }
    }
  }
</script>
