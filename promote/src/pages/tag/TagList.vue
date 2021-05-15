<template>
  <div>
    <q-btn
      label="新增标签"
      @click="addFlag = !addFlag"
    />
    <q-dialog
      class="q-gutter-md"
      v-model="addFlag"
    >
      <q-card style="min-width: 400px">
        <div class="q-pa-md">
          <div class="text-h6">
            添加标签
          </div>
          <q-form>
            <q-input filled v-model="color" label="标签颜色">
              <template v-slot:append>
                <q-icon name="colorize" class="cursor-pointer">
                  <q-popup-proxy transition-show="scale" transition-hide="scale">
                    <q-color v-model="color"/>
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
  </div>
</template>

<script>
import {saveTag} from 'src/api/tag'
import {Notify} from 'quasar'
import {reactive, ref, toRefs} from "@vue/reactivity";

export default {
  name: 'TagList',
  setup () {
    const inputRef = ref(null)
    const       addFlag = ref(false)
    const state = reactive({
      name: null,
      color: null
    })

    const submit = ()  => {
      console.log('开始提交')
      saveTag({
        name: state.name,
        color: state.color
      }).then(res => {
        debugger
        console.log(res)
        if (res.code === 200) {
          Notify.create({
            position: 'top',
            type: 'positive',
            message: '操作成功'
          })
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
    }

    return {
      inputRef,
      addFlag,
      ...toRefs(state),
      submit
    }
  }
}
</script>
<style scoped>

</style>
