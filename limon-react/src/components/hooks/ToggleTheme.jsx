import {useContext, useEffect} from 'react';
import {THEME} from "../../utils/constants";
import {GlobalStore} from "../../store/global";

const ToggleTheme = () => {

  const { theme } = useContext(GlobalStore);

  useEffect(() => {
    document.body.classList.forEach(
      cur => document.body.classList.remove([cur]))

    document.body.classList.add(theme);
    localStorage.setItem(THEME, theme)
  }, [theme]);

  return null
}
export default ToggleTheme;
