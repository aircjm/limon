import {useContext} from 'react';
import {GlobalStore} from "../../store/global";

const GetCurrentUser = () => {

  const { user } = useContext(GlobalStore);
  return user;
}
export default GetCurrentUser;
