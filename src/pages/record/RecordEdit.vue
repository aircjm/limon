<template>
  <q-card style="width: 500px; max-width: 60vw">
    <q-card-section>
      <div class="text-h6">
        Add Record
      </div>
    </q-card-section>
    <q-separator />
    <q-card-section>
      <q-form
        @reset="resetForm"
        style="max-width: 300px"
        class="q-ml-sm"
        @submit="onSubmitForm"
      >
        <q-input
          v-model="title"
          label="title"
        />
        <q-select
          v-model="recordType"
          :options="options"
          :option-value="opt => Object(opt) === opt && 'id' in opt ? opt.id : null"
          :option-label="opt => Object(opt) === opt && 'desc' in opt ? opt.desc : ''"
          :option-disable="opt => Object(opt) === opt ? opt.inactive === true : true"
          use-input
          new-value-mode="add"
        />
        <div style="max-width: 300px">
          <q-input
            filled
            v-model="noticeDate"
          >
            <template v-slot:prepend>
              <q-icon
                name="today"
                class="cursor-pointer"
              >
                <q-popup-proxy
                  transition-show="scale"
                  transition-hide="scale"
                >
                  <q-date
                    v-model="noticeDate"
                    default-year-month="2020/01"
                    mask="YYYY-MM-DD HH:mm"
                  />
                </q-popup-proxy>
              </q-icon>
            </template>

            <template v-slot:append>
              <q-icon
                name="access_time"
                class="cursor-pointer"
              >
                <q-popup-proxy
                  transition-show="scale"
                  transition-hide="scale"
                >
                  <q-time
                    v-model="noticeDate"
                    mask="YYYY-MM-DD HH:mm"
                    format24h
                    now-btn
                  />
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </div>
      </q-form>
    </q-card-section>
  </q-card>
</template>

<script>
export default {
  name: 'RecordEdit',
  data () {
    return {
      title: '',
      recordType: null,
      date: null,
      openDialog: false,
      noticeDate: null,
      options: [
        {
          id: 1,
          desc: 'first'
        }
      ]
    }
  },
  methods: {
    onSubmitForm (e) {
      console.log(e)
    },
    resetForm () {
      this.title = ''
      this.recordType = null
      this.date = null
      this.noticeDate = null
    }
  }
}
</script>

<style scoped>

</style>
