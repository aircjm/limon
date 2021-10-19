<template>
  <q-input
    filled
    autogrow
    style="max-width: 300px"
    v-model="text"
    :label="label"
    clearable
    @clear="value = null"
  >
    <template v-slot:append>
      <q-icon name="event" class="cursor-pointer">
        <q-popup-proxy transition-show="scale" transition-hide="scale">
          <div>
            <div v-if="switchDate">
              <q-date v-model="value" mask="x">
                <div class="row items-center justify-between">
                  <q-btn label="Time" color="primary" @click="switchDate= false"></q-btn>
                  <q-btn v-close-popup label="Close" color="primary" flat/>
                </div>
              </q-date>
            </div>
            <div v-else>
              <q-time v-model="value" mask="x" format24h>
                <div class="row items-center justify-between">
                  <q-btn label="Date" color="primary" @click="switchDate= true"></q-btn>
                  <q-btn v-close-popup label="Close" color="primary" flat/>
                </div>
              </q-time>
            </div>
            <div>

            </div>
          </div>
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script>
import {computed, defineComponent, ref, watch} from "vue";
import {date} from "quasar";

export default defineComponent({
  props: {
    timestamp: {
      type: [Number, Object]
    },
    label: {
      type: String,
      required: true
    }
  },
  emits: ["update:timestamp"],
  setup(props, ctx) {
    const model = ref(props.timestamp);
    const value = computed({
      get: () => {
        if (model.value) {
          return model.value;
        }
      },
      set: (val) => {
        model.value = val;
        console.log("timestamp set "+ val)
        return ctx.emit('update:timestamp', val);
      }
    });

    watch(() => props.timestamp, (first, second) => {
      if (first) {
        value.value = null
      }
      value.value = first
    });


    const switchDate = ref(true)

    return {
      value,
      switchDate,
      text: computed(() => {
        if (model.value === null) {
          return '';
        }
        return date.formatDate(new Date(Number(model.value)), "YYYY-MM-DD HH:mm:ss");
      })
    };
  }
});
</script>
