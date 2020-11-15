import axios from 'axios'

// 获取详情
export function getRecordDetail (id) {
  return axios({
    url: '/api/record/detail/' + id,
    method: 'get'
  })
}

// 保存
export function saveRecord (data) {
  return axios({
    url: '/api/record/save',
    method: 'post',
    data: data
  })
}
