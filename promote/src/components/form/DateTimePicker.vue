<template>
  <div>
    <q-badge color="teal">
      Model: {{ time }}
    </q-badge>
    <q-input
      filled
      :label="label"
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
              mask="YYYY-MM-DD HH:mmZ"
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
              format24h
              mask="YYYY-MM-DD HH:mmZ"
              now-btn
            />
          </q-popup-proxy>
        </q-icon>
      </template>
    </q-input>
  </div>
</template>

<script>

import {date} from "quasar";

export default {
  name: 'DateTimePicker',
  props: {
    time: {
      type: String,
      default: ''
    },
    timestamp: {
      type: Number
    },
    label: {
      type: String,
      default: ''
    }
  },
  created() {
    console.log(this.props)
    debugger;
  },
  data: function () {
    return {
      dateTime: this.time
    }
  },
  methods: {
    updateDateTime() {
      this.$emit('update:time', this.dateTime)
      this.$emit('update:timestamp', date.formatDate(this.dateTime, 'x'))
    }
  }
}
</script>

<style scoped>

</style>
