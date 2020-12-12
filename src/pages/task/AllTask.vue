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
          <q-btn
            name="add"
            @click.native="saveTitle"
          />
          <q-icon
            name="add"
            @click.native="saveTitle"
          />
        </template>
      </q-input>
    </div>
    <div class="q-gutter-md">
      <q-form>
        <q-field
          label="title"
          style="max-width: 500px"
        >
          <q-input
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
      </q-form>
    </div>
    <div class="q-pa-md q-gutter-md">
      <q-list>
        <q-item
          clickable
          v-ripple
          v-for="(task) in data"
          :key="task.id"
        >
          <q-item-section>
            <q-item-label>
              <q-checkbox :value="task.status === 2" />
            </q-item-label>
          </q-item-section>
          <q-item-section>
            <q-item-label>{{ task.endTime }}</q-item-label>
            <q-item-label>{{ task.title }}</q-item-label>
          </q-item-section>

          <q-item-section
            side
            top
          >
            <q-item-label caption>
              {{ task.dueTime }}
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
                icon="date"
                @click="setEndTime(task)"
              />
              <q-btn
                class="gt-xs"
                size="12px"
                flat
                dense
                round
                icon="done"
              />
              <q-btn
                size="12px"
                flat
                dense
                round
                icon="more_vert"
              />
            </div>
          </q-item-section>
        </q-item>
      </q-list>
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
      options: [
        {
          id: 1,
          desc: 'first'
        }
      ],
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
      pagination: {
        sortBy: 'desc',
        descending: false,
        page: 1,
        rowsPerPage: 10,
        rowsNumber: 10
      },
      selected: [],
      columns: [
        {
          // 唯一的ID
          // 标识列
          // （由pagination.sortBy使用，“body-cell-[name]”插槽，...）
          name: 'id',

          // 头部标签
          label: 'id',

          // 行对象属性以确定此列的值
          field: 'id',
          // 或者field: row => row.some.nested.prop

          // （可选）如果我们使用可见列，这个列将始终可见
          required: true,

          // （可选）对齐
          align: 'left',

          // （可选）告诉QTable你想要这个列可排序
          // sortable: true,

          // （可选）比较功能，如果你有
          //  一些自定义数据或想要一个特定的方式来比较两行
          sort: (a, b, rowA, rowB) => parseInt(a, 10) - parseInt(b, 10),
          // 函数返回值：
          // *小于0，然后将a排序为低于b的索引，即首先出现
          // *为0，则相对于彼此保持a和b不变，但相对于所有不同的元素进行排序
          // *大于0，则将b排序为低于a的索引，即b先到达

          // （可选）您可以使用函数格式化数据
          // format: (val, row) => `${val}%`,
          // 另一个格式示例：
          // format: val => `${val}`,
          //   ? /* 选中Unicode复选标记 */ "\u2611"
          //   : /* 未选中Unicode复选标记 */ "\u2610",

          // body td:
          style: 'width: 20px'
          // classes: 'my-special-class',

          // (v1.3.0+) header th:
          // headerStyle: 'width: 500px',
          // headerClasses: 'my-special-class'
        },
        {
          name: 'title',
          label: 'title',
          field: 'title',
          align: 'left',
          style: 'width:200px'
        },
        {
          name: 'context',
          label: 'context',
          field: 'context',
          align: 'left'
        },
        {
          name: 'status',
          label: '状态',
          field: 'status',
          align: 'left',
          style: 'width: 20px'
        },
        {
          name: 'dueTime',
          label: '截止时间（通知）',
          field: 'dueTime',
          align: 'left',
          style: 'width:100px'
        },
        {
          name: 'startTime',
          label: '开始时间',
          field: 'startTime',
          align: 'left',
          style: 'width:100px'
        },
        {
          name: 'endTime',
          label: '结束时间',
          field: 'endTime',
          align: 'left',
          style: 'width:100px'
        },
        {
          name: 'options',
          label: '操作',
          field: 'options',
          align: 'center',
          style: 'width: 100px'
        }
      ],
      data: []
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
    saveTaskDetail () {
      saveTask(this.setTimeForm.task)
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
        this.data = res.data.records
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

</style>
