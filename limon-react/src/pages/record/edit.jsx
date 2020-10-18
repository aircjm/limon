import React from 'react'
import {Button, Form, Modal} from "semantic-ui-react";
import {SaveRecord} from "../../request/record";
import {DatePicker} from "antd";
import {parseDate} from "../../utils/DateUtil";


function RecordEdit() {

    function initFrom() {
        return {
            title: '',
            context: '',
            tagList: [],
            type: 0,
            status: 0,
            beginTime: null
        };
    }


    const [form, setForm] = React.useState(initFrom());
    const [option, setOption] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);

    function submit() {
        setOpen(false);
        setLoading(true);
        SaveRecord(form);
        setLoading(false);
    }

    function modifyTitle(e) {
        let date = parseDate(e.target.value);
        setForm({...form, beginTime: date})
    }



    return (
        <React.Fragment>
            <Modal
                onClose={() => setOpen(false)}
                onOpen={() => setOpen(true)}
                open={open}
                trigger={<Button size={"small"} icon={"add"} color={"green"}>新增</Button>}>
                <Modal.Header>新增</Modal.Header>
                <Modal.Content>
                    <Form>
                        <Form.Group widths='equal'>
                            <Form.Input fluid label='title' placeholder='title' value={form.title}
                                        onChange={(e) => setForm({...form, title: e.target.value})}  onBlur={modifyTitle}/>
                            <Form.Select
                                fluid
                                label='标签'
                                options={option}
                                placeholder='Gender'
                            />
                        </Form.Group>
                        <Form.Group label='begin'>
                            <TimePicker showToday value={form.beginTime}/>
                        </Form.Group>
                        <Form.TextArea label='描述内容'
                                       onChange={(e) => setForm({...form, context: e.target.value})}/>
                    </Form>
                </Modal.Content>
                <Modal.Actions>
                    <Button color='black' onClick={() => setOpen(false)}>
                        关闭
                    </Button>
                    <Button size={"small"}
                            content="提交"
                            labelPosition='right'
                            icon='checkmark'
                            onClick={() => submit()}
                            positive
                    />
                </Modal.Actions>
            </Modal>
        </React.Fragment>
    )
}


export default RecordEdit