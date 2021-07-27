<template>
  <q-page class="q-gutter">
    <q-card class="q-gutter">
      <q-card-section>
        <div class="text-h7" v-if="id">
          Edit Record {{ id }}
        </div>
        <div class="text-h7" v-else>
          Add Record {{ id }}
        </div>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <q-form class="q-gutter-y-md">
          <q-input
            v-model="form.title"
            label="title"
            :rules="[ val => val && val.length > 0 && val.length < 500 || 'Please type something']"
          />
          <div class="q-gutter-md">
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
                @filter="filterTag"
                @filter-abort="filterTagAbort"
                label="标签"
              >
                <template v-slot:append>
                  <q-btn
                    round
                    dense
                    flat
                    icon="add"
                    @click="toAddTag"
                  />
                </template>
              </q-select>
            </div>
          </div>
        </q-form>
      </q-card-section>
      <q-card-section>
        <div>
<!--          <MarkdownEditor-->
<!--            label="context"-->
<!--            v-if="editorFlag"-->
<!--            :context.sync="form.taskDesc"-->
<!--          />-->
          <TipTapEditor/>
        </div>
      </q-card-section>
      <q-card-section>
        <div class="q-gutter-md">
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
            color="primary"
            flat
            v-close-popup
          />
        </div>
      </q-card-section>
    </q-card>
  </q-page>
  <q-dialog
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
  </q-dialog>
</template>

<script>
import {getTaskDetail, saveTask} from 'src/api/task'
import {Loading, useQuasar} from 'quasar'
import {defineComponent} from "vue";
import {reactive, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";
import {useRoute, useRouter} from "vue-router";
import DateTimePicker from "../../components/form/DateTimePicker";
import MarkdownEditor from "../../components/editor/MarkdownEditor";
import TipTapEditor from "../../components/tipTapEditor/TipTapEditor";


export default defineComponent(
  {
    name: 'TaskEdit',
    components: {
      TipTapEditor,
      MarkdownEditor,
      DateTimePicker
    },
    props: {
      taskId : {
        type: Number
      }
    },
    setup(props) {
      const {taskId} = toRefs(props)
      debugger
      if (taskId) {
        console.log("taskId is " + taskId.value)
      }
      let route = useRoute();
      const router = useRouter();
      const $q = useQuasar();

      const state = reactive({
        id: null,
        form: {
          title: '',
          recordType: null,
          openDialog: false,
          startTime:null,
          endTime:null,
          dueTime:null,
          contextJson: ''
        },
        recordType: null,
        date: null,
        openDialog: false,

        startTime:null,
        endTime:null,
        dueTime:null,
        filter: '',
        loading: false,
        editorFlag: false,
        tag: {
          editorFlag: false,
          filter: null,
          tagList: [],
          selectList: []
        }
      });

      // 初始化
      onMounted(() => {
        init()
      })
      const init = async () => {
        Loading.show();
        const id = route.query.id
        if (id) {
          state.id = id
          await getTaskDetail(id).then(res => {
            state.form = res.data
            state.editorFlag = true
          })
          state.editorFlag = true
        }
        Loading.hide();
      }


      // 同步添加标签
      const addTag = async (tagDetail) => {
        console.log(state.tag.selectList.indexOf(tagDetail))
        if (state.tag.selectList.indexOf(tagDetail) > -1) {
        } else {
          state.tag.selectList.push(tagDetail)
        }
      }

      // 跳转到添加标签
      const toAddTag = () => {
        router.push('/tag')
      }

      const onSubmit = () => {
        $q.loading.show()
        saveTask(state.form).then(res => {
          if (res.code === 200) {
            router.push('/alltask')
          }
          $q.loading.hide()
        })
      }

      const autoSave = () => {
        saveTask(state.form).then(res => {
          if (res.code === 200) {
            $q.notify({
              message: '自动更新成功'
            })
          }
        })
      }

      const stringOptions = [
        'Google', 'Facebook', 'Twitter', 'Apple', 'Oracle'
      ]
      const filterTagAbort = () => {
        console.log('delayed filter aborted')
      }

      const filterTag = (val, update, abort) => {
        console.log(state.tag.tagList)
        if (state.tag.tagList.value !== null) {
          // already loaded
          update()
          return
        }

        setTimeout(() => {
          update(() => {
            state.tag.tagList.value = stringOptions
          })
        }, 2000)
      }

      return {
        ...toRefs(state), toAddTag, filterTag, filterTagAbort,
        addTag, onSubmit, autoSave,
      }
    }
  }
)
</script>

<style scoped>

</style>
