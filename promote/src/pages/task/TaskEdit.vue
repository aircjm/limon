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
        <q-btn
          @click="tag.editorFlag = true"
          label="添加标签"
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
    <q-dialog
      v-model="tag.editorFlag"
      style="min-width: 300px"
    >
      <q-card>
        <q-card-section>
          <q-toolbar-title>添加标签</q-toolbar-title>
          <q-input
            ref="filter"
            filled
            v-model="tag.filter"
            label="Search - only filters labels that have also '(*)'"
          >
            <template v-slot:append>
              <q-icon
                v-if="filter !== ''"
                name="clear"
                class="cursor-pointer"
                @click="resetFilter"
              />
            </template>
          </q-input>
          <div
            v-for="(selectTag) in tag.selectList"
            :key="selectTag.id"
          >
            <q-badge
              outline
              :color="selectTag.color ? selectTag.color: 'red'"
              :label="selectTag.label"
            />
          </div>
          <q-list>
            <q-item
              v-for="(tagDetail) in tag.tagList"
              :key="tagDetail.id"
            >
              <q-item-label @click="addTag(tagDetail)">
                {{ tagDetail.label }}
              </q-item-label>
            </q-item>
          </q-list>
        </q-card-section>
        <q-card-actions>
          <q-btn>submit</q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
import { getTaskDetail, saveTask } from 'src/api/task'
import DateTimePicker from 'components/form/DateTimePicker'
import MarkdownEditor from 'components/editor/MarkdownEditor'
import { Notify } from 'quasar'

export default {
  name: 'TaskEdit',
  components: { MarkdownEditor, DateTimePicker },
  data () {
    return {
      title: '',
      recordType: null,
      date: null,
      openDialog: false,
      form: {},
      filter: '',
      loading: false,
      editorFlag: false,
      tag: {
        editorFlag: false,
        filter: '',
        tagList: [{ id: 1, label: '测试' }, { id: 2, label: 'test' }],
        selectList: []
      }

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
    addTag (tagDetail) {
      console.log(this.tag.selectList.indexOf(tagDetail))
      if (this.tag.selectList.indexOf(tagDetail) > -1) {
      } else {
        this.tag.selectList.push(tagDetail)
      }
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
