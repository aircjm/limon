<template>
  <div class="row">
    <div class="q-pa-md col-md-4 col-12">
      <div class="row">
        <div class="col-6">
          <p></p>
          <q-tree
            :nodes="simple"
            node-key="id"
            @lazy-load="lazyLoad"
            selected-color="primary"
            v-model:selected="selected"
            @update:selected="v => updateCurrentNote(v)"
          />
        </div>
        <div class="col-6">
          <q-list>
            <q-item>
              <q-card>
                <q-card-section>title</q-card-section>
                <q-card-section>context</q-card-section>
              </q-card>
            </q-item>
          </q-list>
        </div>
      </div>

    </div>
    <div class="q-pa-md col-md-8 col-12">
      <tip-tap-editor/>
    </div>
  </div>
</template>

<script>

import {ref} from "@vue/reactivity";
import TipTapEditor from "components/tipTapEditor/TipTapEditor";

export default {
  name: 'AllNote',
  components: {TipTapEditor},
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
      console.log(v)
    }


    return {
      lazyLoad,
      updateCurrentNote,
      selected,
      splitterModel: ref(50),
      simple: [
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
