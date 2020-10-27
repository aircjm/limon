import React, {Fragment, useEffect, useState} from 'react'
import * as HyperMD from "hypermd"
import {Button} from "semantic-ui-react";

function BaseEditor(props) {


    const id = props.id;

    const [readOnly, setReadOnly] = useState(false)
    const [mdContext, setMdContext] = useState();
    const [title, setTitle] = useState('');
    const [editor, setEditor] = useState(null);


    function handleSave(data) {
        setMdContext(data)
    }

    function getTags() {
    }

    function updateTitle() {

    }

    function handleKeyDown() {

    }

    function handleClick() {
        console.log(mdContext)
    }


    useEffect(() => {
        let myTextarea = document.getElementById('EditorCore-frame' + id);

        const editor = HyperMD.fromTextArea(myTextarea, {
            mode: {
                name: 'hypermd',
                hashtag: true,
            },
            hmdFold: {
                image: true,
                link: true,
                math: true,
                html: true, // maybe dangerous
                emoji: true,
                code: true,
            },
            hmdFoldCode: {
                flowchart: true,
                mermaid: true,
            },
            tabSize: 2,
        });
        editor.setSize(null, "100%");
        editor.setOption("lineNumbers", false);
        editor.setOption("foldGutter", false);
        editor.setOption("autofocus", false);

        // update title when user change content
        let updateTitle = HyperMD.debounce(() => {
            updateTitle();
        }, 500);
        editor.on('change', updateTitle);

        // parse tags when user change content
        let parseTags = HyperMD.debounce(() => {
            getTags();
        }, 2000);
        editor.on('change', parseTags);

        // save note content automaticaly
        let autoSaver = HyperMD.debounce(() => {
            handleSave(editor.getValue());
        }, 1000);
        editor.on('change', autoSaver);
        setEditor(editor)

    }, []);


    useEffect(() => {
        debugger;
        if (editor) {
            editor.setOption("readOnly", readOnly)
        }
    }, [readOnly]);


    return (
        <Fragment>
            {readOnly ? <Button onClick={() => setReadOnly(false)} content={"修改"}/>: <Button onClick={() => setReadOnly(true)} content={"保存"}/>}


            <div className='EditorCore-frame' onKeyDown={handleKeyDown}>
                <div className='EditorCore-HMD-wrapper'>
                    <textarea id={'EditorCore-frame' + id} onKeyDown={handleKeyDown}></textarea>
                </div>
                {/*<PerfectScrollbar>*/}
                {/**/}
                {/*</PerfectScrollbar>*/}

                {/*<div id={"math-preview-" + this.props.id} className="float-win float-win-hidden">*/}
                {/*<div className="float-win-title">*/}
                {/*<button className="float-win-close"><i className="fas fa-times"></i></button>*/}
                {/*Math Preview*/}
                {/*</div>*/}
                {/*<div className="float-win-content" id={"math-preview-content-" + id}></div>*/}
                {/*</div>*/}
            </div>

        </Fragment>

    )
}


export default BaseEditor