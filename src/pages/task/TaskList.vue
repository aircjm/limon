<template>
  <q-page>
    <q-btn-group>
      <q-btn
        label="add"
        @click="openDialog = true"
      />
      <q-btn
        label="search"
        name="search"
        @click="list"
      />
    </q-btn-group>
    <q-dialog v-model="openDialog">
      <TaskEdit />
    </q-dialog>
    <q-table
      :columns="table.columns"
      :data="table.tableData"
      title="Record List"
      row-key="id"
      :loading="loading"
      :pagination.sync="pagination"
      selection="single"
      :selected.sync="table.selected"
    >
      <template v-slot:body-cell-options="props">
        <q-td :props="props">
          <q-btn
            label="detail"
            @click="openDetail(props.row.id)"
          />
          <q-btn label="update" />
        </q-td>
      </template>
    </q-table>
  </q-page>
</template>

<script>
import TaskEdit from 'pages/task/TaskEdit'
import { getTaskList } from 'src/api/task'
export default {
  name: 'TaskList',
  components: { TaskEdit },
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

      table: {
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
            sortable: true,

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
          { name: 'title', label: 'title', field: 'title', sortable: true },
          { name: 'context', label: 'context', field: 'context' },
          { name: 'noticeDate', label: 'noticeDate', field: 'noticeDate' },
          { name: 'options', label: '操作', field: 'options', style: 'width: 100px' }
        ],
        tableData: [
          {
            id: 1,
            title: '123',
            context: '内容',
            noticeDate: null
          }, {
            id: 2,
            title: '345',
            context: '内容1213',
            noticeDate: null
          }
        ]
      },
      filter: '',
      loading: false,
      pagination: {
        sortBy: 'desc',
        descending: false,
        page: 1,
        rowsPerPage: 3,
        rowsNumber: 10
      }
    }
  },

  methods: {
    openDetail (id) {
      console.log(id)
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
        this.table.tableData = res.data.records
      })
      // ...and turn of loading indicator
      this.loading = false
    }
  }
}
</script>

<style scoped>

</style>
