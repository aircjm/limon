<template>
  <div>
    <q-splitter class="column"
      v-model="splitterModel"
    >
      <template v-slot:before>
        <div class="q-pa-md col-3">
          <q-tree
            :nodes="simple"
            node-key="id"
            @lazy-load="lazyLoad"
            selected-color="primary"
            v-model:selected="selected"
            @update:selected="v => updateCurrentNote(v)"
          />
        </div>
      </template>

      <template v-slot:after>
        <div class="q-pa-md col-9">
          {{selected}}
        </div>
      </template>
    </q-splitter>
  </div>
</template>

<script>

import {ref} from "@vue/reactivity";

export default {
  name: 'AllNote',
  setup() {


    const lazyLoad = ({node, key, done, fail}) => {
      // call fail() if any error occurs
      setTimeout(() => {
        // simulate loading and setting an empty node
        if (key.indexOf('Lazy load empty') > -1) {
          done([])
          return
        }
        const label = node.label
        done([
          {label: `${label}.1`},
          {label: `${label}.2`, lazy: true},
          {
            label: `${label}.3`,
            children: [
              {label: `${label}.3.1`, lazy: true},
              {label: `${label}.3.2`, lazy: true}
            ]
          }
        ])
      }, 1000)
    }

    const selected = ref('');

    const updateCurrentNote = (v) => {
      debugger;
      console.log(v)
    }



    return {
      lazyLoad,
      updateCurrentNote,
      selected,
      splitterModel: ref(50),
      simple:  [
        {
          id: 1,
          label: 'Node 1',
          children: [
            {
              id: 3,
              label: 'Node 1.1',
              lazy: true
            },
            {
              id: 4,
              label: 'Node 1.2',
              lazy: true
            }
          ]
        },
        {
          id: 5,
          label: 'Node 2',
          lazy: true
        }
      ]
    }
  }


}
</script>

<style scoped>

</style>
