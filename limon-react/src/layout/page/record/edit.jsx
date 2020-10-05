import React from 'react'
import {Button, Form, Modal, Table} from "semantic-ui-react";
import {SaveRecord} from "../../../request/record";


function RecordEdit() {


    function initFrom() {
        return {
            title: '',
            context: '',
            tagList: [],
            type: 0,
            status: 0,
            checked: false
        };
    }

    const [form, setForm] = React.useState(initFrom());
    const [option, setOption] = React.useState([]);
    const [loading, setLoading] = React.useState(false);
    const [open, setOpen] = React.useState(false);


    function submit() {
        setOpen(false);
        setLoading(true);
        SaveRecord(form);
        setLoading(false);
    }

    return (
        <React.Fragment>
            <Modal
                onClose={() => setOpen(false)}
                onOpen={() => setOpen(true)}
                open={open}
                size="small"
                trigger={<Button size={"small"}>Show Modal</Button>}>
                <Modal.Header>新增</Modal.Header>
                <Modal.Content>
                    <Form>
                        <Form.Group widths='equal'>
                            <Form.Input fluid label='title' placeholder='title' value={form.title}
                                        onChange={(e) => setForm({...form, title: e.target.value})}/>
                            <Form.Select
                                fluid
                                label='标签'
                                options={option}
                                placeholder='Gender'
                            />
                        </Form.Group>
                        <Form.TextArea label='About' placeholder='Tell us more about you...'
                                       onChange={(e) => setForm({...form, context: e.target.value})}/>
                        <Form.Checkbox label='I agree to the Terms and Conditions' defaultChecked={form.checked}
                                       onClick={(e, data) => setForm({...form, checked: data.checked})}/>
                        <Form.Button loading={loading} onClick={() => submit()}>Submit</Form.Button>
                    </Form>
                </Modal.Content>
                <Modal.Actions>
                    <Button color='black' onClick={() => setOpen(false)}>
                        Nope
                    </Button>
                    <Button size={"small"}
                            content="提交"
                            labelPosition='right'
                            icon='checkmark'
                            onClick={() => submit()}
                            positive
                    />
                </Modal.Actions>
            </Modal>


            <Table striped>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell>Name</Table.HeaderCell>
                        <Table.HeaderCell>Date Joined</Table.HeaderCell>
                        <Table.HeaderCell>E-mail</Table.HeaderCell>
                        <Table.HeaderCell>Called</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>

                <Table.Body>
                    <Table.Row>
                        <Table.Cell>John Lilki</Table.Cell>
                        <Table.Cell>September 14, 2013</Table.Cell>
                        <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                        <Table.Cell>No</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Jamie Harington</Table.Cell>
                        <Table.Cell>January 11, 2014</Table.Cell>
                        <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                        <Table.Cell>Yes</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Jill Lewis</Table.Cell>
                        <Table.Cell>May 11, 2014</Table.Cell>
                        <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                        <Table.Cell>Yes</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>John Lilki</Table.Cell>
                        <Table.Cell>September 14, 2013</Table.Cell>
                        <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                        <Table.Cell>No</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>John Lilki</Table.Cell>
                        <Table.Cell>September 14, 2013</Table.Cell>
                        <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                        <Table.Cell>No</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Jamie Harington</Table.Cell>
                        <Table.Cell>January 11, 2014</Table.Cell>
                        <Table.Cell>jamieharingonton@yahoo.com</Table.Cell>
                        <Table.Cell>Yes</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>Jill Lewis</Table.Cell>
                        <Table.Cell>May 11, 2014</Table.Cell>
                        <Table.Cell>jilsewris22@yahoo.com</Table.Cell>
                        <Table.Cell>Yes</Table.Cell>
                    </Table.Row>
                    <Table.Row>
                        <Table.Cell>John Lilki</Table.Cell>
                        <Table.Cell>September 14, 2013</Table.Cell>
                        <Table.Cell>jhlilk22@yahoo.com</Table.Cell>
                        <Table.Cell>No</Table.Cell>
                    </Table.Row>
                </Table.Body>
            </Table>
        </React.Fragment>






    )
}


export default RecordEdit