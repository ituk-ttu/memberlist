<template lang="pug">
  span.badge(:class="getStatusClass(status)") {{ getStatusName(status) }}
</template>

<script>
  export default {
    name: 'MemberStatusLabel',
    props: ['status'],
    methods: {
      getStatusClass: function (status) {
        switch (status) {
          case 'ACTIVE':
            return 'badge-success'
          case 'BOARD':
            return 'badge-primary'
          case 'REPRESENTATIVE':
            return 'badge-info'
          case 'ALUMNI':
            return 'badge-danger'
          case 'GONE':
            return 'badge-secondary'
          default:
            return 'badge-light'
        }
      },
      getStatusName: function (status) {
        switch (status) {
          case 'ACTIVE':
            return 'aktiivgrupp'
          case 'BOARD':
            return 'juhatus'
          case 'REPRESENTATIVE':
            return 'volikogu'
          case 'ALUMNI':
            return 'vilistlane'
          case 'GONE':
            return 'läinud'
          default:
            return 'teadmata'
        }
      }
    }
  }
</script>
