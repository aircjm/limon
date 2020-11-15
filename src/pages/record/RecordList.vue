<template>
  <q-page>
    <q-btn-group>
      <q-btn
        label="add"
        @click="openDialog = true"
      />
    </q-btn-group>
    <q-dialog v-model="openDialog">
      <RecordEdit />
    </q-dialog>
  </q-page>
</template>

<script>
import RecordEdit from 'pages/record/RecordEdit'
export default {
  name: 'RecordList',
  components: { RecordEdit },
  data () {
    return {
      title: '',
      recordType: null,
      date: null,
      openDialog: false,
      options: [
        {
          id: 1,
          desc: 'first'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
