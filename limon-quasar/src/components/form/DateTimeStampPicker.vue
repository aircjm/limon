<template>
  <q-input
    filled
    autogrow
    style="max-width: 300px"
    v-model="text"
    :label="label"
    clearable
    @clear="value = 0"
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
import {computed, defineComponent, ref} from "vue";
import {date} from "quasar";

export default defineComponent({
  props: {
    modelValue: {
      type: [Number, Object],
      default: 0
    },
    label: {
      type: String,
      required: true
    }
  },
  emits: ["update:modelValue"],
  setup(props, {emit}) {

    const model = ref(props.modelValue);
    const value = computed({
      get: () => {
        if (model.value) {
          return model.value;
        }
      },
      set: (val) => {
        model.value = val;
        return emit("update:modelValue", val);
      }
    });


    const switchDate = ref(true)

    return {
      value,
      switchDate,
      text: computed(() => {
        if (model.value === 0) {
          return '';
        }
        return date.formatDate(new Date(Number(model.value)), "YYYY-MM-DD HH:mm:ss");
      })
    };
  }
});
</script>
