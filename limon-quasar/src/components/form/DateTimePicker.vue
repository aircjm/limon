<template>
  <q-input
    filled
    autogrow
    :modelValue="text"
    :label="label"
    clearable
    @clear="value = null"
  >
    <template v-slot:prepend>
      <q-icon name="event" class="cursor-pointer">
        <q-popup-proxy transition-show="scale" transition-hide="scale">
          <q-date v-model="value" mask="YYYY-MM-DD HH:mm:ss" :range="range">
            <div class="row items-center justify-end">
              <q-btn v-close-popup label="Close" color="primary" flat />
            </div>
          </q-date>
        </q-popup-proxy>
      </q-icon>
    </template>
    <template v-slot:append>
      <q-icon name="access_time" class="cursor-pointer">
        <q-popup-proxy v-if="range" transition-show="scale" transition-hide="scale">
          <q-time
            v-model="value.from"
            @update:modelValue="
              $emit('update:modelValue', `${value.from},${value.to}`)
            "
            mask="YYYY-MM-DD HH:mm:ss"
            format24h
          >
            <div class="row items-center justify-end">
              <q-btn v-close-popup label="Close" color="primary" flat />
            </div>
          </q-time>
          <q-time
            v-model="value.to"
            @update:modelValue="
              $emit('update:modelValue', `${value.from},${value.to}`)
            "
            mask="YYYY-MM-DD HH:mm:ss"
            format24h
          >
            <div class="row items-center justify-end">
              <q-btn v-close-popup label="Close" color="primary" flat />
            </div>
          </q-time>
        </q-popup-proxy>
        <q-popup-proxy v-else transition-show="scale" transition-hide="scale">
          <q-time v-model="value" mask="YYYY-MM-DD HH:mm:ss" format24h>
            <div class="row items-center justify-end">
              <q-btn v-close-popup label="Close" color="primary" flat />
            </div>
          </q-time>
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script>
import { defineComponent, ref, computed } from "vue";
import { useVuelidate } from "@vuelidate/core";

export default defineComponent({
  name: "DateTimePicker",
  props: {
    modelValue: {
      type: [String, Object],
      default: ""
    },
    label: {
      type: String,
      required: true
    },
    range: {
      type: Boolean,
      default: false
    }
    // rules: {
    //   type: Object,
    //   required: true,
    // },
  },
  emits: ["update:modelValue"],
  setup(props, { emit }) {
    const model = ref(props.modelValue);
    const value = computed({
      get: () => {
        if (model.value) {
          return model.value;
        }
        if (!props.range) {
          return "";
        } else {
          return { from: "", to: "" };
        }
      },
      set: (val) => {
        model.value = val;
        if (val && props.range) {
          if (!val.from && !val.to) {
            model.value = { from: val, to: val };
          }
          return emit("update:modelValue", `${val.from},${val.to}`);
        }
        return emit("update:modelValue", val);
      }
    });
    const v$ = useVuelidate(props.rules, value);

    return {
      value,
      text: computed(() => {
        if (!props.range) return model.value;
        return `${model.value?.from || ""}~${model.value?.to || ""}`;
      }),
      v$
    };
  }
});
</script>
