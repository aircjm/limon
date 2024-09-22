<script setup lang="ts">
import {computed, ref} from 'vue'
import {NDatePicker} from 'naive-ui';

function changeDate(value: number, formatValue: string) {
  console.log("value is "+ value);
  console.log("formatValue is "+ formatValue);
}


const is_all_day = ref<boolean>(false);
const dateType = computed(() => {
  if (is_all_day.value) {
    return "date";
  } else {
    return "datetime";
  }
})

</script>

<template>


  <n-switch v-model:value="is_all_day" />
  {{is_all_day}}
  {{dateType}}
  <div class="card">
    <NDatePicker :type="dateType" :on-update:value="changeDate" />
  </div>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
