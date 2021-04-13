import React from "react";
import 'remirror/styles/all.css';
import {RemirrorProvider} from "@remirror/react";


const CustomEditor = (props) => {
    const manager = useManager([
        new BoldExtension(),
        new ItalicExtension(),
        new UnderlineExtension(),
        new StrikeExtension(),
    ]);

    return (
        <RemirrorProvider manager={manager} onChange={props.onChangeHandler}>
            <Editor/>
        </RemirrorProvider>
    );
};

export default CustomEditor;