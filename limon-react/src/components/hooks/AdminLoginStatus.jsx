import {useContext, useEffect, useState} from 'react';

import {GlobalStore} from "../../store/global";

const AdminLoginStatus = () => {

  const [status, setStatus] = useState(false);
  const {user} = useContext(GlobalStore);
  useEffect(() => {

      setStatus((user.name !== ""))
  }, [user]);


  return status
}
export default AdminLoginStatus;
