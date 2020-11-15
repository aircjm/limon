<template>
  <q-card style="width: 800px; max-width: 60vw">
    <q-card-section>
      <div class="text-h6">
        Add Record
      </div>
    </q-card-section>
    <q-separator />
    <q-card-section>
      <q-form
        @reset="resetForm"
        style="max-width: 300px"
        class="q-ml-sm"
        @submit="onSubmitForm"
      >
        <q-input
          v-model="title"
          label="title"
        />
        <q-select
          v-model="recordType"
          :options="options"
          :option-value="opt => Object(opt) === opt && 'id' in opt ? opt.id : null"
          :option-label="opt => Object(opt) === opt && 'desc' in opt ? opt.desc : ''"
          :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
          use-input
          new-value-mode="add"
        />
        <date-time-picker
          :time.sync="noticeDate"
        />

        <div>
          <q-btn
            label="Submit"
            type="submit"
            color="primary"
          />
          <q-btn
            label="Reset"
            type="reset"
            color="primary"
            flat
            class="q-ml-sm"
          />
        </div>
      </q-form>
    </q-card-section>
  </q-card>
</template>

<script>
import { getRecordDetail } from 'src/api/record'
import DateTimePicker from 'components/form/DateTimePicker'

export default {
  name: 'RecordEdit',
  components: { DateTimePicker },
  data () {
    return {
      id: null,
      title: '',
      recordType: null,
      date: null,
      openDialog: false,
      noticeDate: null,
      options: [
        {
          id: 1,
          desc: 'first'
        }
      ]
    }
  },
  methods: {
    onSubmitForm (e) {
      console.log(this.noticeDate)
    },
    resetForm () {
      this.title = ''
      this.recordType = null
      this.date = null
      this.noticeDate = null
    }
  },
  created () {
    if (this.id) {
      // 获取详情内容 回填数据
      getRecordDetail(this.id)
    }
  }

}
</script>

<style scoped>

</style>
