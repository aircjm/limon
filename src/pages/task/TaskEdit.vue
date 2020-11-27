<template>
  <q-page class="row items-center justify-evenly">
    <q-card style="width: 800px;max-width: 80%">
      <q-card-section>
        <div
          class="text-h6"
          v-if="form.id"
        >
          Edit Record {{ form.id }}
        </div>
        <div
          class="text-h6"
          v-if="form.id == null"
        >
          Add Record {{ form.id }}
        </div>
      </q-card-section>
      <q-separator />
      <q-card-section>
        <q-form
          class="q-gutter-y-md column"
        >
          <q-input
            v-model="form.title"
            label="title"
            :rules="[ val => val && val.length > 0 && val.length < 500 || 'Please type something']"
            @keyup.enter="onSubmit"
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
          <MarkdownEditor
            label="context"
            :context.sync="form.taskDesc"
          />

          <date-time-picker
            label="截止时间"
            :time.sync="form.dueTime"
          />
          <div>
            <q-btn
              icon="save"
              label="Submit"
              type="submit"
              color="primary"
              @click="onSubmit"
            />
            <q-btn
              class="mdi-book-cancel"
              label="Cancel"
              @click="goBack()"
              color="primary"
              flat
              v-close-popup
            />
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-page>
</template>

<script>
import { getTaskDetail, saveTask } from 'src/api/task'
import TagSelect from 'pages/tag/TagSelect'
import DateTimePicker from 'components/form/DateTimePicker'
import MarkdownEditor from 'components/editor/MarkdownEditor'

export default {
  name: 'TaskEdit',
  components: { MarkdownEditor, TagSelect, DateTimePicker },
  data () {
    return {
      title: '',
      recordType: null,
      date: null,
      openDialog: false,
      form: {
        id: null,
        title: '',
        type: null,
        dueTime: null,
        startTime: null,
        endTime: null,
        taskDesc: null
      },
      filter: '',
      loading: false
    }
  },
  async created () {
    const id = this.$route.query.id
    if (id) {
      this.form.id = id
      await getTaskDetail(id).then(res => {
        this.form = res.data
      })
    }
  },
  methods: {
    onSubmit () {
      saveTask(this.form).then(res => {
        this.$router.push('/task')
      })
    },
    resetForm () {
      this.form.title = ''
      this.form.dueTime = null
      this.form.startTime = null
      this.form.endTime = null
      this.openDialog = false
    },
    goBack () {
      this.$router.back()
    }
  }
}
</script>

<style scoped>

</style>
