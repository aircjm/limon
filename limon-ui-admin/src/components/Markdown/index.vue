<template>
  <div class="container">
    <div class="markdown-body">
      <div v-html="compileMarkdown"></div>
    </div>
  </div>
</template>

<script>
  import Marked from "marked";
  // 这里引入想要的样式
  import highlight from "highlight.js";
  import 'highlight.js/styles/github.css'

  let renderMd = new Marked.Renderer();
  Marked.setOptions({
    renderer: renderMd,
    gfm: true,
    tables: true,
    breaks: false,
    pedantic: false,
    sanitize: false,
    smartLists: true,
    smartypants: false,
    // 引入样式
    highlight: function (code) {
      return highlight.highlightAuto(code).value;
    }
  });

  export default {
    name: 'MarkdownParse',
    data() {
      return {
        article: {
          content: "加载中……"
        }
      };
    },
    computed: {
      compileMarkdown() {
        return Marked(this.article.content, {sanitize: true});
      }
    }
  };
</script>
