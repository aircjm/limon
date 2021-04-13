import React from "react";
import {AnnotationExtension} from "remirror/extension/annotation";
import {AutoLinkExtension} from "remirror/extension/auto-link";
import {BidiExtension} from "remirror/extension/bidi";
import {BoldExtension} from "remirror/extension/bold";
import {CodeExtension} from "remirror/extension/code";
import {CodeBlockExtension} from "remirror/extension/code-block";
import {EmojiExtension} from "remirror/extension/emoji";
import {EventsExtension} from "remirror/extension/events";
import {HardBreakExtension} from "remirror/extension/hard-break";
import {HeadingExtension} from "remirror/extension/heading";
import {ImageExtension} from "remirror/extension/image";
import {ItalicExtension} from "remirror/extension/italic";
import {LinkExtension} from "remirror/extension/link";
import {MentionAtomExtension} from "remirror/extension/mention-atom";
import {CorePreset} from "remirror/preset/core";
import styled from "styled-components";
import {
    TableCellExtension,
    TableExtension,
    TableHeaderCellExtension,
} from "remirror/preset/table";
import {TablePreset, WysiwygPreset} from "remirror/preset/wysiwyg";
import {
    RemirrorProvider, useManager, useRemirror
} from "remirror/react";

const EXTENSIONS = () => [
    new CodeExtension(),
    new CodeBlockExtension(),
    new EmojiExtension(),
    new BoldExtension(),
    new BidiExtension(),
    new AnnotationExtension(),
    new AutoLinkExtension(),
    new MentionAtomExtension(),
    new ItalicExtension(),
    new LinkExtension(),
    new ImageExtension(),
    new HardBreakExtension(),
    new HeadingExtension(),
    new EventsExtension(),
    new TableExtension(),
    new TableCellExtension(),
    new TableHeaderCellExtension(),
    new WysiwygPreset(),
    new TablePreset(),
    new CorePreset(),
];


const Menu = () => {
    const {commands, active} = useRemirror({autoUpdate: true});

    return (
        <div>
            <button
                onClick={() => commands.toggleBold()}
                style={{fontWeight: active.bold() ? 'bold' : undefined}}
            >
                B
            </button>
            <button
                onClick={() => commands.toggleItalic()}
                style={{fontWeight: active.italic() ? 'bold' : undefined}}
            >
                I
            </button>
            <button
                onClick={() => commands.toggleUnderline()}
                style={{fontWeight: active.underline() ? 'bold' : undefined}}
            >
                U
            </button>
        </div>
    );
};

const EditorTextArea = styled.div`
overflow: hidden; 
overflow-wrap: break-word; 
resize: none; 
min-height: 108px;
`

/**
 * This component contains the editor and any toolbars/chrome it requires.
 */
const SmallEditor = () => {
    const {getRootProps, active, commands} = useRemirror({autoUpdate: true});

    return (
        <EditorTextArea {...getRootProps()} />
    );
};


const SmallEditorContainer = (props) => {
    const {onChange} = props;
    const extensionManager = useManager(EXTENSIONS);

    return (
        <React.Fragment>
            <RemirrorProvider
                manager={extensionManager}
                onChange={onChange}
            >
                <Menu/>
                <SmallEditor/>
            </RemirrorProvider>
        </React.Fragment>
    );
};

export default SmallEditorContainer;
