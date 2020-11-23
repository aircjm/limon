<template>
  <q-select
    filled
    v-model="model"
    use-input
    use-chips
    multiple
    input-debounce="0"
    @new-value="createValue"
    :options="filterOptions"
    map-options
    emit-value
    :readonly="readonly"
    :option-value="returnValue"
    @filter="filterFn"
  >
    <template v-slot:option="scope">
      <q-item
        v-bind="scope.itemProps"
        v-on="scope.itemEvents"
      >
        <q-item-section>
          <q-item-label v-html="scope.opt.label" />
        </q-item-section>
      </q-item>
    </template>
  </q-select>
</template>

<script>

import { saveTag } from 'src/api/tag'

export default {
  name: 'TagSelect',
  data () {
    return {
      readonly: false,
      model: [],
      options: [
        {
          label: 'default',
          value: -1
        }
      ],
      filterOptions: []
    }
  },
  watch: {
    model: function (val) {
      this.updateTags()
    }
  },
  methods: {
    updateTags () {
      this.$emit('update:tags', this.model)
      console.log(this.model)
    },
    async createValue (val, done) {
      this.readonly = true
      // Calling done(var) when new-value-mode is not set or "add", or done(var, "add") adds "var" content to the model
      // and it resets the input textbox to empty string
      // ----
      // Calling done(var) when new-value-mode is "add-unique", or done(var, "add-unique") adds "var" content to the model
      // only if is not already set
      // and it resets the input textbox to empty string
      // ----
      // Calling done(var) when new-value-mode is "toggle", or done(var, "toggle") toggles the model with "var" content
      // (adds to model if not already in the model, removes from model if already has it)
      // and it resets the input textbox to empty string
      // ----
      // If "var" content is undefined/null, then it doesn't tampers with the model
      // and only resets the input textbox to empty string

      if (val.length > 0) {
        // if (!stringOptions.includes(val)) {
        //   stringOptions.push(val)
        // }
        await saveTag({ name: val }).then(res => {
          const id = res.data.id
          this.options.push({
            value: id,
            label: val
          })
          this.model.push(id)
        })
        done(val, 'toggle')
        this.readonly = false
      }
    },
    filterFn (val, update) {
      update(() => {
        if (val === '' || val.length < 2) {
          this.filterOptions = this.options
        } else {
          const needle = val.toLowerCase()
          this.filterOptions = this.options.filter(
            v => v.label.toLowerCase().indexOf(needle) > -1
          )
        }
      })
    },
    returnValue (opt) {
      if ('value' in opt) {
        return opt.value
      }
    }
  }
}
</script>

<style scoped>

</style>
