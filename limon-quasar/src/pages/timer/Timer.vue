<template>
  <div class="row justify-center">
    <q-card class="q-card q-gutter-md q-pa-md col-md-4 col-sm-12">
      <q-card-section>
        <div class="text-h3">番茄工作法</div>
      </q-card-section>
      <div v-if="!isTiming">开始番茄 UP UP</div>
      <q-card-section v-else>
        任务：
        <q-badge>{{ taskName }}</q-badge>
        正在开始
        开始时间为：
        <q-chip square color="blue" text-color="white" icon="alarm" :label="startTimeStr"/>
        结束实际为：
        <q-chip square color="red" text-color="white" icon="alarm" :label="stopTimeStr"/>
      </q-card-section>

      <q-input label="工作名称" type="text" :readonly="isTiming" v-model="taskName"/>
      <q-input label="工作时长(分钟)" type="text" v-model="workTime"/>
      <q-input label="休息时长(分钟)" type="text" v-model="restTime"/>

      <q-card-section>
        <div class="q-gutter-md">
          <q-btn color="blue" @click="startWorkButton" v-if="!isTiming">开始</q-btn>
          <q-btn color="red" @click="stopButton">终止</q-btn>
        </div>

      </q-card-section>
      <q-separator/>
      <div class="text-h4">番茄进度</div>
      <q-linear-progress stripe rounded size="20px" :value="progress" :buffer="buffer" color="warning" class="q-mt-sm"
                         style="max-width: 400px">
        <div class="absolute-full flex flex-center">
          <q-badge color="white" text-color="red" :label="progressLabel" v-if="progress !== 0"/>
        </div>
      </q-linear-progress>

      <q-separator/>

      <div class="text-h4">番茄记录</div>
      <div v-for="tomato in tomatoList" :key="tomato.startTime" class="row">
        <div>{{ tomato.taskName }}</div>
        <div>{{ tomato.startTimeStr }}</div>
        <div>{{ tomato.stopTimeStr }}</div>
      </div>
    </q-card>
  </div>
</template>

<script>
import {computed, reactive, ref, toRefs} from "@vue/reactivity";
import {useQuasar} from "quasar";
import {onBeforeUnmount} from "@vue/runtime-core";
import {convertMinToMs, getFormatDateTime, getTimestamp} from "src/utils/dateUtil";

export default {
  name: "Timer",
  setup() {
    const $q = useQuasar();
    const state = reactive({
      taskName: '',
      workTime: 25,
      restTime: 5,
      startTime: null,
      stopTime: null,
      planTime: null,
      tomatoList:[]
    })
    let interval = undefined;

    const startTimeStr = computed(() =>getFormatDateTime(state.startTime))
    const stopTimeStr = computed(() => getFormatDateTime(state.stopTime))
    const progress = ref(0.00)
    const isTiming = ref(false)

    const startWorkButton = () => {
      startTiming(convertMinToMs(state.workTime))
    }



    const stopButton = () => {
      isTiming.value = false
      state.startTime = null
      state.stopTime = null
      state.taskName = ''
      progress.value = 0.00
      $q.notify({
        message: "停止番茄时成功"
      })
    }

    const planStart = function (t) {
      state.planTime = t;
      if (isNaN(state.planTime) || state.planTime <= 0) {
        $q.notify({
          message: "请输入正确的分钟数"
        })
      } else {
        state.startTime = +new Date();
        state.stopTime = state.startTime + state.planTime;
        $q.notify({
          type: "info",
          message: "开始番茄时"
        })
      }
    }

    onBeforeUnmount(() => {
      clearInterval(interval)
    })

    function updateProgress() {
      interval = setInterval(() => {
        const nowDate = getTimestamp(new Date());
        const timeBit = state.stopTime - nowDate
        if (!isNaN(timeBit) && timeBit >= 0) {
          progress.value = (state.workTime * 60 * 1000 - timeBit) / (state.workTime * 60 * 1000)
        } else {
          console.log("已经完成番茄时  清理 interval")
          clearInterval(interval)
        }
      }, 1000)
    }

    const startTiming = function (t) {
      if (!isTiming.value) {
        planStart(t);
        updateProgress();
        isTiming.value = true;

        const currentTask = {
          taskName: state.taskName,
          startTime: state.startTime,
          startTimeStr: getFormatDateTime(state.startTime),
          stopTime: state.stopTime,
          stopTimeStr: getFormatDateTime(state.stopTime)
        }
        addTomatoList(currentTask);
        // saveTaskList(tomatoData);
        // showCurrentTask();
      }
    }

    const addTomatoList =  (tomato) => {
      state.tomatoList.push(tomato);
    }


    return {
      ...toRefs(state),
      isTiming,
      startWorkButton,
      startTimeStr,
      stopTimeStr,
      stopButton,
      progress,
      buffer: ref(1 - progress.value),
      progressLabel: computed(() => (progress.value * 100).toFixed(2) + '%')
    }
  }
}
</script>

<style scoped>
</style>
