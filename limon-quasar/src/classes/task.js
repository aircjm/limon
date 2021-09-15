import AbstractModel from "src/classes/AbstractModel";


export default class TaskModel extends AbstractModel {

  constructor(data) {
    super();
    this.id = ''
    this.listId = ''
    /**
     * 名称
     * @type {string}  null
     */
    this.title = ''

    /**
     * 内容描述
     * @type {string} null
     */
    this.taskHtml = ''

    /**
     * 内容json
     * @type {null}
     */
    this.taskJson = null

    /**
     * 内容 markdown
     * @type {string}
     */
    this.taskMd = ''

    this.status = 0
    this.dueDate = null
    this.startTime = null
    this.endDate = null
    this.hexColor = null
    this.dueDate = null
  }

  defaults() {
    return {
      allDay: 0,
      descHtml: "",
      descJson: "",
      descMd: "",
      doneTime: null,
      dueTime: null,
      endTime: null,
      id: "",
      listId: 0,
      position: 0,
      projectId: 0,
      reminderTime: null,
      startTime: null,
      tagList: null,
      taskStatus: 0,
      timeZone: null,
      title: "",
      url: ""
    }
  }

}
