import {Editor as ByteEditor, Viewer as ByteViewer} from '@bytemd/react';
import {message} from "antd";
import gfm from '@bytemd/plugin-gfm';
import highlight from "@bytemd/plugin-highlight"
import image from "@bytemd/plugin-import-image"
import math from "@bytemd/plugin-math"
import zoom from "@bytemd/plugin-medium-zoom"
import mermaid from "@bytemd/plugin-mermaid"
import 'bytemd/dist/index.css'
import 'highlight.js/styles/vs.css';
import './override.css'
import {UploadFile} from "../../../request/utils";
import copy from 'copy-to-clipboard';
import {useRef} from "react";

import 'prosemirror-view/style/prosemirror.css';

import React from 'react';
import {schema} from 'prosemirror-schema-basic';
import {history, redo, undo} from "prosemirror-history";
import {keymap} from "prosemirror-keymap";
import {baseKeymap, Command, toggleMark} from "prosemirror-commands";
import {useProseMirror, ProseMirror} from 'use-prosemirror';
import {EditorState, Transaction} from "prosemirror-state";
import {Button} from "semantic-ui-react";
import styled from "styled-components";

// import frontmatter from "@bytemd/plugin-frontmatter"
// import gemoji from    "@bytemd/plugin-gemoji"
// import vega from    "@bytemd/plugin-vega"
const copyToClipboard = (content = '拷贝的内容') => {
  if (copy(content)) {
    message.success('上传成功，已拷贝到剪贴板')
  } else {
    message.warning('上传成功，拷贝到剪贴板失败， 请手动拷贝地址：' + content, 10)
  }

}
const plugins = [
  gfm(),
  // frontmatter(),
  // gemoji(),
  highlight(),
  image({
    async upload(files) {
      // TODO: upload image files and return urls
      if(files.length < 1) return ['']

      let url = ''
      const callback = (data) => {
        url = data;
        copyToClipboard(url)
      };
      await UploadFile(files[0], callback);
      // console.log("url", url, resp)
      return [url]
    }
  }),
  math(), zoom(), mermaid(),
];


export const Editor = ({value, setValue}) => {
  return (
      <ByteEditor
          value={value}
          plugins={plugins}
          onChange={setValue}
          editorConfig={{}}
      />
  )
}
export const Viewer = ({value}) => {
  return (
      <ByteViewer
          value={value}
          plugins={plugins}
      />
  )
}


export const ProseMirrorContainer = styled.div`
  padding: 16px;
  border: 1px solid #ccc;
  transition: 0.1s;
  border-radius: 5px;
  background-color: #fff;
`

export const ProseMirrorApp = styled.div`
  font-family: sans-serif;
`


export const ProEditor = (props) => {

  const toggleBold = toggleMarkCommand(schema.marks.strong);
  const toggleItalic = toggleMarkCommand(schema.marks.em);


  const customSchema = {
    schema,
    plugins: [
      history(),
      keymap({
        ...baseKeymap,
        "Mod-z": undo,
        "Mod-y": redo,
        "Mod-Shift-z": redo,
        "Mod-b": toggleBold,
        "Mod-i": toggleItalic
      })
    ]
  }

  function toggleMarkCommand(mark) {
    return (
        EditorState,
        dispatch
    ) => toggleMark(mark)(state, dispatch);
  }


  function isBold(state) {
    return isMarkActive(state, schema.marks.strong);
  }

  function isItalic(state) {
    return isMarkActive(state, schema.marks.em);
  }

  // https://github.com/ProseMirror/prosemirror-example-setup/blob/afbc42a68803a57af3f29dd93c3c522c30ea3ed6/src/menu.js#L57-L61
  function isMarkActive(state, mark) {
    const {from, $from, to, empty} = state.selection;
    return empty
        ? !!mark.isInSet(state.storedMarks || $from.marks())
        : state.doc.rangeHasMark(from, to, mark);
  }


  const [state, setState] = useProseMirror(customSchema);
  return (
      <ProseMirrorApp>
        <div className="Menu">
          <Button
              className="bold"
              isActive={isBold(state)}
              onClick={() => toggleBold(state, (tr) => setState(state.apply(tr)))}
          >
            B
          </Button>
          <Button
              className="italic"
              isActive={isItalic(state)}
              onClick={() => toggleItalic(state, (tr) => setState(state.apply(tr)))}
          >
            I
          </Button>
        </div>
        <ProseMirrorContainer>
          <ProseMirror
              className="ProseMirror"
              state={state}
              onChange={(value) => {
                setState(value);
                console.log(value)
              }}
          />
        </ProseMirrorContainer>
      </ProseMirrorApp>
  );
}