<template>
  <div>
    <div style="height: 65px">
      <q-input
        v-model="title"
        style="width: 400px"
        @keyup.enter="saveTitle"
        placeholder="Please Input Task"
      >
        <template v-slot:append>
          <q-btn>
            <q-icon
              name="add"
              @click.native="saveTitle"
            />
          </q-btn>
        </template>
      </q-input>
    </div>
    <div class="q-gutter-md">
      <q-field style="max-width: 200px">
        <q-input
          label="title"
          v-model="searchForm.title"
        />
      </q-field>

      <div class="content-end">
        <q-btn
          label="Search"
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
    </div>
    <div class="row">
      <div class="col-md-4 col-sm-12">
        <div class="q-pa-md q-gutter-md">
          <q-list
            dense
            bordered
            class="rounded-borders"
            style="max-width: 650px"
          >
            <div
              v-for="(task) in tasks"
              :key="task.id"
            >
              <q-item
                tag="label"
                v-ripple
              >
                <!--                <q-item-section-->
                <!--                  side-->
                <!--                  top-->
                <!--                >-->
                <!--                  <q-checkbox v-model="task.status" />-->
                <!--                </q-item-section>-->
                <q-item-section>
                  <q-item-label
                    style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"
                    :class="{ 'done': task.status == 9}"
                    class="taskStr"
                  >
                    {{ task.title }}
                  </q-item-label>
                  <q-item-label
                    side
                    top
                  >
                    <q-badge
                      transparent
                      align="middle"
                      color="red"
                      v-if="task.dueTime"
                    >
                      <q-icon name="timer" />
                      {{ task.dueTime }}
                    </q-badge>
                  </q-item-label>
                </q-item-section>
                <q-item-section
                  top
                  side
                >
                  <div class="text-grey-8 q-gutter-xs">
                    <q-btn
                      class="gt-xs"
                      size="12px"
                      flat
                      dense
                      round
                      icon="delete"
                      @click="deleteTask(task)"
                    />
                    <q-btn
                      class="gt-xs"
                      size="12px"
                      flat
                      dense
                      round
                      icon="timer"
                      @click="setEndTime(task)"
                    />
                    <q-btn
                      class="gt-xs"
                      size="12px"
                      flat
                      dense
                      round
                      icon="done"
                      v-if="task.status != 9"
                      @click="doneTask(task)"
                    />

                    <router-link :to="`/task/edit?id=${task.id}`">
                      <template>
                        <q-btn
                          size="12px"
                          flat
                          dense
                          round
                          icon="more_vert"
                        />
                      </template>
                    </router-link>
                  </div>
                </q-item-section>
              </q-item>
            </div>
          </q-list>
        </div>
      </div>
    </div>
    <q-dialog
      v-model="setTimeForm.loading"
      persistent
      style="min-width: 300px"
    >
      <q-card>
        <q-toolbar>
          <q-toolbar-title><span class="text-weight-bold">设置截至时间</span></q-toolbar-title>
          <q-btn
            flat
            round
            dense
            icon="close"
            v-close-popup
          />
        </q-toolbar>
        <q-card-section>
          <date-time-picker :time.sync="setTimeForm.task.dueTime" />
        </q-card-section>
        <q-card-actions
          align="right"
          class="text-primary"
        >
          <q-btn
            flat
            label="submit"
            icon="primary"
            @click="saveTaskDetail"
          />
          <q-btn
            flat
            label="Close"
            v-close-popup
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
import { getTaskList, saveTask } from 'src/api/task'
import DateTimePicker from 'components/form/DateTimePicker'

export default {
  name: 'AllTask',
  components: { DateTimePicker },
  data () {
    return {
      title: '',
      recordType: null,
      date: null,
      setTimeForm: {
        loading: false,
        task: {}
      },
      form: {
        id: '',
        title: '',
        type: null,
        dueTime: null,
        startTime: null,
        endTime: null,
        context: null
      },
      searchForm: {
        title: null
      },
      filter: '',
      loading: false,
      tasks: []
    }
  },
  mounted () {
    this.list()
  },
  methods: {
    edit (id) {
      const path = '/task/edit?id==' + id
      this.$route.push(path)
    },
    saveTitle () {
      if (this.title.length > 0) {
        saveTask({ title: this.title }).then(res => {
          this.title = ''
          this.list()
        })
      }
    },
    deleteTask (task) {
      console.log('删除' + task.id)
      // 删除任务
    },
    setEndTime (task) {
      console.log('设置时间' + task.id)
      // 删除任务
      this.setTimeForm = { loading: true }
      this.setTimeForm.task = task
    },
    // 完成任务
    doneTask (updatedTask) {
      for (const i in this.tasks) {
        if (this.tasks[i].id === updatedTask.id) {
          updatedTask.status = 9
          saveTask(updatedTask)
          this.tasks.splice(i, 1)
          this.tasks.push(updatedTask)
          break
        }
      }
    },
    async saveTaskDetail () {
      this.$q.loading.show()
      await saveTask(this.setTimeForm.task)
      this.$q.loading.hide()
      this.setTimeForm.loading = false
    },
    list () {
      this.loading = true
      const queryRequest = {
        size: 1000,
        current: 1
      }
      queryRequest.title = this.searchForm.title

      // calculate starting row of data

      // fetch data from "server"
      getTaskList(queryRequest).then(res => {
        this.tasks = res.data.records
      })
      // ...and turn of loading indicator
      this.loading = false
    },
    onSubmitForm (e) {
      this.$refs.myForm.validate().then(success => {
        if (success) {
          // 是的，模型是正确的
        } else {
          // 哦，不，用户至少
          // 填写了一个无效值
        }
      })

      // 重置验证：
      this.$refs.myForm.resetValidation()
      saveTask(this.form)
    },
    resetForm () {
    }

  }
}
</script>

<style scoped>
.taskStr.done {
  text-decoration: line-through;
  color: rgba(0,0,0,.36);
}
</style>
