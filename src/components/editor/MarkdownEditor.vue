<template>
  <q-card-section>
    <div
      id="vditor"
      class="vditor"
    />
  </q-card-section>
</template>

<script>
import Vditor from 'vditor'
import 'vditor/dist/index.css'
import { getToken } from 'src/utils/project'

export default {
  name: 'MarkdownEditor',
  props: {
    context: {
      type: String,
      default: ''
    }
  },
  watch: {
    context: function (val) {
      console.log(val)
      if (!this.contextMd) {
        this.vditor.setValue(val)
      }
    }
  },
  data: function () {
    return {
      config: {
        headers: {
          token: getToken()
        }
      },
      contextMd: this.context,
      vditor: null,
      toolbar: [
        'emoji',
        'headings',
        'bold',
        'italic',
        'strike',
        'link',
        '|',
        'list',
        'ordered-list',
        'check',
        'outdent',
        'indent',
        '|',
        'quote',
        'line',
        'code',
        'inline-code',
        'upload',
        'table',
        '|',
        'undo',
        'redo',
        '|',
        'outline',
        'edit-mode',
        'preview',
        'both',
        'fullscreen',
        {
          name: 'enter',
          tip: '换行',
          icon: '<svg version="1.1" id="layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 16 16" style="enable-background:new 0 0 16 16;" xml:space="preserve"><style type="text/css">.st0{fill:#2C2C2C;}</style><path class="st0" d="M0.7,0.6c0-0.3,0.2-0.5,0.5-0.5c0,0,0,0,0,0h14.1c0.3,0,0.5,0.2,0.5,0.5c0,0,0,0,0,0v0.1c0,0.3-0.2,0.5-0.5,0.5l0,0H1.2C0.9,1.1,0.7,0.9,0.7,0.6C0.7,0.7,0.7,0.6,0.7,0.6z M0.7,4.6c0-0.3,0.2-0.5,0.5-0.5h14.1c0.3,0,0.5,0.2,0.5,0.5v0v0.1c0,0.3-0.2,0.5-0.5,0.5l0,0H1.2C0.9,5.1,0.7,4.9,0.7,4.6C0.7,4.7,0.7,4.6,0.7,4.6z M0.7,8.6c0-0.3,0.2-0.5,0.5-0.5c0,0,0,0,0,0h12c0.3,0,0.5,0.2,0.5,0.5v0v0.1c0,0.3-0.2,0.5-0.5,0.5h0h-12C0.9,9.2,0.7,8.9,0.7,8.6L0.7,8.6C0.7,8.7,0.7,8.6,0.7,8.6z M0.7,12.6c0-0.3,0.2-0.5,0.5-0.5c0,0,0,0,0,0h5.5c0.3,0,0.5,0.2,0.5,0.5l0,0v0.1c0,0.3-0.2,0.5-0.5,0.5h0H1.2C0.9,13.2,0.7,13,0.7,12.6L0.7,12.6C0.7,12.7,0.7,12.6,0.7,12.6z"/><path class="st0" d="M15.2,8.1c-0.3,0-0.5,0.2-0.5,0.5v0v3.1c0,0.3-0.2,0.5-0.5,0.5h0h-2.8v-2l-2.8,2.1c-0.2,0.2-0.2,0.5-0.1,0.7c0,0,0.1,0.1,0.1,0.1l2.8,2.1v-2h3.8c0.3,0,0.5-0.2,0.5-0.5v0V8.6c0-0.3-0.2-0.5-0.5-0.5h0C15.3,8.1,15.2,8.1,15.2,8.1z"/></svg>',
          click: () => {
            this.vditor.insertValue('\n&nbsp;')
          }
        },
        '|',
        'edit-mode',
        {
          name: 'history',
          tip: '历史版本',
          icon: "<i class='fa fa-history fa-lg'></i>",
          click: () => {
            this.showHistory()
          }
        },
        {
          name: 'more',
          toolbar: [
            'content-theme',
            'export',
            'outline',
            'preview'
          ]
        }, {
          hotkey: '⌘-s',
          name: 'save',
          tip: '保存',
          icon: '<i class="fa fa-save fa-lg"/>',
          click: () => {
            console.log(this.vditor.getValue())
            this.saveContent(this.vditor.getValue())
          }
        }
      ]
    }
  },
  mounted () {
    this.init()
    this.vditor.clearCache()
  },
  methods: {
    init () {
      const options = {
        mode: 'ir',
        toolbar: this.toolbar,
        input: this.updateContextMd,
        preview: {
          hljs: {
            style: 'native',
            lineNumber: true
          },
          maxWidth: 4000
        },
        minHeight: 300,
        cache: false,
        // upload: {
        //   filename (name) {
        //     return name.replace(/\?|\\|\/|:|\||<|>|\*|\[|\]|\s+/g, '-')
        //   },
        //   handler (files) {
        //     debugger
        //     that.uploadFiles(files)
        //       .then({})
        //       .catch(e => {
        //         console.log(e)
        //       })
        //   },
        //   linkToImgUrl (files) {
        //     debugger
        //   }
        // }
        upload: {
          accept: 'image/*',
          url: '/api/api/task/upload',
          headers: {
            Authorization: 'Bearer ' + getToken()
          },
          linkToImgUrl: '/api/api/task/upload',
          filename (name) {
            return name.replace(/\?|\\|\/|:|\||<|>|\*|\[|\]|\s+/g, '-')
          },
          extraData: {
            id: 123
          },
          format: this.uploadFormat
        }
      }
      // 初始化编辑器
      this.vditor = new Vditor('vditor', options)
    },
    uploadFormat (files, response) {
      const respJson = JSON.parse(response)
      const succMap = {}
      const fileList = respJson.data
      for (let i = fileList.length - 1; i >= 0; i--) {
        const fileVo = fileList[i]
        succMap[fileVo.fileName] = fileVo.url
      }
      const result = {
        msg: respJson.msg,
        code: 0,
        data: {
          errFiles: [],
          succMap: succMap
        }
      }
      return JSON.stringify(result)
    },
    updateContextMd (contextMd) {
      this.$emit('update:context', contextMd)
    }
  }
}
</script>
<style>

</style>
