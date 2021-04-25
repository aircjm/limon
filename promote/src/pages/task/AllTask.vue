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
      <q-field style="max-width: 400px">
        <q-input
          label="title"
          v-model="searchForm.title"
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
        <div class="col col-md-4 col-sm-12">
          <q-list
            dense
            bordered
            class="rounded-borders"
          >
            <div
              v-for="(task) in tasks"
              :key="task.id"
              class="row q-pa-sm q-gutter-sm"
            >
              <div class="col-7 taskStr" :class="{ 'done': task.status == 9}" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
                {{ task.title }}
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
  import {doPost} from 'src/utils/axios'
  import {taskList} from 'src/api/url'
  import {reactive, toRefs} from "@vue/reactivity";
  import {onMounted} from "@vue/runtime-core";
  import {Loading} from "quasar";
  import {useRoute} from "vue-router";

  export default {
    name: 'AllTask',
    components: {DateTimePicker},
    setup() {

      const route = useRoute();

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
          title: null
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
            state.title = ''
            this.list()
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
        const path = '/task/edit?id==' + id
        route.push(path)
      }

      const deleteTask = (task) => {
        console.log('删除' + task.id)
        // 删除任务
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
      const onSubmitForm = (e) => {
        // this.$refs.myForm.validate().then(success => {
        //   if (success) {
        //     // 是的，模型是正确的
        //   } else {
        //     // 哦，不，用户至少
        //     // 填写了一个无效值
        //   }
        // })
        //
        // // 重置验证：
        // this.$refs.myForm.resetValidation()
        saveTask(state.form)
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
</style>
