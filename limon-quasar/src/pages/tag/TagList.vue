<template>
  <div class="q-gutter-md">
    <q-dialog
      v-model="addFlag"
    >
      <q-card style="min-width: 400px">
        <div class="q-pa-md">
          <div class="text-h6">
            添加标签
          </div>
          <q-form>
            <q-input v-model="color" label="标签颜色" @blur="changeColor">
              <template v-slot:before>
                <q-btn :style="{ background : classColor}">
                </q-btn>
              </template>
              <template v-slot:append>
                <q-icon name="colorize" class="cursor-pointer">
                  <q-popup-proxy transition-show="scale" transition-hide="scale" @blur="changeColor">
                    <q-color v-model="color" default-view="palette"/>
                  </q-popup-proxy>
                </q-icon>
              </template>
            </q-input>
            <q-input label="标签名称" v-model="name" ref="inputRef"
                     :rules="[
          val => !!val || '* Required',
          val => val.length < 50 || 'Please use maximum 50 character',
        ]"/>
            <q-space/>
            <q-separator/>
            <q-btn flat color="primary" @click="submit()">提交</q-btn>
            <q-btn flat type="reset" @click="onReset">取消</q-btn>
          </q-form>
        </div>
      </q-card>
    </q-dialog>
    <div class="column q-gutter-md">
      <div class="col-1">
        <div class="col bg-white shadow-4 q-pa-md">
          <div class="row items-center justify-start q-mb-md">
            <q-item class="">
              <q-item-section class="col-3 text-right gt-sm">
                <q-item-label>标签名：</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-input outlined dense clearable v-model="search.name" label="标签名"/>
              </q-item-section>
            </q-item>
            <q-item class="col-xl-2 col-md-3 col-sm-6 col-xs-12">
              <q-item-section class="col-3 text-right gt-sm">
                <q-item-label>状态：</q-item-label>
              </q-item-section>
              <q-item-section class="col">
                <q-input outlined dense clearable v-model="search.name" label="状态"/>
              </q-item-section>
            </q-item>

            <q-item class="col-xl-2 col-md-3 col-sm-6 col-xs-12 q-pr-sm">
              <q-item-label class="col-12 text-right row no-wrap justify-center">
                <q-btn
                  label="查询"
                  no-wrap
                  color="primary"
                  class="q-mr-sm"
                  :loading="search.loading"
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


      <div class="col-1 q-gutter-md">
        <q-btn
          label="新增标签"
          @click="addFlag = !addFlag"
        />
      </div>
      <div>
        <q-table
          title="标签列表"
          :rows="rows"
          :columns="columns"
          row-key="id"
        >
          <template v-slot:body-cell-color="props">
            <q-td :props="props">
              <div>
                <q-btn outline :label="props.value" :style="{color: props.value}"/>
              </div>
            </q-td>
          </template>

          <template v-slot:body-cell-opt="props">
            <q-td :props="props">
              <div>
                <q-btn outline label="编辑" @click="editTag(props)" />
              </div>
            </q-td>
          </template>
        </q-table>
      </div>

    </div>
  </div>
</template>

<script>
import {Notify, useQuasar} from 'quasar'
import {reactive, ref, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";
import client, {doPost} from "src/utils/axios";
import {apiLagList} from "src/api/url";


const columns = [
  {
    name: 'id',
    required: true,
    label: '标签编号',
    align: 'left',
    field: row => row.id,
    format: val => `${val}`,
    sortable: true
  },
  {
    name: 'name',
    required: true,
    label: '标签名称',
    align: 'left',
    field: row => row.name,
    format: val => `${val}`,
    sortable: true
  },  {
    name: 'color',
    required: true,
    label: '标签颜色',
    align: 'left',
    field: row => row.color,
    format: val => `${val}`,
    sortable: true
  },{
  name: 'opt',
    required: false,
    label: 'opt',
    align: 'left'
  },
]


export default {
  name: 'TagList',
  setup () {
    const  $q = useQuasar()
    const inputRef = ref(null)
    const addFlag = ref(false)
    const state = reactive({
      id: null,
      name: null,
      color: null,
      search:{
        name: '',
        loading: false
      },
      rows:[]
    })


    const classColor = ref(null)


    const changeColor = () => {
      console.log(state.color)
      classColor.value = state.color
      console.log(classColor.value)
    }


    onMounted(() => {
      console.log("初始化列表页面")
      list()
    })

    const list = () => {
      state.search.loading = true
      const queryRequest = {
        size: 100,
        current: 1,
        name : state.search.name
      }

      doPost(apiLagList, queryRequest).then(res => {
        state.rows = res.data.records
      })
      state.search.loading = false
      $q.loading.hide()

    }

    const editTag =(props) => {
      addFlag.value = true
      // 后面改成单个查询 保证数据实效性
      state.id = props.row.id
      state.name = props.row.name
      state.color = props.row.color
      classColor.value = props.row.color
    }

    const submit = ()  => {
      console.log('开始提交')
      $q.loading.show()
      client.post("/api/tag/save", {
        id: state.id,
        name: state.name,
        color: state.color
      }).then(res => {
        if (res.code === 200) {
          Notify.create({
            position: 'top',
            type: 'positive',
            message: '操作成功'
          })
          $q.loading.hide()
          addFlag.value = false
          list()
          onReset()
        } else {
          Notify.create({
            position: 'top',
            type: 'negative',
            message: res.msg
          })
        }
      })
    }

    const onReset =() =>  {
      state.name = null
      state.color = null
      addFlag.value = false
    }

    return {
      inputRef,
      addFlag,
      ...toRefs(state),
      submit,
      list,
      onReset,
      changeColor,
      classColor,
      editTag,
      columns
    }
  }
}
</script>
<style scoped>
</style>
