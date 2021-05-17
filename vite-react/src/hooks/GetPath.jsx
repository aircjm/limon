import {useLocation} from "react-router-dom";

const GetPath = () => {
  const location = useLocation();
  const {pathname} = location;
  return pathname;
}

export default GetPath;
