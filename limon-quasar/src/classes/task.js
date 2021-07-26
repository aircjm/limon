import AbstractModel from "src/classes/AbstractModel";


export default class TaskModel extends AbstractModel {

  constructor(data) {
    super();
    this.id = Number(this.id)
    this.listId = Number(this.listId)
    /**
     * 名称
     * @type {string}
     */
    this.name = null

  }

  defaults() {
    return {
      id: 0,
      name: '',
      projectId: 0,
      listId: 0,
      parentTaskId: 0,
      desc: '',
      status: 0,
      reminderDates: [],
      labels: [],
      dueDate: null,
      startDate: null,
      endDate: null,
      checkItemStates :[],
      hexColor: null,
      relatedTasks: {},
      attachments: [],
      pos :0,
      created: null,
      updated: null,
      subscribed: []
    }
  }

}
