<template>
  <node-view-wrapper class="code-block">
    <button @click="copyCode" style="user-select: none; transition: background 20ms ease-in 0s; cursor: pointer; display: inline-flex; align-items: center; white-space: nowrap; height: 25px; border-radius: 3px 0px 0px 3px; font-size: 11.5px; line-height: 1.2; padding: 4px 6px; color: rgb(55, 53, 47); background: rgb(234, 233, 229); font-weight: 400;">copyCode</button>
    <select contenteditable="false" v-model="selectedLanguage">
      <option :value="null">
        auto
      </option>
      <option disabled>
        —
      </option>
      <option v-for="(language, index) in languages" :value="language" :key="index">
        {{ language }}
      </option>
    </select>
    <pre><node-view-content as="code" /></pre>
  </node-view-wrapper>
</template>

<script>
import { NodeViewWrapper, NodeViewContent, nodeViewProps } from '@tiptap/vue-3'
import { copyToClipboard } from 'quasar'

export default {
  components: {
    NodeViewWrapper,
    NodeViewContent,
  },


  props: nodeViewProps,

  data() {
    return {
      languages: this.extension.options.lowlight.listLanguages(),
    }
  },

  methods: {
    copyCode() {
      const code = this.node.textContent
      console.log("copy code is:\n" + code)

      copyToClipboard(code)
        .then(() => {
          console.log("复制成功")

        })
        .catch(() => {
          // fail
        })
    }
  },


  computed: {
    selectedLanguage: {
      get() {
        return this.node.attrs.language
      },
      set(language) {
        this.updateAttributes({ language })
      },
    },
  },
}
</script>

<style lang="scss">
</style>
