<template>
  <q-page class="row">
    <div class="col-md-9 col-sm-12">
      <q-card>
        <q-card-section>
          <div
            class="text-h6"
            v-if="form.id"
          >
            Edit Record {{ form.id }}
          </div>
          <div
            class="text-h6"
            v-else
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
            <MarkdownEditor
              label="context"
              v-if="this.editorFlag"
              :context.sync="form.taskDesc"
            />
            <!--          <q-uploader-->
            <!--            color="teal-10"-->
            <!--            ref="uploaderRef"-->
            <!--            style="max-width: 300px"-->
            <!--            class="full-width"-->
            <!--            :factory="factoryUpload"-->
            <!--            hide-upload-btn-->
            <!--            multiple-->
            <!--            batch-->
            <!--          />-->
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
    </div>
    <div class="col-md-3 col-sm-12 column">
      <div class="col">
        <TagSelect
          label="标签"
          :tag-list.sync="form.tagList"
        />
        <date-time-picker
          label="开始时间"
          :time.sync="form.startTime"
        />
        <date-time-picker
          label="通知时间"
          :time.sync="form.endTime"
        />

        <date-time-picker
          label="截止时间"
          :time.sync="form.dueTime"
        />
      </div>
      <div class="col">
        2
      </div>
      <div class="col">
        3
      </div>
    </div>
  </q-page>
</template>

<script>
import { getTaskDetail, saveTask } from 'src/api/task'
import TagSelect from 'pages/tag/TagSelect'
import DateTimePicker from 'components/form/DateTimePicker'
import MarkdownEditor from 'components/editor/MarkdownEditor'
import { Notify } from 'quasar'

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
      },
      filter: '',
      loading: false,
      editorFlag: false
    }
  },
  watch: {
    // form: function (oldVal, newVal) {
    //   if (newVal.id && this.editorFlag) {
    //     debounce(this.autoSave(), 5000)
    //   }
    // }
  },
  created () {
    const id = this.$route.query.id
    if (id) {
      this.form.id = id
      this.$q.loading.show()
      getTaskDetail(id).then(res => {
        this.form = res.data
        this.$q.loading.hide()
        this.editorFlag = true
      })
    } else {
      this.editorFlag = true
    }
  },
  methods: {
    onSubmit () {
      saveTask(this.form).then(res => {
        if (res.code === 200) {
          this.$router.push('/task')
        }
      })
    },
    autoSave () {
      saveTask(this.form).then(res => {
        if (res.code === 200) {
          Notify.create({
            message: '自动更新成功'
          })
        }
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
