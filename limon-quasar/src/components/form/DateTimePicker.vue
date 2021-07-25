<template>
  <div>
    <q-input
      :label="label"
      clearable
      v-model="dateTime"
      @blur="updateDateTime"
    >
      <template v-slot:prepend>
        <q-icon name="event" class="cursor-pointer">
          <q-popup-proxy transition-show="scale" transition-hide="scale">
            <q-date v-model="dateTime" mask="YYYY-MM-DD HH:mm:ss" today-btn>
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
            <q-time v-model="dateTime" mask="YYYY-MM-DD HH:mm:ss" format24h now-btn>
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
import {ref} from "@vue/reactivity";

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

  setup(props, context) {
    let time = props.time;
    let timestamp = props.timestamp;
    if (timestamp) {
      time =  date.formatDate(timestamp, 'YYYY-MM-DD HH:mm:ss.SSS')
    }

    const dateTime = ref(time)

    const updateDateTime = () => {
      context.emit('update','time', dateTime.value)
      context.emit('update','timestamp', date.formatDate(dateTime.value, 'x'))
    }

    return {
      updateDateTime,
      dateTime
    }

  }
}
</script>
