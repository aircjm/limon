import {useContext} from 'react';
import {GlobalStore} from "../../store/global";

const GetTheme = () => {

  const { theme } = useContext(GlobalStore);
  return theme;
}
export default GetTheme;
