<script setup lang="ts">
import {computed, ref} from 'vue';
const switchValue = ref(false)
const ts = ref(null)


const dateType = computed(() => {
  if (switchValue.value) {
    return 'date';
  } else {
    return 'datetime';
  }
})




</script>

<template>
  <div>
    <p>DatePickerTest</p>
    <n-switch v-model:value="switchValue"></n-switch>
    <n-date-picker :type="dateType" v-model:value="ts"/>
    <p>{{ts}}</p>
  </div>
</template>
