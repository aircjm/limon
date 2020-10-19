import React from 'react'
import {SaveRecord} from "../../api/record";
import {useForm} from "react-hook-form";
import TextField from "@material-ui/core/TextField";


function EditRecord() {



    const [handleSubmit] = useForm();

    function initFrom() {
        return {
            title: '',
            contextDesc: '',
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

    const submitDialog = () => {
        setOpen(false);
    };


    return (
        <form onSubmit={handleSubmit}>
            <div>
                {/*<TextField input/>*/}
            </div>
        </form>
    )
}

export default EditRecord