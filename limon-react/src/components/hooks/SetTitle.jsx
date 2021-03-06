import {useContext, useEffect} from "react";
import {GlobalStore} from "../../store/global";

const SetTitle = (title) => {

    document.title = `${title ? title + ' - ' : ''} Jansora' Web App`

    const {dispatch} = useContext(GlobalStore);


    useEffect(() => {
        const payload = title;
        dispatch({ type: 'title', payload });
    },[dispatch, title]);
};
export default SetTitle;
