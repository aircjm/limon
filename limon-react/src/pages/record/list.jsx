import React from "react";
import SetTitle from "../../components/hooks/SetTitle";
import RecordEdit from "./edit";


const RecordList = (props) => {
    SetTitle('记录列表');

    return (

        <React.Fragment>
            <RecordEdit/>
        </React.Fragment>
    )
};

export default RecordList;
