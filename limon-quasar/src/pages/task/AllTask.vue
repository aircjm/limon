<template>
  <div class="column q-gutter-md q-pa-md">
    <div class="col-1">
      <div class="col shadow-2 q-pa-md">
        <div class="row items-center justify-start q-mb-md">
          <q-item class="">
            <q-item-section class="col-3 text-right gt-sm">
              <q-item-label>标题：</q-item-label>
            </q-item-section>
            <q-item-section class="col">
              <q-input label="标题" v-model="searchForm.title"/>
            </q-item-section>
          </q-item>
          <q-item class="col-xl-2 col-md-3 col-sm-6 col-xs-12">
            <q-item-section class="col-3 text-right gt-sm">
              <q-item-label>记录时间：</q-item-label>
            </q-item-section>
            <q-item-section class="col">
              <date-time-picker label="记录时间" style="width: 220px" v-model:time="searchForm.logTimeStr" v-model:timestamp="searchForm.logTime"/>
            </q-item-section>
          </q-item>
          <q-item class="col-xl-2 col-md-3 col-sm-6 col-xs-12 q-pr-sm">
            <q-item-label class="col-12 text-right row no-wrap justify-center">
              <q-btn
                label="查询"
                no-wrap
                color="primary"
                class="q-mr-sm"
                type="submit"
                :loading="searchForm.loading"
                @click="list"
              >
                <template v-slot:loading>
                  <q-spinner-ios/>
                </template>
              </q-btn>
              <q-btn
                outline
                label="重置"
                no-wrap
                class="q-mr-sm"
                color="secondary"
                @click="onReset"
              />
            </q-item-label>
          </q-item>
        </div>
      </div>
    </div>
    <div class="col col-md-8 col-sm-12">
      <div class="col-md-7 col-sm-12">
        <q-input outlined standout v-model="title" @keypress.enter="saveTitle">
          <template v-slot:prepend>
            <q-icon name="task"/>
          </template>
          <template v-slot:append>
            <q-icon name="event" />
            <q-separator  vertical spaced />
            <q-btn-dropdown outline flat color="black-3" dense>
              <div class="row no-wrap q-pa-md">
                <div class="column">
                  <div class="text-h6 q-mb-md">Settings</div>
                  <q-btn label="Use Mobile Data" />
                  <q-btn label="Bluetooth" />
                </div>

                <q-separator vertical inset class="q-mx-lg" />

                <div class="column items-center">
                  <div class="text-subtitle1 q-mt-md q-mb-xs">John Doe</div>

                  <q-btn
                    color="primary"
                    label="Logout"
                    push
                    size="sm"
                    v-close-popup
                  />
                </div>
              </div>
            </q-btn-dropdown>
          </template>

          <template v-slot:after>
            <q-btn icon="add_task" color="green-4" @click="addTaskFlag = true"/>
          </template>
        </q-input>
      </div>
      <q-separator spaced />
      <q-list dense class="q-pa-xs q-gutter-xs">
        <div v-for="(task) in tasks" :key="task.id" class="row list-task">
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
    <q-dialog v-model="timeDialogFlag" persistent style="min-width: 400px">
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
          <date-time-picker :time.sync="setTimeForm.dueTime"/>
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
    <q-dialog v-model="addTaskFlag" persistent>
      <q-card style="min-width: 500px;">
        <q-toolbar>
          <q-toolbar-title><span class="text-weight-bold">新增任务</span></q-toolbar-title>
          <q-btn
            flat
            round
            dense
            icon="close"
            v-close-popup
          />
        </q-toolbar>
        <q-card-section>
          <q-input
            filled
            v-model="title"
            label="title *"
            :rules='[(v) => !!v || "不可以为空"]'
          />
        </q-card-section>
        <q-card-actions
          align="right"
          class="text-primary"
        >
          <q-btn
            flat
            label="submit"
            icon="primary"
            @click="saveTitle"
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
import client, {doPost} from 'src/utils/axios'
import {taskList} from 'src/api/url'
import {computed, reactive, ref, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";
import {useQuasar} from "quasar";
import {useRouter} from "vue-router";
import DateTimePicker from "../../components/form/DateTimePicker";
import TaskModel from "src/classes/task";

export default {
  name: 'AllTask',
  components: {DateTimePicker},
  setup: function () {
    const $q = useQuasar()
    const router = useRouter();
    const timeDialogFlag = ref(false)

    const addTaskFlag = ref(false)
    const state = reactive({
      title: '',
      recordType: null,
      date: null,
      setTimeForm: {
        dueTime: null,
        id: null
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
        logTime: null,
        logTimeStr: null,
        loading: false
      },
      filter: '',
      tasks: []
    });

    const addTaskValue = ref('')


    onMounted(() => {
      list()
      if (!state.form.startTime) {
        state.form.startTime = Date.now()
      }
    })


    const list = () => {
      const queryRequest = {
        size: 50,
        current: 1
      }
      queryRequest.title = state.searchForm.title

      doPost(taskList, queryRequest).then(res => {
        state.tasks = res.data.records
      })
    }

    const saveTitle = () => {
      const task = new TaskModel()
      task.name = state.title
      // 缺少校验
      saveTask(task).then(res => {
        console.log(res)
        $q.notify({
          message: "新增成功",
          type: "positive"
        })
        addTaskFlag.value = false
        state.title = ''
        list()

      })
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
      timeDialogFlag.value = true
      state.setTimeForm.id = task.id
      state.setTimeForm.dueTime = task.dueTime
    }

    const onReset = () => {
    }

    const saveTaskDetail = async () => {
      await saveTask(state.setTimeForm)
      console.log("保存时间" + JSON.stringify(state.setTimeForm))
      timeDialogFlag.value = false
      state.setTimeForm.dueTime = null
      state.setTimeForm.id = null
    }

    return {
      ...toRefs(state),
      timeDialogFlag,
      saveTaskDetail,
      saveTitle,
      list,
      doneTask,
      addTaskFlag,
      setEndTime,
      deleteTask,
      edit,
      addTaskValue,
      onReset
    }

  }
}
</script>

<style scoped>
.taskStr.done {
  text-decoration: line-through;
  color: rgba(0, 0, 0, .36);
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
