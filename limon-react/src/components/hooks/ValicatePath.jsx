import {useLocation} from "react-router-dom";

const ValidatePath = (path) => {
  const location = useLocation();
  const {pathname} = location;
  return pathname === path;
}

export default ValidatePath;
