<template>
  <q-card style="width: 800px;max-width: 80%">
    <q-card-section>
      <div class="text-h6">
        Add Record
      </div>
    </q-card-section>
    <q-separator />
    <q-card-section>
      <q-form
        @reset="resetForm"
        class="q-gutter-y-md column"
        @submit="onSubmitForm"
      >
        <q-input
          v-model="form.title"
          label="title"
        />
        <TagSelect :select.sync="form.tags" />

        <date-time-picker
          label="开始时间"
          :time.sync="form.startTime"
        />
        <date-time-picker
          label="通知时间"
          :time.sync="form.endTime"
        />
        <q-input
          label="context"
          v-model="form.context"
          filled
          type="textarea"
        />

        <date-time-picker
          label="截止时间"
          :time.sync="form.dueTime"
        />
        <div>
          <q-btn
            label="Submit"
            type="submit"
            color="primary"
          />
          <!--<q-btn
            label="Reset"
            type="reset"
            color="primary"
            flat
            class="q-ml-sm"
          />-->
        </div>
      </q-form>
    </q-card-section>
  </q-card>
</template>

<script>
import { getTaskDetail, saveTask } from 'src/api/task'
import DateTimePicker from 'components/form/DateTimePicker'
import TagSelect from 'pages/tag/TagSelect'

export default {
  name: 'TaskEdit',
  components: { TagSelect, DateTimePicker },
  data () {
    return {
      date: null,
      openDialog: false,
      tags: [
        1
      ],
      form: {
        id: null,
        title: '',
        type: null,
        dueTime: null,
        startTime: null,
        endTime: null,
        context: null
      }
    }
  },
  methods: {
    onSubmitForm (e) {
      saveTask(this.form)
    },
    resetForm () {
      this.title = ''
      this.recordType = null
      this.date = null
      this.noticeDate = null
    },
    createValue (val, done) {
      // specific logic to eventually call done(...) -- or not
      done(val, 'add-unique')

      // done callback has two optional parameters:
      //  - the value to be added
      //  - the behavior (same values of new-value-mode prop,
      //    and when it is specified it overrides that prop –
      //    if it is used); default behavior (if not using
      //    new-value-mode) is to add the value even if it would
      //    be a duplicate
    }
  },
  created () {
    if (this.id) {
      // 获取详情内容 回填数据
      getTaskDetail(this.id)
    }
  }

}
</script>

<style scoped>

</style>
