<template>
  <div class="shadow-2" autocorrect="off" spellcheck="false" autocapitalize="off">
    <bubble-menu
      :editor="editor"
      v-if="editor"
    >
      <button class="q-mr-sm" icon="mdi-format-bold" @click="editor.chain().focus().toggleBold().run()" :class="{ 'is-active': editor.isActive('bold') }">
        Bold1
      </button>
      <button  class="q-mr-sm" @click="editor.chain().focus().toggleItalic().run()" :class="{ 'is-active': editor.isActive('italic') }">
        Italic
      </button>
      <button  class="q-mr-sm" @click="editor.chain().focus().toggleStrike().run()" :class="{ 'is-active': editor.isActive('strike') }">
        Strike
      </button>
    </bubble-menu>

    <floating-menu
      :editor="editor"
      v-if="editor"
    >
      <button class="q-mr-sm" @click="editor.chain().focus().toggleHeading({ level: 1 }).run()">
        H11
      </button>
      <button @click="editor.chain().focus().toggleHeading({ level: 2 }).run()"
              :class="{ 'is-active': editor.isActive('heading', { level: 2 }) }">
        H2
      </button>
      <button @click="editor.chain().focus().toggleBulletList().run()"
              :class="{ 'is-active': editor.isActive('bulletList') }">
        Bullet List
      </button>
    </floating-menu>
    <editor-content :editor="editor" style="min-height: 200px;"/>
  </div>
</template>

<script>

import {useEditor, EditorContent, BubbleMenu, FloatingMenu} from '@tiptap/vue-3'
import Image from '@tiptap/extension-image'
import StarterKit from "@tiptap/starter-kit";


export default {
  name: "TipTapEditor",
  components: {
    EditorContent, BubbleMenu, FloatingMenu
  },
  setup() {
    const editor = useEditor({
      content: '',
      extensions: [
        StarterKit,
      ],
    })
    return { editor }
  }
}
</script>

<style scoped>
</style>
