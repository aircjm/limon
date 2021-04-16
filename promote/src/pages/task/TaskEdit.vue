<template>
  <q-page class="row">
    <div class="col-md-9 col-sm-12">
      <q-card>
        <q-card-section>
          <div class="text-h7" v-if="form.id">
            Edit Record {{ form.id }}
          </div>
          <div class="text-h7" v-else>
            Add Record {{ form.id }}
          </div>
        </q-card-section>
        <q-separator/>
        <q-card-section>
          <!--<q-form class="q-gutter-y-md column">
            <q-input
              v-model="form.title"
              label="title"
              :rules="[ val => val && val.length > 0 && val.length < 500 || 'Please type something']"
              @keyup.enter="onSubmit"
            />
            <div class="q-gutter-sm">
              <q-badge
                v-for="tagDetail in tag.selectList"
                :key="tagDetail.id"
                :label="tagDetail.label"
              />
            </div>
            <div class="row q-gutter-sm">
              <div style="width: 330px">
                <date-time-picker
                  label="通知时间"
                  :time.sync="form.endTime"
                />
              </div>
              <div style="width: 330px">
                <date-time-picker
                  label="开始时间"
                  :time.sync="form.startTime"
                />
              </div>
              <div style="width: 330px">
                <date-time-picker
                  label="截止时间"
                  :time.sync="form.dueTime"
                />
              </div>
            </div>
            <div>
              <div>
                <q-select
                  filled
                  v-model="tag.selectList"
                  use-input
                  multiple
                  style="width: 330px"
                  input-debounce="0"
                  :options="tag.tagList"
                  label="标签"
                >
                  <template v-slot:append>
                    <q-btn
                      round
                      dense
                      flat
                      icon="add"
                      @click="tag.editorFlag == true"
                    />
                  </template>
                </q-select>
              </div>
            </div>
            <MarkdownEditor
              label="context"
              v-if="this.editorFlag"
              :context.sync="form.taskDesc"
            />
            <div>
              <q-btn
                icon="save"
                label="Submit"
                type="submit"
                color="primary"
                @click="onSubmit"
              />
              <q-btn
                class="mdi-book-cancel"
                label="Cancel"
                @click="goBack()"
                color="primary"
                flat
                v-close-popup
              />
            </div>
          </q-form>-->
        </q-card-section>
      </q-card>
    </div>
    <!-- <q-dialog
       v-model="tag.editorFlag"
       style="min-width: 300px"
     >
       <q-card>
         <q-card-section>
           <div
             v-for="(selectTag) in tag.selectList"
             :key="selectTag.id"
           >
             <q-badge
               outline
               :color="selectTag.color ? selectTag.color: 'red'"
               :label="selectTag.label"
             />
           </div>
           <q-list>
             <q-item
               v-for="(tagDetail) in tag.tagList"
               :key="tagDetail.id"
             >
               <q-item-label @click="addTag(tagDetail)">
                 {{ tagDetail.label }}
               </q-item-label>
             </q-item>
           </q-list>
         </q-card-section>
         <q-card-actions>
           <q-btn>submit</q-btn>
         </q-card-actions>
       </q-card>
     </q-dialog>-->
  </q-page>
</template>

<script>
import {getTaskDetail} from 'src/api/task'
import {Loading} from 'quasar'
import {defineComponent} from "vue";
import {reactive, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";


export default defineComponent(
  {
    name: 'TaskEdit',
    setup() {
      const data = reactive({
        id: null,
        title: '',
        recordType: null,
        date: null,
        openDialog: false,
        form: {},
        filter: '',
        loading: false,
        editorFlag: false,
        tag: {
          editorFlag: false,
          filter: null,
          tagList: [{id: 1, label: '测试'}, {id: 2, label: 'test'}],
          selectList: []
        }
      });

      // 初始化
      onMounted(() => {
        init()
      })
      const init = async () => {
        Loading.show();
        const id = this.$route.params.id
        if (id) {
          data.id = id
          Loading.show()
          await getTaskDetail(id).then(res => {
            data.form = res.data
            Loading.hide()
            data.editorFlag = true
          })
        } else {
          data.editorFlag = true
        }
      }

      return toRefs(data)
    },

    // watch: {},
    // created() {
    //   const id = this.$route.query.id
    //   if (id) {
    //     this.form.id = id
    //     this.$q.loading.show()
    //     getTaskDetail(id).then(res => {
    //       this.form = res.data
    //       this.$q.loading.hide()
    //       this.editorFlag = true
    //     })
    //   } else {
    //     this.editorFlag = true
    //   }
    // },
    // methods: {
    //   onSubmit() {
    //     saveTask(this.form).then(res => {
    //       if (res.code === 200) {
    //         this.$router.push('/task')
    //       }
    //     })
    //   },
    //   addTag(tagDetail) {
    //     console.log(this.tag.selectList.indexOf(tagDetail))
    //     if (this.tag.selectList.indexOf(tagDetail) > -1) {
    //     } else {
    //       this.tag.selectList.push(tagDetail)
    //     }
    //   },
    //   autoSave() {
    //     saveTask(this.form).then(res => {
    //       if (res.code === 200) {
    //         Notify.create({
    //           message: '自动更新成功'
    //         })
    //       }
    //     })
    //   },
    //   resetForm() {
    //     this.form.title = ''
    //     this.form.dueTime = null
    //     this.form.startTime = null
    //     this.form.endTime = null
    //     this.openDialog = false
    //   },
    //   goBack() {
    //     this.$router.back()
    //   }
    // }
  }
)
</script>

<style scoped>

</style>
