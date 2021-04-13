import React, {useState} from "react";
import CustomEditor from "./CustomEditor";
import p from "../helpers/p";
import h from "../helpers/h";
import {client} from "../../../request/request";
import 'remirror/styles/all.css';

function processRemirror(e) {
    let res = "";
    const content = e.state.doc.content.content;
    content.forEach((e) => {
        if (e.content.content[0]) {
            res += e.content.content[0].text + "\r\n";
        } else {
            return "";
        }
    });

    return res;
}

const NoteEditor = (props) => {
    const [text, setText] = useState();
    if (
        h.testFn({
            test: {
                state: {
                    doc: {
                        content: {
                            content: [{content: {content: [{text: "hi"}]}}],
                        },
                    },
                },
            },
            fn: processRemirror,
            expect: `hi\r\n`,
        }).res
    ) {
        //console.log("Note parsing works");
    } else {
        throw new Error("Error parsing note");
    }

    function addNote(e) {
        const note = {
            title: "",
            content: text
        };

        // 请求接口
        client.post("/note/editNote", note)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    function onChangeHandler(e) {
        setText(processRemirror(e));
    }

    return (
        <div
            className={p.appendID(props.className, "noteEditor", " ")}
            id={props.id}
        >
            <Header title={props.title} icon="save-outline"></Header>
            <div className="viewBody">
                <CustomEditor onChangeHandler={onChangeHandler}></CustomEditor>
                <div>
                    <div className="off userinfo">用户信息</div>
                    <div className="textGroup">
                        <Input
                            icon="fa-user"
                            id={p.appendID(props.id, "personNameInput", "-")}
                            text="Attach to Person"
                            type="text"
                            hideLabel="true"
                            autoComplete="true"
                            list="true"
                        ></Input>
                        <div id="personList"></div>
                        <Input
                            icon="fa-tags"
                            id={p.appendID(props.id, "noteTagText", "-")}
                            text="Tags"
                            type="text"
                            hideLabel="true"
                            autoComplete="true"
                            list="true"
                        ></Input>
                        <div id="tagList"></div>
                        <Input
                            icon="fa-calendar-alt"
                            id={p.appendID(props.id, "noteDateInput", "-")}
                            text="Date"
                            type="date"
                            hideLabel="true"
                            autoComplete="false"
                        ></Input>
                    </div>
                    <CheckedField title="Check in?" childID="checkinDate">
                        <Input
                            icon="fa-calendar-alt"
                            id={p.appendID(props.id, "checkinDateInput", "-")}
                            text="Date"
                            type="date"
                            hideLabel="true"
                            autoComplete="false"
                        ></Input>
                    </CheckedField>
                    <div id={p.appendID(props.id, "noteCheckGroup", "-")}>
                        <div id={p.appendID(props.id, "emailSelf", "-")}>
                            <input
                                type="checkbox"
                                id={p.appendID(props.id, "emailSelfCB", "-")}
                            />
                            <label htmlFor="emailSelfCB">Email to Myself</label>
                        </div>
                        <div id={p.appendID(props.id, "nextDay", "-")}>
                            <input
                                type="checkbox"
                                id={p.appendID(props.id, "nextDayCB", "-")}
                            />
                            <label htmlFor="nextDayCB">Next day reminder</label>
                        </div>
                        <CheckedField
                            title="Custom Reminder"
                            childID={p.appendID(props.id, "customReminder", "-")}
                        >
                            <Input
                                icon="fa-calendar-alt"
                                id={p.appendID(props.id, "customReminderInput", "-")}
                                text="Date"
                                type="datetime-local"
                                hideLabel="true"
                                autoComplete="false"
                            ></Input>
                        </CheckedField>

                        <div id={p.appendID(props.id, "addToBio", "-")}>
                            <input
                                type="checkbox"
                                id={p.appendID(props.id, "addToBioCB", "-")}
                            />
                            <label htmlFor="addToBioCB">Add to bio</label>
                        </div>
                    </div>
                </div>
                <div className="saveButton" onClick={addNote}>
                    <button>Save</button>
                </div>
            </div>
        </div>
    );
};

export default NoteEditor;