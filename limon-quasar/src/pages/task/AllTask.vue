<template>
  <div>
    <div style="height: 65px">
      <q-input
        style="width: 400px"
        @keyup.enter="saveTitle"
        placeholder="Please Input Task"
        model-value="title">
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
      <q-field style="max-width: 400px">
        <q-input
          label="title"
          v-model="searchForm.title"
        />
      </q-field>
      <q-field style="max-width: 400px">
        <date-time-picker
          label="记录时间"
          v-model="searchForm.logTime"
        />
      </q-field>

      <div class="content-end">
        <q-btn
          label="Search"
          type="submit"
          @click="list"
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
    <div>
      <div class="row q-pa-md q-gutter-md ">
        <div class="col col-md-8 col-sm-12">
          <q-list
            dense
            bordered
            class="rounded-borders list q-pa-xs q-gutter-xs"
          >
            <div
              v-for="(task) in tasks"
              :key="task.id"
              class="row list-task"
            >
              <div class="col-8 q-gutter-auto" @click="edit(task.id)">
                <div class="taskStr" :class="{ 'done': task.status === 9}"
                     style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
                  {{ task.title }}
                </div>
                <q-item-label side top>
                  <q-badge
                    transparent
                    align="middle"
                    color="red"
                    v-if="task.dueTime"
                  >
                    <q-icon name="timer"/>
                    {{ task.dueTime }}
                  </q-badge>
                </q-item-label>
              </div>
              <div class="col-4 q-gutter-xs column items-end">
                <div>
                  <q-btn
                    size="12px" flat dense round
                    icon="delete"
                    @click="deleteTask(task)"
                  />
                  <q-btn
                    size="12px" flat dense round
                    icon="timer"
                    @click="setEndTime(task)"
                  />
                  <q-btn
                    size="12px" flat dense round
                    icon="done"
                    v-if="task.status !== 9"
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
              </div>
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
          <date-time-picker :time.sync="setTimeForm.task.dueTime"/>
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
import {saveTask} from 'src/api/task'
import DateTimePicker from 'components/form/DateTimePicker'
import client, {doPost} from 'src/utils/axios'
import {taskList} from 'src/api/url'
import {reactive, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";
import {Loading, useQuasar} from "quasar";
import {useRouter} from "vue-router";

export default {
  name: 'AllTask',
  components: {DateTimePicker},
  setup() {

    const $q = useQuasar()
    const router = useRouter();
    const state = reactive({
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
          title: null,
          logTime: null
        },
        filter: '',
        loading: false,
        tasks: []
      });


      onMounted(() => {
        list()
        if (!state.form.startTime) {
          state.form.startTime = Date.now()
        }
      })


      const list = () => {
        state.loading = true
        const queryRequest = {
          size: 50,
          current: 1
        }
        queryRequest.title = state.searchForm.title

        doPost(taskList, queryRequest).then(res => {
          state.tasks = res.data.records
        })
        state.loading = false
      }

      const saveTitle = () => {
        if (state.title.length > 0) {
          saveTask({title: state.title}).then(res => {
            $q.notify({
              message: "新增成功",
              position: 'bottom-left',
              type: "positive"
            })
            state.title = ''
            list()
          })
        }
      }

      // 完成任务
      const doneTask = (updatedTask) => {

        for (const i in state.tasks) {
          if (state.tasks[i].id === updatedTask.id) {
            updatedTask.status = 9
            saveTask(updatedTask)
            state.tasks.splice(i, 1)
            state.tasks.push(updatedTask)
            break
          }
        }
      }


      const edit = (id) => {
        const path = '/task/edit?id=' + id
        router.push(path)
      }

      const deleteTask = (task) => {
        console.log('删除' + task.id)
        // 删除任务
        $q.loading.show()
        client.post("/api/task/del", {
          id: task.id
        }).then(res => {
          $q.loading.hide()
          $q.notify({
            message: "删除成功",
            position: 'bottom-left',
            type: "positive"
          })
          list()
        })
      }
      const setEndTime = (task) => {
        console.log('开始设置时间' + task.id)
        state.setTimeForm = {loading: true}
        state.setTimeForm.task = task
      }

      const saveTaskDetail = async () => {
        Loading.show()
        await saveTask(state.setTimeForm.task)
        Loading.hide()
        state.setTimeForm.loading = false
      }

      return {
        ...toRefs(state),
        saveTaskDetail,
        saveTitle,
        list,
        doneTask,
        setEndTime,
        deleteTask,
        edit
      }

    }
  }
</script>

<style scoped>
.taskStr.done {
  text-decoration: line-through;
  color: rgba(0, 0, 0, .36);
}

.list {
  background-color: #EBECF0;
  border-radius: 3px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  max-height: 100%;
  position: relative;
  white-space: normal;
}

.list-task {
  background-color: #FFFFFF;
  border-radius: 3px;
  box-shadow: 0 1px 0 rgb(9 30 66 / 25%);
  cursor: pointer;
  margin-bottom: 8px;
  min-height: 46px;
  position: relative;
  text-decoration: none;
  z-index: 0;
}
</style>
