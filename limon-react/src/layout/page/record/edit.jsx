import React from 'react'
import {Form} from "semantic-ui-react";


function RecordEdit() {


    function initFrom() {
        return {
            title: '',
            context: '',
            status: 0
        };
    }

    const [form, setForm] = React.useState(initFrom());
    const [option, setOption] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [value, setValue] = React.useState('');


    function submit() {
        setLoading(true);
        console.log(form);
        setLoading(false);
    }

    return (
        <Form>
            <Form.Group widths='equal'>
                <Form.Input fluid label='title' placeholder='title' value={form.title} onChange={(e) => setForm({ ...form, title: e.target.value })}/>
                <Form.Select
                    fluid
                    label='Gender'
                    options={option}
                    placeholder='Gender'
                />
            </Form.Group>
            <Form.Group inline>
                <label>Size</label>
                <Form.Radio
                    label='Small'
                    value='0'
                    checked={value === '0'}
                    onClick={(e) => setForm({ ...form, status: e.target.value })}
                />
                <Form.Radio
                    label='Medium'
                    value='1'
                    checked={value === '1'}
                    onChange={(e) => setForm({ ...form, status: e.target.value })}
                />
                <Form.Radio
                    label='Large'
                    value='2'
                    checked={value === '2'}
                    onChange={(e) => setForm({ ...form, status: e.target.value })}
                />
            </Form.Group>
            <Form.TextArea label='About' placeholder='Tell us more about you...' onChange={(e) => setForm({...form, context : e.target.value})} />
            <Form.Checkbox label='I agree to the Terms and Conditions' />
            <Form.Button loading={loading} onClick={() => submit()}>Submit</Form.Button>
        </Form>
    )
}


export default RecordEdit