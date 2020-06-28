<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="数据状态" clearable size="small">
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
    </el-row>


    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="名称" align="center" width="200" autocapitalize="on" prop="cardTitle"/>
      <el-table-column label="链接" align="center" prop="url" type="url"/>
      <el-table-column label="状态" align="center" prop="status"/>
      <el-table-column label="trello更新时间" align="center" prop="trelloUpdateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="setAnki(scope.row)"
            v-hasPermi="['system:dict:edit']"
          >设置anki
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dict:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
  import {listData, setAnki, exportCard} from "@/api/card/card";

  export default {
    name: "Card",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 总条数
        total: 0,
        // 字典表格数据
        dataList: [],
        // 默认字典类型
        defaultDictType: "",
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据字典
        statusOptions: [],
        // 类型数据字典
        typeOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          dictName: undefined,
          dictType: undefined,
          status: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          dictLabel: [
            {required: true, message: "数据标签不能为空", trigger: "blur"}
          ],
          dictValue: [
            {required: true, message: "数据键值不能为空", trigger: "blur"}
          ],
          dictSort: [
            {required: true, message: "数据顺序不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
      // const dictId = this.$route.params && this.$route.params.dictId;
      // this.getType(dictId);
      // this.getTypeList();
      // this.getDicts("sys_normal_disable").then(response => {
      //   this.statusOptions = response.data;
      // });
    },
    methods: {

      /** 查询字典数据列表 */
      getList() {
        this.loading = true;
        listData(this.queryParams).then(response => {
          let data = response.data;
          this.dataList = data.records;
          this.total = data.total;
          this.loading = false;
        });
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },

      setAnki(row) {
        const a = {}
        a.cardId = row.cardId
        setAnki(a);
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有角色数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCard(queryParams);
        }).then(response => {
          this.download(response.data.msg);
        }).catch(function() {});
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.dictId)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
    }
  };
</script>
