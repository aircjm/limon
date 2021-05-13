<template>
  <div>
    <q-input
      filled
      :label="label"
      clearable
      v-model="dateTime"
      @blur="updateDateTime"
    >
      <template v-slot:prepend>
        <q-icon name="event" class="cursor-pointer">
          <q-popup-proxy transition-show="scale" transition-hide="scale">
            <q-date v-model="dateTime" mask="YYYY-MM-DD HH:mmZ">
              <div class="row items-center justify-end">
                <q-btn v-close-popup label="Close" color="primary" flat/>
              </div>
            </q-date>
          </q-popup-proxy>
        </q-icon>
      </template>

      <template v-slot:append>
        <q-icon name="access_time" class="cursor-pointer">
          <q-popup-proxy transition-show="scale" transition-hide="scale">
            <q-time v-model="dateTime" mask="YYYY-MM-DD HH:mmZ" format24h now-btn>
              <div class="row items-center justify-end">
                <q-btn v-close-popup label="Close" color="primary" flat/>
              </div>
            </q-time>
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
