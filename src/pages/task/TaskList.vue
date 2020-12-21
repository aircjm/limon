<template>
  <q-page class="column">
    <div
      style="height: 65px"
      class="q-pa-sm q-ma-sm"
    >
      <q-input
        v-model="title"
        style="width: 300px"
        @keyup.enter="saveTitle"
        placeholder="Please Input Task"
      >
        <template v-slot:append>
          <q-icon
            name="add"
            @click.native="saveTitle"
          />
        </template>
      </q-input>
    </div>
    <div class="q-pa-md">
      <q-btn
        icon="add"
        label="新增"
        to="/task/edit"
      />
    </div>

    <div>
      <q-table
        @request="list"
        :columns="columns"
        :data="data"
        title="Record List"
        row-key="id"
        :loading="loading"
        :pagination.sync="pagination"
        selection="single"
        :selected.sync="selected"
      >
        <template v-slot:top-left>
          <div class="row  q-gutter-x-sm">
            <div class="col">
              <q-input
                outlined
                dense
                no-error-icon
                v-model="searchForm.title"
                placeholder="输入标题"
              />
            </div>
            <div class="col">
              <q-btn
                label="查询"
                color="primary"
                @click="list"
              />
            </div>
          </div>
        </template>
        <template v-slot:body-cell-dueTime="props">
          <q-td
            :props="props"
            class="q-gutter-xs action"
          >
            <q-chip
              class="gt-xs"
              size="12px"
              text-color="red"
              icon="event"
              clickable
              @click="setEndTime(props.row)"
            >
              {{ props.row.dueTime }}
            </q-chip>
          </q-td>
        </template>
        <template v-slot:body-cell-status="props">
          <q-td
            :props="props"
            class="q-gutter-xs action"
          >
            <q-chip
              class="gt-xs"
              size="12px"
              text-color="red"
              clickable
            >
              {{ getStatus(props.row.status) }}
            </q-chip>
          </q-td>
        </template>
        <template v-slot:body-cell-options="props">
          <q-td
            :props="props"
            class="q-gutter-xs action"
          >
            <router-link
              style="text-decoration: none;"
              :to="`/task/edit?id=${props.row.id}`"
              class="text-primary"
            >
              编辑
            </router-link>
            <q-btn
              class="text-primary"
              v-if="props.row.status == 0"
              @click="doneTask(props.row)"
            >
              已完成
            </q-btn>
          </q-td>
        </template>
      </q-table>
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
  </q-page>
</template>

<script>
import { getTaskList, saveTask } from 'src/api/task'
import DateTimePicker from 'components/form/DateTimePicker'

export default {
  name: 'TaskList',
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
        rowsPerPage: 50,
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
        { name: 'title', label: 'title', field: 'title', align: 'left', style: 'width:200px' },
        { name: 'context', label: 'context', field: 'taskDesc', align: 'left', style: 'width:100px' },
        { name: 'status', label: '状态', field: 'status', align: 'center', style: 'width: 20px' },
        { name: 'dueTime', label: '截止时间（通知）', field: 'dueTime', align: 'left', style: 'width:100px' },
        { name: 'startTime', label: '开始时间', field: 'startTime', align: 'left', style: 'width:100px' },
        { name: 'endTime', label: '结束时间', field: 'endTime', align: 'left', style: 'width:100px' },
        { name: 'options', label: '操作', field: 'options', align: 'center', style: 'width: 100px' }
      ],
      data: [
      ],
      setTimeForm: {
        loading: false,
        task: {}
      }
    }
  },
  mounted () {
    this.list()
  },
  methods: {
    doneTask (task) {
      task.status = 1
      console.log(task)
      saveTask(task).then(res => {
        this.list()
        this.$q.dialog({
          title: '提交成功',
          message: '是否回退处理',
          position: 'bottom'
        }).onOk(() => {
          task.status = 0
          saveTask(task)
          this.list()
        })
      })
    },
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
    setEndTime (task) {
      this.setTimeForm = { loading: true }
      this.setTimeForm.task = task
    },
    getStatus (status) {
      if (status === 0) {
        return '待处理'
      } else {
        return '已完成'
      }
    },
    async saveTaskDetail () {
      this.$q.loading.show()
      await saveTask(this.setTimeForm.task)
      this.$q.loading.hide()
      this.setTimeForm.loading = false
    },
    list () {
      const { page, rowsPerPage } = this.pagination
      // const filter = this.filter
      // get all rows if "All" (0) is selected
      const fetchCount = rowsPerPage === 0 ? this.pagination.rowsNumber : rowsPerPage

      const queryRequest = {
        size: fetchCount,
        current: page
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
