import React, {useState} from 'react';
import {Form, FormField, Input} from "semantic-ui-react";

const NewTask = (props) => {

    const [title, setTitle] = useState('')


    return <React.Fragment>
        <Form>
            <FormField>
                <Input></Input>
            </FormField>
        </Form>
    </React.Fragment>;
}

export default NewTask;
