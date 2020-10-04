import React from 'react'
import {Dropdown, Menu,} from 'semantic-ui-react'

const FixedMenuLayout = () => (
    <React.Fragment>
        <Menu size='mini'>
            <Menu.Item as='a' header>
                {/*<Image size='mini' src='/logo.png' style={{ marginRight: '1em' }} />*/}
                limon
            </Menu.Item>
            <Menu.Item as='a'>Home</Menu.Item>

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


            <Menu.Item position='right'>Login</Menu.Item>
        </Menu>

        {/*<Segment inverted vertical style={{ margin: '5em 0em 0em', padding: '5em 0em' }}>*/}
        {/*<Container textAlign='center'>*/}
        {/*<Grid divided inverted stackable>*/}
        {/*<Grid.Column width={3}>*/}
        {/*<Header inverted as='h4' content='Group 1' />*/}
        {/*<List link inverted>*/}
        {/*<List.Item as='a'>Link One</List.Item>*/}
        {/*<List.Item as='a'>Link Two</List.Item>*/}
        {/*<List.Item as='a'>Link Three</List.Item>*/}
        {/*<List.Item as='a'>Link Four</List.Item>*/}
        {/*</List>*/}
        {/*</Grid.Column>*/}
        {/*<Grid.Column width={3}>*/}
        {/*<Header inverted as='h4' content='Group 2' />*/}
        {/*<List link inverted>*/}
        {/*<List.Item as='a'>Link One</List.Item>*/}
        {/*<List.Item as='a'>Link Two</List.Item>*/}
        {/*<List.Item as='a'>Link Three</List.Item>*/}
        {/*<List.Item as='a'>Link Four</List.Item>*/}
        {/*</List>*/}
        {/*</Grid.Column>*/}
        {/*<Grid.Column width={3}>*/}
        {/*<Header inverted as='h4' content='Group 3' />*/}
        {/*<List link inverted>*/}
        {/*<List.Item as='a'>Link One</List.Item>*/}
        {/*<List.Item as='a'>Link Two</List.Item>*/}
        {/*<List.Item as='a'>Link Three</List.Item>*/}
        {/*<List.Item as='a'>Link Four</List.Item>*/}
        {/*</List>*/}
        {/*</Grid.Column>*/}
        {/*<Grid.Column width={7}>*/}
        {/*<Header inverted as='h4' content='Footer Header' />*/}
        {/*<p>*/}
        {/*Extra space for a call to action inside the footer that could help re-engage users.*/}
        {/*</p>*/}
        {/*</Grid.Column>*/}
        {/*</Grid>*/}

        {/*<Divider inverted section />*/}
        {/*<Image centered size='mini' src='/logo.png' />*/}
        {/*<List horizontal inverted divided link size='small'>*/}
        {/*<List.Item as='a' href='#'>*/}
        {/*Site Map*/}
        {/*</List.Item>*/}
        {/*<List.Item as='a' href='#'>*/}
        {/*Contact Us*/}
        {/*</List.Item>*/}
        {/*<List.Item as='a' href='#'>*/}
        {/*Terms and Conditions*/}
        {/*</List.Item>*/}
        {/*<List.Item as='a' href='#'>*/}
        {/*Privacy Policy*/}
        {/*</List.Item>*/}
        {/*</List>*/}
        {/*</Container>*/}
        {/*</Segment>*/}
    </React.Fragment>
)

export default FixedMenuLayout