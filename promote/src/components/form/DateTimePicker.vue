<template>
  <q-input
    :label="label"
    filled
    clearable
    v-model="dateTime"
    @blur="updateDateTime"
  >
    <template v-slot:prepend>
      <q-icon
        name="today"
        class="cursor-pointer"
      >
        <q-popup-proxy
          transition-show="scale"
          transition-hide="scale"
        >
          <q-date
            v-model="dateTime"
            default-year-month="2020/01"
            mask="YYYY-MM-DD HH:mm:ss"
          />
        </q-popup-proxy>
      </q-icon>
    </template>

    <template v-slot:append>
      <q-icon
        name="access_time"
        class="cursor-pointer"
      >
        <q-popup-proxy
          transition-show="scale"
          transition-hide="scale"
        >
          <q-time
            v-model="dateTime"
            mask="YYYY-MM-DD HH:mm:ss"
            format24h
            now-btn
          />
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script>

export default {
  name: 'DateTimePicker',
  props: {
    time: {
      type: String,
      default: ''
    },
    label: {
      type: String,
      default: ''
    }
  },
  data: function () {
    return {
      dateTime: this.time
    }
  },
  methods: {
    updateDateTime () {
      this.$emit('update:time', this.dateTime)
    }
  }
}
</script>

<style scoped>

</style>
