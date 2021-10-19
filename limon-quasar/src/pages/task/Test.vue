<template>
  <div>
    <p>{{date}}</p>
    <DateTimeStampPicker v-model:timestamp="date"/>
  </div>
</template>

<script>

import DateTimeStampPicker from "components/form/DateTimeStampPicker";
import {ref} from "vue";


export default {
  name: "Test",
  components: {
    DateTimeStampPicker
  },
  setup() {

    const  date =  ref(1633492560000)

    setTimeout(() => {
      date.value = date.value + 1111100000
    }, 2000)
    return {
      date
    }
  }
}
</script>

<style scoped>

</style>
