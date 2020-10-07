import React from 'react'
import {Dropdown, Menu,} from 'semantic-ui-react'
import {useHistory} from "react-router-dom";

const FixedMenuLayout = () => {


    const history = useHistory();

    function toHome() {
        history.push("/home")
    }

    function toLogin() {
        history.push("/login")
    }

    return (
        <React.Fragment>
            <Menu pointing secondary>
                <Menu.Item as='a' header>
                    {/*主页图标*/}
                    {/* <Image size='mini' src='/logo.png' style={{ marginRight: '1em' }} />*/}
                    limon
                </Menu.Item>
                <Menu.Item as='a' onClick={toHome}>Home</Menu.Item>

                <Dropdown item simple text='Dropdown'>
                    <Dropdown.Menu>
                        <Dropdown.Item>List Item</Dropdown.Item>
                        <Dropdown.Item>List Item</Dropdown.Item>
                        <Dropdown.Divider/>
                        <Dropdown.Header>Header Item</Dropdown.Header>
                        <Dropdown.Item>
                            <i className='dropdown icon'/>
                            <span className='text'>Submenu</span>
                            <Dropdown.Menu>
                                <Dropdown.Item>List Item</Dropdown.Item>
                                <Dropdown.Item>List Item</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown.Item>
                        <Dropdown.Item>List Item</Dropdown.Item>
                    </Dropdown.Menu>
                </Dropdown>

                <Menu.Item position='right' onClick={toLogin}>Log-in</Menu.Item>
            </Menu>
        </React.Fragment>
    )
}

export default FixedMenuLayout