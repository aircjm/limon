import React, {useState} from 'react';
import {Input} from "@material-ui/core";

const NewTask = (props: any) => {

    const [title, setTitle] = useState('')
    return (
        <React.Fragment>
            <Input title={"输入框"} value={title} onChange={e => {setTitle(e.target.value)}}/>
        </React.Fragment>
    );
}

export default NewTask;
