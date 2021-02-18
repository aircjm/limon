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
      <q-card style="min-width: 300px">
        <q-card-section>
          <div class="text-h6">
            添加标签
          </div>
          <q-form>
            <q-input
              label="标签名称"
              v-model="name"
              :rules="[
                val => val.length < 50 || '最大50个字符',
                val => val.length > 0 || '不能为空'
              ]"
            />
            <q-input
              filled
              label="标签颜色"
              v-model="color"
            >
              <!--              todo 标签颜色-->
              <template v-slot:append>
                <q-icon
                  name="colorize"
                  class="cursor-pointer"
                >
                  <q-popup-proxy
                    transition-show="scale"
                    transition-hide="scale"
                  >
                    <q-color
                      v-model="color"
                    />
                  </q-popup-proxy>
                </q-icon>
              </template>
            </q-input>
          </q-form>
        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
          <q-btn
            flat
            color="primary"
            @click="submit()"
          >
            提交
          </q-btn>
          <q-btn flat>
            取消
          </q-btn>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script>
export default {
  name: 'TagList',
  data () {
    return {
      addFlag: false,
      name: null,
      color: null
    }
  },
  methods: {
    submit () {
      console.log('开始提交')
    }
  }
}
</script>
<style scoped>

</style>
