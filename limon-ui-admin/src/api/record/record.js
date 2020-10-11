import request from '@/utils/request'

// 查询列表
export function listData(query) {
  return request({
    url: '/api/record/list',
    method: 'post',
    data: query
  })
}

// 新增
export function addRecord(data) {
  return request({
    url: '/api/record/add',
    method: 'post',
    data: data
  })
}
