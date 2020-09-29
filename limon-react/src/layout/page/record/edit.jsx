import React from 'react'
import {Form} from "semantic-ui-react";


function RecordEdit() {


    function initFrom() {
        return {
            title: '',
            context: '',
            type: 0,
            status: 0,
            checked: true
        };
    }

    const [form, setForm] = React.useState(initFrom());
    const [option, setOption] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [value, setValue] = React.useState(true);


    function submit() {
        setLoading(true);
        console.log(form);
        setLoading(false);
    }

    return (
        <Form>
            <Form.Group widths='equal'>
                <Form.Input fluid label='title' placeholder='title' value={form.title}
                            onChange={(e) => setForm({...form, title: e.target.value})}/>
                <Form.Select
                    fluid
                    label='Gender'
                    options={option}
                    placeholder='Gender'
                />
            </Form.Group>
            <Form.TextArea label='About' placeholder='Tell us more about you...'
                           onChange={(e) => setForm({...form, context: e.target.value})}/>
            <Form.Checkbox label='I agree to the Terms and Conditions' defaultChecked={form.checked}  onClick={(e, data) => setForm({...form, checked : data.checked})}/>
            <Form.Button loading={loading} onClick={() => submit()}>Submit</Form.Button>
        </Form>
    )
}


export default RecordEdit