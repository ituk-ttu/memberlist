<template lang="pug">
  div
    .container
      h2 Lisa ruum
    form.container(v-on:submit="save()")
      .form-group
        label.control-label Ruumi nimi:
        input.form-control.form-control-lg(v-model="room" required)
      .form-group
        button.btn.btn-success(type="submit") Salvesta
</template>

<script>
  export default {
    name: 'AddRoom',
    data () {
      return {
        room: ''
      }
    },
    methods: {
      save: function () {
        let self = this
        this.$http.put(this.$config.API_BASE + '/doors', { name: self.room }).then(res => {
          self.$router.push({
            name: 'AccessList'
          })
        })
      }
    }
  }
</script>
