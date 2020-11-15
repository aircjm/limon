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
        class="q-pa-md"
        @submit="onSubmitForm"
      >
        <q-input
          v-model="form.title"
          label="title"
        />
        <q-select
          v-model="form.recordType"
          :options="options"
          :option-value="opt => Object(opt) === opt && 'id' in opt ? opt.id : null"
          :option-label="opt => Object(opt) === opt && 'desc' in opt ? opt.desc : ''"
          :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
          use-input
          new-value-mode="add"
        />
        <date-time-picker
          label="通知时间"
          :time.sync="form.noticeDate"
        />
        <q-input
          label="context"
          v-model="form.context"
          filled
          type="textarea"
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
import { getRecordDetail, saveRecord } from 'src/api/record'
import DateTimePicker from 'components/form/DateTimePicker'

export default {
  name: 'RecordEdit',
  components: { DateTimePicker },
  data () {
    return {

      date: null,
      openDialog: false,
      options: [
        {
          id: 1,
          desc: 'first'
        }
      ],
      form: {
        id: null,
        title: '',
        recordType: null,
        noticeDate: null,
        context: null
      }
    }
  },
  methods: {
    onSubmitForm (e) {
      saveRecord(this.form)
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
