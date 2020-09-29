import React from "react";
import {StyledHeader} from "../../styled/header";
import {Header as TitleHeader, Icon} from "semantic-ui-react";
import {Divider} from "antd";
import Theme from "./theme";
import User from "./user";
import Add from "./add";
import Help from "./help";
import {Link, NavLink} from "react-router-dom";
import GetTitle from "../../components/hooks/GetTitle";
import AdminLoginStatus from "../../components/hooks/AdminLoginStatus";
import Links from "./links";
import HeaderSearch from "./HeaderSearch";


const Header = (props) => {


    const title = GetTitle();
    const loginStatus = AdminLoginStatus();
    const {bootstrap} = props;

    return (
        <StyledHeader bootstrap={bootstrap}>
            <div className="left">
                {
                    !bootstrap && <>
                        <Link to={'/'}><Icon name="home"/></Link>
                        <Divider type="vertical" style={{margin: '0 10px'}}/>
                        <NavLink to={"/post"} title={"博客"}>
                            <Icon name="blogger"/>
                        </NavLink>
                        <Divider type="vertical" style={{margin: '0 10px'}}/>
                        <NavLink to={"/topic"} title={"专栏"}><Icon name="book"/>
                        </NavLink>
                        <Divider type="vertical" style={{margin: '0 10px'}}/>
                        <NavLink to={"/project"} title={"项目"}>
                            <Icon name="paper plane"/>
                        </NavLink>
                        <Divider type="vertical" style={{margin: '0 10px'}}/>
                        <NavLink to={"/feedback"} title={"留言板"}>
                            <Icon name="comment"/>
                        </NavLink>
                    </>
                }
            </div>

            {/*中间标题*/}
            <div className="middle">
                {!bootstrap && <TitleHeader >{title}</TitleHeader>}
            </div>


            {/*右侧按钮*/}
            <div className="right">
                {!bootstrap && <React.Fragment>
                    <HeaderSearch/>
                        <Divider type="vertical" style={{margin: '0 16px 0 50px'}}/>
                        {
                            loginStatus && <React.Fragment>
                                <Add/>
                                <Divider type="vertical" style={{margin: '0 16px'}}/>
                            </React.Fragment>
                        }
                        <Theme/>
                        <Divider type="vertical" style={{margin: '0 16px'}}/>
                        <Links/>
                        <Divider type="vertical" style={{margin: '0 16px'}}/>
                        <Help/>
                        <Divider type="vertical" style={{margin: '0 16px'}}/>
                        <User/>
                    </React.Fragment>
                }

            </div>
        </StyledHeader>
    )
}

export default Header;
