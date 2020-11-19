import axios from 'axios'

// 获取详情
export function getTaskDetail (id) {
  return axios({
    url: '/api/task/detail/' + id,
    method: 'get'
  })
}

// 保存
export function saveTask (data) {
  return axios({
    url: '/api/task/save',
    method: 'post',
    data: data
  })
}

// 列表搜索
export function getTaskList (data) {
  return axios({
    url: '/api/task/list',
    method: 'post',
    data: data
  })
}
