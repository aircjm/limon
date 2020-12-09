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
    <div class="q-pa-md q-gutter-md">
      <q-list>
        <q-item
          clickable
          v-ripple
          v-for="(task) in data"
          :key="task"
        >
          <q-item-section>
            <q-item-label><q-checkbox :value="task.status === 2" /> </q-item-label>
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
              5 min ago
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

    <q-dialog v-model="loading" />
  </div>
</template>

<script>
import { getTaskList, saveTask } from 'src/api/task'

export default {
  name: 'TaskList',
  components: {},
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
