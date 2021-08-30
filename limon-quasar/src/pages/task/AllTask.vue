<template>
  <div class="column q-gutter-md q-pa-md">
    <div class="row">
      <div class="col-2">
        <!--左列表-->
      </div>
      <div class="col-6">
        <!--中间列表数据-->
        <div class="col-md-6 col-sm-12 col-lg-4">
          <q-input outlined standout v-model="newTask.name" @keydown.enter="saveTaskName">
            <template v-slot:prepend>
              <q-icon name="task"/>
            </template>
            <template v-slot:append>
              <q-icon name="event"/>
              <q-separator vertical spaced/>
              <q-btn-dropdown outline flat color="black-3" dense>
                <div class="row no-wrap q-pa-md">
                  <div class="column">
                    <div class="text-h6 q-mb-md">Settings</div>
                    <q-btn label="Use Mobile Data"/>
                    <q-btn label="Bluetooth"/>
                  </div>

                  <q-separator vertical inset class="q-mx-lg"/>

                  <div class="column items-center">
                    <div class="q-mt-md q-mb-xs">John Doe</div>
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
        <div class="q-pa-md" v-if="taskId !== 0">
          <task-edit v-if="taskId !== 0" :taskId="taskId"/>
          <q-btn @click="taskId = 0"></q-btn>
        </div>
        <div class="col col-md-6 col-sm-12">
          <q-separator spaced/>
          <!--  任务列表    -->
          <q-list dense class="q-pa-xs q-gutter-xs">
            <div v-for="(task) in tasks" :key="task.id" class="row list-task">
              <div class="col-8" @click="taskId = task.id">
                <div class="taskStr" :class="{ 'done': task.status === 9}"
                     style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
                  {{ task.name }}
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
                      <q-btn size="12px" flat dense round icon="more_vert"/>
                    </template>
                  </router-link>
                </div>
              </div>
            </div>
          </q-list>
        </div>
      </div>
      <div class="col-4">
        <!--右弹框-->
      </div>
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
  </div>
</template>

<script>
import {saveTask} from 'src/api/task'
import client, {doPost} from 'src/utils/axios'
import {taskList} from 'src/api/url'
import {reactive, ref, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";
import {useQuasar} from "quasar";
import {useRouter} from "vue-router";
import DateTimePicker from "../../components/form/DateTimePicker";
import TaskModel from "src/classes/task";
import TaskEdit from "pages/task/TaskEdit";
import axios from "axios";

export default {
  name: 'AllTask',
  components: {TaskEdit, DateTimePicker},
  setup: function () {
    const $q = useQuasar()
    const router = useRouter();
    const timeDialogFlag = ref(false)

    const addTaskFlag = ref(false)
    const resetTaskId = () => {
      state.taskId = 0
    }

    const state = reactive({
      taskId: 0,
      name: '',
      newTask: new TaskModel(),
      setTimeForm: {
        dueTime: null,
        id: null
      },
      searchForm: {
        name: null
      },
      filter: '',
      tasks: [new TaskModel()]
    });


    console.log(state.newTask)


    onMounted(() => {
      // 1. 加载展示列表数据
      list()
    })

    const saveAnki =() =>  {
      axios.post("http://localhost:8765", {}).then(res => {
        debugger;
        console.log(res)
      })
    }


    const list = () => {
      const queryRequest = {
        size: 50,
        current: 1
      }
      queryRequest.name = state.searchForm.name

      doPost(taskList, queryRequest).then(res => {
        if (res.data) {
          state.tasks = res.data.records
        }
      })
    }

    const saveTaskName = () => {
      const newTask = state.newTask
      // 缺少校验
      saveTask(newTask).then(res => {
        console.log(res)
        $q.notify({
          message: "新增成功",
          type: "positive"
        })
        addTaskFlag.value = false
        state.newTask = new TaskModel()
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
      saveTaskName,
      list,
      doneTask,
      addTaskFlag,
      setEndTime,
      deleteTask,
      edit,
      resetTaskId,
      saveAnki,
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
