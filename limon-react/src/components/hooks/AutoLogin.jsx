import {useContext, useEffect} from 'react';


import {GlobalStore} from "../../store/global";
import {FetchCurrentUser} from "../../request/user";

const AutoLogin = () => {

  const { dispatch } = useContext(GlobalStore);
  useEffect(()=> {
      FetchCurrentUser(user => dispatch({ type: 'user', payload: user }))
  }, [dispatch]);


   return null
}
export default AutoLogin;

