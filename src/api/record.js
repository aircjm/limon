import axios from 'axios'

// 获取详情
export function getRecordDetail (id) {
  return axios({
    url: '/api/record/' + id,
    method: 'get'
  })
}
