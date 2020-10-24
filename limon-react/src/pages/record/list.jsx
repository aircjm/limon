import React from "react";
import SetTitle from "../../components/hooks/SetTitle";
import RecordEdit from "./edit";
import BaseEditor from "../../components/editor/mdEditor/BaseEditor";


const RecordList = (props) => {
    SetTitle('记录列表');

    return (

        <React.Fragment>
            <RecordEdit/>

            <BaseEditor/>
        </React.Fragment>
    )
};

export default RecordList;
