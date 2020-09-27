import React from "react";
import {Button, Col, Form, Input, Row} from 'antd';


const layout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
};


const RecordEdit = (props) => {
    const id = props.id;


    React.useEffect(() => {
        console.log("id is " + id)

    }, id);


    const [title, setTitle] = React.useState("")
    const [context, setContext] = React.useState("")


    // 初始化

    function submit() {
        console.log(title + context)
    }


    return (
        <React.Fragment>
            <Row>
                <Col span={18} push={6}>

                </Col>
                <Form  {...layout} name="basic">
                    <Form.Item label="Username" name="username">
                        <Input value={title} onChange={event => setTitle(event.target.value)}/>
                    </Form.Item>

                    <Form.Item label="Password" name="password"
                               rules={[{required: true, message: 'Please input your password!'}]}
                    >
                        <Input value={context} onChange={event => setContext(event.target.value)}/>
                    </Form.Item>


                    <Form.Item>
                        <Button type="primary" htmlType="submit" onClick={submit}>
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </Row>



        </React.Fragment>


    )
};

export default RecordEdit;
