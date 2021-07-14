<template>
  <node-view-wrapper class="code-block">
    <button @click="copyCode">copyCode</button>
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
          console.log("copy code success")
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
