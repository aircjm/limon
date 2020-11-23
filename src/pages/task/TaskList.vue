<template>
  <q-page class="column">
    <div
      style="height: 100px"
    >
      <q-btn
        label="add"
        @click="openDialog = true"
      />
    </div>
    <q-dialog v-model="openDialog">
      <q-card style="width: 800px;max-width: 80%">
        <q-card-section>
          <div class="text-h6">
            Add Record
          </div>
        </q-card-section>
        <q-separator />
        <q-card-section>
          <q-form
            ref="myForm"
            @reset="resetForm"
            class="q-gutter-y-md column"
            @submit="onSubmitForm"
          >
            <q-input
              v-model="form.title"
              label="title"
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
            <q-input
              label="context"
              v-model="form.context"
              filled
              type="textarea"
            />

            <date-time-picker
              label="截止时间"
              :time.sync="form.dueTime"
            />
            <div>
              <q-btn
                label="Submit"
                type="submit"
                color="primary"
              />
              <q-btn
                label="Cancel"
                type="reset"
                color="primary"
                flat
              />
            </div>
          </q-form>
        </q-card-section>
      </q-card>
    </q-dialog>
    <div>
      <q-table
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

        <template v-slot:body-cell-options="props">
          <q-td
            :props="props"
            class="q-gutter-xs action"
          >
            <a
              class="text-primary"
              @click="openDetail(props.row.id)"
            >详情</a>
            <a class="text-primary">标记</a>
          </q-td>
        </template>
      </q-table>
    </div>
  </q-page>
</template>

<script>
import { getTaskList, saveTask } from 'src/api/task'
import TagSelect from 'pages/tag/TagSelect'
import DateTimePicker from 'components/form/DateTimePicker'
export default {
  name: 'TaskList',
  components: { TagSelect, DateTimePicker },
  data () {
    return {
      title: '',
      recordType: null,
      date: null,
      openDialog: false,
      options: [
        {
          id: 1,
          desc: 'first'
        }
      ],
      tags: [
        1
      ],
      form: {
        id: null,
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
          // format: val => val
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
        { name: 'context', label: 'context', field: 'context', align: 'left' },
        { name: 'status', label: '状态', field: 'status', align: 'left', style: 'width: 20px' },
        { name: 'dueTime', label: '截止时间（通知）', field: 'dueTime', align: 'left', style: 'width:100px' },
        { name: 'startTime', label: '开始时间', field: 'startTime', align: 'left', style: 'width:100px' },
        { name: 'endTime', label: '结束时间', field: 'endTime', align: 'left', style: 'width:100px' },
        { name: 'options', label: '操作', field: 'options', align: 'center', style: 'width: 100px' }
      ],
      data: [
      ]
    }
  },
  mounted () {
    this.list()
  },
  methods: {
    openDetail (id) {
      console.log(id)
    },
    saveTask () {
      saveTask({ title: this.title }).then(res => {
        this.title = ''
      })
    },
    list () {
      const { page, rowsPerPage } = this.pagination
      // const filter = this.filter
      // get all rows if "All" (0) is selected
      const fetchCount = rowsPerPage === 0 ? this.pagination.rowsNumber : rowsPerPage

      // calculate starting row of data

      // fetch data from "server"
      getTaskList({
        size: fetchCount,
        current: page
      }).then(res => {
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
      // this.form.title = ''
      // this.form.dueTime = null
      // this.form.startTime = null
      // this.form.endTime = null
      this.openDialog = false
    }

  }
}
</script>

<style scoped>

</style>
