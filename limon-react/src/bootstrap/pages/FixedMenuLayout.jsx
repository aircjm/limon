import React from 'react'
import {Dropdown, Input, Menu,} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const FixedMenuLayout = () => {

    return (
        <React.Fragment>
            <Menu pointing>
                <Menu.Item as='a' header>
                    {/*主页图标*/}
                    {/* <Image size='mini' src='/logo.png' style={{ marginRight: '1em' }} />*/}
                    Limon
                </Menu.Item>
                <Menu.Item as={Link} to='/home'>Home</Menu.Item>
                <Menu.Item as={Link} to='/record'>record</Menu.Item>
                <Menu.Menu position={'right'}>
                    <Menu.Item>
                        <Input
                            className='icon'
                            icon={{name: 'search', link: true}}
                            placeholder='Search users...'
                        />
                    </Menu.Item>
                    <Dropdown item simple icon={'help'}>
                        <Dropdown.Menu direction={'left'}>
                            <Dropdown.Item as="a" target="_blank" href={`https://react.semantic-ui.com/`}
                                           text='Semantic UI React'/>
                            <Dropdown.Item as="a" target="_blank" href={`https://zh-hans.reactjs.org/`} text='React'
                                           icon={'react'}/>
                            <Dropdown.Divider/>
                            <Dropdown.Header>Header Item</Dropdown.Header>
                            <Dropdown.Item direction={'left'}>
                                <i className='dropdown icon'/>
                                <span className='text'>Item</span>
                                <Dropdown.Menu direction={'left'}>
                                </Dropdown.Menu>
                            </Dropdown.Item>
                            <Dropdown.Item>List Item</Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                    <Menu.Item as={Link} to='/login'>Log-in</Menu.Item>
                </Menu.Menu>
            </Menu>
        </React.Fragment>
    )
};

export default FixedMenuLayout