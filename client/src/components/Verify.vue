<template lang="pug">
  .container
    icon(name="cog" scale="3" spin v-if="status === 'WORKING'")
    icon(name="times" scale="3" v-else-if="status === 'FAIL'")
    icon(name="check" scale="3" v-else)
</template>

<script>
  export default {
    name: 'Verify',
    data () {
      return {
        status: 'WORKING'
      }
    },
    mounted: function () {
      let self = this
      this.$http.post(this.$config.API_BASE + '/auth/verify', {
        email: this.$route.params.email,
        key: this.$route.params.key
      })
        .then(res => {
          self.status = 'SUCCESS'
          localStorage.setItem('accessToken', res.headers.get('Authorization').substring(7))
          localStorage.setItem('refreshToken', res.body)
          self.$router.push({ name: 'List' })
        }, res => {
          self.status = 'FAIL'
        })
    }
  }
</script>

<style scoped>
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 100%;
  }
</style>
