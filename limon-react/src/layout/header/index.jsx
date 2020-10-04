import React from "react";
import {Segment} from "semantic-ui-react";
import AdminLoginStatus from "../../components/hooks/AdminLoginStatus";


const Header = (props) => {
    const loginStatus = AdminLoginStatus();
    const {bootstrap} = props;

    const [fixed, setFixed] = React.useState(false);

    return (
        <Segment.Group horizontal>
            <Segment className="left">
                {/*{*/}
                {/*!bootstrap && <>*/}
                {/*<Link to={'/'}><Icon name="home"/></Link>*/}
                {/*<Divider type="vertical" style={{margin: '0 10px'}}/>*/}
                {/*<NavLink to={"/post"} title={"博客"}>*/}
                {/*<Icon name="blogger"/>*/}
                {/*</NavLink>*/}
                {/*<Divider type="vertical" style={{margin: '0 10px'}}/>*/}
                {/*<NavLink to={"/topic"} title={"专栏"}><Icon name="book"/>*/}
                {/*</NavLink>*/}
                {/*<Divider type="vertical" style={{margin: '0 10px'}}/>*/}
                {/*<NavLink to={"/project"} title={"项目"}>*/}
                {/*<Icon name="paper plane"/>*/}
                {/*</NavLink>*/}
                {/*<Divider type="vertical" style={{margin: '0 10px'}}/>*/}
                {/*<NavLink to={"/feedback"} title={"留言板"}>*/}
                {/*<Icon name="comment"/>*/}
                {/*</NavLink>*/}
                {/*</>*/}
                {/*}*/}
            </Segment>

            <Segment className="right">
                {/*<HeaderSearch/>*/}
                {/*{!bootstrap && <React.Fragment>*/}
                {/*<Divider type="vertical" style={{margin: '0 16px 0 50px'}}/>*/}
                {/*{*/}
                {/*loginStatus && <React.Fragment>*/}
                {/*<Add/>*/}
                {/*<Divider type="vertical" style={{margin: '0 16px'}}/>*/}
                {/*</React.Fragment>*/}
                {/*}*/}
                {/*<Theme/>*/}
                {/*<Divider type="vertical" style={{margin: '0 16px'}}/>*/}
                {/*<Links/>*/}
                {/*<Divider type="vertical" style={{margin: '0 16px'}}/>*/}
                {/*<Help/>*/}
                {/*<Divider type="vertical" style={{margin: '0 16px'}}/>*/}
                {/*<User/>*/}
                {/*<Menu*/}
                {/*fixed={fixed ? 'top' : null}*/}
                {/*inverted={!fixed}*/}
                {/*pointing={!fixed}*/}
                {/*secondary={!fixed}*/}
                {/*size='large'*/}
                {/*>*/}
                {/*<Container>*/}
                {/*<Menu.Item as='a' active>*/}
                {/*Home*/}
                {/*</Menu.Item>*/}
                {/*<Menu.Item as='a'>Work</Menu.Item>*/}
                {/*<Menu.Item as='a'>Company</Menu.Item>*/}
                {/*<Menu.Item as='a'>Careers</Menu.Item>*/}
                {/*<Menu.Item position='right'>*/}
                {/*<Button as='a' inverted={!fixed}>*/}
                {/*Log in*/}
                {/*</Button>*/}
                {/*<Button as='a' inverted={!fixed} primary={fixed} style={{marginLeft: '0.5em'}}>*/}
                {/*Sign Up*/}
                {/*</Button>*/}
                {/*</Menu.Item>*/}
                {/*</Container>*/}
                {/*</Menu>*/}
                {/*</React.Fragment>*/}
                {/*}*/}
            </Segment>

        </Segment.Group>
        // <StyledHeader bootstrap={bootstrap}>
        //
        //
        // </StyledHeader>
    )
}

export default Header;
