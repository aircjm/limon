import React from 'react';
import {NavLink} from "react-router-dom";
import {useResponsive} from 'ahooks';
import LoginStatus from "../../components/hooks/LoginStatus";
import GetCurrentUser from "../../components/hooks/GetCurrentUser";
import GetPath from "../../hooks/GetPath";


const NavBar = (props) => {
    const {activeNav, setActiveNav} = props;
    const responsive = useResponsive();

    const active = GetPath() === "/about/theme"


    const loginStatus = LoginStatus();
    const user = GetCurrentUser();
    return (
        <StyledNavBar activeNav={activeNav} responsive={responsive} onMouseEnter={() => setActiveNav(true)}
                      onMouseLeave={() => setActiveNav(false)}>
            <div className="logo">
                <NavLink to="/"><img src="logo.png" alt="logo"/></NavLink>
            </div>

            <NavLink to={"/notes"}>
                <div><Icon name="globe"/></div>
                博客列表
            </NavLink>
            {
                /* loginStatus ? <NavLink to="/about/user/current" className={GetPath() === "/about/user/current" ? 'active' : ''}>
                       <div><Icon name="circle" color="green"/></div>
                       {user.username}
                     </NavLink> :
                     <NavLink to="/login" className={GetPath() === "/about/user/login" ? 'active' : ''}>
                       <div><Icon name="sign in"/></div>
                       登录
                     </NavLink>*/
            }
        </StyledNavBar>
    )

}

export default NavBar;
